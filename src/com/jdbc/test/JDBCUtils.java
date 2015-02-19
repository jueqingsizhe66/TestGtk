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
 * 			V2.0  改变配置文件变为为final，并把sql.properties文件提到
 * 				com.jdbc目录下 com/jdbc/test/sql.properties
 * 				测试通过
 *          V3.0 改变为静态代码段 加载驱动！ 并把加载配置文件
 *               也放在静态代码段中！仅仅加载一次即可！而不需要
 *               一直加载
 *          V4.0 修正了没有close的问题！！防止一直连着。 明白了吃异常的过程
 *                在类库中不知道怎么处理！直接抛出异常即可！让调用者自己去处理！
 *                咱们提供方法，不提供异常的处理！打印异常不叫做处理！而叫做“吃异常”
 *          V5.0  不吃异常的方式就是 throw new RuntimeException ，静态代码段
 *          	无法throw checkedException检查异常
 *                没想到经过这样的过程封装比我原先的好看多了！而且逻辑更加清晰
 *          V6.0 有没有理解清楚，我们实际用的较多executeUpdate和executeQuery的无conn
 *               的形式，而带conn只是一个中间过程！ 并且无conn的会调用有conn的形式！
 *              再次修改！ 注意观看executeUpdate的实现
 *          V7.0  再次改进！不需要把executeQuery的connection关闭
 *                由于PreparedStatement 来自Statement
 *                rs.getStatement() 表示返回一个statement，而不会关闭PreparedStatement
 *                常见了一个closeAll(),这也是为什么 暂时不关闭查询连接的原因，同时不关闭
 *                方便进行多次查询，反复查询
 *                
 *           V8.0  cachedRowSet 是一个缓冲的mysql字符集，连接中断！把所有数据缓冲到客户端！
 *                 不需要再次连接，所以关闭Connection也是可以
 *                 但是这个栈内存  暂时不使用 
 *                 
 *=思路总结：
1：利用配置表，载入mysql和数据库信息
2：利用静态代码段载入驱动（只需要一次）
3： 编写 带connection的executeUpdate和executeResult,并且在executeResult不要关闭任何信息，待统一关闭
4： 编写 不带Connection的executeUpdate和executeResult,内部调用带Connction版本的而寒暑
5： 编写closeQuietly函数  和closeAll函数
 *                
 */
public class JDBCUtils
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
		InputStream is = null; 
		//InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("sql.properties");
		Properties ps  = new Properties();
		//Connection c1 = null;
		try
		{
			//is = JDBCUtils.class.getClassLoader().getResourceAsStream(""
			//		+ "com/jdbc/test/sql.properties");
			is = JDBCUtils.class.getResourceAsStream("sql.properties");
			//这种情况省略宝和getClassLoader()
			ps.load(is);
			mysqlDriver = ps.getProperty("mysqlDriver");
//			System.out.println(mysqlDriver);
			connectDatabase = ps.getProperty("connectDatabase");
	//			System.out.println(connectDatabase);
			userName = ps.getProperty("userName");
	//			System.out.println(userName);
			Password = ps.getProperty("Password");
	//			System.out.println(Password);
			//仅仅在静态代码段中加载一次驱动即可（在运行中）
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			//System.out.println("配置文件加载失败"+e.getMessage());
			throw new RuntimeException("配置文件sql.properties加载失败"+e.getMessage());
			//return null;
		}
		try
		{
			Class.forName(mysqlDriver); //仅仅在程序运行中加载一次驱动即可！！！
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
//			System.out.println("驱动加载失败"+e.getMessage());
			throw new RuntimeException("驱动加载失败"+e.getMessage());
		}

	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
			
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
/*			String sql = "insert into t_person(Id,Name,Age,Gender) values(?,?,?,?)"; //年龄不能过大
			JDBCUtils.executeUpdate(sql, 13,"lizhan",17,1);*/
		
		/**
		 *  测试结果： 单个连接 插入100条数据耗时3767 
		 */
/*			long startTime = System.currentTimeMillis();
			String sql = "insert into t_person(Id,Name,Age,Gender) values(?,?,?,?)";
			Connection conn = null;
			try
			{
				conn = JDBCUtils.createConnection();
				//原先有数据！并且主键不能重复 所以只好从18开始
				for(int i = 15; i < 115; i++)
				{
					JDBCUtils.executeUpdate(conn,sql, i,"SpringDay",17,1);
				}
				System.out.println("单个连接 插入100条数据耗时"+(System.currentTimeMillis()
						-startTime));

			}catch(SQLException e)
			{
				System.out.println("数据库连接异常");
			}finally
			{
				JDBCUtils.closeQuietly(conn);
			}*/
		/**
		 * 一个连接 插入一条数据 100条后耗时7850  
		 *   可见这种方式还是相对较慢的！！！！
		 *   总结   在多次插入数据库中！最好是一次连接，不要关闭，插入完在关闭
		 */
