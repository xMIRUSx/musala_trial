package com.musala.test.services;

import com.musala.test.entities.drone.DroneLoad;

public interface DroneLoadService {

    public DroneLoad save(DroneLoad load);

    public DroneLoad update(DroneLoad load);

    public DroneLoad fetch(long droneId);

    public void delete(DroneLoad load);

}
