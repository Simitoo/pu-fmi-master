package com.project02_solar_park_api.p02SolarPark_api.controllers;

import com.project02_solar_park_api.p02SolarPark_api.constants.ResponseMessages;
import com.project02_solar_park_api.p02SolarPark_api.entities.Customer;
import com.project02_solar_park_api.p02SolarPark_api.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        this.customerService.createCustomer(customer);
        return ResponseEntity.ok(customer);
    }

}
