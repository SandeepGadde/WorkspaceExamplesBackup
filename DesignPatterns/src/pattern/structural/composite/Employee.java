package pattern.structural.composite;

import java.util.Vector;

public class Employee {
	private String name;
	private double salary;
	private Vector subordinates;

	public Vector getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Vector subordinates) {
		this.subordinates = subordinates;
	}

	// constructor
	public Employee(String name, double sal) {
		setName(name);
		setSalary(sal);
		subordinates = new Vector();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void add(Employee e) {
		subordinates.addElement(e);
	}

	public void remove(Employee e) {
		subordinates.remove(e);
	}
}
