/**
 * 
 */
package com.jdbc.test;

import java.sql.*;



/**
 * @author    Ҷ����
 * @time      2015��2��17������7:47:52
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
			System.out.println("����ʧ��");
			return;
		}	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/study?"
					+ "seUnicode=true&characterEncoding=UTF8","root","root");
			//utf8�����������Ҫ�������������󲿷���������ݿ��
			//���б����ʽ
			//���б����ʽ
			ps = conn.prepareStatement("select * from T_person");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				boolean gender = rs.getBoolean("Gender");
				System.out.println("Id="+id+",����="+name+"���䣺"+age+",�Ա�"+(gender?"��":"Ů"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}