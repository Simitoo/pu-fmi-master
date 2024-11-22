package com.project01_rent_a_car_api.P01RentACar_api.service;

import com.project01_rent_a_car_api.P01RentACar_api.constants.ExceptionMessages;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Client;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Location;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Offer;
import com.project01_rent_a_car_api.P01RentACar_api.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    private LocationService locationService;

    public ClientService(ClientRepository clientRepo, LocationService locationService){
        this.clientRepository = clientRepo;
        this.locationService = locationService;
    }

    public boolean createClient(Client newClient){
        if(!isClientLocationExists(newClient.getLocation())){
            return false;
        }

        clientRepository.insertRecord(newClient);

        return true;
    }

    public List<Client> getAllClients(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClientById(int id){
        return clientRepository.getById(id);
    }

    public boolean updateClient (int id, Client updatedClient){
        return clientRepository.updateRecord(id, updatedClient);
    }

    public boolean deleteClient (int id){
        return clientRepository.deleteRecord(id);
    }

    public boolean acceptOffer(Offer offer){

        return offer.getIsActive() != 1;
    }

    public boolean hasAccidents(int id){
        Optional<Client> client = getClientById(id);
        if(client.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        return client.get().getHasAccidents() == 1;
    }

    private boolean isClientLocationExists(String location){
        for(Location currLocation : locationService.getAllLocations()){
            if(currLocation.getName().equalsIgnoreCase(location)){
                return true;
            }
        }

        return false;
    }
}
