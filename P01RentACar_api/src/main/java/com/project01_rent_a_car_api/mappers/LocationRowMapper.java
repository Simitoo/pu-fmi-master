package com.project01_rent_a_car_api.P01RentACar_api.mappers;

import com.project01_rent_a_car_api.P01RentACar_api.entities.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRowMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        location.setId(rs.getInt("location_id"));
        location.setName(rs.getString("city_name"));

        return location;
    }
}
