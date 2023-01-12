package com.musala.test.entities.drone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class DroneStatus {
    public enum State {
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }

    @Id
    private Long droneId;
    @OneToOne
    @MapsId
    @JoinColumn(name="drone_id")
    @JsonIgnore
    private Drone drone;
    private float batteryLevel;
    private String state;
    private int totalLoadWeight;

    public DroneStatus() {}

    public DroneStatus(Drone drone) {
        this.drone = drone;
    }


    public float getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalLoadWeight() {
        return totalLoadWeight;
    }

    public void setTotalLoadWeight(int totalLoadWeight) {
        this.totalLoadWeight = totalLoadWeight;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
