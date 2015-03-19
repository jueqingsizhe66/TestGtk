/**
 * 
 */
package com.collections.test;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author    Ҷ����
 * @time      2015��2��23������8:35:46
 * @version   com.collections.testTestHashSet V1.0
 */
public class TestHashSet
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		HashSet<String> hs = new HashSet<String>();
		hs.add("abc0");
		hs.add("abc1");
		hs.add("abc2");
		hs.add("abc3");
		hs.add("abc4");
		hs.add("abc0");
		
		System.out.println(hs);
		
		//set����û������ index  ���Ա�����iterator
		Iterator it = hs.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		/**
		 * Person���hashCode��ִ��
			Person���hashCode��ִ��
			Person���hashCode��ִ��
			Person���hashCode��ִ��
			Person���hashCode��ִ��
			Person���equals������ִ��
		 */
		Person p1 = new Person("zhao",32);
		Person p2 = new Person("zhao1",34);
		Person p5 = new Person("zhao1",34);
		Person p3 = new Person("zhao2",35);
		Person p4 = new Person("zhao3",36);
		
		HashSet<Person> hs1 = new HashSet<Person>();
		hs1.add(p1);
		hs1.add(p2);
		hs1.add(p3);
		hs1.add(p4);
		hs1.add(p5);
		
		Iterator it1 = hs1.iterator();
		while(it1.hasNext())
		{
			Person p11 = (Person)it1.next();
			//System.out.println(p11.getName()+":"+p11.getAge());
			System.out.println(p11);
		}
	}

}

//�Զ����м���set��Ҫ��дhashCode��equals����   ���ϵĲ����ظ��ԣ������Ա���
//����hashcode��equals���ֱȽϵͼ��ģ������TreeSet���ÿ��Ǵ�С��������
///Comparable
class Person implements Comparable
{
	
	/*public int compareTo(String anotherString)
	{
		return Name.compareTo(anotherString);
	}*/
	private int Age;
	private String Name;
	
	public Person( String name,int age)
	{
		//super();
		this.Age = age;
		this.Name = name;
	}
	public int getAge()
	{
		return this.Age;
	}
	public void setAge(int age)
	{
		this.Age = age;
	}
	public String getName()
	{
		return this.Name;
	}
	public void setName(String name)
	{
		this.Name = name;
	}
	/// �Ҽ�source---�� generate equals and hashcode method
	@Override
	public int hashCode()
	{
		System.out.println("Person���hashCode��ִ��");
		final int prime = 31;
		int result = 1;
		result = prime * result + Age;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		System.out.println("Person���equals������ִ��");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (Age != other.Age)
			return false;
		if (Name == null)
		{
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "Person [Age=" + Age + ", Name=" + Name + "]";
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
		if(!(o instanceof Person))
		{
			return -1;
		}
		
		Person p1 = (Person)o;
		//�Ȱ�����������
		int temp = p1.Age - this.Age;
		
		if(temp ==0)
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
				return this.Name.compareTo(p1.Name);
			}
		}
		return 0;
	}
		
}