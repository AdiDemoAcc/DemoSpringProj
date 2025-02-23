package com.apptrove.ledgerlyBackend.service;

import java.util.Map;

import com.apptrove.ledgerlyBackend.entities.ApartmentOccupant;
import com.apptrove.ledgerlyBackend.payload.ApartmentOccupantModel;

public interface ApartmentService {
	
	public Map<String, Object> getAllResidentDetails();
	
	public Map<String, Object> saveNewResident(ApartmentOccupantModel apartmentOccupantModel);

	public Map<String, Object> getBldngAndAptmntData();
}
