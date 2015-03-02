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
 * @author    叶昭良
 * @time      2015年2月22日上午2:10:32
 * @version   com.collections.testTestCollections V1.0
 */
public class TestCollections
{
 
	/**
	 * @param args
	 * 泛型主要用于在容器中使用！ 因为它代表的那层意思是待添加到容器中的类型
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
		System.out.println("cl的大小是:"+(cl.size()));
		System.out.println("cl是否含有mi"+cl.contains("mi"));
		cl.remove("mi");
		System.out.println("cl的大小是:"+(cl.size()));
		System.out.println("cl是否含有mi"+cl.contains("mi"));
		cl.add("mi");
		System.out.println(cl);
		System.out.println("是否为空？"+cl.isEmpty());
		
		
		///     第二组方法		
		Collection<String> c2 = new ArrayList<String>();
		c2.add("mi");
		c2.add("do");
		c2.add("co");
		
		
		
		//目的在于检测那个图的正确性。
		//求cl 和c2的交集
		cl.retainAll(c2);
		System.out.println(cl);
		
		cl.addAll(c2);
		System.out.println(cl);
		
		//删除所有在c2中的元素
		cl.removeAll(c2);
		System.out.println(cl);
		
		System.out.println("cl是否包含c2"+cl.containsAll(c2));
		cl.addAll(c2);
		System.out.println("cl是否包含c2"+cl.containsAll(c2));
		
		//验证linkedlist 也是可以添加其中！本质上有着相似之处
		Collection<String> c3 = new LinkedList<String>();
		c3.add("suo");
		c3.add("lei");
		c3.add("xi");
		
		
		//求cl 和c2的交集
		cl.retainAll(c3);
		System.out.println(cl);
		
		cl.addAll(c3);
		System.out.println(cl);
		
		//删除所有在c2中的元素
		cl.removeAll(c3);
		System.out.println(cl);
		
		System.out.println("cl是否包含c3"+cl.containsAll(c3));
		cl.addAll(c3);
		System.out.println("cl是否包含c3"+cl.containsAll(c3));
		
		iteratorTest(cl);
		
		//添加自定义类
		System.out.println("添加自定义类");
		Collection<Person1> c4 = new HashSet<Person1>();
		Person1 p1 = new Person1("zhangsan ",30);
		Person1 p2 = new Person1("lisi",31);
		c4.add(p1);
		c4.add(p2);
		System.out.println(c4);
	}
	/**
	 *  为什么我们需要Iterator  她有什么好处和缺点？
	 * @param c  接口！
	 */
	public static void iteratorTest(Collection c)
	{
		Iterator it = c.iterator();
		while(it.hasNext()) //这边有一个小地方需要注意
			        //只有先判断是否it的previous pointer存在，才能继续！
		{
			Object o = it.next();
			it.remove();
			System.out.println(o);
			break;
		}
		//之所以不能并发 add可能是代价太高！ 而remove则是代价小一些
		System.out.println("remove之后的Collections："+c);
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
		//按照年龄进行排序
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
		//先按照年龄排序
		//降序
		//int temp = p1.Age - this.Age;
		//升序
		int temp = this.Age - p1.Age ;
		System.out.println("到了temp之后");
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
				System.out.println("到了这边了");
				return this.Name.compareTo(p1.Name);
			}
		}
		//return 0 ; 造成的大困难
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
		//按照年龄进行排序
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
		//先按照年龄排序
		//降序
		//int temp = p1.Age - this.Age;
		//升序
		int temp = p1.Age - p2.Age ;
		System.out.println("到了temp之后");
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
				System.out.println("到了这边了");
				return compare(p1.Name, p2.Name);
			}
		}
		//return 0 ; 造成的大困难
		return temp;
	}
	
}