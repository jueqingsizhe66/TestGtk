package com.tank.six;

import java.util.*;







//�ӵ��ࣺ
/**
 * 
 * @author    Ҷ����
 * @time      2015��3��6������7:31:49
 * @version   com.tank.sixbullet V1.0
 * ���ܣ�  ����һ���ӵ����󣬴��ֳɵ�
                ���裺x  y  direction  speed,  ���캯���� ����
 * ˼����run������д��
 * ע�⣺
 * ���գ�
                ˼�����̵߳�run��������д
 * �عˣ�
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
	boolean isLive = true;//�ж��Ƿ�̹���Ѿ� �������ˣ�  �����Ͳ������ػ��ӵ��ˣ�   Ҳ������paint��������&&һ���жϲ�drawbullet�ͺ��ˣ�
	//Ȼ����̹��Ҳ��ʧ  Ҳ���ǲ�����drawtank��
	public bullet(int x,int y,int direction)
	{ //��һ�ı�͵������Hero��̹�˽����޸ģ�
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
		//�߳�һ��ŵĶ���while(true)�޸����꣡
		while(true)
		{
			try 
			{
				Thread.sleep(50);  //��Ȼ�Ļ� cpu�ķ�̫����
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//0���� ��  1 ��  2��  3��
			switch(direction)
			{
				case 0:  //���߳�ʹ�ӵ���  x  y����һֱ�ı䣡  ���ǲ�֪�������������  ����ûһ���̶߳��ǻ�����~  �������� �ͺ���ÿһ���˶������� һ����
				{

					y -= speed;
	//				System.out.println("��ǰ�ӵ�����Ϊ��x="+this.x+"y="+this.y);
					//ͨ����ӡ֪�� ��������仯��������һֱû��ֹͣ��  ����Ե���һ����ѧ������
					
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
			//����޷�repaint����ֻ���ܵ�panelȥ��
			
			if(x<-5||x>405||y<-5||y>405)
			{//�����ʱ������ĳ��� 6 2
				this.isLive = false;
				break;
			}
		}
	}
}
//̹����
class TankM
{
	//Tank������
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
	//Tank������
	int y = 0;
	
	//���е�̹�˶�����Ҫ�����  ���Ƕ���һ��direction����
	int direct = 2;  //Ӱ��л�̹�˵ĳ�ʼ��λ
	public int getDirect() 
	{
		return direct;
	}
	public void setDirect(int direct)
	{
		this.direct = direct;
	}
	//0������  1 ������  2 ������  3 ������
	

	public TankM(int x, int y, int direct)
	{
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	//Ϊ�˵��˵���Ҫ ��������������
	int type = 0;//Ĭ�����Ҿ�̹��
	public int getType() 
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	
	//�ж�̹���Ƿ�����  Ĭ���ǻ��ŵ�
	boolean isLive = true;  //Ȼ��̹�˳�����  400+����  ����400+����  ̹�˼���Ϊ����  �������ػ�

	//���Լ��ɶ��� �ӵ��� �� ��Ȼ����ʱû��
	

	
}


//��Tank���ж������Լ���һ��Tank  Heroang  
//�ҵ�̹��~
class Hero extends TankM
{

	public Hero(int x, int y, int direct) 
	{
		super(x, y, direct);
		// TODO Auto-generated constructor stub
//		this.direct = 0;
	}

	//���������ӵ�
	//bullet bl = null;
	//���������ӵ��У�  ���ӵ������ӵ��� �Ϳ���������   ��С�׼Ӳ�ǹ����ɨ���ھͿ����ˣ�
	//Ϊʲô����������ֳ�ʼ�� ���׵ĳ�ʼ���ڶ������ִ��
	Vector<bullet> vt = new Vector<bullet>();
	//���ü����� ����ֵ
	int life = 3 ;
	
	//�������е�̹�˶������ٶ� ���Ƕ�����һ��speed��ť   ������static��  ���Ա���ɾ��ǰ���һ�仰  ���������·��䵽Hero��Enemy��
	private static int speed = 1;//Ĭ��һ��һ������   
	public  int getSpeed() 
	{
		return speed;
	}
	public static void setSpeed(int speed) 
	{
		Hero.speed = speed;
	}
	
	/*//�ж��Ƿ�ת��  ������ÿһ�����е�������  ��Ϊ��static�࣡
	private static boolean IsTurn = true;
	public static boolean isIsTurn()
	{
		return IsTurn;
	}
	public static void setIsTurn(boolean isTurn)
	{
		IsTurn = isTurn;
	}*/
	
	
	//�ж��Ƿ�ת��  ������ÿһ�����е�������  ��Ϊ��static�࣡
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
	{  //���ĸ����ӵ�������һֱ�ı�
		switch(this.getDirect())
		{
			case 0:
			{
				bullet bl = new bullet(x+9,y,0);//֮���Բ�д��10����Ϊ ��ʵӦ���������ε����Ķ�����Ͳ
				//��ߴ����ӵ��� x y������̹�˵���������ģ�
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
			Thread t1 = new Thread(vt.get(i));  //׷�������߳�
			t1.start();
		}
		
	}
	//��װ������ �� �� �� ���ƶ�
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

	
	//���������ӵ�
	//	bullet bt = null;
	//���������ӵ���
	//�����һ����    ���ùؿ��� ����ss����Ϊstatic��������Ϊ�����
	static Vector<bullet> ss = new Vector<bullet>();
	
	//	bullet bl = new bullet(3,4,1);
	
	int times = 0; //ȫ�����ܵ�������ʱ��  �ӵ�ʱ��   �з�̹��ʱ��(30��*50ms(ÿһ������50))  ���ʱ��    �ӵ�ʱ��== ���ʱ��  ֻ��̹��ʱ��Ƚϳ�һ�� ���ǰѴ����ӵ��ķ������ڵ����Լ�
	//��run������      
	
	////����һ�����������Է��ʵ�MyPanel�����е��˵�̹��  �����Ҫ�Զ��ڴ������˽⣡
	Vector<EnemyTank> ets=new Vector<EnemyTank>();  //��Ȼ���ʵ���Դ��һ���ģ� //����������

	//�ж��Ƿ�ת��  ������ÿһ�����е�������  ��Ϊ��static�࣡
	private static boolean IsTurn = true;
	
	public static boolean isIsTurn() 
	{
		return IsTurn;
	}
	public static void setIsTurn(boolean isTurn)
	{
		IsTurn = isTurn;
	}
	
	
	
	//�������е�̹�˶������ٶ� ���Ƕ�����һ��speed��ť   ������static��  ���Ա���ɾ��ǰ���һ�仰  ���������·��䵽Hero��Enemy��
	private static int speed = 1;//Ĭ��һ��һ������   
	public  int getSpeed() 
	{
		return speed;
	}
	public static void setSpeed(int speed) 
	{
		EnemyTank.speed = speed;
	}
	
	//�õ�MyPanel�ĵ���̹������    �����Ǹ����ı�setets�أ�   Ӧ��������ϣ�
	public void setEts(Vector<EnemyTank> vv)
	{
		this.ets=vv;
	}
	
	
	/**
	 * �ж��Ƿ���������̹��   Ȼ���ٹ��캯����  Ȼ����run������ �������� �䵱������
	 * @return  ������� �򷵻�true  ����false
	 */
	public boolean isTouchOtherEnemy()
	{
		boolean b=false;
//		Shot s = new Shot(10,10,0);
//		ss.add(e);�ں�����Ҳ�ǿ��Ե���ss
		
		switch(this.direct)
		{
		case 0:
			//�ҵ�̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.direct==0||et.direct==2) //�Ҹо�ֻ��Ҫ����et.direct ==2 ����  ��Ϊ�ٶ�һ��
					{
						//���
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//�ҵ�
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
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.direct==0||et.direct==2)
					{
						//�ϵ�
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//�µ� 
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
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.direct==0||et.direct==2)
					{
						//�ҵ����
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//�ҵ��ҵ�
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
			//����
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.direct==0||et.direct==2)
					{
						//�ҵ���һ��
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//��һ��
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						//��һ��
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
		//�߳�һ��ŵĶ���while(true)�޸����꣡
		while(true){
			if(EnemyTank.isIsTurn())
			{
			 ///������1.5��ſ�ʼ�����Ե�  �����Ұ�����ᵽ��̹��֮ǰ   ������û�߾ͷ����ӵ�
			this.times++;
			//����޷�repaint����ֻ���ܵ�panelȥ��
			if(times %2 == 0) //ȡģΪ0��ֱ��
			{
//				//�ж��Ƿ�ø�����̹�˳�ʼ���ӵ��ͼ��ӵ�
				
//				for(int i = 0 ; i< this.ss.size() ; i++){
//				//���ڰ��⼸�зŵ�run������ ��Ȼ���ֻ�ܾ��õط�����һ���ӵ�   ���캯��ֻ�ܹ���һ��  ��������Ҫ���ϵ�run
//				EnemyTank ent = this.get(i);
//				if(ent.isLive){
//				 if(ent.ss.size()<5){  //[���Ƶ���̹���Ƿ����������]
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
					}  //ֻ�Ӳ�����һ����ϰ��   ����ʲô�ط������ͷ�  һ�������paint������
//				bullet bl = new bullet(ent.x,ent.y,ent.direct);
				//�������̹��
			    
			     //һ��Ҫ�Լ������ӵ��߳�   ��Ϊ�����ǲ�������ģ����ӵ��Լ���ȥ�ɣ�

				
				}
			//this.direct = (int)Math.random()*4; ����д���е�
			
			//0���� ��  1 ��  2��  3��
			switch(this.direct)
			{
			case 0:  //���߳�ʹ�ӵ���  x  y����һֱ�ı䣡  ���ǲ�֪�������������  ����ûһ���̶߳��ǻ�����~  �������� �ͺ���ÿһ���˶������� һ����
			{
				for(int i = 0 ; i<30; i++)
				{
					if(y>0&& !this.isTouchOtherEnemy())
					{ //�жϵ�Ŀ����������ʹ���������ڿ����еķ�λ��  [���ڿ���̹�˵ķ�Χ]
						y -= getSpeed();
					
					}else
					{
						break;
					}
					try 
					{
						Thread.sleep(50);  //�Ҿ���ÿһ����Ӧ�ø�ˢ�µ��ٶ�һ��  ������Ϊ��Ϣ50
						//��Ȼ�Ļ� cpu�ķ�̫����
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*System.out.println(this.direct); ����������ʾ�����ˣ� ������ը����*/}
					
				
//				System.out.println("��ǰ�ӵ�����Ϊ��x="+this.x+"y="+this.y);
				//ͨ����ӡ֪�� ��������仯��������һֱû��ֹͣ��  ����Ե���һ����ѧ������
				
			}
			break;
			case 1:
			{
				for(int i = 0 ; i<30; i++)
				{
					if(x<400 && !this.isTouchOtherEnemy())
					{ //[���ڿ���̹�˵ķ�Χ]
						x += getSpeed();
					}else
					{
						break;
					}
					try 
					{
						Thread.sleep(50);  //��Ȼ�Ļ� cpu�ķ�̫����
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
					{  // [���ڿ���̹�˵ķ�Χ]
						y += getSpeed();
					}else
					{
						break;//ֱ��������  ��Ҫ��ѭ����
					}
//					System.out.println(this.direct);
					try 
					{
						Thread.sleep(50);  //��Ȼ�Ļ� cpu�ķ�̫����
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
					{ //[���ڿ���̹�˵ķ�Χ]
						x -=getSpeed();
					}else
					{
						break;
					}
					try 
					{
						Thread.sleep(50);  //��Ȼ�Ļ� cpu�ķ�̫����
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*System.out.println(this.direct);*/} //��ʱ����û�а��������һ�� ����һֱ��������
					
				
			}
			break;
			}
			
			
				
			

			this.direct = (int)(Math.random()*4); //����һ���µķ���
			
			/*�����Ļ�  ����һֱ�ı� ̹�˿϶���֪���ø�ʲô��  ��������һ��������   ��������һ�� ��һ������*/
			if(x<-5||x>405||y<-5||y>405)
			{//�����ʱ������ĳ��� 6 2
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


//����һ��ը����
/**
 * ը�����javaBeans�淶���Ժ��ٸ�
 * @author    Ҷ����
 * @time      2015��3��6������7:13:17
 * @version   com.tank.sixbome V1.0
 */
class bome
{
	//����ը������
	int x,y;
	//����ը��������ֵ  Ĭ�ϵ�����Ϊ12����
	int life = 15;
	//�����Ƿ���
	boolean isLive = true;
	
	public bome(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	//�������ٺ�����   ���Ǹ����ıߴ����أ� ��Ȼ�ǰ�������hitTank ��   һ�������ǾͿ��Բ���һ��ը��  ����������
	//����ը������ֻ��һ��  12life ��4��ը���� ����һ��Vector ��  ���Ե���MyPanel������һ��Vector����
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