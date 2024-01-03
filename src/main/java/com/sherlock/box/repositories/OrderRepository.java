package com.sherlock.box.repositories;

import com.sherlock.box.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(Long id);
}
