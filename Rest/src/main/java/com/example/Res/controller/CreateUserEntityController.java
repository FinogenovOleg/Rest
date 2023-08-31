package com.example.Res.controller;

import com.example.Res.models.UserEntity;
import com.example.Res.serviceis.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create")
public class CreateUserEntityController {
    UserEntityService service;

    @Autowired
    public CreateUserEntityController(UserEntityService service) {
        this.service = service;
    }

    @GetMapping
    public String getCreatePage(@ModelAttribute("user") UserEntity user) {
        return "create.html";
    }

    @PostMapping
    public String create(@ModelAttribute("user") UserEntity userEntity,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "redirect:/create";

        service.create(userEntity);

        return "redirect:/hello?name=" + userEntity.getName();
    }
}
