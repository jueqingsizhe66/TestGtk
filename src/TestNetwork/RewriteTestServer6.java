/**
 * ���ͣ�
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
 * @author    Ҷ����
 * @time      2015��3��24������11:03:58
 * @version   TestNetworkRewriteTestServer6 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class RewriteTestServer6
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
         
         private static boolean isPrint = false;//�Ƿ������Ϣ��־
         private static List user_list = new CopyOnWriteArrayList();//��½�û�����
         private static List<ServerThread> thread_list = new CopyOnWriteArrayList<ServerThread>();//�����������õ��̼߳���
         private static LinkedBlockingQueue<String> message_list = new LinkedBlockingQueue<String>();//�����Ϣ����
        // private static List<String> message_list = new LinkedList<String>();//�����Ϣ����
      
         static
         {
        	 createUI();
         }
         public  TCPServer3(Socket s) throws Exception
         {
        	 this.s = s;
        	 new PrintOutThread();//������ͻ��˷�����Ϣ�߳�
             new ServerThread(this.s);  

        	 
         }
       
  
        public static void createUI()
        {
            JFrame f = new JFrame("������");
            ta = new JTextArea();
            jScroll = new JScrollPane(ta);
            jScroll.setHorizontalScrollBarPolicy(
            		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jScroll.setVerticalScrollBarPolicy(
            		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            tf = new JTextField();
            JPanel p = new JPanel(new BorderLayout());
            bn = new JButton("����");
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
	                        ta.append("������˵:"+str+"\n");
	                        //dos.writeUTF(str);
	                        if(str.equalsIgnoreCase("�ټ�"))
	                        {
	                            close();
	                            System.exit(0);
	                        }if(str.equalsIgnoreCase("showuser"))
	                        {
	                        	//�ᴫ�ݸ��ͻ��� ʵ���������Լ���
	                        	//dos.writeUTF(st.listOnlineUsers());
	                        	ta.append(st.listOnlineUsers());
	                            
	                        }else
	                        {
	                        	st.pushMessage("������˵:"+str+"\n");
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
         * �������߳���
         */
        class ServerThread extends Thread{
        	private Socket client;
        	private String name = null;
        	private ServerThread st = null; // �ܵ����������ã��� Client=s
        	public ServerThread(Socket s) throws Exception
        	{
        		this.client = s;
        		this.st = this;
        		dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF("�ɹ����Ϸ�����"+client.getInetAddress().getHostAddress());
                dos.writeUTF(Thread.currentThread().getName()+"�ѿ���"+"�������������");
                this.start();
                //tf�ļ�����
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
        				//�鿴�����û��б�
                        if ("showuser".equals(line)) {
                            dos.writeUTF(this.listOnlineUsers());
                            line = dis.readUTF();
                        }
                        
                        //��һ�ν��룬��������

                        if(flag++ ==0){
                            name = line;
                            user_list.add(name);
                            thread_list.add(this);
                            System.out.println("this="+this);
                            this.pushMessage("Client<" + name +">����������...+\n");
                            dos.writeUTF(name +"���,���Կ�ʼ������...");
                            //
                            
                            //�����ڷ������Ĵ�����
                            ta.append("Client<" + name +">����������...\n");
                        }else{
                        	 //�����ڷ������Ĵ�����
                        	ta.append("Client<" + name +"> say : " + line+"\n");
                        	this.pushMessage("Client<" + name +"> say : " + line+"\n");
                        }
                        line = dis.readUTF();
        			}
        		}catch(IOException e)
        		{
        			
        		}
        	}
        	 //������Ϣ����ĩβ��׼�����͸��ͻ���
            public void pushMessage(String msg){
            	//msssage_list.
                //message_list.addLast(msg); //�����LinkedList
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
              
            //��ͻ��˷���һ����Ϣ(��PrintOutThread ��ʹ�� ���Ǳ����public����)
            public void sendMessage(String msg) throws Exception{
                dos.writeUTF(msg);
                dos.flush();
            }
              
            //ͳ�������û��б�  ��Ҫ��showuser��ʹ��  ��Ϊpublic
            private String listOnlineUsers() {
                String s ="--- �����û��б� ---";
                for (int i =0; i < user_list.size(); i++) {
                    s +="[" + user_list.get(i) +"]";
                }
                s +="--------------------";
                return s;
            }
 
        }
      	
        /**
         * �����Ƿ��������Ϣ�����߳���,��ͻ��˷�����Ϣ
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
                    if(isPrint){//�������ڶ����е���Ϣ��˳���͵����ͻ��ˣ����Ӷ����������
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
                        //��仰����Ҫ��������������޸�
                        isPrint = message_list.size()>0 ?true :false;
                    }
                }
            }
        }
}
