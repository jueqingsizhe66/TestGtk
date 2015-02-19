/**
 *   通过HSSF比较老的风格读取xls方式，现在的xlsx采用XSSFWorkBook 方式进行加载
 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月19日下午2:27:13
 * @version   com.jdbc.testexcelImport V1.0  poi3.11
 */
// TODO Auto-generated method stub		package com.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;*/
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class excelImport 
{

	/**
	 * @param args
	 */		      
	//记录类的输出信息
   // static Log log = LogFactory.getLog(TestExcel.class); 
    //获取Excel文档的路径
    /**
     * 创建对Excel工作簿文件
     * HSSFWorkbook wookbook= new HSSFWorkbook(new FileInputStream(filePath));
     * 在Excel文档中，第一张工作表的缺省索引是0，
     * 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
     * HSSFSheet sheet = wookbook.getSheet("Sheet1");
     */
    public static String filePath = "e://身份证所在地.xls";
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
	   //创建一个文件流！用于输入流到到workbook中！
	   FileInputStream fis = new FileInputStream(new File(filePath));
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
         for(int j=0;j<val.length;j++)
         {
          //每一行列数中的值遍历出来
        	 System.out.println(val[j]);
          }
        }
   }
}




