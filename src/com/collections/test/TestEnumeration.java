/**
 *  没测试 vector的性能    但是vector如果增长是会100%增长的
 *  完全可以用list的其他实现替代vector，不然ArrayList
 */
package com.collections.test;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author    叶昭良
 * @time      2015年2月23日下午6:15:53
 * @version   com.collections.testTestEnumeration V1.0
 */
public class TestEnumeration
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Vector<String> v1 =  new Vector<String>();
		v1.add("abc0");
		v1.add("abc1");
		v1.add("abc2");
		
		//早起jdk1.0出现的方法
		Enumeration<String> e  = v1.elements(); //会出现对应的方法！
		                                   //小技巧
		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
		}
		
		Iterator<String> it = v1.iterator();
		while(it.hasNext())
		{
			System.out.println();
		}
	}

}
