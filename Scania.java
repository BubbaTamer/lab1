package org.example;
import java.awt.*;

public class Scania extends Car {

    private double platformDegree;

    public Scania() {
        super(2, Color.white, 600, "Scania");
    }

    public double getPlatformDegree() {
        return platformDegree;
    }

    public void raisePlatform(double amount) {
        if (getCurrentSpeed() > 0 || (getPlatformDegree() + amount) > 70) throw new IllegalArgumentException("Error");
        platformDegree += amount;
    }

    public void lowerPlatform(double amount) {
        if (getCurrentSpeed() > 0 || (getPlatformDegree() - amount) < 0) throw new IllegalArgumentException("Error");
        platformDegree -= amount;
    }

    public void incrementSpeed(double amount) {
        if (amount < 0 || amount > 1) throw new IllegalArgumentException("Amount must be between 0 and 1");
        currentSpeed = Math.min(currentSpeed + speedFactor() * amount, enginePower);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

}
