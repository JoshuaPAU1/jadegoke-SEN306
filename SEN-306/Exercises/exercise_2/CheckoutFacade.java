package Exercises.exercise_2;

import java.time.LocalDateTime;

public class CheckoutFacade {

    private Inventory inventory;
    private Payment payment;
    private Shipping shipping;
    private Email email;

    public CheckoutFacade() {
        this.inventory = new Inventory();
        this.payment = new Payment();
        this.shipping = new Shipping();
        this.email = new Email();
    }

    // =========================
    // NEW: TAX CALCULATOR
    // =========================
    private double calculateTax(String state, double price) {
        if ("CA".equalsIgnoreCase(state)) {
            return price * 0.08;
        }
        return 0.0;
    }

    // =========================
    // NEW: LOGGER
    // =========================
    private void log(String userId, boolean success) {
        System.out.println(
            "[" + LocalDateTime.now() + "] "
            + "User: " + userId
            + " | Status: " + (success ? "SUCCESS" : "FAIL")
        );
    }

    public OrderResult checkout(String userId, String productId,
                                double price, String address, String state) {

        // 1. Stock check
        if (!inventory.checkStock(productId)) {
            log(userId, false);
            return new OrderResult(false, null, "Out of stock");
        }

        // 2. Tax calculation
        double tax = calculateTax(state, price);
        double total = price + tax;

        // 3. Payment
        if (!payment.charge(userId, total)) {
            log(userId, false);
            return new OrderResult(false, null, "Payment failed");
        }

        // 4. Reserve product
        inventory.reserve(productId);

        // 5. Shipping check
        if (!shipping.isAvailable()) {
            payment.refund(userId, total);
            inventory.release(productId);

            log(userId, false);
            return new OrderResult(false, null, "Shipping unavailable");
        }

        // 6. Shipping process
        String tracking = shipping.createLabel(address);
        shipping.schedulePickup(tracking);

        // 7. Email WITH TOTAL PRICE
        email.send(
            userId,
            "Order Confirmed",
            "Tracking: " + tracking + " | Total Paid: $" + total
        );

        // 8. Log success
        log(userId, true);

        return new OrderResult(true, tracking, "Order successful");
    }
}