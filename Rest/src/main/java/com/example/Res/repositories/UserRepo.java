package com.example.Res.repositories;

import com.example.Res.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(int id);
    UserEntity findByUsername(String username);
}
