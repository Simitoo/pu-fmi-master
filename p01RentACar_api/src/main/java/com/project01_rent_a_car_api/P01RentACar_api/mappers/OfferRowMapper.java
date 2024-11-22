package com.project01_rent_a_car_api.P01RentACar_api.mappers;

import com.project01_rent_a_car_api.P01RentACar_api.entities.Offer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferRowMapper implements RowMapper<Offer> {

    @Override
    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Offer offer = new Offer();
        offer.setId(rs.getInt("offer_id"));
        offer.setClientId(rs.getInt("client_id"));
        offer.setCarId(rs.getInt("car_id"));
        offer.setRentDays(rs.getInt("rent_days"));
        offer.setFinalPrice(rs.getDouble("final_price"));
        offer.setIsActive(rs.getByte("is_active"));

        return offer;
    }
}
