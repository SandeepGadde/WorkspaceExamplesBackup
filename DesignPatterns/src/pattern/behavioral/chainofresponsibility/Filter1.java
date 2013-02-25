package pattern.behavioral.chainofresponsibility;

public class Filter1 extends Still {
	private int size;

	public Filter1(int size) {
		this.size = size;
	}

	/**
	 * over-ridden method from base class
	 */
	public void collect(Matter gravel) {
		// for the entire quantity of matter
		for (int i = 0; i < gravel.getQuantity(); i++) {
			// if gravel size is less than size of filter,
			// the gravel will pass to the next level.
			if (gravel.getSize() < size) {
				System.out.println("gravel size is less than size");
				super.collect(gravel);
			} else {
				System.out.println("gravel size is more than size");
				// collect here. that means, only matter with less
				// size will pass...
			}
		}
	}

}// End of class
