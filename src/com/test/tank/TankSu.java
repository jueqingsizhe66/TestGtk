/**
 *  * 案例 坦克大战
增加的功能:
 * 9.如何控制敌人的坦克不重叠(待解决)
 * 10.防止敌人坦克重叠运动
 * 11.可以分关
思路:做一个开始的panel(空的)主要用于提示
 * 12.可以在玩游戏的时候暂停和继续
 * 13.可以记录玩家的成绩
 * 14.java如何操作声音文件

解决了第九个问题
/**
 * 功能:坦克游戏的2.0
 * 1.画出坦克
 * 2.我的坦克可以上下移动.
 * 3.实现子弹连发,最多五颗.
 * 4.我方击中坦克时,敌方坦克就消失(有爆炸效果)
 * 5.让敌人坦克也能自由移动
 * 6.控制我方坦克和敌人坦克在指定范围内运动
 * 7.让敌人的坦克也能发射子弹
 * 8.当敌人的坦克击中我时,我发生爆炸
 * 9.如何控制敌人的坦克不重叠(待解决)x
 * 10.防止敌人坦克重叠运动x
 * 11.可以分关x
 * 12.可以在玩游戏的时候暂停和继续x
 * 13.可以记录玩家的成绩x
 * 14.java如何操作声音文件x
 * 
 * 
 * 注意点： Tank工具类中有四点 需要注意  e://myRecording.txt 一定要在正确的盘符
 *        得在e盘下新建一个myRecording.txt文件
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
 * @time      2015年3月6日上午9:48:47
 * @version   com.test.tankTankSu V1.0
 */
public class TankSu extends JFrame implements ActionListener
{

	/**
	 * @param args
	 */
	MyPanel mp=null;
	//定义一个开始面板
	MyStartPanel msp=null;
	
	//做出我需要的菜单
	JMenuBar jmb=null;
	//游戏
	JMenu jm1=null;
	//开始游戏
	JMenuItem jmi1 =null;
	//保存,退出
	JMenuItem jmi2 =null;
	//存盘退出
	JMenuItem jmi3 =null;
	//续上局
	JMenuItem jmi4=null;
	public static void main(String[] args) {
		TankSu mytankgame1=new TankSu();
	}
	
	//构造函数
	public TankSu()
	{	

		
		//创建菜单及菜单选项
		jmb=new JMenuBar();
		jm1 =new JMenu("游戏(G)");
		//设置快捷键
		jm1.setMnemonic('G');
		jmi1 =new JMenuItem("开始新游戏(N)");
		//对jmi1相应
		jmi1.addActionListener(this);
		jmi1.setActionCommand("new");
		jmi1.setMnemonic('N');
		
		jmi2 =new  JMenuItem("退出游戏(Q)");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("quit");
		jmi2.setMnemonic('Q');
		
		jmi3 =new  JMenuItem("存盘退出(C)");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		jmi3.setMnemonic('C');
		
		jmi4=new JMenuItem("继续上局游戏");
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
		//注意往窗体里加JMenuBar不能用add方法,用专门的setJMenuBar()方法.
		this.setJMenuBar(jmb);
		
		this.setSize(700,650);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//对用户不同的点击做出不同的相应
		//创建上次的游戏
		
		if(e.getActionCommand().equals("new"))
		{
			//创建战场的面板
			System.out.println("新游戏");//做测试用的
			//创建对象会自动调用paint函数.
			//先删除旧的面板,然后再加入一个新的面板
			this.remove(msp);
			mp= new MyPanel();
			Thread t=new Thread(mp);
			//创建线程会自动调用run方法
			t.start();
			
			this.add(mp);
			this.addKeyListener(mp);
			//显示,刷新JFrame
			this.setVisible(true);
		}
		//存盘退出,1，保存击毁敌人的数量2,保存敌人的坐标

		else  if(e.getActionCommand().equals("saveExit"))
		{
			System.out.println("存盘退出");//做测试用的
			Recorder.setEts(mp.ets);
			 Recorder.keepRecordAndEnemyTank();
			System.exit(0);
			
		}
		//退出,记录击毁敌人的数量
		else if(e.getActionCommand().equals("quit"))
		 {
			//用户点击了退出系统的按钮
			//保存击毁敌人数量
			System.out.println("退出");//做测试用的
			Recorder.keepRecording ();
			System.exit(0);
		} 
		else if(e.getActionCommand().equals("continue"))
		{	
			//完成恢复
			
			System.out.print("恢复上局");
		}
		
	}

}
