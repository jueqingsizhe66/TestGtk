/**
 * ���ͣ�
 */
package TestNetwork;

/**
 * @author    Ҷ����
 * @time      2015��3��22������3:54:39
 * @version   TestNetworkRewriteTestClient1 V1.0
 * ���ܣ� 
                ���裺  
                1: Socket�Ľ���
                2�� ��������
                3������
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
import java.io.*;
import java.net.*;
public class RewriteTestClient1
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
	 */
	Socket s = null;
	DataOutputStream dos = null;
	public RewriteTestClient1()
	{
		try
		{
			s = new Socket("127.0.0.1",5566);
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("Hello");
			dos.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestClient1();
	}

}