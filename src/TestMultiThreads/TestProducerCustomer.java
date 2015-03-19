/**
 * ���ͣ�
 */
package TestMultiThreads;

/**
 * @author    Ҷ����
 * @time      2015��3��18������9:54:48
 * @version   TestMultiThreadsTestProducerCustomer V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestProducerCustomer
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
		SynStack ss = new SynStack();
		Producer pro = new Producer(ss);
		Customer custom = new Customer(ss);
		
		//Thread tt = new Thread();
/*		pro.run();
		custom.run();*/
		
		Thread t1 = new Thread(pro);
		t1.start();
		Thread t2 = new Thread(custom);
		t2.start();
		
	}

}


class SynStack
{
	private char[] ss = new char[6];
	//private static int count = 0;
	private int count = 0;
	public synchronized void push(char c)
	{
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//if(count == ss.length)
		while(count == ss.length)
		{
			try
			{
				this.wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
/*		if(count == 3)
		{
			this.notify(); //����ﵽ3��ʱ��֪ͨ���ǽ�������
		}*/
		this.notify();

		System.out.printf("It generates %d product.It is %c\n",count,c);
		ss[count] = c;
		count++;
	}
	//synchronized ������ӣ��򱨴�
	public synchronized char pop()
	{
		//if(count == 0)
		while(count == 0)
		{
			try
			{
				this.wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(count == 3)
		{
			this.notify(); //����ﵽ3��ʱ��֪ͨ���ǽ�������
		}
		//this.notify();
		
		count--;
		//�������count--֮�� �ſ��Ե���ss[count]
		System.out.printf("It ate %d product.It is %c\n",count,ss[count]);
		return ss[count];
		
	}
}

 class Producer implements Runnable
{
	private SynStack ss = null;
	
	public Producer(SynStack ss)
	{
		this.ss =ss;
	}
	@Override
	public void run()	
	{
		// TODO Auto-generated method stub
//		ss.push('a');
/*		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		char ch ;
		for(int i =0 ; i < 20 ; i++)
		{
			ch = (char)('a'+i);
			ss.push(ch);
		}
	}
	
}
class Customer implements Runnable
{
	private SynStack ss = null;
	public Customer(SynStack ss)
	{
		this.ss = ss;
	}
	@Override
	public void run()
	{
		
		// TODO Auto-generated method stub
//		System.out.println(ss.pop());
/*		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		for(int i = 0; i < 20 ; i++)
		{
			System.out.println(ss.pop());
		}
	}
}