package Exercises.exercise_1;

public class Payment {
    public boolean charge(String userId, double amount) {
        return true;
    }

    public void refund(String userId, double amount) {
        System.out.println("Refunded " + amount);
    }
}