package pattern.behavioral.chainofresponsibility;

public class Main {
	public static void main(String args[]) {
		Matter m = new Matter();
		m.setQuantity(1);
		m.setSize(4);
		Filter1 filter = new Filter1(2);
		filter.collect(m);
	}
}
