/**
 * 
 */
package com.jdbc.test;

/**
 * @author    Ҷ����
 * @time      2015��2��19������11:54:35
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
	//��ȡExcel�ĵ���·��
    public static String filePath = "e://����֤���ڵ�.xlsx";
    /**
     * ������Excel�������ļ�
     * HSSFWorkbook wookbook= new HSSFWorkbook(new FileInputStream(filePath));
     * ��Excel�ĵ��У���һ�Ź�������ȱʡ������0
     * �����Ϊ��HSSFSheet sheet = workbook.getSheetAt(0);
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
                  //��ȡ��һ������
                  Sheet sheet = book.getSheet("Sheet1");
                  //��ȡ��Excel�ļ��е���������
                  int rows = sheet.getPhysicalNumberOfRows();
                  //������
                  for (int i = 0; i < rows; i++) 
                  {
                        // ��ȡ���϶˵�Ԫ��
	                  Row row = sheet.getRow(i);
	                        // �в�Ϊ��
	                  if (row == null) 
	                  {
	                	  System.out.println("��֪���е����");
	                	  return;
	                  }
	                 //��ȡ��Excel�ļ��е����е���
	                 int cells = row.getPhysicalNumberOfCells();
	                 String value = "";     
	                 //������
	                 for (int j = 0; j < cells; j++) 
	                 {
	                	 //��ȡ���е�ֵ
	                	 Cell cell = row.getCell(j);
	                	 if (cell == null) 
	                	 {
	                		 System.out.println("��Ԫ��֪��");
	                		 return;
	                	 }
	                	 switch (cell.getCellType()) 
	                	 {
	                	 	//��ʽ������
	                	 	case Cell.CELL_TYPE_FORMULA:
	                	 		break;
	                	 	//��ֵ
	                	 	case Cell.CELL_TYPE_NUMERIC:
	                	 		value += cell.getNumericCellValue() + ",";        
	                	 		break;  
	                	 	//�ַ���
	                	 	case Cell.CELL_TYPE_STRING:
	                	 		value += cell.getStringCellValue() + ",";
	                	 		break;
	                	 	default:
                              value += "0";
                              break;
	                	 }
	                 }
	                 	// �����ݲ��뵽mysql���ݿ���
                    String[] val = value.split(",");
                    for(int j=0;j<val.length;j++)
                    {
                      //ÿһ�������е�ֵ��������
                    	System.out.println(val[j]);
                    }
                  }
            }catch(IOException e)
            {
            	System.out.println("IO��ȡ�쳣");
            }
	}
}