package com.musala.test.repositories;

import com.musala.test.entities.drone.DroneStatus;
import org.springframework.data.repository.CrudRepository;

public interface DroneStatusRepository extends CrudRepository<DroneStatus, Long> {
    
}
