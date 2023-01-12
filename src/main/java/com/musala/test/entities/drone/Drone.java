package com.musala.test.entities.drone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.test.entities.medication.Medication;
import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

import java.util.*;

@Entity
public class Drone {

    public final static int MAX_WEIGHT = 500;
    public final static int MAX_SERIAL_LENGHT = 100;
    public final static double MIN_BATTERY_LVL_FOR_LOADING = 0.25;
    public enum DroneModel {
        LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String serialNo;

    private String model;

    private short weightLimit;

    @OneToOne(mappedBy="drone", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="ID", unique=true, nullable=false, updatable=false)
    private DroneStatus status;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="DRONE_ID", unique=false)
    @JsonIgnore
    private List<DroneLoad> load;

    public Drone() {}

    public Drone(String serialNo, String model, short weightLimit) {
        if (serialNo.length() > MAX_SERIAL_LENGHT)
            throw new IllegalArgumentException("Incorrect value of serial number. 100 characters max.");
        if (weightLimit < 0 && weightLimit > MAX_WEIGHT) {
            throw new IllegalArgumentException("Incorrect value of weight limit. Acceptable values are in range 0-500.");
        }
        this.serialNo = serialNo;
        model = model.toUpperCase();
        DroneModel.valueOf(model);
        this.model = model;
        this.weightLimit = weightLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public short getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(short weightLimit) {
        this.weightLimit = weightLimit;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public List<DroneLoad> getLoad() {
        return load;
    }

    public void setLoad(List<DroneLoad> load) {
        this.load = load;
    }
}
