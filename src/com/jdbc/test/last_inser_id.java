/**
 * 
 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月18日下午3:20:17
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
			conn = JDBCUtils.createConnection(); //用于查询
			//当存在相同的Id则会报错，并且conn必须是相同的连接，才会显示最后一次的连接
			JDBCUtils.executeUpdate(conn,sql,"悟空",30,1);
			rs = JDBCUtils.executeQuery(conn, sql2);
			rs.next();
			long id = rs.getLong("Id");
			System.out.println("最后一次插入的ID是"+id);
		}catch(SQLException e)
		{
			System.out.println("插入或查询失败");
		}finally
		{
			JDBCUtils.closeAll(rs);
		}
	}

}
