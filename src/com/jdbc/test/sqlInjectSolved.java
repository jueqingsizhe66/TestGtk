/**
 * �������û����֣�
yzl
�������û����룺
457866
select count(*) c from t_users where userName= ? and password = ?
��½�ɹ�

�������û����֣�
yzl
�������û����룺
a' or 'a'='a
select count(*) c from t_users where userName= ? and password = ?
��½�ɹ�

����û�н������

�������û����֣�
yzl
�������û����룺
a' or 'a'='a
select count(*) c from t_users where userName= ? and password = ?
�޷���½
ԭ������Ϊif(result <= 0) д����if(result < 0)
 */
package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author    Ҷ����
 * @time      2015��2��17������9:53:53
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
			String sql = "select count(*) c from t_users where userName= ? and password = ?";
			System.out.println(sql);
			// userNameд����Name
			// ����������
			ps = conn.prepareStatement(sql);
			//��1��ʼ��
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next())  //��ȥ���ݷ������ж�ȡһ������ �����Ա���ǵùرյ�˳��
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