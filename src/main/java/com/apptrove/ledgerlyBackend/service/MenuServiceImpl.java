package com.apptrove.ledgerlyBackend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.apptrove.ledgerlyBackend.entities.MenuItemMst;
import com.apptrove.ledgerlyBackend.entities.MenuMst;
import com.apptrove.ledgerlyBackend.entities.RoleMenuMst;
import com.apptrove.ledgerlyBackend.repository.MenuItemMstRepository;
import com.apptrove.ledgerlyBackend.repository.MenuMstRepository;
import com.apptrove.ledgerlyBackend.repository.RoleMenuMstRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LogManager.getLogger(MenuServiceImpl.class);
	
	private final RoleMenuMstRepository roleMenuMstRepository;
	
	private final MenuMstRepository menuMstRepository;
	
	private final MenuItemMstRepository menuItemMstRepository;
	
	@Override
	public Map<String, Object> getMenuMap(Integer roleId) {
		Map<String, Object> respObject = new HashMap<String, Object>();
		List<Integer> menuIdList = new ArrayList<Integer>();
		List<Integer> menuSubIdList = new ArrayList<Integer>();
		List<MenuMst> menuMstList = new ArrayList<MenuMst>();
		List<MenuItemMst> menuItemMstList = new ArrayList<MenuItemMst>();
		try {
			logger.info("Inside getMenuMap for roleId: {}",roleId);
			RoleMenuMst roleMenuMst = roleMenuMstRepository.findByRoleIdAndIsActive(roleId, true).orElseThrow(() -> new ResourceNotFoundException("Role Id: "+roleId+" not found."));
			String menuMapString = roleMenuMst.getMenuMap();
			String[] menuMappings = menuMapString.split("\\|");
			
			for (String mapping : menuMappings) {
				mapping = mapping.trim();
				
				String[] parts = mapping.split("=");
				if (parts.length == 2) {
					int menuId = Integer.parseInt(parts[0].trim());
					menuIdList.add(menuId);
					
					Arrays.stream(parts[1].split("~"))
						.map(String::trim)
						.map(Integer::parseInt)
						.forEach(menuSubIdList::add);
				}
			}
			menuMstList = menuMstRepository.findByMenuIdIn(menuIdList);
			menuItemMstList = menuItemMstRepository.findByMenuSubIdIn(menuSubIdList);
			
			respObject.put("menuMstList", menuMstList);
			respObject.put("menuItemMstList", menuItemMstList);
			logger.info("Exiting getMenuMap method");
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return respObject;
	}

}
