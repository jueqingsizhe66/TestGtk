/**
 * �������û����֣�
dfaf
�������û����룺
a' or 'a'='a
select count(*) c from t_users where userName='dfaf' and Password='a' or 'a'='a'
��½�ɹ�

 */
package com.jdbc.test;

/**
 * @author    Ҷ����
 * @time      2015��2��17������9:16:23
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
		System.out.println("�������û����֣�");
		String userName = sc.nextLine();
		System.out.println("�������û����룺");
		String password = sc.nextLine();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.out.println("���ݿ���������ʧ��"+e.getMessage());
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
			// userNameд����Name
			// ����������
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				int result = rs.getInt("c");
				if(result <= 0)
				{
					System.out.println("�޷���½");
				}else
				{
					System.out.println("��½�ɹ�");
				}
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}