package com.tank.six;


/*
 * date : 2012-9-27
 * author : Heroang
 * function : ̹�˽�������  �ӵ�����ˣ�   ���Ұ� bullet s =new bullet();�������� ���Խ���������
 * 				�������5��
 * 
 * 				����ʵ���ӵ�����ʱ����Դݻ�̹�ˣ� ��ΪʲôҪ���ɺ������� isLive���ԣ�
 * 
 * 				�����к�  ̹�˻��б�ը��Ч��
 * 
 * ʹ����̹����������   
 * 
 * ��֤����̹�˲����߽�    if(x>0)'
 * 
 * 
 * �õ��˻���Ҳ�ܷ����ӵ���   ��ʹ���˷�����ӵ��ܹ�hitme������������ϣ���
 * ���趨�ҵ�������
 *  Ϊ�����ӿɶ��� ��hittank���ж����ó�һ������    public void HitEnemyTank()
 *    public void HitHeroTank()
 *    ��һ��bug ����̹��̫�������ˣ�  ��ô��   ���о����������̹�������ǲ��ܷ����ӵ���(��Ȼ����ܼ� ֻҪ�ı�J�����б���������)
 *    
 *    �����ҵ�hittank����
 *    
 *    Ϊʲô����IsTouchEnemy����EnemyTank����Ū��
 *      �뷨�������Լ��ж�   ���������ΪӦ������ÿһ���½���̹���Լ�ȥ�жϣ� �����Ƿ��������ط�    �����ҷ�̹���������Լ�����
 *      	��������������EnemyTank�У�
 *    
 *    ��2���������¹���
 *     1���Էֹ�
 *       1.1ר����һ���յ�Jpanel  ����ʾ����
 *       1.2��������˸  ��һ�仰���ѣ�  ��˸ �� һ�ử������  һ�᲻��������
 *       		�����ǳ��ֲ�ͬ������� ��ʱ��ʱ���������漰��ʱ������⣡  Ӧ���Ǹ�ʱ���йأ�  ��Ƴ�һ���߳�  ��ͬ��ʱ�������귢���ı䣡
 *     2������ͣ�ͼ���
 *     
 *       ���û������ͣʱ �� �ӵ� �� ̹�˵��ٶ�Ϊ 0   ���ҷ�����Ϊ���� ����  
 *       				��Ȼ��֮ǰ�����ȱ���  �ӵ���̹�˵ġ�������������
 *     3���Լ�¼��ҵĳɼ�
 *     	3.1�ļ��� �������涼���ļ�����  �����Ǵ�����Ϸ�����ݿ�  CS ���Σ�
 *      3.2��дһ����¼��
 *     4java�ٿ������ļ�
 *     
 *     
 *     date : 2012-9-29
 *     function: ����Ҫ��1.Record��    2.���ӷֹش���     3.����Ի���    4.�Լ�һ��flags��־��
 *     ���⣺ʲôʱ�򱣴�    ʲô����һ�� ���е��˶�û�е�ʱ��
 *     
 *     ������һ������������
 *     
 *     
 *      date:2012-9-30
 *     �����bug֮��  �����������������  ���汾���������������İ汾 
 *     
 *     date: 2012-10-2
 *     ������һ������������  ֻ��wav�ļ�
 *     
 *     UImageer   Load���ж�if(this.mp3 != null)
			{
				System.out.println("�Ƿ�����mp3");
				this.remove(mp3);
			}
 *    
 *     ��Ҫ���� MyPanel2() �޸��������ĺ���
 *     ����1: Ϊʲô�л��������˵��� ��û�������ˣ������캯����ԭ�򣿣�
 *     ����2�� Ϊʲô����Ϸû�������ԭ����̹�� //// �ѽ�� ɾ��mp3 ����
 *     ����3�� Ϊʲô�������ֺ��ٶ����Ա���
 *     
	1����ͼһ�仰�� Graphics����
	2������ͼƬһ�仰   ImageIo.write("images/...");
	    ��ȻBufferedImage����Ҳ��Ҫ��Graphics����g.drawImage�ſ��Ի�������
	    
	    
	    ������һ��door���������йؿ�������
	    
	    door��һ��ʮ����Ҫ�Ĳ�������
	     *     date : 2012-10-3
 *     ��Ҫʵ�ַֹ�
 *     �ҷ�����̹������һֱΪ2   �ҷ��ӵ��ƶ��ٶ�ҲһֱΪ1
 *     1��  �з�̹���� 3  �з�̹���ƶ��ٶ�1  �з��ӵ��ƶ��ٶ�1     
 *     2��  �з�̹����6  �з�̹���ƶ��ٶ�2  �з��ӵ��ƶ��ٶ�2
 *     3��  �з�̹����8  �з�̹���ƶ�3  �з��ӵ��ƶ��ٶ�3
 *     
 *     ����һ������stage
 * 
 * 
 * 
 * 
 * 
 *   ���ȵð�̹�˻�������1��
 *   
 *   Ȼ��Ѷ��߳����ϰ���
 *   
 *   Ȼ������ݽṹ ���� ���� ���ϰ���
 *   
 *   �ٰ�
 *   
 *   
 *   ���Ű��ı���Ϣ ֱ������ORM����̹�� �ӵ�  ը��  �������ݿ���ȥ
 *   
 *   ̹�˱� 
 *       �ҷ�̹�˱�
 *       �з�̹�˱�
 *   �ӵ���
 *   ը����
 *   
 *   
 *   ȱ�ݣ��������淶�� �߼������
 *   
 *    ���ܾ�����һ�㲻ˬ   ��Ӧ�ð����еĶ���������paint�����н����ػ��� ��Ӧ����
 *    ÿ����һ��Tank��ʱ������ػ�  ��ȽϺ�Щ��  ��Ҳ����һ����Ŀ��
 *    ���µ�Tank���ж���
 *            PositionX
 *            PositionY
 *            Direction  ,����setDirection�ȽϺã� ��Ҫ�ù��캯���Ĳ���̫��
 *            Speed       setSpeed
 *            isLive      setIsLive
 *            isTurn      setIsTurn
 *            
 *     
 *    ���ӵ��ɰɣ������Լ��ɣ����Ա������̣߳�ը������Ϊԭ�����Բ���Ҫ�ƶ����Ͳ�Ҫ�߳�
 *                                       �ҷ�ͨ���������п��ƣ�����Ҳ����Ҫ
 *    class bullet implements Runnable{
 *    class EnemyTank extends TankM implements Runnable{
 *    class bome{  
 *    class MyPanel2 extends JPanel implements KeyListener,Runnable{
 *    class MyStartPanel extends JPanel implements Runnable{
 *            
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;
public class tankversion extends JFrame implements ActionListener ,Runnable
{

	//�����������
	static MyPanel2 mp3 = null;
	
	//����һ����ʼ���
	static MyStartPanel msp = null;
	//����һ���˵���
	JMenuBar jmb = null;
	//����һ���˵���֦
	JMenu jm1 = null;
	//�����˵�
	JMenu jm2 = null;
	//����3���˵���Ҷ    һ�������˳�   һ���Ǵ򿪴��̵��ļ� ������Ϸ

	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	
	//��ͣ
	JMenuItem jmi4 = null;
	//����
	JMenuItem jmi5 = null;
	
	//��������Ϸ
	JMenuItem jnew = null;
	JMenuItem jexit = null;
	
	//�����Ĳ���˵��
	JMenuItem jmi21 = null;
	
///////////////////////////////////////////�ؿ��趨��ʼ//////////////////////////
//����һ�������ı���
static int door = 1;
//��һ��������ʾ���˵Ļ�����Ŀ
static int enSize =6;


//��MyPanel�Ƶ� ��� �������ӵл�����Ŀ
//����һ��Vector��������������ط���ս��
static Vector<EnemyTank> eet = new Vector<EnemyTank>();
//��ͬ�صĹ�ۡ������Ϣ
public static void SetStage(int door)
{
	if(door == 1)
	{
		enSize = 3;
		EnemyTank.setSpeed(1);
		setstagespeed(1);
	}else if(door  == 2)
	{
		enSize = 6;
		EnemyTank.setSpeed(2);
		setstagespeed(2);
	}else if(door == 3)
	{
		enSize = 8;
		EnemyTank.setSpeed(3);
		setstagespeed(3);
	}
}

//����ԭ�ȵĵ����ӵ�����Ϊ��̬����
public static  void setstagespeed(int speed)
{
	for(int i = 0 ; i<EnemyTank.ss.size() ; i++)
	{
		EnemyTank.ss.get(i).setSpeed(speed);
	}
}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// 	 UIManager.put("MenuBar.font",new  Font("����",Font.PLAIN,10));
//	     UIManager.setLookAndFeel("org.fife.plaf.Office2003.Office2003LookAndFeel");
//      UIManager.setLookAndFeel("org.fife.plaf.OfficeXP.OfficeXPLookAndFeel");
//      UIManager.setLookAndFeel("org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel");
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
		//UIManager��Ϊ������
		tankversion tk2 = new tankversion();
		//System.out.println("����while"+tankversion.eet.size());
		if(tankversion.eet.size() ==0 && tankversion.door <4)
		{
			//System.out.println(tankversion.eet.size());
			tankversion.SetStage(tankversion.door);
//				if(door == 1){
			//if(ee)
			if(tankversion.mp3 != null)
			{
				System.out.println("�Ƿ�����mp3");
				tankversion.mp3 = null;
				
			}
			if(tankversion.msp !=null)
			{
				tankversion.msp = null;
			}
			tankversion.msp = new MyStartPanel();
//					door++;
			//�����������
			//���ӿ�ʼ���
			/*this.add(msp);
			//�ü������
			this.setVisible(true);*/
			//����һ���̲߳���ʼ
			Thread t1 =new Thread(msp);
			t1.start();
		}
			
		
		
	}
	
	public tankversion()
	{
		//�����������
		
		//����һ����ʼ���
		msp = new MyStartPanel();
		//�����������
		jmb = new JMenuBar();
		jm1 = new JMenu("�ļ�(F)");
		//�����ļ����Ƿ�
		jm1.setMnemonic('f'); //�����á��� ֻ���õ����Ű���������
		
		//�����������
		//���ӿ�ʼ���
		this.add(msp);
		Thread t1 =new Thread(msp);
		t1.start();
		jmi1 = new JMenuItem("�����˳�(x)");
		jmi1.addActionListener(this);
		
		jmi2 = new JMenuItem("�ָ���(R)");
		jmi2.addActionListener(this);
		
		jmi3 = new JMenuItem("ͳ�Ƶ÷�");
		jmi3.addActionListener(this);
		
		jmi4  =  new JMenuItem("��ͣ(P)");
		jmi4.setMnemonic('P');
		jmi4.addActionListener(this);
		jmi4.setActionCommand("pause");
		
		
		jmi5  =  new JMenuItem("����(C)");
		jmi5.setMnemonic('C');
		jmi5.addActionListener(this);
		jmi5.setActionCommand("continue");
		jmi5.setEnabled(false);
		
		jnew  =  new JMenuItem("����Ϸ(N)");
		jnew.setMnemonic('N');
		jnew.addActionListener(this);
		jnew.setActionCommand("newgame");
		
		jexit  =  new JMenuItem("�˳�(E)");
		jexit.setMnemonic('E');
		jexit.addActionListener(this);
		jexit.setActionCommand("exit");
		
		
		jm2=new JMenu("����(H)");
	    jm2.setMnemonic('h');
	    jmi21=new JMenuItem("����˵��(U)");
	    jmi21.setMnemonic('u');
	    jmi21.addActionListener(this);
	    jmi21.setActionCommand("help");

		jm1.add(jnew);
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jm1.add(jmi5);
		jm1.add(jexit);
		
		jm2.add(jmi21);
		
		jmb.add(jm1);
		jmb.add(jm2);
		
		
		
		this.setJMenuBar(jmb);
		//������Ĵ���ת�Ƶ� ����Ϸ��
		/*mp3 = new MyPanel2(); //���ڲ�������������齨��  �����Ļ���û���κ�̹����
		//���ֹ����� 
		
		//�������
		this.add(mp3);
		//���ô�������
		Thread t3 = new Thread(mp3);  //�൱�ڻ���һ���� �Ϳ�ʼ����������棡
		t3.start();
		//����JFrame������
		this.addKeyListener(mp3);*/
		this.setTitle("̹�˴�սԭʼ��װ");
		this.setSize(400,400);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��ʾ����
		this.setVisible(true);
	}

	public void checktank()
	{
		int exitChoose = JOptionPane.showConfirmDialog(this, "��Ҫ�˳���", "�˳���ʾ", JOptionPane.OK_CANCEL_OPTION);
		if (exitChoose == JOptionPane.OK_OPTION) 
		{
			recorddata rd = new recorddata();
			rd.writeRecord();
			//��������ɹ�����
			 ReSuccess re=new  ReSuccess();
			 System.exit(0);
		} else 
		{
			return;
		
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		//�����˳�
		if(e.getSource() == jmi1){ 
//			System.out.println("�ѱ�����");
			//���ñ�����Ϸ����
			recorddata.writeRecord ();
			//��������ɹ�����
			 ReSuccess re=new  ReSuccess();
			 //�˳�
			 System.exit(0);

			 
		//�ָ�  load
		}else if(e.getSource() == jmi2)
		{
			if(this.mp3 != null)
			{
				System.out.println("�Ƿ�����mp3");
				this.remove(mp3);
			}
			//�ټ���֮ǰ ���ȵ�ɾ���ɵ� ���ǵ�һ��
			if(this.msp !=null){
			this.remove(msp);
			}
//			System.out.println("�ѱ�����");
			recorddata.flags = "load";
			recorddata.RecordRead();
			
			//�ټ���֮ǰ ���ȵ�ɾ���ɵ� ���ǵ�һ��
			this.remove(msp);
			mp3 = new MyPanel2(); //���ڲ�������������齨��  �����Ļ���û���κ�̹����
			//���ֹ����� 
			
			//�������
			this.add(mp3);
			//���ô�������
			Thread t3 = new Thread(mp3);  //�൱�ڻ���һ���� �Ϳ�ʼ����������棡
			t3.start();
			//����JFrame������
			this.addKeyListener(mp3);
			//��������ɼ�    ���ǵڶ���   ȱһ����
			this.setVisible(true);
			
		}else if(e.getSource() == jmi3){
			System.out.println("�ѱ�����");
		}else if(e.getActionCommand().equals("newgame"))
		{
			
			//Ҳ��������߶�ȡ���ݣ�  ������MYpanel�ж�ȡ����
			recorddata.flags = "newgame";
			//recorddata.RecordRead();  //��仰һ���� ��û��
			
		    recorddata.setEnNum(20);
		    recorddata.setMylife(3);
		    recorddata.setKillenNum(0);

			//�ټ���֮ǰ ���ȵ�ɾ���ɵ� ���ǵ�һ��
			//this.remove(msp);
			if(this.mp3 != null)
			{
				System.out.println("�Ƿ�����mp3");
				this.remove(mp3);
			}
			//�ټ���֮ǰ ���ȵ�ɾ���ɵ� ���ǵ�һ��
			if(this.msp !=null){
			this.remove(msp);
			}
			mp3 = new MyPanel2(); //���ڲ�������������齨��  �����Ļ���û���κ�̹����
			//���ֹ����� 
			
			//�������
			this.add(mp3);
			//���ô�������
			Thread t3 = new Thread(mp3);  //�൱�ڻ���һ���� �Ϳ�ʼ����������棡
			t3.start();
			//����JFrame������
			this.addKeyListener(mp3);
			//��������ɼ�    ���ǵڶ���   ȱһ����
			this.setVisible(true);
		}else if(e.getActionCommand().equals("pause"))
		{
			//����һ�������������  �����ҵ�һ��ѧ����   ����Ӧ�ñ�����static ���Բſ���  ����������ԲŻᱻ�̳�
			//֤ʵ��ȷ������ 
			bullet.setSpeed(0);
			Hero.setSpeed(0);
			EnemyTank.setSpeed(0);
			
			EnemyTank.setIsTurn(false);
			Hero.setCanTurn(false);
			jmi4.setEnabled(false);
			jmi5.setEnabled(true);
		}else if(e.getActionCommand().equals("continue"))
		{
			bullet.setSpeed(1);  //��߿��Լ��������ٶ�
			Hero.setSpeed(1);
			EnemyTank.setSpeed(1);
			
			EnemyTank.setIsTurn(true);
			
			Hero.setCanTurn(true); 
			jmi4.setEnabled(true);
			jmi5.setEnabled(false);//Ȼ����Զ�MyHero����w s a d����     ������EnemyTank����ȥrun�߳��У�����if(EnemyTank.s)
		}else if(e.getActionCommand().equals("exit"))
		{
			
			//�˳���Ϸ
			//���ұ�����ٵ��˵���Ŀ   Ҳ����˵���Ӧ������һ��д���ļ�������Ϊ��  ����������������Recorddata�����
		/*	recorddata.writeRecord();
			System.exit(0);*/
			//�°汾�ķ���
			int exitChoose = JOptionPane.showConfirmDialog(this, "��Ҫ����������", "�˳���ʾ", 

			JOptionPane.OK_CANCEL_OPTION);
			if (exitChoose == JOptionPane.OK_OPTION) {
				recorddata rd = new recorddata();
				rd.writeRecord();
				//��������ɹ�����
				 ReSuccess re=new  ReSuccess();
				 System.exit(0);
			} else {
				System.exit(0);
			
			}
		}else if(e.getActionCommand().equals("help"))
		{
			Help h=new Help();
		}
	}
	//��run����д�����û��Ч����
	
	//��Ϊ��ߵ�run�����Ͳ���Ӧ  �÷���Tank���ڵ��Ǹ�run���вſ���
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
		while(door <4)
		{
			System.out.println("����while"+tankversion.eet.size());
			if(tankversion.eet.size() ==0)
			{
				System.out.println(tankversion.eet.size());
				tankversion.SetStage(tankversion.door);
//				if(door == 1){
				//if(ee)
				if(this.mp3 != null)
				{
					System.out.println("�Ƿ�����mp3");
					this.remove(mp3);
					
				}
				if(this.msp !=null)
				{
					this.remove(msp);
				}
				msp = new MyStartPanel();
//					door++;
				//�����������
				//���ӿ�ʼ���
				this.add(msp);
				//�ü������
				this.setVisible(true);
				//����һ���̲߳���ʼ
				Thread t1 =new Thread(msp);
				t1.start();
			}

		}/*else if(door == 2){
				
			}else if(door == 3){
				
			}*/
			//Ӧ�÷������  ����Ӧ�÷���ÿһ�ε�����̹�˽����ĵط�   Ȼ������door�������ж�
//			door++;
//			if(recorddata.getKillenNum() == tankversion.enSize){
//				tankversion.door++;
//			}

	}
}

