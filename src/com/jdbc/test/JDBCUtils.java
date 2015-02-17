/**
 * 
 */
package com.jdbc.test;

import java.util.Properties;
import java.io.*;
import java.sql.*;
/**
 * @author    叶昭良
 * @time      2015年2月17日下午11:31:53
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
			
	//查询的测试分为三个阶段    无参 的两个   有参的一个
			//测试1   success
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
			System.out.println("总的返回了"+i+"条记录");
			*/
			//测试2   success
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
			System.out.println("总的返回了"+i+"条记录");
			*/
			
			//测试3    success
			/* mysql> select * from t_person where name='xinran'
			 * 3 rows in set (0.00 sec)
			 */
/*			ResultSet rs = JDBCUtils.executeQuery("select * from t_person where name = ?", "xinran");
			int i = 0;
			while(rs.next())
			{
				i++;
			}
			System.out.println("总的返回了"+i+"条记录");*/
			
	//插入阶段的测试   类似查询分为三个阶段    无参 的两个   有参的一个
			/**
			 * 测试成功！ //1
			 */
			/*Connection conn = JDBCUtils.createConnection() ;
			int i = JDBCUtils.executeUpdate(conn, "insert into t_person(Id,Name,Age,Gender) values(8,'lizi',82,1)", null);

			//System.out.println("总的返回了"+i+"条记录");

            */	
			/**
			 * 测试成功！ //2 没想到只要两行语句 则进行了一次插入
			 */
			/*String sql = "insert into t_person(Id,Name,Age,Gender) values(9,'孙悟空',12,1)"; //年龄不能过大
			JDBCUtils.executeUpdate(sql, null);*/
			
			/**
			 *  测试成功！  //3  连有参的形式也是成功了
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
			|  9 | 孙悟空  |  12 |      1 |
			| 10 | 蝴蝶    |   3 |      0 |
			+----+---------+-----+--------+
			9 rows in set (0.00 sec)
			 */
			String sql = "insert into t_person(Id,Name,Age,Gender) values(?,?,?,?)"; //年龄不能过大
			JDBCUtils.executeUpdate(sql, 10,"蝴蝶",3,0);
			
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
			System.out.println("连接成功");
		} catch (IOException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("数据库驱动加载失败"+e.getMessage());
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
		System.out.println("有"+rows+"条记录被影响");
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
		System.out.println("有"+rows+"条记录被影响");
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
