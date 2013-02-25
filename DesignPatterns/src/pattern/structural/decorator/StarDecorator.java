package pattern.structural.decorator;

public class StarDecorator {
	public StarDecorator(ChristmasTree tree) {
		Branch branch = tree.getBranch();
		place(branch);
	}
	/*
	* The method places each decorative item
	* on the tree.
	*/
	public void place(Branch branch) {
		branch.put("star");
	} 
}
