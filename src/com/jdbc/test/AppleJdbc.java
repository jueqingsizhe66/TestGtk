/**
 *   ��һ����������
 */
package com.jdbc.test;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;



/**
 * @author    Ҷ����
 * @time      2015��2��17������1:52:36
 * @version   com.jdbc.testAppleJdbc V1.0
 */
public class AppleJdbc 
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
			System.out.println("���ݿ��������ʧ��"+e.getMessage());
			return; //������ʧ���˾Ͳ���Ҫ������ȥ
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/study?se"
					+ "Unicode=true&characterEncoding=UTF8","root","root");
			System.out.println(conn.getClass().getName());
			ps = conn.prepareStatement("insert into T_person(Id,Name,Age,Gender) values"
					+ "(6,'yezhao1',25,0)");
			System.out.println(ps.getClass().getName());
			int i = ps.executeUpdate();
			System.out.println("��"+i+"����¼��Ӱ��");
//			System.out.println("���ӳɹ�");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("���ݿ�����ʧ��"+e.getMessage());
		}finally
		{
			AppleJdbc.closeQuietly(ps);
			AppleJdbc.closeQuietly(conn);
			
		}
	}
	
	static void closeQuietly(PreparedStatement ps)
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

	

}