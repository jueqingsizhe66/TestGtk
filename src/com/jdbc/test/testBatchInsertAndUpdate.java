/**
 * 
 */
package com.jdbc.test;

/**
 * @author    Ҷ����
 * @time      2015��2��18������2:31:56
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
		//��û��������������֮ǰ��һ������Ҫ���Ѻܳ���ʱ�䣬���ڲ���addbatch����
		Connection conn = null;
		PreparedStatement ps = null;
		String sql="insert into t_person(Id,Name,Age,Gender)values(?,?,?,?)";
		//������ɾ��������success!
		//String sql= "delete from t_person where Name=?";
		long startTime = System.currentTimeMillis();
		/**
		 * ������������������1000�����ʱ845
		 *  ������������  ����11000����   ��ʱ2604  ,�����Ҳ�֪���������ĺô���������
		 *                           ������ʹ2530s����
		 * 
		   ������������		����1000�����ʱ799
		                                 ����11000�����ʱ3206
		                                 ����11000�����ʱ3143(ÿ��1000�ύһ�Σ�


		 */
		try
		{
			//�������Ĺ��̲�Ҫ���з�װ ����Ϊÿ�����漰�� �����ݿ���л�����
			//Ҳ���Ǵ����ݿ��ж�һ�� �����дһ����ûʲô��Ҫ�������ҷ����Ǳߣ�
			//֮ǰ�������� 215��
			conn = JDBCUtils.createConnection();
			conn.setAutoCommit(false);//�൱��begin������
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
				 * if(i%1000 == 0) //ÿ��1000���ύһ�Σ����������ã�
				 * {
				 *    ps.executeBatch();
				 *  }
				 *  ����1000�ε����һ��  ����һ��ps.executeBatch();
				 */
				ps.addBatch(); //��˵��װ�����ӵ�����
				if(i%1000 == 0)
				{
					ps.executeBatch();
				}
			}
				//JDBCUtils.executeUpdate(conn, sql, i,"Autumn",20,1);
				//����ɾ��ʹ�õ�
				//JDBCUtils.executeUpdate(conn, sql, "Autumn");
			//}
			ps.executeBatch(); //��������������� ֱ��һ�����ύmysql
			conn.commit();
			System.out.println("����1000�����ʱ"+(System.currentTimeMillis()
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