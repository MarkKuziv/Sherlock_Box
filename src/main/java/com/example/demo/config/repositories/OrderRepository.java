package com.example.demo.config.repositories;

import com.example.demo.config.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(Long id);
    Order findByType(String type);
}
