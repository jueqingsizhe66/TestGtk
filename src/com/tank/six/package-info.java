/**
 * 
 */
/**
 * @author Ҷ����
 * @time 2015��3��6������1:34:49
 * @version com.tank.sixpackage-info V1.0
 */
package com.tank.six;

/**  ��ѧ����ôһ�вſ���  ����menu���
 * 
 	//����һ����ʼ���
		msp = new MyStartPanel();
		//�����������
		jmb = new JMenuBar();
		jm1 = new JMenu("�ļ�(F)");
		//�����ļ����Ƿ�
		jm1.setMnemonic('f'); //�����á��� ֻ���õ����Ű���������
		
		//�����������
		//���ӿ�ʼ���
		this.add(msp);
		Thread t1 =new Thread(msp);
		t1.start();
		jmi1 = new JMenuItem("�����˳�(x)");
		jmi1.addActionListener(this);
		
		jmi2 = new JMenuItem("�ָ���(R)");
		jmi2.addActionListener(this);
		
		jmi3 = new JMenuItem("ͳ�Ƶ÷�");
		jmi3.addActionListener(this);
		
		jmi4  =  new JMenuItem("��ͣ(P)");
		jmi4.setMnemonic('P');
		jmi4.addActionListener(this);
		jmi4.setActionCommand("pause");
		
		
		jmi5  =  new JMenuItem("����(C)");
		jmi5.setMnemonic('C');
		jmi5.addActionListener(this);
		jmi5.setActionCommand("continue");
		jmi5.setEnabled(false);
		
		jnew  =  new JMenuItem("����Ϸ(N)");
		jnew.setMnemonic('N');
		jnew.addActionListener(this);
		jnew.setActionCommand("newgame");
		
		jexit  =  new JMenuItem("�˳�(E)");
		jexit.setMnemonic('E');
		jexit.addActionListener(this);
		jexit.setActionCommand("exit");
		
		
		jm2=new JMenu("����(H)");
	    jm2.setMnemonic('h');
	    jmi21=new JMenuItem("����˵��(U)");
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
		//�����˳� 
		if(e.getSource() == jmi1){ 
//			System.out.println("�ѱ�����");
			//���ñ�����Ϸ����
			
			//��������
//			new recorddata().writeRecord ();
			
			recorddata rd = new recorddata();
			rd.writeRecord();
			//��������ɹ�����
			 ReSuccess re=new  ReSuccess();
			 //�˳�
//			 System.exit(0);

			 
		//�ָ�  load
		}else if(e.getSource() == jmi2){
//			System.out.println("�ѱ�����");
			if(this.mp3 != null)
			{
				System.out.println("�Ƿ�����mp3");
				this.remove(mp3);
			}
			//�ټ���֮ǰ ���ȵ�ɾ���ɵ� ���ǵ�һ��
			if(this.msp !=null){
			this.remove(msp);
			}
			recorddata.flags = "load";
			recorddata.setNodes(recorddata.getNodes());
			recorddata.RecordRead();
			
			
			mp3 = new MyPanel2(); //���ڲ�������������齨��  �����Ļ���û���κ�̹����
			//���ֹ����� 
			
			//�������
			this.add(mp3);
			//���ô�������
			Thread t3 = new Thread(mp3);  //�൱�ڻ���һ���� �Ϳ�ʼ����������棡
			t3.start();
			//����JFrame������
			this.addKeyListener(mp3);
			//��������ɼ�    ���ǵڶ���   ȱһ����
			this.setVisible(true);
			
		}else if(e.getSource() == jmi3){
			System.out.println("�ѱ�����");
		}else if(e.getActionCommand().equals("newgame")){
			
			
			//����Ҫ��if �ж�
			//����ս����������¿�ʼ
			if(this.mp3!=null)
			{
				this.remove(mp3);  //���Ǽ�����ôһ�亦��һֱ ����   ��Ϊ��ԭ�ȵ����һֱ��
							//�� ,�����ԭ�ȵ�ģ���ɾ����
			}

			//Ҳ��������߶�ȡ���ݣ�  ������MYpanel�ж�ȡ����   
			recorddata.flags = "newgame";  //Ϊʲô�����ð� flags�����Ž����캯���Ĳ��������е�����
						//������Ų���д����  ���Ե���һ��������
//			recorddata.RecordRead();
			
		    recorddata.setEnNum(20);  //��recorddata.RecordRead�Ѿ�������
		    recorddata.setMylife(3);
		    recorddata.setKillenNum(0);

			//�ټ���֮ǰ ���ȵ�ɾ���ɵ� ���ǵ�һ��
			this.remove(msp);
			mp3 = new MyPanel2(); //���ڲ�������������齨��  �����Ļ���û���κ�̹����
			//���ֹ����� 
			
			//�������
			this.add(mp3);
			//���ô�������
			Thread t3 = new Thread(mp3);  //�൱�ڻ���һ���� �Ϳ�ʼ����������棡
			t3.start();
			//����JFrame������
			this.addKeyListener(mp3);
			//��������ɼ�    ���ǵڶ���   ȱһ����
			this.setVisible(true);
		}else if(e.getActionCommand().equals("pause")){
			//����һ�������������  �����ҵ�һ��ѧ����   ����Ӧ�ñ�����static ���Բſ���  ����������ԲŻᱻ�̳�
			//֤ʵ��ȷ������ 
			bullet.setSpeed(0);
			Hero.setSpeed(0);
			EnemyTank.setSpeed(0);
			
			EnemyTank.setIsTurn(false);
			Hero.setIsTurn(false);
			jmi4.setEnabled(false);
			jmi5.setEnabled(true);
		}else if(e.getActionCommand().equals("continue")){
			bullet.setSpeed(1);  //��߿��Լ��������ٶ�
			Hero.setSpeed(1);
			EnemyTank.setSpeed(1);
			
			EnemyTank.setIsTurn(true);
			
			Hero.setIsTurn(true); 
			jmi4.setEnabled(true);
			jmi5.setEnabled(false);//Ȼ����Զ�MyHero����w s a d����     ������EnemyTank����ȥrun�߳��У�����if(EnemyTank.s)
		}else if(e.getActionCommand().equals("exit")){
			
			//�˳���Ϸ
			//���ұ�����ٵ��˵���Ŀ   Ҳ����˵���Ӧ������һ��д���ļ�������Ϊ��  ����������������Recorddata�����
			new recorddata().writeRecord();
			System.exit(0);
		}else if(e.getActionCommand().equals("help")){
			Help h=new Help();
		}
*/