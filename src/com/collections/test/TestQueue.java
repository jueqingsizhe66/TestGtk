/**
 * 
 */
package com.collections.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author    Ҷ����
 * @time      2015��2��23������8:07:29
 * @version   com.collections.testTestQueue V1.0
 */
public class TestQueue
{

	/**
	 * @param args
	 */
	private static List<String> list1 = new ArrayList<String>();
	/**
	 * 
	 * @param apple  �����ַ�������ջ��
	 */
	public void push(String apple)
	{
		list1.add(apple);
	}
	/**
	 * 
	 * @return  ���ش�ɾ�����ַ���
	 */
	public String pop()
	{
		//System.out.println("����Ϊ��"+length);
		String apple = list1.get(0);
		//String apple = list1.get(length-1);
		list1.remove(0);
		return apple;
	}

	
	public  void sayStatck()
	{
		Iterator<String> it = list1.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		TestQueue ts = new TestQueue();
		ts.push("apple");
		ts.push("banana");
		ts.push("orange");
		
		
		ts.sayStatck();
		String text = ts.pop();
		System.out.println("Popֵ��"+text);
		ts.sayStatck();
		
		String text1 = ts.pop();
		System.out.println("Popֵ��"+text1);
		ts.sayStatck();
	}

}