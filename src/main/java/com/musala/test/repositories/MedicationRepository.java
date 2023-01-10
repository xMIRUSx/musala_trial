package com.musala.test.repositories;

import com.musala.test.entities.medication.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication, Long> {
    
}
