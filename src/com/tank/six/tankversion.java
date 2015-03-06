package com.tank.six;


/*
 * date : 2012-9-27
 * author : Heroang
 * function : 坦克界面制作  子弹会飞了！   并且把 bullet s =new bullet();换成向量 可以进行连发！
 * 				最多连发5颗
 * 
 * 				继续实现子弹到的时候可以摧毁坦克！ （为什么要做成函数！） isLive属性！
 * 
 * 				当击中后  坦克会有爆炸的效果
 * 
 * 使敌人坦克自幼随机活动   
 * 
 * 保证敌人坦克不出边界    if(x>0)'
 * 
 * 
 * 让敌人机器也能发射子弹！   并使敌人发射的子弹能够hitme（发生在面板上！）
 * 并设定我的作用域
 *  为了增加可读性 把hittank的判断设置成一个函数    public void HitEnemyTank()
 *    public void HitHeroTank()
 *    有一个bug 己方坦克太容易死了！  怎么办   还有就是如果己方坦克死了是不能发射子弹的(当然这个很简单 只要改变J建的判别条件即可)
 *    
 *    升级我的hittank属性
 *    
 *    为什么设置IsTouchEnemy放在EnemyTank里卖弄？
 *      想法：让其自己判断   首先这个行为应该是让每一辆新建的坦克自己去判断！ 而不是放在其他地方    至于我方坦克由我们自己控制
 *      	所以最后决定放在EnemyTank中！
 *    
 *    （2）新增如下功能
 *     1可以分关
 *       1.1专门做一个空的Jpanel  起提示功能
 *       1.2字体做闪烁  就一句话而已！  闪烁 ： 一会画出字来  一会不画出来！
 *       		由于是出现不同的情况！ 即时闪时不闪所以涉及到时间的问题！  应该是跟时间有关！  设计成一个线程  不同的时间内坐标发生改变！
 *     2可以暂停和继续
 *     
 *       当用户点击暂停时 ： 子弹 和 坦克的速度为 0   并且方向设为不变 即可  
 *       				当然在之前必须先保存  子弹和坦克的。。。。好像不用
 *     3可以记录玩家的成绩
 *     	3.1文件流 （单机版都是文件流的  除非是大型游戏是数据库  CS 网游）
 *      3.2单写一个记录类
 *     4java操控声音文件
 *     
 *     
 *     date : 2012-9-29
 *     function: 最重要的1.Record类    2.增加分关处理     3.保存对话框    4.以及一个flags标志！
 *     问题：什么时候保存    什么叫做一关 所有敌人都没有的时候！
 *     
 *     增加了一个帮主窗口类
 *     
 *     
 *      date:2012-9-30
 *     解决了bug之后  就想着如何添加声音  本版本将升级到有声音的版本 
 *     
 *     date: 2012-10-2
 *     新增了一个声音播放类  只限wav文件
 *     
 *     UImageer   Load的判定if(this.mp3 != null)
			{
				System.out.println("是否进入旧mp3");
				this.remove(mp3);
			}
 *    
 *     主要是在 MyPanel2() 修改了声音的函数
 *     问题1: 为什么切换到帮助菜单！ 则没有声音了！（构造函数的原因？）
 *     问题2： 为什么新游戏没有清除掉原来的坦克 //// 已解决 删除mp3 即可
 *     问题3： 为什么加入音乐后，速度明显变慢
 *     
	1：画图一句话： Graphics对象
	2：加载图片一句话   ImageIo.write("images/...");
	    当然BufferedImage对象也需要用Graphics对象g.drawImage才可以画出来！
	    
	    
	    新增了一个door参数，进行关卡的设置
	    
	    door是一个十分重要的参数！！
	     *     date : 2012-10-3
 *     想要实现分关
 *     我方敌人坦克移速一直为2   我方子弹移动速度也一直为1
 *     1关  敌方坦克数 3  敌方坦克移动速度1  敌方子弹移动速度1     
 *     2关  敌方坦克数6  敌方坦克移动速度2  敌方子弹移动速度2
 *     3关  敌方坦克数8  敌方坦克移动3  敌方子弹移动速度3
 *     
 *     设置一个变量stage
 * 
 * 
 * 
 * 
 * 
 *   首先得把坦克绘制连上1天
 *   
 *   然后把多线程连上半天
 *   
 *   然后把数据结构 集合 容器 连上半天
 *   
 *   再把
 *   
 *   
 *   想着把文本信息 直接利用ORM保存坦克 子弹  炸弹  对象到数据库中去
 *   
 *   坦克表 
 *       右方坦克表
 *       敌方坦克表
 *   子弹表
 *   炸弹表
 *   
 *   
 *   缺陷：命名不规范！ 逻辑不清楚
 *   
 *    我总觉得有一点不爽   不应该把所有的东西都放在paint函数中进行重画！ 而应该在
 *    每生成一个Tank的时候进行重画  会比较好些。  这也是下一步的目标
 *    在新的Tank类中定义
 *            PositionX
 *            PositionY
 *            Direction  ,放在setDirection比较好！ 不要让构造函数的参数太多
 *            Speed       setSpeed
 *            isLive      setIsLive
 *            isTurn      setIsTurn
 *            
 *     
 *    让子弹飞吧！！！自己飞！所以必须有线程，炸弹是因为原地所以不需要移动，就不要线程
 *                                       右方通过案件进行控制！所以也不需要
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

	//定义所需组件
	static MyPanel2 mp3 = null;
	
	//定义一个开始面板
	static MyStartPanel msp = null;
	//定义一个菜单条
	JMenuBar jmb = null;
	//定义一个菜单树枝
	JMenu jm1 = null;
	//帮助菜单
	JMenu jm2 = null;
	//定义3个菜单树叶    一个存盘退出   一个是打开存盘的文件 继续游戏

	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	
	//暂停
	JMenuItem jmi4 = null;
	//继续
	JMenuItem jmi5 = null;
	
	//创建新游戏
	JMenuItem jnew = null;
	JMenuItem jexit = null;
	
	//帮助的操作说明
	JMenuItem jmi21 = null;
	
///////////////////////////////////////////关卡设定开始//////////////////////////
//设置一个关数的变量
static int door = 1;
//用一个变量显示敌人的机器数目
static int enSize =6;


//从MyPanel移到 这边 用来监视敌机的数目
//定义一个Vector的数组用来储存地方的战机
static Vector<EnemyTank> eet = new Vector<EnemyTank>();
//不同关的官邸配置信息
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

//更改原先的敌人子弹容器为静态变量
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
// 	 UIManager.put("MenuBar.font",new  Font("宋体",Font.PLAIN,10));
//	     UIManager.setLookAndFeel("org.fife.plaf.Office2003.Office2003LookAndFeel");
//      UIManager.setLookAndFeel("org.fife.plaf.OfficeXP.OfficeXPLookAndFeel");
//      UIManager.setLookAndFeel("org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel");
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
		//UIManager是为了美观
		tankversion tk2 = new tankversion();
		//System.out.println("我在while"+tankversion.eet.size());
		if(tankversion.eet.size() ==0 && tankversion.door <4)
		{
			//System.out.println(tankversion.eet.size());
			tankversion.SetStage(tankversion.door);
//				if(door == 1){
			//if(ee)
			if(tankversion.mp3 != null)
			{
				System.out.println("是否进入旧mp3");
				tankversion.mp3 = null;
				
			}
			if(tankversion.msp !=null)
			{
				tankversion.msp = null;
			}
			tankversion.msp = new MyStartPanel();
//					door++;
			//添加所需组件
			//添加开始面板
			/*this.add(msp);
			//得加上这句
			this.setVisible(true);*/
			//设置一个线程并开始
			Thread t1 =new Thread(msp);
			t1.start();
		}
			
		
		
	}
	
	public tankversion()
	{
		//创建所需组件
		
		//创建一个开始面板
		msp = new MyStartPanel();
		//创建所需组件
		jmb = new JMenuBar();
		jm1 = new JMenu("文件(F)");
		//设置文件助记符
		jm1.setMnemonic('f'); //不能用“” 只能用单引号把其括起来
		
		//添加所需组件
		//添加开始面板
		this.add(msp);
		Thread t1 =new Thread(msp);
		t1.start();
		jmi1 = new JMenuItem("存盘退出(x)");
		jmi1.addActionListener(this);
		
		jmi2 = new JMenuItem("恢复！(R)");
		jmi2.addActionListener(this);
		
		jmi3 = new JMenuItem("统计得分");
		jmi3.addActionListener(this);
		
		jmi4  =  new JMenuItem("暂停(P)");
		jmi4.setMnemonic('P');
		jmi4.addActionListener(this);
		jmi4.setActionCommand("pause");
		
		
		jmi5  =  new JMenuItem("继续(C)");
		jmi5.setMnemonic('C');
		jmi5.addActionListener(this);
		jmi5.setActionCommand("continue");
		jmi5.setEnabled(false);
		
		jnew  =  new JMenuItem("新游戏(N)");
		jnew.setMnemonic('N');
		jnew.addActionListener(this);
		jnew.setActionCommand("newgame");
		
		jexit  =  new JMenuItem("退出(E)");
		jexit.setMnemonic('E');
		jexit.addActionListener(this);
		jexit.setActionCommand("exit");
		
		
		jm2=new JMenu("帮助(H)");
	    jm2.setMnemonic('h');
	    jmi21=new JMenuItem("操作说明(U)");
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
		//把下面的代码转移到 新游戏中
		/*mp3 = new MyPanel2(); //现在不能在这边添加组建了  这样的话就没有任何坦克了
		//布局管理器 
		
		//添加组件
		this.add(mp3);
		//设置窗口属性
		Thread t3 = new Thread(mp3);  //相当于画面一启动 就开始绘制这个画面！
		t3.start();
		//设置JFrame被监听
		this.addKeyListener(mp3);*/
		this.setTitle("坦克大战原始改装");
		this.setSize(400,400);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//显示窗口
		this.setVisible(true);
	}

	public void checktank()
	{
		int exitChoose = JOptionPane.showConfirmDialog(this, "需要退出吗？", "退出提示", JOptionPane.OK_CANCEL_OPTION);
		if (exitChoose == JOptionPane.OK_OPTION) 
		{
			recorddata rd = new recorddata();
			rd.writeRecord();
			//跳出保存成功窗口
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
		//存盘退出
		if(e.getSource() == jmi1){ 
//			System.out.println("已被监听");
			//调用保存游戏函数
			recorddata.writeRecord ();
			//跳出保存成功窗口
			 ReSuccess re=new  ReSuccess();
			 //退出
			 System.exit(0);

			 
		//恢复  load
		}else if(e.getSource() == jmi2)
		{
			if(this.mp3 != null)
			{
				System.out.println("是否进入旧mp3");
				this.remove(mp3);
			}
			//再加入之前 首先得删除旧的 这是第一步
			if(this.msp !=null){
			this.remove(msp);
			}
//			System.out.println("已被监听");
			recorddata.flags = "load";
			recorddata.RecordRead();
			
			//再加入之前 首先得删除旧的 这是第一步
			this.remove(msp);
			mp3 = new MyPanel2(); //现在不能在这边添加组建了  这样的话就没有任何坦克了
			//布局管理器 
			
			//添加组件
			this.add(mp3);
			//设置窗口属性
			Thread t3 = new Thread(mp3);  //相当于画面一启动 就开始绘制这个画面！
			t3.start();
			//设置JFrame被监听
			this.addKeyListener(mp3);
			//让你的面板可见    这是第二步   缺一不可
			this.setVisible(true);
			
		}else if(e.getSource() == jmi3){
			System.out.println("已被监听");
		}else if(e.getActionCommand().equals("newgame"))
		{
			
			//也可以在这边读取数据！  或者在MYpanel中读取数据
			recorddata.flags = "newgame";
			//recorddata.RecordRead();  //这句话一点用 都没有
			
		    recorddata.setEnNum(20);
		    recorddata.setMylife(3);
		    recorddata.setKillenNum(0);

			//再加入之前 首先得删除旧的 这是第一步
			//this.remove(msp);
			if(this.mp3 != null)
			{
				System.out.println("是否进入旧mp3");
				this.remove(mp3);
			}
			//再加入之前 首先得删除旧的 这是第一步
			if(this.msp !=null){
			this.remove(msp);
			}
			mp3 = new MyPanel2(); //现在不能在这边添加组建了  这样的话就没有任何坦克了
			//布局管理器 
			
			//添加组件
			this.add(mp3);
			//设置窗口属性
			Thread t3 = new Thread(mp3);  //相当于画面一启动 就开始绘制这个画面！
			t3.start();
			//设置JFrame被监听
			this.addKeyListener(mp3);
			//让你的面板可见    这是第二步   缺一不可
			this.setVisible(true);
		}else if(e.getActionCommand().equals("pause"))
		{
			//可以一整个类击中设置  这是我第一次学到的   但是应该必须是static 属性才可以  这样类的属性才会被继承
			//证实的确是这样 
			bullet.setSpeed(0);
			Hero.setSpeed(0);
			EnemyTank.setSpeed(0);
			
			EnemyTank.setIsTurn(false);
			Hero.setCanTurn(false);
			jmi4.setEnabled(false);
			jmi5.setEnabled(true);
		}else if(e.getActionCommand().equals("continue"))
		{
			bullet.setSpeed(1);  //这边可以继续设置速度
			Hero.setSpeed(1);
			EnemyTank.setSpeed(1);
			
			EnemyTank.setIsTurn(true);
			
			Hero.setCanTurn(true); 
			jmi4.setEnabled(true);
			jmi5.setEnabled(false);//然后可以对MyHero设置w s a d即可     而对于EnemyTank可以去run线程中！设置if(EnemyTank.s)
		}else if(e.getActionCommand().equals("exit"))
		{
			
			//退出游戏
			//并且保存击毁敌人的数目   也就是说这边应该是有一个写入文件流的行为！  而这个动作最好是由Recorddata来完成
		/*	recorddata.writeRecord();
			System.exit(0);*/
			//新版本的方法
			int exitChoose = JOptionPane.showConfirmDialog(this, "需要保存数据吗？", "退出提示", 

			JOptionPane.OK_CANCEL_OPTION);
			if (exitChoose == JOptionPane.OK_OPTION) {
				recorddata rd = new recorddata();
				rd.writeRecord();
				//跳出保存成功窗口
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
	//把run函数写在这边没有效果！
	
	//因为这边的run根本就不反应  得放在Tank所在的那个run当中才可以
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
		while(door <4)
		{
			System.out.println("我在while"+tankversion.eet.size());
			if(tankversion.eet.size() ==0)
			{
				System.out.println(tankversion.eet.size());
				tankversion.SetStage(tankversion.door);
//				if(door == 1){
				//if(ee)
				if(this.mp3 != null)
				{
					System.out.println("是否进入旧mp3");
					this.remove(mp3);
					
				}
				if(this.msp !=null)
				{
					this.remove(msp);
				}
				msp = new MyStartPanel();
//					door++;
				//添加所需组件
				//添加开始面板
				this.add(msp);
				//得加上这句
				this.setVisible(true);
				//设置一个线程并开始
				Thread t1 =new Thread(msp);
				t1.start();
			}

		}/*else if(door == 2){
				
			}else if(door == 3){
				
			}*/
			//应该放在这边  不对应该放在每一次的所有坦克结束的地方   然后利用door来进行判断
//			door++;
//			if(recorddata.getKillenNum() == tankversion.enSize){
//				tankversion.door++;
//			}

	}
}

//新定义一个开始面板
class MyStartPanel extends JPanel implements Runnable
{
	int times = 0 ;//用来控制重画的次数！
	public void paint(Graphics g)
	{
		
		
		super.paint(g); //这是继承JPanel的第一步
		g.fillRect(0,0,420,400);
		if(times%2 == 0 )
		{
		g.setColor(Color.yellow);
		Font newFont = new Font("华文新魏",Font.BOLD,30);
		g.setFont(newFont);
		// 最后一次改进的地方！！关卡的设置
		g.drawString("Stage: "+tankversion.door, 160, 180);
		}
		if(times > 1000)
		{
			times = 0; //害怕有一个傻逼 一直再看着傻逼而不玩游戏   所以不想再让 times 一直增加了
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
			}//上来先睡他一秒    然后在重画！   这是一个技术
			times++;
			this.repaint();
		}
	}
}
//////////////////////////////////////////////////////////////////////
//定义一个结束面板(当hero.life == -1时     放在hero寿命减少的地方)
class MyEndPanel extends JPanel implements Runnable
{
	int times = 0 ;//用来控制重画的次数！
	public void paint(Graphics g)
	{
		super.paint(g); //这是继承JPanel的第一步
		g.fillRect(0,0,420,400);
		if(times%2 == 0 )
		{
			g.setColor(Color.yellow);
			Font newFont = new Font("华文新魏",Font.BOLD,30);
			g.setFont(newFont);
			g.drawString("游戏已经结束\n如果还想玩请点击新游戏！", 160, 180);
		}
		if(times > 1000)
		{
			times = 0; //害怕有一个傻逼 一直再看着傻逼而不玩游戏   所以不想再让 times 一直增加了
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
			}//上来先睡他一秒    然后在重画！   这是一个技术
				times++;
				this.repaint();
		}
	}
}




