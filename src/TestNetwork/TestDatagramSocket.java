/**
 * 解释：
 */
package TestNetwork;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author    叶昭良
 * @time      2015年3月19日下午1:49:36
 * @version   TestNetworkTestDatagramSocket V1.0
 * 功能：  测试UDP
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestDatagramSocket
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
		DatagramSocket ds  = null;
		DatagramPacket dp = null;
		 
		try
		{
			ds = new DatagramSocket(5566);
			byte[] apple = new byte[1025];
			/*for(int i =0 ; i < 20; i++)
			{
				apple[i] = (byte) ((byte)'a'+i);
			}*/
			dp = new DatagramPacket(apple, apple.length);
			while(true)
			{
				try
				{
					ds.receive(dp);
					ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
					DataInputStream dis = new DataInputStream(bais);
					System.out.println(dis.readLong());
					
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
