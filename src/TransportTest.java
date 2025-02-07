import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransportTest {
    private Transport transport;
    private TestCar transporterCar;
    private TestCar normalCar;
    private TestCar distantCar;

    static class TestCar extends Car {
        public TestCar() {
            super(4, Color.RED, 100, "TestCar");
        }

        @Override
        protected double speedFactor() {
            return 1.0;
        }
    }

    @BeforeEach
    void setUp() {
        transporterCar = new TestCar();

        normalCar = new TestCar();
        normalCar.setX(0);
        normalCar.setY(0);

        distantCar = new TestCar();
        distantCar.setX(3);
        distantCar.setY(4);

        TestCar transportVehicle = new TestCar();
        transportVehicle.setX(0);
        transportVehicle.setY(0);
        transport = new Transport(transportVehicle);
        transporterCar.enableTransport(transport);
    }

    @Test
    void testLoadCarNormalConditions() {
        transport.loadCar(normalCar);
        assertEquals(normalCar, transport.unloadCar());
    }

    @Test
    void testLoadCarPlatformRaised() {
        transport.raise(10);
        assertThrows(FullCapacityException.class, () -> transport.loadCar(normalCar));
    }

    @Test
    void testLoadCarFullCapacity() {
        transport.loadCar(normalCar);
        transport.loadCar(normalCar);
        assertThrows(FullCapacityException.class, () -> transport.loadCar(normalCar));
    }

    @Test
    void testLoadCarTransporterVehicle() {
        assertThrows(FullCapacityException.class, () -> transport.loadCar(transporterCar));
    }

    @Test
    void testUnloadCarValidConditions() {
        transport.loadCar(normalCar);
        Car unloaded = transport.unloadCar();
        assertEquals(normalCar, unloaded);
    }

    @Test
    void testUnloadCarEmptyStack() {
        assertThrows(IllegalStateException.class, () -> transport.unloadCar());
    }

    @Test
    void testRaisePlatformWithinLimits() {
        transport.raise(70);
        assertEquals(70, transport.getAngle());
    }

    @Test
    void testRaisePlatformOverLimit() {
        assertThrows(IllegalArgumentException.class, () -> transport.raise(71));
    }

    @Test
    void testLowerPlatformWithinLimits() {
        transport.raise(30);
        transport.lower(10);
        assertEquals(20, transport.getAngle());
    }

    @Test
    void testLowerPlatformBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> transport.lower(1));
    }

    @Test
    void testSetCarsPosition() {
        TestCar car = new TestCar();
        car.setX(1);
        car.setY(1);

        transport.loadCar(car);
        transport.setCarsPosition();

        assertEquals(0.0, car.getX());
        assertEquals(0.0, car.getY());
    }

    @Test
    void testValidateLoadDistantCar() {
        assertThrows(IllegalArgumentException.class, () -> transport.validateLoad(distantCar));
    }
}