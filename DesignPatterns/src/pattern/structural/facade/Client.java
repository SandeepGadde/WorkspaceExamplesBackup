package pattern.structural.facade;

public class Client {

	 /**
	* to get raw materials
	*/
	public static void main(String[] args) {
		StoreKeeper keeper = new StoreKeeper();
		FinishedGoods finishedGoods = (FinishedGoods) keeper.getGoods("");
		finishedGoods.getGoods();
	}
}// End of class
