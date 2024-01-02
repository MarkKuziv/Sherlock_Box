package com.example.demo.config.service;

import com.example.demo.config.exception.CarNotFoundException;
import com.example.demo.config.models.Car;
import com.example.demo.config.repositories.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public ResponseEntity<Car> getCarById(Long id) throws Exception {
        Car car = carRepository.findCarById(id);
        if (isNull(car)){
            LOGGER.info("Car with " + id + " not found");
            throw new CarNotFoundException(String.format("Car with %d not found", id)); // якщо не знайшов кидай помилку
        }
        return new ResponseEntity<>(car, HttpStatus.OK );
    }

    public ResponseEntity<String> deletedCarById(Long id) throws CarNotFoundException {
        Car car = carRepository.findCarById(id);
        if (isNull(car)) {
            carRepository.deleteById(id);
            LOGGER.info("Deleted car");
            return new ResponseEntity<>("Deleted car", HttpStatus.OK);
        }
        throw new CarNotFoundException(String.format("Car with %d not found", id));
    }

    public void updateCar(Car newCar) throws CarNotFoundException {
        Car car = carRepository.findCarById(newCar.getId());
        if (isNull(car)) {
            LOGGER.info("Car with: " + newCar.getId() + "not found");
            throw new CarNotFoundException(String.format("Car with %d not found", newCar.getId()));
        }
            update(car, newCar);
            LOGGER.info("Updated with: " + newCar.getId());
            carRepository.save(car);
        }


    public void update(Car car, Car newCar){
        car.setName(newCar.getName());
        car.setModel(newCar.getModel());
        car.setEngineCapacity(newCar.getEngineCapacity());
    }

    public ResponseEntity<String> addCar(Car car) throws CarNotFoundException {
        if (isNull(car)) {
            throw new CarNotFoundException(String.format("Car with %d not found", car.getId()));
        }
            carRepository.save(car);
            return new ResponseEntity<>("Added", HttpStatus.OK);


    }
}
