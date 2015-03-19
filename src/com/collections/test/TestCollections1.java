package com.collections.test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author    叶昭良
 * @time      2015年3月19日下午1:25:44
 * @version   com.collections.testTestCollections1 V1.0
 * 功能：  测试 骰子 的乱序  随机排序
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
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
