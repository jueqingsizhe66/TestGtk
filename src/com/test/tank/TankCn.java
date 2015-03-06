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
	
	//构造函数
	public TankCn()
	{	
		//创建对象会自动调用paint函数.
		mp= new MyPanelInit();
		Thread t=new Thread(mp);
		//创建线程会自动调用run方法
		t.start();
		
		this.add(mp);
		this.addKeyListener(mp);
		
		this.setSize(500,500);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}


//就是起提示作用
class  MyStartPanelInit extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 500, 500 );
	}
}
//我的面板
class  MyPanelInit extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	HeroInit HeroInit=null;
	//定义敌人的坦克组
	Vector<EnemyTankInit> ets =new Vector<EnemyTankInit>();
	int emsize=25; //定义坦克数目
	//定义炸弹的炸弹组
	Vector<BombInit> BombInits  =new Vector<BombInit>();
	
	//定义三张图片
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//构造函数
	public MyPanelInit()
	{
		HeroInit =new HeroInit(10,10);
		HeroInit.setX(250);
		HeroInit.setY(440);
		//初始化敌人的坦克
		for (int i=0;i<emsize;i++)
		{
			
			//创建敌人的一辆坦克,方向朝下
			EnemyTankInit et=new EnemyTankInit((i+1)*20,0);
			et.setDirect(2);
			//将MyPanelInit的敌人坦克向量交给该敌人坦克
			et.setEts(ets);
			
			
			//启动敌人坦克
			Thread t=new Thread(et);
			t.start();
			//给敌人坦克添加一颗子弹
			ShutInit s=new ShutInit(et.x+10, et.y+30, 2);
			//加入给敌人坦克
			et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			
			//加入敌人的坦克
			ets.add(et);
		}
		//初始化图片,三张图片才能组成一颗炸弹
		image1 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/0.gif"));
		image2 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/5.gif"));
		image3 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/10.gif"));
				
	}
	//重写paint
	//paint功能:画出敌人三个坦克,画出自己坦克,,画出炸弹,画出子弹
	public void paint(Graphics g)
	{
		//继承父类,必须有
		super.paint(g);
		//设置背景颜色,500为窗体的大小
		g.fillRect(0, 0, 500, 500);
		
		
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++)
		{
			EnemyTankInit et=ets.get(i);
			if(et.isLive)
			{
				this.drawTankInit(et.getX(), et.getY(), g,et.getDirect(), 1);
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
					ShutInit enemyShutInit =(ShutInit)et.ss.get(j);
					if(enemyShutInit.isLive)
					{
						 g.fill3DRect(enemyShutInit.x, enemyShutInit.y, 3, 3, false);
					}
					else 
					{
						//如果敌人的坦克死亡了,就从向量里面删掉
						et.ss.remove(enemyShutInit);
					}
				}
			}
		}
		//画出炸弹
		for(int i=0;i<BombInits.size();i++)
		{
			//System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");  //做测试用的
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
			
			//划过一次就让life生命值减少一次
			b.life--;
			//如果炸弹生命值为零,就把该炸弹从向量中去掉
			if(b.life==0)
			{
				BombInits.remove(b);
			}
		}
		//画出自己的坦克
		if(HeroInit.isLive==true)
		{
			this.drawTankInit(HeroInit.getX(), HeroInit.getY(), g,HeroInit.getDirect(), 0);
		}
	   //从ss中取出每一颗子弹,并画出.
	   for(int i=0;i<HeroInit.ss.size();i++)
	   {
		   ShutInit myShutInit= HeroInit.ss.get(i);
		   if (myShutInit.isLive==true)
			   {
			   	   //3,3表示画出子弹的长和宽.
				   g.fill3DRect(myShutInit.x, myShutInit.y, 3, 3, false);
			   }
		   if (myShutInit.isLive==false)
		   {
			   //从向量中删除子弹
			   HeroInit.ss.remove(myShutInit);
		   }
		}
	   //画出子弹  ,画出一颗子弹
	   //isLive=true控制子弹到达边缘的时候,会消失否则不会消失,这个判断来控制内存,减少内存资源的.
