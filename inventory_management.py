# Define a list of items with their quantities
inventory = [('item1', 10), ('item2', 0), ('item3', 5)]

# Function to check inventory
def check_inventory(inventory):
    # Iterate through the inventory list
    for item, quantity in inventory:
        # Check if the item is out of stock
        if quantity == 0:
            print(item," is out of stock!")

# Check the inventory and print the results
check_inventory(inventory)
