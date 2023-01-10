package com.musala.test.services;

import com.musala.test.entities.drone.Drone;

public interface DroneService {

    public Drone save(Drone drone);

    public Drone update(Drone drone);

    public Drone fetch(long droneId);

    public void delete(Drone drone);
}
