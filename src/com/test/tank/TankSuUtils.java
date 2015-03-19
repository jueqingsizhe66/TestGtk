/**
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
 * @time      2015��3��6������10:02:24
 * @version   com.test.tankTankUtils V1.0
 */
public class TankSuUtils
{

}

//��������ʾ����
class  MyStartPanel extends JPanel implements Runnable
{
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 500, 500 );
		
		if(times%2==0)
		{
		//��ʾ��Ϣ
		g.setColor(Color.yellow);
		//������������
		g.setFont(new Font("������κ",Font.BOLD,30));
		g.drawString("stage 1", 200, 200);
		
		//��ȫ������,��ֹ���˴�һֱ����,��ֹtimes Խ��
		if(times==1000)
		{
			times=0;
		}
		}
	}
	
	//������,����ʲôʱ�仭,�ͻ������˸��Ч��
	int times =0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true)
		{
			//����һ��
			try {
				Thread.sleep(10);
				times ++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			//�ػ�
			this.repaint();
		}
		
	}
}
//�ҵ����
class  MyPanel extends JPanel implements KeyListener,Runnable
{
	//����һ���ҵ�̹��
	Hero hero=null;
	
	//�ж��Ǽ����Ͼ� ���ǿ�ʼ����Ϸ
	String flag="newgame";
	
	//������˵�̹����
	Vector<EnemyTank> ets =new Vector<EnemyTank>();
	
	Vector<Node> nodes =new  Vector<Node>();
	int emsize=Recorder.getEnNum(); //����̹����Ŀ
	//����ը����ը����
	Vector<Bomb> bombs  =new Vector<Bomb>();
	
