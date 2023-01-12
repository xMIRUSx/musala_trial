package com.musala.test.controllers;

import com.musala.test.entities.drone.Drone;
import com.musala.test.entities.drone.DroneLoad;
import com.musala.test.entities.drone.DroneStatus;
import com.musala.test.entities.medication.Medication;
import com.musala.test.services.BasicService;
import com.musala.test.services.DroneLoadService;
import com.musala.test.services.DroneStatusService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class DroneController {

    @Autowired
    BasicService<Drone> droneService;

    @Autowired
    DroneStatusService droneStatusService;

    @Autowired
    DroneLoadService droneLoadService;

    @Autowired
    BasicService<Medication> medicationService;

    @Autowired
    EntityManager em;

    @PostMapping("/drones")
    public Drone save(@RequestBody Drone drone) {
        if (drone.getSerialNo().length() > 100) {
            //TODO generate error
        }

        if (drone.getWeightLimit() < 0 && drone.getWeightLimit() > Drone.MAX_WEIGHT) {
            //TODO generate error
        }

        Drone d = droneService.save(drone);
        DroneStatus status = new DroneStatus(d.getId());
        status.setBatteryLevel(1);
        status.setTotalLoadWeight(0);
        status.setState(DroneStatus.State.IDLE.name());
        droneStatusService.save(status);

        return d;
    }

    @GetMapping("/drones/{id}")
    public Drone get(@PathVariable("id") long id) {
        Optional<Drone> d = droneService.fetch(id);
        return d.get();
    }


    @PostMapping("/drones/load/{id}")
    public String updateDroneLoad(@PathVariable("id") long droneId, @RequestBody List<DroneLoad> droneLoadList) {
        /*
        IN: { drone_id, [ {med_id, amnt} ] } OUT: confirmation message
	    updating DRONE_LOAD and DRONE_STATUS tables

	    Prevent the drone from being loaded with more weight that it can carry;
	    Prevent the drone from being in LOADING state if the battery level is **below 25%**
         */

        //long droneId = droneLoad.getDrone().getId();

        Drone drn = droneService.getReferenceById(droneId);
        if (drn == null) {
            // TODO make error
        }

        Optional<DroneStatus> ds = droneStatusService.fetch(droneId);
        if (ds.isEmpty()) {
            // TODO make error
        }
        DroneStatus droneStatus = ds.get();
        if (droneStatus.getBatteryLevel() < 0.25) {
            // TODO make error
        }

        int loaded = droneStatus.getTotalLoadWeight();
        int newLoadWeight = loaded;
        for(DroneLoad dl : droneLoadList) {
            Optional<Medication> med = medicationService.fetch(dl.getMedication().getId());
            if (med.isEmpty()) {
                // TODO make error
            }
            dl.setMedication(med.get());
            newLoadWeight += dl.getAmount() * med.get().getWeight();
        }
        if (newLoadWeight > drn.getWeightLimit()) {
            // TODO make error
        }
        droneStatus.setState(newLoadWeight == drn.getWeightLimit() ? "LOADED" : "LOADING");
        droneStatus.setTotalLoadWeight(newLoadWeight);

        //droneLoad.setDrone(drn);

        drn.getLoad().addAll(droneLoadList);

        droneService.save(drn);
        droneStatusService.save(droneStatus);
        //DroneLoad dl = droneLoadService.save(droneLoad);
        return "OK";
    }

    @GetMapping("/drones/load/{id}")
    public List<DroneLoad> getDroneLoad(@PathVariable("id") long id) {
        /*
        IN: {drone_id} OUT: {[{%med structure%}, amount]}
	    quering DRONE_LOAD table
         */
//        List<DroneLoad> dlList = droneLoadService.getLoadForDrone(id);
//        for(DroneLoad dl : dlList) {
//            dl.setDrone(null);
//        }

        Optional<Drone> dr = droneService.fetch(id);
        if (dr.isEmpty()) {
            // TODO error message
        }
        List<DroneLoad> dlList = dr.get().getLoad();

        return dlList;
    }

    @GetMapping("/drones/available")
    public List<DroneStatus> getAvailableDrones() {
        /*
        IN: request OUT: {drone specs, drone status, }
	    quering DRONE_STATUS and DRONE table

	    Prevent the drone from being in LOADING state if the battery level is **below 25%**

	    where STATE in ('IDLE', 'LOADING') and BATTERY_LVL > 0.25
         */
        //TODO return drone specs also
        return droneStatusService.findAvailableForLoading(Arrays.asList("IDLE", "LOADING"), 0.25);
    }


    //TODO wrap result in JSON
    @GetMapping("/drones/battery/{id}")
    public Float checkBatteryLevel(@PathVariable("id") long id) {
        Optional<DroneStatus> ds = droneStatusService.fetch(id);
        final Float[] battery = new Float[1];
        battery[0] = null;
        ds.ifPresent(status -> battery[0] = status.getBatteryLevel());
        return battery[0];
    }

}
