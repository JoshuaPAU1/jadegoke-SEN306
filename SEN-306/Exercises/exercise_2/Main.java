package Exercises.exercise_2;

public class Main {
    public static void main(String[] args) {
        CheckoutFacade checkout = new CheckoutFacade();

        OrderResult result = checkout.checkout(
            "alice@example.com",
            "LAPTOP",
            999.99,
            "123 Main St",
            "CA"
        );

        if (result.isSuccess()) {
            System.out.println("Success! Tracking: " + result.getTrackingNumber());
        } else {
            System.out.println("Failed: " + result.getMessage());
        }
    }
}