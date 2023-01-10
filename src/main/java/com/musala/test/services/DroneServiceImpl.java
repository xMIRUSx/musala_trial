package com.musala.test.services;

import com.musala.test.entities.drone.Drone;
import com.musala.test.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneServiceImpl implements BasicService<Drone> {
    @Autowired
    DroneRepository droneRepository;

    @Override
    public Drone save(Drone obj) {
        return droneRepository.save(obj);
    }

    @Override
    public Optional<Drone> fetch(long droneId) {
        return droneRepository.findById(droneId);
    }

    @Override
    public void delete(Drone obj) {
        droneRepository.delete(obj);
    }
}
