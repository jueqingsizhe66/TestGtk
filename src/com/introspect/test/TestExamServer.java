/**
 * ���ͣ�
 */
package com.introspect.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 * @author    Ҷ����
 * @time      2015��3��21������2:56:04
 * @version   com.introspect.testTestExamServer V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestExamServer
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
		// TODO Auto-generated method stub
		Font font = new Font("Dialog", Font.PLAIN, 12); //һ���Ǹı�Ĭ�ϵ��齨����ʾ�����壬������������һЩ
		UIManager.put("MenuBar.font", font);
		UIManager.put("MenuItem.font", font);
		UIManager.put("Menu.font", font);
		UIManager.put("PopupMenu.font", font);
		UIManager.put("ToolBar.font", font);
		UIManager.put("ToolTip.font", font);
		UIManager.put("TabbedPane.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("List.font", font);
		UIManager.put("ComboBox.font", font);
		UIManager.put("Button.font", font);
		UIManager.put("Table.font", font);
		UIManager.put("TableHeader.font", font);
		UIManager.put("Tree.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		UIManager.put("TitledBorder.font", font);
		UIManager.put("OptionPane.font", font);
		UIManager.put("RadioButton.font", font);
		UIManager.put("CheckBox.font", font);
		UIManager.put("ToggleButton.font", font);
		UIManager.put("Dialog.font", font);
		UIManager.put("Panel.font", font);
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
                        ta.append("�ͻ���˵��"+str+"\n");
                        if(str.equalsIgnoreCase("�ټ�"))
                        {
                            close();
                            System.exit(0);
                        }
                        if(str.matches(".*\\|.*"))
                        {
                        	System.out.println("��ȷ����");
                        	String[] piles = str.split("[|]");
                        	Student s1 = (Student)MyORM.select(Student.class, piles[1]);
                        	ta.append("str��������:"+s1.getname());
                        }
                        if(str.matches("2"))
                        {
                        	ta.append("�������Կͻ���˵���ܹ���"+MyORM.select(Student.class)+"��ѧ��\n"); 
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
                        ta.append("������˵:"+str+"\n");
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