/**
 * ���ͣ�
 */
package com.introspect.test;

import java.sql.SQLException;

/**
 * @author    Ҷ����
 * @time      2015��3��21������2:21:40
 * @version   com.introspect.testFifthExam V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class FifthExam
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Student s1 = new Student();
		//��������
/*		s1.setname("zhaoliang");
		s1.setnum("a001");*/
/*		s1.setname("xinran");
		s1.setnum("a002");*/
/*		s1.setname("liming");
		s1.setnum("a003");*/
		s1.setname("wanglei");
		s1.setnum("a004");
		try
		{
			//�������ݲ���
			//MyORM.insert(s1);
			//���Բ�ѯ��һ����
			System.out.println(MyORM.select(Student.class));
			
			//���Բ�ѯ�ڶ�����
			//s1= (Student)MyORM.select(Student.class,"a001");
			//s1= (Student)MyORM.select(Student.class,"a002");
			System.out.println(s1.toString());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}