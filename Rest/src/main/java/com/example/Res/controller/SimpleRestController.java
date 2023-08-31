package com.example.Res.controller;

import com.example.Res.DTO.User;
import com.example.Res.models.UserEntity;
import com.example.Res.repositories.UserRepo;
import com.example.Res.serviceis.UserEntityService;
import com.example.Res.util.PersonErrorResponse;
import com.example.Res.util.PersonNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:8080"})
public class SimpleRestController {
    UserEntityService userEntityService;
    UserRepo userRepo;

    @Autowired
    public SimpleRestController(UserEntityService userEntityService, UserRepo userRepo) {
        this.userEntityService = userEntityService;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
            return userRepo.findAll();
    }

    @GetMapping("/get/{username}")
    public HttpEntity<User> getUser(@PathVariable("username") String username) {
        HttpEntity<User> response =
                new HttpEntity<>(new User(userRepo.findByUsername(username)));
        return response;
    }

    @PostMapping("/post")
    public ResponseEntity<HttpStatus> createUserFromJSon (@RequestBody UserEntity userEntity,
                                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").
                        append(error.getDefaultMessage()).append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }

        userEntityService.create(userEntity);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleException (PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
