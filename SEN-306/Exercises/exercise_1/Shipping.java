package Exercises.exercise_1;

public class Shipping {
    public String createLabel(String address) {
        return "TRK" + System.currentTimeMillis();
    }

    public void schedulePickup(String label) {
        System.out.println("Pickup scheduled for " + label);
    }

    public boolean isAvailable() {
        return true;
    }
}