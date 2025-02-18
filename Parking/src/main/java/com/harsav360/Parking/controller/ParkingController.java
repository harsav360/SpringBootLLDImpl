package com.harsav360.Parking.controller;

import com.harsav360.Parking.entity.ParkingLot;
import com.harsav360.Parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping("/freeSpots")
    public ResponseEntity<List<ParkingLot>> getFreeParkingSpots() {
        return new ResponseEntity<>(parkingService.freeParkingSpots(),HttpStatus.OK);
    }

    @PostMapping("/createParkingSpot")
    public ResponseEntity<Boolean> createParkingSpot(@RequestBody ParkingLot parkingLot){
        return new ResponseEntity<>(parkingService.createParkingSpot(parkingLot),HttpStatus.OK);
    }

    @PostMapping("/reserveParkingSpot/{id}")
    public ResponseEntity<String> reserveParkingSpot(@PathVariable String id){
        return new ResponseEntity<>(parkingService.reserveParking(id),HttpStatus.OK);
    }

    @PostMapping("/freeParkingSpot/{id}")
    public ResponseEntity<String> freeParkingSpot(@PathVariable String id){
        return new ResponseEntity<>(parkingService.freeParkingSpot(id),HttpStatus.OK);
    }
}
