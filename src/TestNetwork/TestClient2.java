/**
 * 解释：
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    叶昭良
 * @time      2015年3月19日下午2:38:52
 * @version   TestNetworkTestClient2 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestClient2
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
		Socket s1 = null;
		OutputStream os = null;
		InputStream is = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		BufferedReader br = null;
		try
		{
			s1 = new Socket("127.0.0.1",5566);
			os = s1.getOutputStream();
			is = s1.getInputStream();
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
			
			dos.writeUTF("Come to the world of net server");
			dos.flush();
			
			br = new BufferedReader(new InputStreamReader(System.in));
			while(true)
			{
				String str = br.readLine();
				dos.writeUTF(str);
				if(str.equalsIgnoreCase("byebye"))
                    break;
                str = dis.readUTF();
                System.out.println("服务器 said that" + str+"\n");
				if(str.equalsIgnoreCase("byebye"))
                    break;

			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
