package pattern.structural.decorator;

public class Main {
	public Main() {
		ChristmasTree c = new ChristmasTree();
		
		BallDecorator b = new BallDecorator(c);
		b.place(new Branch());
		System.out.println(c.getBranch().name);
	}
	public static void main(String args[]) {
		Main m = new Main();		//Cannot be implemented properly
	}
	
}
