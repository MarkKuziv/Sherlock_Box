package com.sherlock.box.service;

import com.sherlock.box.exception.CarNotFoundException;
import com.sherlock.box.models.Car;
import com.sherlock.box.repositories.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public ResponseEntity<Car> getCarById(Long id) throws Exception {
        Car car = carRepository.findCarById(id);
        if (isNull(car)) {
            LOGGER.info("Car with " + id + " not found");
            throw new CarNotFoundException(String.format("Car with %d not found", id)); // якщо не знайшов кидай помилку
        }
        LOGGER.info("Order has been got. ID:" + car.getId());
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    public ResponseEntity<String> deletedCarById(Long id) throws CarNotFoundException {
        Car car = carRepository.findCarById(id);
        if (isNull(car)) {
            throw new CarNotFoundException(String.format("Car with %d not found", id));
        }
        carRepository.deleteById(id);
        return new ResponseEntity<>("Order has been added. ID:" + car.getId(), HttpStatus.OK);
    }

    public void updateCar(Car newCar) throws CarNotFoundException {
        Car car = carRepository.findCarById(newCar.getId());
        if (isNull(car)) {
            LOGGER.info("Car with: " + newCar.getId() + "not found");
            throw new CarNotFoundException(String.format("Car with %d not found", newCar.getId()));
        }
        update(car, newCar);
        LOGGER.info("Order has been updated. ID:" + car.getId());
        carRepository.save(car);
    }


    public void update(Car car, Car newCar) {
        car.setName(newCar.getName());
        car.setModel(newCar.getModel());
        car.setEngineCapacity(newCar.getEngineCapacity());
    }

    public ResponseEntity<String> addCar(Car car) throws CarNotFoundException {
        if (isNull(car)) {
            throw new CarNotFoundException(String.format("Car with %d not found", car.getId()));
        }
        carRepository.save(car);
        return new ResponseEntity<>("Order has been added. ID:" + car.getId(), HttpStatus.OK);


    }
}
