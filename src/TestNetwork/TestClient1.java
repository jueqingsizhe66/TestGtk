/**
 * ���ͣ�
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    Ҷ����
 * @time      2015��3��19������2:30:23
 * @version   TestNetworkTestClient1 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestClient1
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
		Socket s1 =null;
		OutputStream os = null;
		DataOutputStream dos = null;
		try
		{
			 s1 = new Socket("127.0.0.1",5566);
			 os = s1.getOutputStream();
			 dos =  new DataOutputStream(os);
			dos.writeUTF("I am coming ,Sir");
			dos.flush();
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try
			{
				dos.close();
				os.close();
				s1.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}

}