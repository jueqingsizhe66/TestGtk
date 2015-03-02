/**
 * 
 */
package com.original.sort;

/**
 * @author    叶昭良
 * @time      2015年2月27日下午1:35:36
 * @version   com.original.sortMonkeyGetPeach V1.0
 */
import java.util.*;
public class MonkeyGetPeach
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MonkeyGetPeach.scan();
	}
	//前天的苹果树  是当天的苹果数的两倍加1
	//day表示想得到哪天的桃子树   sday表示吃了多少天剩下一个桃子
		public static int peach(int day,int sday){
			if(day == sday){
				return 1;
			}else{
				return (peach(day+1,sday)+1)*2;
			}
		}
		
		
		public static void scan(){
			System.out.println("请先后输入想得到哪天的桃子数目  和吃了多少天剩下一个桃子！");
			Scanner sr = new Scanner(System.in);
			int a = sr.nextInt();
			int b = sr.nextInt();
			if(a<b){
				System.out.println(MonkeyGetPeach.peach(a,b));
			}else{
				System.out.println("第一个数要比第二个数打！");
				MonkeyGetPeach.scan();
			}
		}

}
