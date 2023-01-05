package com.musala.test.entities.drone;

import jakarta.persistence.*;

@Entity
public class DroneSpecs {

    private final static int MAX_WEIGHT = 500;
    enum DroneModel {
        LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
    }

    @Id @Column(name="DRONE_ID")
    private Long id;
    @OneToOne(optional=false, mappedBy="droneSpecs")
    private Drone drone;

    private String serialNo;

    private DroneModel model;

    private int weightLimit;

}
