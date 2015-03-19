/**
 * ���ͣ�
 */
package com.reflect.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author    Ҷ����
 * @time      2015��3��16������8:43:56
 * @version   com.reflect.testTestReflectOriginal2 V1.0
 * ���ܣ�   java�����������ʵ�ʵı�̵��еĻ������� ��д
                ���裺 1�� ��  2���ֶ�  3�����캯�� 4������
 * ע�⣺ 1��getDeclared ������û��Declared
 * ���գ� 1����������ķ���  2������ͨ���ֶ�invoke����
                ˼���� ������ʵ�������е�ʹ�÷�ʽ
 * �عˣ� 1���ٴλع��˷����ʹ�÷���
 */
public class TestReflectOriginal2
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
		Class clazzField = Person.class;
		Class clazzConstructor = Person.class;
		Class clazzMethod = Person.class;
		
		/**
		 * ��ʼ�������ֲ�ͬ������÷�ʽ
		 
		 */
		//1
		Class clazz1 = Person.class;
		System.out.println(clazz1.getName());
		//2
		Person per = new Person();
		Class clazz2 = per.getClass();
		System.out.println(clazz2.getName());
		//3
		Class clazz3 = null;
		try
		{
			clazz3 = Class.forName("com.reflect.test.Person");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("��õ�һ�ֻ�ȡ��ķ���  Class.forName():");
		System.out.println(clazz3.getName());
		System.out.println(clazz3.getSimpleName());
		//�ֶεĲ���
		//��ο�ʼ���ԣ�
		//�����л�ȡ
	
		//�������ж��Ǵ����
/*		//��ȡ�����ֶ�
		Field[] fields = clazz3.getFields();
		//��ȡ���еĹ�����
		Constructor[] cons = clazz3.getConstructors();
		//��ȡ���еķ���
		Method[] methods = clazz3.getMethods();
		
		for(Field temp : fields)
		{
			System.out.println(temp.getName());
		}*/
		//��ȡ�����ֶ�
		Field[] fields = clazz3.getDeclaredFields();
		//��ȡ���еĹ�����
		Constructor[] cons = clazz3.getDeclaredConstructors();
		//��ȡ���еķ���
		Method[] methods = clazz3.getDeclaredMethods();
		
		for(Field temp : fields)
		{
			System.out.println(temp.getName());
		}
		
		//Field Test
		Person personLijian= new Person();
		try
		{
			Field nameField = clazzField.getDeclaredField("Name");
			System.out.println(nameField);
			nameField.setAccessible(true);
			nameField.set(personLijian, "lijian");
			System.out.println(nameField.get(personLijian));
			Object  o = nameField.get(personLijian);
			System.out.println(o);
			personLijian.sayHi();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			//��ȡ�޲ι��캯��
			Constructor  contest= clazzConstructor.getDeclaredConstructor();
			//��ȡ�вι��캯��
			//Constructor conWithParameter = clazzConstructor.getDeclaredConstructor(String.class,int.class);
			Constructor<Person> conWithParameter = clazzConstructor.getDeclaredConstructor(String.class,int.class);
			Person piaoliang = conWithParameter.newInstance("Ư��",33);
			System.out.println("�޲ι��캯����"+contest);
			System.out.println("�вι��캯����"+conWithParameter);
			piaoliang.sayHi();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Method temp : methods)
		{
			System.out.println(temp);
		}
		try
		{
			Method sayHi = clazzMethod.getDeclaredMethod("sayHi");
			Person p1 = (Person)clazzMethod.newInstance();
			p1.sayHi();
			sayHi.invoke(p1);
			
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}