/**
 * ���ͣ�
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    Ҷ����
 * @time      2015��3��21������12:47:03
 * @version   TestNetworkTestClient3 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestClient3
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

        new ClientRead(dis).start();
        new ClientWrite(dos).start();
	}

}

class ClientRead extends Thread
{
    private DataInputStream dis = null;
    public ClientRead(DataInputStream dis)
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
class ClientWrite extends Thread
{
    private DataOutputStream dos = null;
    public ClientWrite(DataOutputStream dos)
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