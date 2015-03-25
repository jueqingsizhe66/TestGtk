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
public class RewriteTestClient3
{

	Socket s = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	
	public RewriteTestClient3()
	{
		createConnection();
		ClientReRead3 sr = new ClientReRead3(dis);
		ClientReWrite3 sw = new ClientReWrite3(dos);
		Thread t1 = new Thread(sr);
		Thread t2 = new Thread(sw);
		t1.start();
		t2.start();
		
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
		new RewriteTestClient3();
	}

}
//��Ҫ�����ط� wordsFromServer  �ͷ������Ĳ�ͬ�ط�
class ClientReRead3 implements Runnable
{
	private DataInputStream dis = null;
	private String wordsFromServer  = null;
	public ClientReRead3(DataInputStream dis)
	{
		this.dis = dis;
	}
	@Override
	public void run()
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
class ClientReWrite3 implements Runnable
{
	private DataOutputStream dos = null;
	private BufferedReader br = null;
	private String  wordsFromClient= null;
	public ClientReWrite3(DataOutputStream dos)
	{
		this.dos = dos;
	}
	@Override
	public void run()
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