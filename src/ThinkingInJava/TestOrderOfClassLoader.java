/**
 *  ע��۲�˳�� ������Ϊʲô
 */
package ThinkingInJava;

/**
 * @author    Ҷ����
 * @time      2015��3��3������12:20:50
 * @version   ThinkingInJavaTestOrderOfClassLoader V1.0
 */
public class TestOrderOfClassLoader  extends PortableLunch
{

	/**
	 * @param args
	 */
	private Bread b = new Bread();
	private Cheese c = new Cheese();
	private Lettuce l = new Lettuce();
	public TestOrderOfClassLoader() { System.out.println("Sandwich()"); }
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new TestOrderOfClassLoader();
	}
	//: polymorphism/Sandwich.java
	// Order of constructor calls.

}
class Meal {
	Meal() { System.out.println("Meal()"); }
	}
	class Bread {
	Bread() { System.out.println("Bread()"); }
	}
	class Cheese {
	Cheese() { System.out.println("Cheese()"); }
	}
	class Lettuce {
	Lettuce() { System.out.println("Lettuce()"); }
	}
	class Lunch extends Meal {
	Lunch() { System.out.println("Lunch()"); }
	}
	class PortableLunch extends Lunch {
	PortableLunch() { System.out.println("PortableLunch()");}
	}