//	   if (HeroInit.s!=null&&HeroInit.s.isLive==true)
//	   {
//		   g.fill3DRect(HeroInit.s.x, HeroInit.s.y, 3, 3, false);
//	   }
	}
	
	
	//判断我的子弹是否击中敌人的坦克
	public void hitEnemyTankInit()
	{
		//因为要随时去判断,所以在这里判断是否击中敌人坦克.
		for(int i=0;i<HeroInit.ss.size();i++)
		{
			//取出子弹
			ShutInit s=HeroInit.ss.get(i);
			//判断子弹是否有效
			if(s.isLive==true)
			{
				//取出每个敌人的坦克,与他判断
				for(int j=0;j<ets.size();j++)
				{
					//取出敌人坦克
					EnemyTankInit et =ets.get(j);
					if (et.isLive==true);
					{
						this.hitTankInit(s, et);
					}
				}
			}
		}
	}
	//判断敌人的子弹是否击中我的坦克
	public void hitMe()
	{
		for(int i=0;i<ets.size();i++)
		{
			//	取出坦克
			EnemyTankInit et=ets.get(i);
			{
				//取出子弹
				for(int j=0;j<et.ss.size();j++)
				{
					ShutInit enemyshot=et.ss.get(j);
					this.hitTankInit(enemyshot, HeroInit);
				}
			}
		}
	}
	//写一个函数专门判断子弹是否集中敌人坦克.
	public void  hitTankInit(ShutInit s,TankInit et)
	{
		//判断 该坦克的方向,因为该坦克不是正方形
		////0表示上,1表示右,2表示下,3表示左
		if(et.isLive)
		{
		switch(et.getDirect())
		{
			case 0:
			case 2:
				if(s.x>et.getX()&&s.x<et.getX()+20&&s.y>et.getY()&&s.y<et.y+30)
				{
					//击中了
					//子弹死亡
					s.isLive=false;
					//敌人坦克死亡
					et.isLive=false;
					//创建一颗炸弹,放入Vector中
					BombInit b=new BombInit(et.x,et.y);
					BombInits.add(b);
					
				}
				break;
			case 1:
			case 3:
				if(s.x>et.getX()&&s.x<et.getX()+30&&s.y>et.getY()&&s.y<et.y+20)
				{
					//击中了
					//子弹死亡
					s.isLive=false;
					//敌人坦克死亡
					et.isLive=false;
					//创建一颗炸弹,放入Vector中
					BombInit b=new BombInit(et.x,et.y);
					BombInits.add(b);
				}
				break;
		}	
		}
	}
	
	
	//画出坦克的函数
	public void drawTankInit (int x,int y ,Graphics g,int direct ,int type)
	{
		//判断类型
		switch (type)
		{
		//我的坦克
		case 0 :
		g.setColor(Color.red);
		break;
		//敌人坦克
		case 1:
			g.setColor(Color.green);
			break;
		}
		//判断方向
		switch (direct)
		{
		//向上
		case 0 :	
			//画出我的坦克(到时再封装成一个函数)
			//1.画出左边的巨型
				g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边的矩形
				g.fill3DRect(x+15, y, 5, 30,false);
			//3.画中间矩形
				g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画出圆形
				g.fillOval(x+4, y+7, 10, 15);
			//5.画出炮筒
				g.drawLine(x+10, y-4, x+10, y+20);
				break;
		//向左
		case 1:
			//1.画出上边的巨型
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出下边的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.画中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.画出圆形
			g.fillOval(x+7, y+4, 15, 10);
			//5.画出炮筒
			g.drawLine(x+20, y+10, x+35, y+10);
			break;
			//向下
		case 2 :	
			//画出我的坦克(到时再封装成一个函数)
			//1.画出左边的巨型
				g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边的矩形
				g.fill3DRect(x+15, y, 5, 30,false);
			//3.画中间矩形
				g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画出圆形
				g.fillOval(x+4, y+7, 10, 15);
			//5.画出炮筒
				g.drawLine(x+10, y+20, x+10, y+35);
				break;
			//向右
		case 3:
			//1.画出上边的巨型
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出下边的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.画中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.画出圆形
			g.fillOval(x+7, y+4, 15, 10);
			//5.画出炮筒
			g.drawLine(x-4, y+10, x+20, y+10);
			break;
		}
		
	}
	@Override
	//案件处理,用a(左边)s(向下)d(向右)w(向上)
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			//设置我的坦克的方向
			//向上
			this.HeroInit.setDirect(0);
			this.HeroInit.moveup();
			
		}	else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			//向下
			this.HeroInit.setDirect(2);
			this.HeroInit.movedown();
		}else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			//向左
			this.HeroInit.setDirect(3);
			this.HeroInit.moveleft();
		}else	if(e.getKeyCode()==KeyEvent.VK_D)
		{
			//向右
			this.HeroInit.setDirect(1);
			this.HeroInit.moveright();
		}
		if (e.getKeyCode()==KeyEvent.VK_J)
		{
			//判断玩家是否按下J
			//开火
			//控制子弹最多五颗
			if (this.HeroInit.ss.size()<5)
			{
				this.HeroInit.ShutInitEnemy();
			}
		}
		this.repaint();
	}
	
	@Override
	//run方法实现了刷屏和调用大家坦克方法
	public void run() {
		//每个100毫秒去重画一次
		while (true)
		{
			try
			{
			Thread.sleep(100);
			//判断我的子弹是否击中敌人的坦克
			this.hitEnemyTankInit();
			//
			this.hitMe();
			
			//重绘
			this.repaint();
		    }
		
		     catch(Exception e)
		     {
		    	 e.printStackTrace();
		     }
		}
	//while循环的大括号	
	}
	//这是没用方法
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}



