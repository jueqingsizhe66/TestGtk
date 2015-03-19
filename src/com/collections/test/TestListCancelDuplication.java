/**
 * 
 */
package com.collections.test;

import java.util.*;
/**
 * @author    Ҷ����
 * @time      2015��2��23������6:25:14
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
		List<String> apple = new ArrayList<String>(); //Ϊʲô���ܣ�List?��Ϊlistֻ��һ���ӿ�
		apple.add("abc0");
		apple.add("abc1");
		apple.add("abc2");
		apple.add("abc1");
		apple.add("abc1");
		
		/*for(Object ob:apple)
		{
			System.out.println(ob);
		}*/
		
		//��һ�ַ�������  ����
		List<String> temp = getApple(apple);
		for(Object ap1:temp)
		{
			System.out.println(ap1);
		}
		
		//�ڶ��ַ����� ����Iterator
		List<String> temp1 = getApple1(apple);
		for(Object ap1:temp1)
		{
			System.out.println(ap1);
		}
	}

}