package com.example.demo.config.controllers;

import com.example.demo.config.exception.OrderNotFoundException;
import com.example.demo.config.models.Order;
import com.example.demo.config.service.OrderService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) throws Exception {
        return orderService.getOrderById(id);
    }

    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @DeleteMapping(value = "/deleted/{id}")
    public ResponseEntity<String> deletedOrderById(@PathVariable long id){
        return orderService.deletedOrderById(id);
    }

    @PutMapping(value = "/update")
    public void updateCompany(@RequestBody Order order) throws OrderNotFoundException {
        orderService.updateOrder(order);
    }
}
