import java.util.ArrayList;
import java.util.List;

public class Verkstad<T extends Car> {
    private List<T> cars;
    private int capacity;

    public Verkstad(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Capacity must be a positive integer");
        this.capacity = capacity;
        this.cars = new ArrayList<>(capacity); // init with capacity
    }

    public void addCar(T car) {
        if (cars.size() >= capacity)
            throw new FullCapacityException("There's no space currently. Max capacity: " + capacity + "reached.");

        cars.add(car);
    }

    public T getLastCar() {
        // return last given car?
        if (cars.isEmpty()) {
            throw new IllegalStateException("No cars in the workshop");
        }
        return cars.removeLast();
    }
}
