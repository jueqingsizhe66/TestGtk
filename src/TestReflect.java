import java.lang.reflect.Method;

/**
 * 
 */

/**
 * @author    Ҷ����
 * @time      2015��2��4������10:30:57
 * @version   TestReflect V1.0  ����һ�·������  ����ĵ�һ�����ðѰ��е����ж�����ʾ����
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