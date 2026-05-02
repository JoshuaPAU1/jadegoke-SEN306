package Exercises.exercise_3;

public class LegacyOrderFacade {

    private LegacyOrderProcessor legacyProcessor;

    public LegacyOrderFacade() {
        this.legacyProcessor = new LegacyOrderProcessor();
    }

    // Clean modern API
    public void placeOrder(String email, String itemCode,
                           double amount, String address) {

        // Just delegate to legacy system
        legacyProcessor.processOrder(email, itemCode, amount, address);
    }
}
