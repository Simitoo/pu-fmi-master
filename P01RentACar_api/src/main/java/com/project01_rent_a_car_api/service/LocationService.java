package com.project01_rent_a_car_api.P01RentACar_api.service;

import com.project01_rent_a_car_api.P01RentACar_api.constants.ExceptionMessages;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Location;
import com.project01_rent_a_car_api.P01RentACar_api.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepo){
        this.locationRepository = locationRepo;
    }

    public boolean createLocation(Location location){
        return locationRepository.insertRecord(location);
    }

    public List<Location> getAllLocations(){
        return locationRepository.getAll();
    }

    public Optional<Location> getLocationById(int id){
        if(locationRepository.getById(id).isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        return locationRepository.getById(id);
    }
}
