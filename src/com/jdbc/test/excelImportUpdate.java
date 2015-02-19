/**
 *  导入身份证信息到数据库
 */
package com.jdbc.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author    叶昭良
 * @time      2015年2月19日下午7:43:29
 * @version   com.jdbc.testexcelImportUpdate V1.0
 */
public class excelImportUpdate
{
	public static String filePath = "e://身份证所在地.xls";
	/**
	 * 连接成功
	 * 执行完毕，已导入6337条数据
	 */
	public static void load()
	{
		  //创建一个文件流！用于输入流到到workbook中！
		FileInputStream fis = null;
		Connection conn = null;
		PreparedStatement ps = null;
		int rowInsert = 1; //用于标记批提交
		String sql = "insert into certificate(code,Addr) values(?,?)";
		try
		{
			fis = new FileInputStream(new File(filePath));
			conn = JDBCUtils.createConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
		} catch (FileNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   //创建一个电子簿
	       Workbook book = null;
	       try 
	       {
	    	  //创建一个POIFS文件系统，用于加载xls文件的信息！！POIFS是OLE2的纯java方式的实现！
	    	   //用于读和写
	          book = new HSSFWorkbook(fis);
	       } catch (Exception ex) 
	       {
	    	  System.out.println("载入流到POIFS系统异常");
	       }
	       //getSheet通过表名获取表的信息！ 默认的通过sheet1进行读取
	       //如何实现遍历多张表格？ 就相当于是便利一个包的过程！所以有多个表格
	       Sheet sheet = book.getSheet("Sheet1");
	       //获取到Excel文件中的所有行数 
	       int rows = sheet.getPhysicalNumberOfRows();
	       //遍历行
	       for (int i = 0; i < rows; i++) 
	       {
	          // 读取左上端单元格  通过sheet表的getRow行数获取一条记录的对象
	          Row row = sheet.getRow(i);
	           // 行不为空
	          if (row == null) 
	          {
	        	  System.out.println("不知道行的情况！");
	        	  return;
	          }
	           //获取到Excel文件中的所有的列
	          int cells = row.getPhysicalNumberOfCells();
	          String value = "";     
	          //遍历列   按照具体的Cell类型读取每一列的值！！
	          for (int j = 0; j < cells; j++)
	          {
	             //获取单元格对象
	             Cell cell = row.getCell(j);
	             //判断是否为不知道列
	             if (cell == null) 
	             {
	             	System.out.println("不知道单元格的情况");
	            	return;
	             }
	           //通过单元格的类型getCellType获取单元格的类型 来读取数据
	             switch (cell.getCellType())
	             {
	              //公式类型
	                case Cell.CELL_TYPE_FORMULA:
	                break;
	                //数值类型
	                case Cell.CELL_TYPE_NUMERIC:
	                	value += cell.getNumericCellValue() + ",";        
	                break;  
	                //字符串类型
	                case Cell.CELL_TYPE_STRING:
	                	value += cell.getStringCellValue() + ",";
	                break;
	                default:
	                      value += "0";
	                break;
	              }
	          }
	           // 将数据插入到mysql数据库中
	         String[] val = value.split(","); //从逗号分隔的字符串中提取出数据
	         try
			{
				ps.clearParameters();
				ps.setString(1, val[0]);
				ps.setString(2, val[1]);
				
				ps.addBatch();
				if(rowInsert%2000==0)
				{
					ps.executeBatch();
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				/*try
				{
					conn.rollback();
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				JDBCUtils.rollback(conn);
			}
	         
	         /* for(int j=0;j<val.length;j++)
	         {
	          //每一行列数中的值遍历出来
	        	 System.out.println(val[j]);
	          }*/
	         rowInsert++;
	       }
	     try
		{
			ps.executeBatch();
			conn.commit();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			//用完之后一定得关掉！ 不然会造成内存的负担（不像CachedRowse)
			JDBCUtils.closeQuietly(ps);
			JDBCUtils.closeQuietly(conn);
		}
	       
	       System.out.println("执行完毕，已导入"+rowInsert+"条数据");
	}
	/**
	 * 请输入你身份证的前六位
		350628
		连接成功
		身份证的前六位为350628的哥们或者姐们来自福建省平和县
		请输入你身份证的前六位
	 * @param id   你的身份证的前六位
	 */
	
	public static void checkNumber(String id)
	{
		ResultSet rs = null;
		String sql = "select * from certificate where code like ?";
		try
		{
			rs = JDBCUtils.executeQuery(sql, id);
			if(!rs.next())
			{
				System.out.println("输入为空");
				return;
			}
			System.out.println("身份证的前六位为"+id+"的哥们或者姐们来自"+rs.getString("Addr"));
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return;
		}finally
		{
			//用完之后 插一下屁股
			JDBCUtils.closeQuietly(rs);
		}
		
		
	}
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		//第一次用于导入你的数据库，其他的时候不要调用！！不然重复添加
        // load();
		while(true)
		{
			System.out.println("请输入你身份证的前六位");
			Scanner sc = new Scanner(System.in);
			String PhoneNumber = sc.nextLine();
			
			if(PhoneNumber.equalsIgnoreCase("exit")||PhoneNumber.equalsIgnoreCase("quit"))
			{
				return;
			}
			PhoneNumber = PhoneNumber.substring(0,6);
			checkNumber(PhoneNumber);
		}
    }
	
}
