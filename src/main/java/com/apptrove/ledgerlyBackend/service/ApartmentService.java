package com.apptrove.ledgerlyBackend.service;

import java.util.Map;

import com.apptrove.ledgerlyBackend.payload.ApartmentOccupantModel;
import com.apptrove.ledgerlyBackend.payload.CommReqObj;

public interface ApartmentService {
	
	public Map<String, Object> getAllResidentDetails();
	
	public Map<String, Object> saveNewResident(ApartmentOccupantModel apartmentOccupantModel);

	public Map<String, Object> getBldngAndAptmntData();
	
	public Map<String, Object> findAllApartmentData(CommReqObj reqObj);
}
