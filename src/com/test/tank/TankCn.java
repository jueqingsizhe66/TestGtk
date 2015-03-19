package com.test.tank;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class TankCn extends JFrame
{
	MyPanelInit mp=null;
	
	public static void main(String[] args) {
		TankCn myTankInitgame1=new TankCn();
	}
	
	//���캯��
	public TankCn()
	{	
		//����������Զ�����paint����.
		mp= new MyPanelInit();
		Thread t=new Thread(mp);
		//�����̻߳��Զ�����run����
		t.start();
		
		this.add(mp);
		this.addKeyListener(mp);
		
		this.setSize(500,500);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}


//��������ʾ����
class  MyStartPanelInit extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 500, 500 );
	}
}
//�ҵ����
class  MyPanelInit extends JPanel implements KeyListener,Runnable
{
	//����һ���ҵ�̹��
	HeroInit HeroInit=null;
	//������˵�̹����
	Vector<EnemyTankInit> ets =new Vector<EnemyTankInit>();
	int emsize=25; //����̹����Ŀ
	//����ը����ը����
	Vector<BombInit> BombInits  =new Vector<BombInit>();
	
	//��������ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//���캯��
	public MyPanelInit()
	{
		HeroInit =new HeroInit(10,10);
		HeroInit.setX(250);
		HeroInit.setY(440);
		//��ʼ�����˵�̹��
		for (int i=0;i<emsize;i++)
		{
			
			//�������˵�һ��̹��,������
			EnemyTankInit et=new EnemyTankInit((i+1)*20,0);
			et.setDirect(2);
			//��MyPanelInit�ĵ���̹�����������õ���̹��
			et.setEts(ets);
			
			
			//��������̹��
			Thread t=new Thread(et);
			t.start();
			//������̹������һ���ӵ�
			ShutInit s=new ShutInit(et.x+10, et.y+30, 2);
			//���������̹��
			et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			
			//������˵�̹��
			ets.add(et);
		}
		//��ʼ��ͼƬ,����ͼƬ�������һ��ը��
		image1 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/0.gif"));
		image2 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/5.gif"));
		image3 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/10.gif"));
				
	}
	//��дpaint
	//paint����:������������̹��,�����Լ�̹��,,����ը��,�����ӵ�
	public void paint(Graphics g)
	{
		//�̳и���,������
		super.paint(g);
		//���ñ�����ɫ,500Ϊ����Ĵ�С
		g.fillRect(0, 0, 500, 500);
		
		
		//�������˵�̹��
		for(int i=0;i<ets.size();i++)
		{
			EnemyTankInit et=ets.get(i);
			if(et.isLive)
			{
				this.drawTankInit(et.getX(), et.getY(), g,et.getDirect(), 1);
				for(int j=0;j<et.ss.size();j++)
				{
					//ȡ���ӵ�
					ShutInit enemyShutInit =(ShutInit)et.ss.get(j);
					if(enemyShutInit.isLive)
					{
						 g.fill3DRect(enemyShutInit.x, enemyShutInit.y, 3, 3, false);
					}
					else 
					{
						//������˵�̹��������,�ʹ���������ɾ��
						et.ss.remove(enemyShutInit);
					}
				}
			}
		}
		//����ը��
		for(int i=0;i<BombInits.size();i++)
		{
			//System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");  //�������õ�
			BombInit b=BombInits.get(i);
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
				BombInits.remove(b);
			}
		}
		//�����Լ���̹��
		if(HeroInit.isLive==true)
		{
			this.drawTankInit(HeroInit.getX(), HeroInit.getY(), g,HeroInit.getDirect(), 0);
		}
	   //��ss��ȡ��ÿһ���ӵ�,������.
	   for(int i=0;i<HeroInit.ss.size();i++)
	   {
		   ShutInit myShutInit= HeroInit.ss.get(i);
		   if (myShutInit.isLive==true)
			   {
			   	   //3,3��ʾ�����ӵ��ĳ��Ϳ�.
				   g.fill3DRect(myShutInit.x, myShutInit.y, 3, 3, false);
			   }
		   if (myShutInit.isLive==false)
		   {
			   //��������ɾ���ӵ�
			   HeroInit.ss.remove(myShutInit);
		   }
		}
	   //�����ӵ�  ,����һ���ӵ�
	   //isLive=true�����ӵ������Ե��ʱ��,����ʧ���򲻻���ʧ,����ж��������ڴ�,�����ڴ���Դ��.
//	   if (HeroInit.s!=null&&HeroInit.s.isLive==true)
//	   {
//		   g.fill3DRect(HeroInit.s.x, HeroInit.s.y, 3, 3, false);
//	   }
	}
	
	
	//�ж��ҵ��ӵ��Ƿ���е��˵�̹��
	public void hitEnemyTankInit()
	{
		//��ΪҪ��ʱȥ�ж�,�����������ж��Ƿ���е���̹��.
		for(int i=0;i<HeroInit.ss.size();i++)
		{
			//ȡ���ӵ�
			ShutInit s=HeroInit.ss.get(i);
			//�ж��ӵ��Ƿ���Ч
			if(s.isLive==true)
			{
				//ȡ��ÿ�����˵�̹��,�����ж�
				for(int j=0;j<ets.size();j++)
				{
					//ȡ������̹��
					EnemyTankInit et =ets.get(j);
					if (et.isLive==true);
					{
						this.hitTankInit(s, et);
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
			EnemyTankInit et=ets.get(i);
			{
				//ȡ���ӵ�
				for(int j=0;j<et.ss.size();j++)
				{
					ShutInit enemyshot=et.ss.get(j);
					this.hitTankInit(enemyshot, HeroInit);
				}
			}
		}
	}
	//дһ������ר���ж��ӵ��Ƿ��е���̹��.
	public void  hitTankInit(ShutInit s,TankInit et)
	{
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
					BombInit b=new BombInit(et.x,et.y);
					BombInits.add(b);
					
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
					BombInit b=new BombInit(et.x,et.y);
					BombInits.add(b);
				}
				break;
		}	
		}
	}
	
	
	//����̹�˵ĺ���
	public void drawTankInit (int x,int y ,Graphics g,int direct ,int type)
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
			this.HeroInit.setDirect(0);
			this.HeroInit.moveup();
			
		}	else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			//����
			this.HeroInit.setDirect(2);
			this.HeroInit.movedown();
		}else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			//����
			this.HeroInit.setDirect(3);
			this.HeroInit.moveleft();
		}else	if(e.getKeyCode()==KeyEvent.VK_D)
		{
			//����
			this.HeroInit.setDirect(1);
			this.HeroInit.moveright();
		}
		if (e.getKeyCode()==KeyEvent.VK_J)
		{
			//�ж�����Ƿ���J
			//����
			//�����ӵ�������
			if (this.HeroInit.ss.size()<5)
			{
				this.HeroInit.ShutInitEnemy();
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
			this.hitEnemyTankInit();
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



//̹����
class TankInit
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
	public TankInit(int x,int y)
	{
		this.x=x;
		this.y=y;
	}	
}
//���˵�̹��,�ѵ���̹�������߳���
class EnemyTankInit extends TankInit implements Runnable
{
	
	
	int times=0;
	
	//����һ������,���Է��ʵ�MyPanelInit�����е��˵�̹��
	Vector<EnemyTankInit> ets =new Vector<EnemyTankInit>();
	//����һ������,���Դ���ӵ�
	Vector <ShutInit> ss =new Vector<ShutInit>();
	//���������ӵ�,Ӧ���ڸոմ���̹�˺͵���̹���ӵ�������
	
	//���캯��
	public EnemyTankInit(int x,int y)
	{
		super(x,y);
	}
	//�õ�MyPanelInit�ĵ��˵�̹������
	public void setEts(Vector<EnemyTankInit> vv)
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
					EnemyTankInit et =ets.get(i);
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
					EnemyTankInit et =ets.get(i);
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
					EnemyTankInit et =ets.get(i);
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
					EnemyTankInit et =ets.get(i);
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
					 ShutInit s=null;
					//û���ӵ��� ����
					//0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��
					 switch(this.getDirect())
					 {
					case 0:
						//����һ���ӵ�,s����������.����ָ��.
						s=new ShutInit(this.getX()+10,this.getY(),0);
						//���ӵ����뵽����
						this.ss.add(s);
						break;
					case 1 :
						s=new ShutInit(this.getX()+30,this.getY()+10,1);
						this.ss.add(s);
						break;
					case 2 :
						s=new ShutInit(this.getX()+10,this.getY()+30,2);
						this.ss.add(s);
						break;
					case 3 :
						s=new ShutInit(this.getX(),this.getY()+10,3);
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
class HeroInit extends TankInit
{
	//�ӵ�
	ShutInit s=null;
	//ʵ���ӵ�����
	Vector<ShutInit> ss=new Vector<ShutInit>();
	//���캯��
	public HeroInit (int x,int y)
	{
		super(x,y);
	}
	//̹�˷����ӵ�����
	public void ShutInitEnemy()
	{
		switch (this.getDirect())
		{
		//0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��
		case 0:
			//����һ���ӵ�,s����������.����ָ��.
			s=new ShutInit(this.getX()+10,this.getY(),0);
			//���ӵ����뵽����
			ss.add(s);
			break;
		case 1 :
			s=new ShutInit(this.getX()+30,this.getY()+10,1);
			ss.add(s);
			break;
		case 2 :
			s=new ShutInit(this.getX()+10,this.getY()+30,2);
			ss.add(s);
			break;
		case 3 :
			s=new ShutInit(this.getX(),this.getY()+10,3);
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
class ShutInit implements Runnable
{
	//����
	int x;
	int y;
	int direct;
	int speed=10;
	//�Ƿ񻹻���
	boolean isLive=true;
	//���캯��
	public ShutInit (int x,int y,int direct)
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
			// System.out.println("�ӵ�����x="+x+"y="+y.);    //�����õ�
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
class  BombInit 
{
	int x,y;
	//ը��������
	int life=9;
	boolean isLive=true;
	//���캯��
	public BombInit(int x,int y)
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