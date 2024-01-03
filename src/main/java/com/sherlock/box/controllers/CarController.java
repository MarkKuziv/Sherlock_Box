package com.sherlock.box.controllers;

import com.sherlock.box.exception.CarNotFoundException;
import com.sherlock.box.models.Car;
import com.sherlock.box.service.CarService;
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
    public ResponseEntity<Car> getCarById(@PathVariable long id) throws CarNotFoundException{
        return carService.getCarById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCar(@RequestBody Car car) {

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
