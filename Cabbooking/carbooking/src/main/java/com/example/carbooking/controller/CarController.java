package com.example.carbooking.controller;

import com.example.carbooking.entities.CarEntity;
import com.example.carbooking.exception.ConflictException;
import com.example.carbooking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
