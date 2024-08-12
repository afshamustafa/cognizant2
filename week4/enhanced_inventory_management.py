import json
import threading
import time

class Inventory:
    def __init__(self):
        self.items = {}

    def add_item(self, item_name, quantity):
        if item_name in self.items:
            self.items[item_name] += quantity
        else:
            self.items[item_name] = quantity
        print("Added ",quantity," of ",item_name,". New stock:", self.items[item_name])

    def remove_item(self, item_name, quantity):
        if item_name in self.items and self.items[item_name] >= quantity:
            self.items[item_name] -= quantity
            print("Removed ",quantity," of ",item_name,". Remaining stock:", self.items[item_name])
        else:
            print("Insufficient stock or item not found")

    def check_stock(self, item_name):
        return self.items.get(item_name, 0)

    def save_to_file(self, filename):
        try:
            with open(filename, 'w') as file:
                json.dump(self.items, file)
            print("Inventory saved to ",filename)
        except IOError as e:
            print("Error saving inventory to file: ",e)

    def load_from_file(self, filename):
        try:
            with open(filename, 'r') as file:
                self.items = json.load(file)
            print("Inventory loaded from ",filename)
        except (IOError, json.JSONDecodeError) as e:
            print("Error loading inventory from file: ",e)

    def restocking_alerts(self, threshold=5, check_interval=10):
        while True:
            for item_name, quantity in self.items.items():
                if quantity <= threshold:
                    print("Alert: ",item_name," is low in stock. Current quantity: ",quantity)
            time.sleep(check_interval)

inventory = Inventory()

inventory.add_item("Apples", 10)
inventory.add_item("Bananas", 2)
inventory.add_item("Oranges", 15)
inventory.remove_item("Apples", 7)
inventory.remove_item("Bananas", 1)

inventory.save_to_file("inventory_data.json")

inventory.load_from_file("inventory_data.json")
print("Loaded inventory state:", inventory.items)

restocking_thread = threading.Thread(target=inventory.restocking_alerts, args=(5, 5), daemon=True)
restocking_thread.start()

time.sleep(20)

print("Stopping inventory management system...")
