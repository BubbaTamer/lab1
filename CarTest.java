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

}