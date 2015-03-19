/**
 *   ͨ��HSSF�Ƚ��ϵķ���ȡxls��ʽ�����ڵ�xlsx����XSSFWorkBook ��ʽ���м���
 */
package com.jdbc.test;

/**
 * @author    Ҷ����
 * @time      2015��2��19������2:27:13
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
	//��¼��������Ϣ
   // static Log log = LogFactory.getLog(TestExcel.class); 
    //��ȡExcel�ĵ���·��
    /**
     * ������Excel�������ļ�
     * HSSFWorkbook wookbook= new HSSFWorkbook(new FileInputStream(filePath));
     * ��Excel�ĵ��У���һ�Ź�������ȱʡ������0��
     * �����Ϊ��HSSFSheet sheet = workbook.getSheetAt(0);
     * HSSFSheet sheet = wookbook.getSheet("Sheet1");
     */
    public static String filePath = "e://����֤���ڵ�.xls";
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
	   //����һ���ļ�������������������workbook�У�
	   FileInputStream fis = new FileInputStream(new File(filePath));
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
         for(int j=0;j<val.length;j++)
         {
          //ÿһ�������е�ֵ��������
        	 System.out.println(val[j]);
          }
        }
   }
}



