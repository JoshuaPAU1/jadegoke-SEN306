class Inventory {
    public boolean checkStock(String product) {
        System.out.println("Checking stock for " + product);
        return true; 
    }

    public void reserve(String product) {
        System.out.println("Reserving " + product);
    }
}

class Payment {
    public boolean charge(String email, double amount) {
        System.out.println("Charging " + email + " $" + amount);
        return true; 
    }
}

class Shipping {
    public String createLabel(String address) {
        System.out.println("Creating shipping label for " + address);
        return "LABEL123";
    }

    public void schedulePickup(String label) {
        System.out.println("Scheduling pickup for " + label);
    }
}

class Email {
    public void send(String to, String subject, String message) {
        System.out.println("Sending email to " + to + ": " + subject);
    }
}

public class Main {
    public static void main(String[] args) {

        // Client code without Facade
        Inventory inv = new Inventory();
        Payment pay = new Payment();
        Shipping ship = new Shipping();
        Email email = new Email();

        if (inv.checkStock("LAPTOP")) {
            if (pay.charge("alice@example.com", 999.99)) {
                inv.reserve("LAPTOP");

                String label = ship.createLabel("123 Main St");
                ship.schedulePickup(label);

                email.send(
                    "alice@example.com",
                    "Order Confirmed",
                    "Your order is on the way"
                );

                System.out.println("Order success!");
            } else {
                System.out.println("Payment failed");
            }
        } else {
            System.out.println("Out of stock");
        }
    }
}