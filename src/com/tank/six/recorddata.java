package com.tank.six;
//record�ͺ�������ʵ�еĲ���һ��   ��������ʱ���¼���Ͼͼ�һ    hitnums��һ

import java.io.*;
import java.util.Vector;
public class recorddata 
{
	//��ʼ��дһ����¼��     ���������ļ�����F:/tankrecord.txt�У�
	
		
		//��¼ÿ���ж��ٸ�����
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
		//��¼���Լ�����������   ///����ǰ�����õ�lifeɾ��
		private static int mylife = 3;
		
		//��¼�ܹ������˶��ٵ���
		private static int killenNum = 0;
		
		
		//����һ����־  
		//��flags == newgame ֻ�ǻָ��� ����ļ�¼�ĵ�һ�У� Ҳ����killnum
		//��flags == load  ��ָ�����������   killnum   ��������   �͵�������
		public static String flags = null;
		
		//Ϊ�˱�֤load�����  ������е�tank�����½���һ��Enemytank����
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
		//�����½�һ���ڵ��������̹�˵�����  �պ���TankM��Ϳ�����  ������ һ �з���
		public static Vector<Node> nodes = new Vector<Node>();
		//public static Vector<TankM> nodes = new Vector<TankM>();
		
		//���ھͿ���ʹ��������������  ��load 
		
		public static int getKillenNum()
		{
			return killenNum;
		}
		public static void setKillenNum(int killenNum)
		{
			recorddata.killenNum = killenNum;
		}
		//ÿ�����һ������ ���ٵ�����Ŀ
		public static void reduceenNum()
		{
			enNum--; //��hitenemy�е���
		}
		
		//��ÿ�����˴�һ��  ����ֵ��һ
		public static void reduceMylife()
		{
			mylife--;
		}
		
		//�ҵ�ս��ͳ��
		public static void addkillnum()
		{
			killenNum++;
		}
		
		//д���¼
		//������ս�ĳɼ�  ��ͷ��ʼ   �������Ǳ��ص�  ���Ǻܶ�أ� //�����˳���Ϸ�Ĺ�����
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
					//�ָ����������  �������ݡ������ȵ�ȥ��һ������̹������ ��Recorddata����
					bw.write(recorddata.getKillenNum()+"\r\n");
					bw.write(recorddata.getEnNum()+"\r\n");
					bw.write(recorddata.getMylife()+"\r\n");
					
					for(int i = 0; i< enems.size() ; i++)
					{
						//������е�ÿһ������̹�˵�����
						EnemyTank temp = enems.get(i);
						//������ǻ�ľͿ���д��
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
				//��д���ȹص�  �ͺ�����ջһ��
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
		//��ȡ��¼
		private static FileReader fr = null;
		private static BufferedReader  br = null;
		public static void RecordRead()
		{
			try 
			{
				fr = new FileReader("F:/datarecord.txt");
				br = new BufferedReader(fr);
				
				//��ζ���   ����Interger
				String n = null;
				try 
				{
					if(flags.equals("newgame"))
					{
						n = br.readLine();
						killenNum = Integer.parseInt(n);
					}else if(flags.equals("load"))
					{
						
						//�ֱ��ȡ��1.2.3��
						String n1=br.readLine(); //��ô�ͷ��β������
						String n2=br.readLine();//��õ��˵�ʣ����
						String n3=br.readLine();//����ҵ�����ֵ
						setKillenNum(Integer.parseInt(n1));
						setEnNum(Integer.parseInt(n2));
						setMylife(Integer.parseInt(n3));
						//��ȡ̹�˵�����ͷ���
						while((n=br.readLine())!=null)
						{
							//�Կո�Ϊ��־���ַ����з�
							 String []xydirect=n.split(" ");
							Node node=new Node(Integer.parseInt(xydirect[0]),Integer.parseInt(xydirect[1]),Integer.parseInt(xydirect[2]));
							//���ζ�ȡÿһ���ڵ����Ϣ
							nodes.add(node);
							//�������ǾͿ�����nodes ��load����������  ֱ��recorddata.RecordRead() �Ϳ����� ��չ�Ի���

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
					//���ȹ�  
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


