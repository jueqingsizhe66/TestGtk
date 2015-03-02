/**
 * 
 */
package com.original.sort;

/**
 * @author    叶昭良
 * @time      2015年2月27日下午12:54:06
 * @version   com.original.sortTestGuess2 V1.0
 */
import java.util.*;
public class TestGuess2
{

	/**
	 * @param args
	 */
	public static int count = 0;
	public static int p=0;//平的次数
	public static int q=0;//你赢的次数
	public static int r=0;//电脑赢的次数
	public static String[] result=new String[50];
	public static void main(String[] args)
	{
/*		for(int i  =0 ; i < 10; i++)
		{
			System.out.println(Math.random());
		}*/
		// TODO Auto-generated method stub
		System.out.println("你想和电脑猜拳嘛？想的话输入 0 1 2 0代表石头 1代表剪刀 2代表布");
		Scanner sr = new Scanner(System.in);
		Random rand = new Random(System.currentTimeMillis());
		int i = 0;
		int j = 0;
		while(true){
			System.out.println("你想好了，出什么？(0石头,1剪刀,2布)");
			i = sr.nextInt();//i存储你出的值~   j存储电脑出的值
			//如何产生正确的随机数？
		//	int j = (int)Math.random()*3%3;
			j = Math.abs(rand.nextInt()%3);
			if(i == j){
				p++;
				result[count] = "你出的："+i+" 电脑出的"+j+"\n两者打平\n";
				System.out.println("两者打平！");
			}else if((i-j) == -1 || (i-j)==2){
				q++;
				result[count] ="你出的："+i+" 电脑出的"+j+"\n恭喜你赢了\n";
				System.out.println("恭喜你赢了！");
			}else{
				r++;
				result[count] = "你出的："+i+" 电脑出的"+j+"\n电脑赢了\n";
				System.out.println("两者打平！");
			}
			System.out.println("还想再来吗？ a再来 n不来（其实你输入a都是不来）");
			String  k = sr.next();
			if(k.equals("a")){
				
			}
			else{
				break;
			}
			count++;
		}
		
		System.out.println("打平了"+p+"次"+"你赢了"+q+"次"+"电脑赢了"+r+"次");
		System.out.println("记录清单");
		for(int i1 = 0 ; i1<count;i1++){
			System.out.println(result[i1]);
		}
	}

}
