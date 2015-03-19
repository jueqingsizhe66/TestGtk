/**
 * 
 */
package com.jdbc.test;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * @author    Ҷ����
 * @time      2015��2��19������10:21:46
 *                10:40  ����      19min
 * @version   com.jdbc.testrewrite3 V1.0
 */
public class rewrite3
{
	private static final String mysqlDriver;
	private static final String connectDatabase;
	private static final String userName;
	private static final String Password;
	/**
	 *  ��д���ı��ʱ��  ����һ������ ��executeUpdate��ʱ��  �ǲ��Ǹ���
	 *  try...finally   ��try���Ӧ�÷����� ������finally�ĺ���
	 *  ����ں��� �϶��Ǵ���ģ�����
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
			throw new RuntimeException("�����ļ������쳣");
		}
		
		try
		{
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("mysql ���������쳣");
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
			throw new RuntimeException("����δ�����ɹ�");
			
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