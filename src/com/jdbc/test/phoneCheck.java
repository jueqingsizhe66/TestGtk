/**
 * 
 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月18日下午3:49:22
 *                        下午5:00左右
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
	连接成功
	总共添加了300105条数据.耗时：64712
 */
	private static void load()
	{
		//导入csv数据，利用BufferedInputStream
	  InputStream is = null;
	  InputStreamReader isr = null;
	  BufferedReader bis = null; //一行一行读入数据
	  
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
			//第一行字段舍去
			String apple=  null;
			String[] applePiles = null;
			//第一次测试  字符串的行读入
/*			System.out.println(bis.readLine());
			System.out.println(bis.readLine());*/
			/*apple =bis.readLine();
			//字符串的一次失误！！ 导致插入失败！！＂＂是默认存在的　必须删掉
			apple = apple.replaceAll("\"", "");
			//第二次测试 字符串分隔
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
			 * 370中国电信
				371中国电信
				之后 报错 ，可能是为空的原因 
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
				//每个2000步之后进行提交一次 到数据库
				if(i%2000==0)
				{
					ps.executeBatch();
				}
			}
			//提交 最后的几百步
			ps.executeBatch();
			conn.commit();
			long endMs = System.currentTimeMillis();
			System.out.println("总共添加了"+i+"条数据"+".耗时："+(endMs-startms));
		}catch(IOException e)
		{
			throw new RuntimeException("读取文件异常");
		}catch(SQLException e)
		{
			try
			{
				conn.rollback();
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //又忘记了
			throw new RuntimeException("数据库插入异常！");
			
		}finally
		{
			//用完之后记得差屁股
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
				System.out.println("当前版本没有"+PhoneNumber+"的信息");
//				return;
			}
			//rs.next();
			System.out.println(PhoneNumber+"手机号来自"+rs.getString("MobileArea")+rs.getString("MobileType"));
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return;
		}finally
		{
			//用完之后就差屁股
			JDBCUtils.closeAll(rs);
		}
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//导入一次即可
		//load();
		while(true)
		{
			System.out.println("请输入手机号(至少7位)");
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
