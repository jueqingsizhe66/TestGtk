/**
 * ���ͣ�
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    Ҷ����
 * @time      2015��3��21������1:39:22
 * @version   TestNetworkTestDatagramClient V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestDatagramClient
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