//�¶���һ����ʼ���
class MyStartPanel extends JPanel implements Runnable
{
	int times = 0 ;//���������ػ��Ĵ�����
	public void paint(Graphics g)
	{
		
		
		super.paint(g); //���Ǽ̳�JPanel�ĵ�һ��
		g.fillRect(0,0,420,400);
		if(times%2 == 0 )
		{
		g.setColor(Color.yellow);
		Font newFont = new Font("������κ",Font.BOLD,30);
		g.setFont(newFont);
		// ���һ�θĽ��ĵط������ؿ�������
		g.drawString("Stage: "+tankversion.door, 160, 180);
		}
		if(times > 1000)
		{
			times = 0; //������һ��ɵ�� һֱ�ٿ���ɵ�ƶ�������Ϸ   ���Բ������� times һֱ������
		}
	
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			try 
			{
				Thread.sleep(500);
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//������˯��һ��    Ȼ�����ػ���   ����һ������
			times++;
			this.repaint();
		}
	}
}
//////////////////////////////////////////////////////////////////////
//����һ���������(��hero.life == -1ʱ     ����hero�������ٵĵط�)
class MyEndPanel extends JPanel implements Runnable
{
	int times = 0 ;//���������ػ��Ĵ�����
	public void paint(Graphics g)
	{
		super.paint(g); //���Ǽ̳�JPanel�ĵ�һ��
		g.fillRect(0,0,420,400);
		if(times%2 == 0 )
		{
			g.setColor(Color.yellow);
			Font newFont = new Font("������κ",Font.BOLD,30);
			g.setFont(newFont);
			g.drawString("��Ϸ�Ѿ�����\n�����������������Ϸ��", 160, 180);
		}
		if(times > 1000)
		{
			times = 0; //������һ��ɵ�� һֱ�ٿ���ɵ�ƶ�������Ϸ   ���Բ������� times һֱ������
		}
	
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) 
			{
			// TODO Auto-generated catch block
				e.printStackTrace();
			}//������˯��һ��    Ȼ�����ػ���   ����һ������
				times++;
				this.repaint();
		}
	}
}




