import java.util.Stack;

public class Transport {
    private final int MAX_CARS;
    private final Car _transport;
    private final Stack<Car> carStack = new Stack<>();

    // Constants doesn't need to be stored in heap.
    // Stack is better, hence static in this case.
    private static final double MAX_DISTANCE = 2;

    private double angle = 0;

    public Transport(Car transport) {
        _transport = transport;
        MAX_CARS = 2;
    }

    public Transport(int maxCars) {
        _transport = null; // workaround... better ways may exist
        MAX_CARS = maxCars;
    }

    public void loadCar(Car car) {
        if (!validateLoad(car))
            throw new FullCapacityException("There's no space for this vehicle.");;

        carStack.push(car);
    }

    public Car unloadCar() {
        if (angle != 0 || carStack.isEmpty())
            throw new IllegalStateException("Failed to unload car right now.");
        return carStack.pop();
    }

    public void lower(double deg) {
        if (angle - deg >= 0) {
            angle -= deg;
        } else {
            throw new IllegalArgumentException("Cannot lower platform below 0 degrees.");
        }
    }

    public void raise(double deg) {
        if (deg < 0) throw new IllegalArgumentException("Degrees must be positive");
        if (angle + deg > 70) {
            throw new IllegalArgumentException("Cannot exceed 70 degrees");
        }

        angle += deg;
    }

    public void setCarsPosition() {
        carStack.forEach((Car car) -> {
            car.setX(_transport.getX());
            car.setY(_transport.getY());
        });
    }

    public double getAngle() {
        return angle;
    }

    protected boolean validateLoad(Car car) {
        return angle == 0 &&
                carStack.size() < MAX_CARS &&
                !car.canTransport() &&
                calculateDistance(car);
    }

    private boolean calculateDistance(Car car) {
        if (Math.sqrt(Math.pow(car.getX() - _transport.getX(), 2) +
                Math.pow(car.getY() - _transport.getY(), 2)) > MAX_DISTANCE) {
            throw new IllegalArgumentException("Car is too far away.");
        }
        return true;
    }

}
