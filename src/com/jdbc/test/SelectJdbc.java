/**
 * 
 */
package com.jdbc.test;

import java.sql.*;



/**
 * @author    叶昭良
 * @time      2015年2月17日下午7:47:52
 * @version   com.jdbc.testSelectJdbc V1.0
 */
public class SelectJdbc
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.out.println("驱动失败");
			return;
		}	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/study?"
					+ "seUnicode=true&characterEncoding=UTF8","root","root");
			//utf8编码问题很重要！！！如果乱码大部分情况是数据库和
			//表有编码格式
			//库有编码格式
			ps = conn.prepareStatement("select * from T_person");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				boolean gender = rs.getBoolean("Gender");
				System.out.println("Id="+id+",姓名="+name+"年龄："+age+",性别："+(gender?"男":"女"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
