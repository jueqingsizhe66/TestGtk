/**
 * 
 */
package com.collections.test;

/**
 * @author    叶昭良
 * @time      2015年2月24日上午2:13:50
 * @version   com.collections.testTestTreeSet V1.0
 */
import java.util.*;
public class TestTreeSet
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//测试1  添加字符串对象
		
		TreeSet<String> ts = new TreeSet<String>();
		ts.add("abc");
		ts.add("bcda");
		ts.add("zdfa");
		ts.add("cde");
		ts.add("efg");
		
		Iterator<String> it = ts.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		//测试2 添加自定义类 类中必须重写Comparable接口
		
		Person1 p1 = new Person1("zhao",34);
		Person1 p2 = new Person1("liang",31);
		Person1 p3 = new Person1("xin",10);
		Person1 p4 = new Person1("ran",20);
		TreeSet<Person1> ts1 = new TreeSet<Person1>();
		ts1.add(p1);
		ts1.add(p2);
		ts1.add(p3);
		ts1.add(p4);
		Iterator<Person1> it1 = ts1.iterator();
		while(it1.hasNext())
		{
			Person1 pTemp = (Person1)it1.next();
			System.out.println(pTemp);
		}
		
		TreeMap m;
	}

}
