/**
 * 通过comparators这个类 调用getComparator，返回一个comparator对象
 */
package com.collections.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;





/**
 * @author    叶昭良
 * @time      2015年2月24日下午1:25:42
 * @version   com.collections.testTestTreeSetComparator V1.0
 */
public class TestTreeSetComparator
{

	/**
	 * @param <K>
	 * @param args
	 */
	public static <K> void callt(K t)
	{
		System.out.println(t);
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//测试2 添加自定义类 类中必须重写Comparable接口
		callt("abc");
		
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
		
		PersonPig[] pdian = new PersonPig[]
				{
					new PersonPig("yezhao",34,Boolean.TRUE),
					new PersonPig("xinran",10,Boolean.FALSE),
					new PersonPig("zhaoliang",33,Boolean.TRUE),
					new PersonPig("zhaidc",30,Boolean.FALSE),
					new PersonPig("zhaidc",31,Boolean.FALSE),
				};
		for(int  i =0 ; i < pdian.length; i++)
		{
			System.out.println("before sort:"+pdian[i]);
		}
		Arrays.sort(pdian, new Comparators().getComparator());
		for(int  i =0 ; i < pdian.length; i++)
		{
			System.out.println("After sort:"+pdian[i]);
		}
		
		
		//TreeSet<PersonPig> ts2 = new TreeSet<PersonPig>(); //找到解决办法了
		TreeSet<PersonPig> ts2 = new TreeSet<PersonPig>(new Comparators().getComparator());
		for(PersonPig temp:pdian)
		{
			ts2.add(temp);
		}
		Iterator<PersonPig> it2 = ts2.iterator();
		while(it2.hasNext())
		{
			PersonPig pTemp = (PersonPig)it2.next();
			System.out.println(pTemp);
		}
		
		//直接利用 Person2 实现的 Comparator
/*		System.out.println("真心的Comparator实现");
		Person2[] pdian2 = new Person2[]
				{
					new Person2("yezhao",34),
					new Person2("xinran",10),
					new Person2("zhaoliang",33),
					new Person2("zhaidc",30),
					new Person2("zhaidc",31),
				};
		TreeSet<Person2> tsDog = new TreeSet<Person2>();
		for(Person2 temp:pdian2)
		{
			tsDog.add(temp);
		}
		Iterator itDog = tsDog.iterator();
		while(itDog.hasNext())
		{
			System.out.println(itDog.next());
		}*/
		
	}

}

class PersonPig 
{
	private String Name;
	private int Age;
	private Boolean Sex;
	

	@Override
	public String toString()
	{
		return "PersonPig [Name=" + this.Name + ", Age=" + this.Age + ",Sex="+(this.Sex?"男":"女")+"]";
	}


	public PersonPig(String name, int age,Boolean Sex)
	{
		//super();
		this.Name = name;
		this.Age =age;
		this.Sex = Sex;
	}


/*	*//**
	 * object o1 是什么？
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
	
}

class Comparators
{
	public Comparator getComparator()
	{
		return new Comparator()
		{

			// 0 表示  o1  o2相等
			// 负数 表示  o1 < o2
			// 整数 表示  o1 > o2
			@Override
			public int compare(Object o1, Object o2)
			{
				if(o1 instanceof String)
				{
					//字符串的比较
					return compare((String) o1,(String) o2);
				}else if(o1 instanceof Integer)
				{
					//整数的比较
					return compare((Integer) o1,(Integer) o2);
				}else if(o1 instanceof Boolean)
				{
					//布尔类型的比较
					return compare((Boolean) o1,(Boolean) o2);
				}else if(o1 instanceof PersonPig)
				{
					//布尔类型的比较
					return compare((PersonPig) o1,(PersonPig) o2);
				}else
				{
					System.out.println("未找到合适的比较器	");
					return 1; //默认大于0
				}
			}
			//用于比较字符串
			public int compare(String o1, String o2)
			{
				//暂时备份一下 字符串
				String s1 = (String)o1;
				String s2 = (String)o2;
				//获取字符串的长度
				int len1 = s1.length();
				int len2 = s2.length();
				//获取最小值
				int n = Math.min(len1, len2);
				//转化为字符数组
				char[] v1 = s1.toCharArray();
				char[] v2 = s2.toCharArray();
				
				//设置位置参数
				int pos = 0;
				
				//从未到头  按照index下表从头到尾进行判断
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
				return len1-len2; //相反则是反序 降序
			}
			//用于比较整数
			public int compare(Integer o1, Integer o2)
			{
				int val1 = o1.intValue();
				int val2 = o2.intValue();
				//return (val1 < val2 ? -1 :(val1 == val2 ? 0 : 1));
//				return (val1 > val2 ? 1 :(val1 == val2 ? 0 : -1));
				//升序
				//return (val2 > val1 ? -1 :(val1 == val2 ? 0 : 1));
				//降序
				return (val2 > val1 ? 1 :(val1 == val2 ? 0 : -1));
			}
			//用于比较布尔值
			public int compare(Boolean o1,Boolean o2)
			{
				return (o1.equals(o2)? 0: (o1.booleanValue()==true)?1:-1);
			}
			
			public int compare(final PersonPig o1, final PersonPig o2)
			{
				String Name1 = o1.getName();
				String Name2 = o2.getName();
				int Age1 = o1.getAge();
				int Age2 = o2.getAge();
				Boolean sex1 = o1.getSex();
				Boolean sex2 = o2.getSex();
				/*//第一次 线比较年龄
				return (compare(Age1,Age2)==0? 
						//第二次比较名字
						(compare(Name1,Name2)==0? 
								//第三次比较性别
								(compare(sex1,sex2)==0? 0 :compare(sex1,sex2)):
									compare(Name1,Name2)):
										compare(Age1,Age2));*/
				//第一次  比较年龄
				return (compare(Name1,Name2)==0?
						//第二次比较 岁数
						(compare(Age1,Age2)==0?
								//第三次比较岁数
								(compare(sex1,sex2)==0?0:compare(sex1,sex2)):
									compare(Age1,Age2)):
										compare(Name1,Name2));
						
			}
		};
	}
}

//泛型接口
interface iter<K>
{
	void show(K k);
}


class Impl implements iter<String>
{

	@Override
	public void show(String k)
	{
		// TODO Auto-generated method stub
		
	}
	
}

class Impl1<T> implements iter<T>
{

	@Override
	public void show(T k)
	{
		// TODO Auto-generated method stub
		
	}
	
}