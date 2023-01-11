package com.musala.test.services;

import com.musala.test.entities.drone.DroneStatus;
import com.musala.test.repositories.DroneStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneStatusServiceImpl implements DroneStatusService {
    @Autowired
    DroneStatusRepository droneStatusRepository;

    @Override
    public DroneStatus save(DroneStatus obj) {
        return droneStatusRepository.save(obj);
    }

    @Override
    public Optional<DroneStatus> fetch(long id) {
        return droneStatusRepository.findById(id);
    }

    @Override
    public void delete(DroneStatus obj) {
        droneStatusRepository.delete(obj);
    }

    @Override
    public DroneStatus getReferenceById(long objId) {
        return droneStatusRepository.getReferenceById(objId);
    }

    @Override
    public List<DroneStatus> findAvailableForLoading(List<String> states, double battery) {
        return droneStatusRepository.findByStateInAndBatteryLevelGreaterThan(states, battery);
    }
}
