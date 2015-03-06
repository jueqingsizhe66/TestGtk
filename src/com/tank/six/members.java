package com.tank.six;

import java.util.*;







//子弹类：
/**
 * 
 * @author    叶昭良
 * @time      2015年3月6日下午7:31:49
 * @version   com.tank.sixbullet V1.0
 * 功能：  产生一个子弹对象，带现成的
                步骤：x  y  direction  speed,  构造函数， 方法
 * 思考：run函数的写法
 * 注意：
 * 掌握：
                思考：线程的run方法的重写
 * 回顾：
 */
class bullet implements Runnable
{
	int x = 0;
	int y = 0;

	int direction = 0;
	private static int speed = 1;
	public int getSpeed() 
	{
		return speed;
		
	}



	public static void setSpeed(int speed) 
	{
		bullet.speed = speed;
	}
	boolean isLive = true;//判断是否坦克已经 被打死了！  打死就不用再重绘子弹了！   也就是在paint函数中在&&一个判断不drawbullet就好了！
	//然后让坦克也消失  也就是不进行drawtank了
	public bullet(int x,int y,int direction)
	{ //这一改变就得下面的Hero的坦克进行修改！
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	
	
	public int getDirection()
	{
		return direction;
	}
	
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		//线程一般放的都是while(true)修改坐标！
		while(true)
		{
			try 
			{
				Thread.sleep(50);  //不然的话 cpu耗费太多了
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//0代表 上  1 右  2下  3左
			switch(direction)
			{
				case 0:  //这线程使子弹的  x  y坐标一直改变！  但是不知道他是如何死的  反正没一个线程都是会死的~  这是真理 就好像每一个人都会死的 一样！
				{

					y -= speed;
	//				System.out.println("当前子弹坐标为：x="+this.x+"y="+this.y);
					//通过打印知道 鼠标的坐标变化！并且是一直没有停止！  这可以当做一个教学案例！
					
				}
				break;
				case 1:
				{
					x += speed;
				}
				break;
				case 2:
				{
					y += speed;
				}
				break;
				case 3:
				{
					x -=speed;
				}
				break;
			}
			//这边无法repaint所以只好跑到panel去了
			
			if(x<-5||x>405||y<-5||y>405)
			{//考虑资本本身的长度 6 2
				this.isLive = false;
				break;
			}
		}
	}
}
//坦克类
class TankM
{
	//Tank横坐标
	int x = 0;
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	//Tank纵坐标
	int y = 0;
	
	//所有的坦克都是需要方向的  于是定义一个direction变量
	int direct = 2;  //影响敌机坦克的初始走位
	public int getDirect() 
	{
		return direct;
	}
	public void setDirect(int direct)
	{
		this.direct = direct;
	}
	//0代表上  1 代表右  2 代表下  3 代表左
	

	public TankM(int x, int y, int direct)
	{
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	//为了敌人的需要 所以设置了类型
	int type = 0;//默认是我军坦克
	public int getType() 
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	
	//判断坦克是否死亡  默认是活着的
	boolean isLive = true;  //然后当坦克超过了  400+长度  活着400+宽度  坦克即认为死亡  不进行重绘

	//可以集成定义 子弹夹 ！ 当然我暂时没做
	

	
}


//从Tank类中定义我自己的一架Tank  Heroang  
//我的坦克~
class Hero extends TankM
{

	public Hero(int x, int y, int direct) 
	{
		super(x, y, direct);
		// TODO Auto-generated constructor stub
//		this.direct = 0;
	}

	//定义所需子弹
	//bullet bl = null;
	//定义所需子弹夹！  把子弹换成子弹夹 就可以连发了   把小米加步枪换成扫射炮就可以了！
	//为什么必须加上这种初始化 父亲的初始化在儿子这边执行
	Vector<bullet> vt = new Vector<bullet>();
	//设置己方的 生命值
	int life = 3 ;
	
	//发现所有的坦克都是有速度 于是定义了一个speed按钮   由于是static类  所以必须删掉前面的一句话  把它们重新分配到Hero和Enemy中
	private static int speed = 1;//默认一步一步的走   
	public  int getSpeed() 
	{
		return speed;
	}
	public static void setSpeed(int speed) 
	{
		Hero.speed = speed;
	}
	
	/*//判断是否转向  必须在每一个类中单独定义  因为是static类！
	private static boolean IsTurn = true;
	public static boolean isIsTurn()
	{
		return IsTurn;
	}
	public static void setIsTurn(boolean isTurn)
	{
		IsTurn = isTurn;
	}*/
	
	
	//判断是否转向  必须在每一个类中单独定义  因为是static类！
		private static boolean CanTurn = true;
		public static boolean isCanTurn() {
			return CanTurn;
		}
		public static void setCanTurn(boolean CanTurn) {
			CanTurn = CanTurn;
		}
	
