/**
 *  û���� vector������    ����vector��������ǻ�100%������
 *  ��ȫ������list������ʵ�����vector����ȻArrayList
 */
package com.collections.test;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author    Ҷ����
 * @time      2015��2��23������6:15:53
 * @version   com.collections.testTestEnumeration V1.0
 */
public class TestEnumeration
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Vector<String> v1 =  new Vector<String>();
		v1.add("abc0");
		v1.add("abc1");
		v1.add("abc2");
		
		//����jdk1.0���ֵķ���
		Enumeration<String> e  = v1.elements(); //����ֶ�Ӧ�ķ�����
		                                   //С����
		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
		}
		
		Iterator<String> it = v1.iterator();
		while(it.hasNext())
		{
			System.out.println();
		}
	}

}