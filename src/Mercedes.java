import java.awt.*;

public class Mercedes extends Car {
    private final Transport transport;

    public Mercedes() {
        super(2, Color.red, 760, "Volvo");
        transport = new Transport(this);
        enableTransport(transport);
    }

    public void rampDown() {
        transport.lower(70);
    }

    public void rampUp() {
        transport.raise(70);
    }

    public Car unloadCar() {
        return transport.unloadCar();
    }

    public void loadCar(Car car) {
        transport.loadCar(car);
    }

    @Override
    public void move() {
        if (transport.getAngle() != 0) 
            throw new IllegalStateException("Transport can't move while ramp is up.");
        super.move();
        transport.setCarsPosition();
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
