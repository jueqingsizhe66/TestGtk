/**

������һ�����⣬��ΰ�Vector��ΪArrayList����LinkedList

�������ʹ����HashMap


1:ͨ�� Graphics��MyJpanel�е���Paint������  ͨ��g������ö�Ӧ��ͼ�ķ���
      //����һ������ ������Tank����
       paint����ֻ����
       
2������ĳЩ�߳�   ��Ҫ�Լ���� ��Ȼ�ӵ�    �з�ս��
3�� ����ʱ�����  ����ҷ�̹��
4�� ����һ�����ݽṹ  ���ڴ洢̹��  �ӵ���
5�� �ж��ӵ���ը�ĺ���   �ж�̹�˽Ӵ��ĺ���

6��̹�˾���  �������ҵķ���   λ��x y(ƽ�����꣩ ��ʵӦ�û����ٶ�speed
7��ͨ��������� ˼�������б��
8��ͨ��this.repaint�����ػ����
     ÿ����һ�μ��̣�����������ˢ����Ļ��
9��KeyEvent��getKeyCode����  ������OOGTK��OOKeyCode�ҵ���Ӧ�ļ�������

10�� ����������˼��˵�����ӵ� shootEnemyӦ�÷������  Hero�� �ҷ�����
       ��ô�ط���ͨ���̲߳��ϵؽ���ɨ��
       1.�Լ�װ�ӵ�
       2.�Լ����ӵ�
       3.�Լ������з���ֻҪ������ �͹�����
        �õ���̹��Ҳ�ܷ����ӵ���   ��ʹ���˷�����ӵ��ܹ�hitme������������ϣ���
    //����һ��Vector��������������ط���ս��
	Vector<EnemyTank> eet = new Vector<EnemyTank>();
	Vector<bome> bomes = new Vector<bome>();
	//���������ӵ�
	//bullet bl = null;
	//���������ӵ��У�  ���ӵ������ӵ��� �Ϳ���������   ��С�׼Ӳ�ǹ����ɨ���ھͿ����ˣ�
	//Ϊʲô����������ֳ�ʼ�� ���׵ĳ�ʼ���ڶ������ִ��
    Vector<bullet> vt = new Vector<bullet>();
	 
       ���趨�ҵ�������
11���ӵ�Ҳ���� λ��x y  ��ʵӦ�û����ٶ�

12: ֮ǰʹ��Vectorһ���̰߳�ȫ������洢 �ط���ս��̹�� �� 1 �������ݽṹ �洢̹������
                                                    2 �ڽṹ�����и���̹���������л���
                                                    3 
13�� isLive���ж������Ĳ���   �����ӵ�����ը��  ̹�ˣ��������þ���

14����֤����̹�˲����߽�    if(x>0)    �߽���ο���
15: ը���Ļ���

16�� ��Ϻͽӿڵ�˼��  �̳е�˼��

17���ж��Ƿ�ת�� isTurn �ط��Լ�ת  �ҷ�ͨ��w a s d���п���


18�� �����ǲ���Ҫ�Ĳ��� ! ��ȴ��̹�˴�ս��������  ������Recorddata�࣬���ڶ�ȡ���� �͵������� 
            1��ɱ̹���� killenNum
            2:��¼ÿ���ж��ٸ�����private static int enNum = 20 ;
            3:mylife;
            4:enems ����̹��
            5�� �ҷ�̹��
            
19��  ��2���������¹���
 *     1���Էֹ�
 *       1.1ר����һ���յ�Jpanel  ����ʾ����
 *       1.2��������˸  ��һ�仰���ѣ�  ��˸ �� һ�ử������  һ�᲻��������
 *       		�����ǳ��ֲ�ͬ������� ��ʱ��ʱ���������漰��ʱ������⣡  Ӧ���Ǹ�ʱ���йأ�  ��Ƴ�һ���߳�  ��ͬ��ʱ�������귢���ı䣡
 *     2������ͣ�ͼ���
 *     
 *       ���û������ͣʱ �� �ӵ� �� ̹�˵��ٶ�Ϊ 0   ���ҷ�����Ϊ���� ����  
 *       				��Ȼ��֮ǰ�����ȱ���  �ӵ���̹�˵ġ�������������
 *     3���Լ�¼��ҵĳɼ�
 *     	3.1�ļ��� �������涼���ļ�����  �����Ǵ�����Ϸ�����ݿ�  CS ���Σ�
 *      3.2��дһ����¼��
 *     4java�ٿ������ļ�
 *     ���⣺ʲôʱ�򱣴�    ʲô����һ�� ���е��˶�û�е�ʱ��
19 datarecord������ ����Ϸ��ʱ��Ž��ж�ȡ		
**
 * @author Ҷ����
 * @time 2015��3��6������9:45:19
 * @version com.test.tankpackage-info V1.0
 */
