package com.musala.test.repositories;

import com.musala.test.entities.drone.Drone;
import com.musala.test.entities.medication.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, Long>, JpaRepository<Medication, Long> {
    
}
