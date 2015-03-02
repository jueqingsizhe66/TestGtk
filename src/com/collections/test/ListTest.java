/**
 * 
 */
package com.collections.test;

import java.util.*;

/**
 * @author    叶昭良
 * @time      2015年2月23日下午12:15:42
 * @version   com.collections.testListTest V1.0
 */
public class ListTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
//		Collection c = new LinkedList();
		/**
		 *   测试list内部有别于Collection的特有的方法
		 */
		List<String> list1 = new ArrayList<String>();
		list1.add("abc0");
		list1.add("abc1");
		list1.add("abc2");
		System.out.println(list1);
		
		list1.add(1, "abchaha");
		System.out.println(list1);
		
		for(int i = 0 ; i < list1.size(); i++)
		{
			System.out.println("list1["+i+"]="+list1.get(i));
		}
		for(Object o:list1)
		{
			System.out.println(o);
		}
		
		list1.set(3, "fdsf");
		System.out.println(list1);
		
		System.out.println("fdsf在list1的第"+list1.indexOf("fdsf")+"位");
		//最后一次abc1出现的位置
		System.out.println(list1.lastIndexOf("abc1"));
		
		
		List list2 = list1.subList(1, 4);
		System.out.println("list2:"+list2);
		
		ListIterator lit = list1.listIterator();
		while(lit.hasNext())
		{
			Object  o = lit.next();
			System.out.println("List:"+o);
/*	
 *  无线循环中		
			if(o.equals("abc1"))
			{
				Object oo1 = lit.previous();
				System.out.println("-----"+oo1);
			}*/
		}
	}

}
