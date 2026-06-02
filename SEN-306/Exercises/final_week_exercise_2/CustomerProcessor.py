from typing import List, Optional

# Constants for customer types
REGULAR_CUSTOMER = 1
PREMIUM_CUSTOMER = 2

REGULAR_DISCOUNT = 0.10
PREMIUM_DISCOUNT = 0.20


def process_customer(
    name: str,
    address: str,
    customer_type: int,
    email: Optional[str],
    is_vip: bool,
    orders: List[float]
) -> float:
    """Processes a customer's order total, applies discounts, and sends an email alert."""
    
    # Python's built-in sum() replaces the manual for-loop
    order_sum = sum(orders)
    
    # Determine discount
    discount = 0.0
    if customer_type == REGULAR_CUSTOMER:
        discount = REGULAR_DISCOUNT
    elif customer_type == PREMIUM_CUSTOMER:
        discount = PREMIUM_DISCOUNT
        
    final_total = order_sum - (order_sum * discount)
    
    # Build clean message using f-strings
    message = f"Hello {name} of {address}, your total is {final_total}"
    if is_vip:
        message += " (VIP)"
        
    print(message)
    
    if email is not None:
        send_email(email, message)
        
    # Return the value to fix the line 13 flaw
    return final_total