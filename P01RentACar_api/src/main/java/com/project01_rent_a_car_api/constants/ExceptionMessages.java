package com.project01_rent_a_car_api.P01RentACar_api.constants;

public class ExceptionMessages {
    public static final String FAILED_INSERT_INTO_DATABASE = "Database error during INSERT operation";
    public static final String RECORD_NOT_FOUND = "Can't find a record with the given ID.";
    public static final String FAILED_UPDATE_OPERATION = "Database error during UPDATE operation";
    public static final String FAILED_DELETE_OPERATION = "Database error during DELETE operation";

    public static final String NON_EXISTED_CAR = "Car with the given ID is not found: ";
    public static final String NON_EXISTED_CLIENT = "Client with the give ID is not found: ";
    public static final String LOCATION_INVALID = "The given Location doesn't exist. You cannot add or change record with invalid location!";
}
