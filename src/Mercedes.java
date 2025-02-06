import java.awt.*;

public class Mercedes extends Transport {
    private boolean rampDown;

    public Mercedes() {
        super(2, Color.red, 760, "Volvo");
    }

    @Override
    public boolean canLoadUnload() {
        return rampDown;
    }

    public void validateMovement() {
        if (rampDown) {
            throw new IllegalStateException();
        }
    }

    public void toggleRamp() {
        if (getCurrentSpeed() == 0) rampDown = !rampDown;
        else throw new IllegalStateException("Ramp cannot be lowered while driving.");
    }

    public void loadCar(Car car) {
        if (validateLoad(car)) carStack.push(car);
    }

    public Car unloadCar() {
        if (canLoadUnload() && !carStack.isEmpty()) {
            return carStack.pop();
        } else {
            throw new IllegalStateException("Cannot unload car whilst driving.");
        }
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
