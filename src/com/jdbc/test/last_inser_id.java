/**
 * 
 */
package com.jdbc.test;

/**
 * @author    Ҷ����
 * @time      2015��2��18������3:20:17
 * @version   com.jdbc.testlast_inser_id V1.0
 */
import java.sql.*;
public class last_inser_id
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String sql = "insert into t_person (Name,Age,Gender) values(?,?,?)";
		String sql2 = "select last_insert_id() id";
		Connection conn = null;
		ResultSet rs = null;
		try
		{
			conn = JDBCUtils.createConnection(); //���ڲ�ѯ
			//��������ͬ��Id��ᱨ��������conn��������ͬ�����ӣ��Ż���ʾ���һ�ε�����
			JDBCUtils.executeUpdate(conn,sql,"���",30,1);
			rs = JDBCUtils.executeQuery(conn, sql2);
			rs.next();
			long id = rs.getLong("Id");
			System.out.println("���һ�β����ID��"+id);
		}catch(SQLException e)
		{
			System.out.println("������ѯʧ��");
		}finally
		{
			JDBCUtils.closeAll(rs);
		}
	}

}