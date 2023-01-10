package com.musala.test.controllers;

import com.musala.test.entities.drone.Drone;
import com.musala.test.entities.drone.DroneLoad;
import com.musala.test.services.BasicService;
import com.musala.test.services.DroneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DroneController {

    @Autowired
    BasicService<Drone> droneService;

    @PostMapping("/drones")
    public Drone save(@RequestBody Drone drone) {
        /*
        fill drone specs and default status
	    updating DRONE and DRONE_STATUS tables
         */
        return droneService.save(drone);
    }


    @PostMapping("/drones/load/{id}")
    public Drone updateDroneLoad(@RequestBody DroneLoad droneLoad) {
        /*
        IN: { drone_id, [ {med_id, amnt} ] } OUT: confirmation message
	    updating DRONE_LOAD and DRONE_STATUS tables

	    Prevent the drone from being loaded with more weight that it can carry;
	    Prevent the drone from being in LOADING state if the battery level is **below 25%**
         */
        return null;
    }

    @GetMapping("/drones/load/{id}")
    public Drone getDroneLoad(@RequestBody DroneLoad droneLoad) {
        /*
        IN: {drone_id} OUT: {[{%med structure%}, amount]}
	    quering DRONE_LOAD table
         */
        return null;
    }

    @GetMapping("/drones/available")
    public List<Drone> getDroneLoad() {
        /*
        IN: request OUT: {drone specs, drone status, }
	    quering DRONE_STATUS and DRONE table

	    Prevent the drone from being in LOADING state if the battery level is **below 25%**
         */
        return null;
    }

    @GetMapping("/drones/battery/{id}")
    public Drone checkBatteryLevel() {
        /*
        IN: {drone_id} OUT: {drone_id, battery_lvl}
	    quering DRONE_STATUS table
         */
        return null;
    }

}
