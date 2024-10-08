# Define a dictionary to store customer names and their purchase amounts
customer_data = {
    'Alice': 120,
    'Bob': 75,
    'Charlie': 90
}

# Function to update purchase amount for a given customer
def update_purchase(customer_data, name, amount):
    if name in customer_data:
        customer_data[name] = amount
    else:
        print("Customer ",name," not found.")

# Update the purchase amount for 'Bob' to 100
update_purchase(customer_data, 'Bob', 100)

# Print the updated customer data
print("Updated customer data:")
for customer, amount in customer_data.items():
    print(customer,":",amount)
