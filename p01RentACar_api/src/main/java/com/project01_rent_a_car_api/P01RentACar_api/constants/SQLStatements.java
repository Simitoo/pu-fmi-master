package com.project01_rent_a_car_api.P01RentACar_api.constants;

public class SQLStatements {
    // for Clients tb
    public static final String INSERT_CLIENT = "INSERT INTO Clients (name, location, phone, age, has_accidents) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_CLIENTS = "SELECT * FROM Clients WHERE is_active = 1";
    public static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Clients WHERE is_active = 1 AND client_id = ?";
    public static final String UPDATE_CLIENT_BY_ID = "UPDATE Clients SET name = ?, location = ?, phone = ?, age = ?, has_accidents = ? WHERE client_id = ?";
    public static final String SOFT_DELETE_CLIENT_BY_ID = "UPDATE Clients SET is_active = 0 WHERE client_id = ?";

    // for Location tb
    public static final String INSERT_LOCATION = "INSERT INTO Locations (name) VALUES (?)";
    public static final String SELECT_ALL_LOCATIONS = "SELECT * FROM Locations";
    public static final String SELECT_LOCATION_BY_ID = "SELECT * FROM Locations WHERE location_id = ?";

    // for Car tb
    public static final String INSERT_CAR = "INSERT INTO Cars (model, price_per_day, city_id) VALUES (?, ?, ?)";
    public static final String SELECT_ALL_CARS = "SELECT * FROM Cars WHERE is_active = 1";
    public static final String FILTER_CARS_BY_LOCATION = "SELECT * FROM Cars WHERE is_active = 1 AND city_id = ?";
    public static final String SELECT_CAR_BY_ID = "SELECT * FROM Cars WHERE is_active = 1 AND car_id = ?";
    public static final String UPDATE_CAR_BY_ID = "UPDATE Cars SET model = ?, price_per_day = ?, city_id = ? WHERE car_id = ?";
    public static final String SOFT_DELETE_CAR_BY_ID = "UPDATE Cars SET is_active = 0 WHERE car_id = ?";

    // for Offer tb
    public static final String INSERT_OFFER = "INSERT INTO Offer (client_id, car_id, rent_days, on_weekends, final_price) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_OFFERS = "SELECT * FROM Offers WHERE is_active = 1";
    public static final String SELECT_OFFER_BY_ID = "SELECT * FROM Offers WHERE is_active = 1 AND offer_id = ?";
    public static final String UPDATE_OFFER_BY_ID = "UPDATE Offers SET client_id = ?, car_id = ?, rent_days = ?, on_weekends = ?, final_price = ? WHERE offer_id = ?";
    public static final String SOFT_DELETE_OFFER_BY_ID = "UPDATE Offers SET is_active = 0 WHERE offer_id = ?";
}
