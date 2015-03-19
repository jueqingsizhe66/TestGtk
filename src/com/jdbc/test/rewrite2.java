/**
 * 
 */
package com.jdbc.test;

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * @author    Ҷ����
 * @time      2015��2��19������9:52:52
 *                          10:20��00���
 * @version   com.jdbc.testrewrite2 V1.0
 */
public class rewrite2
{
	private static final String mysqlDriver ;
	private static final String connectDatabase;
	private static final String userName;
	private static final String Password;
	
	//��������д ��ʶ����  ��̬����첻�ܼ��� ����Ե��쳣! ��try---catch��ʱ��
	//һ����throw new RuntimeException �Ų����ڱ���
	
	//��Ȼ����������д Connection�Ĵ�����ֱ��close,!�е���ҵĸо���
	//Ӧ���ǰ����߼���˳����б�д�����Ǵ������ӣ� ����ڹر�
	//ֻ������ʱ��������ģ� ��ʵ��Щ�رջ�һֱ�������õ���
	//�������Ȱ����Ǹ�д�ϣ�Ҳ�Ƿ����߼�����Ϊ�߼����������Ƿ�����
	//���õ�С���
	
	//ͬʱ�ڴ���connection�������Ƿ��׳��쳣�� �����׳��ˣ�
	//Ϊʲô��  ��Ϊ����쳣���Լ����Դ�����
	//�����Ӷ��ò��� ���Ǿ�ֱ�ӷ��ذɣ��϶��������������
	
	//����executeUpate executeQuery���쳣������ ������ȥ���������쳣
	static
	{
		//��һ����  ���������ļ�
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
			throw new RuntimeException("�����ļ������쳣��");
		}
		
		//��������
		try
		{
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("mysql���������쳣");
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