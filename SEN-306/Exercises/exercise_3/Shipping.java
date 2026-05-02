package Exercises.exercise_3;

public class Shipping {
    public String createLabel(String address) { return "TRK" + System.currentTimeMillis(); }
    public void schedulePickup(String label) { System.out.println("Pickup scheduled for " + label); }
}
