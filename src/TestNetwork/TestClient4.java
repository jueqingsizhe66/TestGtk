/**
 * ���ͣ�
 */
package TestNetwork;
import java.net.*;
import java.io.*;
// some error   swing : controler
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author    Ҷ����
 * @time      2015��3��19������3:22:27
 * @version   TestNetworkTestClient4 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestClient4
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
		 new TCPClient().launch();
	}

}
class TCPClient
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

       
         public void launch()
         {
             createUI();
             connect();
             new ClientRead().start();
             new ClientWrite().start();
         }
        // construct member
        /*
        public TCPClient()
        {

        }
        */
        public void createUI()
        {
            Frame f = new Frame("�ͻ���");
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
                s = new Socket("127.0.0.1",5599);
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
                s.close();
            }catch(Exception e)
            {
                System.exit(0);
            }
        }

        class ClientRead extends Thread
        {
            public void run()
            {
                while(true)
                {
                   try{
                        String str = dis.readUTF();
                   //     System.out.println("�Է�˵:"+str);
                        ta.append("�Է�˵:"+str+"\n");
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
        class ClientWrite extends Thread
        {
            public void run()
            {
                tf.addActionListener(new TCPClientListener());
                bn.addActionListener(new TCPClientListener());
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