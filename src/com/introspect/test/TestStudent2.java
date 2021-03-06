	/**
 * 
 */
package com.introspect.test;

/**
 * @author    叶昭良
 * @time      2015年3月3日下午7:31:34
 * @version   com.introspect.testTestStudent2 V1.0
 */
import java.util.*;
import java.beans.*;
import java.lang.reflect.*;
public class TestStudent2
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Map<String,Object> studentInfo = new HashMap<String,Object>();
		studentInfo.put("id", "001");
		studentInfo.put("name","wangyin");
		studentInfo.put("age",3);
		
		//创建student对象
		Student s1 = new Student();
		
		System.out.println("封装数据之前"+s1);
		
		//packagingStudent(s1,studentInfo);
		try
		{
			//packagingObject(s1,studentInfo);
			packagingStudentUsingInstropector(s1,studentInfo);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("封装数据之后"+s1);
	}
	
	public static void packagingStudent(Student stu,Map<String,Object> map)
	{
		/*stu.setId((String)map.get("Id"));
		stu.setName((String)map.get("Name"));
		stu.setAge((int)map.get("Age"));*/
		
		//而且 字段 使用大写和小写都可以！！ 但是使用BeansInfo必须是小写的
		//字段
		stu.setId((String)map.get("id"));
		stu.setName((String)map.get("name"));
		stu.setAge((int)map.get("age"));
	}
	
	//使用反射把数据封装到对象中
	public static void packagingObject(Object obj,Map<String,Object> map)
	throws Exception
	{
		Class clazz = obj.getClass();
		
		//方法1
		/*Field[] fields = clazz.getDeclaredFields();
		for(Field temp :fields)
		{
			String fieldName = temp.getName();
			Object value = map.get(fieldName);
			temp.setAccessible(true);
			temp.set(obj, value);
		}*/
		//方法2
		Field[] fields = clazz.getDeclaredFields();
		for(Field temp :fields)
		{
			String fieldName = temp.getName();
			//间接得到setter方法的参数类型(字段可以！ 但是属性表怎么不可以）
			Class paramType  = temp.getType();
			String methodName = "set"+fieldName.substring(0, 1).toUpperCase()+
					fieldName.substring(1).toLowerCase();
			Method methodSet = clazz.getMethod(methodName, paramType);
			methodSet.invoke(obj, map.get(fieldName));

		}
	}
	
	//java内省简化 了反射的字段的拼接
	public static void packagingStudentUsingInstropector(Object obj,
			Map<String,Object> map) throws Exception
	{
		
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		
		//获得所有字段的属性值
		PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor temp:pro)
		{
			String fieldName = temp.getName();
			//通过属性的javabeans方法获得对应的set方法
			//Class c1 = temp.getPropertyType();
			//temp.getPropertyType()
			
			//age  调试结果   fieldName是age！ 也就是这边肯定是有问题的
			System.out.println(fieldName);
			Method methodSet = temp.getWriteMethod();
			if(methodSet !=null) //如果缺少if判断  就会报错！！
					//因为所有的类都包含 class字段！ 这是容易错误的点！
					//一定得注意这边
			{
				methodSet.invoke(obj, map.get(fieldName));
			}
		}
	}

}
