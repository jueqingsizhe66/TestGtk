/**
 *  ��ջ���Ƚ����
 */
package com.collections.test;

/**
 * @author    Ҷ����
 * @time      2015��2��23������7:47:29
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
	 * @param apple  �����ַ�������ջ��
	 */
	public void push(String apple)
	{
		//list1.addLast(apple);
		//list1.addOffer(apple);
		
		list1.add(apple);
	}
	/**
	 * 
	 * @return  ���ش�ɾ�����ַ���
	 */
	public String pop()
	{
		int length = getListLength(); //���� ֱ���� list1.size()�Ϳ�����
		//System.out.println("����Ϊ��"+length);
		String apple = list1.get(length-1);
		//String apple = list1.get(length-1);
		//��һ�ַ���
		//return list1.removeLast() ������IE
		//�ڶ��ַ���
		//return list1.pollLast();
		list1.remove(length-1);
		return apple;
	}
	/**
	 *  �õ���ջ�ĳ���
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