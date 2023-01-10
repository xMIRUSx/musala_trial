package com.musala.test.entities.medication;

import jakarta.persistence.*;

@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name; // allowed only letters, numbers, ‘-‘, ‘_’

    private int weight;

    private String code; // allowed only upper case letters, underscore and numbers

    @OneToOne
    @JoinColumn(name="ID", unique=true, nullable=false, updatable=false)
    private MedicationImage image;

    public Medication(int weight, String code) {
        this.weight = weight;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MedicationImage getImage() {
        return image;
    }

    public void setImage(MedicationImage image) {
        this.image = image;
    }
}
