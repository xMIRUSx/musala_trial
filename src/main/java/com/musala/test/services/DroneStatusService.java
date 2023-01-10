package com.musala.test.services;

import com.musala.test.entities.drone.DroneStatus;

public interface DroneStatusService {

    public DroneStatus save(DroneStatus status);

    public DroneStatus update(DroneStatus status);

    public DroneStatus fetch(long droneId);

    public void delete(DroneStatus status);
}
