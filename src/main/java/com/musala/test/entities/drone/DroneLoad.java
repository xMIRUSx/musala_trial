package com.musala.test.entities.drone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.test.entities.medication.Medication;
import jakarta.persistence.*;
import org.springframework.data.annotation.Reference;

import java.util.List;

@Entity
@Table(name="DRONE_LOAD")
public class DroneLoad {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne //(fetch = FetchType.LAZY)
    //@JoinColumn(name="DRONE_ID", insertable=false, updatable=false)
    @JsonIgnore
    private Drone drone;

    @ManyToOne
    //@JoinColumn(name="MEDICATION_ID", insertable=false, updatable=false)
    private Medication medication;
    private int amount;

    public DroneLoad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
