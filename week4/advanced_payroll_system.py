class Employee:
    def __init__(self, name, hours_worked, hourly_rate):
        self.name = name
        self.hours_worked = hours_worked
        self.hourly_rate = hourly_rate

    def calculate_pay(self):
        standard_hours = 40
        overtime_rate = 1.5

        if self.hours_worked <= standard_hours:
            total_pay = self.hours_worked * self.hourly_rate
        else:
            overtime_hours = self.hours_worked - standard_hours
            total_pay = (standard_hours * self.hourly_rate) + (overtime_hours * self.hourly_rate * overtime_rate)

        return total_pay


class Manager(Employee):
    def __init__(self, name, hours_worked, hourly_rate, bonus):
        super().__init__(name, hours_worked, hourly_rate)
        self.bonus = bonus

    def calculate_pay(self):
        base_pay = super().calculate_pay()
        return base_pay + self.bonus

employee = Employee(name="Allen", hours_worked=45, hourly_rate=30)
employee_pay = employee.calculate_pay()

# Instantiate a Manager object
manager = Manager(name="Paul", hours_worked=45, hourly_rate=40, bonus=400)
manager_pay = manager.calculate_pay()

# Print the total pay for both
print("Total pay for ",employee.name," is ",employee_pay)
print("Total pay for ",manager.name," is ",manager_pay)
