/**
 * ���ͣ�
 */
package TestNetwork;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author    Ҷ����
 * @time      2015��3��19������1:49:36
 * @version   TestNetworkTestDatagramSocket V1.0
 * ���ܣ�  ����UDP
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestDatagramSocket
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
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
			for(int i =0 ; i < 20; i++)
			{
				apple[i] = (byte) ((byte)'a'+i);
			}
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