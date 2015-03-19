/**
 * 
 */
package com.jdbc.test;

/**
 * @author    Ҷ����
 * @time      2015��2��18������3:49:22
 *                        ����5:00����
 * @version   com.jdbc.testphoneCheck V1.0
 */
import java.io.*;
import java.sql.*;
import java.util.Scanner;


public class phoneCheck
{

	/**
	 * @param args
	 */
/**
 * ID,MobileNumber,MobileArea,MobileType,AreaCode,PostCode,0
	���ӳɹ�
	�ܹ�������300105������.��ʱ��64712
 */
	private static void load()
	{
		//����csv���ݣ�����BufferedInputStream
	  InputStream is = null;
	  InputStreamReader isr = null;
	  BufferedReader bis = null; //һ��һ�ж�������
	  
	  Connection conn = null;
	  PreparedStatement ps = null;
		int len = 0;
		int i = 0;
		long startms = System.currentTimeMillis();
		try
		{
			is = new FileInputStream("e://phone.csv");
		    isr = new InputStreamReader(is);
			bis = new BufferedReader(isr);
			
			System.out.println(bis.readLine());
			//��һ���ֶ���ȥ
			String apple=  null;
			String[] applePiles = null;
			//��һ�β���  �ַ������ж���
/*			System.out.println(bis.readLine());
			System.out.println(bis.readLine());*/
			/*apple =bis.readLine();
			//�ַ�����һ��ʧ�󣡣� ���²���ʧ�ܣ���������Ĭ�ϴ��ڵġ�����ɾ��
			apple = apple.replaceAll("\"", "");
			//�ڶ��β��� �ַ����ָ�
			String[] splitArray = apple.split(",");
			for(String apple1 :splitArray)
			{
				System.out.println(apple1);
			}*/
			conn = JDBCUtils.createConnection();
			conn.setAutoCommit(false);
			
			String sql = "insert into phone(MobileNumber,MobileArea,MobileType,Area"
					+ "Code,PostCode) values(?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			/**
			 * 370�й�����
				371�й�����
				֮�� ���� ��������Ϊ�յ�ԭ�� 
			 */
			while((apple=bis.readLine()) != null)
			{
				apple = apple.replaceAll("\"", "");
				applePiles = apple.split(",");
				
				ps.clearParameters();
				//ps.setInt(1, Integer.parseInt(applePiles[1].equalsIgnoreCase("")?"1111":applePiles[4]));
				//ps.setInt(1, Integer.parseInt(applePiles[1]));
				ps.setString(1, applePiles[1]);
				ps.setString(2, applePiles[2]);
				//System.out.println(i+applePiles[3]+applePiles[4]);
				ps.setString(3, applePiles[3]);
//				ps.setInt(4, Integer.parseInt(applePiles[4]));//.equalsIgnoreCase("")?"1111":applePiles[4]));
//				ps.setInt(5, Integer.parseInt(applePiles[5]));//.equalsIgnoreCase("")?"1111":applePiles[4]));
				ps.setString(4, applePiles[4]);
				ps.setString(5, applePiles[5]);
				ps.addBatch();

				i++;
				//ÿ��2000��֮������ύһ�� �����ݿ�
				if(i%2000==0)
				{
					ps.executeBatch();
				}
			}
			//�ύ ���ļ��ٲ�
			ps.executeBatch();
			conn.commit();
			long endMs = System.currentTimeMillis();
			System.out.println("�ܹ�������"+i+"������"+".��ʱ��"+(endMs-startms));
		}catch(IOException e)
		{
			throw new RuntimeException("��ȡ�ļ��쳣");
		}catch(SQLException e)
		{
			try
			{
				conn.rollback();
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //��������
			throw new RuntimeException("���ݿ�����쳣��");
			
		}finally
		{
			//����֮��ǵò�ƨ��
			JDBCUtils.closeQuietly(ps);
			JDBCUtils.closeQuietly(conn);
		}
	}
	
	public static void checkPhoneNumber(String PhoneNumber)
	{

		
		PhoneNumber = "%"+PhoneNumber+"%";
		String sql = "select * from phone where MobileNumber like ?";
		ResultSet rs  = null;
		try
		{
			rs = JDBCUtils.executeQuery(sql, PhoneNumber);
			if(!rs.next())
			{
				System.out.println("��ǰ�汾û��"+PhoneNumber+"����Ϣ");
//				return;
			}
			//rs.next();
			System.out.println(PhoneNumber+"�ֻ�������"+rs.getString("MobileArea")+rs.getString("MobileType"));
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return;
		}finally
		{
			//����֮��Ͳ�ƨ��
			JDBCUtils.closeAll(rs);
		}
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//����һ�μ���
		//load();
		while(true)
		{
			System.out.println("�������ֻ���(����7λ)");
			Scanner sc = new Scanner(System.in);
			String PhoneNumber = sc.nextLine();
			
			if(PhoneNumber.equalsIgnoreCase("exit")||PhoneNumber.equalsIgnoreCase("quit"))
			{
				return;
			}
			PhoneNumber = PhoneNumber.substring(0,7);
			checkPhoneNumber(PhoneNumber);
		}
		
	}

}