/**
 * װ�ӵ��Ĺ���
 * 			if(temp.isLive == true){
			drawTank(temp.getX(),temp.getY(),g,temp.getDirect(),temp.getType());
//			temp.shootHero();  //���ڸ�̹��װ��ը����  �Ŵ��ط���  ����ǻ��� ����Ҫ���ڹ��캯����
				//ȡ���ӵ�
				for(int j = 0 ; j<temp.ss.size(); j++){
					bullet tempbl = temp.ss.get(j);
					if(tempbl.isLive){ //�����ӵ�
						drawbullet(tempbl.x,tempbl.y,g,tempbl.getDirection(),1);
//						System.out.println("��"+(i)+"��̹�˵ĵ�"+(j+1)+"���ӵ���"+"�����ǡ�����"+tempbl.x+" y ="+tempbl.y);
					//ͨ�� ���Բ鿴ÿһ��̹�˵ڼ����ӵ�������[]   �������һ���ö�û�У�
						//���飺	
					}else{
						//���ӵ���ɾ�� ������ʱ��͵����  ���ſӸ�������
//						eet.remove(tempbl);//����ɾ��̹��  Ӧ����ɾ���ӵ�
						temp.ss.remove(tempbl);  //��������仰�������Ч��
					}
					
				}
			}
 */
//----------------------------------ը����ĸĽ�---------------- ������ֵ
/**
 * //����һ��ը����
class bome{
	//����ը������
	int x,y;
	//����ը��������ֵ  Ĭ�ϵ�����Ϊ12����
	int life = 15;
	//�����Ƿ���
	boolean isLive = true;
	
	public bome(int x, int y){
		this.x = x;
		this.y = y;
	}
	//�������ٺ�����   ���Ǹ����ıߴ����أ� ��Ȼ�ǰ�������hitTank ��   һ�������ǾͿ��Բ���һ��ը��  ����������
	//����ը������ֻ��һ��  12life ��4��ը���� ����һ��Vector ��  ���Ե���MyPanel������һ��Vector����
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
 * 		//��ʼ��ը��
		for(int i = 0 ; i<bomes.size() ; i++){
			//ȡ��ը��
			System.out.println("ը���������ǣ�"+bomes.lastElement()+"bomes.size = "+bomes.size());  //���Կ����ǲ���ը����û����
			bome b1 = bomes.get(i);
			if(b1.life >10){
				
				g.drawImage(im1, b1.x, b1.y, 30, 30, this);
			}else if(b1.life >5){
				g.drawImage(im2, b1.x, b1.y, 30, 30, this);
			}else{
				g.drawImage(im3, b1.x, b1.y, 30, 30, this);
			}
			b1.lifedown();
			//���ը��������ֵΪ0  ���ը����������ȥ��
			if(b1.life == 0){
//				b1.isLive = false;
				bomes.remove(b1);
			}
		}
 */


/**
 * ���������ж�ը��������
 * public void hitTank(bullet bl, EnemyTank em){
		switch(em.getDirect()){
		case 0:
		case 2:
		{
			if(bl.x>em.x && bl.x<em.x+20 && bl.y>em.y &&bl.y<em.y+30){
				//����
				//�ӵ�����
				bl.isLive = false;
				//����̹������
				em.isLive = false;
				
				//���̹������  ���ǿ�ʼ��������
				//����һ��ը������
				bome b1 = new bome(em.x,em.y);
				//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
				bomes.add(b1);
			}
		
		}
		break;
		case 1:
		case 3:
		{
			//�������if(bl.x>em.x && bl.x<em.x+30 && bl.y>0 &&bl.y<em.y+20)
			if(bl.x>em.x && bl.x<em.x+30 && bl.y>em.y &&bl.y<em.y+20){ 
				//����
				//�ӵ�����
				bl.isLive = false;
				//����̹������
				em.isLive = false;
				
				
				//���̹������  ���ǿ�ʼ��������
				//����һ��ը������
				bome b1 = new bome(em.x,em.y);
				//���ӽ�ը����       ����ը������������ͼƬ��� �������ǵ����������������ͼƬ  �� Ȼ���ٹ��캯���ж���ͼƬ
				bomes.add(b1);
			}
		}
		}
 */



