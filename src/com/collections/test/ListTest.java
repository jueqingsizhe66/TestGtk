/**
 * 
 */
package com.collections.test;

import java.util.*;

/**
 * @author    Ҷ����
 * @time      2015��2��23������12:15:42
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
		 *   ����list�ڲ��б���Collection�����еķ���
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
		
		System.out.println("fdsf��list1�ĵ�"+list1.indexOf("fdsf")+"λ");
		//���һ��abc1���ֵ�λ��
		System.out.println(list1.lastIndexOf("abc1"));
		
		
		List list2 = list1.subList(1, 4);
		System.out.println("list2:"+list2);
		
		ListIterator lit = list1.listIterator();
		while(lit.hasNext())
		{
			Object  o = lit.next();
			System.out.println("List:"+o);
/*	
 *  ����ѭ����		
			if(o.equals("abc1"))
			{
				Object oo1 = lit.previous();
				System.out.println("-----"+oo1);
			}*/
		}
	}

}