///结束面板定义结束
/////////////////////////////////////////////////////////////////
//定义一个通关面板（当door==4时）
class MyCongratulationPanel extends JPanel implements Runnable
{
	int times = 0 ;//用来控制重画的次数！
	public void paint(Graphics g){
	super.paint(g); //这是继承JPanel的第一步
	g.fillRect(0,0,420,400);
	if(times%2 == 0)
	{
		g.setColor(Color.yellow);
		Font newFont = new Font("华文新魏",Font.BOLD,30);
		g.setFont(newFont);
		g.drawString("恭喜你通关了!\n可以下山当俗家弟子了\n顺便取个老鼠婆  \n生一大堆老鼠儿子", 160, 180);
	}
	if(times > 1000)
	{
		times = 0; //害怕有一个傻逼 一直再看着傻逼而不玩游戏   所以不想再让 times 一直增加了
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
			}//上来先睡他一秒    然后在重画！   这是一个技术
				times++;
				this.repaint();
			}
		}
	}



///通关面板定义结束 

/// 上面的关卡开始到结束是主要  的修改部分！（仅限最后一次） 
//  另外还有tankversion.enSize


//定义一个画板 用来给Tank游走
class MyPanel2 extends JPanel implements KeyListener,Runnable
{
	
	
	//小类中  类似大类Tank1的定义所需组件一样
	Hero zl = null;   //一般是在类中定义  在构造函数中创建  添加  和设置面板属性！  跟JFrame类不同的是 不需要显示
	//初始化JPanel的绘图函数paint  相当于获得一根画笔g
//	int[] xaxis = {zl.bl.x,zl.bl.x+3/2,zl.bl.x+3};
//	int[] yaxis = {zl.bl.y,zl.bl.y+3,zl.bl.y};
//	int[] xaxis = {3,4,10};
//	int[] yaxis = {10,5,10};
	/*
	 * 由于刚刚学完文件的IO编程！ 所以用这个一下
	 * */



	
	
