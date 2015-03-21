/**
 * 解释：
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    叶昭良
 * @time      2015年3月21日上午1:39:22
 * @version   TestNetworkTestDatagramClient V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestDatagramClient
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
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		try
		{
			ds = new DatagramSocket();
			long m  = 1000l;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			
			dos.writeLong(m);
			
			byte[] apple = baos.toByteArray();
			dp = new DatagramPacket(apple, apple.length,new
					InetSocketAddress("127.0.0.1",5566));
			ds.send(dp);
		}catch(IOException e)
		{
			
		}
	}

}
