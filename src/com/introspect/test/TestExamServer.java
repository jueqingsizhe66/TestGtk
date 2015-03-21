/**
 * 解释：
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
 * @author    叶昭良
 * @time      2015年3月21日下午2:56:04
 * @version   com.introspect.testTestExamServer V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestExamServer
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
		// TODO Auto-generated method stub
		Font font = new Font("Dialog", Font.PLAIN, 12); //一下是改变默认的组建上显示的字体，这样更加美观一些
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
              //          System.out.println("对方说:"+str);
                        ta.append("客户端说："+str+"\n");
                        if(str.equalsIgnoreCase("再见"))
                        {
                            close();
                            System.exit(0);
                        }
                        if(str.matches(".*\\|.*"))
                        {
                        	System.out.println("的确存在");
                        	String[] piles = str.split("[|]");
                        	Student s1 = (Student)MyORM.select(Student.class, piles[1]);
                        	ta.append("str的姓名是:"+s1.getname());
                        }
                        if(str.matches("2"))
                        {
                        	ta.append("服务器对客户端说：总共有"+MyORM.select(Student.class)+"个学生\n"); 
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
                        ta.append("服务器说:"+str+"\n");
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
