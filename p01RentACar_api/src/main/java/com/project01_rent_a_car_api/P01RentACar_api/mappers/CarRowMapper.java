package com.project01_rent_a_car_api.P01RentACar_api.mappers;

import com.project01_rent_a_car_api.P01RentACar_api.entities.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("car_id"));
        car.setModel(rs.getString("model"));
        car.setPricePerDay(rs.getBigDecimal("price_per_day"));
        car.setIsActive(rs.getByte("is_active"));
        car.setCityID(rs.getInt("city_id"));

        return car;
    }
}
