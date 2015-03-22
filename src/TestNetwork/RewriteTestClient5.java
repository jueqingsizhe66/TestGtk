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

import TestNetwork.TCPClient1.ClientRead;
import TestNetwork.TCPClient1.ClientWrite;
import TestNetwork.TCPClient1.TCPClientListener;

/**
 * @author    叶昭良
 * @time      2015年3月23日上午1:57:54
 * @version   TestNetworkRewriteTestClient5 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class RewriteTestClient5
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
		Socket s  = null;
		s = new Socket("127.0.0.1",5599);

		 new TCPClient5(s);
	}

}
class TCPClient5
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
         
         /**
          * 与服务器连接，并输入发送消息
          */
         public TCPClient5(Socket s) throws Exception
         {
        	 this.s = s;
        	 dis = new DataInputStream(s.getInputStream());
             dos = new DataOutputStream(s.getOutputStream());
             createUI();
             //new ClientRead(dis);
             //new ClientWrite(dos);
             new readLineThread();

         }
         /**
          * 用于监听服务器端向客户端发送消息线程类
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
        /**
         * 用于监听服务器端向客户端发送消息线程类
         */
        class readLineThread extends Thread
        {
              
            private BufferedReader buff = null;;
            public readLineThread(){
            	start();
                
            }
              
            @Override
            public void run() {
                try {
                    while(true){
                        String result = dis.readUTF();
                        if("byeClient".equals(result)){//客户端申请退出，服务端返回确认退出
                            break;
                        }else{//输出服务端发送消息
                        	//ta.append(result);
                            System.out.println(result);
                           // dos.writeUTF(result);
                            ta.append("服务器传递说:"+result+"\n");
                            if(result.equalsIgnoreCase("再见"))
                            {
                                close();
                                System.exit(0);
                            }
                        }
                    }
                }catch (Exception e) {
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

