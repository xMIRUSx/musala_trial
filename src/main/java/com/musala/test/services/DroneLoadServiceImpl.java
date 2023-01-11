package com.musala.test.services;

import com.musala.test.entities.drone.DroneLoad;
import com.musala.test.repositories.DroneLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneLoadServiceImpl implements DroneLoadService {
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

    @Override
    public DroneLoad getReferenceById(long objId) {
        return droneLoadRepository.getReferenceById(objId);
    }


    @Override
    public List<DroneLoad> getLoadForDrone(long droneId) {
        return droneLoadRepository.findByDroneId(droneId);
    }
}
