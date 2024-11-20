package com.project02_solar_park_api.p02SolarPark_api.repositories;

import com.project02_solar_park_api.p02SolarPark_api.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.isActive = 1")
    List<Customer> getAllActiveCustomers();

    @Query("SELECT c FROM Customer c WHERE c.id = :id AND c.isActive = 1")
    Customer getCustomerById(int id);

}
