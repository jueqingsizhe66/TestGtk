/**
 * 
 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月19日下午10:41:37
 *                        10:55     14min
 * @version   com.jdbc.testrewrite4 V1.0
 */
import java.util.*;
import java.sql.*;
import java.io.*;
public class rewrite4
{
	private final static String mysqlDriver;
	private final static String connectDatabase;
	private final static String userName;
	private final static String Password;
	
	static
	{
		Properties prop = new Properties();
		InputStream in = rewrite4.class.getResourceAsStream("sql.properties");
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
			throw new RuntimeException("配置文件加载失败");
		}
		try
		{
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("mysql驱动加载失败");
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
			throw new RuntimeException("未能正确创建连接");
		}
		return conn;
		
	}
	public static int executeUpdate(String sql,Object... param) throws SQLException
	{
		Connection conn = rewrite4.createConnection();
		return rewrite4.executeUpdate(conn,sql, param);
	}
	public static int executeUpdate(Connection conn,String sql,Object... param) throws SQLException
	{
		PreparedStatement ps = null;
		int row = 1;
		try
		{
			ps = conn.prepareStatement(sql);
			for(Object par:param)
			{
				ps.setObject(row, par);
				row++;
			}		
			return ps.executeUpdate();
		}finally
		{
			rewrite4.closeQuietly(ps);
		}

	}
	
	public static ResultSet executeQuery(String sql,Object... param) throws SQLException
	{
		Connection conn = rewrite4.createConnection();
		return rewrite4.executeQuery(conn, sql, param);
	}
	public static ResultSet executeQuery(Connection conn,String sql,Object... param) throws SQLException
	{
		ResultSet  rs = null;
		PreparedStatement ps = null;
		
		ps = conn.prepareStatement(sql);
		int row =1;
		for(Object par:param)
		{
			ps.setObject(row, par);
			row++;
		}
		rs = ps.executeQuery();
		return rs;
		
	}
	public static void closeQuietly(Connection conn)
	{
		if(conn != null)
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
		if(stmt != null)
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
	
	public static void rollback(Connection conn)
	{
		if(conn != null)
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
