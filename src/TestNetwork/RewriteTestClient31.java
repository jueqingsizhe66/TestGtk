/**
 * 解释：
 */
package TestNetwork;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author    叶昭良
 * @time      2015年3月22日下午5:12:56
 * @version   TestNetworkRewriteTestClient3 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class RewriteTestClient31
{

	Socket s = null;
	DataInputStream dis = null;
    DataOutputStream dos = null;
	
	public RewriteTestClient31()
	{
		createConnection();
		ClientReRead31 sr = new ClientReRead31(dis);
		ClientReWrite31 sw = new ClientReWrite31(dos);
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
			s= new Socket("127.0.0.1",5566);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			//增加了下面两句
			
		}catch(IOException e)
		{
			System.out.println("创建连接问题");
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestClient31();

	}

}
//主要更换地方 wordsFromServer  和服务器的不同地方
class ClientReRead31 extends Thread
{
	private DataInputStream dis = null;
	private String wordsFromServer  = null;
	public ClientReRead31(DataInputStream dis)
	{
		this.dis = dis;
	}
	@Override
	public synchronized void  run()
	{
		// TODO Auto-generated method stub
		try
		{
			wordsFromServer = dis.readUTF();
			System.out.println("服务器端对你说："+wordsFromServer);  
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
//主要更换地方 wordsFromClient  和服务器的不同地方
class ClientReWrite31 extends Thread
{
	private DataOutputStream dos = null;
	private BufferedReader br = null;
	private String  wordsFromClient= null;
	public ClientReWrite31(DataOutputStream dos)
	{
		this.dos = dos;
	}
	@Override
	public synchronized void run()
	{
		// TODO Auto-generated method stub
		br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			try
			{
				wordsFromClient = br.readLine();
				dos.writeUTF(wordsFromClient);
				//要不要break????? if(bye){break}
			}catch(IOException e)
			{
				
			}
		}
	}
	
}