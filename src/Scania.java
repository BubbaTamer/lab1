import java.awt.*;

public class Scania extends Transport {

    private double platformDegree;

    public Scania() {
        super(2, Color.white, 600, "Scania");
    }

    public double getPlatformDegree() {
        return platformDegree;
    }

    // useless implementation
    @Override
    protected boolean canLoadUnload() {
        return platformDegree == 0;
    }

    @Override
    protected void validateMovement() {
        if (platformDegree != 0)
            throw new IllegalStateException();
    }

    public void raisePlatform(double amount) {
        if (getCurrentSpeed() > 0 || (platformDegree + amount) > 70)
            throw new IllegalArgumentException("Cant raise platform");
        platformDegree += amount;
    }

    public void lowerPlatform(double amount) {
        if (getCurrentSpeed() > 0 || (platformDegree - amount) < 0)
            throw new IllegalArgumentException("Cant lower platform");
        platformDegree -= amount;
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
