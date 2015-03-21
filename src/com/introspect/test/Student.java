/**
 * 
 */
package com.introspect.test;

/**
 * @author    叶昭良
 * @time      2015年3月21日下午2:10:39
 * @version   com.introspect.testStudent V1.0
 */
//据说下面是符合javaBeans设计思想的普通类
public class Student
{
	//字段私有化  ，使用BeansInfo必须是用id  name  num而不是id name num
	/*private String id;
	private String name;
	private int num;*/
	
	private int id;
	private String name;
	private String num;
	
	//提供一个无参的构造函数
	public Student()
	{
		
	}
	@Override
	public String toString()
	{
		StringBuilder studentInfo = new StringBuilder();
		studentInfo.append("学生的id：").append(id).append(",姓名:").append(name).append(
				",学号是:").append(num);
		return studentInfo.toString();
	}	
	public int getid()
	{
		return id;
	}
	public void setid(int id)
	{
		this.id = id;
	}
	public String getname()
	{
		return name;
	}
	public void setname(String name)
	{
		this.name = name;
	}
	public String getnum()
	{
		return num;
	}
	public void setnum(String num)
	{
		
		this.num = num;
	}
}
