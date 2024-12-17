package com.example.carbooking.controller;

import com.example.carbooking.entities.BookingEntity;
import com.example.carbooking.entities.CarEntity;
import com.example.carbooking.exception.ConflictException;
import com.example.carbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<Object> create(BookingEntity bookingEntity){
        try {
            BookingEntity savedBooking = bookingService.booked(bookingEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
        } catch (ConflictException e) {

            return      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<CarEntity>> getAvailableDrivers(){
        List<CarEntity> availableDrivers = bookingService.getActiveCar();
        if (availableDrivers.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.ok(availableDrivers);
    }
}
