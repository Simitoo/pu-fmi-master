package com.project02_solar_park_api.p02SolarPark_api.services;

import com.project02_solar_park_api.p02SolarPark_api.entities.Customer;
import com.project02_solar_park_api.p02SolarPark_api.repositories.CustomerRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    public Customer createCustomer(Customer newCustomer){
        return customerRepo.save(newCustomer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.getAllActiveCustomers();
    }

    public Optional<Customer> getCustomerById(int id){
        return getCustomerById(id);
    }

    public boolean updateCustomer(int id, Customer updatedCustomer){
        if(customerRepo.existsById(id)){
            updatedCustomer.setId(id);
            customerRepo.save(updatedCustomer);
            return true;
        }

        return false;
    }

    public boolean deleteCustomer(int id){
        if(customerRepo.existsById(id)){
            customerRepo.deleteById(id);
            return true;
        }

        return false;
    }
}
