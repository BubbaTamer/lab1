import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransportTest {
    private Transport<Car> transport;
    private Mercedes transporterCar;
    private Saab95 normalCar;
    private Volvo240 distantCar;

    @BeforeEach
    void setUp() {
        normalCar = new Saab95();
        normalCar.setPosition(0, 0);

        distantCar = new Volvo240();
        distantCar.setPosition(3,4);

        Mercedes transportVehicle = new Mercedes();
        transportVehicle.setPosition(0, 0);

        transporterCar = new Mercedes();
        transport = new Transport<>(transporterCar,2);
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
        Saab95 car = new Saab95();

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