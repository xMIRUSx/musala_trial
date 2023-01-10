package com.musala.test.services;

import com.musala.test.entities.drone.DroneStatus;
import com.musala.test.repositories.DroneStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneStatusServiceImpl implements BasicService<DroneStatus> {
    @Autowired
    DroneStatusRepository droneStatusRepository;

    @Override
    public DroneStatus save(DroneStatus obj) {
        return droneStatusRepository.save(obj);
    }

    @Override
    public Optional<DroneStatus> fetch(long id) {
        return null;
    }

    @Override
    public void delete(DroneStatus obj) {
        droneStatusRepository.delete(obj);
    }
}
