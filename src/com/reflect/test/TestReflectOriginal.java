/**
 *   reflect 包含三个主要部分   java.lang.Constructor java.lang.Field java.lang.Method
 *   
 *   反射的感觉就是逆着来
 */
package com.reflect.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * @author    叶昭良
 * @time      2015年3月3日下午3:17:16
 * @version   com.reflect.testTestReflectOriginal V1.0
 */
public class TestReflectOriginal
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		//注意Class类大写！ 符合一般的类规范
		Class clazz = Person.class;
		Class clazzConstructor = Person.class;
		Class clazzMethod = Person.class;
		
		//Test class
		//*************************************************************
		//第一种方式获得类
		System.out.println("//*************************************************************");
		Class clazz1 = Person.class;
		//第二种方式获得类
		System.out.println("第一种方式获得类"+clazz1.getName());
		Person personClass = new Person();
		Class clazz2 = personClass.getClass();
		System.out.println("第二种方式获得类"+clazz1.getName());
		//第三种方式获得类
		try
		{
			Class clazz3 = Class.forName("com.reflect.test.Person");
			System.out.println("第三种方式获得类"+clazz1.getName());
			System.out.println("三种类方式都是同一个类！因为类只加载一次"+(clazz2==clazz1)+(clazz2==clazz3));
			System.out.println("包的名字"+clazz3.getPackage().getName());
			System.out.println("简单类名字（不带包）"+clazz3.getSimpleName());

			System.out
					.println("------------------------------通过Class对象获得类的构造函数---------------------");
			Constructor[] constructors = clazz3.getConstructors();
			for (Constructor constructor : constructors) {
				System.out.println(constructor);
			}

			System.out.println("------------------通过Class对象获得类的字段----------------");
			Field[] fields = clazz3.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field);
			}

			System.out.println("--------通过Class对象获得类的方法----------");

			Method[] methods = clazz3.getMethods();// 返回所有的public 方法,包括父类声明的public方法
			for (Method method : methods) {
				System.out.println(method);
			}

		} catch (ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//*************************************************************
		//TestField  测试字段开始
		//*************************************************************
		System.out.println("//*************************************************************");
		try
		{
			/**
			 *  打印出所有字段
			 *  
			 *  字段类的一个特点设置
			 */
			Field[] fields = clazz.getDeclaredFields();
			for(Field temp : fields)
			{
				System.out.println(temp);
			}
			Person personLiMing = (Person) clazz.newInstance();
			/**
			 * 测试添加一个名字的字段
			 */
			//Field nameField = clazz.getField("Name"); //会报异常
			//Field nameField = clazz.getDeclaredField("name"); //注意区分大小写
			Field nameField = clazz.getDeclaredField("Name");
			//一定得设置？
			nameField.setAccessible(true);
			nameField.set(personLiMing,"李明");
			
			Object o = nameField.get(personLiMing);
			System.out.println(o);
			personLiMing.sayHi();
		} catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			System.out.println("创建新的实例异常");
		}catch( NoSuchFieldException | SecurityException e)
		{
			System.out.println("没有那个字段");
		}
		//*************************************************************
		System.out.println("//*************************************************************");
		System.out.println("开始对Person类的 构造函数进行测试--------------");
		/**
		 *  分为两个部分测试构造函数 
		 *  1：  有参的 ，注意有参的时候String.class int.class
		 *  2：  无参的
		 */
		//TestConstructor
		/**
		 * 打印所有的类的构造函数
		 */
		try
		{
			Constructor con1 = clazzConstructor.getDeclaredConstructor();
			
			Object o = con1.newInstance();
			
			Person personWanghao = (Person)o;
			personWanghao.sayHi();
			
			System.out.println("------------------------");
			
			Constructor<Person> con2 = clazzConstructor.getDeclaredConstructor(String.class,int.class);
			Object o2 = con2.newInstance("汪峰",22);
			//很浅显的道理  只有构造函数可以新建对象！！上面的代码正是
			//体现如此！	
			Person personWangfeng = (Person)o2;
			personWangfeng.sayHi();
			
		} catch (NoSuchMethodException | SecurityException e)
		{
			// TODO Auto-generated catch block
			System.out.println("获取构造函数异常");
		}catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			System.out.println("通过构造函数创建实例异常");
		}
		
		//*************************************************************
		//TestMethod
		/**
		 *  开始测试 所有Person类的普通方法
		 *  
		 *  方法类的一个特点，方法对应具体的参数！类似于构造函数
		 *  都需要考虑重载的问题
		 */
		System.out.println("//*************************************************************");
		System.out.println("开始测试 所有Person类的普通方法---------");
		Method[] methods = clazzMethod.getDeclaredMethods();
		
		for(Method temp: methods)
		{
			System.out.println(temp);
		}
		
		//测试Person的sayHi方法
		try
		{
			Method sayHi = clazzMethod.getDeclaredMethod("sayHi");
			//Method singSong = clazz.getDeclaredMethod("singSong");
			
			Object o = clazzMethod.newInstance();
			
			Person personPig = (Person)o;
			personPig.sayHi();
			System.out.println("通过普通方法1 进行Invoke1！！！");

			sayHi.invoke(o);
			System.out.println("通过普通方法1 进行Invoke2！！！");
			//反射的感觉有点像，逆着来的感觉
			//通过方法调用对象！！并执行某个动作！
			//而不是通过对象调用方法执行某个动作
			sayHi.invoke(personPig);
 
			Class returnType = sayHi.getReturnType();
			System.out.println("Person类 sayHi的返回值类型"+returnType);
			
			int modifies = sayHi.getModifiers();
			//private 的值为0   public的值为1
			System.out.println("是private?:"+Modifier.isPrivate(modifies));
			System.out.println(modifies);
			
			/**
			 *  必须隐藏 ！否则下面的无法执行！ 有对应的参数  需要设置上 参数才可以
			 *  否则找不到 singSong() 无参的函数
			 */
		/*	Method singSong = clazzMethod.getDeclaredMethod("singSong");
			System.out.println(singSong);*/
			
			Method singSong2 = clazzMethod.getDeclaredMethod("singSong", String.class);
			Person personDandan = new Person("淡淡",22);
			singSong2.invoke(personDandan, "好汉歌");
			System.out.println(singSong2.getReturnType());
			
			//System.out.println("Person类singSong的返回值类型"+clazz.getDeclaredMethod("singSong").getReturnType());
		} catch (NoSuchMethodException | SecurityException e)
		{
			// TODO Auto-generated catch block
			System.out.println("没有对应的方法"+e.getMessage());
		}catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			System.out.println("获取实例异常"+e.getMessage());
		}catch (IllegalArgumentException | InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Invoke异常"+e.getMessage());
		}
		
	}

}
