package pattern.creational.singleton;

public class SingletonInstantiator 
{
	public SingletonInstantiator() 
	{ 
		ClassicSingleton instance = ClassicSingleton.getInstance();
		ClassicSingleton instance1 = ClassicSingleton.getInstance();
		System.out.println(instance.toString());
		System.out.println(instance1.toString());
		//ClassicSingleton anotherInstance = new ClassicSingleton();
		//ClassicSingleton anotherInstance1 = new ClassicSingleton();
		//System.out.println(anotherInstance.toString());
		instance.assam = "Hero1";
		System.out.println(instance.assam);
		System.out.println(instance1.assam);
		//anotherInstance.assam = "Hero";
		//System.out.println(anotherInstance.assam);
	} 
	public static void main(String args[])
	{
		new SingletonInstantiator();
	}
}
