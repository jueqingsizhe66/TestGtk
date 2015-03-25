/**
 * ���ͣ�
 */
package TestNetwork;

/**
 * @author    Ҷ����
 * @time      2015��3��22������4:49:25
 * @version   TestNetworkRewriteTestServer3 V1.0
 * ���ܣ�  
 * 	1: �Ѷ���д�Ĺ��� ��while��������� ����̵߳���ʽ
 *      �����̺߳�д�߳�
 *  2����BufferedReader���뵽д���߳��� ����Ϊֻ�����Ǳ߶�ȡ����̨�������ַ�
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
import java.io.*;
import java.net.*;
public class RewriteTestServer3
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
	DataOutputStream dos = null;
	
	public RewriteTestServer3()
	{
		createConnection();
		
		
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
			ss = new ServerSocket(5566);
			while(true)
			{
				s =  ss.accept();
				ServerReRead2 sr = new ServerReRead2(dis);
				ServerReWrite2 sw = new ServerReWrite2(dos);
				Thread t1 = new Thread(sr);
				Thread t2 = new Thread(sw);
				t1.start();
				t2.start();
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				//��������������
				dos.writeUTF("���ã����Ƿ�������Ҫ��Щʲô:");
				dos.flush();
			}
				

		}catch(IOException e)
		{
			System.out.println("������������");
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestServer3();
	}

}

class ServerReRead2 implements Runnable
{
	private DataInputStream dis = null;
	private String wordsFromClient = null;
	public ServerReRead2(DataInputStream dis)
	{
		this.dis = dis;
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		try
		{
			wordsFromClient = dis.readUTF();
			System.out.println("�ͻ��˶���˵��"+wordsFromClient);  
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}

class ServerReWrite2 implements Runnable
{
	private DataOutputStream dos = null;
	private BufferedReader br = null;
	private String wordsFromServer = null;
	public ServerReWrite2(DataOutputStream dos)
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
				wordsFromServer = br.readLine();
				dos.writeUTF(wordsFromServer);
				//Ҫ��Ҫbreak????? if(bye){break}
			}catch(IOException e)
			{
				
			}
		}
	}
	
}