/**
 * 
 */
package com.jdbc.test;

//�����ǰ� window--> preferenece-->java-->compilor--->error|warning--->Deprecated API��������
//���� ����ʶ������࣡������δ�ṩԴ�ļ���ʵ��

import java.sql.*;

import javax.sql.rowset.*;
/**
 * @author    Ҷ����
 * @time      2015��2��19������12:33:47
 * @version   com.jdbc.testCachedRowSetTest V1.0
 */
public class CachedRowSetTest
{

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
	private static final String DATABASE_URL="jdbc:mysql://localhost/study?"
					+ "seUnicode=true&characterEncoding=UTF8";
	private static final String DATABASE_USER="root";
	private static final String DATABASE_PASSWORD="root";
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		/*CachedRowSet rs;
	    String ROWSET_IMPL_CLASS = "com.sun.rowset.CachedRowSetImpl";
	    
	    
	    Class c;
		try
		{
			c = Class.forName(ROWSET_IMPL_CLASS);
			rs = (CachedRowSet) c.newInstance();

		    rs.setUrl("jdbc:postgresql:study");
		    rs.setUsername("root");
		    rs.setPassword("root");

		    rs.setCommand("select * from b1 where Age like ?");
		    //rs.setString(1, "I%");
		    rs.setInt(1,10);
		    rs.execute();

		    while (rs.next()) {
		      if (rs.getInt("Id") == 0) {
		        rs.setString(1, "newString");
		        rs.updateRow(); // Normal JDBC

		        rs.acceptChanges();
		      }
		    }
		    rs.close();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		try{
			String sql="select * from t_users";
			Class.forName(DRIVER_CLASS);
			Connection conn=DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
			conn.setAutoCommit(false);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			//ʹ��RowSetProvider����RowSetFactory
			RowSetFactory factory =RowSetProvider.newFactory();
			//����Ĭ�ϵ�CachedRowSetʵ��
			CachedRowSet cache=factory.createCachedRowSet();
			//��rs���CachedRowSet
			cache.populate(rs);
			
			//�ر���Դ
			//rs.close();
			//psmt.close();
			//conn.close();
			
			cache.afterLast();
			while(cache.previous()){
				System.out.println(cache.getInt(1)+"\t"+cache.getString(2)+"\t"+cache.getString(3));
				if(cache.getInt("Id")==1){
					cache.updateString("userName", "honghonghuohuo");
					cache.updateRow();
					//�Ѷ�RowSet�������޸�ͬ�����ײ����ݿ�
						cache.acceptChanges(conn);
					}
				}
	
	}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
