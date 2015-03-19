/**
 *  ͨ���ӿ���ʾ��̬�� ������ǰ�ڰ󶨺ͺ��ڰ�
 */
package com.original.sort;

/**
 * @author    Ҷ����
 * @time      2015��2��27������12:29:26
 * @version   com.original.sortInterface_preStage_postStage_polymophic V1.0
 */
public class Interface_preStage_postStage_polymophic
{
	
	 //�۳�����
	 private int money = 0;
	 //����һ����
	 //ͨ��car�ӿ�  Ȼ��ѡ��ͬ�ĳ���
	 public void sellCar(car car1){
		 System.out.println("���ͣ�"+car1.getName()+"����"+car1.getPrice());
		 //�����������ۼ۵�����
		 money += car1.getPrice();
	 }
	 
	 public int getMoney()
	 {
		 return money;
	 }
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Interface_preStage_postStage_polymophic ippp = new Interface_preStage_postStage_polymophic();
//		System.out.println("BNW:"+ippp.sellCar(new BNW()));
		//ԭ������BNW�������sellCar��ʱ���Ѿ���ӡ������ �����ظ���ӡ
		//����һ����
		ippp.sellCar(new BNW());
		//��������һ���� cherryQQ
		ippp.sellCar(new CheryQQ());
		//��������һ���� Santana
		ippp.sellCar(new Santana());
		
		//��ӡ��������������
		System.out.println("��������������������"+ippp.getMoney());
		
	}

}

interface car
{
	String getName();
	int getPrice();
}

//����

class BNW implements car
{
	public String getName()
	{
		return "BNW";
	}
	
	public int getPrice()
	{
		return 300000;
	}
}


//cherry QQ 

class CheryQQ implements car
{
	public String getName()
	{
		return "cheryQQ";
		
	}
	public int getPrice()
	{
		return 32000;
	}
}


//��������ڻ���������һ�����͵Ļ�  ֻҪ�������дһ��car�Ľӿں����ͺ�    ����carshop���������������Ҫ
//�����κε��޸�     �����ͺܺõ���  carshop ���е� sellcar����ʵ���˶�̬

/*
 * ���н����
 *  ���ͣ�BNW����300000
	���ͣ�cheryQQ����32000
	������332000
	�ܽ� ��ߵ� sellcar�Ĳ���  �����ں��ڰ�  
	
	c++ ��java����ǰ�ڰ�  �ͺ��ڰ󶨵�˵��  
	ΪʲôҪ������˵�� ������˵����ʲô�ĺô� ������ʲôʱ���õ�����
	ǰ�ڰ����ڳ�������֮ǰ  �ɱ����������������а�  �ֽо�̬��  static������final���� 
	����Ҳ�ǰ���private��������Ϊ������ʽfinal��Ҳ����˵final����ʽ����ʽ���֣�
	
	���ڰ󶨣�������ʱ���ݶ�������ͽ��а�  �з������û���ʵ�� Ҳ������̬��
	  �����Ҫע����� �������� �ͷ������û���   Ҳ���Ƕ�̬����ͨ������ʵ�� Ҳ��������� sellCar�Ĳ���
	  ��Ҳ��һ����̬�� �� ���ݲ�ͬ�Ķ�����а�
	  ֮���Բ���ǰ�ڰ󶨺ͺ��ڰ��Ҿ�������Ϊ��̬����Ҫ  �Լ�static��final��������Ҫ�� Ϊ���������ǵļ���
	  ʱ��Ĳ�ͬ ��
	   ��ô���������ö�̬  ��̬����Ҫ�̳к� �ӿڵ�ʵ��   ��������������ֺ��ڰ󶨵�����Ҳ�������java�ļ��е�����
	    extends����interface implements  ��Щ�ؼ���
	    
	    Ҳ����˵��̬�����ں��ڰ󶨵����ֻ�����ʵ��

 * */

//ɣ����

class Santana implements car{
	public String getName(){
		return "Santana";
	}
	
	public int getPrice(){
		return 50000;
	}
}


//�����Ϳ���ֱ�ӵ�����
