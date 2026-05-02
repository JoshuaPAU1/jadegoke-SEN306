package Exercises.exercise_3;

public class Main {
    public static void main(String[] args) {

        LegacyOrderFacade facade = new LegacyOrderFacade();

        facade.placeOrder(
            "alice@example.com",
            "LAPTOP",
            999.99,
            "123 Main St"
        );
    }
}
