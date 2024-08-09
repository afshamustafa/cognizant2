# Define a list of sales amounts
sales = [200, 600, 150, 800, 300]

# Function to generate a report of sales
def generate_report(sales):
    print("Sales Report:")
    for amount in sales:
        if amount > 500:
            print("High Sale: ",amount)
        else:
            print("Sale: ",amount)

# Generate the report for the example sales list
generate_report(sales)

# Calculate and print the total sales for the month
total_sales = sum(sales)
print("\nTotal Sales for the Month: ",total_sales)
