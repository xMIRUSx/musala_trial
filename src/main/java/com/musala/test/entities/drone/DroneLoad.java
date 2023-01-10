package com.musala.test.entities.drone;

import com.musala.test.entities.medication.Medication;
import jakarta.persistence.*;
import org.springframework.data.annotation.Reference;

import java.util.List;

@Entity
public class DroneLoad {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="DRONE_ID", insertable=false, updatable=false)
    private Drone drone;

    @OneToOne
    @JoinColumn(name="MEDICATION_ID", insertable=false, updatable=false)
    private Medication medication;
    private int amount;
}
