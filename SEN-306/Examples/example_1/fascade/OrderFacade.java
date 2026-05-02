class Inventory {
    public boolean checkStock(String productId) {
        System.out.println("Checking stock for " + productId);
        return true; 
    }

    public void reserve(String productId) {
        System.out.println("Reserving product: " + productId);
    }
}

class Payment {
    public boolean charge(String userId, double amount) {
        System.out.println("Charging user " + userId + " amount $" + amount);
        return true; 
    }
}

class Shipping {
    public String createLabel(String address) {
        System.out.println("Creating shipping label for " + address);
        return "SHIP123";
    }

    public void schedulePickup(String label) {
        System.out.println("Scheduling pickup with label " + label);
    }
}

class Email {
    public void send(String userId, String subject, String message) {
        System.out.println("Sending email to " + userId + ": " + subject);
    }
}

// ✅ Your Facade Class (unchanged, just integrated)
public class OrderFacade {
    private Inventory inventory;
    private Payment payment;
    private Shipping shipping;
    private Email email;
    
    public OrderFacade() {
        this.inventory = new Inventory();
        this.payment = new Payment();
        this.shipping = new Shipping();
        this.email = new Email();
    }
    
    public boolean placeOrder(String userId, String productId,
                              double price, String address) {
        if (!inventory.checkStock(productId)) return false;
        if (!payment.charge(userId, price)) return false;
        
        inventory.reserve(productId);
        String label = shipping.createLabel(address);
        shipping.schedulePickup(label);
        email.send(userId, "Order Confirmed", "On its way!");
        return true;
    }
}


class Main {
    public static void main(String[] args) {
        OrderFacade facade = new OrderFacade();

        boolean success = facade.placeOrder(
            "alice@example.com",
            "LAPTOP",
            999.99,
            "123 Main St"
        );

        if (success) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Order failed.");
        }
    }
}