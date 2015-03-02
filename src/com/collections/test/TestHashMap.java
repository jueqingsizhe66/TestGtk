/**
 * 
 */
package com.collections.test;

/**
 * @author    叶昭良
 * @time      2015年2月25日上午1:38:30
 * @version   com.collections.testTestHashMap V1.0
 */
import java.util.*;
public class TestHashMap
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("1", "zhangsan");
		map.put("2", "lisi");
		map.put("3", "cbd");
		
		System.out.println(map.get("1"));
		
		Set keySet = map.keySet();
		
		Iterator it = keySet.iterator();
		while(it.hasNext())
		{
			Object o = it.next();
			System.out.println(map.get(o)+":"+o);
		}
		
		System.out.println("The Second method to get Value---");
		Collection value = map.values();
		Iterator it1 =value.iterator();
		while(it1.hasNext())
		{
			System.out.println(it1.next());
		}
		
		map.remove("1");
		System.out.println("The Third Method to get value----");
		Set keyset1 = map.entrySet();
		Iterator itp = keyset1.iterator();
		while(itp.hasNext())
		{
			Map.Entry o  = (Map.Entry)itp.next();
			System.out.println(o+"，其中 键位："+o.getKey()+",值为："+o.getValue());
		}
	}

}
