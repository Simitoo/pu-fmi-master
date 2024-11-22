package com.project01_rent_a_car_api.P01RentACar_api.service;

import com.project01_rent_a_car_api.P01RentACar_api.constants.ExceptionMessages;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Car;
import com.project01_rent_a_car_api.P01RentACar_api.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    private LocationService locationService;

    public CarService(CarRepository carRepo, LocationService locationService){
        this.carRepository = carRepo;
        this.locationService = locationService;
    }

    public boolean createCar(Car newCar){
        if(isLocationValid(newCar.getLocationId())){
            carRepository.insertRecord(newCar);
            return true;
        }

        return false;
    }

    public List<Car> getAllCars(){
        return carRepository.getAll();
    }

    public List<Car> getAllCarsByLocation(int locationId){
        return carRepository.filterCarsByLocation(locationId);
    }

    public Optional<Car> getCarById(int id){
        if(carRepository.getById(id).isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        return carRepository.getById(id);
    }

    public boolean updateCar(int id, Car newRecord){
        if(isLocationValid(newRecord.getLocationId())){
            carRepository.updateRecord(id, newRecord);
            return true;
        }

        return false;
    }

    public boolean deleteCar(int id){
        return carRepository.deleteRecord(id);
    }

    private boolean isLocationValid(int locationId){
        if(locationService.getLocationById(locationId).isEmpty()){
            throw new RuntimeException(ExceptionMessages.LOCATION_INVALID);
        }

        return true;
    }
}
