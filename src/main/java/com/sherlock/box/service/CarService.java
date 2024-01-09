package com.sherlock.box.service;

import com.sherlock.box.dto.CarDTO;
import com.sherlock.box.exception.CarNotFoundException;
import com.sherlock.box.models.Car;
import com.sherlock.box.repositories.CarRepository;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;


    public CarService(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<CarDTO> getCarById(Long id) throws CarNotFoundException {
        Car car = carRepository.findCarById(id);
        if (isNull(car)) {
            LOGGER.info("Car with " + id + " not found");
            throw new CarNotFoundException(String.format("Car with %d not found", id)); // якщо не знайшов кидай помилку
        }
        LOGGER.info("Car has been got. ID:" + car.getId());
        return new ResponseEntity<>(modelMapper.map(car, CarDTO.class), HttpStatus.OK);
    }

    public ResponseEntity<String> deletedCarById(Long id) throws CarNotFoundException {
        Car car = carRepository.findCarById(id);
        if (isNull(car)) {
            throw new CarNotFoundException(String.format("Car with %d not found", id));
        }
        carRepository.deleteById(id);
        return new ResponseEntity<>("Car has been added. ID:" + car.getId(), HttpStatus.OK);
    }

    public ResponseEntity<String> addCar(Car car) {
        carRepository.save(car);
        return new ResponseEntity<>("Car has been added. ID:" + car.getId(), HttpStatus.OK);
    }

    public void updateCar(Car newCar) throws CarNotFoundException {
        Car car = carRepository.findCarById(newCar.getId());
        if (isNull(car)) {
            LOGGER.info("Car with: " + newCar.getId() + "not found");
            throw new CarNotFoundException(String.format("Car with %d not found", newCar.getId()));
        }
        update(car, newCar);
        LOGGER.info("Car has been updated. ID:" + car.getId());
        carRepository.save(car);
    }


    private void update(Car car, Car newCar) {
        car.setName(newCar.getName());
        car.setModel(newCar.getModel());
        car.setEngineCapacity(newCar.getEngineCapacity());
    }

}
