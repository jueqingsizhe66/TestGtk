/**
 * 
 */
package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author    Ҷ����
 * @time      2015��2��17������10:14:24
 * @version   com.jdbc.testnullTest V1.0
 */
public class nullTest
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
			String sql = "select * from b1";
			System.out.println(sql);
			// userNameд����Name
			// ����������
			ps = conn.prepareStatement(sql);
			//��1��ʼ��
			/*ps.setString(1, userName);
			ps.setString(2, password);*/
			rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String Hobbies = rs.getString("Hobbies");
				//int age = rs.getInt("Age");
				Integer age = (Integer)rs.getObject("Age");
				//��Ϊint����Ϊnull����ֻ��Ϊ0  ��ΪbooleanҲ����Ϊnull������ֻ��ΪĬ��ֵfalse
				//boolean gender = rs.getBoolean("Gender");
				if(age == null)
				{
					System.out.println("���䲻֪��");
				}else
				{
					int age1 =age;
					System.out.println("����Ϊ"+age1);
				}
				
				Boolean bGender = (Boolean)rs.getObject("Gender");
				if(bGender==null)
				{
					System.out.println("�Ա�֪��");
	
				}else
				{
					boolean gender1 = bGender;
					System.out.println("�Ա���"+(gender1?"��":"Ů"));
				}
				//System.out.println("Id="+id+",ҵ�మ��="+Hobbies+"���䣺"+age+",�Ա�"+(gender?"��":"Ů"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}