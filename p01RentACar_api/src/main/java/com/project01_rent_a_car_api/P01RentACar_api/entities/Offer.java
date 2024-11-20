package com.project01_rent_a_car_api.P01RentACar_api.entities;

import java.math.BigDecimal;

public class Offer {
    private int id;
    private int clientId;
    private int carId;
    private int rentDays;
    private byte onWeekends = 0;
    private BigDecimal finalPrice;
    private byte isActive = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getRentDays() {
        return rentDays;
    }

    public void setRentDays(int rentDays) {
        this.rentDays = rentDays;
    }

    public byte getOnWeekends() {
        return onWeekends;
    }

    public void setOnWeekends(byte onWeekends) {
        this.onWeekends = onWeekends;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}
