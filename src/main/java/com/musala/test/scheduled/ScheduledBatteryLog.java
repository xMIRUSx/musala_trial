package com.musala.test.scheduled;

import com.musala.test.entities.drone.Drone;
import com.musala.test.services.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledBatteryLog {
    private static final Logger log = LoggerFactory.getLogger(ScheduledBatteryLog.class);

    SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    @Autowired
    BasicService<Drone> droneService;

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {
        List<Drone> drones = droneService.fetchAll();
        log.info("\n{}", df.format(new Date()));
        log.info("ID, serial No, battery level");

        for (Drone d : drones) {
            log.info("{}, {}, {}", d.getId(), d.getSerialNo(), d.getStatus().getBatteryLevel());
        }

    }
}
