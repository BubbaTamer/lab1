import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransportTest {
    private Scania scania;
    private Mercedes mercedes;
    private Car rampCar;

    @BeforeEach
    void setUp() {
        scania = new Scania();
        mercedes = new Mercedes();
        rampCar = new Volvo240();
    }

    // Scania test
    @Test
    void raisePlatformWhileMovingThrows() {
        scania.startEngine();
        scania.gas(0.5);
        assertThrows(IllegalArgumentException.class, () -> scania.raisePlatform(10));
    }

    @Test
    void lowerPlatformBelowZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> scania.lowerPlatform(10));
    }

    // Mercedes (with Ramp) test
    @Test
    void testToggleRampWhenStill() {
        mercedes.toggleRamp();
        assertTrue(mercedes.canLoadUnload());
    }

    @Test
    void testToggleRampWhileMoving() {
        mercedes.startEngine();
        mercedes.gas(0.5);
        assertThrows(IllegalStateException.class, mercedes::toggleRamp);
    }

    @Test
    void testLoadCarWithRampDown() {
        mercedes.toggleRamp();
        mercedes.loadCar(rampCar);

        mercedes.toggleRamp(); // up and
        mercedes.toggleRamp(); // down to simulate normal situation

        assertEquals(rampCar, mercedes.unloadCar());
    }

    @Test
    void testUnloadCarWithRampUp() {
        mercedes.toggleRamp();
        mercedes.loadCar(rampCar);
        mercedes.toggleRamp();
        assertThrows(IllegalStateException.class, mercedes::unloadCar);
    }

    @Test
    void testMoveWithRampDown() {
        mercedes.toggleRamp();
        assertThrows(IllegalStateException.class, () -> mercedes.gas(1));
    }
}
