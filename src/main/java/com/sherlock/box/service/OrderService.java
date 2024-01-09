package com.sherlock.box.service;

import com.sherlock.box.dto.OrderDTO;
import com.sherlock.box.exception.OrderNotFoundException;
import com.sherlock.box.mapper.OrderMapper;
import com.sherlock.box.models.Order;
import com.sherlock.box.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;


    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }


    public ResponseEntity<OrderDTO> getOrderById(Long id) throws OrderNotFoundException {
        Order order = orderRepository.findOrderById(id);
        if (isNull(order)) {
            LOGGER.info("Order with " + id + " not found");
            throw new OrderNotFoundException(String.format("Order with %d not found", id));
        }
        LOGGER.info("Order has been got. ID:" + order.getId());
        return new ResponseEntity<>(OrderMapper.toOrderDTO(order), HttpStatus.OK);
    }

    public ResponseEntity<String> deletedOrderById(Long id) throws OrderNotFoundException {
        Order order = orderRepository.findOrderById(id);
        if (isNull(order)) {
            throw new OrderNotFoundException(String.format("Order with %d not found", id));
        }
        orderRepository.deleteById(id);
        return new ResponseEntity<>("Order has been deleted. ID:" + order.getId(), HttpStatus.OK);
    }

    public ResponseEntity<String> addOrder(Order order) {
        orderRepository.save(order);
        return new ResponseEntity<>("Order has been deleted. ID:" + order.getId(), HttpStatus.OK);
    }

    public void updateOrder(Order newOrder) throws OrderNotFoundException {
        Order order = orderRepository.findOrderById(newOrder.getId());
        if (isNull(order)) {
            LOGGER.info("Order with " + newOrder.getId() + "not found");
            throw new OrderNotFoundException(String.format("Order with %d not found", newOrder.getId()));
        }
        update(order, newOrder);
        LOGGER.info("Order has been updated. ID:" + newOrder.getId());
        orderRepository.save(order);
    }

    private void update(Order order, Order newOrder) {
        order.setType(newOrder.getType());
        order.setCar(newOrder.getCar());
    }
}

