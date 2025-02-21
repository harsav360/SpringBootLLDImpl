package com.harsav.parking.controller;


import com.harsav.parking.helper.Level;
import com.harsav.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ParkingService parkingService;
    // Admin Can create the levels

    // Admin can see the parked and un-parked spots

    @PostMapping("/level")
    public String createLevel(@RequestBody Level level){
        parkingService.addLevel(level);
        return "Done";
    }

    @GetMapping("/checkSpots")
    public String listParkingSpots(){
        parkingService.displayAvailability();
        return "Done";

    }
}
