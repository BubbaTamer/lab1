import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VerkstadTests {

    private Verkstad<Volvo240> volvoWorkshop;
    private Verkstad<Car> genericWorkshop;
    private Volvo240 volvo240;
    private Saab95 saab95;

    @BeforeEach
    void setUp() {
        volvoWorkshop = new Verkstad<>(2);
        genericWorkshop = new Verkstad<>(1);
        volvo240 = new Volvo240();
        saab95 = new Saab95();
    }

    @Test
    void testVerkstadCapacityExceeded() {
        genericWorkshop.addCar(saab95);
        assertThrows(FullCapacityException.class, () -> genericWorkshop.addCar(new Saab95()));
    }

    @Test
    void testVerkstadGetCar() {
        volvoWorkshop.addCar(volvo240);
        // type safety check
        assertEquals(true, volvoWorkshop.getLastCar() instanceof Volvo240);
    }

    @Test
    void testVerkstadGetNonExistingCar() {
        assertThrows(IllegalStateException.class, volvoWorkshop::getLastCar);
    }
}
