/**
 *  1: 一个class类一般对应三种玩意  字段  方法 和类
 *        也就是字段反射 、方法反射  和类反射 另外还包括较特殊的Constructor构造函数
 *        的反射
 *        
 *        字段反射用 Field
 *        方法反射用Method
 *        构造函数对应 Constructor
 *        类反射  用 Class
 *        
 *        一般需要首先过得class反射才可以获得下面的三个反射！有着这重关系
 *        也就是一个类的反射通常包括字段 构造函数 方法 三个部分
 *  
 */
package com.reflect.test;

/**
 * @author    叶昭良
 * @time      2015年3月3日下午3:19:03
 * @version   com.reflect.testPerson V1.0
 */
public class Person
{
	//
	private String Name;
	private int Age;
	
	//无参构造函数
	public Person()
	{
		
	}
	//有参构造函数  对于构造函数  对应的反射为Constructor
	public Person(String Name, int Age)
	{
		this.Name = Name;
		this.Age = Age;
	}
	
	//普通方法
	public void sayHi()
	{
		System.out.println("我是"+this.Name+",我的年龄是"+this.Age+",很高兴认识你！");
	}
	
	public void singSong(String musicName)
	{
		if(musicName.equals("好汉歌"))
		{
			System.out.println("大河向东流，天上星星参北斗");
		}else
		{
			System.out.println("对不起，太难了不会唱！");
		}
	}
}
