/**
 * 
 */
package com.introspect.test;

import java.sql.SQLException;

/**
 * @author    Ҷ����
 * @time      2015��3��4������9:05:50
 * @version   com.introspect.testTestMyORM V1.0
 */
public class TestMyORM
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Person yangzhou = new Person();
		yangzhou.setName("yangzhou");
		yangzhou.setAge(20);
		
		try
		{
			//MyORM.insert(yangzhou);
			//MyORM.delete(Person.class, 2);
			
			
			/*Person p1 = (Person)MyORM.select(Person.class, 4);
			System.out.println(p1);*/
			MyORM.update(Person.class, 50, 4);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
