package com.project01_rent_a_car_api.P01RentACar_api.controllers;

import com.project01_rent_a_car_api.P01RentACar_api.constants.StatusMessages;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Car;
import com.project01_rent_a_car_api.P01RentACar_api.http.AppResponse;
import com.project01_rent_a_car_api.P01RentACar_api.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarController {

    private CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @PostMapping("/cars")
    public ResponseEntity<?> createNewCar(@RequestBody Car car){

        if (car.getPricePerDay() == 0) {
            throw new RuntimeException("Received Car object: " + car);
        }

        if(this.carService.createCar(car)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_INSERT)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_INSERT)
                .build();
    }

    @GetMapping("/cars")
    public ResponseEntity<?> fetchAllCars(){
        List<Car> carList = this.carService.getAllCars();

        return AppResponse.success()
                .withDataAsArray(carList)
                .build();
    }

    @GetMapping("/cars/location/{location_id}")
    public ResponseEntity<?> fetchAllCarsByLocation(@PathVariable("location_id") int locationId){
        List<Car> filteredCars = this.carService.getAllCarsByLocation(locationId);

        return AppResponse.success()
                .withDataAsArray(filteredCars)
                .build();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<?> fetchSingleCar(@PathVariable int id){
        Optional<Car> carResponse = this.carService.getCarById(id);
        if(carResponse.isEmpty()){
            return AppResponse.error()
                    .withMessage(StatusMessages.DATA_NOT_FOUND)
                    .build();
        }

        return AppResponse.success()
                .withData(carResponse)
                .build();
    }

    @PutMapping("/cars")
    public ResponseEntity<?> updateCar(@RequestBody Car car){
        if(this.carService.updateCar(car.getId(), car)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_UPDATE)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_UPDATE)
                .build();
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id){
        if(this.carService.deleteCar(id)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_DELETE)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_DELETE)
                .build();
    }

}
