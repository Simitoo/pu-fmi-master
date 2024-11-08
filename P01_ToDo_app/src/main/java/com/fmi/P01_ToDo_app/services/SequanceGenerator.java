package com.fmi.P01_ToDo_app.services;

import org.springframework.stereotype.Service;

@Service
public class SequanceGenerator {
    private int sequanceId = 1;

    public int getNextId(){
        return this.sequanceId++;
    }

}
