package com.harsav360.Parking.service;

import com.harsav360.Parking.entity.ParkingLot;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkingService {

    private final List<ParkingLot> parkingLotList = new ArrayList<>();
    private final Map<String,String> reserveParkings = new HashMap<>();

    // Search Available Parking Spots
    public List<ParkingLot> freeParkingSpots(){
        List<ParkingLot> result = new ArrayList<>();
        for(ParkingLot parkingLot : parkingLotList){
            if (parkingLot.getIsAvailable()) {
                result.add(parkingLot);
            }
        }
        return result;
    }

    // Create Parking Spot
    public boolean createParkingSpot(ParkingLot parkingLot){
        parkingLotList.add(parkingLot);
        return true;
    }

    // Reserve a Parking Spot
    public synchronized String reserveParking(String id){
        boolean found = false;
        String reservationId = generateUniqueId();
        for(ParkingLot parkingLot : parkingLotList){
            if(parkingLot.getId().equalsIgnoreCase(id) && Boolean.TRUE.equals(parkingLot.getIsAvailable())){
                parkingLot.setIsAvailable(false);
                found = true;
                reserveParkings.put(reservationId, parkingLot.getId());
                break;
            }
        }
        if (!found){
            return "Parking Spot is not free - Try some other space";
        } else {
            return "Parking Spot reserved with id : " + reservationId;
        }
    }

    // Free a Parking Spot
    public synchronized String freeParkingSpot(String id){
        boolean success = false;
        if (reserveParkings.containsKey(id)) {
            String parkingId = reserveParkings.get(id);
            for (ParkingLot parkingLot : parkingLotList) {
                if (parkingLot.getId().equalsIgnoreCase(parkingId) && Boolean.FALSE.equals(parkingLot.getIsAvailable())) {
                    success = true;
                    parkingLot.setIsAvailable(true);
                    reserveParkings.remove(id);
                    break;
                }
            }
        }
            if (success) {
                return "Successfully vacated the spot with id : " + id;
            } else {
                return "No parking spot exist, Please pass valid entry";
            }
    }

    // generate uuid for every reserve parking
    private String generateUniqueId(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 7);
    }

}
