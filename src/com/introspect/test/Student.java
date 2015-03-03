/**
 * 
 */
package com.introspect.test;

/**
 * @author    叶昭良
 * @time      2015年3月3日下午6:09:39
 * @version   com.introspect.testStudent V1.0
 */
//据说下面是符合javaBeans设计思想的普通类
public class Student
{
	//字段私有化
	private String Id;
	private String Name;
	private int Age;
	
	//提供一个无参的构造函数
	public Student()
	{
		
	}
	@Override
	public String toString()
	{
		StringBuilder studentInfo = new StringBuilder();
		studentInfo.append("学生的Id：").append(Id).append(",姓名:").append(Name).append(
				",年龄是:").append(Age);
		return studentInfo.toString();
	}	
	public String getId()
	{
		return Id;
	}
	public void setId(String id)
	{
		Id = id;
	}
	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public int getAge()
	{
		return Age;
	}
	public void setAge(int age)
	{
		if(age < 0)
		{
			age = 0;
		}
		Age = age;
	}
}
