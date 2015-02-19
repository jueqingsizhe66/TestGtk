/**
 * 
 */
package com.jdbc.test;

//必须是把 window--> preferenece-->java-->compilor--->error|warning--->Deprecated API都给构成
//忽略 才能识别这个类！！！！未提供源文件的实现

import java.sql.*;

import javax.sql.rowset.*;
/**
 * @author    叶昭良
 * @time      2015年2月19日下午12:33:47
 * @version   com.jdbc.testCachedRowSetTest V1.0
 */
public class CachedRowSetTest
{

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
	private static final String DATABASE_URL="jdbc:mysql://localhost/study?"
					+ "seUnicode=true&characterEncoding=UTF8";
	private static final String DATABASE_USER="root";
	private static final String DATABASE_PASSWORD="root";
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		/*CachedRowSet rs;
	    String ROWSET_IMPL_CLASS = "com.sun.rowset.CachedRowSetImpl";
	    
	    
	    Class c;
		try
		{
			c = Class.forName(ROWSET_IMPL_CLASS);
			rs = (CachedRowSet) c.newInstance();

		    rs.setUrl("jdbc:postgresql:study");
		    rs.setUsername("root");
		    rs.setPassword("root");

		    rs.setCommand("select * from b1 where Age like ?");
		    //rs.setString(1, "I%");
		    rs.setInt(1,10);
		    rs.execute();

		    while (rs.next()) {
		      if (rs.getInt("Id") == 0) {
		        rs.setString(1, "newString");
		        rs.updateRow(); // Normal JDBC

		        rs.acceptChanges();
		      }
		    }
		    rs.close();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		try{
			String sql="select * from t_users";
			Class.forName(DRIVER_CLASS);
			Connection conn=DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
			conn.setAutoCommit(false);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			//使用RowSetProvider创建RowSetFactory
			RowSetFactory factory =RowSetProvider.newFactory();
			//创建默认的CachedRowSet实例
			CachedRowSet cache=factory.createCachedRowSet();
			//用rs填充CachedRowSet
			cache.populate(rs);
			
			//关闭资源
			//rs.close();
			//psmt.close();
			//conn.close();
			
			cache.afterLast();
			while(cache.previous()){
				System.out.println(cache.getInt(1)+"\t"+cache.getString(2)+"\t"+cache.getString(3));
				if(cache.getInt("Id")==1){
					cache.updateString("userName", "honghonghuohuo");
					cache.updateRow();
					//把对RowSet所做的修改同步到底层数据库
						cache.acceptChanges(conn);
					}
				}
	
	}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