///������嶨�����
/////////////////////////////////////////////////////////////////
//����һ��ͨ����壨��door==4ʱ��
class MyCongratulationPanel extends JPanel implements Runnable
{
	int times = 0 ;//���������ػ��Ĵ�����
	public void paint(Graphics g){
	super.paint(g); //���Ǽ̳�JPanel�ĵ�һ��
	g.fillRect(0,0,420,400);
	if(times%2 == 0)
	{
		g.setColor(Color.yellow);
		Font newFont = new Font("������κ",Font.BOLD,30);
		g.setFont(newFont);
		g.drawString("��ϲ��ͨ����!\n������ɽ���׼ҵ�����\n˳��ȡ��������  \n��һ����������", 160, 180);
	}
	if(times > 1000)
	{
		times = 0; //������һ��ɵ�� һֱ�ٿ���ɵ�ƶ�������Ϸ   ���Բ������� times һֱ������
	}

}


	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			try 
			{
				Thread.sleep(500);
			} catch (InterruptedException e)
			{
			// TODO Auto-generated catch block
				e.printStackTrace();
			}//������˯��һ��    Ȼ�����ػ���   ����һ������
				times++;
				this.repaint();
			}
		}
	}



///ͨ����嶨����� 

/// ����Ĺؿ���ʼ����������Ҫ  ���޸Ĳ��֣����������һ�Σ� 
//  ���⻹��tankversion.enSize


