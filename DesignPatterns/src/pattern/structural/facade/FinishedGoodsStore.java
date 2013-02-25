package pattern.structural.facade;

public class FinishedGoodsStore implements Store {

	 public Goods getGoods() {
		FinishedGoods finishedGoods = new FinishedGoods();
		return finishedGoods;
	 }
}// End of class
