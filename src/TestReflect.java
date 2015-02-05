import java.lang.reflect.Method;

/**
 * 
 */

/**
 * @author    叶昭良
 * @time      2015年2月4日上午10:30:57
 * @version   TestReflect V1.0  测试一下反射机制  反射的第一个作用把包中的所有东西显示出来
 */
public class TestReflect
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Class c;
		try
		{
			//c = Class.forName("java.lang.String");
			c = Class.forName("GTKEncapsulate.OOCairo");
			//Method[] mApple = c.getMethods();
			Method[] mApple = c.getDeclaredMethods();
			for(int i = 0 ; i < mApple.length; i++)
			{
				//System.out.println("mApple["+i+"] is : "+mApple[i]);
				System.out.println("mApple["+i+"] is : "+mApple[i].toString());
			}
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
