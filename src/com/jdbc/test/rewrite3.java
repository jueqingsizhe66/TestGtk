/**
 * 
 */
package com.jdbc.test;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * @author    叶昭良
 * @time      2015年2月19日下午10:21:46
 *                10:40  结束      19min
 * @version   com.jdbc.testrewrite3 V1.0
 */
public class rewrite3
{
	private static final String mysqlDriver;
	private static final String connectDatabase;
	private static final String userName;
	private static final String Password;
	/**
	 *  书写第四遍的时候  遇到一个问题 在executeUpdate的时候  是不是改在
	 *  try...finally   的try块就应该返回了 而不是finally的后面
	 *  如果在后面 肯定是错误的！！！
	 */
	static
	{
		Properties prop = new Properties();
		InputStream in = rewrite3.class.getResourceAsStream("sql.properties");
		
		try
		{
			prop.load(in);
			mysqlDriver = prop.getProperty("mysqlDriver");
			connectDatabase = prop.getProperty("connectDatabase");
			userName = prop.getProperty("userName");
			Password = prop.getProperty("Password");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("配置文件加载异常");
		}
		
		try
		{
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("mysql 驱动加载异常");
		}
	}
	
	public static Connection createConnection()
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(connectDatabase);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("连接未创建成功");
			
		}
		return conn;
		
	}
	public static int executeUpdate(String sql,Object... param) throws SQLException
	{
		Connection conn = null;
		conn = rewrite3.createConnection();
		return rewrite3.executeUpdate(conn, sql, param);
	}
	public static int executeUpdate(Connection conn,String sql,Object... param) throws SQLException
	{
		PreparedStatement ps = null;		
		int row = 1;
		try
		{
			ps = conn.prepareStatement(sql);
			for(Object pa1 : param)
			{
				ps.setObject(row, pa1);
				row++;
			}
			return ps.executeUpdate();
		}finally
		{
			rewrite3.closeQuietly(ps);
		}
		
	}
	
	public static ResultSet executeQuery(String sql,Object... param) throws SQLException
	{
		Connection conn = rewrite3.createConnection();
		return rewrite3.executeQuery(conn,sql, param);
	}
	public static ResultSet executeQuery(Connection conn,String sql,Object... param) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		ps = conn.prepareStatement(sql);
		int row = 1;
		for(Object pa1:param)
		{
			ps.setObject(row,pa1);
			row++;
		}
		rs = ps.executeQuery();
		return rs;
	}
	
	public static void closeQuietly(Connection conn)
	{
		if(conn !=null)
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void closeQuietly(Statement stmt)
	{
		if(stmt !=null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void closeQuietly(ResultSet rs)
	{
		if(rs != null)
		{
			try
			{
				rs.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void closeAll(ResultSet rs)
	{
		if(rs !=null)
		{
			try
			{
				rewrite3.closeQuietly(rs.getStatement().getConnection());
				rewrite3.closeQuietly(rs.getStatement());
				rewrite3.closeAll(rs);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void rollback(Connection conn)
	{
		if(conn !=null)
		{
			try
			{
				conn.rollback();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
