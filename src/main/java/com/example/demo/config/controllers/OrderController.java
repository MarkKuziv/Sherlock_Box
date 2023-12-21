package com.example.demo.config.controllers;

import com.example.demo.config.models.Order;
import com.example.demo.config.service.OrderService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(value = "/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) throws Exception {
        return orderService.getOrderById(id);
    }

    @PostMapping(value = "/order/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @DeleteMapping(value = "/order/deleted/{id}")
    public ResponseEntity<String> deletedOrderById(@PathVariable long id){
        return orderService.deletedOrderById(id);
    }

    @PutMapping(value = "/order/update")
    public void updateCompany(@RequestBody Order order){
        orderService.updateOrder(order);
    }
}
