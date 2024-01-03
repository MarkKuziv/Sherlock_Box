package com.example.demo;
import com.example.demo.controllers.OrderController;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.models.Order;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTests {
    private static final Long ID = 1L;

    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Mock
    private Order order;

    @BeforeEach
    public void setUp(){
        orderController = new OrderController(orderService);
    }

    @Test
    public void itGetsOrder() throws Exception {
        orderController.getOrderById(ID);

        verify(orderService).getOrderById(ID);
    }

    @Test
    public void itAddsOrder() throws CarNotFoundException {
        orderController.addOrder(order);

        verify(orderService).addOrder(order);
    }

    @Test
    public void itDeletesOrder() throws CarNotFoundException {
        orderController.deletedOrderById(ID);

        verify(orderService).deletedOrderById(ID);
    }

    @Test
    public void itUpdatesOrder() throws OrderNotFoundException {
        orderController.updateOrder(order);

        verify(orderService).updateOrder(order);
    }
}
