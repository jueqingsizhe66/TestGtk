/**

我再想一个问题，如何把Vector变为ArrayList或者LinkedList

或者如何使用上HashMap


1:通过 Graphics在MyJpanel中导入Paint函数！  通过g对象调用对应画图的方法
      //定义一个画板 用来给Tank游走
       paint函数只放在
       
2：创造某些线程   需要自己活动的 不然子弹    敌方战车
3： 创建时间监听  针对右方坦克
4： 创建一个数据结构  用于存储坦克  子弹！
5： 判断子弹爆炸的函数   判断坦克接触的函数

6：坦克具有  上下左右的方向   位置x y(平面坐标） 其实应该还有速度speed
7：通过面向对象 思想来进行编程
8：通过this.repaint进行重绘操作
     每按下一次键盘，都是在重新刷新屏幕的
9：KeyEvent的getKeyCode方法  类似于OOGTK的OOKeyCode找到对应的键盘命令

10： 攻击敌人意思是说发射子弹 shootEnemy应该放在哪里？  Hero类 右方车类
       那么地方是通过线程不断地进行扫射
       1.自己装子弹
       2.自己用子弹
       3.自己攻击敌方（只要还活着 就攻击）
        让敌人坦克也能发射子弹！   并使敌人发射的子弹能够hitme（发生在面板上！）
    //定义一个Vector的数组用来储存地方的战机
	Vector<EnemyTank> eet = new Vector<EnemyTank>();
	Vector<bome> bomes = new Vector<bome>();
	//定义所需子弹
	//bullet bl = null;
	//定义所需子弹夹！  把子弹换成子弹夹 就可以连发了   把小米加步枪换成扫射炮就可以了！
	//为什么必须加上这种初始化 父亲的初始化在儿子这边执行
    Vector<bullet> vt = new Vector<bullet>();
	 
       并设定我的作用域
11：子弹也具有 位置x y  其实应该还有速度

12: 之前使用Vector一个线程安全的数组存储 地方的战斗坦克 ： 1 设置数据结构 存储坦克数量
                                                    2 在结构函数中根据坦克数量进行绘制
                                                    3 
13： isLive的判定死亡的参数   包括子弹（爆炸）  坦克（生命已用尽）

14：保证敌人坦克不出边界    if(x>0)    边界如何控制
15: 炸弹的绘制

16： 组合和接口的思想  继承的思想

17：判断是否转向 isTurn 地方自己转  右方通过w a s d进行控制


18： 下面是不需要的部分 ! 但却是坦克大战的升级版  加入了Recorddata类，用于读取数据 和导入数据 
            1：杀坦克数 killenNum
            2:记录每关有多少个敌人private static int enNum = 20 ;
            3:mylife;
            4:enems 敌人坦克
            5： 右方坦克
            
19：  （2）新增如下功能
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
 *     问题：什么时候保存    什么叫做一关 所有敌人都没有的时候！
19 datarecord仅仅在 新游戏的时候才进行读取		
**
 * @author 叶昭良
 * @time 2015年3月6日上午9:45:19
 * @version com.test.tankpackage-info V1.0
 */
/**
 * 装子弹的过程
 * 			if(temp.isLive == true){
			drawTank(temp.getX(),temp.getY(),g,temp.getDirect(),temp.getType());
//			temp.shootHero();  //用于给坦克装上炸弹！  放错地方了  这边是绘制 创建要放在构造函数中
				//取出子弹
				for(int j = 0 ; j<temp.ss.size(); j++){
					bullet tempbl = temp.ss.get(j);
					if(tempbl.isLive){ //绘制子弹
						drawbullet(tempbl.x,tempbl.y,g,tempbl.getDirection(),1);
//						System.out.println("第"+(i)+"辆坦克的第"+(j+1)+"颗子弹的"+"坐标是　ｘ＝"+tempbl.x+" y ="+tempbl.y);
					//通过 可以查看每一辆坦克第几颗子弹的坐标[]   但是这边一点用都没有！
						//经验：	
					}else{
						//把子弹给删掉 当死的时候就得清除  留着坑给别人用
//						eet.remove(tempbl);//这是删除坦克  应该是删除子弹
						temp.ss.remove(tempbl);  //可以了这句话真的是奏效！
					}
					
				}
			}
 */
//----------------------------------炸弹类的改进---------------- 有生命值
/**
 * //定义一个炸弹类
class bome{
	//定义炸弹坐标
	int x,y;
	//定义炸弹的生命值  默认的设置为12条命
	int life = 15;
	//定义是否存活
	boolean isLive = true;
	
	public bome(int x, int y){
		this.x = x;
		this.y = y;
	}
	//生命减少函数！   我们该在哪边创建呢？ 当然是把它放在hitTank 中   一击中咱们就可以产生一个炸弹  并引爆他！
	//但是炸弹不是只有一颗  12life 有4颗炸弹！ 放在一个Vector 中  所以得在MyPanel中添加一个Vector变量
	public void lifedown(){
		if(life > 0){
			life--;
		}else{
			isLive = false;
		}
	}
}
 */

