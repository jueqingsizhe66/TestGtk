/**
 * 
 */
package com.collections.test;

import java.util.HashMap;

/**
 * @author    叶昭良
 * @time      2015年3月3日下午6:54:32
 * @version   com.collections.testTestMapMap V1.0
 */
import java.util.*;
public class TestMapMap
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//Map<String,Map<String,Map<Integer,Double>>> stuTotal = new HashMap<String,Map<String,Map<Integer,Double>>>();
		Map<String, Integer> innerMap = new HashMap<String, Integer>();
		innerMap.put("innerKey", 2014);
		Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
		map.put("outerKey", innerMap);
		 
		Map<String, Integer> targetMap = map.get("outerKey");
		if(targetMap == innerMap){
		    System.out.println("You got the inner Map, and it is saved in targetMap!!!");
		}
		
		//原来嵌套集合数据结构是可以实现的
		Map<String,Map<String,Map<Integer,Double>>> outterMap = new HashMap<String, Map<String,Map<Integer,Double>>>();
		
		//==
	}

}


