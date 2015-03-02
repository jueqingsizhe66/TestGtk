/**
 * 
 */
package com.collections.test;

import java.util.ArrayList;

/**
 * @author    叶昭良
 * @time      2015年2月25日上午1:12:42
 * @version   com.collections.testTestGeneric V1.0
 */
import java.util.*;
public class TestGeneric
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//泛型的向后兼容
		//l1 只是回去找List的方法   
		List l1 =  new ArrayList<String>();
		l1.add("abc");
		l1.add(3);
		
/*		List<String> l2 = new ArrayList<String>();
		l2.add("abc");
		l2.add(3);*/
		
		//泛型的不协变
//		List<String> l3 = new ArrayList<Integer>();
	}

}
