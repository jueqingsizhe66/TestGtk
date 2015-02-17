/**
 *   第一次连接数据
 */
package com.jdbc.test;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;



/**
 * @author    叶昭良
 * @time      2015年2月17日上午1:52:36
 * @version   com.jdbc.testAppleJdbc V1.0
 */
public class AppleJdbc 
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
					+ "(6,'yezhao1',25,0)");
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
