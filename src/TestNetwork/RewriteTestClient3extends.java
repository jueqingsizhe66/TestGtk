/**
 * ���ͣ�
 */
package TestNetwork;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class RewriteTestClient3extends 
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
	 */

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
        Socket s = new Socket("127.0.0.1",8888);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        DataInputStream dis = new DataInputStream(s.getInputStream());

        new Thread(new ClientReadImp(dis)).start();
        new Thread(new ClientWriteImp(dos)).start();

	}

}
class ClientReadImp implements Runnable
{
    private DataInputStream dis = null;
    public ClientReadImp(DataInputStream dis)
    {
        this.dis = dis;
    }

    public void run()
    {
        while(true)
        {
           try{
                String str = dis.readUTF();
                System.out.println("������˵:"+str);
                if(str.equalsIgnoreCase("byebye"))
                    break;
           }catch(Exception e)
           {
               e.printStackTrace();
           }
        }
    }
}
class ClientWriteImp implements Runnable
{
    private DataOutputStream dos = null;
    public ClientWriteImp(DataOutputStream dos)
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