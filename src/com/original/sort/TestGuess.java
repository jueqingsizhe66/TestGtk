/**
 * 
 */
package com.original.sort;

/**
 * @author    Ҷ����
 * @time      2015��2��27������12:41:28
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
			System.out.println("��ѡ�� 0.Ϊ����1.Ϊ����");
	 		x = sr.nextInt();
		}
		System.out.println("tomӮ��" + tom.count + "��");
		tom.print();
	}

}

class Tom{
	//��¼���Գ��������
	public int r;
	public static int count = 0; 
//�ü��ϵĹ���洢����     ֻ��¼��Ӯ�Ĵ���  ��Ӯ�����
	public List<Integer> ll = new LinkedList<Integer>();


	public void get(){
	  Scanner sr = new Scanner(System.in);
	  System.out.println("��������Ҫ��ʲô��  0Ϊʯͷ 1Ϊ���� 2Ϊ��");
	  //��ȡ�����������
	  int x = sr.nextInt();
	  this.r = x;
		}
	public void print(){
		if(0 == this.r)
			{System.out.println("tom��ʯͷ");}
		else if(1 == this.r)
			{System.out.println("tom������");}
		else
			{System.out.println("tom����");}
		}
	public void caiquan(){
	   int a = (int)(Math.random()*3);
		if(0 == a && 0 == this.r){
		  System.out.println("���Գ�ʯͷ");
		  this.print();
		  System.out.println("һ��");
		}else if(0 == a && 1 == this.r){
			System.out.println("���Գ�ʯͷ");
			this.print();
			System.out.println("����Ӯ��");
		}else if(0 == a && 2 == this.r){
			System.out.println("���Գ�ʯͷ");
			this.print();
			System.out.println("��Ӯ�ˣ�");
			ll.add(0);
			this.count++;
		}else if(1 == a && 0 == this.r){
			System.out.println("���Գ�����");
			this.print();
			System.out.println("��Ӯ�ˣ�");
			ll.add(1);
			this.count++;
		}else if(1 == a && 1 == this.r){
			System.out.println("���Գ�����");
			this.print();
			System.out.println("һ��");
		}else if(1 == a && 2 == this.r){
			System.out.println("���Գ�����");
			this.print();
			System.out.println("����Ӯ��");
		}else if(2 == a && 0 == this.r){
			System.out.println("���Գ���");
			this.print();
			System.out.println("����Ӯ��");
		}else if(2 == a && 1 == this.r){
			System.out.println("���Գ���");
			this.print();
			System.out.println("��Ӯ�ˣ�");
			ll.add(2);
			this.count++;
		}else{
			System.out.println("���Գ���");
			this.print();
			System.out.println("һ��");
		}
	    }
}