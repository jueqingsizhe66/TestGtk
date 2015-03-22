/**
 * 解释：
 */
package TestNetwork;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author    叶昭良
 * @time      2015年3月22日下午5:57:44
 * @version   TestNetworkRewriteTestClient3extends V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class RewriteTestClient3extendsConn 
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
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
