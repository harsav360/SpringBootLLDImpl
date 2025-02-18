package com.harsav360.Parking.entity;

import com.harsav360.Parking.Enums.VehicleTypes;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ParkingLot {
    String id;
    Integer floorNumber;
    VehicleTypes vehicleType;
    Boolean isAvailable;
}
