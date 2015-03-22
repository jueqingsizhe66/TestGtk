/**
 * 解释：
 */
package TestNetwork;

/**
 * @author    叶昭良
 * @time      2015年3月22日下午4:49:25
 * @version   TestNetworkRewriteTestServer3 V1.0
 * 功能：  
 * 	1: 把读和写的过程 从while中脱离出来 变成线程的形式
 *      即读线程和写线程
 *  2：把BufferedReader放入到写出线程中 ，因为只有在那边读取控制台的输入字符
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
import java.io.*;
import java.net.*;
public class RewriteTestServer31
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	ServerSocket ss = null;
	Socket s = null;
	 DataInputStream dis = null;
	 DataOutputStream dos = null;
	 
	public RewriteTestServer31()
	{
		createConnection();
		ServerReRead21 sr= new ServerReRead21(dis);
		ServerReWrite21 sw = new ServerReWrite21(dos);
		sr.start();
		sw.start();
	}
	
	/**
	 * 功能： 创建一个读的连接，用于读取客户端的socket数据
	 *       创建一个写的连接，通过BufferedReader进行缓冲读取System.in的数据
	 *       并通过DataOutputStream发送出去
	 *       思考：        
	 *       步骤：
	 */
	private void createConnection()
	{
		try
		{
			ss = new ServerSocket(5566);
			s =  ss.accept();
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			//增加了下面两句
			dos.writeUTF("您好！我是服务器想要聊些什么:");
			dos.flush();
		}catch(IOException e)
		{
			System.out.println("创建连接问题");
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestServer31();
	}

}

class ServerReRead21 extends Thread
{
	private DataInputStream dis = null;
	private String wordsFromClient = null;
	public ServerReRead21(DataInputStream dis)
	{
		this.dis = dis;
	}
	@Override
	public synchronized void run()
	{
		// TODO Auto-generated method stub
		try
		{
			wordsFromClient = dis.readUTF();
			System.out.println("客户端对你说："+wordsFromClient);  
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}

class ServerReWrite21 extends Thread
{
	private DataOutputStream dos = null;
	private BufferedReader br = null;
	private String wordsFromServer = null;
	public ServerReWrite21(DataOutputStream dos)
	{
		this.dos = dos;
	}
	
	@Override
	public synchronized void run()
	{
		System.out.println("dos="+dos);
		// TODO Auto-generated method stub
		br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			try
			{
				wordsFromServer = br.readLine();
				dos.writeUTF(wordsFromServer);
				//要不要break????? if(bye){break}
			}catch(IOException e)
			{
				
			}
		}
	}
	
}
