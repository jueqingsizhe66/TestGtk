/**
 * 请输入用户名字：
dfaf
请输入用户密码：
a' or 'a'='a
select count(*) c from t_users where userName='dfaf' and Password='a' or 'a'='a'
登陆成功

 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月17日下午9:16:23
 * @version   com.jdbc.testSqlInject V1.0
 */
import java.sql.*;
import java.util.Scanner;
public class SqlInject
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入用户名字：");
		String userName = sc.nextLine();
		System.out.println("请输入用户密码：");
		String password = sc.nextLine();
		
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
			String sql = "select count(*) c from t_users where userName='"+
					userName+"' and Password='"+password+"'";
			System.out.println(sql);
			// userName写成了Name
			// 单引号问题
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				int result = rs.getInt("c");
				if(result <= 0)
				{
					System.out.println("无法登陆");
				}else
				{
					System.out.println("登陆成功");
				}
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
