package com.musala.test.services;

import com.musala.test.entities.drone.DroneStatus;

import java.util.List;

public interface DroneStatusService extends BasicService<DroneStatus> {

    public List<DroneStatus> findAvailableForLoading(List<String> states, double battery);
}
