package com.musala.test.repositories;

import com.musala.test.entities.drone.DroneStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneStatusRepository extends CrudRepository<DroneStatus, Long>, JpaRepository<DroneStatus, Long> {

    List<DroneStatus> findByStateInAndBatteryLevelGreaterThan(List<String> states, double battery);
}
