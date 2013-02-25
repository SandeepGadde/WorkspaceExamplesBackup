package pattern.behavioral.command;

public class Main {
	public static void main(String args[]) {
		Waiter w = new Waiter();
		w.takeOrder(new Customer("Assam"),new Order("Lunch"));
		
	}
}
