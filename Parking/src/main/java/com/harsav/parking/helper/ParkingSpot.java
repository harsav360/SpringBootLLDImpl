package com.harsav.parking.helper;

import com.harsav.parking.vehicletype.Vehicle;
import com.harsav.parking.vehicletype.VehicleTypes;
import lombok.Getter;

@Getter
public class ParkingSpot {

    private final int spotNumber;
    private final VehicleTypes vehicleType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, VehicleTypes vehicleType){
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    public synchronized boolean isAvailable() {
        return this.parkedVehicle == null;
    }

    public synchronized void parkVehicle(Vehicle vehicle){
        if (isAvailable() && vehicle.getType() == vehicleType){
            parkedVehicle = vehicle;
        } else {
            throw new IllegalArgumentException("Invalid Vehicle type or spot not available");
        }
    }

    public synchronized void unparkVehicle(){
        parkedVehicle = null;
    }

}
