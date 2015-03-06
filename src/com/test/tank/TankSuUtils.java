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
 * @author    叶昭良
 * @time      2015年3月6日上午10:02:24
 * @version   com.test.tankTankUtils V1.0
 */
public class TankSuUtils
{

}

//就是起提示作用
class  MyStartPanel extends JPanel implements Runnable
{
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 500, 500 );
		
		if(times%2==0)
		{
		//提示信息
		g.setColor(Color.yellow);
		//开关设置字体
		g.setFont(new Font("华文新魏",Font.BOLD,30));
		g.drawString("stage 1", 200, 200);
		
		//安全性问题,防止有人打开一直不玩,防止times 越界
		if(times==1000)
		{
			times=0;
		}
		}
	}
	
	//做开关,控制什么时间画,就会产生闪烁的效果
	int times =0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true)
		{
			//休眠一秒
			try {
				Thread.sleep(10);
				times ++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			//重画
			this.repaint();
		}
		
	}
}
//我的面板
class  MyPanel extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	Hero hero=null;
	
	//判断是继续上局 还是开始新游戏
	String flag="newgame";
	
	//定义敌人的坦克组
	Vector<EnemyTank> ets =new Vector<EnemyTank>();
	
	Vector<Node> nodes =new  Vector<Node>();
	int emsize=Recorder.getEnNum(); //定义坦克数目
	//定义炸弹的炸弹组
	Vector<Bomb> bombs  =new Vector<Bomb>();
	
	//定义三张图片
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//构造函数
	public MyPanel()
	{
		
		//恢复记录,
		Recorder.getRecording();
		hero =new Hero(10,10);
		hero.setX(250);
		hero.setY(440);
		//初始化敌人的坦克
		if(this.flag.equals("newgame"))
		{
		for (int i=0;i<emsize;i++)
		{
			
			//创建敌人的一辆坦克,方向朝下
			EnemyTank et=new EnemyTank((i+1)*20,0);
			et.setDirect(2);
			//将MyPanel的敌人坦克向量交给该敌人坦克
			et.setEts(ets);
			
			
			//启动敌人坦克
			Thread t=new Thread(et);
			t.start();
			//给敌人坦克添加一颗子弹
			shut s=new shut(et.x+10, et.y+30, 2);
			//加入给敌人坦克
			et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			
			//加入敌人的坦克
			ets.add(et);
		}
		}
		else
		{
			for (int i=0;i<nodes.size();i++)
			{
				
				Node node=nodes.get(i);
				//创建敌人的一辆坦克,方向朝下
				EnemyTank et=new EnemyTank(node.x,0);
				et.setDirect(2);
				//将MyPanel的敌人坦克向量交给该敌人坦克
				et.setEts(ets);
				
				
				//启动敌人坦克
				Thread t=new Thread(et);
				t.start();
				//给敌人坦克添加一颗子弹
				shut s=new shut(et.x+10, et.y+30, 2);
				//加入给敌人坦克
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//加入敌人的坦克
				ets.add(et);
			}
		}
		
		
		
		//初始化图片,三张图片才能组成一颗炸弹
		image1 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/0.gif"));
		image2 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/5.gif"));
		image3 =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/10.gif"));
				
	}
	
	
	//画出提示信息,然后调用该函数
	public void showInfo(Graphics g)
	{
	//画出提示信息坦克信息,该坦克不参与战斗
	this.drawTank(100, 520, g, 0, 1);
	//Recorder.getEnNum()+""加上后面的""作用是将int类型隐式转化为String类型
	g.setColor(Color.BLACK);
	g.drawString(Recorder.getEnNum()+"", 130, 540);
	//我的坦克
	this.drawTank(350, 520, g, 0, 0);
	g.setColor(Color.BLACK);
	g.drawString(Recorder.getMyLife()+"", 380, 540);
	
	//画出玩家的总成绩
	g.setColor(Color.BLACK);
	g.setFont(new Font("宋书",Font.BOLD,20));
	g.drawString("您的总成绩:", 540, 40);
	
	this.drawTank(510, 20, g, 0, 1);
	
	g.drawString(Recorder.allkilledEnemy+"", 580, 40);
	}
	
	
	//重写paint
	//paint功能:画出敌人三个坦克,画出自己坦克,,画出炸弹,画出子弹
	public void paint(Graphics g)
	{
		//继承父类,必须有
		super.paint(g);
		//设置背景颜色,500为窗体的大小
		g.fillRect(0, 0, 500, 500);
		
		//画出提示信息
		this.showInfo(g);
		
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.drawTank(et.getX(), et.getY(), g,et.getDirect(), 1);
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
					shut enemyShut =(shut)et.ss.get(j);
					if(enemyShut.isLive)
					{
						 g.fill3DRect(enemyShut.x, enemyShut.y, 3, 3, false);
					}
					else 
					{
						//如果敌人的坦克死亡了,就从向量里面删掉
						et.ss.remove(enemyShut);
					}
				}
			}
		}
		//画出炸弹
		for(int i=0;i<bombs.size();i++)
		{
			//System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");  //做测试用的
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
			
			//划过一次就让life生命值减少一次
			b.life--;
			//如果炸弹生命值为零,就把该炸弹从向量中去掉
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		//画出自己的坦克
		if(hero.isLive==true)
		{
			this.drawTank(hero.getX(), hero.getY(), g,hero.getDirect(), 0);
		}
	   //从ss中取出每一颗子弹,并画出.
	   for(int i=0;i<hero.ss.size();i++)
	   {
		   shut myShut= hero.ss.get(i);
		   if (myShut.isLive==true)
			   {
			   	   //3,3表示画出子弹的长和宽.
				   g.fill3DRect(myShut.x, myShut.y, 3, 3, false);
			   }
		   if (myShut.isLive==false)
		   {
			   //从向量中删除子弹
			   hero.ss.remove(myShut);
		   }
		}
	   //画出子弹  ,画出一颗子弹
	   //isLive=true控制子弹到达边缘的时候,会消失否则不会消失,这个判断来控制内存,减少内存资源的.
//	   if (hero.s!=null&&hero.s.isLive==true)
//	   {
//		   g.fill3DRect(hero.s.x, hero.s.y, 3, 3, false);
//	   }
	}
	
	
	//判断我的子弹是否击中敌人的坦克
	public void hitEnemyTank()
	{
		boolean b=false;
		//因为要随时去判断,所以在这里判断是否击中敌人坦克.
		for(int i=0;i<hero.ss.size();i++)
		{
			//取出子弹
			shut s=hero.ss.get(i);
			//判断子弹是否有效
			if(s.isLive==true)
			{
				//取出每个敌人的坦克,与他判断
				for(int j=0;j<ets.size();j++)
				{
					//取出敌人坦克
					EnemyTank et =ets.get(j);
					if (et.isLive==true);
					{
						b=this.hitTank(s, et);
						
						if(b==true)
						{
							//减少敌人坦克数量
							Recorder.reduceEnNum();
							//增加杀死敌人坦克数量
							Recorder.addKilledEnemy();
							
						}
						
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
			EnemyTank et=ets.get(i);
			{
				//取出子弹
				for(int j=0;j<et.ss.size();j++)
				{
					shut enemyshot=et.ss.get(j);
					//if控制我的坦克被击中后不会反复爆炸
					if(hero.isLive)
					{
						this.hitTank(enemyshot, hero);
					}
				}
			}
		}
	}
	//写一个函数专门判断子弹是否集中敌人坦克.
	public boolean  hitTank(shut s,Tank et)
	{
		boolean b2=false;
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
					b2=true;
					Bomb b=new Bomb(et.x,et.y);
					bombs.add(b);
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
					b2=true;
					Bomb b=new Bomb(et.x,et.y);
					bombs.add(b);
				}
				
				break;
		}	
		}
		return b2;
	}
	
	
	//画出坦克的函数
	//我的坦克类型是0;敌人坦克类型是1
	public void drawTank (int x,int y ,Graphics g,int direct ,int type)
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
			this.hero.setDirect(0);
			this.hero.moveup();
			
		}	else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			//向下
			this.hero.setDirect(2);
			this.hero.movedown();
		}else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			//向左
			this.hero.setDirect(3);
			this.hero.moveleft();
		}else	if(e.getKeyCode()==KeyEvent.VK_D)
		{
			//向右
			this.hero.setDirect(1);
			this.hero.moveright();
		}
		if (e.getKeyCode()==KeyEvent.VK_J)
		{
			//判断玩家是否按下J
			//开火
			//控制子弹最多五颗
			if (this.hero.ss.size()<5)
			{
				this.hero.shutEnemy();
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
			this.hitEnemyTank();
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



class Node
{
	int x ;
	int y;
	int direct;
	//构造函数
	public Node(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
}
//记录类,同时也可以保存玩家的设置
class Recorder
{
	//记录每关有多少敌人
	private static int enNum=20;
	//我有多少可用的人
	private static int myLife=3;
	//记录总共消灭多少敌人
	public static int allkilledEnemy=0;
	//从文件中恢复记录点

	
	
	//完成对文件里面的坦克的读取
	public static Vector<Node> getNodesAndEnemyNum()
	{
		 Vector<Node> nodes =new Vector<Node>();
		try {
			fr=new FileReader("e:\\myRecording.txt");
			
			br=new  BufferedReader(fr);
			try {
				//先读取第一行.
				String str =br.readLine();
				//将字符串转化为int类型的方法
				if(str!=null)
				{
				Recorder.allkilledEnemy =Integer.parseInt(str);
				}
				
				
				while((str=br.readLine())!=null)
				{
					//这就话就是把str字符串按照空格分成一个字符串数组.
					String []xyz =str.split(" ");
					//在这我已经敢肯定就是劈成了三个
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
	
	//减少敌人的数量
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
	//把玩家击毁敌人坦克的数量保存到文件中
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
				//后打开先关闭的原则
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
	}
	
	//把玩家击毁敌人坦克的数量从文件中恢复回来
	public static void getRecording()
	{
		
		try {
			fr=new FileReader("e:\\myRecording.txt");
			
			br=new  BufferedReader(fr);
			try {
				String str =br.readLine();
				//将字符串转化为int类型的方法
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
	

	//保存击毁敌人坦克的数量和敌人坦克的坐标
	public static void  keepRecordAndEnemyTank()
	{
		
		try {
			fw =new FileWriter("e:\\myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allkilledEnemy+"\r\n");
			
			//保存当前活着的坦克的坐标和方向
			for(int i=0;i<ets.size();i++)
			{
				//去吃第一个坦克
				EnemyTank et =ets.get(i);
				if(et.isLive)
				{
					//活的就保存
					String recorde=et.x+"  "+et.y+"  "+et.direct;
					//写入,
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
				//后打开先关闭的原则
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}
	
	
	
//坦克类
class Tank
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
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}	
}
//敌人的坦克,把敌人坦克做成线程类
class EnemyTank extends Tank implements Runnable
{
	
	
	int times=0;
	
	//定义一个向量,可以访问到MyPanel上所有敌人的坦克
	Vector<EnemyTank> ets =new Vector<EnemyTank>();
	//定义一个向量,可以存放子弹
	Vector <shut> ss =new Vector<shut>();
	//敌人添加子弹,应该在刚刚创建坦克和敌人坦克子弹死亡后
	
	//构造函数
	public EnemyTank(int x,int y)
	{
		super(x,y);
	}
	//得到MyPanel的敌人的坦克向量
	public void setEts(Vector<EnemyTank> vv)
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
					EnemyTank et =ets.get(i);
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
					EnemyTank et =ets.get(i);
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
					EnemyTank et =ets.get(i);
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
					EnemyTank et =ets.get(i);
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
					 shut s=null;
					//没有子弹了 添加
					//0表示上,1表示右,2表示下,3表示左
					 switch(this.getDirect())
					 {
					case 0:
						//创建一颗子弹,s仅仅是引用.就像指针.
						s=new shut(this.getX()+10,this.getY(),0);
						//将子弹加入到向量
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
class Hero extends Tank
{
	//子弹
	shut s=null;
	//实现子弹连发
	Vector<shut> ss=new Vector<shut>();
	//构造函数
	public Hero (int x,int y)
	{
		super(x,y);
	}
	//坦克发射子弹开火
	public void shutEnemy()
	{
		switch (this.getDirect())
		{
		//0表示上,1表示右,2表示下,3表示左
		case 0:
			//创建一颗子弹,s仅仅是引用.就像指针.
			s=new shut(this.getX()+10,this.getY(),0);
			//将子弹加入到向量
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
class shut implements Runnable
{
	//坐标
	int x;
	int y;
	int direct;
	int speed=10;
	//是否还活着
	boolean isLive=true;
	//构造函数
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
			// System.out.println("子弹坐标x="+x+"y="+y);    //调试用的
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
class  Bomb 
{
	int x,y;
	//炸弹的生命
	int life=9;
	boolean isLive=true;
	//构造函数
	public Bomb(int x,int y)
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
