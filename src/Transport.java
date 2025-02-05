import java.awt.*;
import java.util.Stack;

public abstract class Transport extends Car {

    protected Stack<Car> carStack;
    protected static final int MAX_CARS = 4;
    protected static final double MAX_DISTANCE = 2;

    public Transport(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);

        // init carstack
        carStack = new Stack<>();
    }

    protected abstract boolean canLoadUnload(); // status
    protected abstract void validateMovement(); // car running

    @Override
    public void gas(double amount) {
        validateMovement();
        super.gas(amount);
    }

    /**
     * Check load to see if Car is allowed and can fit
     * @param car of type Car
     * @return true/false depending on state
     */
    // Here to allow other subclasses to use it.
    protected boolean validateLoad(Car car) {
        return canLoadUnload() &&
                carStack.size() < MAX_CARS &&
                !(car instanceof Transport) &&
                calculateDistance(car) <= MAX_DISTANCE;
    }

    @Override
    public void move() {
        super.move();
        updateLoadedCarsPosition();
    }

    private void updateLoadedCarsPosition() {
        carStack.forEach((Car car) -> {
            car.setX(getX());
            car.setY(getY());
        });
    }


    private double calculateDistance(Car car) {
        return Math.sqrt(Math.pow(car.getX() - getX(), 2) +
                Math.pow(car.getY() - getY(), 2));
    }
}
