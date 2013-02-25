package pattern.behavioral.command;

public class Food {
	public Food(Order o) {
		System.out.println(o.getOrder());
	}
}
