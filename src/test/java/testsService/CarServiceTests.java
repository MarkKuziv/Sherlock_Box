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
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    private static final Long ID = 1L;

    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private Car car;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        carService = new CarService(carRepository, modelMapper);
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
    public void itThrownCarNotFoundExceptionWhenCarUpdates() {
        when(car.getId()).thenReturn(ID);

        assertThatThrownBy(() -> carService.updateCar(car))
                .hasMessage(String.format("Car with %d not found", ID))
                .isInstanceOf(CarNotFoundException.class);
    }

    @Test
    public void itDeletesCar() throws CarNotFoundException {
        when(carRepository.findCarById(ID)).thenReturn(car);

        carService.deletedCarById(ID);

        verify(carRepository).deleteById(ID);
    }

    @Test
    public void itThrownCarNotFoundExceptionWhenCarDeletes() {
        assertThatThrownBy(() -> carService.deletedCarById(ID))
                .hasMessage(String.format("Car with %d not found", ID))
                .isInstanceOf(CarNotFoundException.class);
    }

    @Test
    public void itGetsCar() throws CarNotFoundException {
        when(carRepository.findCarById(ID)).thenReturn(car);

        carService.getCarById(ID);

        verify(carRepository).findCarById(ID);
    }

    @Test
    public void itThrownCarNotFoundExceptionWhenCarGets() {
        assertThatThrownBy(() -> carService.getCarById(ID))
                .hasMessage(String.format("Car with %d not found", ID))
                .isInstanceOf(CarNotFoundException.class);
    }

}
