package testsService;

import com.sherlock.box.exception.CarNotFoundException;
import com.sherlock.box.models.Car;
import com.sherlock.box.repositories.CarRepository;
import com.sherlock.box.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    private static final Long ID = 1L;

    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private Car car;

    @BeforeEach
    private void setUp() {
        carService = new CarService(carRepository);
    }

    @Test
    public void itAddsCar() {
        carService.addCar(car);

        verify(carRepository).save(car);
    }

    @Test
    public void itUpdatesCar() throws CarNotFoundException {
        when(car.getId()).thenReturn(ID);
        when(carRepository.findCarById(car.getId())).thenReturn(car);

        carService.updateCar(car);

        verify(carRepository).save(car);
    }

    @Test
    public void itDeletesCar() throws CarNotFoundException {
        when(carRepository.findCarById(ID)).thenReturn(car);

        carService.deletedCarById(ID);

        verify(carRepository).deleteById(ID);
    }

    @Test
    public void itGotCar() throws CarNotFoundException {
        when(carRepository.findCarById(ID)).thenReturn(car);

        carService.getCarById(ID);

        verify(carRepository).findCarById(ID);
    }

}
