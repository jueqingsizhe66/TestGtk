/**
 * 
 */
package com.collections.test;

import java.util.*;
/**
 * @author    叶昭良
 * @time      2015年2月23日下午6:25:14
 * @version   com.collections.testTestListCancelDuplication V1.0
 */
public class TestListCancelDuplication
{

	/**
	 * @param args
	 */
	public static List<String> getApple(List<String> ct)
	{
		List<String> list1 = new ArrayList<String>();
		if(ct.isEmpty())
		{
			return null;
		}
		list1.add(ct.get(0));
		for(int i = 1 ; i < ct.size(); i++)
		{
			if(!list1.contains(ct.get(i)))
			{
				list1.add(ct.get(i));
			}
		}
		return list1;
	}
	public static List<String> getApple1(List<String> ct)
	{
		List<String> list1 = new ArrayList<String>();
		if(ct.isEmpty())
		{
			return null;
		}
		//list1.add(ct.get(0));
		Iterator<String> it = ct.iterator();
		while(it.hasNext())
		{
			String str = (String)it.next();
			if(!list1.contains(str))
			{
				list1.add(str);
			}
		}
		return list1;
	}
	public static void main(String[] args)
	{
		//	 TODO Auto-generated method stub
		List<String> apple = new ArrayList<String>(); //为什么不能？List?因为list只是一个接口
		apple.add("abc0");
		apple.add("abc1");
		apple.add("abc2");
		apple.add("abc1");
		apple.add("abc1");
		
		/*for(Object ob:apple)
		{
			System.out.println(ob);
		}*/
		
		//第一种方法利用  函数
		List<String> temp = getApple(apple);
		for(Object ap1:temp)
		{
			System.out.println(ap1);
		}
		
		//第二种方法！ 利用Iterator
		List<String> temp1 = getApple1(apple);
		for(Object ap1:temp1)
		{
			System.out.println(ap1);
		}
	}

}
