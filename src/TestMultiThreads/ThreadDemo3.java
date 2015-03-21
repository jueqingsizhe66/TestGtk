package TestMultiThreads;
/*
 * 多线程资源共享操作   可能发生的安全问题：
 * 1, 线程的随机性
 * 2, 线程执行过程中的延迟
 * 
 * 线程安全问题的解决：同步
 * 同步：在线程执行多行代码体（资源共享操作）过程中，不会释放CPU执行权，该线程执行结束 才释放CPU执行权
 * 同步的体现：
 * synchronized(obj){   obj 是任意对象
 * 	同步的代码块
 * }
 * 
 * 同步的原理：
 * 锁机制：每个线程在执行同步代码块的时候，首先会判断 同步锁，同步锁其实就是 是否有线程正在执行同步代码块的一个标识
 * 	如果这个时候，已经有一个线程进来了，同步锁就相当于把同步代码块 锁上了   阻止其他线程的进入执行
 * 
 * 同步的好处：
 * 	同步解决了多线程操作的数据共享安全问题
 * 同步的弊端：
 * 	因为每次线程代码体执行都需要判断 同步锁，效率会降低
 * 
 * 同步的前提：
 * 	1，是否有两个或者两个以上的线程
 * 	2，同步使用的 是否是同一个锁
 * 
 * 同步其实是对一块代码的一个封装
 * 函数也是对代码的一个封装
 * 
 * 同步的另一中体现形式： 同步函数
 * 同步函数的体现：
 * public synchronized void funName(){
 * 	.....
 * }
 * 同步函数的锁：
 * 	非静态同步函数：使用的是  this 锁
 * 	静态同步函数：使用的是  Class TicketWin2.class
 * 
 * 同步代码块与同步函数：
 * 	如果仅仅是希望 一个方法中的局部代码进行线程同步，使用同步代码块
 * 同步代码块  使用的锁 是任意 对象，但是必须保证 是对一个锁
 * 同步函数使用的锁   是 this 当前类.class
 */



class TicketWin2 implements Runnable{

	private static int num = 100;
	Object obj = new Object();

	public void run()
	{ // num = 1

		while (true) 
		{ // cpu 执行权 被另一个线程 抢走了
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
					System.out.println(Thread.currentThread().getName() + "正在售"
							+ num--);
				}
			}
		}
	}
	
	/*
	 * 售票
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
			System.out.println(Thread.currentThread().getName() + "正在售"
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