/**
 * 
 */
package com.jdbc.test;

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * @author    叶昭良
 * @time      2015年2月19日下午9:52:52
 *                          10:20：00完成
 * @version   com.jdbc.testrewrite2 V1.0
 */
public class rewrite2
{
	private static final String mysqlDriver ;
	private static final String connectDatabase;
	private static final String userName;
	private static final String Password;
	
	//第三次重写 意识到了  静态代码快不能加载 检查性的异常! 在try---catch的时候
	//一定得throw new RuntimeException 才不至于报错
	
	//居然忘记了先重写 Connection的创建！直接close,!有点错乱的感觉！
	//应该是按照逻辑的顺序进行编写，先是创建连接！ 最后在关闭
	//只不过当时是这样想的！ 其实这些关闭会一直反复的用到，
	//就想着先把他们给写上！也是符合逻辑！因为逻辑里面他们是反复被
	//调用的小插件
	
	//同时在创建connection再疑问是否抛出异常？ 最终抛出了？
	//为什么？  因为这个异常是自己可以处理的
	//连连接都拿不到 ！那就直接返回吧！肯定程序是有问题的
	
	//至于executeUpate executeQuery的异常都交由 调用者去处理他的异常
	static
	{
		//第一部分  加载配置文件
		Properties prop = new Properties();
		InputStream in = rewrite2.class.getResourceAsStream("sql.properties");
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
			throw new RuntimeException("配置文件加载异常！");
		}
		
		//加载驱动
		try
		{
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("mysql驱动加载异常");
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
			return null;
		}
		return conn;
	}
	
	public static int executeUpdate(String sql,Object... param) throws SQLException
	{
		Connection conn = null;
		conn = rewrite2.createConnection();
		try
		{
			return executeUpdate(conn, sql, param);
		} finally
		{
			rewrite2.closeQuietly(conn);
		}
		
	}
	public static int executeUpdate(Connection conn,String sql,Object... param) throws SQLException
	{
		PreparedStatement ps = null;
		int row = 1;
		try
		{
			ps = conn.prepareStatement(sql);
			for(Object pa1: param)
			{
				ps.setObject(row, pa1);
				row++;
			}
		}finally
		{
			rewrite2.closeQuietly(ps);
		}
		return ps.executeUpdate();
	}
	
	public static ResultSet executeQuery(String sql,Object... param) throws SQLException
	{
		Connection conn = rewrite2.createConnection();
		return executeQuery(conn,sql, param);
	}
	public static ResultSet executeQuery(Connection conn,String sql,Object... param) throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		int row = 1;

		for(Object pal:param)
		{
			ps.setObject(row, pal);
			row++;
		}
		//rs = executeQuery(conn, sql, param);
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
				rewrite2.closeQuietly(rs.getStatement().getConnection());
				rewrite2.closeQuietly(rs.getStatement());
				rewrite2.closeQuietly(rs);
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
