package com.project01_rent_a_car_api.P01RentACar_api.entities;

public class Client {
    private int id;
    private String name;
    private String location;
    private String phone;
    private int age;
    private byte hasAccidents = 0;  // 0 - false; 1 - true;
    private byte isActive = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte getHasAccidents() {
        return hasAccidents;
    }

    public void setHasAccidents(byte hasAccidents) {
        this.hasAccidents = hasAccidents;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}
