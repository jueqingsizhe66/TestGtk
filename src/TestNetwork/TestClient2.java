/**
 * ���ͣ�
 */
package TestNetwork;
import java.io.*;
import java.net.*;
/**
 * @author    Ҷ����
 * @time      2015��3��19������2:38:52
 * @version   TestNetworkTestClient2 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestClient2
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
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
                System.out.println("The opposite said that" + str);


			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}