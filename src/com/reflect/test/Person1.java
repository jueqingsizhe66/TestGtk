/**
 * ���ͣ�
 */
package com.reflect.test;

/**
 * @author    Ҷ����
 * @time      2015��3��21������1:42:37
 * @version   com.reflect.testPerson1 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class Person1
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
	 */
	private int age;

	private String name;

	public void setAge(int age)

	{

	    this.age = age;

	}

	public void setName(String name)

	{

	    this.name = name;

	}

	public void sayHello()

	{

	    System.out.println("��ã�����"+name+"����"+age+"����");

	}

}