package testsController;

import com.sherlock.box.controllers.CarController;
import com.sherlock.box.exception.CarNotFoundException;
import com.sherlock.box.models.Car;
import com.sherlock.box.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarControllerTests {

    private static final Long ID = 1L;

    private CarController carController;

    @Mock
    private CarService carService;

    @Mock
    private Car car;

    @BeforeEach
    public void setUp(){
       carController = new CarController(carService);

    }

    @Test
    public void itAddsCar() {
        carController.addCar(car);

        verify(carService).addCar(car);
    }

    @Test
    public void itGetsCar() throws CarNotFoundException {
        carController.getCarById(ID);

        verify(carService).getCarById(ID);
    }

    @Test
    public void itDeletesCar() throws CarNotFoundException {
        carController.deletedCarById(ID);

        verify(carService).deletedCarById(ID);
    }

    @Test
    public void itUpdatesCar() throws CarNotFoundException {
        carController.updateCompany(car);

        verify(carService).updateCar(car);
    }
}
