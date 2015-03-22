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
 * @time      2015年3月22日下午4:30:42
 * @version   TestNetworkRewriteTestClient2 V1.0
 * 功能： 
                步骤： 
                1: Socket
                2: dis dos
 * 注意： 少了ServerSocket  多了new Socket("127.0.0.1",5566);
 * 掌握：
                思考：
 * 回顾：
 */
public class RewriteTestClient2
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	Socket s = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	BufferedReader br = null;
	String wordsFromClient  =null;
	String wordsFromServer  =null;
	
	public RewriteTestClient2()
	{
		createConnection();
		
			try
			{
				while(true)
				{
				wordsFromServer = dis.readUTF();
				System.out.println("服务器说:"+wordsFromServer);
				System.out.println("客户端，您想对服务器说什么？");
				wordsFromClient = br.readLine();
				dos.writeUTF(wordsFromClient);
				}
				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			s =  new Socket("127.0.0.1",5566);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(System.in));
		}catch(IOException e)
		{
			System.out.println("创建连接c出现问题");
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestClient2();
	}

}
