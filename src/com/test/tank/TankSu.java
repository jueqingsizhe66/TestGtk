/**
 *  * ���� ̹�˴�ս
���ӵĹ���:
 * 9.��ο��Ƶ��˵�̹�˲��ص�(�����)
 * 10.��ֹ����̹���ص��˶�
 * 11.���Էֹ�
˼·:��һ����ʼ��panel(�յ�)��Ҫ������ʾ
 * 12.����������Ϸ��ʱ����ͣ�ͼ���
 * 13.���Լ�¼��ҵĳɼ�
 * 14.java��β��������ļ�

����˵ھŸ�����
/**
 * ����:̹����Ϸ��2.0
 * 1.����̹��
 * 2.�ҵ�̹�˿��������ƶ�.
 * 3.ʵ���ӵ�����,������.
 * 4.�ҷ�����̹��ʱ,�з�̹�˾���ʧ(�б�ըЧ��)
 * 5.�õ���̹��Ҳ�������ƶ�
 * 6.�����ҷ�̹�˺͵���̹����ָ����Χ���˶�
 * 7.�õ��˵�̹��Ҳ�ܷ����ӵ�
 * 8.�����˵�̹�˻�����ʱ,�ҷ�����ը
 * 9.��ο��Ƶ��˵�̹�˲��ص�(�����)x
 * 10.��ֹ����̹���ص��˶�x
 * 11.���Էֹ�x
 * 12.����������Ϸ��ʱ����ͣ�ͼ���x
 * 13.���Լ�¼��ҵĳɼ�x
 * 14.java��β��������ļ�x
 * 
 * 
 * ע��㣺 Tank�����������ĵ� ��Ҫע��  e://myRecording.txt һ��Ҫ����ȷ���̷�
 *        ����e�����½�һ��myRecording.txt�ļ�
 *
 */
package com.test.tank;
 import java.awt.*;
 import java.io.*;
 import java.awt.event.*;
 import java.util.*;
 import javax.swing.*;
/**
 * @author    Ҷ����
 * @time      2015��3��6������9:48:47
 * @version   com.test.tankTankSu V1.0
 */
public class TankSu extends JFrame implements ActionListener
{

	/**
	 * @param args
	 */
	MyPanel mp=null;
	//����һ����ʼ���
	MyStartPanel msp=null;
	
	//��������Ҫ�Ĳ˵�
	JMenuBar jmb=null;
	//��Ϸ
	JMenu jm1=null;
	//��ʼ��Ϸ
	JMenuItem jmi1 =null;
	//����,�˳�
	JMenuItem jmi2 =null;
	//�����˳�
	JMenuItem jmi3 =null;
	//���Ͼ�
	JMenuItem jmi4=null;
	public static void main(String[] args) {
		TankSu mytankgame1=new TankSu();
	}
	
	//���캯��
	public TankSu()
	{	

		
		//�����˵����˵�ѡ��
		jmb=new JMenuBar();
		jm1 =new JMenu("��Ϸ(G)");
		//���ÿ�ݼ�
		jm1.setMnemonic('G');
		jmi1 =new JMenuItem("��ʼ����Ϸ(N)");
		//��jmi1��Ӧ
		jmi1.addActionListener(this);
		jmi1.setActionCommand("new");
		jmi1.setMnemonic('N');
		
		jmi2 =new  JMenuItem("�˳���Ϸ(Q)");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("quit");
		jmi2.setMnemonic('Q');
		
		jmi3 =new  JMenuItem("�����˳�(C)");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		jmi3.setMnemonic('C');
		
		jmi4=new JMenuItem("�����Ͼ���Ϸ");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("continue");
		
		
		jmb.add(jm1);
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		
		
		msp =new MyStartPanel();
		Thread t2=new Thread(msp);
		t2.start();
		this.add(msp);
		//ע�����������JMenuBar������add����,��ר�ŵ�setJMenuBar()����.
		this.setJMenuBar(jmb);
		
		this.setSize(700,650);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//���û���ͬ�ĵ��������ͬ����Ӧ
		//�����ϴε���Ϸ
		
		if(e.getActionCommand().equals("new"))
		{
			//����ս�������
			System.out.println("����Ϸ");//�������õ�
			//����������Զ�����paint����.
			//��ɾ���ɵ����,Ȼ���ټ���һ���µ����
			this.remove(msp);
			mp= new MyPanel();
			Thread t=new Thread(mp);
			//�����̻߳��Զ�����run����
			t.start();
			
			this.add(mp);
			this.addKeyListener(mp);
			//��ʾ,ˢ��JFrame
			this.setVisible(true);
		}
		//�����˳�,1��������ٵ��˵�����2,������˵�����

		else  if(e.getActionCommand().equals("saveExit"))
		{
			System.out.println("�����˳�");//�������õ�
			Recorder.setEts(mp.ets);
			 Recorder.keepRecordAndEnemyTank();
			System.exit(0);
			
		}
		//�˳�,��¼���ٵ��˵�����
		else if(e.getActionCommand().equals("quit"))
		 {
			//�û�������˳�ϵͳ�İ�ť
			//������ٵ�������
			System.out.println("�˳�");//�������õ�
			Recorder.keepRecording ();
			System.exit(0);
		} 
		else if(e.getActionCommand().equals("continue"))
		{	
			//��ɻָ�
			
			System.out.print("�ָ��Ͼ�");
		}
		
	}

}