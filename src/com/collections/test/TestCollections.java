/**
 * 
 */
package com.collections.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author    Ҷ����
 * @time      2015��2��22������2:10:32
 * @version   com.collections.testTestCollections V1.0
 */
public class TestCollections
{
 
	/**
	 * @param args
	 * ������Ҫ������������ʹ�ã� ��Ϊ���������ǲ���˼�Ǵ����ӵ������е�����
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Collection<String> cl  = new ArrayList<String>();
		cl.add("mi");
		cl.add("lei");
		
		System.out.println(cl);
		
		cl.clear();
		System.out.println(cl);
		cl.add("mi");
		cl.add("lei");
		System.out.println(cl);
		System.out.println("cl�Ĵ�С��:"+(cl.size()));
		System.out.println("cl�Ƿ���mi"+cl.contains("mi"));
		cl.remove("mi");
		System.out.println("cl�Ĵ�С��:"+(cl.size()));
		System.out.println("cl�Ƿ���mi"+cl.contains("mi"));
		cl.add("mi");
		System.out.println(cl);
		System.out.println("�Ƿ�Ϊ�գ�"+cl.isEmpty());
		
		
		///     �ڶ��鷽��		
		Collection<String> c2 = new ArrayList<String>();
		c2.add("mi");
		c2.add("do");
		c2.add("co");
		
		
		
		//Ŀ�����ڼ���Ǹ�ͼ����ȷ�ԡ�
		//��cl ��c2�Ľ���
		cl.retainAll(c2);
		System.out.println(cl);
		
		cl.addAll(c2);
		System.out.println(cl);
		
		//ɾ��������c2�е�Ԫ��
		cl.removeAll(c2);
		System.out.println(cl);
		
		System.out.println("cl�Ƿ����c2"+cl.containsAll(c2));
		cl.addAll(c2);
		System.out.println("cl�Ƿ����c2"+cl.containsAll(c2));
		
		//��֤linkedlist Ҳ�ǿ����������У���������������֮��
		Collection<String> c3 = new LinkedList<String>();
		c3.add("suo");
		c3.add("lei");
		c3.add("xi");
		
		
		//��cl ��c2�Ľ���
		cl.retainAll(c3);
		System.out.println(cl);
		
		cl.addAll(c3);
		System.out.println(cl);
		
		//ɾ��������c2�е�Ԫ��
		cl.removeAll(c3);
		System.out.println(cl);
		
		System.out.println("cl�Ƿ����c3"+cl.containsAll(c3));
		cl.addAll(c3);
		System.out.println("cl�Ƿ����c3"+cl.containsAll(c3));
		
		iteratorTest(cl);
		
		//�����Զ�����
		System.out.println("�����Զ�����");
		Collection<Person1> c4 = new HashSet<Person1>();
		Person1 p1 = new Person1("zhangsan ",30);
		Person1 p2 = new Person1("lisi",31);
		c4.add(p1);
		c4.add(p2);
		System.out.println(c4);
	}
	/**
	 *  Ϊʲô������ҪIterator  ����ʲô�ô���ȱ�㣿
	 * @param c  �ӿڣ�
	 */
	public static void iteratorTest(Collection c)
	{
		Iterator it = c.iterator();
		while(it.hasNext()) //�����һ��С�ط���Ҫע��
			        //ֻ�����ж��Ƿ�it��previous pointer���ڣ����ܼ�����
		{
			Object o = it.next();
			it.remove();
			System.out.println(o);
			break;
		}
		//֮���Բ��ܲ��� add�����Ǵ���̫�ߣ� ��remove���Ǵ���СһЩ
		System.out.println("remove֮���Collections��"+c);
	}

}


class Person1 implements Comparable
{
	private String Name;
	private int Age;
	public Person1(String Name,int age)
	{
		this.Name = Name;
		this.Age  = age;
	}
	public String toString()
	{
		return this.Name+"-----"+this.Age;
	}
	
	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public int getAge()
	{
		return Age;
	}
	public void setAge(int age)
	{
		this.Age = age;
	}
	
	@Override
	public int compareTo(Object o)
	{
		// TODO Auto-generated method stub
		//���������������
		if(this == o)
		{
			return 0;
		}
		if(this == null)
		{
			return -1;
		}
		if(!(o instanceof Person1))
		{
			return -1;
		}
		
		Person1 p1 = (Person1)o;
		//�Ȱ�����������
		//����
		//int temp = p1.Age - this.Age;
		//����
		int temp = this.Age - p1.Age ;
		System.out.println("����temp֮��");
		if(temp == 0)
		{
			if(this.Name == null)
			{
				if(p1.Name == null)
				{
					return 0;
				}else
				{
					return -1;
				}
			}else
			{
				System.out.println("���������");
				return this.Name.compareTo(p1.Name);
			}
		}
		//return 0 ; ��ɵĴ�����
		return temp;
	}
	
}

class Person2 implements Comparator
{
	private String Name;
	private int Age;
	public Person2(String Name,int age)
	{
		this.Name = Name;
		this.Age  = age;
	}
	public String toString()
	{
		return this.Name+"-----"+this.Age;
	}
	
	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public int getAge()
	{
		return Age;
	}
	public void setAge(int age)
	{
		this.Age = age;
	}
	
	@Override
	public int compare(Object o1, Object o2)
	{
		// TODO Auto-generated method stub
		//���������������
		if(o1 == o2)
		{
			return 0;
		}
		if(o1 == null ||o2 == null)
		{
			return -1;
		}
		
		if(!((o1 instanceof Person2) || (o2 instanceof Person2)))
		{
			return -1;
		}
		
		Person2 p1 = (Person2)o1;
		Person2 p2 = (Person2)o2;
		//�Ȱ�����������
		//����
		//int temp = p1.Age - this.Age;
		//����
		int temp = p1.Age - p2.Age ;
		System.out.println("����temp֮��");
		if(temp == 0)
		{
			if(p1.Name == null)
			{
				if(p2.Name == null)
				{
					return 0;
				}else
				{
					return -1;
				}
			}else
			{
				System.out.println("���������");
				return compare(p1.Name, p2.Name);
			}
		}
		//return 0 ; ��ɵĴ�����
		return temp;
	}
	
}