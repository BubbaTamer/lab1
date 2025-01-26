import java.awt.*;

public abstract class Car implements Movable {

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    public double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    public final String modelName; // The car model name

    public double x;
    public double y;
    public double direction;

    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        stopEngine();
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color clr) {
        this.color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }



    public static class Saab95 extends Car {

        private boolean turboOn;

        public Saab95(int nrDoors, Color color, double enginePower, String modelName) {
            super(nrDoors, color, enginePower, modelName);
            this.turboOn = false;
        }


        public void setTurboOn() {
            this.turboOn = true;
        }

        public void setTurboOff() {
            this.turboOn = false;
        }

        public double speedFactor() {
            double turbo = 1;
            if (turboOn) turbo = 1.3;
            return getEnginePower() * 0.01 * turbo;
        }

        public void incrementSpeed(double amount) {
            currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        }

        public void decrementSpeed(double amount) {
            currentSpeed = getCurrentSpeed() - speedFactor() * amount;
        }

        // TODO fix this method according to lab pm
        public void gas(double amount) {
            incrementSpeed(amount);
        }

        // TODO fix this method according to lab pm
        public void brake(double amount) {
            decrementSpeed(amount);
        }
    }


    public static class Volvo240 extends Car {

        private final static double trimFactor = 1.25;

        public Volvo240(int nrDoors, Color color, double enginePower, String modelName) {
            super(nrDoors, color, enginePower, modelName);
        }


        public double speedFactor() {
            return getEnginePower() * 0.01 * trimFactor;
        }

        public void incrementSpeed(double amount) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
        }

        public void decrementSpeed(double amount) {
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        }

        // TODO fix this method according to lab pm
        public void gas(double amount) {
            incrementSpeed(amount);
        }

        // TODO fix this method according to lab pm
        public void brake(double amount) {
            decrementSpeed(amount);
        }
    }
}
