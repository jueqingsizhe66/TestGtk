/**
 * 解释：
 * 采用反射的方法创建Person类的一个对象，并且通过反射的方法调用setAge、setName进行赋值，并且用反射的方法调用sayHello方法。

记录完成所需要的时长
 */
package com.reflect.test;
import java.lang.reflect.*;
/**
 * @author    叶昭良
 * @time      2015年3月21日下午1:43:08
 * @version   com.reflect.testTestPerson1Reflect V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestPerson1Reflect
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Person1 p1 = new Person1();
		Class testClass = p1.getClass();
		try
		{
			Field nameField  = testClass.getDeclaredField("name");
			Field ageField = testClass.getDeclaredField("age");
			nameField.setAccessible(true);
			ageField.setAccessible(true);
			nameField.set(p1, "张三");
			ageField.set(p1, 32);
			System.out.println("通过对象调用sayHello方法");
			p1.sayHello();
			
			Method helloSay = testClass.getDeclaredMethod("sayHello");
			System.out.println("第二种方法打印sayHello");
			helloSay.invoke(p1);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
