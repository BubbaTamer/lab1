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

        assertEquals(expectedNrDoors, actualNrDoors, "The name returned by getName() should match the expected value.");
    }

    @Test
    void testGetEnginePower() {
        double expectedEnginePower = 125;
        Car car = new Car.Saab95(2, Color.WHITE, expectedEnginePower, "Saab95");
        double actualEnginePower = car.getEnginePower();

        assertEquals(expectedEnginePower, actualEnginePower, "The number returned by getEnginePower() should match the expected value.");
    }


    @Test
    void testGetCurrentSpeed() {
        Car car = new Car.Saab95(2, Color.red, 125, "Saab95");
        double initialSpeed = car.getCurrentSpeed(); // Call the getter for currentSpeed

        assertEquals(0, initialSpeed, "getCurrentSpeed should return 0 after the car is initialized.");

        car.startEngine();
        double speedAfterStart = car.getCurrentSpeed();
        assertEquals(0.1, speedAfterStart, "getCurrentSpeed should return 0.1 after starting the engine.");
    }

    @Test
    void testGetColor() {
        Color expectedColor = Color.WHITE;
        Car car = new Car.Saab95(2, expectedColor, 125, "Saab95");
        Color actualColor = car.getColor();

        assertEquals(expectedColor, actualColor, "The color returned by getColor() should match the expected value.");
    }

    @Test
    void testSetColor() {
        Car car = new Car.Saab95(4, Color.BLUE, 100, "Saab95");

        car.setColor(Color.RED); // Call the setter to change the color

        // Assert
        assertEquals(Color.RED, car.getColor(), "setColor should update the car's color to red.");
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

        assertEquals(125 * 0.01, Saab.speedFactor(), 0.001, "Speed factor should be normal when turbo is off.");
    }

    @Test
    void testSpeedFactorWithTurbo() {
        Car.Saab95 Saab = new Car.Saab95(2, Color.red, 125, "Saab95");
        Saab.setTurboOn();

        assertEquals(125 * 0.01 * 1.3, Saab.speedFactor(), 0.001, "Speed factor should be normal when turbo is off.");
    }

    @Test
    void testIncrementSpeedWithTurbo() {
        Car.Saab95 saab = new Car.Saab95(2, Color.red, 125, "Saab95");
        saab.startEngine();
        saab.setTurboOn();
        saab.incrementSpeed(10);
        assertEquals((125 * 0.01 * 1.3 * 10) + 0.1, saab.getCurrentSpeed(), 0.001);
    }





}
