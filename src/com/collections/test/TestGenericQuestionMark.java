/**
 *  ���Է��͵��ʺ�
 *  
 *  �ʺ�   �ǻ�û��ָ��    ���ں����õ�ʱ����������ģ���T�͹̶��ˣ�����ʹ��ʱ��ֻ����T��
T��ָһ�����ͣ�?��ָ����
 */
package com.collections.test;

/**
 * @author    Ҷ����
 * @time      2015��2��23������10:54:00
 * @version   com.collections.testTestGenericQuestionMark V1.0
 */
import java.util.*;
public class TestGenericQuestionMark
{

	/**
	 * @param args
	 */
	public static void doTest(List<? extends Parent> list)
	//����Parend�Լ����м̳���Parent����
	{
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		List<Parent> list1 = new ArrayList<Parent>();
		List<Child> list2 = new ArrayList<Child>();
		doTest(list1);
		
/*		//�޷�ͨ��������󣡣�Ϊ��������Ҳ���� ���� ,�޸�doTest��ʵ��
		//����
		doTest(list2);*/
		doTest(list2); // ��Parent��Ϊ �� extends Parent����ͨ����
	}

}

class Parent
{
	//
}
class Child extends Parent
{
	
}