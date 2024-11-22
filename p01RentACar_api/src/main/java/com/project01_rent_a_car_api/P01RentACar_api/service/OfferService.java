package com.project01_rent_a_car_api.P01RentACar_api.service;

import com.project01_rent_a_car_api.P01RentACar_api.constants.ExceptionMessages;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Car;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Offer;
import com.project01_rent_a_car_api.P01RentACar_api.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    private ClientService clientService;
    private CarService carService;

    public OfferService(OfferRepository offerRepository, ClientService clientService, CarService carService){
        this.offerRepository = offerRepository;
        this.clientService = clientService;
        this.carService = carService;
    }

    public boolean createOffer(Offer offer){

        System.out.println("Creating offer for client ID: " + offer.getClientId() + ", car ID: " + offer.getCarId());

        if(!isValidCarIdAndClientId(offer.getCarId(),offer.getClientId())){
            System.out.println("Invalid client or car ID.");
            return false;
        }

        boolean clientHasAccidents = clientService.hasAccidents(offer.getClientId());
        System.out.println("Client has accidents: " + clientHasAccidents);

        double carPrice = carService.getCarById(offer.getCarId()).get().getPricePerDay();
        System.out.println("Car has price: " + carPrice);

        offer.setFinalPrice(calculatePrice(offer.getRentDays(), clientHasAccidents, carPrice));

        return offerRepository.insertRecord(offer);
    }

    public List<Offer> getAllOffers(){
        return offerRepository.getAll();
    }

    public Optional<Offer> getOfferById (int id){
        Optional<Offer> offer = offerRepository.getById(id);
        if(offer.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        return offer;
    }

    public boolean updateOffer(Offer offer){
        System.out.println("service update id" + offer.getId());

        return offerRepository.updateRecord(offer.getId(),offer);
    }

    public boolean deleteOffer(int id){
        return offerRepository.deleteRecord(id);
    }

    public boolean acceptOffer(int id){
        Optional<Offer> offerToAccept = getOfferById(id);

        if(offerToAccept.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        if(clientService.acceptOffer(offerToAccept.get())){
            this.offerRepository.acceptOfferById(id);
            return true;
        }

        return false;
    }

    private double calculatePrice(int rentDays, boolean hasAccidents, double pricePerDay){

        double finalPrice = pricePerDay * rentDays;

        if(isOnWeekend(rentDays)){

            finalPrice *= 1.1;
        }

        if(hasAccidents){
            finalPrice += 200;
        }

        return finalPrice;
    }

    private boolean isOnWeekend(int rentDays){
        LocalDate today = LocalDate.now();
        LocalDate rentDay;

        for (int i = 0; i < rentDays; i++){
            rentDay = today.plusDays(i);

            if(rentDay.getDayOfWeek() == DayOfWeek.SATURDAY || rentDay.getDayOfWeek() == DayOfWeek.SUNDAY){
                return true;
            }
        }

        return false;
    }

    private boolean isValidCarIdAndClientId(int carId, int clientId){
        if(clientService.getClientById(clientId).isEmpty()){
            throw new RuntimeException(ExceptionMessages.NON_EXISTED_CLIENT + clientId);
        }

        if(carService.getCarById(carId).isEmpty()){
            throw new RuntimeException(ExceptionMessages.NON_EXISTED_CAR + carId);
        }

        return true;
    }
}
