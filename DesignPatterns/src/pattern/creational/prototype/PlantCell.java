package pattern.creational.prototype;

public class PlantCell implements Cloneable {
	String hello = "assam";
 	public Object split() {
 	 	try {
 	 	 	return super.clone();
		}catch(Exception e) {
			System.out.println("Exception occured: "+e.getMessage());
		return null;
		}
 	}
 	public String data() {
 		return hello;
 	}
}// End of class