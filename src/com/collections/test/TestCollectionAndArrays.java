/**
 * 
 */
package com.collections.test;

/**
 * @author    Ҷ����
 * @time      2015��3��2������11:43:45
 * @version   com.collections.testTestCollectionAndArrays V1.0
 */
import java.util.*;
public class TestCollectionAndArrays
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//List<String> pileOfApple = new ArrayList<String>();
		List<String> pileOfApple = new ArrayList();
		pileOfApple.add("add");
		pileOfApple.add("gd");
		pileOfApple.add("xsx");
		pileOfApple.add("bbc");
		pileOfApple.add("dee");
		
		System.out.println(pileOfApple);
		//ΪʲôArrayList�Ĵ�ӡ�������ӣ�
		Collections.sort(pileOfApple);
		for(String temp:pileOfApple)
		{
			System.out.println(temp);
		}
		//���ź��� �ſ��������� ! ���򲻿��Խ��ж��ֲ���  Arrays���Ƶ��÷�
		System.out.println(Collections.binarySearch(pileOfApple, "gd"));
		
		
		int[] pileOfPear = {3,4,7,5,8,5,3,6};
		//��������list�����ǲ������� �ͼ��٣�ֻ���Ա���
		//��һ���ط������ˣ���  ��������ǿforֻ�ܱ����������Խ������Ӻ�ɾ���Ĳ���
		List list = Arrays.asList(pileOfPear);
		for(int temp: pileOfPear)
		{
			System.out.println(temp);
		}
		
	}

}