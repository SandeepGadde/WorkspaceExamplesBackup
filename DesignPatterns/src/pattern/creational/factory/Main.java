package pattern.creational.factory;

public class Main {
	public static void main(String args[]) {
		Main m = new Main();
		m.getPerson("Assam","M");
	}
	public Person getPerson(String name,String gender) {
		if(gender.equals("M"))
			return new Male(name);
		else
			return new Female(name);
	}
}
