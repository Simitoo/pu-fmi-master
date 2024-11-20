package com.project02_solar_park_api.p02SolarPark_api.entities;

import com.project02_solar_park_api.p02SolarPark_api.constants.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "number_of_projects")
    private int numberOfProjects;

    @Enumerated(EnumType.ORDINAL)
    private Status isActive = Status.ACTIVE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    public void setNumberOfProjects(int numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

    public Status getIsActive() {
        return isActive;
    }

    public void setIsActive(Status isActive) {
        this.isActive = isActive;
    }
}
