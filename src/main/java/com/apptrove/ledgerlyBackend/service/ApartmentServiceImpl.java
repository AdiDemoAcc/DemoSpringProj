package com.apptrove.ledgerlyBackend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.apptrove.ledgerlyBackend.entities.ApartmentMst;
import com.apptrove.ledgerlyBackend.entities.ApartmentOccupant;
import com.apptrove.ledgerlyBackend.entities.BuildingMst;
import com.apptrove.ledgerlyBackend.exception.ResourceNotFoundException;
import com.apptrove.ledgerlyBackend.payload.ApartmentOccupantModel;
import com.apptrove.ledgerlyBackend.repository.ApartmentMstRepository;
import com.apptrove.ledgerlyBackend.repository.ApartmentOccupantRepository;
import com.apptrove.ledgerlyBackend.repository.BuildingMstRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

	private static final Logger logger = LogManager.getLogger(ApartmentServiceImpl.class);
	
	private final BuildingMstRepository buildingMstRepository;
	
	private final ApartmentMstRepository apartmentMstRepository;
	
	private final ApartmentOccupantRepository apartmentOccupantRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public Map<String, Object> getAllResidentDetails() {
		Map<String, Object> respObject = new HashMap<String, Object>();
		List<BuildingMst> bldngList = new ArrayList<BuildingMst>();
		List<ApartmentMst> aptmntMstList = new ArrayList<ApartmentMst>();
		List<ApartmentOccupant> aptmntOccpntList = new ArrayList<ApartmentOccupant>();
		try {
			logger.info("Inside getAllResidentDetails method");
			
			bldngList = buildingMstRepository.findByIsActive(1);
			logger.info("Found Building List {}",bldngList);
			
			aptmntMstList = apartmentMstRepository.findByIsActive(1);
			logger.info("Found Apartment List {}",aptmntMstList);
			
			aptmntOccpntList = apartmentOccupantRepository.findByIsActive(1);
			logger.info("Found Apartment Occupant List {}",aptmntOccpntList);
			
			respObject.put("bldngList", bldngList);
			respObject.put("aptmntMstList", aptmntMstList);
			respObject.put("aptmntOccpntList", aptmntOccpntList);
			
			logger.info("Exiting getAllResidentDetails method");
		} catch (Exception e) {
			logger.error("An error occurred: ",e.getMessage());
			e.printStackTrace();
		}
		return respObject;
	}

	@Override
	public Map<String, Object> saveNewResident(ApartmentOccupantModel apartmentOccupantModel) {
		Map<String, Object> respObject = new HashMap<String, Object>();
		try {
			logger.info("Inside saveNewResident method for apartment id: {}",apartmentOccupantModel.getAptmntId());
			
			ApartmentMst apartmentMst = apartmentMstRepository.findById(apartmentOccupantModel.getAptmntId())
					.orElseThrow(() -> new ResourceNotFoundException("Apartment id: "+apartmentOccupantModel.getAptmntId()+" not found"));
			
			ApartmentOccupant apartmentOccupant = modelMapper.map(apartmentOccupantModel, ApartmentOccupant.class);
			apartmentOccupant.setAptmntMst(apartmentMst);
			
			apartmentOccupant = apartmentOccupantRepository.save(apartmentOccupant);
			respObject.put("apartmentOccupant", apartmentOccupant);
			
			logger.info("Exiting saveNewResident method");
		} catch (Exception e) {
			logger.error("An error occurred: {}"+e.getMessage());
			e.printStackTrace();
		}
		return respObject;
	}

	@Override
	public Map<String, Object> getBldngAndAptmntData() {
		Map<String, Object> respObject = new HashMap<String, Object>();
		try {
			List<BuildingMst> bldngList = buildingMstRepository.findByIsActive(1);
			List<ApartmentMst> aptmntList = apartmentMstRepository.findByIsActive(1);
			respObject.put("bldngList", bldngList);
			respObject.put("aptmntList", aptmntList);
		} catch (Exception e) {
			logger.error("An error occurred: {}",e.getMessage());
			e.printStackTrace();
		}
		return respObject;
	}

}
