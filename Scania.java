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

    @Override
    public void gas(double amount) {
        if (getPlatformDegree() > 0) throw new IllegalArgumentException("Error");
        super.gas(amount);
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

}
