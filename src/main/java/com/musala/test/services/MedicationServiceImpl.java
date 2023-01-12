package com.musala.test.services;

import com.musala.test.entities.medication.Medication;
import com.musala.test.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements BasicService<Medication> {
    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public Medication save(Medication obj) {
        return medicationRepository.save(obj);
    }

    @Override
    public Optional<Medication> fetch(long droneId) {
        return medicationRepository.findById(droneId);
    }

    @Override
    public List<Medication> fetchAll() {
        return medicationRepository.findAll();
    }

    @Override
    public void delete(Medication obj) {
        medicationRepository.delete(obj);
    }

    @Override
    public Medication getReferenceById(long objId) {
        return medicationRepository.getReferenceById(objId);
    }
}
