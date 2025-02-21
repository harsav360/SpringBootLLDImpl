package com.harsav.parking.helper;

import com.harsav.parking.vehicletype.Vehicle;
import com.harsav.parking.vehicletype.VehicleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private final int floor;
    private final List<ParkingSpot> parkingSpots;

    Logger logger = LoggerFactory.getLogger(Level.class);

    public Level(int floor,int numSpots){
        this.floor = floor;
        parkingSpots = new ArrayList<>(numSpots);

        double spotsForBikes = 0.50;
        double spotsForCars = 0.40;

        int numBikes = (int) (numSpots*spotsForBikes);
        int numCars = (int) (numSpots*spotsForCars);

        for(int i= 1; i <= numBikes;i++){
            parkingSpots.add(new ParkingSpot(i, VehicleTypes.MOTORCYCLE));
        }

        for(int i= numBikes+1; i <= numBikes+numCars;i++){
            parkingSpots.add(new ParkingSpot(i, VehicleTypes.CAR));
        }

        for(int i= numBikes+numCars+1; i <= numSpots;i++){
            parkingSpots.add(new ParkingSpot(i, VehicleTypes.TRUCK));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable() && spot.getVehicleType() == vehicle.getType()) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVehicle().equals(vehicle)) {
                spot.unparkVehicle();
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        logger.info("Level {} Availability ",floor);
        for (ParkingSpot spot : parkingSpots) {
            logger.info("Spot {}: {} {}",
                    spot.getSpotNumber(),
                    spot.isAvailable() ? "Available For" : "Occupied By",
                    spot.getVehicleType()
            );
        }
    }



}
