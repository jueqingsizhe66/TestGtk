/**
 * 
 */
package com.original.sort;

import java.util.Scanner;

/**
 * @author    叶昭良
 * @time      2015年2月27日下午1:31:09
 * @version   com.original.sortSimulationMaries V1.0
 */
public class SimulationMaries
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner sr = new Scanner(System.in);//System.in
		System.out.println("1-25 or we will quit!");
		int i = sr.nextInt();
		
		Mariess mr = new Mariess();
//		for(int a = i ;a<=25;a--){//为什么这样写for循环
		while(i<=25 && i>=0)
		{
//			if(i<=25){
				mr.move(i);
				i = sr.nextInt();
				if(i>25 && i<0)
				{
					System.out.println("you have stop the game!");
					break;
				}
			}
	}

}


class Mariess
{
	//private int x,y;
	//构造方法  初始化场景  	确定地面  Maries的位置
	
	public Mariess()
	{
		System.out.println("I am Maries @");
	}
	public void move(int x)
	{
		for(int k = 1 ; k<=25; k++)
		{
			if(x==k)
			{
				System.out.print("@");
			}else
			{
				System.out.print("。");
			}
		}
	}
}