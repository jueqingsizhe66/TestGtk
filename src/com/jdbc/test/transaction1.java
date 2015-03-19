/**
 * 
 */
package com.jdbc.test;

/**
 * @author    Ҷ����
 * @time      2015��2��18������1:43:28
 * @version   com.jdbc.testtransaction1 V1.0
 */
import java.sql.*;
public class transaction1
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try
		{
		    conn = JDBCUtils.createConnection();
		    //�����Զ��ύ�ر�֮��ֻ��ʹ��conn.commit()֮�󣬲��ܽ����ύ
		    conn.setAutoCommit(false);
		    ps1 = conn.prepareStatement("Update t_person Set Age=Age+1 where Name='yezhao'");
		    ps1.executeUpdate();
		    ps2 = conn.prepareStatement("Update t_person Set Age=Age-1 where Name='xinran'");
		    ps2.executeUpdate();
		    //��֮ǰ��update��Ϣ�ύ��
		    conn.commit();
		} catch (SQLException e)
		{
		    try
			{
				conn.rollback();
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}