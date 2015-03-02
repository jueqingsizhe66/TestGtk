/**
 *  测试泛型的问号
 *  
 *  问号   是还没有指明    你在后续用到时可以是任意的，但T就固定了，后续使用时，只能用T。
T是指一种类型，?是指泛型
 */
package com.collections.test;

/**
 * @author    叶昭良
 * @time      2015年2月23日下午10:54:00
 * @version   com.collections.testTestGenericQuestionMark V1.0
 */
import java.util.*;
public class TestGenericQuestionMark
{

	/**
	 * @param args
	 */
	public static void doTest(List<? extends Parent> list)
	//包含Parend以及所有继承自Parent的类
	{
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		List<Parent> list1 = new ArrayList<Parent>();
		List<Child> list2 = new ArrayList<Child>();
		doTest(list1);
		
/*		//无法通过编译错误！！为了让子类也可以 于是 ,修改doTest的实现
		//即可
		doTest(list2);*/
		doTest(list2); // 从Parent改为 ？ extends Parent则是通过了
	}

}

class Parent
{
	//
}
class Child extends Parent
{
	
}
