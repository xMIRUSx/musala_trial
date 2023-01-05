package com.musala.test.entities.medication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MedicationImage {

    @Id
    @Column(name="MEDICATION_ID")
    private Long id;

    private String image; // coded in base64

    public MedicationImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
