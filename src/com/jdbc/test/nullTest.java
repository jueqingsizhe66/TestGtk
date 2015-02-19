/**
 * 
 */
package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.sql.rowset.CachedRowSet;

//import com.mysql.jdbc.CachedResultSetMetaData;


/**
 * @author    叶昭良
 * @time      2015年2月17日下午10:14:24
 * @version   com.jdbc.testnullTest V1.0
 */
public class nullTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		
		//CachedRowSet crs = new
		// TODO Auto-generated method stub
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.out.println("数据库驱动加载失败"+e.getMessage());
			return;
		}
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/study?"
					+ "seUnicode=true&characterEncoding=UTF8","root","root");
			String sql = "select * from b1";
			System.out.println(sql);
			// userName写成了Name
			// 单引号问题
			ps = conn.prepareStatement(sql);
			//从1开始的
			/*ps.setString(1, userName);
			ps.setString(2, password);*/
			rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String Hobbies = rs.getString("Hobbies");
				//int age = rs.getInt("Age");
				Integer age = (Integer)rs.getObject("Age");
				//因为int不能为null所以只能为0  因为boolean也不能为null，所以只能为默认值false
				//boolean gender = rs.getBoolean("Gender");
				if(age == null)
				{
					System.out.println("年龄不知道");
				}else
				{
					int age1 =age;
					System.out.println("年龄为"+age1);
				}
				
				Boolean bGender = (Boolean)rs.getObject("Gender");
				if(bGender==null)
				{
					System.out.println("性别不知道");
	
				}else
				{
					boolean gender1 = bGender;
					System.out.println("性别是"+(gender1?"男":"女"));
				}
				//System.out.println("Id="+id+",业余爱好="+Hobbies+"年龄："+age+",性别："+(gender?"男":"女"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
