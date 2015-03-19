/**
 *  �����   �½���һ�� new Comparator<T>(){}��ʵ�֣�����sort���棡���൱��
 *  �������ʵ��
 *  
 *  comparable��compareTo(object o1��
 *      Comparator��compare(object o1,object o2)
 *      
 *     comparators�����Լ��½���һ���Ƚ�����
 *                     �Ƚ�����������Arrays.sort�ĵڶ�������
 */
package com.collections.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author    Ҷ����
 * @time      2015��2��24������5:18:55
 * @version   com.collections.testTestTreeSetComparator1 V1.0
 */
public class TestTreeSetComparator1
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		PersonMan[] pdian = new PersonMan[]
				{
					new PersonMan("yezhao", 10, Boolean.FALSE),
					new PersonMan("yexiao",20,Boolean.TRUE),
					new PersonMan("yexiao",28,Boolean.TRUE),	
					new PersonMan("yexiao",24,Boolean.TRUE),
					new PersonMan("xin", 24, Boolean.FALSE),
					
				};
		TreeSet<PersonMan> ts1 =  new TreeSet<PersonMan>();
		for(PersonMan temp:pdian)
		{
			ts1.add(temp);
		}
		Iterator<PersonMan> it = ts1.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}	
		
		System.out.println("��������һ������ʽ �����µ�treeSet");
		//Ҳ����ֱ�Ӷ���һ��new Comparators() �������ʵ��Comparators����
		//�����������implement Comparators 
		//��ôֻҪ���䷵��һ��java.util.comparator���ɣ�newһ��comparator����
		//�������ַ�����ʵ��
		//Ȼ��д��compare�����Ϳ����ˣ�
//		TreeSet tsPig= new TreeSet(new Comparators().getComparator());
		//�������� TestTreeSetComparator�и���
		
		System.out.println("��ʼ����һ������");
		Arrays.sort(pdian, new Comparator<Object>()
		{
			// 0 ��ʾ  o1  o2���
			// ���� ��ʾ  o1 < o2
			// ���� ��ʾ  o1 > o2
			@Override
			public int compare(Object o1, Object o2)
			{
				if(o1 instanceof String)
				{
					//�ַ����ıȽ�
					return compare((String) o1,(String) o2);
				}else if(o1 instanceof Integer)
				{
					//�����ıȽ�
					return compare((Integer) o1,(Integer) o2);
				}else if(o1 instanceof Boolean)
				{
					//�������͵ıȽ�
					return compare((Boolean) o1,(Boolean) o2);
				}else if(o1 instanceof PersonMan)
				{
					//�������͵ıȽ�
					return compare((PersonMan) o1,(PersonMan) o2);
				}else
				{
					System.out.println("δ�ҵ����ʵıȽ���	");
					return 1; //Ĭ�ϴ���0
				}
			}
			//���ڱȽ��ַ���
			public int compare(String o1, String o2)
			{
				//��ʱ����һ�� �ַ���
				String s1 = (String)o1;
				String s2 = (String)o2;
				//��ȡ�ַ����ĳ���
				int len1 = s1.length();
				int len2 = s2.length();
				//��ȡ��Сֵ
				int n = Math.min(len1, len2);
				//ת��Ϊ�ַ�����
				char[] v1 = s1.toCharArray();
				char[] v2 = s2.toCharArray();
				
				//����λ�ò���
				int pos = 0;
				
				//��δ��ͷ  ����index�±���ͷ��β�����ж�
				while(n-- != 0)
				{
					char c1 = v1[pos];
					char c2 = v2[pos];
					if(c1 != c2)
					{
						return c1 - c2;
					}
					pos++;
				}
				return len1-len2; //�෴���Ƿ��� ����
			}
			//���ڱȽ�����
			public int compare(Integer o1, Integer o2)
			{
				int val1 = o1.intValue();
				int val2 = o2.intValue();
				//return (val1 < val2 ? -1 :(val1 == val2 ? 0 : 1));
//							return (val1 > val2 ? 1 :(val1 == val2 ? 0 : -1));
				//����
				//return (val2 > val1 ? -1 :(val1 == val2 ? 0 : 1));
				//����
				return (val2 > val1 ? -1 :(val1 == val2 ? 0 : 1));
			}
			//���ڱȽϲ���ֵ
			public int compare(Boolean o1,Boolean o2)
			{
				return (o1.equals(o2)? 0: (o1.booleanValue()==true)?1:-1);
			}
			
			public int compare(final PersonMan o1, final PersonMan o2)
			{
				String Name1 = o1.getName();
				String Name2 = o2.getName();
				int Age1 = o1.getAge();
				int Age2 = o2.getAge();
				Boolean sex1 = o1.getSex();
				Boolean sex2 = o2.getSex();
				/*//��һ�� �߱Ƚ�����
				return (compare(Age1,Age2)==0? 
						//�ڶ��αȽ�����
						(compare(Name1,Name2)==0? 
								//�����αȽ��Ա�
								(compare(sex1,sex2)==0? 0 :compare(sex1,sex2)):
									compare(Name1,Name2)):
										compare(Age1,Age2));*/
				//��һ��  �Ƚ�����
				return (compare(Name1,Name2)==0?
						//�ڶ��αȽ� ����
						(compare(Age1,Age2)==0?
								//�����αȽ�����
								(compare(sex1,sex2)==0?0:compare(sex1,sex2)):
									compare(Age1,Age2)):
										compare(Name1,Name2));
						
			}
		});
		
		for(PersonMan temp : pdian)
		{
			System.out.println(temp);
		}
		
	}

}

/**
 * class StudentComparatorByName implements Comparator
{
    public int compare(Object o1,Object o2)
    {
        Student s1 = (Student)o1;
        Student s2 = (Student)o2;
 
        int num = s1.getName().compareTo(s2.getName());
        return num==0?new Integer(s1.getAge()).compareTo(new Integer(s2.getAge())):num;
    }
}
���Ʒ���
 * @author    Ҷ����
 * @time      2015��2��24������5:59:02
 * @version   com.collections.testPersonMan V1.0
 */
//������Comparable�ӿ�
class PersonMan implements Comparable<Object>
{
	private String Name;
	private int Age;
	private Boolean Sex;
	

	@Override
	public String toString()
	{
		return "PersonPig [Name=" + this.Name + ", Age=" + this.Age + ",Sex="+(this.Sex?"��":"Ů")+"]";
	}


	public PersonMan(String name, int age,Boolean Sex)
	{
		//super();
		this.Name = name;
		this.Age =age;
		this.Sex = Sex;
	}


/*	*//**
	 * object o1 ��ʲô��
	 *//*
	@Override
	public int compare(Object o1, Object o2)
	{
		// TODO Auto-generated method stub
		return 0;
	}*/


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


	public void setAge(int Age)
	{
		this.Age = Age;
	}
	
	public Boolean getSex()
	{
		return this.Sex;
	}
	public void setSex(Boolean Sex)
	{
		this.Sex = Sex;
	}
	public int compareTo(Object o1)
	{
		//����Լ����ڲ��ıȽ����� ������ͨ����
		PersonMan ptemp	= (PersonMan)o1;
		//����������
		int num = new Integer(this.Age).compareTo(ptemp.getAge());
		System.out.println("�ڲ���Num"+num);
		//����������
		return num==0?this.Name.compareTo(ptemp.getName()):num;
	}
	
}