	//��������ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//���캯��
	public MyPanel()
	{
		
		//�ָ���¼,
		Recorder.getRecording();
		hero =new Hero(10,10);
		hero.setX(250);
		hero.setY(440);
		//��ʼ�����˵�̹��
		if(this.flag.equals("newgame"))
		{
		for (int i=0;i<emsize;i++)
		{
			
			//�������˵�һ��̹��,������
			EnemyTank et=new EnemyTank((i+1)*20,0);
			et.setDirect(2);
			//��MyPanel�ĵ���̹�����������õ���̹��
			et.setEts(ets);
			
			
			//��������̹��
			Thread t=new Thread(et);
			t.start();
			//������̹������һ���ӵ�
			shut s=new shut(et.x+10, et.y+30, 2);
			//���������̹��
			et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			
			//������˵�̹��
			ets.add(et);
		}
		}
		else
		{
			for (int i=0;i<nodes.size();i++)
			{
				
				Node node=nodes.get(i);
				//�������˵�һ��̹��,������
				EnemyTank et=new EnemyTank(node.x,0);
				et.setDirect(2);
				//��MyPanel�ĵ���̹�����������õ���̹��
				et.setEts(ets);
				
				
				//��������̹��
				Thread t=new Thread(et);
				t.start();
				//������̹������һ���ӵ�
				shut s=new shut(et.x+10, et.y+30, 2);
				//���������̹��
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//������˵�̹��
				ets.add(et);
			}
		}
		
		
		
		//��ʼ��ͼƬ,����ͼƬ�������һ��ը��
		image1 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/0.gif"));
		image2 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/5.gif"));
		image3 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/10.gif"));
				
	}
	
	
	//������ʾ��Ϣ,Ȼ����øú���
	public void showInfo(Graphics g)
	{
	//������ʾ��Ϣ̹����Ϣ,��̹�˲�����ս��
	this.drawTank(100, 520, g, 0, 1);
	//Recorder.getEnNum()+""���Ϻ����""�����ǽ�int������ʽת��ΪString����
	g.setColor(Color.BLACK);
	g.drawString(Recorder.getEnNum()+"", 130, 540);
	//�ҵ�̹��
	this.drawTank(350, 520, g, 0, 0);
	g.setColor(Color.BLACK);
	g.drawString(Recorder.getMyLife()+"", 380, 540);
	
	//������ҵ��ܳɼ�
	g.setColor(Color.BLACK);
	g.setFont(new Font("����",Font.BOLD,20));
	g.drawString("�����ܳɼ�:", 540, 40);
	
	this.drawTank(510, 20, g, 0, 1);
	
	g.drawString(Recorder.allkilledEnemy+"", 580, 40);
	}
	
	
	//��дpaint
	//paint����:������������̹��,�����Լ�̹��,,����ը��,�����ӵ�
	public void paint(Graphics g)
	{
		//�̳и���,������
		super.paint(g);
		//���ñ�����ɫ,500Ϊ����Ĵ�С
		g.fillRect(0, 0, 500, 500);
		
		//������ʾ��Ϣ
		this.showInfo(g);
		
		//�������˵�̹��
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.drawTank(et.getX(), et.getY(), g,et.getDirect(), 1);
				for(int j=0;j<et.ss.size();j++)
				{
					//ȡ���ӵ�
					shut enemyShut =(shut)et.ss.get(j);
					if(enemyShut.isLive)
					{
						 g.fill3DRect(enemyShut.x, enemyShut.y, 3, 3, false);
					}
					else 
					{
						//������˵�̹��������,�ʹ���������ɾ��
						et.ss.remove(enemyShut);
					}
				}
			}
		}
		//����ը��
		for(int i=0;i<bombs.size();i++)
		{
			//System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");  //�������õ�
			Bomb b=bombs.get(i);
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			}
			else if(b.life>3)
			{
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			}
			else 
			{
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			
			//����һ�ξ���life����ֵ����һ��
			b.life--;
			//���ը������ֵΪ��,�ͰѸ�ը����������ȥ��
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		//�����Լ���̹��
		if(hero.isLive==true)
		{
			this.drawTank(hero.getX(), hero.getY(), g,hero.getDirect(), 0);
		}
	   //��ss��ȡ��ÿһ���ӵ�,������.
	   for(int i=0;i<hero.ss.size();i++)
	   {
		   shut myShut= hero.ss.get(i);
		   if (myShut.isLive==true)
			   {
			   	   //3,3��ʾ�����ӵ��ĳ��Ϳ�.
				   g.fill3DRect(myShut.x, myShut.y, 3, 3, false);
			   }
		   if (myShut.isLive==false)
		   {
			   //��������ɾ���ӵ�
			   hero.ss.remove(myShut);
		   }
		}
	   //�����ӵ�  ,����һ���ӵ�
	   //isLive=true�����ӵ������Ե��ʱ��,����ʧ���򲻻���ʧ,����ж��������ڴ�,�����ڴ���Դ��.
//	   if (hero.s!=null&&hero.s.isLive==true)
//	   {
//		   g.fill3DRect(hero.s.x, hero.s.y, 3, 3, false);
//	   }
	}
	
	
	//�ж��ҵ��ӵ��Ƿ���е��˵�̹��
	public void hitEnemyTank()
	{
		boolean b=false;
		//��ΪҪ��ʱȥ�ж�,�����������ж��Ƿ���е���̹��.
		for(int i=0;i<hero.ss.size();i++)
		{
			//ȡ���ӵ�
			shut s=hero.ss.get(i);
			//�ж��ӵ��Ƿ���Ч
			if(s.isLive==true)
			{
				//ȡ��ÿ�����˵�̹��,�����ж�
				for(int j=0;j<ets.size();j++)
				{
					//ȡ������̹��
					EnemyTank et =ets.get(j);
					if (et.isLive==true);
					{
						b=this.hitTank(s, et);
						
						if(b==true)
						{
							//���ٵ���̹������
							Recorder.reduceEnNum();
							//����ɱ������̹������
							Recorder.addKilledEnemy();
							
						}
						
					}
				}
			}
		}
	}
	//�жϵ��˵��ӵ��Ƿ�����ҵ�̹��
	public void hitMe()
	{
		for(int i=0;i<ets.size();i++)
		{
			//	ȡ��̹��
			EnemyTank et=ets.get(i);
			{
				//ȡ���ӵ�
				for(int j=0;j<et.ss.size();j++)
				{
					shut enemyshot=et.ss.get(j);
					//if�����ҵ�̹�˱����к󲻻ᷴ����ը
					if(hero.isLive)
					{
						this.hitTank(enemyshot, hero);
					}
				}
			}
		}
	}
	//дһ������ר���ж��ӵ��Ƿ��е���̹��.
	public boolean  hitTank(shut s,Tank et)
	{
		boolean b2=false;
		//�ж� ��̹�˵ķ���,��Ϊ��̹�˲���������
		////0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��
		if(et.isLive)
		{
		switch(et.getDirect())
		{
			case 0:
			case 2:
				if(s.x>et.getX()&&s.x<et.getX()+20&&s.y>et.getY()&&s.y<et.y+30)
				{
					//������
					//�ӵ�����
					s.isLive=false;
					//����̹������
					et.isLive=false;
					//����һ��ը��,����Vector��
					b2=true;
					Bomb b=new Bomb(et.x,et.y);
					bombs.add(b);
				}
				
				break;
			case 1:
			case 3:
				if(s.x>et.getX()&&s.x<et.getX()+30&&s.y>et.getY()&&s.y<et.y+20)
				{
					//������
					//�ӵ�����
					s.isLive=false;
					//����̹������
					et.isLive=false;
					//����һ��ը��,����Vector��
					b2=true;
					Bomb b=new Bomb(et.x,et.y);
					bombs.add(b);
				}
				
				break;
		}	
		}
		return b2;
	}
	
	
	//����̹�˵ĺ���
	//�ҵ�̹��������0;����̹��������1
	public void drawTank (int x,int y ,Graphics g,int direct ,int type)
	{
		//�ж�����
		switch (type)
		{
		//�ҵ�̹��
		case 0 :
		g.setColor(Color.red);
		break;
		//����̹��
		case 1:
			g.setColor(Color.green);
			break;
		}
		//�жϷ���
		switch (direct)
		{
		//����
		case 0 :	
			//�����ҵ�̹��(��ʱ�ٷ�װ��һ������)
			//1.������ߵľ���
				g.fill3DRect(x, y, 5, 30,false);
			//2.�����ұߵľ���
				g.fill3DRect(x+15, y, 5, 30,false);
			//3.���м����
				g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.����Բ��
				g.fillOval(x+4, y+7, 10, 15);
			//5.������Ͳ
				g.drawLine(x+10, y-4, x+10, y+20);
				break;
		//����
		case 1:
			//1.�����ϱߵľ���
			g.fill3DRect(x, y, 30, 5,false);
			//2.�����±ߵľ���
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.���м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.����Բ��
			g.fillOval(x+7, y+4, 15, 10);
			//5.������Ͳ
			g.drawLine(x+20, y+10, x+35, y+10);
			break;
			//����
		case 2 :	
			//�����ҵ�̹��(��ʱ�ٷ�װ��һ������)
			//1.������ߵľ���
				g.fill3DRect(x, y, 5, 30,false);
			//2.�����ұߵľ���
				g.fill3DRect(x+15, y, 5, 30,false);
			//3.���м����
				g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.����Բ��
				g.fillOval(x+4, y+7, 10, 15);
			//5.������Ͳ
				g.drawLine(x+10, y+20, x+10, y+35);
				break;
			//����
		case 3:
			//1.�����ϱߵľ���
			g.fill3DRect(x, y, 30, 5,false);
			//2.�����±ߵľ���
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.���м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.����Բ��
			g.fillOval(x+7, y+4, 15, 10);
			//5.������Ͳ
			g.drawLine(x-4, y+10, x+20, y+10);
			break;
		}
		
	}
	@Override
	//��������,��a(���)s(����)d(����)w(����)
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			//�����ҵ�̹�˵ķ���
			//����
			this.hero.setDirect(0);
			this.hero.moveup();
			
		}	else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			//����
			this.hero.setDirect(2);
			this.hero.movedown();
		}else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			//����
			this.hero.setDirect(3);
			this.hero.moveleft();
		}else	if(e.getKeyCode()==KeyEvent.VK_D)
		{
			//����
			this.hero.setDirect(1);
			this.hero.moveright();
		}
		if (e.getKeyCode()==KeyEvent.VK_J)
		{
			//�ж�����Ƿ���J
			//����
			//�����ӵ�������
			if (this.hero.ss.size()<5)
			{
				this.hero.shutEnemy();
			}
		}
		this.repaint();
	}
	
	@Override
	//run����ʵ����ˢ���͵��ô��̹�˷���
	public void run() {
		//ÿ��100����ȥ�ػ�һ��
		while (true)
		{
			try
			{
			Thread.sleep(100);
			//�ж��ҵ��ӵ��Ƿ���е��˵�̹��
			this.hitEnemyTank();
			//
			this.hitMe();
			
			//�ػ�
			this.repaint();
		    }
		
		     catch(Exception e)
		     {
		    	 e.printStackTrace();
		     }
		}
	//whileѭ���Ĵ�����	
	}
	//����û�÷���
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}



