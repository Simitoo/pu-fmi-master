package com.project01_rent_a_car_api.P01RentACar_api.controllers;

import com.project01_rent_a_car_api.P01RentACar_api.constants.StatusMessages;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Offer;
import com.project01_rent_a_car_api.P01RentACar_api.http.AppResponse;
import com.project01_rent_a_car_api.P01RentACar_api.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService){
        this.offerService = offerService;
    }

    @PostMapping("/offers")
    public ResponseEntity<?> createNewOffer(@RequestBody Offer offer){

        System.out.println("Received offer creation request: " + offer);

        if(this.offerService.createOffer(offer)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_INSERT)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_INSERT)
                .build();
    }

    @GetMapping("/offers")
    public ResponseEntity<?> fetchAllOffers(){
        List<Offer> offers = this.offerService.getAllOffers();

        return AppResponse.success()
                .withDataAsArray(offers)
                .build();
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<?> fetchSingleOffer(@PathVariable int id){
        System.out.println("service update id" + id);

        Optional<Offer> offer = this.offerService.getOfferById(id);
        if(offer.isEmpty()){
            return AppResponse.error()
                    .withMessage(StatusMessages.DATA_NOT_FOUND)
                    .build();
        }

        return AppResponse.success()
                .withDataAsArray(offer)
                .build();
    }

    @PutMapping("/offers")
    public ResponseEntity<?> updateOffer(@RequestBody Offer offer){
        System.out.println("controller update id" + offer.getId());

        if(offerService.updateOffer(offer)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_UPDATE)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_UPDATE)
                .build();
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable int id){
        if(offerService.deleteOffer(id)){
            return AppResponse.success()
                    .withMessage(StatusMessages.SUCCESSFUL_DELETE)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.FAILED_DELETE)
                .build();
    }

    @PutMapping("/offers/{id}/accept")
    public ResponseEntity<?> setOfferAccepted(@PathVariable int id){
        if(offerService.acceptOffer(id)){
            return AppResponse.success()
                    .withMessage(StatusMessages.ACTIVE_OFFER)
                    .build();
        }

        return AppResponse.error()
                .withMessage(StatusMessages.OFFER_ALREADY_ACCEPTED)
                .build();
    }
}
