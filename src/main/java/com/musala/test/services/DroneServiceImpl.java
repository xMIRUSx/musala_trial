package com.musala.test.services;

import com.musala.test.entities.drone.Drone;
import com.musala.test.repositories.DroneRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DroneServiceImpl implements BasicService<Drone> {
    @Autowired
    DroneRepository droneRepository;

    @Override
    public Drone save(Drone obj) {
        return droneRepository.save(obj);
    }

    @Override
    public Optional<Drone> fetch(long droneId) {
        Optional<Drone> d = droneRepository.findById(droneId);
        return d;
    }

    @Override
    public List<Drone> fetchAll() {
        return droneRepository.findAll();
    }

    @Override
    public void delete(Drone obj) {
        droneRepository.delete(obj);
    }

    @Override
    public Drone getReferenceById(long objId) {
        return droneRepository.getReferenceById(objId);
    }


}
