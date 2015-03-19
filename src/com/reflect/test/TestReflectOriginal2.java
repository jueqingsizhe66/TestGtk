/**
 * 解释：
 */
package com.reflect.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author    叶昭良
 * @time      2015年3月16日下午8:43:56
 * @version   com.reflect.testTestReflectOriginal2 V1.0
 * 功能：   java反射机制用于实际的编程当中的基础测试 重写
                步骤： 1： 类  2：字段  3：构造函数 4：方法
 * 注意： 1：getDeclared 而不是没有Declared
 * 掌握： 1：第三种类的方法  2：掌握通过字段invoke函数
                思考： 反射在实际运用中的使用方式
 * 回顾： 1：再次回顾了反射的使用方法
 */
public class TestReflectOriginal2
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
		Class clazzField = Person.class;
		Class clazzConstructor = Person.class;
		Class clazzMethod = Person.class;
		
		/**
		 * 开始测试三种不同的类调用方式
		 
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
		System.out.println("最常用的一种获取类的方法  Class.forName():");
		System.out.println(clazz3.getName());
		System.out.println(clazz3.getSimpleName());
		//字段的测试
		//如何开始测试？
		//从类中获取
	
		//下面三中都是错误的
/*		//获取所有字段
		Field[] fields = clazz3.getFields();
		//获取所有的构造器
		Constructor[] cons = clazz3.getConstructors();
		//获取所有的方法
		Method[] methods = clazz3.getMethods();
		
		for(Field temp : fields)
		{
			System.out.println(temp.getName());
		}*/
		//获取所有字段
		Field[] fields = clazz3.getDeclaredFields();
		//获取所有的构造器
		Constructor[] cons = clazz3.getDeclaredConstructors();
		//获取所有的方法
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
			//获取无参构造函数
			Constructor  contest= clazzConstructor.getDeclaredConstructor();
			//获取有参构造函数
			//Constructor conWithParameter = clazzConstructor.getDeclaredConstructor(String.class,int.class);
			Constructor<Person> conWithParameter = clazzConstructor.getDeclaredConstructor(String.class,int.class);
			Person piaoliang = conWithParameter.newInstance("漂亮",33);
			System.out.println("无参构造函数："+contest);
			System.out.println("有参构造函数："+conWithParameter);
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
