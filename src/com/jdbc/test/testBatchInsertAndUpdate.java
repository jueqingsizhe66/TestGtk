/**
 * 
 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月18日下午2:31:56
 * @version   com.jdbc.testtestBatchInsertAndUpdate V1.0
 */
import java.sql.*;
public class testBatchInsertAndUpdate
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//在没有批量导入数据之前，一般是需要花费很长的时间，现在采用addbatch进行
		Connection conn = null;
		PreparedStatement ps = null;
		String sql="insert into t_person(Id,Name,Age,Gender)values(?,?,?,?)";
		//测试了删除操作，success!
		//String sql= "delete from t_person where Name=?";
		long startTime = System.currentTimeMillis();
		/**
		 * 不添加批处理后，添加1000条后耗时845
		 *  不添加批处理  添加11000条后   耗时2604  ,所以我不知道批处理的好处啊！！！
		 *                           反正即使2530s左右
		 * 
		   添加批处理后		添加1000条后耗时799
		                                 添加11000条后耗时3206
		                                 添加11000条后耗时3143(每个1000提交一次）


		 */
		try
		{
			//批处理的过程不要进行封装 ！因为每次是涉及到 和数据库进行互联！
			//也就是从数据库中读一条 你进行写一条！没什么必要！！姑且放在那边！
			//之前表中已有 215行
			conn = JDBCUtils.createConnection();
			conn.setAutoCommit(false);//相当于begin的作用
			ps = conn.prepareStatement(sql);
			for(int i = 316;i < 11316;i++)
			{
				//JDBCUtils.executeUpdate(conn, sql, i,"Autumn",20,1);
				//ps.clearParameters();
				ps.setInt(1, i);
				ps.setString(2,"Winter");
				ps.setInt(3, 21);
				ps.setBoolean(4, true);
				//ps.executeUpdate();
				
				
				/**
				 * if(i%1000 == 0) //每个1000次提交一次！！！这样好！
				 * {
				 *    ps.executeBatch();
				 *  }
				 *  少于1000次的最后一笔  再用一次ps.executeBatch();
				 */
				ps.addBatch(); //据说是装到箱子的作用
				if(i%1000 == 0)
				{
					ps.executeBatch();
				}
			}
				//JDBCUtils.executeUpdate(conn, sql, i,"Autumn",20,1);
				//测试删除使用的
				//JDBCUtils.executeUpdate(conn, sql, "Autumn");
			//}
			ps.executeBatch(); //把箱子里面的数据 直接一次性提交mysql
			conn.commit();
			System.out.println("添加1000条后耗时"+(System.currentTimeMillis()
					-startTime));
		}catch(SQLException e)
		{
			try
			{
				conn.rollback();
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally
		{
			JDBCUtils.closeQuietly(conn);
		}
		
	}

}
