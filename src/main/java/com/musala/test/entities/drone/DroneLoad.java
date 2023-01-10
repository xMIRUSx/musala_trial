package com.musala.test.entities.drone;

import com.musala.test.entities.medication.Medication;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class DroneLoad {

    @Id
    @Column(name="DRONE_ID")
    private Long id;

    @OneToOne
    private Medication medication;
    private int amount;
}
/*
drone_id; med_id; amount
drone_id; med_id; amount
drone_id; med_id; amount
 */
