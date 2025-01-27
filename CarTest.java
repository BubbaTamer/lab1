package org.example;
import java.awt.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testGetNrDoors() {
        int expectedNrDoors = 4;
        Car car = new Car.Saab95(expectedNrDoors, Color.WHITE, 125, "Saab95");
        int actualNrDoors = car.getNrDoors();

        assertEquals(expectedNrDoors, actualNrDoors);
    }

    @Test
    void testGetEnginePower() {
        double expectedEnginePower = 125;
        Car car = new Car.Saab95(2, Color.WHITE, expectedEnginePower, "Saab95");
        double actualEnginePower = car.getEnginePower();

        assertEquals(expectedEnginePower, actualEnginePower);
    }


    @Test
    void testGetCurrentSpeed() {
        Car car = new Car.Saab95(2, Color.red, 125, "Saab95");
        double initialSpeed = car.getCurrentSpeed();

        assertEquals(0, initialSpeed);

        car.startEngine();
        double speedAfterStart = car.getCurrentSpeed();
        assertEquals(0.1, speedAfterStart);
    }

    @Test
    void testGetColor() {
        Color expectedColor = Color.WHITE;
        Car car = new Car.Saab95(2, expectedColor, 125, "Saab95");
        Color actualColor = car.getColor();

        assertEquals(expectedColor, actualColor);
    }

    @Test
    void testSetColor() {
        Car car = new Car.Saab95(4, Color.BLUE, 100, "Saab95");

        car.setColor(Color.RED);
        assertEquals(Color.RED, car.getColor());
    }

    @Test
    void testStartEngine(){
        Car car = new Car.Saab95(4, Color.WHITE, 100, "Saab95");
        car.startEngine();
        assertEquals(0.1, car.getCurrentSpeed());

    }
    @Test
    void testStopEngine(){
        Car car = new Car.Saab95(4, Color.WHITE, 100, "Saab95");
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed());
    }


    @Test
    void testMove() {
        Car car = new Car.Saab95(4, Color.WHITE, 100, "Saab95");
        car.startEngine();
        car.direction = 0;

        car.move();

        assertEquals(0.1, car.x, 0.001);
        assertEquals(0.0, car.y, 0.001);
    }

    @Test
    void testTurnLeft() {
        Car car = new Car.Saab95(2, Color.red, 125, "Saab95");
        car.direction = 0;

        car.turnLeft();
        assertEquals(270, car.direction);
    }

    @Test
    void testTurnRight() {
        Car car = new Car.Saab95(2, Color.red, 125, "Saab95");
        car.direction = 0;
        car.turnRight();
        assertEquals(90, car.direction);
    }

    //Testing Saab95
    @Test
    void testTurbo(){
        Car.Saab95 Saab = new Car.Saab95(2, Color.red, 125, "Saab95");
        Saab.setTurboOn();
        Saab.setTurboOff();

        assertFalse(Saab.getTurboOn());
    }
    @Test
    void testSpeedFactorWithoutTurbo() {
        Car.Saab95 Saab = new Car.Saab95(2, Color.red, 125, "Saab95");
        Saab.setTurboOff();

        assertEquals(125 * 0.01, Saab.speedFactor(), 0.001);
    }

    @Test
    void testSpeedFactorWithTurbo() {
        Car.Saab95 Saab = new Car.Saab95(2, Color.red, 125, "Saab95");
        Saab.setTurboOn();

        assertEquals(125 * 0.01 * 1.3, Saab.speedFactor(), 0.001);
    }

    @Test
    void testIncrementSpeedWithTurbo() {
        Car.Saab95 saab = new Car.Saab95(2, Color.red, 125, "Saab95");
        saab.startEngine();
        saab.setTurboOn();
        saab.incrementSpeed(10);
        assertEquals((125 * 0.01 * 1.3 * 10) + 0.1, saab.getCurrentSpeed(), 0.001);
    }

//Testing Volvo240
    @Test
    void testSpeedFactor() {
        Car.Volvo240 volvo = new Car.Volvo240(2, Color.red, 125, "Saab95");
        assertEquals(1.25 * 125 * 0.01, volvo.speedFactor());
    }

    @Test
    void testIncrementSpeed() {
        Car.Volvo240 volvo = new Car.Volvo240(2, Color.red, 125, "Saab95");
        volvo.startEngine();
        volvo.incrementSpeed(100);
        assertEquals(125, volvo.getCurrentSpeed(), 0.001);
    }



}
