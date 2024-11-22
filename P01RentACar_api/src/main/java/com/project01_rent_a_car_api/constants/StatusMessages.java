package com.project01_rent_a_car_api.P01RentACar_api.constants;

public class StatusMessages {
    public static final String SUCCESSFUL_INSERT = "Record has been inserted successfully! Good job!";
    public static final String FAILED_INSERT = "Record cannot be inserted!";
    public static final String SUCCESSFUL_UPDATE = "You're amazing! Update is successful! <3";
    public static final String FAILED_UPDATE = "Failed Update. You are not prepared!";
    public static final String SUCCESSFUL_DELETE = "Deleted! Record has been banished to the shadow realm!";
    public static final String FAILED_DELETE = "Delete failed";
    public static final String DATA_NOT_FOUND = "Can't find a record with the given ID. Try again! :)";

    public static final String ACTIVE_OFFER = "Offer: %d is now active!";
    public static final String CLIENT_ACCEPTED_OFFER = "Client: %d accepted Offer: %d";
    public static final String CLIENT_CANNOT_ACCEPT_OFFER = "This client cannot accept offer with different [client ID]!";
    public static final String OFFER_ALREADY_ACCEPTED = "This offer is already accepted";
}
