package com.example.demo.config.controllers;

import com.example.demo.config.exception.CarNotFoundException;
import com.example.demo.config.models.Car;
import com.example.demo.config.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) throws Exception {
        return carService.getCarById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCar(@RequestBody Car car) throws CarNotFoundException {

        return carService.addCar(car);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletedCarById(@PathVariable long id) throws CarNotFoundException {
        return carService.deletedCarById(id);
    }

    @PutMapping
    public void updateCompany(@RequestBody Car car) throws CarNotFoundException {
        carService.updateCar(car);
    }
}