	Vector<bome> bomes = new Vector<bome>();
	
	//定义三张图片
	BufferedImage im1 = null;
	BufferedImage im2 = null;
	BufferedImage im3 = null;  //该在哪边创建呢？  不应该有c语言中的    声明和定义的思想   而应该是  定义和创建的思想
	 
	public void paint(Graphics g)
	{
		super.paint(g);
		
		//一般都是在paint函数中进行绘制图形类的东西
		
		//开始在MyPanel的Paint函数绘制我的坦克
			//画出坦克  然后到最后包装称一个函数
		//先画左边矩形
		
		//背景颜色设置为黑色不要灰色的 不好看！
		g.fillRect(0, 0, 420, 400);//默认面板是黑色的背景   而JFrame默认是灰色的背景
	
//提示面板的信息
		//提示信息的敌人坦克     封装函数的思想所以把这几行封装成一个showinfo信息
		showinfo(g);
		
		//画出子弹  //必须加上 zl.bl!=nulll    我觉得太奇怪了！　不然会爆出空指针问题！
		//而且在这个底下还是不能调用  zl.getX()  和zl.getY()  真是让我不理解的
		for(int i =0;i<zl.vt.size();i++)
		{
			if(zl.vt.get(i)!=null &&zl.vt.get(i).isLive ==true)
			{
				drawbullet(zl.vt.get(i).x,zl.vt.get(i).y,g,zl.vt.get(i).getDirection(),0);
//				System.out.println(zl.vt.get(i)+"子弹当前坐标"+zl.vt.get(i).y);
			}
			//if(zl.vt.get(i).isLive == false)//就可以把它从里面删除了！
		}



		//画出己方坦克   不同的对象不能上下级混用！
//		if(zl.bl.isLive == true){
//			drawTank(zl.getX(),zl.getY(),g,zl.getDirect(),zl.getType());
//		}
		if(zl.isLive)
		{
			drawTank(zl.getX(),zl.getY(),g,zl.getDirect(),zl.getType());
		}
		//忘记吧这边进行修改了  你不动肯定是某个地方保持不变了  如果是方向 就找direction  如果是速度就找speed 
		//如果是类型就找type
		//这样一经封装我只需要在这边的4 5参数位 改变他的direction和type 就可以修改颜色 机型了！
//		drawbullet(zl.getX(),zl.getY(),g,zl.bl.getDirection());
		//画出敌机
		//新建三个线程给了敌机！
		
	//这边有问题  eet还没有值 为什么有值？
		for(int i = 0;i<tankversion.eet.size();i++)
		{
			//System.out.println(tankversion.eet.size());
			EnemyTank temp =tankversion.eet.get(i);
		
//			temp.setDirect(2);  //这边设置了方向固定肯定是一直跑
			temp.setType(1);
			if(temp.isLive == true)
			{
				drawTank(temp.getX(),temp.getY(),g,temp.getDirect(),temp.getType());
	//			temp.shootHero();  //用于给坦克装上炸弹！  放错地方了  这边是绘制 创建要放在构造函数中
					//取出子弹
					for(int j = 0 ; j<temp.ss.size(); j++)
					{
						bullet tempbl = temp.ss.get(j);
						if(tempbl.isLive)
						{ //绘制子弹
							drawbullet(tempbl.x,tempbl.y,g,tempbl.getDirection(),1);
	//						System.out.println("第"+(i)+"辆坦克的第"+(j+1)+"颗子弹的"+"坐标是　ｘ＝"+tempbl.x+" y ="+tempbl.y);
						//通过 可以查看每一辆坦克第几颗子弹的坐标[]   但是这边一点用都没有！
							//经验：	
						}else
						{
							//把子弹给删掉 当死的时候就得清除  留着坑给别人用
	//						eet.remove(tempbl);//这是删除坦克  应该是删除子弹
							temp.ss.remove(tempbl);  //可以了这句话真的是奏效！
						}
						
					}
			}
//			System.out.println("敌机方向"+temp.getDirect());//这边无法进行判断
//			}
			//不应该在这边放上线程！  应该在构造函数中
//			Thread t = new Thread(temp);
//			t.start(); 
			
//			

		}
		
//		//画敌方子弹
//		for(int  i=0 ; i<eet.size(); i++){
//			EnemyTank temp = eet.get(i);
//			for(j = 0 ; i < eet.get(i))
//		}
		
		//开始画炸弹
		for(int i = 0 ; i<bomes.size() ; i++)
		{
			//取出炸弹
			System.out.println("炸弹的名字是："+bomes.lastElement()+"bomes.size = "+bomes.size());  //调试看看是不是炸弹！没进来
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
			//如果炸弹的生命值为0  则从炸弹的向量中去掉
			if(b1.life == 0)
			{
//				b1.isLive = false;
				bomes.remove(b1);
			}
		}
		
		//利用implements接口的方式实现线程  要是通过继承的方式的话行不通  因为本身Enemytank就extends坦克类  java是单继承属性
		//暂时先不让线程开启  实验一把是否会让子弹击毁目标
/*		Thread t3 =new Thread(eet.get(0));
		Thread t4 =new Thread(eet.get(1));
		Thread t5 =new Thread(eet.get(2));
		t3.start();
		t4.start();
		t5.start();*/
		
	}
	
