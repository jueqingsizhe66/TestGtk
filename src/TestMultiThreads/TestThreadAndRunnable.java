/**
 * 
 */
package TestMultiThreads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * @author    叶昭良
 * @time      2015年3月5日下午11:34:50
 * @version   TestMultiThreadsTestThreadAndRunnable V1.0
 */
public class TestThreadAndRunnable
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
/*		
		Pipe pig = new Pipe();
		pig.run();

		Cat cat1 = new Cat();
		cat1.start();
		
	
		Dog dog1 = new Dog();
		Thread t1 = new Thread(dog1);
		t1.start();
*/
		Calendar cl = Calendar.getInstance();
		System.out.println(cl);
		System.out.println(cl.getTimeInMillis());
		System.out.println(cl.get(Calendar.WEDNESDAY));
		System.out.println(cl.get(Calendar.DAY_OF_MONTH));
		
		Date  da  = new Date();
		System.out.println(da);
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-DD");
		String pit = sdf1.format(da);
		System.out.println("经过转变之后的值"+pit);
		
		Date  ba = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM");
		String pit2 = sdf2.format(ba);
		System.out.println("经过转变之后的值"+pit2);
		System.out.println(pit2);
		
	}

}

class Cat extends Thread
{
	static int count = 0;

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while(count <=5)
		{
			//1000ms 休眠
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("我是猫的线程：喵喵");
			count++;
		}
	}
	
}

class Dog implements Runnable
{
	static int count1 = 0;
	@Override
	public void run()
	{
		
		// TODO Auto-generated method stub
		while(count1 <=5)
		{
			//1000ms 休眠
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("我是狗的线程：wangwang");
			count1++;
		}
	}
	
}
	


class Pipe implements Runnable
{
	static int  count = 0;
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 10 ; i++)
		{
			System.out.println("wi"+Thread.currentThread().getName()+" "+count);
			try
			{
				Thread.sleep(1000);
			}catch(Exception e)
			{
				throw new RuntimeException("hi"+count);
			}
			count++;
		}

	}
	
}
