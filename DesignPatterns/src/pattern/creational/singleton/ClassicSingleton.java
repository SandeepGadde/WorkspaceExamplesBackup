package pattern.creational.singleton;
public class ClassicSingleton {
   private static ClassicSingleton instance = null;
   public String assam = "Real Assam";
   private ClassicSingleton() {
      // Exists only to defeat instantiation.
	   System.out.println("Hello");
   }
   public static ClassicSingleton getInstance() {
      if(instance == null) {
         instance = new ClassicSingleton();
      }
      return instance;
   }
}
