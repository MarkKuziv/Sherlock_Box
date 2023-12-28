package com.example.demo.config.service;

import com.example.demo.config.exception.CarNotFoundException;
import com.example.demo.config.exception.OrderNotFoundException;
import com.example.demo.config.models.Order;
import com.example.demo.config.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public ResponseEntity<Order> getOrderById(Long id) throws Exception {
        Order order = orderRepository.findOrderById(id);
        if (isNull(order)){
            LOGGER.info("Order with " + id + " not found");
            throw new CarNotFoundException(String.format("Order with %d not found", id)); // якщо не знайшов кидай помилку
        }
        return new ResponseEntity<>(order, HttpStatus.OK );
    }

    public ResponseEntity<String> deletedOrderById(Long id) throws CarNotFoundException {
        Order order = orderRepository.findOrderById(id);
        if (nonNull(order)) {
            orderRepository.deleteById(id);
            LOGGER.info("Deleted order");
            return new ResponseEntity<>("Deleted order", HttpStatus.OK);
        }
        throw new CarNotFoundException(String.format("Order with %d not found", id));
    }

    public void updateOrder(Order newOrder) throws OrderNotFoundException {
        Order order = orderRepository.findOrderById(newOrder.getId());
        if (nonNull(order)) {
            update(order, newOrder);
            LOGGER.info("Updated with: " + newOrder.getId());
            orderRepository.save(order);
        } else {
            LOGGER.info("Order with " + newOrder.getId() + "not found");
            throw new OrderNotFoundException(String.format("Car with %d not found", newOrder.getId()));
        }

    }

    public void update(Order order, Order newOrder){
        order.setType(newOrder.getType());
        order.setCar(newOrder.getCar());
    }

    public ResponseEntity<String> addOrder(Order order) throws CarNotFoundException {
        if (nonNull(order)) {
            orderRepository.save(order);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        }
        throw new CarNotFoundException(String.format("Order with %d not found", order.getId()));
    }
}

