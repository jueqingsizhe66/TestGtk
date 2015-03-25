/**
 * ���ͣ�
 */
package TestNetwork;

/**
 * @author    Ҷ����
 * @time      2015��3��22������4:08:24
 * @version   TestNetworkRewriteTestServer2 V1.0
 * ���ܣ� 
 *  	    �ڵ�һ�汾�������Ļ�����  �����˿ͻ��˵�ֻ���� ������
 *         �Լ��������˵�ֻ���ܲ�����
 *         ����BufferedReader��ȡSystem.in���������͵��ͻ���
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ� �ع��˵�һ�汾��ServerSocket   ss.accept()  �Լ�DataInputStream(s.getInputStream());
 */
import java.io.*;
import java.net.*;
public class RewriteTestServer2
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
	BufferedReader br = null;
	String wordsFromClient  =null;
	String wordsFromServer  =null;
	public RewriteTestServer2()
	{
		createConnection();
		String ip = s.getInetAddress().getHostAddress();

		
			try
			{
				while(true)
				{
				wordsFromClient = dis.readUTF();
				System.out.println("�ͻ���IP:"+ip+" said:"+wordsFromClient);
				if(wordsFromClient.equalsIgnoreCase("bye*"))
				{
					break;
				}
				System.out.println("��������������������Կͻ���˵ʲô��");
				wordsFromServer = br.readLine();
				dos.writeUTF(wordsFromServer);
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
			ss = new ServerSocket(5566);
			s =  ss.accept();
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			//��������������
			dos.writeUTF("���ã����Ƿ�������Ҫ��Щʲô:");
			dos.flush();
			br = new BufferedReader(new InputStreamReader(System.in));
		}catch(IOException e)
		{
			System.out.println("������������");
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestServer2();
	}

}