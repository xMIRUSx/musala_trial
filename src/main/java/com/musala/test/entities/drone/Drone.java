package com.musala.test.entities.drone;

import jakarta.persistence.*;

/*
TODO cascade updates on status, specs and load
 */
@Entity
public class Drone {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="DRONE_ID", unique=true, nullable=false, updatable=false)
    private DroneSpecs specs;

    @OneToOne
    @JoinColumn(name="DRONE_ID", unique=true, nullable=false, updatable=false)
    private DroneStatus status;

    @OneToOne
    @JoinColumn(name="DRONE_ID", unique=true, nullable=false, updatable=false)
    private DroneLoad load;

    public Drone(DroneSpecs specs) {
        this.specs = specs;
    }

    public Long getId() {
        return id;
    }

    public DroneSpecs getSpecs() {
        return specs;
    }

    public void setSpecs(DroneSpecs specs) {
        this.specs = specs;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public DroneLoad getLoad() {
        return load;
    }

    public void setLoad(DroneLoad load) {
        this.load = load;
    }
}
