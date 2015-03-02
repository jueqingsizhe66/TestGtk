/**
 *  这个是   新建了一个 new Comparator<T>(){}的实现，放在sort里面！！相当于
 *  匿名类的实现
 *  
 *  comparable的compareTo(object o1）
 *      Comparator的compare(object o1,object o2)
 *      
 *     comparators是你自己新建的一个比较器！
 *                     比较神器用于在Arrays.sort的第二个参数
 */
package com.collections.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author    叶昭良
 * @time      2015年2月24日下午5:18:55
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
		
		System.out.println("调用两外一种排序方式 定义新的treeSet");
		//也可以直接定义一种new Comparators() 让这个类实现Comparators即可
		//而如果不加上implement Comparators 
		//那么只要让其返回一个java.util.comparator即可！new一个comparator出来
		//这是两种方法的实现
		//然后写上compare函数就可以了！
//		TreeSet tsPig= new TreeSet(new Comparators().getComparator());
		//测试仪在 TestTreeSetComparator中给出
		
		System.out.println("开始另外一种排序");
		Arrays.sort(pdian, new Comparator<Object>()
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
				}else if(o1 instanceof PersonMan)
				{
					//布尔类型的比较
					return compare((PersonMan) o1,(PersonMan) o2);
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
//							return (val1 > val2 ? 1 :(val1 == val2 ? 0 : -1));
				//升序
				//return (val2 > val1 ? -1 :(val1 == val2 ? 0 : 1));
				//降序
				return (val2 > val1 ? -1 :(val1 == val2 ? 0 : 1));
			}
			//用于比较布尔值
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
类似方法
 * @author    叶昭良
 * @time      2015年2月24日下午5:59:02
 * @version   com.collections.testPersonMan V1.0
 */
//改用了Comparable接口
class PersonMan implements Comparable<Object>
{
	private String Name;
	private int Age;
	private Boolean Sex;
	

	@Override
	public String toString()
	{
		return "PersonPig [Name=" + this.Name + ", Age=" + this.Age + ",Sex="+(this.Sex?"男":"女")+"]";
	}


	public PersonMan(String name, int age,Boolean Sex)
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
	public int compareTo(Object o1)
	{
		//针对自己的内部的比较器！ 不具有通用性
		PersonMan ptemp	= (PersonMan)o1;
		//先排序年龄
		int num = new Integer(this.Age).compareTo(ptemp.getAge());
		System.out.println("内部的Num"+num);
		//在排序名字
		return num==0?this.Name.compareTo(ptemp.getName()):num;
	}
	
}

