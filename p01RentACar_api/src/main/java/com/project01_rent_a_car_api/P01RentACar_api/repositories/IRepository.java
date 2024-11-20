package com.project01_rent_a_car_api.P01RentACar_api.repositories;

import java.util.List;
import java.util.Optional;

public interface IRepository<T>{
    boolean insertRecord(T param);
    List<T> getAll();
    Optional<T> getById(int id);
    boolean updateRecord(int id, T param);
    boolean deleteRecord(int id);

}
