/**
 * 
 */
package com.original.sort;

/**
 * @author    Ҷ����
 * @time      2015��2��27������1:35:36
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
	//ǰ���ƻ����  �ǵ����ƻ������������1
	//day��ʾ��õ������������   sday��ʾ���˶�����ʣ��һ������
		public static int peach(int day,int sday){
			if(day == sday){
				return 1;
			}else{
				return (peach(day+1,sday)+1)*2;
			}
		}
		
		
		public static void scan(){
			System.out.println("���Ⱥ�������õ������������Ŀ  �ͳ��˶�����ʣ��һ�����ӣ�");
			Scanner sr = new Scanner(System.in);
			int a = sr.nextInt();
			int b = sr.nextInt();
			if(a<b){
				System.out.println(MonkeyGetPeach.peach(a,b));
			}else{
				System.out.println("��һ����Ҫ�ȵڶ�������");
				MonkeyGetPeach.scan();
			}
		}

}