	public void lifedown()
	{
		if(life>0)
		{
			life--;
		}else
		{
			this.isLive = false;
		}
	}
	

	
	
	public void shootenemy()
	{  //是哪个让子弹的坐标一直改变
		switch(this.getDirect())
		{
			case 0:
			{
				bullet bl = new bullet(x+9,y,0);//之所以不写成10是因为 其实应该是正方形的中心对着炮筒
				//这边创建子弹的 x y是依据坦克的坐标而定的！
				vt.add(bl);
				
			}
			break;
			case 1:
			{
				bullet bl = new bullet(x+30,y+9,1);
				vt.add(bl);
			}
			break;
			case 2:
			{
				bullet bl = new bullet(x+9,y+30,2);
				vt.add(bl);
			}
			break;
			case 3:
			{
				bullet bl = new bullet(x,y+9,3);
				vt.add(bl);
			}
			break;
		}
		for(int i = 0; i<vt.size();i++)
		{
			Thread t1 = new Thread(vt.get(i));  //追个开启线程
			t1.start();
		}
		
	}
	//封装起向上 下 左 右 的移动
	public void moveup()
	{
		y -= getSpeed();
	}
	
	public void movedown()
	{
		y += getSpeed();
	}
	
	public void moveleft()
	{
		x -= getSpeed();
	}
	public void moveright()
	{
		x += getSpeed();
	}
	
	
}


class EnemyTank extends TankM implements Runnable
{

	
	//定义所需子弹
	//	bullet bt = null;
	//定义所需子弹夹
	//在最后一步的    设置关卡中 设置ss变量为static变量！成为类变量
	static Vector<bullet> ss = new Vector<bullet>();
	
	//	bullet bl = new bullet(3,4,1);
	
	int times = 0; //全场中总的有三个时间  子弹时间   敌方坦克时间(30步*50ms(每一步休眠50))  面板时间    子弹时间== 面板时间  只有坦克时间比较长一点 于是把创建子弹的方法放在敌人自己
	//的run函数中      
	
	////定义一个向量，可以访问到MyPanel上所有敌人的坦克  这个需要对对内存有所了解！
	Vector<EnemyTank> ets=new Vector<EnemyTank>();  //居然访问的资源是一样的！ //不是这样的

	//判断是否转向  必须在每一个类中单独定义  因为是static类！
	private static boolean IsTurn = true;
	
	public static boolean isIsTurn() 
	{
		return IsTurn;
	}
	public static void setIsTurn(boolean isTurn)
	{
		IsTurn = isTurn;
	}
	
	
	
	//发现所有的坦克都是有速度 于是定义了一个speed按钮   由于是static类  所以必须删掉前面的一句话  把它们重新分配到Hero和Enemy中
	private static int speed = 1;//默认一步一步的走   
	public  int getSpeed() 
	{
		return speed;
	}
	public static void setSpeed(int speed) 
	{
		EnemyTank.speed = speed;
	}
	
