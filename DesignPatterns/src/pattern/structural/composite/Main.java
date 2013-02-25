package pattern.structural.composite;

public class Main {
	Employee CFO = null;
	public static void main(String args[]) {
		Main m = new Main();
		m.addEmployeesToTree();
		System.out.println("Size:"+m.CFO.getSubordinates().size());
	}
	 private void addEmployeesToTree() {
		CFO = new Employee("CFO", 30000);
		
		Employee headFinance1 = new Employee("Head Finance. North Zone", 20000);
		Employee headFinance2 = new Employee("Head Finance. West Zone", 22000);
		
		Employee accountant1 = new Employee("Accountant1", 10000);
		Employee accountant2 = new Employee("Accountant2", 9000);
		
		Employee accountant3 = new Employee("Accountant3", 11000);
		Employee accountant4 = new Employee("Accountant4", 12000);
		
		CFO.add(headFinance1);
		CFO.add(headFinance2);
		
		headFinance1.add(accountant1);
		headFinance1.add(accountant4);
		
		headFinance2.add(accountant2);
		headFinance2.add(accountant3);
		
		}// End of class
	 
}
