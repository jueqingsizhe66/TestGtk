/**
 * 解释：
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    叶昭良
 * @time      2015年3月19日下午2:30:08
 * @version   TestNetworkTestServer1 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestServer1
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
		try
		{
			ServerSocket ss = new ServerSocket(5566);
			while(true)
			{
				Socket s1 =ss.accept();
				DataInputStream dis = new DataInputStream(s1.getInputStream());
				System.out.println(dis.readUTF());
				dis.close();
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
