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
 * @time      2015��3��22������5:12:56
 * @version   TestNetworkRewriteTestClient3 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class RewriteTestClient31
{

	Socket s = null;
	DataInputStream dis = null;
    DataOutputStream dos = null;
	
	public RewriteTestClient31()
	{
		createConnection();
		ClientReRead31 sr = new ClientReRead31(dis);
		ClientReWrite31 sw = new ClientReWrite31(dos);
		sr.start();
		sw.start();	
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
			s= new Socket("127.0.0.1",5566);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			//��������������
			
		}catch(IOException e)
		{
			System.out.println("������������");
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestClient31();

	}

}
//��Ҫ�����ط� wordsFromServer  �ͷ������Ĳ�ͬ�ط�
class ClientReRead31 extends Thread
{
	private DataInputStream dis = null;
	private String wordsFromServer  = null;
	public ClientReRead31(DataInputStream dis)
	{
		this.dis = dis;
	}
	@Override
	public synchronized void  run()
	{
		// TODO Auto-generated method stub
		try
		{
			wordsFromServer = dis.readUTF();
			System.out.println("�������˶���˵��"+wordsFromServer);  
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
//��Ҫ�����ط� wordsFromClient  �ͷ������Ĳ�ͬ�ط�
class ClientReWrite31 extends Thread
{
	private DataOutputStream dos = null;
	private BufferedReader br = null;
	private String  wordsFromClient= null;
	public ClientReWrite31(DataOutputStream dos)
	{
		this.dos = dos;
	}
	@Override
	public synchronized void run()
	{
		// TODO Auto-generated method stub
		br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			try
			{
				wordsFromClient = br.readLine();
				dos.writeUTF(wordsFromClient);
				//Ҫ��Ҫbreak????? if(bye){break}
			}catch(IOException e)
			{
				
			}
		}
	}
	
}