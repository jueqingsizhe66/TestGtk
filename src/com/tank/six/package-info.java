/**
 * 
 */
/**
 * @author 叶昭良
 * @time 2015年3月6日下午1:34:49
 * @version com.tank.sixpackage-info V1.0
 */
package com.tank.six;

/**  得学会这么一招才可以  进行menu编程
 * 
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
	    
	    jexit.setActionCommand("exit")
	    jmi21.setActionCommand("help");
	    jnew.setActionCommand("newgame");
	    jmi5.setActionCommand("continue");
	    jmi4.setActionCommand("pause");
	    
@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//存盘退出 
		if(e.getSource() == jmi1){ 
//			System.out.println("已被监听");
			//调用保存游戏函数
			
			//无名对象
//			new recorddata().writeRecord ();
			
			recorddata rd = new recorddata();
			rd.writeRecord();
			//跳出保存成功窗口
			 ReSuccess re=new  ReSuccess();
			 //退出
//			 System.exit(0);

			 
		//恢复  load
		}else if(e.getSource() == jmi2){
//			System.out.println("已被监听");
			if(this.mp3 != null)
			{
				System.out.println("是否进入旧mp3");
				this.remove(mp3);
			}
			//再加入之前 首先得删除旧的 这是第一步
			if(this.msp !=null){
			this.remove(msp);
			}
			recorddata.flags = "load";
			recorddata.setNodes(recorddata.getNodes());
			recorddata.RecordRead();
			
			
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
		}else if(e.getActionCommand().equals("newgame")){
			
			
			//很重要的if 判断
			//进入战斗后可以重新开始
			if(this.mp3!=null)
			{
				this.remove(mp3);  //忘记加上这么一句害我一直 错误   因为和原先的面板一直重
							//叠 ,必须把原先的模板给删除了
			}

			//也可以在这边读取数据！  或者在MYpanel中读取数据   
			recorddata.flags = "newgame";  //为什么这边最好把 flags参数放进构造函数的参数里是有道理的
						//这样你才不会写错！  可以当做一个技术点
//			recorddata.RecordRead();
			
		    recorddata.setEnNum(20);  //在recorddata.RecordRead已经有设置
		    recorddata.setMylife(3);
		    recorddata.setKillenNum(0);

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
		}else if(e.getActionCommand().equals("pause")){
			//可以一整个类击中设置  这是我第一次学到的   但是应该必须是static 属性才可以  这样类的属性才会被继承
			//证实的确是这样 
			bullet.setSpeed(0);
			Hero.setSpeed(0);
			EnemyTank.setSpeed(0);
			
			EnemyTank.setIsTurn(false);
			Hero.setIsTurn(false);
			jmi4.setEnabled(false);
			jmi5.setEnabled(true);
		}else if(e.getActionCommand().equals("continue")){
			bullet.setSpeed(1);  //这边可以继续设置速度
			Hero.setSpeed(1);
			EnemyTank.setSpeed(1);
			
			EnemyTank.setIsTurn(true);
			
			Hero.setIsTurn(true); 
			jmi4.setEnabled(true);
			jmi5.setEnabled(false);//然后可以对MyHero设置w s a d即可     而对于EnemyTank可以去run线程中！设置if(EnemyTank.s)
		}else if(e.getActionCommand().equals("exit")){
			
			//退出游戏
			//并且保存击毁敌人的数目   也就是说这边应该是有一个写入文件流的行为！  而这个动作最好是由Recorddata来完成
			new recorddata().writeRecord();
			System.exit(0);
		}else if(e.getActionCommand().equals("help")){
			Help h=new Help();
		}
*/