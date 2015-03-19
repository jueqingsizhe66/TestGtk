/**
 *  ��������֤��Ϣ�����ݿ�
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
 * @author    Ҷ����
 * @time      2015��2��19������7:43:29
 * @version   com.jdbc.testexcelImportUpdate V1.0
 */
public class excelImportUpdate
{
	public static String filePath = "e://����֤���ڵ�.xls";
	/**
	 * ���ӳɹ�
	 * ִ����ϣ��ѵ���6337������
	 */
	public static void load()
	{
		  //����һ���ļ�������������������workbook�У�
		FileInputStream fis = null;
		Connection conn = null;
		PreparedStatement ps = null;
		int rowInsert = 1; //���ڱ�����ύ
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
		   //����һ�����Ӳ�
	       Workbook book = null;
	       try 
	       {
	    	  //����һ��POIFS�ļ�ϵͳ�����ڼ���xls�ļ�����Ϣ����POIFS��OLE2�Ĵ�java��ʽ��ʵ�֣�
	    	   //���ڶ���д
	          book = new HSSFWorkbook(fis);
	       } catch (Exception ex) 
	       {
	    	  System.out.println("��������POIFSϵͳ�쳣");
	       }
	       //getSheetͨ��������ȡ������Ϣ�� Ĭ�ϵ�ͨ��sheet1���ж�ȡ
	       //���ʵ�ֱ������ű��� ���൱���Ǳ���һ�����Ĺ��̣������ж������
	       Sheet sheet = book.getSheet("Sheet1");
	       //��ȡ��Excel�ļ��е��������� 
	       int rows = sheet.getPhysicalNumberOfRows();
	       //������
	       for (int i = 0; i < rows; i++) 
	       {
	          // ��ȡ���϶˵�Ԫ��  ͨ��sheet����getRow������ȡһ����¼�Ķ���
	          Row row = sheet.getRow(i);
	           // �в�Ϊ��
	          if (row == null) 
	          {
	        	  System.out.println("��֪���е������");
	        	  return;
	          }
	           //��ȡ��Excel�ļ��е����е���
	          int cells = row.getPhysicalNumberOfCells();
	          String value = "";     
	          //������   ���վ����Cell���Ͷ�ȡÿһ�е�ֵ����
	          for (int j = 0; j < cells; j++)
	          {
	             //��ȡ��Ԫ�����
	             Cell cell = row.getCell(j);
	             //�ж��Ƿ�Ϊ��֪����
	             if (cell == null) 
	             {
	             	System.out.println("��֪����Ԫ������");
	            	return;
	             }
	           //ͨ����Ԫ�������getCellType��ȡ��Ԫ������� ����ȡ����
	             switch (cell.getCellType())
	             {
	              //��ʽ����
	                case Cell.CELL_TYPE_FORMULA:
	                break;
	                //��ֵ����
	                case Cell.CELL_TYPE_NUMERIC:
	                	value += cell.getNumericCellValue() + ",";        
	                break;  
	                //�ַ�������
	                case Cell.CELL_TYPE_STRING:
	                	value += cell.getStringCellValue() + ",";
	                break;
	                default:
	                      value += "0";
	                break;
	              }
	          }
	           // �����ݲ��뵽mysql���ݿ���
	         String[] val = value.split(","); //�Ӷ��ŷָ����ַ�������ȡ������
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
	          //ÿһ�������е�ֵ��������
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
			//����֮��һ���ùص��� ��Ȼ������ڴ�ĸ���������CachedRowse)
			JDBCUtils.closeQuietly(ps);
			JDBCUtils.closeQuietly(conn);
		}
	       
	       System.out.println("ִ����ϣ��ѵ���"+rowInsert+"������");
	}
	/**
	 * ������������֤��ǰ��λ
		350628
		���ӳɹ�
		����֤��ǰ��λΪ350628�ĸ��ǻ��߽������Ը���ʡƽ����
		������������֤��ǰ��λ
	 * @param id   �������֤��ǰ��λ
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
				System.out.println("����Ϊ��");
				return;
			}
			System.out.println("����֤��ǰ��λΪ"+id+"�ĸ��ǻ��߽�������"+rs.getString("Addr"));
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return;
		}finally
		{
			//����֮�� ��һ��ƨ��
			JDBCUtils.closeQuietly(rs);
		}
		
		
	}
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		//��һ�����ڵ���������ݿ⣬������ʱ��Ҫ���ã�����Ȼ�ظ�����
        // load();
		while(true)
		{
			System.out.println("������������֤��ǰ��λ");
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