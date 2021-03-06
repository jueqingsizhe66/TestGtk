/**
 * 解释：
 */
package TestNetwork;
import java.net.*;
import java.io.*;

// some error   swing : controler
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * @author    叶昭良
 * @time      2015年3月19日下午3:22:27
 * @version   TestNetworkTestClient4 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestClient4
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
           //  new ClientRead().start();
            // new ClientWrite().start();
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
                s = new Socket("127.0.0.1",5599);
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
     	       try{
   	            String str = dis.readUTF();
   	       //     System.out.println("对方说:"+str);
   	            ta.append("服务器说:"+str+"\n");
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

	/*public class ClientRead1
	{ 
	    while(true)
	    {

	    	}
	 }*/
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