/**
 * 解释：
 */
package TestNetwork;

/**
 * @author    叶昭良
 * @time      2015年3月22日下午3:54:39
 * @version   TestNetworkRewriteTestClient1 V1.0
 * 功能： 
                步骤：  
                1: Socket的建立
                2： 发送数据
                3：。。
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
import java.io.*;
import java.net.*;
public class RewriteTestClient1
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	Socket s = null;
	DataOutputStream dos = null;
	public RewriteTestClient1()
	{
		try
		{
			s = new Socket("127.0.0.1",5566);
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("Hello");
			dos.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new RewriteTestClient1();
	}

}
