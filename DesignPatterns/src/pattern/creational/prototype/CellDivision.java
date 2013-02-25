package pattern.creational.prototype;

public class CellDivision {
	public static void main(String args[]) {
		PlantCell cell = new PlantCell();
		System.out.println(cell.toString()+"|"+cell.data());
		cell.hello = "assam123123";
		PlantCell cell1 = (PlantCell) cell.split();
		cell1.hello = "assam";
		System.out.println(cell1.toString()+"|"+cell1.data());
	}
}
