/**
 * �龰 : ��һ�Ҳ���,ʱ��������Ʒ , ���˽���ʱ�����չʾ���еĲ�Ʒ,
 * Ҫ�󿪷�һ�������Ժõĳ���,���Ǹ�����Ʒʱֻ��Ҫ��һЩ�򵥵����þͿ���
 * 
 * ��������� �Լ���ѧ ����    ��Ƶû��ô��
 */
package com.reflect.test;

import java.util.ArrayList;


/**
 *  ͨ������Ĳ�����  �ܷ����ͨ��Food�ӿ� 
 *  ���Ӹ�����²ˣ� ֻҪ�����¶���һ��  ʳ��.java Ȼ����
 *  food.properties����һ����ֵ�Լ���
 */



/**
 * @author    Ҷ����
 * @time      2015��3��3������4:44:15
 * @version   com.reflect.testRestaurant V1.0
 */
import com.reflect.test.food.*;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Restaurant 
{

	/**
	 * @param args
	 */
	//û���뵽��list���������ݣ�һ��Ҫ�м��ϵĸ��
	private List<Food>  foods = new ArrayList<Food>();
	
	//����һ��init���� �����ڹ��캯���ڲ� ���ã�����ʼ���˵���׼������ ������
	public Restaurant()
	{
	}
	public void init()
	{
		try
		{
			InputStream is = Restaurant.class.getResourceAsStream("food.properties");
			Properties prop = new Properties();
			/**
			 *  Ҫ֪�� food.properties ��key-value��д��
			 *  Prop�������һ��map����
			 *  ��ǰֻд��һ��ֵ�Ǵ���ģ� ����д��
			 *  Apple=com.reflect.test.food.Apple
			 */
			//�������src����
			//prop.load(new FileInputStream("food.properties"));
			try
			{
				prop.load(is);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Collection  coll1 = prop.values();
			for(Object foodClassName:coll1)
			{
				Class temp;
			
				temp = Class.forName((String)(foodClassName));
				
				Food food = (Food)temp.newInstance();
				foods.add(food);
			}
		}catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.out.println("δ�ҵ���Ӧ����");
		}catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			System.out.println("����ʵ�����쳣");
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void showFoods()
	{
		for(Food temp:foods)
		{
			//Food�ӿڴ�����һ��Ψһ�ӿڷ���
			System.out.println(temp.getFoodName());
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//����һ�ҵ�
		Restaurant res = new Restaurant();
		System.out.println("������λ���͵ĵ���-- ��������");
		System.out.println("******************************");
		res.showFoods();
	}

}