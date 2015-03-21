/**
 * 解释：
 */
package TestNetwork;

/**
 * @author    叶昭良
 * @time      2015年3月21日下午12:43:24
 * @version   TestNetworkTestServer3 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
import java.io.*;
import java.net.*;
public class TestServer3  //throws Exception
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
		       
	        new ServerRead(dis).start();
	        new ServerWrite(dos).start();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}


class ServerRead extends Thread
{
    private DataInputStream dis = null;
    public ServerRead(DataInputStream dis)
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
class ServerWrite extends Thread
{
    private DataOutputStream dos = null;
    public ServerWrite(DataOutputStream dos)
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