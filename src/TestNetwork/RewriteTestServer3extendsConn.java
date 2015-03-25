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
 * @time      2015��3��22������5:57:16
 * @version   TestNetworkRewriteTestServer3extends V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class RewriteTestServer3extendsConn extends Thread
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
	 */
    private static ServerSocket ss = null;
    private static Socket s = null;
    private static DataOutputStream dos  = null;
    private static DataInputStream dis = null;
    
	private static void createConnection()
	{
		try
		{
			ss = new ServerSocket(8888);
			s = ss.accept();
			dos = new DataOutputStream(s.getOutputStream());

			dis  = new DataInputStream(s.getInputStream());
		       

		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{


		new RewriteTestServer3extendsConn();

	}

}