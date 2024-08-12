from abc import ABC, abstractmethod

class DiscountStrategy(ABC):
    @abstractmethod
    def apply_discount(self, order_amount):
        pass

class RegularDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount


class PremiumDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.9


class VIPDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.8

class Order:
    def __init__(self, customer_type, order_amount):
        self.customer_type = customer_type
        self.order_amount = order_amount
        self.discount_strategy = self._get_discount_strategy()

    def _get_discount_strategy(self):
        if self.customer_type == 'regular':
            return RegularDiscount()
        elif self.customer_type == 'premium':
            return PremiumDiscount()
        elif self.customer_type == 'vip':
            return VIPDiscount()
        else:
            print("Unknown customer type")

    def final_price(self):
        return self.discount_strategy.apply_discount(self.order_amount)

regular_order = Order(customer_type="regular", order_amount=1000)
premium_order = Order(customer_type="premium", order_amount=1000)
vip_order = Order(customer_type="vip", order_amount=1000)

print("Final price for regular customer: ",regular_order.final_price())
print("Final price for premium customer: ",premium_order.final_price())
print("Final price for VIP customer: ",vip_order.final_price())
