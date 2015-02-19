/**
 * 
 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月18日下午1:43:28
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
		    //设置自动提交关闭之后，只能使用conn.commit()之后，才能进行提交
		    conn.setAutoCommit(false);
		    ps1 = conn.prepareStatement("Update t_person Set Age=Age+1 where Name='yezhao'");
		    ps1.executeUpdate();
		    ps2 = conn.prepareStatement("Update t_person Set Age=Age-1 where Name='xinran'");
		    ps2.executeUpdate();
		    //把之前的update信息提交到
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
