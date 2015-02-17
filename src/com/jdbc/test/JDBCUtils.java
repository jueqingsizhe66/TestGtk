/**
 * 
 */
package com.jdbc.test;

import java.util.Properties;
import java.io.*;
import java.sql.*;
/**
 * @author    Ҷ����
 * @time      2015��2��17������11:31:53
 * @version   com.jdbc.testJDBCUtils V1.0
 */
public class JDBCUtils
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		try
		{
			
	//��ѯ�Ĳ��Է�Ϊ�����׶�    �޲� ������   �вε�һ��
			//����1   success
			/**
			 *  mysql> select * from t_person;
			 *  6 rows in set (0.35 sec)
			 */
			/*
			Connection conn = JDBCUtils.createConnection() ;
			ResultSet rs = JDBCUtils.executeQuery(conn, "select * from t_person", null);
			int i = 0;
			while(rs.next())
			{
				i++;
			}
			System.out.println("�ܵķ�����"+i+"����¼");
			*/
			//����2   success
			/**
			 *  mysql> select * from t_person;
			 *  6 rows in set (0.35 sec)
			 */
			/*
			ResultSet rs = JDBCUtils.executeQuery( "select * from t_person", null);
			int i = 0;
			while(rs.next())
			{
				i++;
			}
			System.out.println("�ܵķ�����"+i+"����¼");
			*/
			
			//����3    success
			/* mysql> select * from t_person where name='xinran'
			 * 3 rows in set (0.00 sec)
			 */
/*			ResultSet rs = JDBCUtils.executeQuery("select * from t_person where name = ?", "xinran");
			int i = 0;
			while(rs.next())
			{
				i++;
			}
			System.out.println("�ܵķ�����"+i+"����¼");*/
			
	//����׶εĲ���   ���Ʋ�ѯ��Ϊ�����׶�    �޲� ������   �вε�һ��
			/**
			 * ���Գɹ��� //1
			 */
			/*Connection conn = JDBCUtils.createConnection() ;
			int i = JDBCUtils.executeUpdate(conn, "insert into t_person(Id,Name,Age,Gender) values(8,'lizi',82,1)", null);

			//System.out.println("�ܵķ�����"+i+"����¼");

            */	
			/**
			 * ���Գɹ��� //2 û�뵽ֻҪ������� �������һ�β���
			 */
			/*String sql = "insert into t_person(Id,Name,Age,Gender) values(9,'�����',12,1)"; //���䲻�ܹ���
			JDBCUtils.executeUpdate(sql, null);*/
			
			/**
			 *  ���Գɹ���  //3  ���вε���ʽҲ�ǳɹ���
			 *  mysql> select * from t_person;
			+----+---------+-----+--------+
			| Id | Name    | Age | Gender |
			+----+---------+-----+--------+
			|  1 | yezhao  |  24 |      1 |
			|  3 | xinran  |  32 |      0 |
			|  4 | xinran  |  32 |      4 |
			|  5 | xinran  |  32 |      4 |
			|  6 | yezhao1 |  25 |      0 |
			|  7 | yezhao  |  25 |      1 |
			|  8 | lizi    |  82 |      1 |
			|  9 | �����  |  12 |      1 |
			| 10 | ����    |   3 |      0 |
			+----+---------+-----+--------+
			9 rows in set (0.00 sec)
			 */
			String sql = "insert into t_person(Id,Name,Age,Gender) values(?,?,?,?)"; //���䲻�ܹ���
			JDBCUtils.executeUpdate(sql, 10,"����",3,0);
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static  Connection createConnection() throws SQLException
	{
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("sql.properties");
		Properties ps  = new Properties();
		Connection c1 = null;
		try
		{
			ps.load(is);
			String mysqlDriver = ps.getProperty("mysqlDriver");
//			System.out.println(mysqlDriver);
			
			String connectDatabase = ps.getProperty("connectDatabase");
//			System.out.println(connectDatabase);
			
			String userName = ps.getProperty("userName");
//			System.out.println(userName);
			
			String Password = ps.getProperty("Password");
//			System.out.println(Password);
			
			Class.forName(mysqlDriver);
			
			c1 = DriverManager.getConnection(connectDatabase,userName,Password);
			System.out.println("���ӳɹ�");
		} catch (IOException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("���ݿ���������ʧ��"+e.getMessage());
			return null;
		}
		return c1;
	}

	public static int executeUpdate(String sql,Object... parameter) throws SQLException
	{
		Connection conn = JDBCUtils.createConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		int i = 1;
		if(parameter != null)
		{
			for(Object pa1:parameter)
			{
				ps.setObject(i, pa1);
				i++;
			}
		}
		int rows = ps.executeUpdate();
		System.out.println("��"+rows+"����¼��Ӱ��");
		return rows;
	}
	
	public static int executeUpdate(Connection conn,String sql,Object... parameter) throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement(sql);
		int i = 1;
		if(parameter != null)
		{
			for(Object pa1:parameter)
			{
				ps.setObject(i, pa1);
				i++;
			}
		}
		int rows = ps.executeUpdate();
		System.out.println("��"+rows+"����¼��Ӱ��");
		return rows;
	}
	
	public static ResultSet executeQuery(String sql,Object... parameter) throws SQLException
	{
		Connection conn = JDBCUtils.createConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		int i =1;
		if(parameter != null)
		{
			for(Object pa1:parameter)
			{
				ps.setObject(i, pa1);
				i++;
			}
		}
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet executeQuery(Connection conn,String sql,Object... parameter) throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement(sql);
		int i =1;
		if(parameter != null)
		{
			for(Object pa1:parameter)
			{
				ps.setObject(i, pa1);
				i++;
			}
		}
		ResultSet rs = ps.executeQuery();
		return rs;
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
	static void closeQuietly(ResultSet rs)
	{
		if(rs!= null)
		{
			try
			{
				rs.close();
			}catch(SQLException e)
			{
			
			}
		}
	}
}