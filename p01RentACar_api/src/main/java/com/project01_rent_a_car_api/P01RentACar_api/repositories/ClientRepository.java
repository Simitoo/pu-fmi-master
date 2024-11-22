package com.project01_rent_a_car_api.P01RentACar_api.repositories;

import com.project01_rent_a_car_api.P01RentACar_api.constants.ExceptionMessages;
import com.project01_rent_a_car_api.P01RentACar_api.constants.SQLStatements;
import com.project01_rent_a_car_api.P01RentACar_api.entities.Client;

import com.project01_rent_a_car_api.P01RentACar_api.mappers.ClientRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository implements IRepository<Client> {

    private JdbcTemplate db;

    public ClientRepository(JdbcTemplate jdbcTemplate){
        this.db = jdbcTemplate;
    }

    @Override
    public boolean insertRecord(Client param) {

        try {
            db.update(SQLStatements.INSERT_CLIENT,
                    param.getName(),
                    param.getLocation(),
                    param.getPhone(),
                    param.getAge(),
                    param.getHasAccidents());

            return true;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILED_INSERT_INTO_DATABASE, e);
        }
    }

    @Override
    public List<Client> getAll() {

        return this.db.query(SQLStatements.SELECT_ALL_CLIENTS, new ClientRowMapper());
    }

    @Override
    public Optional<Client> getById(int id) {

        List<Client> clients = this.db.query(SQLStatements.SELECT_CLIENT_BY_ID, new ClientRowMapper(),id);
        return clients.isEmpty() ? Optional.empty() : Optional.of(clients.get(0));
    }

    @Override
    public boolean updateRecord(int id, Client param) {
        Optional<Client> currClient = getById(id);
        if(currClient.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        try {
            int rowsAffected = db.update(SQLStatements.UPDATE_CLIENT_BY_ID,
                    param.getName(),
                    param.getLocation(),
                    param.getPhone(),
                    param.getAge(),
                    param.getHasAccidents(),
                    id);

            return rowsAffected > 0;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILED_UPDATE_OPERATION, e);
        }
    }

    @Override
    public boolean deleteRecord(int id) {
        Optional<Client> clientForDelete = getById(id);
        if(clientForDelete.isEmpty()){
            throw new RuntimeException(ExceptionMessages.RECORD_NOT_FOUND);
        }

        try {
            int rowsAffected = this.db.update(SQLStatements.SOFT_DELETE_CLIENT_BY_ID, id);

            return rowsAffected > 0;
        } catch (Exception e){
            throw new RuntimeException(ExceptionMessages.FAILED_DELETE_OPERATION, e);
        }
    }
}
