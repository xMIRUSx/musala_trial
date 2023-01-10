package com.musala.test.repositories;

import com.musala.test.entities.drone.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone, Long> {

}
