/**
 *   reflect ����������Ҫ����   java.lang.Constructor java.lang.Field java.lang.Method
 *   
 *   ����ĸо�����������
 */
package com.reflect.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * @author    Ҷ����
 * @time      2015��3��3������3:17:16
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
		
		//ע��Class���д�� ����һ�����淶
		Class clazz = Person.class;
		Class clazzConstructor = Person.class;
		Class clazzMethod = Person.class;
		
		//Test class
		//*************************************************************
		//��һ�ַ�ʽ�����
		System.out.println("//*************************************************************");
		Class clazz1 = Person.class;
		//�ڶ��ַ�ʽ�����
		System.out.println("��һ�ַ�ʽ�����"+clazz1.getName());
		Person personClass = new Person();
		Class clazz2 = personClass.getClass();
		System.out.println("�ڶ��ַ�ʽ�����"+clazz1.getName());
		//�����ַ�ʽ�����
		try
		{
			Class clazz3 = Class.forName("com.reflect.test.Person");
			System.out.println("�����ַ�ʽ�����"+clazz1.getName());
			System.out.println("�����෽ʽ����ͬһ���࣡��Ϊ��ֻ����һ��"+(clazz2==clazz1)+(clazz2==clazz3));
			System.out.println("��������"+clazz3.getPackage().getName());
			System.out.println("�������֣���������"+clazz3.getSimpleName());

			System.out
					.println("------------------------------ͨ��Class��������Ĺ��캯��---------------------");
			Constructor[] constructors = clazz3.getConstructors();
			for (Constructor constructor : constructors) {
				System.out.println(constructor);
			}

			System.out.println("------------------ͨ��Class����������ֶ�----------------");
			Field[] fields = clazz3.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field);
			}

			System.out.println("--------ͨ��Class��������ķ���----------");

			Method[] methods = clazz3.getMethods();// �������е�public ����,��������������public����
			for (Method method : methods) {
				System.out.println(method);
			}

		} catch (ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//*************************************************************
		//TestField  �����ֶο�ʼ
		//*************************************************************
		System.out.println("//*************************************************************");
		try
		{
			/**
			 *  ��ӡ�������ֶ�
			 *  
			 *  �ֶ����һ���ص�����
			 */
			Field[] fields = clazz.getDeclaredFields();
			for(Field temp : fields)
			{
				System.out.println(temp);
			}
			Person personLiMing = (Person) clazz.newInstance();
			/**
			 * ��������һ�����ֵ��ֶ�
			 */
			//Field nameField = clazz.getField("Name"); //�ᱨ�쳣
			//Field nameField = clazz.getDeclaredField("name"); //ע�����ִ�Сд
			Field nameField = clazz.getDeclaredField("Name");
			//һ�������ã�
			nameField.setAccessible(true);
			nameField.set(personLiMing,"����");
			
			Object o = nameField.get(personLiMing);
			System.out.println(o);
			personLiMing.sayHi();
		} catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			System.out.println("�����µ�ʵ���쳣");
		}catch( NoSuchFieldException | SecurityException e)
		{
			System.out.println("û���Ǹ��ֶ�");
		}
		//*************************************************************
		System.out.println("//*************************************************************");
		System.out.println("��ʼ��Person��� ���캯�����в���--------------");
		/**
		 *  ��Ϊ�������ֲ��Թ��캯�� 
		 *  1��  �вε� ��ע���вε�ʱ��String.class int.class
		 *  2��  �޲ε�
		 */
		//TestConstructor
		/**
		 * ��ӡ���е���Ĺ��캯��
		 */
		try
		{
			Constructor con1 = clazzConstructor.getDeclaredConstructor();
			
			Object o = con1.newInstance();
			
			Person personWanghao = (Person)o;
			personWanghao.sayHi();
			
			System.out.println("------------------------");
			
			Constructor<Person> con2 = clazzConstructor.getDeclaredConstructor(String.class,int.class);
			Object o2 = con2.newInstance("����",22);
			//��ǳ�Եĵ���  ֻ�й��캯�������½����󣡣�����Ĵ�������
			//������ˣ�	
			Person personWangfeng = (Person)o2;
			personWangfeng.sayHi();
			
		} catch (NoSuchMethodException | SecurityException e)
		{
			// TODO Auto-generated catch block
			System.out.println("��ȡ���캯���쳣");
		}catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			System.out.println("ͨ�����캯������ʵ���쳣");
		}
		
		//*************************************************************
		//TestMethod
		/**
		 *  ��ʼ���� ����Person�����ͨ����
		 *  
		 *  �������һ���ص㣬������Ӧ����Ĳ����������ڹ��캯��
		 *  ����Ҫ�������ص�����
		 */
		System.out.println("//*************************************************************");
		System.out.println("��ʼ���� ����Person�����ͨ����---------");
		Method[] methods = clazzMethod.getDeclaredMethods();
		
		for(Method temp: methods)
		{
			System.out.println(temp);
		}
		
		//����Person��sayHi����
		try
		{
			Method sayHi = clazzMethod.getDeclaredMethod("sayHi");
			//Method singSong = clazz.getDeclaredMethod("singSong");
			
			Object o = clazzMethod.newInstance();
			
			Person personPig = (Person)o;
			personPig.sayHi();
			System.out.println("ͨ����ͨ����1 ����Invoke1������");

			sayHi.invoke(o);
			System.out.println("ͨ����ͨ����1 ����Invoke2������");
			//����ĸо��е����������ĸо�
			//ͨ���������ö��󣡣���ִ��ĳ��������
			//������ͨ��������÷���ִ��ĳ������
			sayHi.invoke(personPig);
 
			Class returnType = sayHi.getReturnType();
			System.out.println("Person�� sayHi�ķ���ֵ����"+returnType);
			
			int modifies = sayHi.getModifiers();
			//private ��ֵΪ0   public��ֵΪ1
			System.out.println("��private?:"+Modifier.isPrivate(modifies));
			System.out.println(modifies);
			
			/**
			 *  �������� ������������޷�ִ�У� �ж�Ӧ�Ĳ���  ��Ҫ������ �����ſ���
			 *  �����Ҳ��� singSong() �޲εĺ���
			 */
		/*	Method singSong = clazzMethod.getDeclaredMethod("singSong");
			System.out.println(singSong);*/
			
			Method singSong2 = clazzMethod.getDeclaredMethod("singSong", String.class);
			Person personDandan = new Person("����",22);
			singSong2.invoke(personDandan, "�ú���");
			System.out.println(singSong2.getReturnType());
			
			//System.out.println("Person��singSong�ķ���ֵ����"+clazz.getDeclaredMethod("singSong").getReturnType());
		} catch (NoSuchMethodException | SecurityException e)
		{
			// TODO Auto-generated catch block
			System.out.println("û�ж�Ӧ�ķ���"+e.getMessage());
		}catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			System.out.println("��ȡʵ���쳣"+e.getMessage());
		}catch (IllegalArgumentException | InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Invoke�쳣"+e.getMessage());
		}
		
	}

}