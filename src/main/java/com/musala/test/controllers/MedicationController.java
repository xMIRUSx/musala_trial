package com.musala.test.controllers;

import com.musala.test.entities.drone.Drone;
import com.musala.test.entities.medication.Medication;
import com.musala.test.services.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicationController {

    @Autowired
    BasicService<Medication> medicationService;

    @PostMapping
    public Medication save(@RequestBody Medication med) {

        return medicationService.save(med);
    }
}
