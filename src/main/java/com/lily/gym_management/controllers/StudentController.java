package com.lily.gym_management.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public ResponseEntity<String> getAllStudent() {
        return ResponseEntity.ok("deu ok");
    }
}
