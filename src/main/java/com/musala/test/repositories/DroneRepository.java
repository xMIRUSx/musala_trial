package com.musala.test.repositories;

import com.musala.test.entities.drone.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Long>, JpaRepository<Drone, Long> {

}
