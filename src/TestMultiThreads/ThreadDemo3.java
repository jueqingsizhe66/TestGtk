package TestMultiThreads;
/*
 * ���߳���Դ��������   ���ܷ����İ�ȫ���⣺
 * 1, �̵߳������
 * 2, �߳�ִ�й����е��ӳ�
 * 
 * �̰߳�ȫ����Ľ����ͬ��
 * ͬ�������߳�ִ�ж��д����壨��Դ���������������У������ͷ�CPUִ��Ȩ�����߳�ִ�н��� ���ͷ�CPUִ��Ȩ
 * ͬ�������֣�
 * synchronized(obj){   obj ���������
 * 	ͬ���Ĵ����
 * }
 * 
 * ͬ����ԭ����
 * �����ƣ�ÿ���߳���ִ��ͬ��������ʱ�����Ȼ��ж� ͬ������ͬ������ʵ���� �Ƿ����߳�����ִ��ͬ��������һ����ʶ
 * 	������ʱ���Ѿ���һ���߳̽����ˣ�ͬ�������൱�ڰ�ͬ������� ������   ��ֹ�����̵߳Ľ���ִ��
 * 
 * ͬ���ĺô���
 * 	ͬ������˶��̲߳��������ݹ�����ȫ����
 * ͬ���ı׶ˣ�
 * 	��Ϊÿ���̴߳�����ִ�ж���Ҫ�ж� ͬ������Ч�ʻή��
 * 
 * ͬ����ǰ�᣺
 * 	1���Ƿ������������������ϵ��߳�
 * 	2��ͬ��ʹ�õ� �Ƿ���ͬһ����
 * 
 * ͬ����ʵ�Ƕ�һ������һ����װ
 * ����Ҳ�ǶԴ����һ����װ
 * 
 * ͬ������һ��������ʽ�� ͬ������
 * ͬ�����������֣�
 * public synchronized void funName(){
 * 	.....
 * }
 * ͬ������������
 * 	�Ǿ�̬ͬ��������ʹ�õ���  this ��
 * 	��̬ͬ��������ʹ�õ���  Class TicketWin2.class
 * 
 * ͬ���������ͬ��������
 * 	���������ϣ�� һ�������еľֲ���������߳�ͬ����ʹ��ͬ�������
 * ͬ�������  ʹ�õ��� ������ ���󣬵��Ǳ��뱣֤ �Ƕ�һ����
 * ͬ������ʹ�õ���   �� this ��ǰ��.class
 */



class TicketWin2 implements Runnable{

	private static int num = 100;
	Object obj = new Object();

	public void run()
	{ // num = 1

		while (true) 
		{ // cpu ִ��Ȩ ����һ���߳� ������
//			//synchronized (obj) { //
//				sellTicket();
//			//}
			//synchronized (TicketWin2.class) {
			synchronized ("aaa") {
				if (num > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "������"
							+ num--);
				}
			}
		}
	}
	
	/*
	 * ��Ʊ
	 */
	public static synchronized void sellTicket()
	{
		if (num > 0) 
		{
			try 
			{
				Thread.sleep(100);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "������"
					+ num--);
		}
	}
		
	
}

public class ThreadDemo3 {

	public static void main(String[] args) {

		TicketWin2 win = new TicketWin2(); 
		Thread win1 = new Thread(win);
		Thread win2 = new Thread(win);
		Thread win3 = new Thread(win);
		Thread win4 = new Thread(win);
		
		win1.start();
		win2.start();
		win3.start();
		win4.start();
		
	}

}