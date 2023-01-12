package com.musala.test.repositories;

import com.musala.test.entities.drone.DroneLoad;
import com.musala.test.entities.drone.DroneStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneLoadRepository extends CrudRepository<DroneLoad, Long>, JpaRepository<DroneLoad, Long> {

    List<DroneLoad> findByDroneId(long droneId);
}
