package com.example.carbooking.service;

import com.example.carbooking.entities.BookingEntity;
import com.example.carbooking.entities.CarEntity;
import com.example.carbooking.exception.ConflictException;
import com.example.carbooking.repository.BookingRepository;
import com.example.carbooking.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public CarEntity create(CarEntity carEntity) {
        Optional<CarEntity> existingCar = carRepository.findByRegistrationNumber(carEntity.getRegistrationNumber());
        if (existingCar.isPresent()) {

            throw new ConflictException("Car with registration number " + carEntity.getRegistrationNumber() + " already exists.");
        }
        return carRepository.save(carEntity);
    }

    public CarEntity getById(Long id) {
        Optional<CarEntity> foundCar = carRepository.findById(id);
        if (foundCar.isPresent()) {
            return foundCar.get();
        }
        throw new ConflictException("Car with ID " + id + " not found.");
    }

    public List<BookingEntity> getBookingDetails(int carid){
        return bookingRepository.findByCarid(carid);

    }
}
