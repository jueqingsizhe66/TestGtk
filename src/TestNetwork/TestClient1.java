/**
 * 解释：
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    叶昭良
 * @time      2015年3月19日下午2:30:23
 * @version   TestNetworkTestClient1 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestClient1
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Socket s1 =null;
		OutputStream os = null;
		DataOutputStream dos = null;
		try
		{
			 s1 = new Socket("127.0.0.1",5566);
			 os = s1.getOutputStream();
			 dos =  new DataOutputStream(os);
			dos.writeUTF("I am coming ,Sir");
			dos.flush();
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try
			{
				dos.close();
				os.close();
				s1.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}

}
