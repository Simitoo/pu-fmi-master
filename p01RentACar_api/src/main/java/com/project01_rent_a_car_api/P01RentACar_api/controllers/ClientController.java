package com.project01_rent_a_car_api.P01RentACar_api.controllers;

import com.project01_rent_a_car_api.P01RentACar_api.constants.StatusMessages;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Client;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Offer;
import com.project01_rent_a_car_api.P01RentACar_api.http.AppResponse;
import com.project01_rent_a_car_api.P01RentACar_api.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<?> createNewClient(@RequestBody Client client){

        if(this.clientService.createClient(client)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_INSERT)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_INSERT)
                .build();
    }

    @GetMapping("/clients")
    public ResponseEntity<?> fetchAllClients(){
        List<Client> clientList = this.clientService.getAllClients();

        return AppResponse.success()
                .withDataAsArray(clientList)
                .build();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> fetchSingleClient(@PathVariable int id){
        Optional<Client> clientResponse = this.clientService.getClientById(id);
        if(clientResponse.isEmpty()){
            return AppResponse.error()
                    .withMessage(StatusMessages.DATA_NOT_FOUND)
                    .build();
        }

        return AppResponse.success()
                .withDataAsArray(clientResponse)
                .build();
    }

    @PutMapping("/clients")
    public ResponseEntity<?> updateClient(@RequestBody Client client){
        if(clientService.updateClient(client.getId(), client)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_UPDATE)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_UPDATE)
                .build();
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id){
        if(clientService.deleteClient(id)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_DELETE)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_DELETE)
                .build();
    }

    @PostMapping("/clients/acceptOffer")
    public ResponseEntity<?> acceptOffer(@RequestBody Offer offer){
        if(clientService.getClientById(offer.getClientId()).isEmpty()){
            return AppResponse.error()
                    .withMessage(StatusMessages.CLIENT_CANNOT_ACCEPT_OFFER)
                    .build();
        } else if (!clientService.acceptOffer(offer)){
            return AppResponse.error()
                    .withMessage(StatusMessages.OFFER_ALREADY_ACCEPTED)
                    .build();
        }

        return AppResponse.success()
                .withMessage(StatusMessages.CLIENT_ACCEPTED_OFFER + offer.getClientId() + offer.getId())
                .build();
    }
}
