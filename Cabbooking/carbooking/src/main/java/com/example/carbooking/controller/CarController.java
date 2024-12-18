package com.example.carbooking.controller;

import com.example.carbooking.entities.BookingEntity;
import com.example.carbooking.entities.CarEntity;
import com.example.carbooking.exception.ConflictException;
import com.example.carbooking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/create")
    public CarEntity createCar(@RequestBody CarEntity carEntity) {
        return carService.create(carEntity);
    }
    @GetMapping("/{id}")
    public CarEntity getCarById(@PathVariable Long id) {
        return (CarEntity) carService.getById(id);
    }

    @GetMapping("/booking")
    public ResponseEntity<Object> getBookingCarId(@RequestParam int carid){
        try{
            List<BookingEntity> booking = carService.getBookingDetails(carid);
            if (booking.isEmpty()){
                throw new ConflictException("No booking found");
            }
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/accept")
    public ResponseEntity<String> acceptRide(@RequestParam Long carid){
        boolean isUpdate = carService.UpdateCarAvabality(carid);
        if (isUpdate){
            return ResponseEntity.ok("Ride has Confirmed");
        }
        return ResponseEntity.notFound().build();
    }
}
