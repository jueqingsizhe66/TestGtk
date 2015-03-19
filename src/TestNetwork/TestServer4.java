/**
 * ���ͣ�
 */
package TestNetwork;
import java.io.*;
import java.net.ServerSocket;

import java.net.Socket;

//some error   swing : controler
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * @author    Ҷ����
 * @time      2015��3��19������3:20:34
 * @version   TestNetworkTestServer4 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestServer4
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
		 new TCPServer().launch();
	}

}


class TCPServer
{
        // class variables
        // class variables
        // connect
         private ServerSocket ss = null;
         private Socket s = null;
         // data flow
         private DataOutputStream dos = null;
         private DataInputStream dis = null;
         // UI
         private Frame f = null;
         private TextArea ta = null;
         private TextField tf = null;
         private Button  bn = null;

       
         public void launch()
         {
             createUI();
             connect();
             new ServerRead().start();
             new ServerWrite().start();
         }
        // construct member
        /*
        public TCPServer()
        {

        }
        */
        public void createUI()
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
                ss = new ServerSocket(5599);
                s = ss.accept();
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
            }catch(Exception e)
            {
                System.exit(0);
            }
        }
        public void close()
        {
            try{
                dis.close();
                dos.close();
                ss.close();
                s.close();
            }catch(Exception e)
            {
                System.exit(0);
            }
        }

        class ServerRead extends Thread
        {
            public void run()
            {
                while(true)
                {
                   try{
                        String str = dis.readUTF();
              //          System.out.println("�Է�˵:"+str);
                        ta.append("�Է�˵��"+str+"\n");
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
        class ServerWrite extends Thread
        {
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
                        tf.setText("");
                        ta.append("�Լ�˵:"+str+"\n");
                        dos.writeUTF(str);
                        if(str.equalsIgnoreCase("�ټ�"))
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






