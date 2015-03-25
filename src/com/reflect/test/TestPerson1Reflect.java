/**
 * ���ͣ�
 * ���÷���ķ�������Person���һ�����󣬲���ͨ������ķ�������setAge��setName���и�ֵ�������÷���ķ�������sayHello������

��¼�������Ҫ��ʱ��
 */
package com.reflect.test;
import java.lang.reflect.*;
/**
 * @author    Ҷ����
 * @time      2015��3��21������1:43:08
 * @version   com.reflect.testTestPerson1Reflect V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestPerson1Reflect
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
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
			nameField.set(p1, "����");
			ageField.set(p1, 32);
			System.out.println("ͨ���������sayHello����");
			p1.sayHello();
			
			Method helloSay = testClass.getDeclaredMethod("sayHello");
			System.out.println("�ڶ��ַ�����ӡsayHello");
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