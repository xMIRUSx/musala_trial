package com.musala.test.entities.drone;

import com.musala.test.entities.medication.Medication;
import jakarta.persistence.*;
import org.springframework.data.annotation.Reference;

import java.util.List;

@Entity
public class DroneLoad {

    @Id
    private Long id;

    private Drone drone;

    @OneToOne
    private Medication medication;
    private int amount;
}
