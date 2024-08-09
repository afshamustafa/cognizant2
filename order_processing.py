# Define the order amount
order_amount = 150

# Function to apply discount
def apply_discount(order_amount):
    # Apply a 10% discount if the order amount is greater than $100
    if order_amount > 100:
        discount = 0.10  # 10% discount
        return order_amount * (1 - discount)
    else:
        return order_amount

# Calculate the final price for the example order amount
final_price = apply_discount(order_amount)

# Print the final price after applying the discount
print("Final price after applying discount: $",final_price)
