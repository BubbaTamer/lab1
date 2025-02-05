import java.awt.*;

public class Volvo extends Transport {
    private boolean rampDown;

    public Volvo() {
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
        return canLoadUnload() && !carStack.isEmpty() ?
                carStack.pop() : null;
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
