package com.musala.test.entities.drone;

import com.musala.test.entities.medication.Medication;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
TODO cascade updates on status, specs and load
 */
@Entity
public class Drone {

    private final static int MAX_WEIGHT = 500;
    enum DroneModel {
        LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String serialNo;

    private DroneModel model;

    private int weightLimit;

    @OneToOne
    @JoinColumn(name="DRONE_ID", unique=true, nullable=false, updatable=false)
    private DroneStatus status;

    @OneToMany
    @JoinColumn(name="DRONE_ID", unique=false, nullable=false, updatable=false)
    private List<DroneLoad> load;

    public Drone(String serialNo, DroneModel model, int weightLimit) {
        this.serialNo = serialNo;
        this.model = model;
        if (weightLimit < 0 && weightLimit > MAX_WEIGHT)
            throw new IllegalArgumentException("Incorrect value of weight limit. Acceptable values are in range 0-500.");
        this.weightLimit = weightLimit;
    }

    public Long getId() {
        return id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

}
