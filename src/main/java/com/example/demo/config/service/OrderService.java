package com.example.demo.config.service;

import com.example.demo.config.exception.CarNotFoundException;
import com.example.demo.config.models.Order;
import com.example.demo.config.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public ResponseEntity<Order> getOrderById(Long id) throws Exception {
        Order order = orderRepository.findOrderById(id);
        if (order == null){
            LOGGER.info("Order with " + id + " not found");
            throw new CarNotFoundException(String.format("Order with %d not found", id)); // якщо не знайшов кидай помилку
        }
        return new ResponseEntity<>(order, HttpStatus.OK );
    }

    public ResponseEntity<String> deletedOrderById(Long id){
        orderRepository.deleteById(id);
        LOGGER.info("Deleted order");
        return new ResponseEntity<>("Deleted order", HttpStatus.OK);
    }

    public void updateOrder(Order newOrder) {
        Order order = orderRepository.findOrderById(newOrder.getId());
        update(order, newOrder);
        LOGGER.info("Updated with: " + newOrder.getId());
        orderRepository.save(order);

    }

    public void update(Order order, Order newOrder){
        order.setType(newOrder.getType());
        order.setCar(newOrder.getCar());
    }

    public ResponseEntity<String> addOrder(Order order){
        orderRepository.save(order);
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }
}

