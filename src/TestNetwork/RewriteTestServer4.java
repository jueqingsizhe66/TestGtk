/**
 * 解释：
 */
package TestNetwork;

/**
 * @author    叶昭良
 * @time      2015年3月22日下午10:08:18
 * @version   TestNetworkRewriteTestServer4 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
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
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;


public class RewriteTestServer4
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
	    ServerSocket ss = new ServerSocket(5599);
        
	    while(true)
	    {
	    	new TCPServer1(ss.accept()).launch();
	    }
	    
	}

}


class TCPServer1
{
        // class variables
        // class variables
        // connect
         
         private Socket s = null;
         // data flow
         private DataOutputStream dos = null;
         private DataInputStream dis = null;
         // UI
         private static Frame f = null;
         private static TextArea ta = null;
         private static TextField tf = null;
         private static Button  bn = null;
         static
         {
        	 createUI();
         }
         public  TCPServer1(Socket s)
         {
        	 this.s = s;
         }
       
         public void launch()
         {

             connect();

         }
        // construct member
        /*
        public TCPServer()
        {

        }
        */
        public static void createUI()
        {
            Frame f = new Frame("服务器");
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
/*            tf.addActionListener(new TCPServerListener());
            bn.addActionListener(new TCPServerListener());*/
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
        	
            try
            {
            	//while(true)
            	//{
            		//阻塞这边 等待客户端的连接
	                dis = new DataInputStream(s.getInputStream());
	                dos = new DataOutputStream(s.getOutputStream());
	                dos.writeUTF("欢迎你的到来"+s.getInetAddress().getHostAddress());
	                dos.writeUTF(Thread.currentThread().getName()+"已开启");
	                new UserReadThread().start();
	                new UserWriteThread().start();
	                System.out.println(Thread.currentThread().getName()+"已开启");
            	//}
                
            }catch(Exception e)
            {
            	System.out.println("服务器端异常");
               // System.exit(0);
            }
        }
        public void close()
        {
            try{
                s.close();
            }catch(Exception e)
            {
                System.exit(0);
            }
        }
        
        class UserReadThread extends Thread
        {
        	@Override
        	public void run()
        	{
        		while(true)
                {
                   try{
                        String str = dis.readUTF();
              //          System.out.println("对方说:"+str);
                        ta.append("客户端说："+str+"\n");
                        if(str.equalsIgnoreCase("再见"))
                        {
                            close();
                            System.exit(0);
                        }
                   }catch(Exception e)
                   {
                       JOptionPane.showMessageDialog(f,"客户端已离开");
                       return;
                   //    e.printStackTrace();
                   }
                }
        	}
        }
        class UserWriteThread extends Thread
        {
        	@Override
        	public void run()
        	{
                tf.addActionListener(new TCPServerListener());
                bn.addActionListener(new TCPServerListener());
        	}
        }
        class TCPServerListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                   try{
                	   String str = tf.getText();
                       if(!str.isEmpty())
                       {
                       	tf.setText("");
	                        ta.append("服务器说:"+str+"\n");
	                        dos.writeUTF(str);
	                        if(str.equalsIgnoreCase("再见"))
	                        {
	                            close();
	                            System.exit(0);
	                        }
                       }
                   }catch(Exception e1)
                   {
                      // e.printStackTrace();
                      System.exit(0);
                   } 
            }
        }
}