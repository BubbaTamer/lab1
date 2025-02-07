import java.awt.*;

public class Scania extends Car {
    private final Transport transport;

    public Scania() {
        super(2, Color.white, 600, "Scania");
        transport = new Transport(this);
        enableTransport(transport);
    }

    public boolean isRampDown() {
        return transport.getAngle() != 70;
    }

    public void raisePlatform(double deg) {
        transport.raise(deg);
    }

    public void lowerPlatform(double deg) {
        transport.lower(deg);
    }

    @Override
    public void move() {
        if (transport.getAngle() != 0) throw new IllegalStateException("Transport can't move while ramp is up.");
        super.move();
        transport.setCarsPosition();
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
