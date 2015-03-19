/**
 * ���ͣ�
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    Ҷ����
 * @time      2015��3��19������2:38:37
 * @version   TestNetworkTestServer2 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestServer2
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
		Socket s1 = null ;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		BufferedReader br = null;
		try
		{
			ServerSocket ss = new ServerSocket(5566);
			s1 = ss.accept();
			
			dis = new DataInputStream(s1.getInputStream());
			dos = new DataOutputStream(s1.getOutputStream());
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while(true)
			{
				String apple = dis.readUTF();
				System.out.println("�Է���"+dis.readUTF());
				if(apple.equals("bye"))
				{
					break;
				}
				apple = br.readLine();
				dos.writeUTF(apple);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try
			{
				dos.close();
				dis.close();
				s1.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}