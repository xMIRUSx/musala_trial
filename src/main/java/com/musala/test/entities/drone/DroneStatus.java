package com.musala.test.entities.drone;

import jakarta.persistence.*;

@Entity
public class DroneStatus {
    enum State {
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }

    @Id
    @Column(name="DRONE_ID")
    private Long id;
    @OneToOne(optional=false, mappedBy="droneStatus")
    private Drone drone;
    private float batteryLevel;
    private State state;
    private int totalLoadWeight;

    public DroneStatus(Drone drone) {
        this.drone = drone;
    }

    public Long getId() {
        return id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public float getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getTotalLoadWeight() {
        return totalLoadWeight;
    }

    public void setTotalLoadWeight(int totalLoadWeight) {
        this.totalLoadWeight = totalLoadWeight;
    }
}
