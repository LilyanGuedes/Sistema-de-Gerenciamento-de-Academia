package com.lily.gym_management.controllers;

import com.lily.gym_management.entities.Gym;
import com.lily.gym_management.useCases.FindAllGymsUseCase;
import com.lily.gym_management.useCases.RegisterGymUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gyms")
public class GymController {

    private final RegisterGymUseCase registerGymUseCase;
    private final FindAllGymsUseCase findAllGymsUseCase;

    @Autowired
    public GymController(RegisterGymUseCase registerGymUseCase, FindAllGymsUseCase findAllGymsUseCase) {
        this.registerGymUseCase = registerGymUseCase;
        this.findAllGymsUseCase = findAllGymsUseCase;
    }

    @PostMapping
    public ResponseEntity<Gym> registerGym(@RequestBody Gym gym) {
        Gym registeredGym = registerGymUseCase.execute(gym);
        return ResponseEntity.status(201).body(registeredGym);
    }

    @GetMapping
    public ResponseEntity<List<Gym>> findAllGyms() {
        List<Gym> gyms = findAllGymsUseCase.execute();
        return ResponseEntity.ok(gyms);
    }
}
