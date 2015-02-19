/**
 *  重写JDBCUtils代码模块
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
 * @author    叶昭良
 * @time      2015年2月19日下午8:48:59
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
			//再次书写的时候  注意 对于吃掉的异常的处理
			//第二  在静态代码段中  加载mysql驱动
			//静态代码块的两个作用：加载配置控件，加载驱动
			
			//try....finally  可以在throw SQLException的情况下使用，最后关闭需要的连接
			//进一步体会两个executeUpdate的区别（executeQuery也是一样）
			//一个抛出异常 一个则负责接住 并继续关闭的相应的连接
			//try....finally 和try...catch....finally的配合使用
			
			//在查询时候的编写 则是都抛出！ 不负责关闭！！ 等到查询的时候！ 统一的去关闭
			//因为可能存在多处查询
			// TODO Auto-generated catch block
			throw new RuntimeException("配置文件加载失败！");
		}
		
		try
		{
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("加载mysql驱动异常");
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
			throw new RuntimeException("数据库创建连接失败");
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
