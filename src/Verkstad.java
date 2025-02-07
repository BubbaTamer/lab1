public class Verkstad<T extends Car> {
    private final Transport transport;

    public Verkstad(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Capacity must be a positive integer");
        transport = new Transport(capacity);
    }

    public void loadCar(T car) {
        transport.loadCar(car);
    }

    public T getLastCar() {
        // cast to return type of Car object.
        return (T)transport.unloadCar();
    }
}
