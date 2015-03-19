/**
 * 解释：
 */
package TestMultiThreads;

/**
 * @author    叶昭良
 * @time      2015年3月18日下午9:09:27
 * @version   TestMultiThreadsTestPesonThread V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestPesonThread
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		A aa = new A();
		Thread t1 = new Thread(aa);
		Thread t2 = new Thread(aa);
		Thread t3 = new Thread(aa);
		
/*		B bb = new B();
		Thread t1 = new Thread(bb);
		Thread t2 = new Thread(bb);
		Thread t3 = new Thread(bb);*/
		
		t1.start();
		t2.start();
		t3.start();
	}

}

class A implements Runnable
{
    private static int tickets = 0;
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while(true)
		{
			synchronized("aaa")
			{
				if(tickets < 10)
				{
					System.out.println(Thread.currentThread().getName()+tickets);
					tickets++;
				}else
				{
					break;
				}
			}

		}
	}
	
}


class B implements Runnable
{
    private static int tickets = 0;
	@Override
	public synchronized void run()
	{
		// TODO Auto-generated method stub
		while(true)
		{
			if(tickets < 10)
			{
				System.out.println(Thread.currentThread().getName()+tickets);
				tickets++;
			}else
			{
				break;
			}
		}
	}
	
}