//����һ������ ������Tank����
class MyPanel2 extends JPanel implements KeyListener,Runnable
{
	
	
	//С����  ���ƴ���Tank1�Ķ����������һ��
	Hero zl = null;   //һ���������ж���  �ڹ��캯���д���  ����  ������������ԣ�  ��JFrame�಻ͬ���� ����Ҫ��ʾ
	//��ʼ��JPanel�Ļ�ͼ����paint  �൱�ڻ��һ������g
//	int[] xaxis = {zl.bl.x,zl.bl.x+3/2,zl.bl.x+3};
//	int[] yaxis = {zl.bl.y,zl.bl.y+3,zl.bl.y};
//	int[] xaxis = {3,4,10};
//	int[] yaxis = {10,5,10};
	/*
	 * ���ڸո�ѧ���ļ���IO��̣� ���������һ��
	 * */



	
	
	Vector<bome> bomes = new Vector<bome>();
	
	//��������ͼƬ
	BufferedImage im1 = null;
	BufferedImage im2 = null;
	BufferedImage im3 = null;  //�����ıߴ����أ�  ��Ӧ����c�����е�    �����Ͷ����˼��   ��Ӧ����  ����ʹ�����˼��
	 
	public void paint(Graphics g)
	{
		super.paint(g);
		
		//һ�㶼����paint�����н��л���ͼ����Ķ���
		
		//��ʼ��MyPanel��Paint���������ҵ�̹��
			//����̹��  Ȼ������װ��һ������
		//�Ȼ���߾���
		
		//������ɫ����Ϊ��ɫ��Ҫ��ɫ�� ���ÿ���
		g.fillRect(0, 0, 420, 400);//Ĭ������Ǻ�ɫ�ı���   ��JFrameĬ���ǻ�ɫ�ı���
	
//��ʾ������Ϣ
		//��ʾ��Ϣ�ĵ���̹��     ��װ������˼�����԰��⼸�з�װ��һ��showinfo��Ϣ
		showinfo(g);
		
		//�����ӵ�  //������� zl.bl!=nulll    �Ҿ���̫����ˣ�����Ȼ�ᱬ����ָ�����⣡
		//������������»��ǲ��ܵ���  zl.getX()  ��zl.getY()  �������Ҳ������
		for(int i =0;i<zl.vt.size();i++)
		{
			if(zl.vt.get(i)!=null &&zl.vt.get(i).isLive ==true)
			{
				drawbullet(zl.vt.get(i).x,zl.vt.get(i).y,g,zl.vt.get(i).getDirection(),0);
//				System.out.println(zl.vt.get(i)+"�ӵ���ǰ����"+zl.vt.get(i).y);
			}
			//if(zl.vt.get(i).isLive == false)//�Ϳ��԰���������ɾ���ˣ�
		}



		//��������̹��   ��ͬ�Ķ��������¼����ã�
//		if(zl.bl.isLive == true){
//			drawTank(zl.getX(),zl.getY(),g,zl.getDirect(),zl.getType());
//		}
		if(zl.isLive)
		{
			drawTank(zl.getX(),zl.getY(),g,zl.getDirect(),zl.getType());
		}
		//���ǰ���߽����޸���  �㲻���϶���ĳ���ط����ֲ�����  ����Ƿ��� ����direction  ������ٶȾ���speed 
		//��������;���type
		//����һ����װ��ֻ��Ҫ����ߵ�4 5����λ �ı�����direction��type �Ϳ����޸���ɫ �����ˣ�
//		drawbullet(zl.getX(),zl.getY(),g,zl.bl.getDirection());
		//�����л�
		//�½������̸߳��˵л���
		
	//���������  eet��û��ֵ Ϊʲô��ֵ��
		for(int i = 0;i<tankversion.eet.size();i++)
		{
			//System.out.println(tankversion.eet.size());
			EnemyTank temp =tankversion.eet.get(i);
		
//			temp.setDirect(2);  //��������˷���̶��϶���һֱ��
			temp.setType(1);
			if(temp.isLive == true)
			{
				drawTank(temp.getX(),temp.getY(),g,temp.getDirect(),temp.getType());
	//			temp.shootHero();  //���ڸ�̹��װ��ը����  �Ŵ��ط���  ����ǻ��� ����Ҫ���ڹ��캯����
					//ȡ���ӵ�
					for(int j = 0 ; j<temp.ss.size(); j++)
					{
						bullet tempbl = temp.ss.get(j);
						if(tempbl.isLive)
						{ //�����ӵ�
							drawbullet(tempbl.x,tempbl.y,g,tempbl.getDirection(),1);
	//						System.out.println("��"+(i)+"��̹�˵ĵ�"+(j+1)+"���ӵ���"+"�����ǡ�����"+tempbl.x+" y ="+tempbl.y);
						//ͨ�� ���Բ鿴ÿһ��̹�˵ڼ����ӵ�������[]   �������һ���ö�û�У�
							//���飺	
						}else
						{
							//���ӵ���ɾ�� ������ʱ��͵����  ���ſӸ�������
	//						eet.remove(tempbl);//����ɾ��̹��  Ӧ����ɾ���ӵ�
							temp.ss.remove(tempbl);  //��������仰�������Ч��
						}
						
					}
			}
//			System.out.println("�л�����"+temp.getDirect());//����޷������ж�
//			}
			//��Ӧ������߷����̣߳�  Ӧ���ڹ��캯����
//			Thread t = new Thread(temp);
//			t.start(); 
			
//			

		}
		
//		//���з��ӵ�
//		for(int  i=0 ; i<eet.size(); i++){
//			EnemyTank temp = eet.get(i);
//			for(j = 0 ; i < eet.get(i))
//		}
		
		//��ʼ��ը��
		for(int i = 0 ; i<bomes.size() ; i++)
		{
			//ȡ��ը��
			System.out.println("ը���������ǣ�"+bomes.lastElement()+"bomes.size = "+bomes.size());  //���Կ����ǲ���ը����û����
			bome b1 = bomes.get(i);
			if(b1.life >10)
			{
				
				g.drawImage(im1, b1.x, b1.y, 30, 30, this);
			}else if(b1.life >5)
			{
				g.drawImage(im2, b1.x, b1.y, 30, 30, this);
			}else{
				g.drawImage(im3, b1.x, b1.y, 30, 30, this);
			}
			b1.lifedown();
			//���ը��������ֵΪ0  ���ը����������ȥ��
			if(b1.life == 0)
			{
//				b1.isLive = false;
				bomes.remove(b1);
			}
		}
		
		//����implements�ӿڵķ�ʽʵ���߳�  Ҫ��ͨ���̳еķ�ʽ�Ļ��в�ͨ  ��Ϊ����Enemytank��extends̹����  java�ǵ��̳�����
		//��ʱ�Ȳ����߳̿���  ʵ��һ���Ƿ�����ӵ�����Ŀ��
/*		Thread t3 =new Thread(eet.get(0));
		Thread t4 =new Thread(eet.get(1));
		Thread t5 =new Thread(eet.get(2));
		t3.start();
		t4.start();
		t5.start();*/
		
	}
	