	//提示信息封装函数
	public void showinfo(Graphics g)
	{
		drawTank(70,420,g,0,1);
		
//		g.drawString(recorddata.getEnNum(),100,420); //转化为String类型
		g.setColor(Color.black);
		g.drawString(recorddata.getEnNum()+" ",100,440);
		//用于显示友方坦克的信息
		drawTank(130,420,g,0,0);
		g.setColor(Color.black);//不然再画己方坦克的时候已经改变了画笔颜色
		g.drawString(recorddata.getMylife()+" ",160,440);
		
		
		
		//在整个画面的右边画出玩家的总成绩
		Font font = new Font("宋体",Font.BOLD,20);
		g.setFont(font);
		g.drawString("你老的总成绩：", 420, 100);
		
		drawTank(440, 140, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(recorddata.getKillenNum()+"", 470, 165);
	}
	//画出子弹
	//调用方式 ： drawbullet(zl.bl.x,zl.bl.y,g,0/1/2/3);

	public void drawbullet(int x,int y,Graphics g,int direction,int type)
	{
		//画出子弹
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
		//换一种思路 利用三点画出三个线段不就是可以了嘛？
		//第一个点
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
	    //判断是什么阵营的坦克
		switch(type)
	     {
	    	 case 0:
	    		 g.setColor(Color.RED);//我方坦克颜色
	    		 break;
	    	 case 1:
	    		 g.setColor(Color.yellow);//地方坦克颜色
	    		 break;
	     }
	     switch(direction){
	     //向上：
	     case 0: //向上
	     {
	    	 
	    	 g.fill3DRect(x,y, 5, 30,false);//左边轮带
	 		 g.fill3DRect(x+15, y, 5, 30,false); //右边的轮带
	 		 g.fill3DRect(x+5, y+5, 10, 20,false);//机身
	 		 g.fillOval(x+5, y+10, 10, 10); //机身圆盖
	 		 g.drawLine(x+10, y+15, x+10, y);//镭射钢炮
	     }
	     break;
	     case 1: //向右
	     {
	    	 g.fill3DRect(x,y, 30, 5,false);//上边轮带
	 		 g.fill3DRect(x, y+15, 30, 5,false); //下边的轮带
	 		 g.fill3DRect(x+5, y+5, 20, 10,false);//机身
	 		 g.fillOval(x+10, y+5, 10, 10); //机身圆盖
	 		 g.drawLine(x+15, y+10, x+30, y+10);//镭射钢炮
	     }
	     break;
	     case 2://向下
	     {
	    	 g.fill3DRect(x,y, 5, 30,false);//左边轮带
	 		 g.fill3DRect(x+15, y, 5, 30,false); //右边的轮带
	 		 g.fill3DRect(x+5, y+5, 10, 20,false);//机身
	 		 g.fillOval(x+5, y+10, 10, 10); //机身圆盖
	 		 g.drawLine(x+10, y+15, x+10, y+30);//镭射钢炮
	     }
	     break;
	     case 3: //向左
	     {
	    	 g.fill3DRect(x,y, 30, 5,false);//上边轮带
	 		 g.fill3DRect(x, y+15, 30, 5,false); //下边的轮带
	 		 g.fill3DRect(x+5, y+5, 20, 10,false);//机身
	 		 g.fillOval(x+10, y+5, 10, 10); //机身圆盖
	 		 g.drawLine(x+15, y+10, x, y+10);//镭射钢炮
	     }
	     break;
	     
	     }
		}
	
	public void HitEnemyTank()
	{
		//下面二重for循环的目的在于控制 他的 isLive 参数  然后传递给drawTank函数  谁的islive 子弹的和敌人坦克的
		for(int i = 0 ; i < zl.vt.size() ; i++)
		{
			//取出子弹
			bullet bt = zl.vt.get(i);
			//判断子弹是否存活
			if(bt.isLive == true)
			{
				for(int j = 0 ; j < tankversion.eet.size() ; j++) //不要写成 i++了 好的敌人坦克组是定义在Mypanel中
				{
					//取出坦克
					EnemyTank em = tankversion.eet.get(j); //j写成了  i 服了
					//判断坦克是否死亡
					if(em.isLive == true)
					{
						hitTank(bt,em);
					}
				}
			}
		}
	}
//执行hitme 函数
	public void HitHeroTank(){
		for(int i = 0 ; i < tankversion.eet.size(); i++)
		{
			//取出坦克
			EnemyTank temp = tankversion.eet.get(i);
			//循环取出此辆坦克的子弹
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
//		this.setJMenuBar(jmb)  这个对于JPanel不行  因为不继承JFrame
		
		zl = new Hero(200, 300,0);  //这边其实是原始的x y的设置点 后来一直被后人用 zl.getX zl.getY进调用
		//初始化敌方的战机    //  找了好久终于找到额
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
			//如果把这两句线程放在paint函数中  会导致线程一直是重新开启   所以记得开启线程一般是在构造函数中
			Thread t = new Thread(ent);
			t.start(); 
			bullet bl = null;
			//现在把这几行放到run函数中 不然这边只能就让地方发射一颗子弹   构造函数只能构造一次  而我们需要不断的run
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
			//加入敌人坦克
		    ent.ss.add(bl);
		     //一定要自己开启子弹线程   因为别人是不会管他的！让子弹自己飞去吧！
		    Thread t1 = new Thread(bl);
		    t1.start();
			
			//把它传给每一个EnemyＴａｎｋ
			ent.setEts(eet);
			recorddata.setEnems(eet);
			eet.add(ent);
//			ent.shootHero();
			//给敌人坦克添加一颗子弹
		}
			
			
		}else if(recorddata.flags.equals("load"))
		{
			 //敌机初始化完毕   那就得回到print函数把它画出来
			
			recorddata.RecordRead();
			System.out.print(recorddata.nodes.size() );
		  for (int i = 0; i <recorddata.nodes.size() ; i++)
		  {
			  TankM node=recorddata.nodes.get(i);
		    EnemyTank enemy = new EnemyTank(node.x, node.y,node.direct);
//		    enemy.setDirect(node.direct);
		    //传到EnemyTank 应该叫做集体传送到EnemyTank  
		    enemy.setEts(eet); //这边要进行适当修改
		    //传到GameRecord
		    recorddata.setEnems(eet);
		    Thread th=new Thread(enemy);
		    th.start();
		    this.eet.add(enemy);
		  }

		}
		
		//初始化图片 创建图片
		im1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		im2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		im3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
		try {
			im1 = ImageIO.read(new File("./images/bomb_1.gif"));
			im2 = ImageIO.read(new File("./images/bomb_2.gif"));
			im3 = ImageIO.read(new File("./images/bomb_3.gif"));  //居然这样可以 放图片在images中
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
				//击中
				//子弹死亡
				bl.isLive = false;
				//敌人坦克死亡
				em.isLive = false;
				
				
		//裁判也过来做丧事  我他妈怎么在计算机中也看到了老封建！操
				recorddata.reduceenNum();
				recorddata.addkillnum();
				//如果坦克死了  我们开始创建导弹
				//创建一个炸弹对象
				bome b1 = new bome(em.x,em.y);
				//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
				bomes.add(b1);
			}
		
		}
		break;
		case 1:
		case 3:
		{
			//经典比武if(bl.x>em.x && bl.x<em.x+30 && bl.y>0 &&bl.y<em.y+20)
			if(bl.x>em.x && bl.x<em.x+30 && bl.y>em.y &&bl.y<em.y+20)
			{ 
				//击中
				//子弹死亡
				bl.isLive = false;
				//敌人坦克死亡
				em.isLive = false;
				
		//裁判也过来做丧事  我他妈怎么在计算机中也看到了老封建！操
				recorddata.reduceenNum();
				recorddata.addkillnum();
				//如果坦克死了  我们开始创建导弹
				//创建一个炸弹对象
				bome b1 = new bome(em.x,em.y);
				//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
				bomes.add(b1);
			}
		}
		}
	}
	
	//判断敌人的子弹是否集中我了    设置爆炸范围！ 和生命减少值！
	public void hitme(bullet bl,Hero em)
	{
		switch(em.getDirect())
		{
			case 0:
			case 2:
			{
				if(bl.x>em.x&&bl.x<em.x+20&&bl.y>em.y&&bl.y<em.y+30) //em.y+5被我写成了5当然是有bug了
				{
					//击中
					//子弹死亡
					bl.isLive = false;
					//敌人坦克死亡
					em.lifedown(); //不会死亡但是会产生炸弹效果！
					System.out.println(bl.x+"zidan vs em"+em.x+"纵坐标"+bl.y+"zidan vs em"+em.y);
					//如果坦克死了  我们开始创建导弹
					//创建一个炸弹对象
			//裁判也过来做丧事  我他妈怎么在计算机中也看到了老封建！操
					recorddata.reduceMylife();
					bome b1 = new bome(em.x,em.y);
					//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
					bomes.add(b1);
				}
			
		}
		break;
		case 1:
		case 3:
		{
			if(bl.x>(em.x)&&bl.x<(em.x+30)&&bl.y>(em.y)&&bl.y<em.y+20) //em.y+5被我写成了5当然是有bug了
			//我居然还怀疑是不是 空格会影响几个&&的执行！  
			{
				//击中
				//子弹死亡
				bl.isLive = false;
				//敌人坦克死亡
				em.lifedown();
				//裁判也过来做丧事  我他妈怎么在计算机中也看到了老封建！操
				recorddata.reduceMylife();
				
				System.out.println(bl.x+"zidan vs em"+em.x+"纵坐标"+bl.y+"zidan vs em"+em.y);
				//如果坦克死了  我们开始创建导弹
				//创建一个炸弹对象
				bome b1 = new bome(em.x,em.y);
				//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
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
	//坦克可以上下左右移动！  w上  a左 s下 d右
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		//最好是按照顺时针进行编写  比较不会混乱！  //在继续和暂停中设置该值
		if(e.getKeyCode() == KeyEvent.VK_S&&Hero.isIsTurn())
		{
			zl.setY(zl.getY()+1);
			zl.setDirect(2);
			
//			System.out.println("你按下了向下的方向键！");
		}else if(e.getKeyCode() == KeyEvent.VK_W&&Hero.isIsTurn())
		{
//			System.out.println("你按下了向上的方向键！");
			zl.setDirect(0);
//			zl.setY(zl.y-1);
			zl.moveup();
		}else if(e.getKeyCode() == KeyEvent.VK_A&&Hero.isIsTurn())
		{
//			System.out.println("你按下了向左的方向键！");
			zl.setDirect(3);
			zl.setX(zl.getX()-1);
		}else if(e.getKeyCode() == KeyEvent.VK_D&&Hero.isIsTurn())
		{
//			System.out.println("你按下了向右的方向键！");
			zl.setDirect(1);
			zl.setX(zl.getX()+1);
		}//else if的弊端就是判断玩一个之后就不判断别的了  所以需要改变！ 开启另外一个if
		
		if(e.getKeyCode() == KeyEvent.VK_J)
		{
			if(zl.vt.size() <= 5 &&zl.isLive)
			{  //这样的结果是 还是保存着6颗子弹 不能再发其他的 必须再把子弹夹给清空了
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
		{  //当然也是可以把它放进paint 函数中也是可以的  韩老师就是放在那边！
//			zl.vt = null;  
			for(int i = 0 ;i<zl.vt.size(); i++)
			{
				if(zl.vt.get(i).isLive == false)
				{  //这样就可以实现不断地死亡  然后再换夹！
					zl.vt.remove(i); //但是这样的结果是子弹还没发杀完呢？  所以得不影响前面的子弹 
				//肯定不能清空
				}
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);  //这边可以设置坦克的出现间隔
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.HitEnemyTank();
			
			this.HitHeroTank();
			
			
			//下面二重for循环的目的在于控制 他的 isLive 参数  然后传递给drawTank函数  谁的islive 子弹的和敌人坦克的
			for(int i = 0 ; i < eet.size() ; i++){ //不对这边的子弹应该是敌方子弹
				//取出敌方坦克
				EnemyTank em = eet.get(i);
				
				if(em.isLive == true){
					//取出敌方子弹
					for(int j = 0 ; j < em.embt.)
				}
				bullet bt = zl.vt.get(i);
				//判断子弹是否存活
				if(bt.isLive == true){
//					for(int j = 0 ; j < eet.size() ; j++) //不要写成 i++了 好的敌人坦克组是定义在Mypanel中
//					{
//						//取出坦克
//						EnemyTank em = eet.get(j); //j写成了  i 服了
//						//判断坦克是否死亡
						if(zl.isLive == true){
							hitme(bt,zl);
						}
					}
			}
			//我倒觉得这边的比较漂亮
			//判断是否得给敌人坦克初始化子弹和加子弹
			bullet bl = null;
			for(int i = 0 ; i< eet.size() ; i++){
			//现在把这几行放到run函数中 不然这边只能就让地方发射一颗子弹   构造函数只能构造一次  而我们需要不断的run
			EnemyTank ent = eet.get(i);
			if(ent.isLive){
			 if(ent.ss.size()<5){  //[控制敌人坦克是否可以连发！]
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
			}  //只加不减是一个坏习惯   得在什么地方进行释放  一般就是在paint函数中
//			bullet bl = new bullet(ent.x,ent.y,ent.direct);
			//加入敌人坦克
		    
		     //一定要自己开启子弹线程   因为别人是不会管他的！让子弹自己飞去吧！
		    Thread t1 = new Thread(bl);
		    t1.start();
			
			}
			
			
			this.repaint();
		}

		
	}*/
		//声音类初始化：
				AePlayWave apw = new AePlayWave("./images/111.wav");
				apw.start();
				
				zl = new Hero(200, 300,0);  //这边其实是原始的x y的设置点 后来一直被后人用 zl.getX zl.getY进调用
				//初始化敌方的战机    //  找了好久终于找到额
				if(recorddata.flags.equals("newgame"))
				{
					//最后一次新改进的地方
					for(int i=0;i<tankversion.enSize; i++)
					{
						EnemyTank ent = new EnemyTank((i+1)*50,10,2);
						//如果把这两句线程放在paint函数中  会导致线程一直是重新开启   所以记得开启线程一般是在构造函数中
						Thread t = new Thread(ent);
						t.start(); 
						
						//把它传给每一个EnemyＴａｎｋ
						ent.setEts(tankversion.eet);
						recorddata rd = new recorddata();
						
						rd.setEnems(tankversion.eet);
						tankversion.eet.add(ent);
	//					ent.shootHero();
						//给敌人坦克添加一颗子弹
					}
					
					
				}else if(recorddata.flags.equals("load"))
				{
					 //敌机初始化完毕   那就得回到print函数把它画出来


					
//					recorddata.RecordRead();  //多了这句坦克数目会急剧增加！
					System.out.print(recorddata.nodes.size() );
//					for (int i = 0; i <recorddata.nodes.size()%5 ; i++) 这个办法好像没用
					for (int i = 0; i <recorddata.nodes.size() ; i++)
				  {
					  Node node=recorddata.nodes.get(i);
				    EnemyTank enemy = new EnemyTank(node.x, node.y,node.direct);
//				    enemy.setDirect(node.direct);
				    //传到EnemyTank 应该叫做集体传送到EnemyTank  
				    enemy.setEts(tankversion.eet); //这边要进行适当修改
				    //传到GameRecord
				    recorddata rd = new recorddata();
				    rd.setEnems(tankversion.eet);
				    Thread th=new Thread(enemy);
				    th.start();
				    tankversion.eet.add(enemy);
				  }

				}
				//当我在这边设置一个断点  发现new的时候根本就是size=0   而没放load的时候就变成12了！  为什么
				System.out.println("new这边："+recorddata.nodes.size());

				try 
				{
					im1 = ImageIO.read(new File("./images/bomb_1.gif"));
					im2 = ImageIO.read(new File("./images/bomb_2.gif"));
					im3 = ImageIO.read(new File("./images/bomb_3.gif"));  //居然这样可以 放图片在images中
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
						//击中
						//子弹死亡
						bl.isLive = false;
						//敌人坦克死亡
						em.isLive = false;
						
						
				//裁判也过来做丧事  我他妈怎么在计算机中也看到了老封建！操
						recorddata.reduceenNum();
						recorddata.addkillnum();
						//如果坦克死了  我们开始创建导弹
						//创建一个炸弹对象
						bome b1 = new bome(em.x,em.y);
						
						AePlayWave apw = new AePlayWave("./images/炸弹.wav");
						apw.start();
						//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
						bomes.add(b1);
					}
				
				}
				break;  //一定要记得加上break
				case 1:
				case 3:
				{
					//经典比武if(bl.x>em.x && bl.x<em.x+30 && bl.y>0 &&bl.y<em.y+20)
					if(bl.x>em.x && bl.x<em.x+30 && bl.y>em.y &&bl.y<em.y+20){ 
						//击中
						//子弹死亡
						bl.isLive = false;
						//敌人坦克死亡
						em.isLive = false;
						
				//裁判也过来做丧事  我他妈怎么在计算机中也看到了老封建！操
						recorddata.reduceenNum();
						recorddata.addkillnum();
						//如果坦克死了  我们开始创建导弹
						//创建一个炸弹对象
						bome b1 = new bome(em.x,em.y);
						AePlayWave apw = new AePlayWave("./images/炸弹.wav");
						apw.start();
						//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
						bomes.add(b1);
					}
				}
				}
				// 关卡所砸  所以必须增加这个  但是这边是中间过程！ 并不能显示恭喜菜单
				//System.out.println(recorddata.getKillenNum());
				if(recorddata.getKillenNum() == tankversion.enSize){
					//System.out.println("刚好相等了");
					tankversion.door++;
					//System.out.println(tankversion.door);
					
					
				}
			}
			
			//判断敌人的子弹是否集中我了    设置爆炸范围！ 和生命减少值！
			public void hitme(bullet bl,Hero em)
			{
				switch(em.getDirect())
				{
				case 0:
				case 2:
				{
					if(bl.x>em.x&&bl.x<em.x+20&&bl.y>em.y&&bl.y<em.y+30) //em.y+5被我写成了5当然是有bug了
					{
						//击中
						//子弹死亡
						bl.isLive = false;
						//敌人坦克死亡
						em.lifedown(); //不会死亡但是会产生炸弹效果！
						System.out.println(bl.x+"zidan vs em"+em.x+"纵坐标"+bl.y+"zidan vs em"+em.y);
						//如果坦克死了  我们开始创建导弹
						//创建一个炸弹对象
				//裁判也过来做丧事  我他妈怎么在计算机中也看到了老封建！操
						recorddata.reduceMylife();
						bome b1 = new bome(em.x,em.y);
						AePlayWave apw = new AePlayWave("./images/炸弹.wav");
						apw.start();
						//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
						bomes.add(b1);
					}
				
				}
				break;
				case 1:
				case 3:
				{
					if(bl.x>(em.x)&&bl.x<(em.x+30)&&bl.y>(em.y)&&bl.y<em.y+20) //em.y+5被我写成了5当然是有bug了
					//我居然还怀疑是不是 空格会影响几个&&的执行！  
					{
						//击中
						//子弹死亡
						bl.isLive = false;
						//敌人坦克死亡
						em.lifedown();
						//裁判也过来做丧事  我他妈怎么在计算机中也看到了老封建！操
						recorddata.reduceMylife();
						
						System.out.println(bl.x+"zidan vs em"+em.x+"纵坐标"+bl.y+"zidan vs em"+em.y);
						//如果坦克死了  我们开始创建导弹
						//创建一个炸弹对象
						bome b1 = new bome(em.x,em.y);
						AePlayWave apw = new AePlayWave("./images/炸弹.wav");
						apw.start();
						//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
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
			//坦克可以上下左右移动！  w上  a左 s下 d右
			public void keyPressed(KeyEvent e)
			{
				// TODO Auto-generated method stub
				//最好是按照顺时针进行编写  比较不会混乱！  //在继续和暂停中设置该值
				if(e.getKeyCode() == KeyEvent.VK_S&&Hero.isCanTurn())
				{
					zl.setY(zl.getY()+1);
					zl.setDirect(2);
					AePlayWave apw = new AePlayWave("./images/坦克经过.wav");
					apw.start();
//					System.out.println("你按下了向下的方向键！");
				}else if(e.getKeyCode() == KeyEvent.VK_W&&Hero.isCanTurn())
				{//如果isIsTurn为假则不可以用按键控制了
//					System.out.println("你按下了向上的方向键！");
					zl.setDirect(0);
//					zl.setY(zl.y-1);
					zl.moveup();
					AePlayWave apw = new AePlayWave("./images/坦克经过.wav");
					apw.start();
				}else if(e.getKeyCode() == KeyEvent.VK_A&&Hero.isCanTurn())
				{
//					System.out.println("你按下了向左的方向键！");
					zl.setDirect(3);
					zl.setX(zl.getX()-1);
					AePlayWave apw = new AePlayWave("./images/坦克经过.wav");
					apw.start();
				}else if(e.getKeyCode() == KeyEvent.VK_D&&Hero.isCanTurn())
				{
//					System.out.println("你按下了向右的方向键！");
					zl.setDirect(1);
					zl.setX(zl.getX()+1);
					AePlayWave apw = new AePlayWave("./images/坦克经过.wav");
					apw.start();
				}//else if的弊端就是判断玩一个之后就不判断别的了  所以需要改变！ 开启另外一个if
				
				if(e.getKeyCode() == KeyEvent.VK_J)
				{
					if(zl.vt.size() <= 5 &&zl.isLive)
					{  //这样的结果是 还是保存着6颗子弹 不能再发其他的 必须再把子弹夹给清空了
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
				{  //当然也是可以把它放进paint 函数中也是可以的  韩老师就是放在那边！
//					zl.vt = null;  
					for(int i = 0 ;i<zl.vt.size(); i++)
					{
					if(zl.vt.get(i).isLive == false)
					{  //这样就可以实现不断地死亡  然后再换夹！
						zl.vt.remove(i); //但是这样的结果是子弹还没发杀完呢？  所以得不影响前面的子弹 
					//肯定不能清空
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
						Thread.sleep(50);  //这边可以设置坦克的出现间隔
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


//帮助面板
class Help extends JFrame
{
	JList jl=null;
	public Help()
	{
		String s[]={"向下---S","向上---W","向左---A","向右---D","开火---J"};
		jl=new JList(s);
		//显示数量
		//jl.setVisibleRowCount(3);
		this.add(jl);
		this.setSize(200, 200);
		this.setVisible(true);
	}
}


//储存成功窗口
class  ReSuccess extends JFrame
{
	JList jl=null;
	public ReSuccess()
	{
		String s[]={"保存成功"};
		jl=new JList(s);
		this.add(jl);
		this.setSize(30,80);
		this.setVisible(true);
	}
}

//用于GameRecord.writeRecord ();之后


/*总结：
 * 有三个位置我可以进行显示控制
 * [direction] 上下左右
 * [type] 敌机   我方机器
 * [MyPanel中的构造函数] 设计不同的初始位置 或者变换位置
*/