/*		long startTime = System.currentTimeMillis();
		String sql = "insert into t_person(Id,Name,Age,Gender) values(?,?,?,?)";
		try
		{
			for(int i = 116; i < 216; i++)
			{
				JDBCUtils.executeUpdate(sql, i,"SpringDay",17,1);
			}
			System.out.println("一个连接 插入一条数据 100条后耗时"+(System.currentTimeMillis()
					-startTime));
		}catch(SQLException e)
		{
			System.out.println("连接失败！主键有问题"+e.getMessage());
		}*/
		//原先有数据！并且主键不能重复 所以只好从18开始
		
		ResultSet rs = null;
		String sql = "select * from t_person";
		try
		{
			rs = executeQuery(sql);
			while(rs.next())
			{
				String Name= rs.getString("Name");
				System.out.println(Name);
			}
		}catch(SQLException e)
		{
			System.out.println("居然报错");
		}finally
		{
			JDBCUtils.closeAll(rs);
		}
	
/*		try
		{
			ResultSet rs = JDBCUtils.executeQuery( "select * from t_person", null);
			int i = 0;
			while(rs.next())
			{
				i++;
			}
			System.out.println("总的返回了"+i+"条记录");
		}catch(SQLException e)
		{
			System.out.println("juran");
		}
		*/
			
	}
	
	/**
	 * 创建数据库的连接
	 * @return   返回mysql的连接
	 */
	public static  Connection createConnection() 
	{
		Connection c1  = null;
		try
		{
			
		  c1 = DriverManager.getConnection(connectDatabase,userName,Password);
			System.out.println("连接成功");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("数据库连接创建失败"+e.getMessage());
			return null;
		}
		return c1;
	}
	/**
	 * 
	 * @param sql        sql的update,delete,insert等修改数据库的语句
	 * @param parameter  参数化的update,delete,insert等的设置 
	 * @return           返回被影响的函数
	 * @throws SQLException   抛出SQLException由用户自己去特殊处理
	 */
	public static int executeUpdate(String sql,Object... parameter) throws SQLException
	{
		Connection conn = null;
		try
		{
			conn = JDBCUtils.createConnection();
			return executeUpdate(conn, sql, parameter);
		}finally
		{
			JDBCUtils.closeQuietly(conn);
		}
	}
	/**
	 * @param conn       数据库的连接
	 * @param sql        sql的update,delete,insert等修改数据库的语句
	 * @param parameter  参数化的update,delete,insert等的设置 
	 * @return           返回被影响的函数
	 * @throws SQLException   抛出SQLException由用户自己去特殊处理
	 */
	public static int executeUpdate(Connection conn,String sql,Object... parameter) throws SQLException
	{
		PreparedStatement ps = null;
		int rows = 0;
		try
		{
			ps = conn.prepareStatement(sql);
			int i = 1;
			if(parameter != null)
			{
				for(Object pa1:parameter)
				{
					ps.setObject(i, pa1);
					i++;
				}
			}
			rows = ps.executeUpdate();
		}finally
		{
			JDBCUtils.closeQuietly(ps);
		}
		
		System.out.println("有"+rows+"条记录被影响");
		return rows;
	}
	/**
	 * 
	 * @param sql        sql的select不修改数据库的语句
	 * @param parameter  参数化的查询，参数集的设置 
	 * @return           返回查询的数据集
	 * @throws SQLException   抛出SQLException由用户自己去特殊处理
	 */
	public static ResultSet executeQuery(String sql,Object... parameter) throws SQLException
	{
		Connection conn = null;
		
		conn = JDBCUtils.createConnection();
		return executeQuery(conn,sql, parameter);
		/*finally
		{
			JDBCUtils.closeQuietly(conn);
		}*/
		
	}
	/**
	 * @param conn       数据库连接
	 * @param sql        sql的select不修改数据库的语句
	 * @param parameter  参数化的查询，参数集的设置 
	 * @return           返回查询的数据集
	 * @throws SQLException   抛出SQLException由用户自己去特殊处理
	 */
	public static ResultSet executeQuery(Connection conn,String sql,Object... parameter) throws SQLException
	{
		PreparedStatement ps = null;
		ps = conn.prepareStatement(sql);
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
	/**
	 * 从resultSet中关闭所有的连接
	 * @param rs
	 */
	static void closeAll(ResultSet rs)
	{
		if(rs == null)
		{
			return;
		}
		try
		{
			JDBCUtils.closeQuietly(rs.getStatement().getConnection());
			JDBCUtils.closeQuietly(rs.getStatement());
			JDBCUtils.closeAll(rs);
		}catch(SQLException e)
		{
			
		}
	}
	/**
	 *  关闭PreparedStatment连接
	 * @param ps   PreparedStatment对象
	 */
	//static void closeQuietly(PreparedStatement ps)
	static void closeQuietly(Statement ps)
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
	/**
	 *  关闭Connection连接
	 * @param connn   Connection对象
	 */
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
	/**
	 *  关闭ResultSet连接
	 * @param rs   ResultSet对象
	 */
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
	/**
	 *   封装批处理提交会使用到的rollback函数
	 * @param conn  Connection 连接类型
	 */
	static void rollback(Connection conn)
	{
		if(conn!= null)
		{
			try
			{
				conn.rollback();
			}catch(SQLException e)
			{
			
			}
		}
	}
}
