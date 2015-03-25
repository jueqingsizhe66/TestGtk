/**
 * ���ͣ�
 */
package TestNetwork;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author    Ҷ����
 * @time      2015��3��22������4:30:42
 * @version   TestNetworkRewriteTestClient2 V1.0
 * ���ܣ� 
                ���裺 
                1: Socket
                2: dis dos
 * ע�⣺ ����ServerSocket  ����new Socket("127.0.0.1",5566);
 * ���գ�
                ˼����
 * �عˣ�
 */
public class RewriteTestClient2
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
	DataInputStream dis = null;
	DataOutputStream dos = null;
	BufferedReader br = null;
	String wordsFromClient  =null;
	String wordsFromServer  =null;
	
	public RewriteTestClient2()
	{
		createConnection();
		
			try
			{
				while(true)
				{
				wordsFromServer = dis.readUTF();
				System.out.println("������˵:"+wordsFromServer);
				System.out.println("�ͻ��ˣ�����Է�����˵ʲô��");
				wordsFromClient = br.readLine();
				dos.writeUTF(wordsFromClient);
				}
				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * ���ܣ� ����һ���������ӣ����ڶ�ȡ�ͻ��˵�socket����
	 *       ����һ��д�����ӣ�ͨ��BufferedReader���л����ȡSystem.in������
	 *       ��ͨ��DataOutputStream���ͳ�ȥ
	 *       ˼����        
	 *       ���裺
	 */
	private void createConnection()
	{
		try
		{
			s =  new Socket("127.0.0.1",5566);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(System.in));
		}catch(IOException e)
		{
			System.out.println("��������c��������");
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestClient2();
	}

}