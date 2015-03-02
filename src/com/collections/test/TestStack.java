/**
 *  堆栈是先进后出
 */
package com.collections.test;

/**
 * @author    叶昭良
 * @time      2015年2月23日下午7:47:29
 * @version   com.collections.testTestStack V1.0
 */
import java.util.*;
public class TestStack
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
		//list1.addLast(apple);
		//list1.addOffer(apple);
		
		list1.add(apple);
	}
	/**
	 * 
	 * @return  返回待删除的字符串
	 */
	public String pop()
	{
		int length = getListLength(); //笨蛋 直接用 list1.size()就可以了
		//System.out.println("长度为："+length);
		String apple = list1.get(length-1);
		//String apple = list1.get(length-1);
		//第一种方法
		//return list1.removeLast() 更方便IE
		//第二种方法
		//return list1.pollLast();
		list1.remove(length-1);
		return apple;
	}
	/**
	 *  得到堆栈的长度
	 * @return
	 */
	private static int getListLength()
	{
		int sum =0;
		Iterator<String> it = list1.iterator();
		while(it.hasNext())
		{
			sum++;
			it.next();
		}
		return sum;
	}
	
	public  void sayStatck()
	{
		Iterator<String> it = list1.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		TestStack ts = new TestStack();
		ts.push("apple");
		ts.push("banana");
		ts.push("orange");
		
		
		ts.sayStatck();
		String text = ts.pop();
		ts.sayStatck();
	}

}
