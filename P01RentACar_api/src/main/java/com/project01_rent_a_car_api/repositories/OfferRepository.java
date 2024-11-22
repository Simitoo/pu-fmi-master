package com.project01_rent_a_car_api.P01RentACar_api.repositories;

import com.project01_rent_a_car_api.P01RentACar_api.constants.ExceptionMessages;
import com.project01_rent_a_car_api.P01RentACar_api.constants.SQLStatements;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Offer;
import com.project01_rent_a_car_api.P01RentACar_api.mappers.OfferRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OfferRepository implements IRepository<Offer> {

    private JdbcTemplate db;

    public OfferRepository(JdbcTemplate jdbcTemplate){
        db = jdbcTemplate;
    }

    @Override
    public boolean insertRecord(Offer param) {

        try {
            int rowAffected = db.update(SQLStatements.INSERT_OFFER,
                    param.getClientId(),
                    param.getCarId(),
                    param.getRentDays(),
                    param.getFinalPrice());

            return rowAffected > 0;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILED_INSERT_INTO_DATABASE);
        }
    }

    @Override
    public List<Offer> getAll() {

        return db.query(SQLStatements.SELECT_ALL_OFFERS, new OfferRowMapper());
    }

    @Override
    public Optional<Offer> getById(int id) {
        List<Offer> offers = db.query(SQLStatements.SELECT_OFFER_BY_ID, new OfferRowMapper(),id);
        return offers.isEmpty() ? Optional.empty() : Optional.of(offers.get(0));
    }

    @Override
    public boolean updateRecord(int id, Offer param) {
        Optional<Offer> currOffer = getById(id);
        if(currOffer.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        try {
            int rowsAffected = db.update(SQLStatements.UPDATE_OFFER_BY_ID,
                    param.getClientId(),
                    param.getCarId(),
                    param.getRentDays(),
                    param.getFinalPrice(),
                    id);

            return rowsAffected > 0;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILED_UPDATE_OPERATION, e);
        }
    }

    @Override
    public boolean deleteRecord(int id) {
        Optional<Offer> currOffer = getById(id);
        if(currOffer.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        try {
            int rowsAffected = db.update(SQLStatements.SOFT_DELETE_OFFER_BY_ID, id);

            return rowsAffected > 0;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILED_DELETE_OPERATION, e);
        }
    }

    public void acceptOfferById(int id){
        db.update(SQLStatements.ACCEPT_OFFER, id);
    }
}
