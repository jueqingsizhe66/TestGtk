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
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;



/**
 * @author    Ҷ����
 * @time      2015��3��24������11:03:36
 * @version   TestNetworkRewriteTestClient6 V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class RewriteTestClient6
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
		Socket s  = null;
		s = new Socket("127.0.0.1",6699);

		 new TCPClient6(s);
	}

}

class TCPClient6
{
        // class variables
        // class variables
        // connect
         private Socket s = null;
         // data flow
         private DataOutputStream dos = null;
         private DataInputStream dis = null;
         // UI
         private JFrame f = null;
         private JTextArea ta = null;
         private JTextField tf = null;
         private JButton  bn = null;
         private JScrollPane jScroll = null;
         
         /**
          * ����������ӣ������뷢����Ϣ
          */
         public TCPClient6(Socket s) throws Exception
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
          * ���ڼ�������������ͻ��˷�����Ϣ�߳���
          */
  
        public void createUI()
        {
            f = new JFrame("�ͻ���");
            ta = new JTextArea();
            ta.setLineWrap(true);//�����Զ����й���
            ta.setWrapStyleWord(true);//������в����ֹ���
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
            //f.setVisable(true);
	        tf.addActionListener(new TCPClientListener());
	        bn.addActionListener(new TCPClientListener());
   
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
         * ���ڼ�������������ͻ��˷�����Ϣ�߳���
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
                        if("byeClient".equals(result)){//�ͻ��������˳�������˷���ȷ���˳�
                            break;
                        }else{//�������˷�����Ϣ
                        	//ta.append(result);
                            System.out.println(result);
                           // dos.writeUTF(result);
                            ta.append("����������˵:"+result+"\n");
                            if(result.equalsIgnoreCase("�ټ�"))
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
	                ta.append("�ͻ���˵:"+str+"\n");
	                
	                if(str.equalsIgnoreCase("�ټ�"))
	                {
	                    close();
	                    System.exit(0);
	                }else if(str.equalsIgnoreCase("open"))
	                {
	                	System.out.println("��һ����");
	                	new MyFileChooser();
	                	//openFile();
	                	/*JFileChooser jf = new JFileChooser();
	                	jf.setVisible(true);
	                	jf.setMultiSelectionEnabled(true);
	                	//��������� ���쳣�˳������Բ���û�� ��Ȼ�˳��ˣ�
	                	jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	                	//����ѡ�����������׺
	                	jf.showDialog(new JLabel(), "ѡ��");
	                	String[] extj = {"java","txt","class"};
	                	FileNameExtensionFilter ff = new FileNameExtensionFilter("�ı��ļ�", extj);
	                	jf.setFileFilter(ff);
	                	InputStream is = null;
	                	try {
	                		File[] filenames = jf.getSelectedFiles();
		                	System.out.println("whye"+filenames[0].getName());
		                	for(int  i = 0 ; i < filenames.length; i++)
		                	{

	        					is = new FileInputStream(filenames[i]);
	        					System.out.println(filenames[i].getName());
	        					dos.writeUTF(filenames[i].getName());
	        					dos.writeUTF("�Ƿ���ܣ� [Yy]|[Nn]");

		                	}
	                	}catch(IOException e1)
	                	{
	                		throw new RuntimeException("��ȡ�쳣");
	                	}
	                	*/
	                	//String[] filenames = jf.
	                }else
	                {
	                	dos.writeUTF(str);
	                }
	           }catch(Exception e1)
	           {
	              // e.printStackTrace();
	              System.exit(0);
	           } 
	    }
	    class MyFileChooser extends JFrame implements ActionListener
	    {
	    	JButton open = null;
	        public MyFileChooser()
			{
				// TODO Auto-generated constructor stub
	    		open = new JButton("���ļ�");
	    		this.add(open);
	    		this.setBounds(200,200,100,100);
	    		this.setVisible(true);
	    		//���ܼ������ ����������ͻ��˶��ر��ˣ�������
	    		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		open.addActionListener(this);
			}

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				JFileChooser jf = new JFileChooser();
	        	//jf.setVisible(true);
				//���ܶ�ѡ��Ϊʲô����һ��ѡ�ͱ���
	        	//jf.setMultiSelectionEnabled(true);
	        	//����ѡ�����������׺
	        	//jf.showDialog(new JLable(),"xuanze")����
	        	//jf.showSaveDialog() ����Ի���
	        	//jf.showOpenDialog() �򿪶Ի���
	        	int result = jf.showOpenDialog(null);
	        	String[] extj = {"java","txt","class"};
	        	FileNameExtensionFilter ff = new FileNameExtensionFilter("�ı��ļ�", extj);
	        	jf.setFileFilter(ff);
	        	InputStream is = null;
	        	File filename = jf.getSelectedFile();
	        	try
				{
					dos.writeUTF(filename.getName());
					dos.writeUTF("�Ƿ���ܣ� [Yy]|[Nn]");
				} catch (IOException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	//System.out.println(filename[0].getName());
	        	String apple = null;
	        	
	        	
	        		/*for(int i = 0 ; i < filename.length; i++)
	        		{*/
    			if(filename!=null && result==JFileChooser.APPROVE_OPTION)
    			{
    				try
					{
						BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
						apple =br.readLine();
						while(apple != null)
						{
							//System.out.println(apple);
							dos.writeUTF(apple);
							apple =br.readLine();
						}
						
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    			}
	        			
	        		//}
	        		
	        	
	        	/*for(int  i = 0 ; i < filenames.length; i++)
	        	{
	        		try
					{
						is = new FileInputStream(filenames[i]);
						System.out.println(filenames[i].getName());
						dos.writeUTF(filenames[i].getName());
						dos.writeUTF("�Ƿ���ܣ� [Yy]|[Nn]");
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}*/
			}
	    	
	    }
	    public void openFile() 
	    {
        	JFileChooser jf = new JFileChooser();
        	jf.setVisible(true);
        	jf.setMultiSelectionEnabled(true);
        	//����ѡ�����������׺
        	jf.showDialog(new JLabel(), "ѡ��");
        	String[] extj = {"java","txt","class"};
        	FileNameExtensionFilter ff = new FileNameExtensionFilter("�ı��ļ�", extj);
        	jf.setFileFilter(ff);
        	InputStream is = null;
        	File[] filenames = jf.getSelectedFiles();
        	for(int  i = 0 ; i < filenames.length; i++)
        	{
        		try
				{
					is = new FileInputStream(filenames[i]);
					System.out.println(filenames[i].getName());
					dos.writeUTF(filenames[i].getName());
					dos.writeUTF("�Ƿ���ܣ� [Yy]|[Nn]");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
	    }
	}
}