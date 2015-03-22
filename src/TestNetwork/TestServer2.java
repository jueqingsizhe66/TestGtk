/**
 * 解释：
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    叶昭良
 * @time      2015年3月19日下午2:38:37
 * @version   TestNetworkTestServer2 V1.0
 * 功能：   在第一版本服务器的基础上  修正了客户端的只发送 不接受
 *         以及服务器端的只接受不发送
 *         并用BufferedReader获取System.in的内容输送到客户端
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestServer2
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
				System.out.println("客户端："+dis.readUTF());
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
