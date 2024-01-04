package testsService;

import com.sherlock.box.exception.OrderNotFoundException;
import com.sherlock.box.models.Order;
import com.sherlock.box.repositories.OrderRepository;
import com.sherlock.box.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    private static final Long ID = 1L;

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Order order;

    @BeforeEach
    private void setUp(){
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void itAddsCar() {
        orderService.addOrder(order);

        verify(orderRepository).save(order);
    }

    @Test
    public void itUpdatesCar() throws OrderNotFoundException {
        when(order.getId()).thenReturn(ID);
        when(orderRepository.findOrderById(order.getId())).thenReturn(order);

        orderService.updateOrder(order);

        verify(orderRepository).save(order);
    }

    @Test
    public void itDeletesCar() throws OrderNotFoundException {
        when(orderRepository.findOrderById(ID)).thenReturn(order);

        orderService.deletedOrderById(ID);

        verify(orderRepository).deleteById(ID);
    }

    @Test
    public void itGotCar() throws OrderNotFoundException {
        when(orderRepository.findOrderById(ID)).thenReturn(order);

        orderService.getOrderById(ID);

        verify(orderRepository).findOrderById(ID);
    }
}
