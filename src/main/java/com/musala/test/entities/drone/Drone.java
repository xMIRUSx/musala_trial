package com.musala.test.entities.drone;

import com.musala.test.entities.medication.Medication;
import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
TODO cascade updates on status, specs and load
 */
@Entity
public class Drone {

    public final static int MAX_WEIGHT = 500;
    public final static double MIN_BATTERY_LVL_FOR_LOADING = 0.25;
    enum DroneModel {
        LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String serialNo;

    private String model;

    private short weightLimit;

    @OneToOne
    @JoinColumn(name="ID", unique=true, nullable=false, updatable=false)
    @Lazy
    private DroneStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="DRONE_ID", unique=false)
    private List<DroneLoad> load = new ArrayList<>();

    public Drone() {}

    public Drone(String serialNo, String model, short weightLimit) {
        this.serialNo = serialNo;
        this.model = model;
        if (weightLimit < 0 && weightLimit > MAX_WEIGHT)
            throw new IllegalArgumentException("Incorrect value of weight limit. Acceptable values are in range 0-500.");
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
