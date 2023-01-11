package com.musala.test.services;

import com.musala.test.entities.drone.DroneLoad;
import com.musala.test.entities.drone.DroneStatus;

import java.util.List;

public interface DroneLoadService extends BasicService<DroneLoad> {

    public List<DroneLoad> getLoadForDrone(long droneId);
}
