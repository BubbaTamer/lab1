package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CarTransport extends Car{
    private boolean rampDown;
    public final List<Car> loadedCars;

    private static final int maxCars = 6;
    private static final double maxCarSize = 5.0;
    private static final double maxLoadDistance = 2.0;

    public CarTransport() {
        super(2, Color.white, 600, "FH16");
        this.loadedCars = new ArrayList<>();
        this.rampDown = false;
    }

    public boolean getRampDown() {
        return rampDown;
    }

    public void raiseRamp() {
        rampDown = false;
    }

    public void lowerRamp() {
        if (getCurrentSpeed() > 0) throw new IllegalArgumentException("Error");
        rampDown = true;
    }

    public void loadCar(Car car) {
        if (!rampDown) throw new IllegalStateException("Ramp must be down");
        if (loadedCars.size() >= maxCars) throw new IllegalStateException("Transport is full");
        if (car instanceof CarTransport) throw new IllegalStateException("Can't load car transport");
        //Vafan ska jag göra här, finns ju ingen length på car
        //if (??? > maxCarSize) throw new IllegalStateException("Car is to long");
        if (distanceTo(car) > maxLoadDistance) throw new IllegalStateException("Car is too far away.");

        loadedCars.add(car);
        car.setPosition(getX(), getY());

    }



    public void unloadCar() {
        if (!rampDown) throw new IllegalStateException("Kan endast lossa bilar när rampen är nere.");
        if (loadedCars.isEmpty()) throw new IllegalStateException("Ingen bil att lossa.");

        Car car = loadedCars.removeLast();
        car.setPosition(getX() + 1, getY() + 1);
    }


    private double distanceTo(Car car) {
        double dx = car.getX() - getX();
        double dy = car.getY() - getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void move() {
        if (rampDown) throw new IllegalStateException("Transporten kan inte köra när rampen är nere.");
        super.move();
        for (Car car : loadedCars) {
            car.setPosition(getX(), getY());
        }
    }



    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

}
