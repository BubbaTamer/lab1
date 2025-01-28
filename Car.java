package org.example;

import java.awt.*;

public abstract class Car implements Movable {

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    public double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    public final String modelName; // The car model name

    public double x;
    public double y;
    public double direction;

    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        stopEngine();
    }

    public int getNrDoors() {
        return this.nrDoors;
    }

    public double getEnginePower() {
        return this.enginePower;
    }

    public double getCurrentSpeed() {
        return this.currentSpeed;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color clr) {
        this.color = clr;
    }

    public void startEngine() {
        this.currentSpeed = 0.1;
    }

    public void stopEngine() {
        this.currentSpeed = 0;
    }


    public void move() {
        x += this.currentSpeed * Math.cos(Math.toRadians(this.direction));
        y += this.currentSpeed * Math.sin(Math.toRadians(this.direction));
    }


    public void turnLeft() {
        this.direction = (this.direction - 90 + 360) % 360;
    }


    public void turnRight() {
        this.direction = (this.direction + 90) % 360;
    }

}
