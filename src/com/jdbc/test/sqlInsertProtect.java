/**
 * 
 */
package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author    叶昭良
 * @time      2015年2月17日下午10:06:14
 * @version   com.jdbc.testsqlInsertProtect V1.0
 */
public class sqlInsertProtect
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.out.println("数据库加载驱动失败"+e.getMessage());
			return; //驱动都失败了就不必要继续下去
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/study?se"
					+ "Unicode=true&characterEncoding=UTF8","root","root");
			System.out.println(conn.getClass().getName());
			ps = conn.prepareStatement("insert into T_person(Id,Name,Age,Gender) values"
					+"(?,?,?,?)");
					//+ "(6,'yezhao1',25,0)");
			ps.setInt(1, 7);
			ps.setString(2, "yezhao");
			ps.setInt(3, 25);
			ps.setBoolean(4, true);
			System.out.println(ps.getClass().getName());
			int i = ps.executeUpdate();
			System.out.println("有"+i+"条记录被影响");
//			System.out.println("连接成功");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败"+e.getMessage());
		}finally
		{
			AppleJdbc.closeQuietly(ps);
			AppleJdbc.closeQuietly(conn);
			
		}
	}
	
	static void closeQuietly(PreparedStatement ps)
	{
		if(ps!= null)
		{
			try
			{
				ps.close();
			}catch(SQLException e)
			{
			
			}
		}
	}
	
	static void closeQuietly(Connection conn)
	{
		if(conn!= null)
		{
			try
			{
				conn.close();
			}catch(SQLException e)
			{
			
			}
		}
	}

}
