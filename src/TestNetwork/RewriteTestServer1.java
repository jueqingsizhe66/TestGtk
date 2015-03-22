/**
 * 解释：
 */
package TestNetwork;

/**
 * @author    叶昭良
 * @time      2015年3月22日下午3:49:11
 * @version   TestNetworkRewriteTestServer1 V1.0
 * 功能： 
                步骤：
                1: Server绑定端口
                2：接受
                3：循环接收
 * 注意：  socket的主要作用就是   getOutputStream  和getInputStream来确定
 *       表明socket交流的方向
 * 掌握：
                思考：
 * 回顾：
 */
import java.io.*;
import java.net.*;
public class RewriteTestServer1
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
	public RewriteTestServer1()
	{
		try
		{
			ss = new ServerSocket(5566);
			s = ss.accept();
			String ip = s.getInetAddress().getHostAddress();
			dis = new DataInputStream(s.getInputStream());
			System.out.println("客户端"+ip+" said: "+dis.readUTF());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try
			{
				dis.close();
				s.close();
			}catch(IOException e)
			{
				
			}		
		}
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestServer1();
	}

}
