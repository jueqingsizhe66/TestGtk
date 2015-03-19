/**
 *  ��дJDBCUtils����ģ��
 */
package com.jdbc.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



/**
 * @author    Ҷ����
 * @time      2015��2��19������8:48:59
 * @version   com.jdbc.testrewrite V1.0
 */
public class rewrite
{

	/**
	 * @param args
	 */
	private static final String mysqlDriver;
	private static final String connectDatabase;
	private static final String userName;
	private static final String Password;
	static
	{
		Properties prop  = new Properties();
		InputStream in = null;
		in = rewrite.class.getResourceAsStream("sql.properties");
		try
		{
			prop.load(in);
			mysqlDriver = prop.getProperty("mysqlDriver");
			connectDatabase = prop.getProperty("connectDatabase");
			userName = prop.getProperty("userName");
			Password = prop.getProperty("Password");
		} catch (IOException e)
		{
			//�ٴ���д��ʱ��  ע�� ���ڳԵ����쳣�Ĵ���
			//�ڶ�  �ھ�̬�������  ����mysql����
			//��̬�������������ã��������ÿؼ�����������
			
			//try....finally  ������throw SQLException�������ʹ�ã����ر���Ҫ������
			//��һ���������executeUpdate������executeQueryҲ��һ����
			//һ���׳��쳣 һ�������ס �������رյ���Ӧ������
			//try....finally ��try...catch....finally�����ʹ��
			
			//�ڲ�ѯʱ��ı�д ���Ƕ��׳��� ������رգ��� �ȵ���ѯ��ʱ�� ͳһ��ȥ�ر�
			//��Ϊ���ܴ��ڶദ��ѯ
			// TODO Auto-generated catch block
			throw new RuntimeException("�����ļ�����ʧ�ܣ�");
		}
		
		try
		{
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("����mysql�����쳣");
		}
	}
	
	public static Connection createConnection()
	{
		Connection ct = null;
		try
		{
			ct  = DriverManager.getConnection(connectDatabase);
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("���ݿⴴ������ʧ��");
		}
		return ct;
	}
	public static int updateExecute(String sql,Object... param)
	{
		Connection conn = null;
		conn =rewrite.createConnection();
		int row = 0;
		try
		{
			row = rewrite.updateExecute(conn, sql, param);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCUtils.closeQuietly(conn);
		}

		return row;
		
	}
	public static int updateExecute(Connection conn,String sql,Object... param) throws SQLException
	{
		PreparedStatement ps = null;
		int rows = 0;
		try
		{
			ps = conn.prepareStatement(sql);
			int i = 1;
			for(Object pa1:param)
			{
				ps.setObject(i, pa1);
				i++;
			}
			rows = ps.executeUpdate();
			
		}finally
		{
			rewrite.closeQuietly(ps);
		}
		return rows;
		
	}
	
	public static ResultSet executeQuery(String sql,Object... param) throws SQLException
	{
		Connection conn = null;
		conn = rewrite.createConnection();
		return rewrite.executeQuery(conn, sql, param);
	}
	public static ResultSet executeQuery(Connection conn,String sql,Object... param) throws SQLException
	{
		ResultSet rs  = null;
		PreparedStatement ps = conn.prepareStatement(sql);
		int  i = 0;
		for(Object pa1:param)
		{
			ps.setObject(i, pa1);
			i++;
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
				rewrite.closeQuietly(rs.getStatement().getConnection());
				rewrite.closeQuietly(rs.getStatement());
				rewrite.closeQuietly(rs);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void rollback(Connection  conn)
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