//坦克类
class TankInit
{
	//设置坦克的速度
	 int speed=4;
	 //设置坦克颜色
	 int color;
	//判断坦克是否还活着
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

	//x表示坦克的横坐标
	 int x=0;
	//坦克的纵坐标
	 int y=0;
	//坦克方向
	//0表示上,1表示右,2表示下,3表示左
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
	//构造函数
	public TankInit(int x,int y)
	{
		this.x=x;
		this.y=y;
	}	
}
//敌人的坦克,把敌人坦克做成线程类
class EnemyTankInit extends TankInit implements Runnable
{
	
	
	int times=0;
	
	//定义一个向量,可以访问到MyPanelInit上所有敌人的坦克
	Vector<EnemyTankInit> ets =new Vector<EnemyTankInit>();
	//定义一个向量,可以存放子弹
	Vector <ShutInit> ss =new Vector<ShutInit>();
	//敌人添加子弹,应该在刚刚创建坦克和敌人坦克子弹死亡后
	
	//构造函数
	public EnemyTankInit(int x,int y)
	{
		super(x,y);
	}
	//得到MyPanelInit的敌人的坦克向量
	public void setEts(Vector<EnemyTankInit> vv)
	{
		this.ets=vv;
	}
	//判断是否碰到了别人的坦克
	public Boolean isTouchOtherEnemy()
	{
		boolean b=false;
		
		
		switch(this.direct)
		{
		////0表示我的坦克向上,1表示右,2表示下,3表示左
			case 0:
				for(int i=0;i<ets.size();i++)
				{
					//取出一个坦克
					EnemyTankInit et =ets.get(i);
					if(et!=this)
					{
						//如果敌人的坦克向上或者敌人的坦克向下
						if(et.direct==0||et.direct==2)
						{
							//需要判断两个点,每个if判断一个点，我的左点
							if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
							{
								b=true;
							}
							else
							{	//我的右点
								if((this.x+20)>=et.x&&(this.x+20)<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
								{
									b=true;
								}
							}
						}
						//如果敌人的坦克向左或者敌人的坦克向右
						if(et.direct==3||et.direct==1)
						{	//我的左点
							if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
							{
								b=true;
							}
							else
							{	//我的右点
								if((this.x+20)>=et.x&&(this.x+20)<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
								{
									b=true;
								}
							}
						}
					}
				}
				break;
				//坦克向右
			case 1:
				for(int i=0;i<ets.size();i++)
				{
					//取出一个坦克
					EnemyTankInit et =ets.get(i);
					if(et!=this)
					{
						//如果敌人的坦克向上或者敌人的坦克向下
						if(et.direct==0||et.direct==2)
						{	//我的上点
							if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
							{
								b=true;
							}
							else
							{	//我的下点
								if((this.x+30)>=et.x&&(this.x+30)<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
								{
									b=true;
								}
							}
						}
						//如果敌人的坦克向左或者敌人的坦克向右
						if(et.direct==3||et.direct==1)
						{	//我的上点
							if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
							{
								b=true;
							}
							else
							{	//我的下一点
								if((this.x+30)>=et.x&&(this.x+30)<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
								{
									b=true;
								}
							}
						}
					}
				}
				break;
			case 2://坦克向下走
				for(int i=0;i<ets.size();i++)
				{
					//取出一个坦克
					EnemyTankInit et =ets.get(i);
					if(et!=this)
					{
						//如果敌人的坦克向上或者敌人的坦克向下
						if(et.direct==0||et.direct==2)
						{	//我的左点
							if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
							{
								b=true;
							}
							else
							{	//我的右点
								if((this.x+20)>=et.x&&(this.x+20)<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
								{
									b=true;
								}
							}
						}
						//如果敌人的坦克向左或者敌人的坦克向右
						if(et.direct==3||et.direct==1)
						{	//我得左点
							if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
							{
								b=true;
							}
							else
							{	//我的右点
								if((this.x+20)>=et.x&&(this.x+20)<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
								{
									b=true;
								}
							}
						}
					}
				}
				break;
			case 3://坦克向左
				for(int i=0;i<ets.size();i++)
				{
					//取出一个坦克
					EnemyTankInit et =ets.get(i);
					if(et!=this)
					{
						//如果敌人的坦克向上或者敌人的坦克向下
						if(et.direct==0||et.direct==2)
						{	//我的上一点
							if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
							{
								b=true;
							}
							else
							{	//我的下一点
								if((this.x)>=et.x&&(this.x)<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
								{
									b=true;
								}
							}
						}
						//如果敌人的坦克向左或者敌人的坦克向右
						if(et.direct==3||et.direct==1)
						{	//上一点
							if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
							{
								b=true;
							}
							else
							{	//下一点
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
			//0表示上,1表示右,2表示下,3表示左
			case 0:
				//说明坦克正在向上移动
				for(int i=0;i<30;i++)
				{
					//if用来控制敌人坦克在指定的范围内移动
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
			
				 //判断子弹是否没有了,子弹可以连发2颗
				 if(this.ss.size()<2)
				 {
					 ShutInit s=null;
					//没有子弹了 添加
					//0表示上,1表示右,2表示下,3表示左
					 switch(this.getDirect())
					 {
					case 0:
						//创建一颗子弹,s仅仅是引用.就像指针.
						s=new ShutInit(this.getX()+10,this.getY(),0);
						//将子弹加入到向量
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
					 
					 //启动子单线程
					 Thread t=new Thread(s);
					 t.start();
				 }
			}
			}
			//让坦克产生一个随机的方向.在这可以控制敌人坦克的移动方向,可以让敌人坦克想自己坦克冲或者看见自己坦克就躲起来
			//Math.random()随机产生一个0到1的小数
			 this.direct=(int)(Math.random()*4);
			 //判断敌人坦克是否死亡
			 if(this.isLive==false)
			 {
				 //让坦克死亡后退出线程,即Linux中的僵尸进程,没有用了,但是还活着.
				 break;
			 }	
		}
	}
}


//我的坦克
class HeroInit extends TankInit
{
	//子弹
	ShutInit s=null;
	//实现子弹连发
	Vector<ShutInit> ss=new Vector<ShutInit>();
	//构造函数
	public HeroInit (int x,int y)
	{
		super(x,y);
	}
	//坦克发射子弹开火
	public void ShutInitEnemy()
	{
		switch (this.getDirect())
		{
		//0表示上,1表示右,2表示下,3表示左
		case 0:
			//创建一颗子弹,s仅仅是引用.就像指针.
			s=new ShutInit(this.getX()+10,this.getY(),0);
			//将子弹加入到向量
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
		//调用子弹的run方法
		t.start();
	}
	//坦克向上移动
	public void moveup()
	{
		y=y-speed;
	}
	//坦克向下移动
	public void movedown()
	{
		y=y+speed;
	}
	//坦克向左移动
	public void moveleft()
	{
		x=x-speed;
	}
	//坦克向右移动
	public void moveright()
	{
		x=x+speed;
	}
}

//子弹类
class ShutInit implements Runnable
{
	//坐标
	int x;
	int y;
	int direct;
	int speed=10;
	//是否还活着
	boolean isLive=true;
	//构造函数
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
			//0表示上,1表示右,2表示下,3表示左
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
			// System.out.println("子弹坐标x="+x+"y="+y.);    //调试用的
			 //子弹死亡条件,500是屏幕的边界
			 if (x<0||x>500||y<0||y>500)
			 {
				 this.isLive=false;
				 break;
			 }
		 }
	}
}

//爆炸类
class  BombInit 
{
	int x,y;
	//炸弹的生命
	int life=9;
	boolean isLive=true;
	//构造函数
	public BombInit(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//减少生命值
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