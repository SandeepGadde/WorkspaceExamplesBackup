package pattern.behavioral.command;

public class Waiter {
	public Food takeOrder(Customer cust, Order order) {

		Cook cook = new Cook();
		Food food = cook.prepareOrder(order, this);
		return food;
	}
}
