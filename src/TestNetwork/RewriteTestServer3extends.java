/**
 * 解释：
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
 * @author    叶昭良
 * @time      2015年3月22日下午5:57:16
 * @version   TestNetworkRewriteTestServer3extends V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class RewriteTestServer3extends
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
		// TODO Auto-generated method stub
        ServerSocket ss = null;
        Socket s = null;
        DataOutputStream dos  = null;
        DataInputStream dis = null;
		try
		{
			ss = new ServerSocket(8888);
			s = ss.accept();
			dos = new DataOutputStream(s.getOutputStream());

			dis  = new DataInputStream(s.getInputStream());
		       

	        new Thread(new ServerReadImp(dis)).start();
	        new Thread(new ServerWriteImp(dos)).start();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
class ServerReadImp implements Runnable
{
    private DataInputStream dis = null;
    public ServerReadImp(DataInputStream dis)
    {
        this.dis = dis;
    }

    public void run()
    {
        while(true)
        {
           try{
                String str = dis.readUTF();
                System.out.println("客户端说:"+str);
                if(str.equalsIgnoreCase("byebye"))
                    break;
           }catch(Exception e)
           {
               e.printStackTrace();
           }
        }
    }
}
class ServerWriteImp implements Runnable
{
    private DataOutputStream dos = null;
    public ServerWriteImp(DataOutputStream dos)
    {
        this.dos = dos;
    }

    public void run()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
           try{
                String str = br.readLine();
                dos.writeUTF(str);
                if(str.equalsIgnoreCase("byebye"))
                    break;
           }catch(Exception e)
           {
               e.printStackTrace();
           }
        }
    }
}