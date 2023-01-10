package com.musala.test.services;

import com.musala.test.entities.drone.DroneLoad;
import com.musala.test.repositories.DroneLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneLoadServiceImpl implements BasicService<DroneLoad> {
    @Autowired
    DroneLoadRepository droneLoadRepository;

    @Override
    public DroneLoad save(DroneLoad obj) {
        return droneLoadRepository.save(obj);
    }

    @Override
    public Optional<DroneLoad> fetch(long droneId) {
        return droneLoadRepository.findById(droneId);
    }

    @Override
    public void delete(DroneLoad obj) {
        droneLoadRepository.delete(obj);
    }
}
