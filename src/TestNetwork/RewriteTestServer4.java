/**
 * ���ͣ�
 */
package TestNetwork;

/**
 * @author    Ҷ����
 * @time      2015��3��22������10:08:18
 * @version   TestNetworkRewriteTestServer4 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
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
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
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
            Frame f = new Frame("������");
            ta = new TextArea();
            tf = new TextField();
            Panel p = new Panel(new BorderLayout());
            bn = new Button("����");
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
            		//������� �ȴ��ͻ��˵�����
	                dis = new DataInputStream(s.getInputStream());
	                dos = new DataOutputStream(s.getOutputStream());
	                dos.writeUTF("��ӭ��ĵ���"+s.getInetAddress().getHostAddress());
	                dos.writeUTF(Thread.currentThread().getName()+"�ѿ���");
	                new UserReadThread().start();
	                new UserWriteThread().start();
	                System.out.println(Thread.currentThread().getName()+"�ѿ���");
            	//}
                
            }catch(Exception e)
            {
            	System.out.println("���������쳣");
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
              //          System.out.println("�Է�˵:"+str);
                        ta.append("�ͻ���˵��"+str+"\n");
                        if(str.equalsIgnoreCase("�ټ�"))
                        {
                            close();
                            System.exit(0);
                        }
                   }catch(Exception e)
                   {
                       JOptionPane.showMessageDialog(f,"�ͻ������뿪");
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
	                        ta.append("������˵:"+str+"\n");
	                        dos.writeUTF(str);
	                        if(str.equalsIgnoreCase("�ټ�"))
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