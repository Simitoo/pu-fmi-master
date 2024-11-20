package com.project01_rent_a_car_api.P01RentACar_api.repositories;

import com.project01_rent_a_car_api.P01RentACar_api.constants.ExceptionMessages;
import com.project01_rent_a_car_api.P01RentACar_api.constants.SQLStatements;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Location;
import com.project01_rent_a_car_api.P01RentACar_api.mappers.LocationRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository implements IRepository<Location> {

    private JdbcTemplate db;

    public LocationRepository(JdbcTemplate jdbcTemplate){
        this.db = jdbcTemplate;
    }

    @Override
    public boolean insertRecord(Location param) {
        try {
            this.db.update(SQLStatements.INSERT_LOCATION,
                    param.getName());

            return true;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILD_INSERT_INTO_DATABASE,e);
        }
    }

    @Override
    public List<Location> getAll() {
        return this.db.query(SQLStatements.SELECT_ALL_LOCATIONS, new LocationRowMapper());
    }

    @Override
    public Optional<Location> getById(int id) {
        List<Location> locations = this.db.query(SQLStatements.SELECT_ALL_LOCATIONS, new LocationRowMapper());
        return locations.isEmpty() ? Optional.empty() : Optional.of(locations.get(0));
    }

    @Override
    public boolean updateRecord(int id, Location param) {
        return false;
    }

    @Override
    public boolean deleteRecord(int id) {
        return false;
    }
}
