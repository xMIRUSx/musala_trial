package com.musala.test.repositories;

import com.musala.test.entities.drone.Drone;
import com.musala.test.entities.drone.DroneStatus;
import com.musala.test.entities.drone.DroneStatusId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneStatusRepository extends CrudRepository<DroneStatus, Long> {
    
}
