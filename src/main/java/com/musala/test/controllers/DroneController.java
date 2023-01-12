package com.musala.test.controllers;

import com.musala.test.entities.drone.Drone;
import com.musala.test.entities.drone.DroneLoad;
import com.musala.test.entities.drone.DroneStatus;
import com.musala.test.entities.medication.Medication;
import com.musala.test.services.BasicService;
import com.musala.test.services.DroneLoadService;
import com.musala.test.services.DroneStatusService;
import com.musala.test.wrappers.ErrorWrapper;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<Object> save(@RequestBody Drone drone) {
        if (drone.getSerialNo().length() > 100) {
            ResponseEntity<Object> re = new ResponseEntity<>(new ErrorWrapper("Drone's serial number is too long. 100 characters max."), HttpStatus.FORBIDDEN);
            return re;
        }

        if (drone.getWeightLimit() < 0 && drone.getWeightLimit() > Drone.MAX_WEIGHT) {
            ResponseEntity<Object> re = new ResponseEntity<>(new ErrorWrapper("Drone's weight limit is too high." + Drone.MAX_WEIGHT +" grams max."), HttpStatus.FORBIDDEN);
            return re;
        }

        try {
            Drone.DroneModel.valueOf(drone.getModel().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            ResponseEntity<Object> re = new ResponseEntity<>(new ErrorWrapper("Incorrect drone model."), HttpStatus.FORBIDDEN);
            return re;
        }

        //Drone d = droneService.save(drone);
        DroneStatus status = new DroneStatus(drone);
        status.setBatteryLevel(1);
        status.setTotalLoadWeight(0);
        status.setState(DroneStatus.State.IDLE.name());


        drone.setStatus(status);
        Drone d = droneService.save(drone);


        //droneStatusService.save(status);
        ResponseEntity<Object> re = new ResponseEntity<>(d, HttpStatus.OK);
        return re;
    }

    @GetMapping(path="/drones/{id}", produces="application/json")
    public ResponseEntity<Object> get(@PathVariable("id") long id) {
        Optional<Drone> d = droneService.fetch(id);
        if (d.isEmpty()) {
            ResponseEntity<Object> re = new ResponseEntity<>(new ErrorWrapper("Drone with id=" + id + " not found"), HttpStatus.NOT_FOUND);
            return re;
        }
        return new ResponseEntity<Object>(d.get(), HttpStatus.OK);
    }


    @PostMapping("/drones/load/{id}")
    public ResponseEntity<Object> updateDroneLoad(@PathVariable("id") long droneId, @RequestBody List<DroneLoad> droneLoadList) {
        Optional<Drone> d = droneService.fetch(droneId);
        if (d.isEmpty()) {
            ResponseEntity<Object> re = new ResponseEntity<>(new ErrorWrapper("Drone with id=" + droneId + " not found"), HttpStatus.NOT_FOUND);
            return re;
        }
        Drone drn = d.get();
        DroneStatus droneStatus = drn.getStatus();

        if (droneStatus.getBatteryLevel() < 0.25) {
            ResponseEntity<Object> re = new ResponseEntity<>(new ErrorWrapper("Drone's battery level is less than " + Drone.MIN_BATTERY_LVL_FOR_LOADING + "%. Loading is forbidden."), HttpStatus.FORBIDDEN);
            return re;
        }

        int loaded = droneStatus.getTotalLoadWeight();
        int newLoadWeight = loaded;
        for(DroneLoad dl : droneLoadList) {
            long medId = dl.getMedication().getId();
            Optional<Medication> med = medicationService.fetch(medId);
            if (med.isEmpty()) {
                ResponseEntity<Object> re = new ResponseEntity<>(new ErrorWrapper("Medication with id=" +medId+ " not found"), HttpStatus.NOT_FOUND);
                return re;
            }
            dl.setMedication(med.get());
            newLoadWeight += dl.getAmount() * med.get().getWeight();
        }
        if (newLoadWeight > drn.getWeightLimit()) {
            ResponseEntity<Object> re = new ResponseEntity<>(new ErrorWrapper("Load weight exceeds drone's capacity."), HttpStatus.FORBIDDEN);
            return re;
        }

        droneStatus.setState(newLoadWeight == drn.getWeightLimit() ? "LOADED" : "LOADING");
        droneStatus.setTotalLoadWeight(newLoadWeight);

        List<DroneLoad> oldDroneLoadList = droneLoadService.getLoadForDrone(droneId);
        droneLoadList.addAll(oldDroneLoadList);
        drn.setLoad(droneLoadList);
        drn.setStatus(droneStatus);
        droneService.save(drn);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/drones/load/{id}")
    public ResponseEntity<Object> getDroneLoad(@PathVariable("id") long id) {
        List<DroneLoad> dlList = droneLoadService.getLoadForDrone(id);
        return new ResponseEntity<Object>(dlList, HttpStatus.OK);

    }

    @GetMapping("/drones/available")
    public List<Drone> getAvailableDrones() {
        List<Drone> availableDrones = new ArrayList<>();
        List<DroneStatus> statusList = droneStatusService.findAvailableForLoading(Arrays.asList("IDLE", "LOADING"), Drone.MIN_BATTERY_LVL_FOR_LOADING);
        statusList.forEach(status -> availableDrones.add(status.getDrone()));

        return availableDrones;
    }

    @GetMapping(path="/drones/battery/{id}", produces="application/json")
    public ResponseEntity<Object> checkBatteryLevel(@PathVariable("id") long id) {
        Optional<DroneStatus> ds = droneStatusService.fetch(id);
        final Float[] battery = new Float[1];
        battery[0] = null;
        ds.ifPresent(status -> battery[0] = status.getBatteryLevel());
        return new ResponseEntity<Object>(battery[0], HttpStatus.OK);
    }

}
