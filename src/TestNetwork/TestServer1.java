/**
 * ���ͣ�
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    Ҷ����
 * @time      2015��3��19������2:30:08
 * @version   TestNetworkTestServer1 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestServer1
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