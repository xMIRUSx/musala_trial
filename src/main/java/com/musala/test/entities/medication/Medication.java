package com.musala.test.entities.medication;

import jakarta.persistence.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name; // allowed only letters, numbers, ‘-‘, ‘_’

    private int weight;

    private String code; // allowed only upper case letters, underscore and numbers

    @OneToOne
    @JoinColumn(name="ID", unique=true, nullable=false, updatable=false)
    private MedicationImage image;

    public static boolean validateName(String name) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9-_]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean validateCode(String code) {
        Pattern pattern = Pattern.compile("[A-Z0-9_]+");
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    public Medication() {}

    public Medication(int weight, String code) {
        this.weight = weight;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
