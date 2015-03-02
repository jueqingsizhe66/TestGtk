/**
 * 
 */
package com.collections.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author    叶昭良
 * @time      2015年2月23日下午8:07:29
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
	 * @param apple  添加字符串到堆栈中
	 */
	public void push(String apple)
	{
		list1.add(apple);
	}
	/**
	 * 
	 * @return  返回待删除的字符串
	 */
	public String pop()
	{
		//System.out.println("长度为："+length);
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
		System.out.println("Pop值："+text);
		ts.sayStatck();
		
		String text1 = ts.pop();
		System.out.println("Pop值："+text1);
		ts.sayStatck();
	}

}
