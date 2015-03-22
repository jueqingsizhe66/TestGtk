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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;





/**
 * @author    叶昭良
 * @time      2015年3月23日上午12:41:10
 * @version   TestNetworkRewriteTestServer5 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class RewriteTestServer5
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
        	new TCPServer2(ss.accept());
        }
	    
	}

}
class TCPServer2
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
         
         private static boolean isPrint = false;//是否输出消息标志
         private static List user_list = new ArrayList();//登陆用户集合
         private static List<ServerThread> thread_list = new ArrayList<ServerThread>();//服务器已启用的线程集合
         private static LinkedList<String> message_list = new LinkedList<String>();//存放消息队列
      
         static
         {
        	 createUI();
         }
         public  TCPServer2(Socket s) throws Exception
         {
        	 this.s = s;
        	 new PrintOutThread();//创建向客户端发送消息线程
             new ServerThread(this.s);  
             //本以为可以让其线程安全的，没想到没什么效果 反而更卡
/*             Collections.synchronizedList(user_list);
             Collections.synchronizedList(thread_list);
             Collections.synchronizedList(message_list);*/
            // launch();
        	 
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
        

        public void close()
        {
            try{
                s.close();
            }catch(Exception e)
            {
                System.exit(0);
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
        
        /**
         * 服务器线程类
         */
        class ServerThread extends Thread{
        	private Socket client;
        	private String name = null;
        	public ServerThread(Socket s) throws Exception
        	{
        		this.client = s;
        		dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF("成功连上服务器"+client.getInetAddress().getHostAddress());
                dos.writeUTF(Thread.currentThread().getName()+"已开启"+"请输入你的姓名");
                this.start();
                tf.addActionListener(new TCPServerListener());
                bn.addActionListener(new TCPServerListener());
        	}
        	@Override
        	public void run()
        	{
        		try
        		{
        			int flag = 0 ;
        			String line = dis.readUTF();
        			while(!"bye".equals(line))
        			{
        				//查看在线用户列表
                        if ("showuser".equals(line)) {
                            dos.writeUTF(this.listOnlineUsers());
                            line = dis.readUTF();
                        }
                        //第一次进入，保存名字

                        if(flag++ ==0){
                            name = line;
                            user_list.add(name);
                            thread_list.add(this);
                            System.out.println("this="+this);
                            dos.writeUTF(name +"你好,可以开始聊天了...");
                            this.pushMessage("Client<" + name +">进入聊天室...+\n");
                            ta.append("Client<" + name +">进入聊天室...\n");
                        }else{
                        	ta.append("Client<" + name +"> say : " + line+"\n");
                        	this.pushMessage("Client<" + name +"> say : " + line+"\n");
                        }
                        line = dis.readUTF();
        			}
        		}catch(IOException e)
        		{
        			
        		}
        	}
        	
        	 //放入消息队列末尾，准备发送给客户端
            private void pushMessage(String msg){
                message_list.addLast(msg);
                isPrint =true;
            }
              
            //向客户端发送一条消息(在PrintOutThread 有使用 于是变成了public修饰)
            public void sendMessage(String msg) throws Exception{
                dos.writeUTF(msg);
                dos.flush();
            }
              
            //统计在线用户列表  需要在showuser中使用  改为public
            private String listOnlineUsers() {
                String s ="--- 在线用户列表 ---\015\012";
                for (int i =0; i < user_list.size(); i++) {
                    s +="[" + user_list.get(i) +"]\015\012";
                }
                s +="--------------------";
                return s;
            }
        }
        /**
         * 监听是否有输出消息请求线程类,向客户端发送消息
         */
        class PrintOutThread extends Thread
        {
            public PrintOutThread()
            {
                start();
            }
              
            @Override
            public void run() 
            {
                while(true){
                    if(isPrint){//将缓存在队列中的消息按顺序发送到各客户端，并从队列中清除。
                        String message = message_list.getFirst();
                        for (ServerThread thread : thread_list) 
                        {
                            try
							{
								thread.sendMessage(message);
							} catch (Exception e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        }
                        message_list.removeFirst();
                        isPrint = message_list.size() >0 ?true :false;
                    }
                }
            }
        }
}
