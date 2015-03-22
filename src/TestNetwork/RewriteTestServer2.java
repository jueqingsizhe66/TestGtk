/**
 * 解释：
 */
package TestNetwork;

/**
 * @author    叶昭良
 * @time      2015年3月22日下午4:08:24
 * @version   TestNetworkRewriteTestServer2 V1.0
 * 功能： 
 *  	    在第一版本服务器的基础上  修正了客户端的只发送 不接受
 *         以及服务器端的只接受不发送
 *         并用BufferedReader获取System.in的内容输送到客户端
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾： 回顾了第一版本的ServerSocket   ss.accept()  以及DataInputStream(s.getInputStream());
 */
import java.io.*;
import java.net.*;
public class RewriteTestServer2
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
	BufferedReader br = null;
	String wordsFromClient  =null;
	String wordsFromServer  =null;
	public RewriteTestServer2()
	{
		createConnection();
		String ip = s.getInetAddress().getHostAddress();

		
			try
			{
				while(true)
				{
				wordsFromClient = dis.readUTF();
				System.out.println("客户端IP:"+ip+" said:"+wordsFromClient);
				if(wordsFromClient.equalsIgnoreCase("bye*"))
				{
					break;
				}
				System.out.println("服务器，请您输入你想对客户端说什么：");
				wordsFromServer = br.readLine();
				dos.writeUTF(wordsFromServer);
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
			ss = new ServerSocket(5566);
			s =  ss.accept();
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			//增加了下面两句
			dos.writeUTF("您好！我是服务器想要聊些什么:");
			dos.flush();
			br = new BufferedReader(new InputStreamReader(System.in));
		}catch(IOException e)
		{
			System.out.println("创建连接问题");
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestServer2();
	}

}
