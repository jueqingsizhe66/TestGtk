package com.tank.six;
//record就好像是现实中的裁判一样   当死亡的时候记录簿上就减一    hitnums加一

import java.io.*;
import java.util.Vector;
public class recorddata 
{
	//开始单写一个记录类     我想把这个文件放在F:/tankrecord.txt中！
	
		
		//记录每关有多少个敌人
		private static int enNum = 20 ;
		public static int getEnNum() 
		{
			return enNum;
		}
		public static void setEnNum(int enNum) 
		{
			recorddata.enNum = enNum;
		}
		public static int getMylife()
		{
			return mylife;
		}
		public static void setMylife(int mylife) 
		{
			recorddata.mylife = mylife;
		}
		//记录我自己的声明次数   ///把以前我设置的life删掉
		private static int mylife = 3;
		
		//记录总共消灭了多少敌人
		private static int killenNum = 0;
		
		
		//定义一个标志  
		//当flags == newgame 只是恢复出 保存的记录的第一行！ 也就是killnum
		//当flags == load  则恢复出六个数据   killnum   敌人坐标   和敌人数量
		public static String flags = null;
		
		//为了保证load情况下  获得所有的tank所以新建了一个Enemytank容器
		public static Vector<EnemyTank> enems = new Vector<EnemyTank>();
		public static Vector<EnemyTank> getEnems()
		{
			return enems;
		}
		public static void setEnems(Vector<EnemyTank> enems)
		{
			recorddata.enems = enems;
		}
		public static Vector<Node> getNodes() 
		{
			return nodes;
		}
		public static void setNodes(Vector<Node> nodes)
		{
			recorddata.nodes = nodes;
		}
		//并且新建一个节点用来获得坦克的坐标  刚好用TankM类就可以了  有坐标 一 有方向
		public static Vector<Node> nodes = new Vector<Node>();
		//public static Vector<TankM> nodes = new Vector<TankM>();
		
		//现在就可以使用这两个容器了  在load 
		
		public static int getKillenNum()
		{
			return killenNum;
		}
		public static void setKillenNum(int killenNum)
		{
			recorddata.killenNum = killenNum;
		}
		//每当打掉一个敌人 减少敌人数目
		public static void reduceenNum()
		{
			enNum--; //在hitenemy中调用
		}
		
		//我每被敌人打一次  生命值减一
		public static void reduceMylife()
		{
			mylife--;
		}
		
		//我的战绩统计
		public static void addkillnum()
		{
			killenNum++;
		}
		
		//写入记录
		//保存作战的成绩  从头开始   不仅仅是本关的  而是很多关！ //放入退出游戏的过程中
		private static FileWriter fw = null;
		private static BufferedWriter bw =null;
		public static void writeRecord()
		{
			
			try 
			{
				fw = new FileWriter("F:/datarecord.txt");
				bw = new BufferedWriter(fw);
				/*if(flags.equals("newgame")){
				bw.write(recorddata.getKillenNum()+"\r\n");
				}else if(flags.equals("loadgame")){*/
					//恢复敌人坐标等  六个数据　　首先得去建一个敌人坦克容器 供Recorddata调用
					bw.write(recorddata.getKillenNum()+"\r\n");
					bw.write(recorddata.getEnNum()+"\r\n");
					bw.write(recorddata.getMylife()+"\r\n");
					
					for(int i = 0; i< enems.size() ; i++)
					{
						//获得其中的每一辆敌人坦克的坐标
						EnemyTank temp = enems.get(i);
						//如果还是活的就可以写入
						if(temp.isLive)
						{
							String record = temp.getX()+" "+temp.getY()+" "+temp.getDirect();
							bw.write(record+"\r\n");
						}
					
				}
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				//后写的先关掉  就好像是栈一样
				try 
				{
					bw.close();
					fw.close();
				} catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		//读取记录
		private static FileReader fr = null;
		private static BufferedReader  br = null;
		public static void RecordRead()
		{
			try 
			{
				fr = new FileReader("F:/datarecord.txt");
				br = new BufferedReader(fr);
				
				//如何读入   利用Interger
				String n = null;
				try 
				{
					if(flags.equals("newgame"))
					{
						n = br.readLine();
						killenNum = Integer.parseInt(n);
					}else if(flags.equals("load"))
					{
						
						//分别获取第1.2.3行
						String n1=br.readLine(); //获得从头到尾的总数
						String n2=br.readLine();//获得敌人的剩余数
						String n3=br.readLine();//获得我的生命值
						setKillenNum(Integer.parseInt(n1));
						setEnNum(Integer.parseInt(n2));
						setMylife(Integer.parseInt(n3));
						//获取坦克的坐标和方向
						while((n=br.readLine())!=null)
						{
							//以空格为标志将字符串切分
							 String []xydirect=n.split(" ");
							Node node=new Node(Integer.parseInt(xydirect[0]),Integer.parseInt(xydirect[1]),Integer.parseInt(xydirect[2]));
							//依次读取每一个节点的信息
							nodes.add(node);
							//这样我们就可以用nodes 在load函数调用它  直接recorddata.RecordRead() 就可以了 延展性还行

					}
				}
				}catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				try 
				{
					//后开先关  
					br.close();
					fr.close();
				} catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	
}



class Node{
	public Node(int x, int y, int direct) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
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
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	int x;
	int y;
	int direct;
	
}



