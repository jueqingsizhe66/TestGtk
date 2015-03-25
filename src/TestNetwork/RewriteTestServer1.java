/**
 * ���ͣ�
 */
package TestNetwork;

/**
 * @author    Ҷ����
 * @time      2015��3��22������3:49:11
 * @version   TestNetworkRewriteTestServer1 V1.0
 * ���ܣ� 
                ���裺
                1: Server�󶨶˿�
                2������
                3��ѭ������
 * ע�⣺  socket����Ҫ���þ���   getOutputStream  ��getInputStream��ȷ��
 *       ����socket�����ķ���
 * ���գ�
                ˼����
 * �عˣ�
 */
import java.io.*;
import java.net.*;
public class RewriteTestServer1
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
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
			System.out.println("�ͻ���"+ip+" said: "+dis.readUTF());
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