package com.collections.test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author    Ҷ����
 * @time      2015��3��19������1:25:44
 * @version   com.collections.testTestCollections1 V1.0
 * ���ܣ�  ���� ���� ������  �������
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestCollections1
{
	public static void main(String[] args)
	{
		ArrayList<Integer> arl = new ArrayList<Integer>();
		arl.add(4);
		arl.add(9);
		arl.add(2);
		arl.add(10);
		arl.add(3);
		Collections.shuffle(arl);
		for(Integer temp:arl)
		{
			System.out.println(temp);
		}
	}
}
