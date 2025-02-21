package com.harsav.parking.controller;

import com.harsav.parking.service.ParkingService;
import com.harsav.parking.vehicletype.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ParkingController {

    private final ParkingService parkingService;

    // User can park and un-park the Vehicle

    @PostMapping("/park/{licensePlate}/{vehicle}")
    public String parkVehicle(@PathVariable String licensePlate, @PathVariable VehicleTypes vehicle){
        Vehicle veh;
        if (VehicleTypes.CAR == vehicle){
            veh = new Car(licensePlate);
        } else if (VehicleTypes.MOTORCYCLE.equals(vehicle)){
            veh = new Motorcycle(licensePlate);
        } else {
            veh = new Truck(licensePlate);
        }
        parkingService.parkVehicle(veh);
        return "done";
    }

    @PostMapping("/unpark/{licensePlate}/{vehicle}")
    public String unparkVehicle(@PathVariable String licensePlate, @PathVariable VehicleTypes vehicle){
        Vehicle veh;
        if (VehicleTypes.CAR == vehicle){
            veh = new Car(licensePlate);
        } else if (VehicleTypes.MOTORCYCLE.equals(vehicle)){
            veh = new Motorcycle(licensePlate);
        } else {
            veh = new Truck(licensePlate);
        }
        parkingService.unparkVehicle(veh);
        return "done";
    }
}
