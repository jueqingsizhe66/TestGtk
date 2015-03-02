/**
 * 
 */
package com.collections.test;

/**
 * @author    叶昭良
 * @time      2015年3月2日下午11:43:45
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
		//为什么ArrayList的打印是这样子？
		Collections.sort(pileOfApple);
		for(String temp:pileOfApple)
		{
			System.out.println(temp);
		}
		//已排好序 才可以这样做 ! 否则不可以进行二分查找  Arrays类似的用法
		System.out.println(Collections.binarySearch(pileOfApple, "gd"));
		
		
		int[] pileOfPear = {3,4,7,5,8,5,3,6};
		//把它当做list，但是不能增加 和减少，只可以遍历
		//有一个地方忘记了！！  类似于增强for只能遍历，不可以进行增加和删除的操作
		List list = Arrays.asList(pileOfPear);
		for(int temp: pileOfPear)
		{
			System.out.println(temp);
		}
		
	}

}
