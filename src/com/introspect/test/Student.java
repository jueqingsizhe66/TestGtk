/**
 * 
 */
package com.introspect.test;

/**
 * @author    Ҷ����
 * @time      2015��3��3������6:09:39
 * @version   com.introspect.testStudent V1.0
 */
//��˵�����Ƿ���javaBeans���˼�����ͨ��
public class Student
{
	//�ֶ�˽�л�  ��ʹ��BeansInfo��������Id  Name  Age������Id Name Age
	/*private String Id;
	private String Name;
	private int Age;*/
	
	private String id;
	private String Name;
	private int Age;
	
	//�ṩһ���޲εĹ��캯��
	public Student()
	{
		
	}
	@Override
	public String toString()
	{
		StringBuilder studentInfo = new StringBuilder();
		studentInfo.append("ѧ����Id��").append(id).append(",����:").append(Name).append(
				",������:").append(Age);
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