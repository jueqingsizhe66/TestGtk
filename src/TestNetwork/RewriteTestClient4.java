/**
 * 解释：
 */
package TestNetwork;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;



/**
 * @author    叶昭良
 * @time      2015年3月22日下午10:08:37
 * @version   TestNetworkRewriteTestClient4 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class RewriteTestClient4
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
		Socket s  = null;
		 try
		{
			s = new Socket("127.0.0.1",5599);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 new TCPClient1(s).launch();
	}

}

class TCPClient1
{
        // class variables
        // class variables
        // connect
         private Socket s = null;
         // data flow
         private DataOutputStream dos = null;
         private DataInputStream dis = null;
         // UI
         private Frame f = null;
         private TextArea ta = null;
         private TextField tf = null;
         private Button  bn = null;
         public TCPClient1(Socket s)
         {
        	 this.s = s;
         }
       
         public void launch()
         {
             createUI();
             connect();
             new ClientRead(dis);
             new ClientWrite(dos);
         }
        // construct member
        /*
        public TCPClient()
        {

        }
        */
        public void createUI()
        {
            Frame f = new Frame("客户端");
            ta = new TextArea();
            tf = new TextField();
            Panel p = new Panel(new BorderLayout());
            bn = new Button("发送");
            p.add(tf,BorderLayout.CENTER);
            p.add(bn,BorderLayout.EAST);

            f.add(ta,BorderLayout.CENTER);
            f.add(p,BorderLayout.SOUTH);

            f.setSize(400,200);
            f.setVisible(true);
            //f.setVisable(true);
	        tf.addActionListener(new TCPClientListener());
	        bn.addActionListener(new TCPClientListener());
            f.addWindowListener(new WindowAdapter()
                    {
                        public void windowClosing(WindowEvent e)
                        {
                            System.exit(0);
                        }
                    });
        }
        public void connect()
        {
            try{
                
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
   	        
            }catch(Exception e)
            {
            	System.out.println("客户端异常");
                //System.exit(0);
            }
        }
        public void close()
        {
            try{
                dis.close();
                dos.close();
                s.close();
            }catch(Exception e)
            {
                System.exit(0);
            }
        }
        class ClientRead extends Thread
        {
            private DataInputStream dis = null;
            public ClientRead(DataInputStream dis)
            {
                this.dis = dis;
                this.start();
            }

            public void run()
            {
                while(true)
                {
                   try{
                        String str = dis.readUTF();
                        ta.append("服务器说:"+str+"\n");
                        if(str.equalsIgnoreCase("再见"))
                        {
                            close();
                            System.exit(0);
                        }
                        System.out.println("服务器说:"+str);
                        if(str.equalsIgnoreCase("byebye"))
                            break;
                   }catch(Exception e)
                   {
       	               JOptionPane.showMessageDialog(f,"客户端已离开");
       	               return;
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
                this.start();
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

	class TCPClientListener implements ActionListener
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	           try{
	                String str = tf.getText();
	                tf.setText("");
	                ta.append("客户端说:"+str+"\n");
	                dos.writeUTF(str);
	                if(str.equalsIgnoreCase("再见"))
	                {
	                    close();
	                    System.exit(0);
	                }
	           }catch(Exception e1)
	           {
	              // e.printStackTrace();
	              System.exit(0);
	           } 
	    }
	}
}