	//得到MyPanel的敌人坦克向量    那我们该在哪边setets呢？   应该是面板上！
	public void setEts(Vector<EnemyTank> vv)
	{
		this.ets=vv;
	}
	
	
	/**
	 * 判断是否触碰到其他坦克   然后再构造函数中  然后在run函数中 调用它！ 充当条件！
	 * @return  如果碰了 则返回true  否则false
	 */
	public boolean isTouchOtherEnemy()
	{
		boolean b=false;
//		Shot s = new Shot(10,10,0);
//		ss.add(e);在函数中也是可以调用ss
		
		switch(this.direct)
		{
		case 0:
			//我的坦克向上
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.direct==0||et.direct==2) //我感觉只需要讨论et.direct ==2 即可  因为速度一样
					{
						//左点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//右点
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			//坦克向右
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.direct==0||et.direct==2)
					{
						//上点
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//下点 
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 2:
			//坦克向下
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.direct==0||et.direct==2)
					{
						//我的左点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//我的右点
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:
			//向左
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.direct==0||et.direct==2)
					{
						//我的上一点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//下一点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						//上一点
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		}
		
		
		return b;
	}
	public EnemyTank(int x, int y, int direct) 
	{
		super(x, y, direct);
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		//线程一般放的都是while(true)修改坐标！
		while(true){
			if(EnemyTank.isIsTurn())
			{
			 ///休眠了1.5秒才开始创建自担  于是我把这个提到了坦克之前   让他还没走就发射子弹
			this.times++;
			//这边无法repaint所以只好跑到panel去了
			if(times %2 == 0) //取模为0则直接
			{
//				//判断是否得给敌人坦克初始化子弹和加子弹
				
//				for(int i = 0 ; i< this.ss.size() ; i++){
//				//现在把这几行放到run函数中 不然这边只能就让地方发射一颗子弹   构造函数只能构造一次  而我们需要不断的run
//				EnemyTank ent = this.get(i);
//				if(ent.isLive){
//				 if(ent.ss.size()<5){  //[控制敌人坦克是否可以连发！]
				if(isLive){
					if(ss.size() < 1)
					{
								bullet bl = null;
								switch(this.direct)
								{
										case 0:
										{
											bl = new bullet(x+10,y,0);
											this.ss.add(bl);
										}
										break;
										case 1:
										{
											bl = new bullet(x+30,y+10,1);
											this.ss.add(bl);
										}
										break;
										case 2:
										{
											bl = new bullet(x+10,y+30,2);
											this.ss.add(bl);
										}
										break;
										case 3:
										{
											bl = new bullet(x,y+10,3);
											this.ss.add(bl);
										}
										break;
									}
							    Thread t1 = new Thread(bl);
							    t1.start();
							}	
					}  //只加不减是一个坏习惯   得在什么地方进行释放  一般就是在paint函数中
//				bullet bl = new bullet(ent.x,ent.y,ent.direct);
				//加入敌人坦克
			    
			     //一定要自己开启子弹线程   因为别人是不会管他的！让子弹自己飞去吧！

				
				}
			//this.direct = (int)Math.random()*4; 这样写不行的
			
			//0代表 上  1 右  2下  3左
			switch(this.direct)
			{
			case 0:  //这线程使子弹的  x  y坐标一直改变！  但是不知道他是如何死的  反正没一个线程都是会死的~  这是真理 就好像每一个人都会死的 一样！
			{
				for(int i = 0 ; i<30; i++)
				{
					if(y>0&& !this.isTouchOtherEnemy())
					{ //判断的目的在于用来使敌人设置在可运行的方位内  [用于控制坦克的范围]
						y -= getSpeed();
					
					}else
					{
						break;
					}
					try 
					{
						Thread.sleep(50);  //我觉得每一步都应该跟刷新的速度一样  都设置为休息50
						//不然的话 cpu耗费太多了
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*System.out.println(this.direct); 不想让他显示坐标了！ 看不到炸弹！*/}
					
				
//				System.out.println("当前子弹坐标为：x="+this.x+"y="+this.y);
				//通过打印知道 鼠标的坐标变化！并且是一直没有停止！  这可以当做一个教学案例！
				
			}
			break;
			case 1:
			{
				for(int i = 0 ; i<30; i++)
				{
					if(x<400 && !this.isTouchOtherEnemy())
					{ //[用于控制坦克的范围]
						x += getSpeed();
					}else
					{
						break;
					}
					try 
					{
						Thread.sleep(50);  //不然的话 cpu耗费太多了
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println(this.direct);}
				}
			}
			break;
			case 2:
			{
				for(int i = 0 ; i<30; i++)
				{
					if(y<400 && !this.isTouchOtherEnemy())
					{  // [用于控制坦克的范围]
						y += getSpeed();
					}else
					{
						break;//直接跳出来  不要再循环了
					}
//					System.out.println(this.direct);
					try 
					{
						Thread.sleep(50);  //不然的话 cpu耗费太多了
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
			}
			break;
			case 3:
			{
				for(int i = 0 ; i<30; i++)
				{
					if(x > 0 && !this.isTouchOtherEnemy())
					{ //[用于控制坦克的范围]
						x -=getSpeed();
					}else
					{
						break;
					}
					try 
					{
						Thread.sleep(50);  //不然的话 cpu耗费太多了
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*System.out.println(this.direct);*/} //那时候是没有把这个放在一起 所以一直出现问题
					
				
			}
			break;
			}
			
			
				
			

			this.direct = (int)(Math.random()*4); //产生一个新的方向
			
			/*这样的话  方向一直改变 坦克肯定不知道该干什么！  得让他往一个方向走   而且是走一步 换一个方向！*/
			if(x<-5||x>405||y<-5||y>405)
			{//考虑资本本身的长度 6 2
				this.isLive = false;
				
			}
			if(this.isLive == false)
			{
				break;
			}
		}
		}
	}
}


//定义一个炸弹类
/**
 * 炸弹类的javaBeans规范！以后再改
 * @author    叶昭良
 * @time      2015年3月6日下午7:13:17
 * @version   com.tank.sixbome V1.0
 */
class bome
{
	//定义炸弹坐标
	int x,y;
	//定义炸弹的生命值  默认的设置为12条命
	int life = 15;
	//定义是否存活
	boolean isLive = true;
	
	public bome(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	//生命减少函数！   我们该在哪边创建呢？ 当然是把它放在hitTank 中   一击中咱们就可以产生一个炸弹  并引爆他！
	//但是炸弹不是只有一颗  12life 有4颗炸弹！ 放在一个Vector 中  所以得在MyPanel中添加一个Vector变量
	public void lifedown()
	{
		if(life > 0)
		{
			life--;
		}else
		{
			isLive = false;
		}
	}
}