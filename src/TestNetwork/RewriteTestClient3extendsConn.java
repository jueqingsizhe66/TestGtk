/**
 * ���ͣ�
 */
package TestNetwork;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author    Ҷ����
 * @time      2015��3��22������5:57:44
 * @version   TestNetworkRewriteTestClient3extends V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class RewriteTestClient3extendsConn 
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
	 */
	private static Socket s  = null;
	private static DataOutputStream dos = null;
	private static DataInputStream dis =  null;
	
	public static void createConn()
	{
		try
		{
			s = new Socket("127.0.0.1",8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
		}catch(IOException e)
		{
			
		}
		
	}
	
	public static void main(String[] args) 
	{
		new RewriteTestClient3extendsConn();
	}

}