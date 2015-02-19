/**
 * 
 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月19日下午11:54:35
 * @version   com.jdbc.testexcelimportUpdateXSSF V1.0
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class excelimportUpdateXSSF
{

	/**
	 * @param args
	 */
	//获取Excel文档的路径
    public static String filePath = "e://身份证所在地.xlsx";
    /**
     * 创建对Excel工作簿文件
     * HSSFWorkbook wookbook= new HSSFWorkbook(new FileInputStream(filePath));
     * 在Excel文档中，第一张工作表的缺省索引是0
     * 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
     * HSSFSheet sheet = wookbook.getSheet("Sheet1");
     * @param args
     */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

            try 
            {
                  Workbook book = null;
                  try 
                  {
                      book = new XSSFWorkbook(new FileInputStream(filePath));
                  } catch (Exception ex) 
                  {
                	  book = new HSSFWorkbook(new FileInputStream(filePath));
                  }
                  //获取第一个表单
                  Sheet sheet = book.getSheet("Sheet1");
                  //获取到Excel文件中的所有行数
                  int rows = sheet.getPhysicalNumberOfRows();
                  //遍历行
                  for (int i = 0; i < rows; i++) 
                  {
                        // 读取左上端单元格
	                  Row row = sheet.getRow(i);
	                        // 行不为空
	                  if (row == null) 
	                  {
	                	  System.out.println("不知道列的情况");
	                	  return;
	                  }
	                 //获取到Excel文件中的所有的列
	                 int cells = row.getPhysicalNumberOfCells();
	                 String value = "";     
	                 //遍历列
	                 for (int j = 0; j < cells; j++) 
	                 {
	                	 //获取到列的值
	                	 Cell cell = row.getCell(j);
	                	 if (cell == null) 
	                	 {
	                		 System.out.println("单元格不知道");
	                		 return;
	                	 }
	                	 switch (cell.getCellType()) 
	                	 {
	                	 	//公式不处理
	                	 	case Cell.CELL_TYPE_FORMULA:
	                	 		break;
	                	 	//数值
	                	 	case Cell.CELL_TYPE_NUMERIC:
	                	 		value += cell.getNumericCellValue() + ",";        
	                	 		break;  
	                	 	//字符串
	                	 	case Cell.CELL_TYPE_STRING:
	                	 		value += cell.getStringCellValue() + ",";
	                	 		break;
	                	 	default:
                              value += "0";
                              break;
	                	 }
	                 }
	                 	// 将数据插入到mysql数据库中
                    String[] val = value.split(",");
                    for(int j=0;j<val.length;j++)
                    {
                      //每一行列数中的值遍历出来
                    	System.out.println(val[j]);
                    }
                  }
            }catch(IOException e)
            {
            	System.out.println("IO读取异常");
            }
	}
}
