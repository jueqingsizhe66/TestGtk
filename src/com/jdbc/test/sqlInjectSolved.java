/**
 * 请输入用户名字：
yzl
请输入用户密码：
457866
select count(*) c from t_users where userName= ? and password = ?
登陆成功

请输入用户名字：
yzl
请输入用户密码：
a' or 'a'='a
select count(*) c from t_users where userName= ? and password = ?
登陆成功

还是没有解决啊！

请输入用户名字：
yzl
请输入用户密码：
a' or 'a'='a
select count(*) c from t_users where userName= ? and password = ?
无法登陆
原来是因为if(result <= 0) 写成了if(result < 0)
 */
package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author    叶昭良
 * @time      2015年2月17日下午9:53:53
 * @version   com.jdbc.testsqlInjectSolved V1.0
 */
public class sqlInjectSolved
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
			String sql = "select count(*) c from t_users where userName= ? and password = ?";
			System.out.println(sql);
			// userName写成了Name
			// 单引号问题
			ps = conn.prepareStatement(sql);
			//从1开始的
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next())  //是去数据服务器中读取一条数据 ，所以必须记得关闭的顺序
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