/**
 * 
 * �ӵ���runnable�ӿ�
 * 	@Override
	public void run() {
		// TODO Auto-generated method stub
		//�߳�һ��ŵĶ���while(true)�޸����꣡
		while(true){
			try {
				Thread.sleep(50);  //��Ȼ�Ļ� cpu�ķ�̫����
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//0���� ��  1 ��  2��  3��
			switch(direction){
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
			
			if(x<-5||x>405||y<-5||y>405){//�����ʱ������ĳ��� 6 2
				this.isLive = false;
				break;
			}
		}
	}
 */

/**
 * ����ֵ�½�����
 * 	public void lifedown(){
		if(life>0){
			life--;
		}else{
			this.isLive = false;
		}
	}
 */


/**
 * 	//�ж��Ƿ���������̹��   Ȼ���ٹ��캯����  Ȼ����run������ �������� �䵱������
	//�ж��Ƿ������˱�ĵ���̹��
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
 */
package com.test.tank;



/**
 * 
 * ������isIsTurn���� ���� �ж�times %2 == 0
 * 	@Override
	public void run() {
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
					}  //ֻ�Ӳ�����һ����ϰ��   ����ʲô�ط������ͷ�  һ�������paint������
//				bullet bl = new bullet(ent.x,ent.y,ent.direct);
				//�������̹��
			    
			     //һ��Ҫ�Լ������ӵ��߳�   ��Ϊ�����ǲ�������ģ����ӵ��Լ���ȥ�ɣ�

				
				}
			//this.direct = (int)Math.random()*4; ����д���е�
			
			//0���� ��  1 ��  2��  3��
			switch(this.direct){
			case 0:  //���߳�ʹ�ӵ���  x  y����һֱ�ı䣡  ���ǲ�֪�������������  ����ûһ���̶߳��ǻ�����~  �������� �ͺ���ÿһ���˶������� һ����
			{
				for(int i = 0 ; i<30; i++){
					if(y>0&& !this.isTouchOtherEnemy()){ //�жϵ�Ŀ����������ʹ���������ڿ����еķ�λ��  [���ڿ���̹�˵ķ�Χ]
					y -= getSpeed();
					
					}else{
						break;
					}
					try {
						Thread.sleep(50);  //�Ҿ���ÿһ����Ӧ�ø�ˢ�µ��ٶ�һ��  ������Ϊ��Ϣ50
						//��Ȼ�Ļ� cpu�ķ�̫����
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//				System.out.println("��ǰ�ӵ�����Ϊ��x="+this.x+"y="+this.y);
				//ͨ����ӡ֪�� ��������仯��������һֱû��ֹͣ��  ����Ե���һ����ѧ������
				
			}
			break;
			case 1:
			{
				for(int i = 0 ; i<30; i++){
					if(x<400 && !this.isTouchOtherEnemy()){ //[���ڿ���̹�˵ķ�Χ]
					x += getSpeed();
					}else{
						break;
					}
					try {
						Thread.sleep(50);  //��Ȼ�Ļ� cpu�ķ�̫����
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
					if(y<400 && !this.isTouchOtherEnemy()){  // [���ڿ���̹�˵ķ�Χ]
					y += getSpeed();
					}else{
						break;//ֱ��������  ��Ҫ��ѭ����
					}
//					System.out.println(this.direct);
					try {
						Thread.sleep(50);  //��Ȼ�Ļ� cpu�ķ�̫����
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
					if(x > 0 && !this.isTouchOtherEnemy()){ //[���ڿ���̹�˵ķ�Χ]
					x -=getSpeed();
					}else{
						break;
					}
					try {
						Thread.sleep(50);  //��Ȼ�Ļ� cpu�ķ�̫����
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
} //��ʱ����û�а��������һ�� ����һֱ��������
					
				
			}
			break;
			}
			
			
				
			

			this.direct = (int)(Math.random()*4); //����һ���µķ���
			
			�����Ļ�  ����һֱ�ı� ̹�˿϶���֪���ø�ʲô��  ��������һ��������   ��������һ�� ��һ������
			if(x<-5||x>405||y<-5||y>405){//�����ʱ������ĳ��� 6 2
				this.isLive = false;
				
			}
			if(this.isLive == false){
				break;
			}
		}
		}
	}
	
*/