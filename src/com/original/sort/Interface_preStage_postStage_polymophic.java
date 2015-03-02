/**
 *  通过接口演示多态！ 并解释前期绑定和后期绑定
 */
package com.original.sort;

/**
 * @author    叶昭良
 * @time      2015年2月27日下午12:29:26
 * @version   com.original.sortInterface_preStage_postStage_polymophic V1.0
 */
public class Interface_preStage_postStage_polymophic
{
	
	 //售车收入
	 private int money = 0;
	 //卖出一部车
	 //通过car接口  然后选择不同的车型
	 public void sellCar(car car1){
		 System.out.println("车型："+car1.getName()+"单价"+car1.getPrice());
		 //增加卖出车售价的收入
		 money += car1.getPrice();
	 }
	 
	 public int getMoney()
	 {
		 return money;
	 }
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Interface_preStage_postStage_polymophic ippp = new Interface_preStage_postStage_polymophic();
//		System.out.println("BNW:"+ippp.sellCar(new BNW()));
		//原因在于BNW这个类在sellCar的时候已经打印出来！ 不能重复打印
		//销售一辆车
		ippp.sellCar(new BNW());
		//销售另外一辆车 cherryQQ
		ippp.sellCar(new CheryQQ());
		//销售另外一辆车 Santana
		ippp.sellCar(new Santana());
		
		//打印三辆车的总收入
		System.out.println("销售了三辆车的总收入"+ippp.getMoney());
		
	}

}

interface car
{
	String getName();
	int getPrice();
}

//宝马

class BNW implements car
{
	public String getName()
	{
		return "BNW";
	}
	
	public int getPrice()
	{
		return 300000;
	}
}


//cherry QQ 

class CheryQQ implements car
{
	public String getName()
	{
		return "cheryQQ";
		
	}
	public int getPrice()
	{
		return 32000;
	}
}


//如果你现在还想再增加一个车型的话  只要在这边再写一个car的接口函数就好    至于carshop里面的主函数不需要
//进行任何的修改     这样就很好的在  carshop 类中的 sellcar函中实现了多态

/*
 * 运行结果：
 *  车型：BNW单价300000
	车型：cheryQQ单价32000
	总收入332000
	总结 这边的 sellcar的参数  类似于后期绑定  
	
	c++ 和java都有前期绑定  和后期绑定的说法  
	为什么要有这种说法 ？这种说法有什么的好处 ？我们什么时候用到它？
	前期绑定是在程序运行之前  由编译器和连接器进行绑定  又叫静态绑定  static方法和final方法 
	这里也是包括private方法，因为他是隐式final（也就是说final分显式和隐式两种）
	
	后期绑定：在运行时根据对象的类型进行绑定  有方法调用机制实现 也叫做动态绑定
	  这边需要注意的是 对象类型 和方法调用机制   也就是动态绑定是通过函数实现 也就是上面的 sellCar的参数
	  绑定也是一个动态绑定 ！ 根据不同的对象进行绑定
	  之所以产生前期绑定和后期绑定我觉得是因为多态的需要  以及static和final变量的需要！ 为了区分他们的加载
	  时间的不同 ！
	   怎么用它就是用多态  多态又需要继承和 接口的实现   所以你想出现这种后期绑定的现象也就是你的java文件中得是有
	    extends或者interface implements  这些关键‘
	    
	    也就是说多态就是在后期绑定的这种机制上实现

 * */

//桑塔纳

class Santana implements car{
	public String getName(){
		return "Santana";
	}
	
	public int getPrice(){
		return 50000;
	}
}


//这样就可以直接调用它

