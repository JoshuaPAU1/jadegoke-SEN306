package Exercises.exercise_1;

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

    public OrderResult checkout(String userId, String productId,
                                double price, String address) {

        if (!inventory.checkStock(productId)) {
            return new OrderResult(false, null, "Out of stock");
        }

        if (!payment.charge(userId, price)) {
            return new OrderResult(false, null, "Payment failed");
        }

        inventory.reserve(productId);

        if (!shipping.isAvailable()) {
            payment.refund(userId, price);
            inventory.release(productId);
            return new OrderResult(false, null, "Shipping unavailable");
        }

        String tracking = shipping.createLabel(address);
        shipping.schedulePickup(tracking);

        email.send(userId, "Order Confirmed", "Tracking: " + tracking);

        return new OrderResult(true, tracking, "Order successful");
    }
}