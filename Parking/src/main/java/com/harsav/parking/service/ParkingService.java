package com.harsav.parking.service;

import com.harsav.parking.helper.Level;
import com.harsav.parking.vehicletype.Vehicle;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final List<Level> levels;
    Logger logger = LoggerFactory.getLogger(ParkingService.class);

    public void addLevel(Level level){
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle){
        for(Level level : levels){
            if (level.parkVehicle(vehicle)){
                logger.info("Vehicle Parked Successfully");
                return true;
            }
        }
        logger.info("Could not park Vehicle");
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.unparkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        for (Level level : levels) {
            level.displayAvailability();
        }
    }
}
