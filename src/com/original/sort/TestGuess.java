/**
 * 
 */
package com.original.sort;

/**
 * @author    叶昭良
 * @time      2015年2月27日下午12:41:28
 * @version   com.original.sortTestGuess V1.0
 */
import java.util.*;
public class TestGuess
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Tom tom = new Tom();
		Scanner sr = new Scanner(System.in);
		int x = 0;
		while(0 == x)
		{
			tom.get();	
			tom.caiquan();
			System.out.println("请选择： 0.为继续1.为结束");
	 		x = sr.nextInt();
		}
		System.out.println("tom赢了" + tom.count + "次");
		tom.print();
	}

}

class Tom{
	//记录电脑出的随机数
	public int r;
	public static int count = 0; 
//用集合的观念存储东西     只记录你赢的次数  你赢的情况
	public List<Integer> ll = new LinkedList<Integer>();


	public void get(){
	  Scanner sr = new Scanner(System.in);
	  System.out.println("请输入你要出什么？  0为石头 1为剪刀 2为布");
	  //获取你输入的整数
	  int x = sr.nextInt();
	  this.r = x;
		}
	public void print(){
		if(0 == this.r)
			{System.out.println("tom出石头");}
		else if(1 == this.r)
			{System.out.println("tom出剪刀");}
		else
			{System.out.println("tom出布");}
		}
	public void caiquan(){
	   int a = (int)(Math.random()*3);
		if(0 == a && 0 == this.r){
		  System.out.println("电脑出石头");
		  this.print();
		  System.out.println("一样");
		}else if(0 == a && 1 == this.r){
			System.out.println("电脑出石头");
			this.print();
			System.out.println("电脑赢！");
		}else if(0 == a && 2 == this.r){
			System.out.println("电脑出石头");
			this.print();
			System.out.println("你赢了！");
			ll.add(0);
			this.count++;
		}else if(1 == a && 0 == this.r){
			System.out.println("电脑出剪刀");
			this.print();
			System.out.println("你赢了！");
			ll.add(1);
			this.count++;
		}else if(1 == a && 1 == this.r){
			System.out.println("电脑出剪刀");
			this.print();
			System.out.println("一样");
		}else if(1 == a && 2 == this.r){
			System.out.println("电脑出剪刀");
			this.print();
			System.out.println("电脑赢！");
		}else if(2 == a && 0 == this.r){
			System.out.println("电脑出布");
			this.print();
			System.out.println("电脑赢！");
		}else if(2 == a && 1 == this.r){
			System.out.println("电脑出布");
			this.print();
			System.out.println("你赢了！");
			ll.add(2);
			this.count++;
		}else{
			System.out.println("电脑出布");
			this.print();
			System.out.println("一样");
		}
	    }
}
