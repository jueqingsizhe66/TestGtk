/**
 * ���ͣ�
 */
package TestMultiThreads;

/**
 * @author    Ҷ����
 * @time      2015��3��18������9:09:27
 * @version   TestMultiThreadsTestPesonThread V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestPesonThread
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
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