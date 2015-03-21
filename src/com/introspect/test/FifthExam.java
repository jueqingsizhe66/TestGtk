/**
 * 解释：
 */
package com.introspect.test;

import java.sql.SQLException;

/**
 * @author    叶昭良
 * @time      2015年3月21日下午2:21:40
 * @version   com.introspect.testFifthExam V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class FifthExam
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Student s1 = new Student();
		//插入数据
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
			//插入数据部分
			//MyORM.insert(s1);
			//测试查询第一部分
			System.out.println(MyORM.select(Student.class));
			
			//测试查询第二部分
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
