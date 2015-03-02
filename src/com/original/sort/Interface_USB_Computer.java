/**
 *   通过接口的多态 来演示不同设备实现USB的多态
 *    不同的设备都可以被相同的电脑上使用！
 *    1 ： 首先定义一个 usb interface
 *    2 : 然后定义几个实现usb接口的设备类
 *    3 : 让电脑使用usb接口  定义usb变量
 *    4 ： 定义一个电脑对象，就可以使用该对象了！ 如果某台电脑上的设备驱动较难
 *    		加载也许就需要通过其他方法进行实现了。
 */
package com.original.sort;

/**
 * @author    叶昭良
 * @time      2015年2月27日下午1:22:53
 * @version   com.original.sortInterface_USB_Computer V1.0
 */
public class Interface_USB_Computer
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		computer c1 =  new computer();
		c1.Useusb(new camero());
		c1.Useusb(new phone());
		
	}

}


//接口类的 局部变量就是static变量  即全部变量    
//接口类中不能对方法进行实现  这是硬性规定  可以说是抽象的抽象  因为抽象类还是可以拥有方法的实现
//但是接口类中一定不能有函数的实现
//接口的实现一定是在对应的硬件的  并且实现所有的方法 否则会报错！
//接口由于它是抽象类所以一定不能进行实例化
//一个类中可以实现多个接口  也就是你再定义一个interface  用于phone都是可以的
//usb接口肯定是准备在来的人身上  而不是准备在服务的人身上
interface usb
{
	int a = 1; //利用usb.a可以访问到a的值 默认是static类型
	//开启usb服务
	public void startup();
	//关掉usb服务
	public void stop();
}

class camero implements usb
{
	public void startup()
	{
		System.out.println("我是camero  现在启动成功");
	}
	public void  stop()
	{
		System.out.println("我是cameron 现在已停止服务");
	}
}

class phone implements usb
{
	
	public void startup()
	{
		System.out.println("我是phone  现在启动成功");
	}
	public void  stop()
	{
		System.out.println("我是phone 现在已停止服务");
	}
}

//那手机  camero 都是得连接在电脑上  我现在应该是怎么设置电脑类呢？
//肯定是需要准备要连接的对象  其次就是usb接口  也就是两个参数是必须的、
//利用多态来解决不同的硬件类

class computer 
{
	public void Useusb(usb Usb)
	{
		Usb.startup();
		Usb.stop();
	}
}