/**
 * 
 */
package com.introspect.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author    叶昭良
 * @time      2015年3月3日下午6:34:08
 * @version   com.introspect.testTestStudentIntrospect V1.0
 */
public class TestStudentIntrospect
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Student wangxiuyan = new Student();//new Student("001","王秀艳",30);
		String fieldName="Age";
		//通过javabeans-->具体某个属性---> 通过反射获取方法！ -->再调用方法
		getProperty(wangxiuyan, fieldName);
		setProperty(wangxiuyan, fieldName);
		getProperties(wangxiuyan, fieldName);
	
	}
	private static void getProperties(Student stu,String fieldName)
	{
		try
		{
			BeanInfo  beanInfo = Introspector.getBeanInfo(stu.getClass());
			PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor temp : pro)
			{
				if(temp.getName().equals(fieldName))
				{
					Method methodx = temp.getReadMethod();

					System.out.println(methodx.invoke(stu)); 
					
				}
			}
		} catch (IntrospectionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void getProperty(Student stu,String fieldName)
	{
		try
		{
			PropertyDescriptor pro = new PropertyDescriptor(fieldName, Student.class);
			Method methodx = pro.getReadMethod(); //
			Object ojx = methodx.invoke(stu);
			System.out.println(ojx);
		} catch (IntrospectionException  e)
		{
			// TODO Auto-generated catch block
			System.out.println("属性描述获取失败");
		}catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			System.out.println("invoke调用失败！");
		}
		
	}
	private static void setProperty(Student stu,String fieldName)
	{
		try
		{
			PropertyDescriptor pro = new PropertyDescriptor(fieldName, Student.class);
			Method methodx = pro.getWriteMethod(); //
			methodx.invoke(stu,8);
			System.out.println(stu.getAge());
		} catch (IntrospectionException  e)
		{
			// TODO Auto-generated catch block
			System.out.println("属性描述获取失败");
		}catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			System.out.println("invoke调用失败！");
		}	
	}

}
