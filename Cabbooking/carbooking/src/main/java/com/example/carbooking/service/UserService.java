package com.example.carbooking.service;


import com.example.carbooking.entities.UserEntity;
import com.example.carbooking.exception.ConflictException;
import com.example.carbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserEntity create(UserEntity userEntity) {
        Optional<UserEntity> customerid = userRepository.findByCustomerid(userEntity.getCustomerid());
        if (customerid.isPresent()) {
            throw new ConflictException("User already exists");
        }
        return userRepository.save(userEntity);
    }
    public UserEntity getById(int id) {
        Optional<UserEntity> findById = userRepository.findByCustomerid(id);
        if (findById.isPresent()) {
            return findById.get();
        }
        throw new ConflictException("Add the User details");
    }
}

