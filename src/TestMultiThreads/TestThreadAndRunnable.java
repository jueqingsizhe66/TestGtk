/**
 * 
 */
package TestMultiThreads;

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
		Cat cat1 = new Cat();
		cat1.start();
		
		Dog dog1 = new Dog();
		Thread t1 = new Thread(dog1);
		t1.start();
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
		