//------------------------------------------------------
/**
 * 		//开始画炸弹
		for(int i = 0 ; i<bomes.size() ; i++){
			//取出炸弹
			System.out.println("炸弹的名字是："+bomes.lastElement()+"bomes.size = "+bomes.size());  //调试看看是不是炸弹！没进来
			bome b1 = bomes.get(i);
			if(b1.life >10){
				
				g.drawImage(im1, b1.x, b1.y, 30, 30, this);
			}else if(b1.life >5){
				g.drawImage(im2, b1.x, b1.y, 30, 30, this);
			}else{
				g.drawImage(im3, b1.x, b1.y, 30, 30, this);
			}
			b1.lifedown();
			//如果炸弹的生命值为0  则从炸弹的向量中去掉
			if(b1.life == 0){
//				b1.isLive = false;
				bomes.remove(b1);
			}
		}
 */


/**
 * 两个方向判断炸弹的死亡
 * public void hitTank(bullet bl, EnemyTank em){
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
			if(bl.x>em.x && bl.x<em.x+30 && bl.y>em.y &&bl.y<em.y+20){ 
				//击中
				//子弹死亡
				bl.isLive = false;
				//敌人坦克死亡
				em.isLive = false;
				
				
				//如果坦克死了  我们开始创建导弹
				//创建一个炸弹对象
				bome b1 = new bome(em.x,em.y);
				//添加进炸弹中       由于炸弹又是由三张图片组成 所以我们得现在面板声明三张图片  ！ 然后再构造函数中定义图片
				bomes.add(b1);
			}
		}
		}
 */



/**
 * 
 * 子弹的runnable接口
 * 	@Override
	public void run() {
		// TODO Auto-generated method stub
		//线程一般放的都是while(true)修改坐标！
		while(true){
			try {
				Thread.sleep(50);  //不然的话 cpu耗费太多了
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//0代表 上  1 右  2下  3左
			switch(direction){
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
			
			if(x<-5||x>405||y<-5||y>405){//考虑资本本身的长度 6 2
				this.isLive = false;
				break;
			}
		}
	}
 */

/**
 * 生命值下降函数
 * 	public void lifedown(){
		if(life>0){
			life--;
		}else{
			this.isLive = false;
		}
	}
 */


/**
 * 	//判断是否触碰到其他坦克   然后再构造函数中  然后在run函数中 调用它！ 充当条件！
	//判断是否碰到了别的敌人坦克
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
 */
package com.test.tank;



/**
 * 
 * 增加了isIsTurn参数 ！！ 判断times %2 == 0
 * 	@Override
	public void run() {
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
					if(ss.size() < 1){
								bullet bl = null;
								switch(this.direct){
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
			switch(this.direct){
			case 0:  //这线程使子弹的  x  y坐标一直改变！  但是不知道他是如何死的  反正没一个线程都是会死的~  这是真理 就好像每一个人都会死的 一样！
			{
				for(int i = 0 ; i<30; i++){
					if(y>0&& !this.isTouchOtherEnemy()){ //判断的目的在于用来使敌人设置在可运行的方位内  [用于控制坦克的范围]
					y -= getSpeed();
					
					}else{
						break;
					}
					try {
						Thread.sleep(50);  //我觉得每一步都应该跟刷新的速度一样  都设置为休息50
						//不然的话 cpu耗费太多了
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//				System.out.println("当前子弹坐标为：x="+this.x+"y="+this.y);
				//通过打印知道 鼠标的坐标变化！并且是一直没有停止！  这可以当做一个教学案例！
				
			}
			break;
			case 1:
			{
				for(int i = 0 ; i<30; i++){
					if(x<400 && !this.isTouchOtherEnemy()){ //[用于控制坦克的范围]
					x += getSpeed();
					}else{
						break;
					}
					try {
						Thread.sleep(50);  //不然的话 cpu耗费太多了
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println(this.direct);}
				}
			}
			break;
			case 2:
			{
				for(int i = 0 ; i<30; i++){
					if(y<400 && !this.isTouchOtherEnemy()){  // [用于控制坦克的范围]
					y += getSpeed();
					}else{
						break;//直接跳出来  不要再循环了
					}
//					System.out.println(this.direct);
					try {
						Thread.sleep(50);  //不然的话 cpu耗费太多了
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
			}
			break;
			case 3:
			{
				for(int i = 0 ; i<30; i++){
					if(x > 0 && !this.isTouchOtherEnemy()){ //[用于控制坦克的范围]
					x -=getSpeed();
					}else{
						break;
					}
					try {
						Thread.sleep(50);  //不然的话 cpu耗费太多了
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
} //那时候是没有把这个放在一起 所以一直出现问题
					
				
			}
			break;
			}
			
			
				
			

			this.direct = (int)(Math.random()*4); //产生一个新的方向
			
			这样的话  方向一直改变 坦克肯定不知道该干什么！  得让他往一个方向走   而且是走一步 换一个方向！
			if(x<-5||x>405||y<-5||y>405){//考虑资本本身的长度 6 2
				this.isLive = false;
				
			}
			if(this.isLive == false){
				break;
			}
		}
		}
	}
	
*/
