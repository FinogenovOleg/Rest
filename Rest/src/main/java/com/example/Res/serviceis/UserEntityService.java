package com.example.Res.serviceis;

import com.example.Res.models.UserEntity;
import com.example.Res.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {
    UserRepo userRepo;

    @Autowired
    public UserEntityService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public void create(UserEntity entity) {
        userRepo.save(entity);
    }
}
