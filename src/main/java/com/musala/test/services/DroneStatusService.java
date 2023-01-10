package com.musala.test.services;

import com.musala.test.entities.drone.DroneStatus;
import com.musala.test.entities.drone.DroneStatusId;

public interface DroneStatusService {

    public DroneStatus save(DroneStatus status);

    public DroneStatus update(DroneStatus status);

    public DroneStatus fetch(DroneStatusId id);

    public void delete(DroneStatus status);
}