class Node
{
	int x ;
	int y;
	int direct;
	//���캯��
	public Node(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
}
//��¼��,ͬʱҲ���Ա�����ҵ�����
class Recorder
{
	//��¼ÿ���ж��ٵ���
	private static int enNum=20;
	//���ж��ٿ��õ���
	private static int myLife=3;
	//��¼�ܹ�������ٵ���
	public static int allkilledEnemy=0;
	//���ļ��лָ���¼��

	
	
	//��ɶ��ļ������̹�˵Ķ�ȡ
	public static Vector<Node> getNodesAndEnemyNum()
	{
		 Vector<Node> nodes =new Vector<Node>();
		try {
			fr=new FileReader("e:\\myRecording.txt");
			
			br=new  BufferedReader(fr);
			try {
				//�ȶ�ȡ��һ��.
				String str =br.readLine();
				//���ַ���ת��Ϊint���͵ķ���
				if(str!=null)
				{
				Recorder.allkilledEnemy =Integer.parseInt(str);
				}
				
				
				while((str=br.readLine())!=null)
				{
					//��ͻ����ǰ�str�ַ������տո�ֳ�һ���ַ�������.
					String []xyz =str.split(" ");
					//�������Ѿ��ҿ϶���������������
						Node n =new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
						nodes.add(n);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return nodes;
	}
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	
	//���ٵ��˵�����
	public static void reduceEnNum()
	{
		Recorder.enNum--;
	}
	public static void addKilledEnemy()
	{
		allkilledEnemy++;
	}
	private static FileWriter fw =null;
	private static BufferedWriter bw=null;
	public static FileReader fr=null;
	public static BufferedReader br =null;
	
	private static Vector<EnemyTank> ets =new  Vector<EnemyTank>();
	public static Vector<EnemyTank> getEts() {
		return ets;
	}
	public static void setEts(Vector<EnemyTank> ets) {
		Recorder.ets = ets;
	}
	//����һ��ٵ���̹�˵��������浽�ļ���
	public static void keepRecording()
	{
		 try {
			fw =new FileWriter("e:\\myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allkilledEnemy+"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			
			try {
				//����ȹرյ�ԭ��
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
	}
	
	//����һ��ٵ���̹�˵��������ļ��лָ�����
	public static void getRecording()
	{
		
		try {
			fr=new FileReader("e:\\myRecording.txt");
			
			br=new  BufferedReader(fr);
			try {
				String str =br.readLine();
				//���ַ���ת��Ϊint���͵ķ���
				if(str!=null)
				{
				Recorder.allkilledEnemy =Integer.parseInt(str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	

	//������ٵ���̹�˵������͵���̹�˵�����
	public static void  keepRecordAndEnemyTank()
	{
		
		try {
			fw =new FileWriter("e:\\myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allkilledEnemy+"\r\n");
			
			//���浱ǰ���ŵ�̹�˵�����ͷ���
			for(int i=0;i<ets.size();i++)
			{
				//ȥ�Ե�һ��̹��
				EnemyTank et =ets.get(i);
				if(et.isLive)
				{
					//��ľͱ���
					String recorde=et.x+"  "+et.y+"  "+et.direct;
					//д��,
					bw.write(recorde +"\r\n");
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			
			try {
				//����ȹرյ�ԭ��
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}
	
	
	
//̹����
class Tank
{
	//����̹�˵��ٶ�
	 int speed=4;
	 //����̹����ɫ
	 int color;
	//�ж�̹���Ƿ񻹻���
	 boolean isLive=true;
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	//x��ʾ̹�˵ĺ�����
	 int x=0;
	//̹�˵�������
	 int y=0;
	//̹�˷���
	//0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��
	int direct =0;
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	//���캯��
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}	
}
//���˵�̹��,�ѵ���̹�������߳���
class EnemyTank extends Tank implements Runnable
{
	
	
	int times=0;
	
	//����һ������,���Է��ʵ�MyPanel�����е��˵�̹��
	Vector<EnemyTank> ets =new Vector<EnemyTank>();
	//����һ������,���Դ���ӵ�
	Vector <shut> ss =new Vector<shut>();
	//���������ӵ�,Ӧ���ڸոմ���̹�˺͵���̹���ӵ�������
	
	//���캯��
	public EnemyTank(int x,int y)
	{
		super(x,y);
	}
	//�õ�MyPanel�ĵ��˵�̹������
	public void setEts(Vector<EnemyTank> vv)
	{
		this.ets=vv;
	}
	//�ж��Ƿ������˱��˵�̹��
	public Boolean isTouchOtherEnemy()
	{
		boolean b=false;
		
		
		switch(this.direct)
		{
		////0��ʾ�ҵ�̹������,1��ʾ��,2��ʾ��,3��ʾ��
			case 0:
				for(int i=0;i<ets.size();i++)
				{
					//ȡ��һ��̹��
					EnemyTank et =ets.get(i);
					if(et!=this)
					{
						//������˵�̹�����ϻ��ߵ��˵�̹������
						if(et.direct==0||et.direct==2)
						{
							//��Ҫ�ж�������,ÿ��if�ж�һ���㣬�ҵ����
							if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
							{
								b=true;
							}
							else
							{	//�ҵ��ҵ�
								if((this.x+20)>=et.x&&(this.x+20)<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
								{
									b=true;
								}
							}
						}
						//������˵�̹��������ߵ��˵�̹������
						if(et.direct==3||et.direct==1)
						{	//�ҵ����
							if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
							{
								b=true;
							}
							else
							{	//�ҵ��ҵ�
								if((this.x+20)>=et.x&&(this.x+20)<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
								{
									b=true;
								}
							}
						}
					}
				}
				break;
				//̹������
			case 1:
				for(int i=0;i<ets.size();i++)
				{
					//ȡ��һ��̹��
					EnemyTank et =ets.get(i);
					if(et!=this)
					{
						//������˵�̹�����ϻ��ߵ��˵�̹������
						if(et.direct==0||et.direct==2)
						{	//�ҵ��ϵ�
							if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
							{
								b=true;
							}
							else
							{	//�ҵ��µ�
								if((this.x+30)>=et.x&&(this.x+30)<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
								{
									b=true;
								}
							}
						}
						//������˵�̹��������ߵ��˵�̹������
						if(et.direct==3||et.direct==1)
						{	//�ҵ��ϵ�
							if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
							{
								b=true;
							}
							else
							{	//�ҵ���һ��
								if((this.x+30)>=et.x&&(this.x+30)<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
								{
									b=true;
								}
							}
						}
					}
				}
				break;
			case 2://̹��������
				for(int i=0;i<ets.size();i++)
				{
					//ȡ��һ��̹��
					EnemyTank et =ets.get(i);
					if(et!=this)
					{
						//������˵�̹�����ϻ��ߵ��˵�̹������
						if(et.direct==0||et.direct==2)
						{	//�ҵ����
							if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
							{
								b=true;
							}
							else
							{	//�ҵ��ҵ�
								if((this.x+20)>=et.x&&(this.x+20)<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
								{
									b=true;
								}
							}
						}
						//������˵�̹��������ߵ��˵�̹������
						if(et.direct==3||et.direct==1)
						{	//�ҵ����
							if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
							{
								b=true;
							}
							else
							{	//�ҵ��ҵ�
								if((this.x+20)>=et.x&&(this.x+20)<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
								{
									b=true;
								}
							}
						}
					}
				}
				break;
			case 3://̹������
				for(int i=0;i<ets.size();i++)
				{
					//ȡ��һ��̹��
					EnemyTank et =ets.get(i);
					if(et!=this)
					{
						//������˵�̹�����ϻ��ߵ��˵�̹������
						if(et.direct==0||et.direct==2)
						{	//�ҵ���һ��
							if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
							{
								b=true;
							}
							else
							{	//�ҵ���һ��
								if((this.x)>=et.x&&(this.x)<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
								{
									b=true;
								}
							}
						}
						//������˵�̹��������ߵ��˵�̹������
						if(et.direct==3||et.direct==1)
						{	//��һ��
							if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
							{
								b=true;
							}
							else
							{	//��һ��
								if((this.x)>=et.x&&(this.x)<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
								{
									b=true;
								}
							}
						}
					}
				}
				break;
		}
		return b;
		
	}
	@Override
	public void run() {
		while (true)
		{
			switch(this.direct)
			{
			//0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��
			case 0:
				//˵��̹�����������ƶ�
				for(int i=0;i<30;i++)
				{
					//if�������Ƶ���̹����ָ���ķ�Χ���ƶ�
					if(y>0&&!this.isTouchOtherEnemy())
					{
						y-=this.speed;
					}
					try
					{
						
						Thread.sleep(50);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				break;
			case 1:
				for(int i=0;i<30;i++)
				{
					if(x<500&&!this.isTouchOtherEnemy())
					{
						x+=this.speed;
					}
					try
					{
						
						Thread.sleep(50);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for(int i=0;i<30;i++)
				{
					if(y<500&&!this.isTouchOtherEnemy())
					{
						y+=this.speed;
				
					}
					try
					{
						
						Thread.sleep(50);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				break;
			case 3:
				for(int i=0;i<30;i++)
				{
					if(x>0&&!this.isTouchOtherEnemy())
					{
						x-=this.speed;
					}
					try
					{
						
						Thread.sleep(50);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				break;
			}
			
		  
		    
			this.times++;
			if(this.times%2==0)
			{
			if(this.isLive)
			{
			
				 //�ж��ӵ��Ƿ�û����,�ӵ���������2��
				 if(this.ss.size()<2)
				 {
					 shut s=null;
					//û���ӵ��� ����
					//0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��
					 switch(this.getDirect())
					 {
					case 0:
						//����һ���ӵ�,s����������.����ָ��.
						s=new shut(this.getX()+10,this.getY(),0);
						//���ӵ����뵽����
						this.ss.add(s);
						break;
					case 1 :
						s=new shut(this.getX()+30,this.getY()+10,1);
						this.ss.add(s);
						break;
					case 2 :
						s=new shut(this.getX()+10,this.getY()+30,2);
						this.ss.add(s);
						break;
					case 3 :
						s=new shut(this.getX(),this.getY()+10,3);
						this.ss.add(s);
						break;
					 }
					 
					 //�����ӵ��߳�
					 Thread t=new Thread(s);
					 t.start();
				 }
			}
			}
			//��̹�˲���һ������ķ���.������Կ��Ƶ���̹�˵��ƶ�����,�����õ���̹�����Լ�̹�˳���߿����Լ�̹�˾Ͷ�����
			//Math.random()�������һ��0��1��С��
			 this.direct=(int)(Math.random()*4);
			 //�жϵ���̹���Ƿ�����
			 if(this.isLive==false)
			 {
				 //��̹���������˳��߳�,��Linux�еĽ�ʬ����,û������,���ǻ�����.
				 break;
			 }	
		}
	}
}


//�ҵ�̹��
class Hero extends Tank
{
	//�ӵ�
	shut s=null;
	//ʵ���ӵ�����
	Vector<shut> ss=new Vector<shut>();
	//���캯��
	public Hero (int x,int y)
	{
		super(x,y);
	}
	//̹�˷����ӵ�����
	public void shutEnemy()
	{
		switch (this.getDirect())
		{
		//0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��
		case 0:
			//����һ���ӵ�,s����������.����ָ��.
			s=new shut(this.getX()+10,this.getY(),0);
			//���ӵ����뵽����
			ss.add(s);
			break;
		case 1 :
			s=new shut(this.getX()+30,this.getY()+10,1);
			ss.add(s);
			break;
		case 2 :
			s=new shut(this.getX()+10,this.getY()+30,2);
			ss.add(s);
			break;
		case 3 :
			s=new shut(this.getX(),this.getY()+10,3);
			ss.add(s);
			break;
		}
		Thread t=new Thread(s);
		//�����ӵ���run����
		t.start();
	}
	//̹�������ƶ�
	public void moveup()
	{
		y=y-speed;
	}
	//̹�������ƶ�
	public void movedown()
	{
		y=y+speed;
	}
	//̹�������ƶ�
	public void moveleft()
	{
		x=x-speed;
	}
	//̹�������ƶ�
	public void moveright()
	{
		x=x+speed;
	}
}

//�ӵ���
class shut implements Runnable
{
	//����
	int x;
	int y;
	int direct;
	int speed=10;
	//�Ƿ񻹻���
	boolean isLive=true;
	//���캯��
	public shut (int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	@Override
	//
	public void run() {
		 while(true)
		 {
			 try 
			 { Thread.sleep(50);}
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 switch (direct)
			 {
			//0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��
			 case 0:
				y-=speed;
				 break;
			 case 1:
				 x+=speed;
				 break;
			 case 2:
				 y+=speed;
				 break;
			 case 3:
				 x-=speed;
				 break;
			 }
			// System.out.println("�ӵ�����x="+x+"y="+y);    //�����õ�
			 //�ӵ���������,500����Ļ�ı߽�
			 if (x<0||x>500||y<0||y>500)
			 {
				 this.isLive=false;
				 break;
			 }
		 }
	}
}

//��ը��
class  Bomb 
{
	int x,y;
	//ը��������
	int life=9;
	boolean isLive=true;
	//���캯��
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//��������ֵ
	public  void lifeDown()
	{
		if(life>0)
		{
			life--;
		}
		else
		{
			isLive=false;
		}
	}
}