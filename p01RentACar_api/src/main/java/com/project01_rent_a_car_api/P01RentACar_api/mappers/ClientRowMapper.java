package com.project01_rent_a_car_api.P01RentACar_api.mappers;

import com.project01_rent_a_car_api.P01RentACar_api.entities.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException{
        Client client = new Client();
        client.setId(rs.getInt("client_id"));
        client.setName(rs.getString("name"));
        client.setLocation(rs.getString("location"));
        client.setPhone(rs.getString("phone"));
        client.setAge(rs.getInt("age"));
        client.setHasAccidents(rs.getByte("has_accidents"));
        client.setIsActive(rs.getByte("is_active"));

        return client;
    }
}
