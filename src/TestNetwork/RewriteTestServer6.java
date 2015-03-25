/**
 * 解释：
 */
package TestNetwork;

import java.awt.BorderLayout;
import java.awt.Font;




/*import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;*/
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;



/**
 * @author    叶昭良
 * @time      2015年3月24日上午11:03:58
 * @version   TestNetworkRewriteTestServer6 V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class RewriteTestServer6
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
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(6699);
        while(true)
        {
        	new TCPServer3(ss.accept());
        }
	}

}


class TCPServer3
{
        // class variables
        // class variables
        // connect
         
         private Socket s = null;
         // data flow
         private DataOutputStream dos = null;
         private DataInputStream dis = null;
         // UI
        
         private static JFrame f = null;
         private static JTextArea ta = null;
         private static JTextField tf = null;
         private static JButton  bn = null;
         private static JScrollPane jScroll = null;
         
         private static boolean isPrint = false;//是否输出消息标志
         private static List user_list = new CopyOnWriteArrayList();//登陆用户集合
         private static List<ServerThread> thread_list = new CopyOnWriteArrayList<ServerThread>();//服务器已启用的线程集合
         private static LinkedBlockingQueue<String> message_list = new LinkedBlockingQueue<String>();//存放消息队列
        // private static List<String> message_list = new LinkedList<String>();//存放消息队列
      
         static
         {
        	 createUI();
         }
         public  TCPServer3(Socket s) throws Exception
         {
        	 this.s = s;
        	 new PrintOutThread();//创建向客户端发送消息线程
             new ServerThread(this.s);  

        	 
         }
       
  
        public static void createUI()
        {
            JFrame f = new JFrame("服务器");
            ta = new JTextArea();
            jScroll = new JScrollPane(ta);
            jScroll.setHorizontalScrollBarPolicy(
            		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jScroll.setVerticalScrollBarPolicy(
            		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            tf = new JTextField();
            JPanel p = new JPanel(new BorderLayout());
            bn = new JButton("发送");
            p.add(tf,BorderLayout.CENTER);
            p.add(bn,BorderLayout.EAST);

            f.add(jScroll,BorderLayout.CENTER);
            f.add(p,BorderLayout.SOUTH);

            f.setSize(400,200);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        	private ServerThread st = null;
        	public TCPServerListener(ServerThread s)
        	{
        		st =s ;
        	}
            @Override
            public void actionPerformed(ActionEvent e)
            {
                   try{
                	   String str = tf.getText();
                       if(!str.isEmpty())
                       {
                       		tf.setText("");
	                        ta.append("服务器说:"+str+"\n");
	                        //dos.writeUTF(str);
	                        if(str.equalsIgnoreCase("再见"))
	                        {
	                            close();
	                            System.exit(0);
	                        }if(str.equalsIgnoreCase("showuser"))
	                        {
	                        	//会传递给客户端 实际上是想自己看
	                        	//dos.writeUTF(st.listOnlineUsers());
	                        	ta.append(st.listOnlineUsers());
	                            
	                        }else
	                        {
	                        	st.pushMessage("服务器说:"+str+"\n");
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
        	private ServerThread st = null; // 受到启发的作用！！ Client=s
        	public ServerThread(Socket s) throws Exception
        	{
        		this.client = s;
        		this.st = this;
        		dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF("成功连上服务器"+client.getInetAddress().getHostAddress());
                dos.writeUTF(Thread.currentThread().getName()+"已开启"+"请输入你的姓名");
                this.start();
                //tf的监听器
                tf.addActionListener(new TCPServerListener(this.st));
                bn.addActionListener(new TCPServerListener(this.st));
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
                            this.pushMessage("Client<" + name +">进入聊天室...+\n");
                            dos.writeUTF(name +"你好,可以开始聊天了...");
                            //
                            
                            //现在在服务器的大厅中
                            ta.append("Client<" + name +">进入聊天室...\n");
                        }else{
                        	 //现在在服务器的大厅中
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
            public void pushMessage(String msg){
            	//msssage_list.
                //message_list.addLast(msg); //针对于LinkedList
            	try
				{
					message_list.put(msg);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                isPrint =true;
            }
              
            //向客户端发送一条消息(在PrintOutThread 有使用 于是变成了public修饰)
            public void sendMessage(String msg) throws Exception{
                dos.writeUTF(msg);
                dos.flush();
            }
              
            //统计在线用户列表  需要在showuser中使用  改为public
            private String listOnlineUsers() {
                String s ="--- 在线用户列表 ---";
                for (int i =0; i < user_list.size(); i++) {
                    s +="[" + user_list.get(i) +"]";
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
                        String message = null;
						try
						{
							message = message_list.take();
						} catch (InterruptedException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        //message = message_list.getFirst();
                        for (ServerThread thread : thread_list) 
                        {
                            try
							{
                            	// if thread == threadthis
                            	//this.
                            	thread.sendMessage(message);
							} catch (Exception e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        }
                       // message_list.removeFirst();
                        //isPrint = false;
                        //这句话很重要！！！不能随便修改
                        isPrint = message_list.size()>0 ?true :false;
                    }
                }
            }
        }
}

