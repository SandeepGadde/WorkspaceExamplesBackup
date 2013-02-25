package pattern.behavioral.command;

public class Order {
	private String command;

	public Order(String command) {
		this.command = command;
	}
	public String getOrder() {
		return command;
	}
}
