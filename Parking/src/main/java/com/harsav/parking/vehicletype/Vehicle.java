package com.harsav.parking.vehicletype;

import lombok.Getter;

public abstract class Vehicle {

    protected String licensePlate;
    @Getter
    protected VehicleTypes type;

    protected Vehicle(String licensePlate, VehicleTypes type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

}
