/**
 * 
 */
package com.original.sort;

/**
 * @author    Ҷ����
 * @time      2015��2��27������12:54:06
 * @version   com.original.sortTestGuess2 V1.0
 */
import java.util.*;
public class TestGuess2
{

	/**
	 * @param args
	 */
	public static int count = 0;
	public static int p=0;//ƽ�Ĵ���
	public static int q=0;//��Ӯ�Ĵ���
	public static int r=0;//����Ӯ�Ĵ���
	public static String[] result=new String[50];
	public static void main(String[] args)
	{
/*		for(int i  =0 ; i < 10; i++)
		{
			System.out.println(Math.random());
		}*/
		// TODO Auto-generated method stub
		System.out.println("����͵��Բ�ȭ���Ļ����� 0 1 2 0����ʯͷ 1�������� 2������");
		Scanner sr = new Scanner(System.in);
		Random rand = new Random(System.currentTimeMillis());
		int i = 0;
		int j = 0;
		while(true){
			System.out.println("������ˣ���ʲô��(0ʯͷ,1����,2��)");
			i = sr.nextInt();//i�洢�����ֵ~   j�洢���Գ���ֵ
			//��β�����ȷ���������
		//	int j = (int)Math.random()*3%3;
			j = Math.abs(rand.nextInt()%3);
			if(i == j){
				p++;
				result[count] = "����ģ�"+i+" ���Գ���"+j+"\n���ߴ�ƽ\n";
				System.out.println("���ߴ�ƽ��");
			}else if((i-j) == -1 || (i-j)==2){
				q++;
				result[count] ="����ģ�"+i+" ���Գ���"+j+"\n��ϲ��Ӯ��\n";
				System.out.println("��ϲ��Ӯ�ˣ�");
			}else{
				r++;
				result[count] = "����ģ�"+i+" ���Գ���"+j+"\n����Ӯ��\n";
				System.out.println("���ߴ�ƽ��");
			}
			System.out.println("���������� a���� n��������ʵ������a���ǲ�����");
			String  k = sr.next();
			if(k.equals("a")){
				
			}
			else{
				break;
			}
			count++;
		}
		
		System.out.println("��ƽ��"+p+"��"+"��Ӯ��"+q+"��"+"����Ӯ��"+r+"��");
		System.out.println("��¼�嵥");
		for(int i1 = 0 ; i1<count;i1++){
			System.out.println(result[i1]);
		}
	}

}