	//��ʾ��Ϣ��װ����
	public void showinfo(Graphics g)
	{
		drawTank(70,420,g,0,1);
		
//		g.drawString(recorddata.getEnNum(),100,420); //ת��ΪString����
		g.setColor(Color.black);
		g.drawString(recorddata.getEnNum()+" ",100,440);
		//������ʾ�ѷ�̹�˵���Ϣ
		drawTank(130,420,g,0,0);
		g.setColor(Color.black);//��Ȼ�ٻ�����̹�˵�ʱ���Ѿ��ı��˻�����ɫ
		g.drawString(recorddata.getMylife()+" ",160,440);
		
		
		
		//������������ұ߻�����ҵ��ܳɼ�
		Font font = new Font("����",Font.BOLD,20);
		g.setFont(font);
		g.drawString("���ϵ��ܳɼ���", 420, 100);
		
		drawTank(440, 140, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(recorddata.getKillenNum()+"", 470, 165);
	}
	//�����ӵ�
	//���÷�ʽ �� drawbullet(zl.bl.x,zl.bl.y,g,0/1/2/3);

	public void drawbullet(int x,int y,Graphics g,int direction,int type)
	{
		//�����ӵ�
//		int[] xaxis = null;
//		int[] yaxis = null;
//		if(zl.bl!= null){
		switch(type)
		{
			case 0: g.setColor(Color.RED);
			break;
			case 1: 
				 g.setColor(Color.blue);
		}
//			g.setColor(Color.red);
//			g.draw3DRect(x, y, 3, 3, false);
		
//			g.drawPolygon(xaxis, yaxis, 3); }
		//��һ��˼· �������㻭�������߶β����ǿ������
		//��һ����
		switch(direction)
		{
			case 0:
			{
				g.draw3DRect(x, y, 2, 4, false);
				g.drawLine(x, y, x+2, y);
				g.drawLine(x, y, x+1, y-2);
				g.drawLine(x+1, y-2, x+2, y);
			}
			break;
			case 1:
			{
				g.draw3DRect(x, y, 4, 2, false);
				g.drawLine(x+4, y, x+4, y+2);
				g.drawLine(x+4, y, x+6, y+1);
				g.drawLine(x+6, y+1, x+4, y+2);
			}
			break;
			case 2:
			{
				g.draw3DRect(x, y, 2, 4, false);
				g.drawLine(x, y+4, x+2, y+4);
				g.drawLine(x, y+4, x+1, y+6);
				g.drawLine(x+1, y+6, x+2, y+4);
			}
			break;
			case 3:
			{
				g.draw3DRect(x, y, 4, 2, false);
				g.drawLine(x, y, x-2, y+1);
				g.drawLine(x, y, x, y+2);
				g.drawLine(x, y+2, x-2, y+1);
			}
			break;
		}
//		}
	}
	/**
	 * @param x
	 * @param y
	 * @param g
	 * @param direction
	 * @param type
	 */
	public void drawTank(int x, int y,Graphics g,int direction,int type)
	{
	    //�ж���ʲô��Ӫ��̹��
		switch(type)
	     {
	    	 case 0:
	    		 g.setColor(Color.RED);//�ҷ�̹����ɫ
	    		 break;
	    	 case 1:
	    		 g.setColor(Color.yellow);//�ط�̹����ɫ
	    		 break;
	     }
	     switch(direction){
	     //���ϣ�
	     case 0: //����
	     {
	    	 
	    	 g.fill3DRect(x,y, 5, 30,false);//����ִ�
	 		 g.fill3DRect(x+15, y, 5, 30,false); //�ұߵ��ִ�
	 		 g.fill3DRect(x+5, y+5, 10, 20,false);//����
	 		 g.fillOval(x+5, y+10, 10, 10); //����Բ��
	 		 g.drawLine(x+10, y+15, x+10, y);//�������
	     }
	     break;
	     case 1: //����
	     {
	    	 g.fill3DRect(x,y, 30, 5,false);//�ϱ��ִ�
	 		 g.fill3DRect(x, y+15, 30, 5,false); //�±ߵ��ִ�
	 		 g.fill3DRect(x+5, y+5, 20, 10,false);//����
	 		 g.fillOval(x+10, y+5, 10, 10); //����Բ��
	 		 g.drawLine(x+15, y+10, x+30, y+10);//�������
	     }
	     break;
	     case 2://����
	     {
	    	 g.fill3DRect(x,y, 5, 30,false);//����ִ�
	 		 g.fill3DRect(x+15, y, 5, 30,false); //�ұߵ��ִ�
	 		 g.fill3DRect(x+5, y+5, 10, 20,false);//����
	 		 g.fillOval(x+5, y+10, 10, 10); //����Բ��
	 		 g.drawLine(x+10, y+15, x+10, y+30);//�������
	     }
	     break;
	     case 3: //����
	     {
	    	 g.fill3DRect(x,y, 30, 5,false);//�ϱ��ִ�
	 		 g.fill3DRect(x, y+15, 30, 5,false); //�±ߵ��ִ�
	 		 g.fill3DRect(x+5, y+5, 20, 10,false);//����
	 		 g.fillOval(x+10, y+5, 10, 10); //����Բ��
	 		 g.drawLine(x+15, y+10, x, y+10);//�������
	     }
	     break;
	     
	     }
		}
	
	public void HitEnemyTank()
	{
		//�������forѭ����Ŀ�����ڿ��� ���� isLive ����  Ȼ�󴫵ݸ�drawTank����  ˭��islive �ӵ��ĺ͵���̹�˵�
		for(int i = 0 ; i < zl.vt.size() ; i++)
		{
			//ȡ���ӵ�
			bullet bt = zl.vt.get(i);
			//�ж��ӵ��Ƿ���
			if(bt.isLive == true)
			{
				for(int j = 0 ; j < tankversion.eet.size() ; j++) //��Ҫд�� i++�� �õĵ���̹�����Ƕ�����Mypanel��
				{
					//ȡ��̹��
					EnemyTank em = tankversion.eet.get(j); //jд����  i ����
					//�ж�̹���Ƿ�����
					if(em.isLive == true)
					{
						hitTank(bt,em);
					}
				}
			}
		}
	}
//ִ��hitme ����
	public void HitHeroTank(){
		for(int i = 0 ; i < tankversion.eet.size(); i++)
		{
			//ȡ��̹��
			EnemyTank temp = tankversion.eet.get(i);
			//ѭ��ȡ������̹�˵��ӵ�
			if(temp.isLive)
			{
				for(int j = 0 ; j < temp.ss.size(); j++)
				{
					bullet bl = temp.ss.get(j);
					if(bl.isLive)
					{
						if(zl.isLive)
						{
							hitme(bl,zl);
						}
					}
				}
			}
		}
	}
	public MyPanel2()
	{
		

	/*	
//		this.setJMenuBar(jmb)  �������JPanel����  ��Ϊ���̳�JFrame
		
		zl = new Hero(200, 300,0);  //�����ʵ��ԭʼ��x y�����õ� ����һֱ�������� zl.getX zl.getY������
		//��ʼ���з���ս��    //  ���˺þ������ҵ���
		if(recorddata.flags.equals("newgame"))
		{
//			if(eet.size()!=0){
//				System.out.println(eet.size());
//				eet.removeAllElements();
//				System.out.println(eet.size());
//			}
		for(int i=0;i<enSize; i++)
		{
			EnemyTank ent = new EnemyTank((i+1)*50,10,2);
			//������������̷߳���paint������  �ᵼ���߳�һֱ�����¿���   ���Լǵÿ����߳�һ�����ڹ��캯����
			Thread t = new Thread(ent);
			t.start(); 
			bullet bl = null;
			//���ڰ��⼸�зŵ�run������ ��Ȼ���ֻ�ܾ��õط�����һ���ӵ�   ���캯��ֻ�ܹ���һ��  ��������Ҫ���ϵ�run
			switch(ent.direct){
			case 0:
			{
				bl = new bullet(ent.x+10,ent.y,0);
			}
			break;
			case 1:
			{
				bl = new bullet(ent.x+30,ent.y+10,1);
			}
			break;
			case 2:
			{
				bl = new bullet(ent.x+10,ent.y+30,2);
			}
			break;
			case 3:
			{
				bl = new bullet(ent.x,ent.y+10,3);
			}
			break;
			}
//			bullet bl = new bullet(ent.x,ent.y,ent.direct);
			//�������̹��
		    ent.ss.add(bl);
		     //һ��Ҫ�Լ������ӵ��߳�   ��Ϊ�����ǲ�������ģ����ӵ��Լ���ȥ�ɣ�
		    Thread t1 = new Thread(bl);
		    t1.start();
			
			//��������ÿһ��Enemy�ԣ���
			ent.setEts(eet);
			recorddata.setEnems(eet);
			eet.add(ent);
//			ent.shootHero();
			//������̹������һ���ӵ�
		}
			
			
		}else if(recorddata.flags.equals("load"))
		{
			 //�л���ʼ�����   �Ǿ͵ûص�print��������������
			
			recorddata.RecordRead();
			System.out.print(recorddata.nodes.size() );
		  for (int i = 0; i <recorddata.nodes.size() ; i++)
		  {
			  TankM node=recorddata.nodes.get(i);
		    EnemyTank enemy = new EnemyTank(node.x, node.y,node.direct);
//		    enemy.setDirect(node.direct);
		    //����EnemyTank Ӧ�ý������崫�͵�EnemyTank  
		    enemy.setEts(eet); //���Ҫ�����ʵ��޸�
		    //����GameRecord
		    recorddata.setEnems(eet);
		    Thread th=new Thread(enemy);
		    th.start();
		    this.eet.add(enemy);
		  }

		}
		
		//��ʼ��ͼƬ ����ͼƬ
		im1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		im2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		im3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
		try {
			im1 = ImageIO.read(new File("./images/bomb_1.gif"));
			im2 = ImageIO.read(new File("./images/bomb_2.gif"));
			im3 = ImageIO.read(new File("./images/bomb_3.gif"));  //��Ȼ�������� ��ͼƬ��images��
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hitTank(bullet bl, EnemyTank em)
	{
		switch(em.getDirect()){
		case 0:
		case 2:
		{
			if(bl.x>em.x && bl.x<em.x+20 && bl.y>em.y &&bl.y<em.y+30)
			{
				//����
				//�ӵ�����
				bl.isLive = false;
				//����̹������
				em.isLive = false;
				
				
		//����Ҳ������ɥ��  ��������ô�ڼ������Ҳ�������Ϸ⽨����
				recorddata.reduceenNum();
				recorddata.addkillnum();
				//���̹������  ���ǿ�ʼ��������
				//����һ��ը������
				bome b1 = new bome(em.x,em.y);
				//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
				bomes.add(b1);
			}
		
		}
		break;
		case 1:
		case 3:
		{
			//�������if(bl.x>em.x && bl.x<em.x+30 && bl.y>0 &&bl.y<em.y+20)
			if(bl.x>em.x && bl.x<em.x+30 && bl.y>em.y &&bl.y<em.y+20)
			{ 
				//����
				//�ӵ�����
				bl.isLive = false;
				//����̹������
				em.isLive = false;
				
		//����Ҳ������ɥ��  ��������ô�ڼ������Ҳ�������Ϸ⽨����
				recorddata.reduceenNum();
				recorddata.addkillnum();
				//���̹������  ���ǿ�ʼ��������
				//����һ��ը������
				bome b1 = new bome(em.x,em.y);
				//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
				bomes.add(b1);
			}
		}
		}
	}
	
	//�жϵ��˵��ӵ��Ƿ�������    ���ñ�ը��Χ�� ����������ֵ��
	public void hitme(bullet bl,Hero em)
	{
		switch(em.getDirect())
		{
			case 0:
			case 2:
			{
				if(bl.x>em.x&&bl.x<em.x+20&&bl.y>em.y&&bl.y<em.y+30) //em.y+5����д����5��Ȼ����bug��
				{
					//����
					//�ӵ�����
					bl.isLive = false;
					//����̹������
					em.lifedown(); //�����������ǻ����ը��Ч����
					System.out.println(bl.x+"zidan vs em"+em.x+"������"+bl.y+"zidan vs em"+em.y);
					//���̹������  ���ǿ�ʼ��������
					//����һ��ը������
			//����Ҳ������ɥ��  ��������ô�ڼ������Ҳ�������Ϸ⽨����
					recorddata.reduceMylife();
					bome b1 = new bome(em.x,em.y);
					//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
					bomes.add(b1);
				}
			
		}
		break;
		case 1:
		case 3:
		{
			if(bl.x>(em.x)&&bl.x<(em.x+30)&&bl.y>(em.y)&&bl.y<em.y+20) //em.y+5����д����5��Ȼ����bug��
			//�Ҿ�Ȼ�������ǲ��� �ո��Ӱ�켸��&&��ִ�У�  
			{
				//����
				//�ӵ�����
				bl.isLive = false;
				//����̹������
				em.lifedown();
				//����Ҳ������ɥ��  ��������ô�ڼ������Ҳ�������Ϸ⽨����
				recorddata.reduceMylife();
				
				System.out.println(bl.x+"zidan vs em"+em.x+"������"+bl.y+"zidan vs em"+em.y);
				//���̹������  ���ǿ�ʼ��������
				//����һ��ը������
				bome b1 = new bome(em.x,em.y);
				//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
				bomes.add(b1);
			}
		}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	//̹�˿������������ƶ���  w��  a�� s�� d��
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		//����ǰ���˳ʱ����б�д  �Ƚϲ�����ң�  //�ڼ�������ͣ�����ø�ֵ
		if(e.getKeyCode() == KeyEvent.VK_S&&Hero.isIsTurn())
		{
			zl.setY(zl.getY()+1);
			zl.setDirect(2);
			
//			System.out.println("�㰴�������µķ������");
		}else if(e.getKeyCode() == KeyEvent.VK_W&&Hero.isIsTurn())
		{
//			System.out.println("�㰴�������ϵķ������");
			zl.setDirect(0);
//			zl.setY(zl.y-1);
			zl.moveup();
		}else if(e.getKeyCode() == KeyEvent.VK_A&&Hero.isIsTurn())
		{
//			System.out.println("�㰴��������ķ������");
			zl.setDirect(3);
			zl.setX(zl.getX()-1);
		}else if(e.getKeyCode() == KeyEvent.VK_D&&Hero.isIsTurn())
		{
//			System.out.println("�㰴�������ҵķ������");
			zl.setDirect(1);
			zl.setX(zl.getX()+1);
		}//else if�ı׶˾����ж���һ��֮��Ͳ��жϱ����  ������Ҫ�ı䣡 ��������һ��if
		
		if(e.getKeyCode() == KeyEvent.VK_J)
		{
			if(zl.vt.size() <= 5 &&zl.isLive)
			{  //�����Ľ���� ���Ǳ�����6���ӵ� �����ٷ������� �����ٰ��ӵ��и������
				zl.shootenemy();	
			}
		}
		this.repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_J)
		{  //��ȻҲ�ǿ��԰����Ž�paint ������Ҳ�ǿ��Ե�  ����ʦ���Ƿ����Ǳߣ�
//			zl.vt = null;  
			for(int i = 0 ;i<zl.vt.size(); i++)
			{
				if(zl.vt.get(i).isLive == false)
				{  //�����Ϳ���ʵ�ֲ��ϵ�����  Ȼ���ٻ��У�
					zl.vt.remove(i); //���������Ľ�����ӵ���û��ɱ���أ�  ���Եò�Ӱ��ǰ����ӵ� 
				//�϶��������
				}
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);  //��߿�������̹�˵ĳ��ּ��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.HitEnemyTank();
			
			this.HitHeroTank();
			
			
			//�������forѭ����Ŀ�����ڿ��� ���� isLive ����  Ȼ�󴫵ݸ�drawTank����  ˭��islive �ӵ��ĺ͵���̹�˵�
			for(int i = 0 ; i < eet.size() ; i++){ //������ߵ��ӵ�Ӧ���ǵз��ӵ�
				//ȡ���з�̹��
				EnemyTank em = eet.get(i);
				
				if(em.isLive == true){
					//ȡ���з��ӵ�
					for(int j = 0 ; j < em.embt.)
				}
				bullet bt = zl.vt.get(i);
				//�ж��ӵ��Ƿ���
				if(bt.isLive == true){
//					for(int j = 0 ; j < eet.size() ; j++) //��Ҫд�� i++�� �õĵ���̹�����Ƕ�����Mypanel��
//					{
//						//ȡ��̹��
//						EnemyTank em = eet.get(j); //jд����  i ����
//						//�ж�̹���Ƿ�����
						if(zl.isLive == true){
							hitme(bt,zl);
						}
					}
			}
			//�ҵ�������ߵıȽ�Ư��
			//�ж��Ƿ�ø�����̹�˳�ʼ���ӵ��ͼ��ӵ�
			bullet bl = null;
			for(int i = 0 ; i< eet.size() ; i++){
			//���ڰ��⼸�зŵ�run������ ��Ȼ���ֻ�ܾ��õط�����һ���ӵ�   ���캯��ֻ�ܹ���һ��  ��������Ҫ���ϵ�run
			EnemyTank ent = eet.get(i);
			if(ent.isLive){
			 if(ent.ss.size()<5){  //[���Ƶ���̹���Ƿ����������]
				switch(ent.direct){
			case 0:
			{
				bl = new bullet(ent.x+10,ent.y,0);
				ent.ss.add(bl);
			}
			break;
			case 1:
			{
				bl = new bullet(ent.x+30,ent.y+10,1);
				ent.ss.add(bl);
			}
			break;
			case 2:
			{
				bl = new bullet(ent.x+10,ent.y+30,2);
				ent.ss.add(bl);
			}
			break;
			case 3:
			{
				bl = new bullet(ent.x,ent.y+10,3);
				ent.ss.add(bl);
			}
			break;
			}
			 }
			}  //ֻ�Ӳ�����һ����ϰ��   ����ʲô�ط������ͷ�  һ�������paint������
//			bullet bl = new bullet(ent.x,ent.y,ent.direct);
			//�������̹��
		    
		     //һ��Ҫ�Լ������ӵ��߳�   ��Ϊ�����ǲ�������ģ����ӵ��Լ���ȥ�ɣ�
		    Thread t1 = new Thread(bl);
		    t1.start();
			
			}
			
			
			this.repaint();
		}

		
	}*/
		//�������ʼ����
				AePlayWave apw = new AePlayWave("./images/111.wav");
				apw.start();
				
				zl = new Hero(200, 300,0);  //�����ʵ��ԭʼ��x y�����õ� ����һֱ�������� zl.getX zl.getY������
				//��ʼ���з���ս��    //  ���˺þ������ҵ���
				if(recorddata.flags.equals("newgame"))
				{
					//���һ���¸Ľ��ĵط�
					for(int i=0;i<tankversion.enSize; i++)
					{
						EnemyTank ent = new EnemyTank((i+1)*50,10,2);
						//������������̷߳���paint������  �ᵼ���߳�һֱ�����¿���   ���Լǵÿ����߳�һ�����ڹ��캯����
						Thread t = new Thread(ent);
						t.start(); 
						
						//��������ÿһ��Enemy�ԣ���
						ent.setEts(tankversion.eet);
						recorddata rd = new recorddata();
						
						rd.setEnems(tankversion.eet);
						tankversion.eet.add(ent);
	//					ent.shootHero();
						//������̹������һ���ӵ�
					}
					
					
				}else if(recorddata.flags.equals("load"))
				{
					 //�л���ʼ�����   �Ǿ͵ûص�print��������������


					
//					recorddata.RecordRead();  //�������̹����Ŀ�ἱ�����ӣ�
					System.out.print(recorddata.nodes.size() );
//					for (int i = 0; i <recorddata.nodes.size()%5 ; i++) ����취����û��
					for (int i = 0; i <recorddata.nodes.size() ; i++)
				  {
					  Node node=recorddata.nodes.get(i);
				    EnemyTank enemy = new EnemyTank(node.x, node.y,node.direct);
//				    enemy.setDirect(node.direct);
				    //����EnemyTank Ӧ�ý������崫�͵�EnemyTank  
				    enemy.setEts(tankversion.eet); //���Ҫ�����ʵ��޸�
				    //����GameRecord
				    recorddata rd = new recorddata();
				    rd.setEnems(tankversion.eet);
				    Thread th=new Thread(enemy);
				    th.start();
				    tankversion.eet.add(enemy);
				  }

				}
				//�������������һ���ϵ�  ����new��ʱ���������size=0   ��û��load��ʱ��ͱ��12�ˣ�  Ϊʲô
				System.out.println("new��ߣ�"+recorddata.nodes.size());

				try 
				{
					im1 = ImageIO.read(new File("./images/bomb_1.gif"));
					im2 = ImageIO.read(new File("./images/bomb_2.gif"));
					im3 = ImageIO.read(new File("./images/bomb_3.gif"));  //��Ȼ�������� ��ͼƬ��images��
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public void hitTank(bullet bl, EnemyTank em){
				switch(em.getDirect()){
				case 0:
				case 2:
				{
					if(bl.x>em.x && bl.x<em.x+20 && bl.y>em.y &&bl.y<em.y+30){
						//����
						//�ӵ�����
						bl.isLive = false;
						//����̹������
						em.isLive = false;
						
						
				//����Ҳ������ɥ��  ��������ô�ڼ������Ҳ�������Ϸ⽨����
						recorddata.reduceenNum();
						recorddata.addkillnum();
						//���̹������  ���ǿ�ʼ��������
						//����һ��ը������
						bome b1 = new bome(em.x,em.y);
						
						AePlayWave apw = new AePlayWave("./images/ը��.wav");
						apw.start();
						//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
						bomes.add(b1);
					}
				
				}
				break;  //һ��Ҫ�ǵü���break
				case 1:
				case 3:
				{
					//�������if(bl.x>em.x && bl.x<em.x+30 && bl.y>0 &&bl.y<em.y+20)
					if(bl.x>em.x && bl.x<em.x+30 && bl.y>em.y &&bl.y<em.y+20){ 
						//����
						//�ӵ�����
						bl.isLive = false;
						//����̹������
						em.isLive = false;
						
				//����Ҳ������ɥ��  ��������ô�ڼ������Ҳ�������Ϸ⽨����
						recorddata.reduceenNum();
						recorddata.addkillnum();
						//���̹������  ���ǿ�ʼ��������
						//����һ��ը������
						bome b1 = new bome(em.x,em.y);
						AePlayWave apw = new AePlayWave("./images/ը��.wav");
						apw.start();
						//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
						bomes.add(b1);
					}
				}
				}
				// �ؿ�����  ���Ա����������  ����������м���̣� ��������ʾ��ϲ�˵�
				//System.out.println(recorddata.getKillenNum());
				if(recorddata.getKillenNum() == tankversion.enSize){
					//System.out.println("�պ������");
					tankversion.door++;
					//System.out.println(tankversion.door);
					
					
				}
			}
			
			//�жϵ��˵��ӵ��Ƿ�������    ���ñ�ը��Χ�� ����������ֵ��
			public void hitme(bullet bl,Hero em)
			{
				switch(em.getDirect())
				{
				case 0:
				case 2:
				{
					if(bl.x>em.x&&bl.x<em.x+20&&bl.y>em.y&&bl.y<em.y+30) //em.y+5����д����5��Ȼ����bug��
					{
						//����
						//�ӵ�����
						bl.isLive = false;
						//����̹������
						em.lifedown(); //�����������ǻ����ը��Ч����
						System.out.println(bl.x+"zidan vs em"+em.x+"������"+bl.y+"zidan vs em"+em.y);
						//���̹������  ���ǿ�ʼ��������
						//����һ��ը������
				//����Ҳ������ɥ��  ��������ô�ڼ������Ҳ�������Ϸ⽨����
						recorddata.reduceMylife();
						bome b1 = new bome(em.x,em.y);
						AePlayWave apw = new AePlayWave("./images/ը��.wav");
						apw.start();
						//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
						bomes.add(b1);
					}
				
				}
				break;
				case 1:
				case 3:
				{
					if(bl.x>(em.x)&&bl.x<(em.x+30)&&bl.y>(em.y)&&bl.y<em.y+20) //em.y+5����д����5��Ȼ����bug��
					//�Ҿ�Ȼ�������ǲ��� �ո��Ӱ�켸��&&��ִ�У�  
					{
						//����
						//�ӵ�����
						bl.isLive = false;
						//����̹������
						em.lifedown();
						//����Ҳ������ɥ��  ��������ô�ڼ������Ҳ�������Ϸ⽨����
						recorddata.reduceMylife();
						
						System.out.println(bl.x+"zidan vs em"+em.x+"������"+bl.y+"zidan vs em"+em.y);
						//���̹������  ���ǿ�ʼ��������
						//����һ��ը������
						bome b1 = new bome(em.x,em.y);
						AePlayWave apw = new AePlayWave("./images/ը��.wav");
						apw.start();
						//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
						bomes.add(b1);
					}
				}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) 
			{
				// TODO Auto-generated method stub
				
			}
			@Override
			//̹�˿������������ƶ���  w��  a�� s�� d��
			public void keyPressed(KeyEvent e)
			{
				// TODO Auto-generated method stub
				//����ǰ���˳ʱ����б�д  �Ƚϲ�����ң�  //�ڼ�������ͣ�����ø�ֵ
				if(e.getKeyCode() == KeyEvent.VK_S&&Hero.isCanTurn())
				{
					zl.setY(zl.getY()+1);
					zl.setDirect(2);
					AePlayWave apw = new AePlayWave("./images/̹�˾���.wav");
					apw.start();
//					System.out.println("�㰴�������µķ������");
				}else if(e.getKeyCode() == KeyEvent.VK_W&&Hero.isCanTurn())
				{//���isIsTurnΪ���򲻿����ð���������
//					System.out.println("�㰴�������ϵķ������");
					zl.setDirect(0);
//					zl.setY(zl.y-1);
					zl.moveup();
					AePlayWave apw = new AePlayWave("./images/̹�˾���.wav");
					apw.start();
				}else if(e.getKeyCode() == KeyEvent.VK_A&&Hero.isCanTurn())
				{
//					System.out.println("�㰴��������ķ������");
					zl.setDirect(3);
					zl.setX(zl.getX()-1);
					AePlayWave apw = new AePlayWave("./images/̹�˾���.wav");
					apw.start();
				}else if(e.getKeyCode() == KeyEvent.VK_D&&Hero.isCanTurn())
				{
//					System.out.println("�㰴�������ҵķ������");
					zl.setDirect(1);
					zl.setX(zl.getX()+1);
					AePlayWave apw = new AePlayWave("./images/̹�˾���.wav");
					apw.start();
				}//else if�ı׶˾����ж���һ��֮��Ͳ��жϱ����  ������Ҫ�ı䣡 ��������һ��if
				
				if(e.getKeyCode() == KeyEvent.VK_J)
				{
					if(zl.vt.size() <= 5 &&zl.isLive)
					{  //�����Ľ���� ���Ǳ�����6���ӵ� �����ٷ������� �����ٰ��ӵ��и������
						zl.shootenemy();
					}
				}
				this.repaint();
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_J)
				{  //��ȻҲ�ǿ��԰����Ž�paint ������Ҳ�ǿ��Ե�  ����ʦ���Ƿ����Ǳߣ�
//					zl.vt = null;  
					for(int i = 0 ;i<zl.vt.size(); i++)
					{
					if(zl.vt.get(i).isLive == false)
					{  //�����Ϳ���ʵ�ֲ��ϵ�����  Ȼ���ٻ��У�
						zl.vt.remove(i); //���������Ľ�����ӵ���û��ɱ���أ�  ���Եò�Ӱ��ǰ����ӵ� 
					//�϶��������
					}
					}
				}
			}
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				while(true)
				{
					try {
						Thread.sleep(50);  //��߿�������̹�˵ĳ��ּ��
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					this.HitEnemyTank();
					
					this.HitHeroTank();
					
					this.repaint();
				}

				
			}
} 


//�������
class Help extends JFrame
{
	JList jl=null;
	public Help()
	{
		String s[]={"����---S","����---W","����---A","����---D","����---J"};
		jl=new JList(s);
		//��ʾ����
		//jl.setVisibleRowCount(3);
		this.add(jl);
		this.setSize(200, 200);
		this.setVisible(true);
	}
}


//����ɹ�����
class  ReSuccess extends JFrame
{
	JList jl=null;
	public ReSuccess()
	{
		String s[]={"����ɹ�"};
		jl=new JList(s);
		this.add(jl);
		this.setSize(30,80);
		this.setVisible(true);
	}
}

//����GameRecord.writeRecord ();֮��


/*�ܽ᣺
 * ������λ���ҿ��Խ�����ʾ����
 * [direction] ��������
 * [type] �л�   �ҷ�����
 * [MyPanel�еĹ��캯��] ��Ʋ�ͬ�ĳ�ʼλ�� ���߱任λ��
*/