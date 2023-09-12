class Bag:
    def __init__(self, color, size, price):
        self.color = color
        self.size = size
        self.price = price

    def inform_state(self):
        print(f"Color: {self.color} size : {self.size}  cost: {self.price}$.")