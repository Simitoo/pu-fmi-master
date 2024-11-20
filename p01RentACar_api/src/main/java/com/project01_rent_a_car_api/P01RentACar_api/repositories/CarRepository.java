package com.project01_rent_a_car_api.P01RentACar_api.repositories;

import com.project01_rent_a_car_api.P01RentACar_api.constants.ExceptionMessages;
import com.project01_rent_a_car_api.P01RentACar_api.constants.SQLStatements;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Car;
import com.project01_rent_a_car_api.P01RentACar_api.mappers.CarRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class CarRepository implements IRepository<Car> {

    private JdbcTemplate db;

    public CarRepository(JdbcTemplate jdbcTemplate){
        db = jdbcTemplate;
    }

    @Override
    public boolean insertRecord(Car param) {

        try {
            db.update(SQLStatements.INSERT_CAR,
                    param.getModel(),
                    param.getPricePerDay(),
                    param.getCityID());

            return true;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILD_INSERT_INTO_DATABASE, e);
        }
    }

    @Override
    public List<Car> getAll() {

        return db.query(SQLStatements.SELECT_ALL_CARS, new CarRowMapper());
    }

    @Override
    public Optional<Car> getById(int id) {

        List<Car> cars = db.query(SQLStatements.SELECT_CAR_BY_ID, new CarRowMapper());
        return cars.isEmpty() ? Optional.empty() : Optional.of(cars.get(0));
    }

    @Override
    public boolean updateRecord(int id, Car param) {
        Optional<Car> currCar = getById(id);
        if(currCar.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        try {
            int rowsAffected = db.update(SQLStatements.UPDATE_CAR_BY_ID,
                    param.getModel(),
                    param.getPricePerDay(),
                    param.getCityID(),
                    id);

            return rowsAffected > 0;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILD_UPDATE_OPERATION, e);
        }
    }

    @Override
    public boolean deleteRecord(int id) {
        Optional<Car> currCar = getById(id);
        if(currCar.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        try {
            int rowsAffected = db.update(SQLStatements.SOFT_DELETE_CAR_BY_ID, id);

            return rowsAffected > 0;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILD_UPDATE_OPERATION, e);
        }
    }

    public List<Car> fillterCarsByLocation(int locationID){

        return db.query(SQLStatements.FILTER_CARS_BY_LOCATION, new CarRowMapper());
    }
}
