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
	//字段私有化  ，使用BeansInfo必须是用Id  Name  Age而不是Id Name Age
	/*private String Id;
	private String Name;
	private int Age;*/
	
	private String id;
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
		studentInfo.append("学生的Id：").append(id).append(",姓名:").append(Name).append(
				",年龄是:").append(Age);
		return studentInfo.toString();
	}	
	public String getId()
	{
		return id;
	}
	public void setId(String Id)
	{
		this.id = Id;
	}
	public String getName()
	{
		return Name;
	}
	public void setName(String Name)
	{
		this.Name = Name;
	}
	public int getAge()
	{
		return Age;
	}
	public void setAge(int Age)
	{
		if(Age < 0)
		{
			Age = 0;
		}
		this.Age = Age;
	}
}
