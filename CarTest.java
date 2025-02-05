import java.awt.*;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testGetNrDoors() {
        int expectedNrDoors = 4;
        Car car = new Saab95();
        int actualNrDoors = car.getNrDoors();

        assertEquals(expectedNrDoors, actualNrDoors);
    }

    @Test
    void testGetEnginePower() {
        double expectedEnginePower = 125;
        Car car = new Saab95();
        double actualEnginePower = car.getEnginePower();

        assertEquals(expectedEnginePower, actualEnginePower);
    }


    @Test
    void testGetCurrentSpeed() {
        Car car = new Saab95();
        double initialSpeed = car.getCurrentSpeed();

        assertEquals(0, initialSpeed);

        car.startEngine();
        double speedAfterStart = car.getCurrentSpeed();
        assertEquals(0.1, speedAfterStart);
    }

    @Test
    void testGetColor() {
        Color expectedColor = Color.RED;
        Car car = new Saab95();
        Color actualColor = car.getColor();

        assertEquals(expectedColor, actualColor);
    }

    @Test
    void testSetColor() {
        Car car = new Saab95();

        car.setColor(Color.RED);
        assertEquals(Color.RED, car.getColor());
    }

    @Test
    void testStartEngine() {
        Car car = new Saab95();
        car.startEngine();
        assertEquals(0.1, car.getCurrentSpeed());

    }

    @Test
    void testStopEngine() {
        Car car = new Saab95();
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed());
    }


    @Test
    void testMove() {
        Car car = new Saab95();
        car.startEngine();

        car.move();

        assertEquals(0.1, car.getX(), 0.001);
        assertEquals(0.0, car.getY(), 0.001);
    }

    @Test
    void testTurnLeft() {
        Car car = new Saab95();

        car.turnLeft();
        assertEquals(270, car.getDirection());
    }

    @Test
    void testTurnRight() {
        Car car = new Saab95();
        car.turnRight();
        assertEquals(90, car.getDirection());
    }

    //Testing Saab95
    @Test
    void testTurboOn() {
        Saab95 Saab = new Saab95(); // IMPORTANT (TURBO IS LIMITED TO SAAB95)
        Saab.setTurboOn();
        double speedTurbo = Saab.speedFactor();

        assertTrue(speedTurbo > Saab.getEnginePower() * 0.01);
    }

    @Test
    void testTurboOff() {
        Saab95 Saab = new Saab95();
        Car saab = new Saab95();
        Saab.setTurboOff();

        assertEquals(Saab.getEnginePower() * 0.01, Saab.speedFactor(), 0.001);
    }

    @Test
    void testGasWithTurbo() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(1);
        assertEquals((saab.getEnginePower() * 0.01 * 1.3) + 0.1, saab.getCurrentSpeed(), 0.001);
    }

    @Test
    void testBrake()
    {
         Car volvo = new Volvo240();
         volvo.startEngine();
         volvo.brake(1);
         assertEquals(0.0, volvo.getCurrentSpeed(), 0.001);
    }

    // test brake method should throw exception
    @Test
    void testBrakeFailure() {
        Car volvo = new Volvo240();
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(-1));
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(2));
    }

    // test brake method should throw exception
    @Test
    void testGasFailure() {
        Car volvo = new Volvo240();
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(-1));
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(2));
    }

    // Testing Volvo240

    @Test
    void testSpeedFactor() {
        Volvo240 volvo = new Volvo240();
        assertEquals(1.25 * 100 * 0.01, volvo.speedFactor());
    }

    @Test
    void testGas() {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.gas(1);
        assertEquals(1.35, volvo.getCurrentSpeed(), 0.001);
    }

    //Testing truck
    @Test
    void testRaisePlatformOverLimit() {
        Scania scania = new Scania();
        assertThrows(IllegalArgumentException.class, () -> scania.raisePlatform(80));
    }

    @Test
    void testRaisePlatformWhileMoving() {
        Scania scania = new Scania();
        scania.startEngine();
        assertThrows(IllegalArgumentException.class, () -> scania.raisePlatform(20));
    }

    @Test
    void testTruckGas() {
        Scania scania = new Scania();
        scania.raisePlatform(10);
        scania.startEngine();

        assertThrows(IllegalArgumentException.class, () -> scania.gas(1));
    }
}
