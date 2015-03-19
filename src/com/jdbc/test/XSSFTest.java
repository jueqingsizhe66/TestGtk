/**
 *   2007֮���xlsx�ļ��Ĵ���
 *    ע�� Ҫ�ർ��org.apache.poi -> poi-ooxml 3.9.jar ���jar��
 *    ����ʹ��XSSFWorkBook
 *    http://poi.apache.org/spreadsheet/converting.html
 *    
 *    ����2007�汾֮���excel �������xmlbeans.apache.org�� xmlbeans��
 *     org.apache.xmlbeans��  ���򱨴�
 */
package com.jdbc.test;

/**
 * @author    Ҷ����
 * @time      2015��2��19������3:00:04
 * @version   com.jdbc.testXSSFTest V1.0
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFTest
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		// import org.apache.poi.ss.usermodel.*;
		
		//����һ������������
		Workbook[] wbs = new Workbook[] { new HSSFWorkbook(), new XSSFWorkbook() };
		for(int i=0; i<wbs.length; i++) 
		{
		   Workbook wb = wbs[i];
		   //����createHelperд�뵥Ԫ������
		   CreationHelper createHelper = wb.getCreationHelper();

		   // create a new sheet
		   Sheet s = wb.createSheet();
		   // declare a row object reference
		   Row r = null;
		   // declare a cell object reference
		   Cell c = null;
		   // create 2 cell styles
		   
		// Style the cell with borders all around.
		   
		   //���п��޲��֣�  ��������������ΪҪͬʱ�������У�
		   CellStyle cs = wb.createCellStyle();
		   CellStyle cs2 = wb.createCellStyle();
		   //���õ�Ԫ�����ݸ�ʽ
		   DataFormat df = wb.createDataFormat();

		   // create 2 fonts objects
		   Font f = wb.createFont();
		   Font f2 = wb.createFont();

		   // Set font 1 to 12 point type, blue and bold
		   f.setFontHeightInPoints((short) 12);
		   f.setColor( IndexedColors.RED.getIndex() );
		   f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		   // Set font 2 to 10 point type, red and bold
		   f2.setFontHeightInPoints((short) 10);
		   f2.setColor( IndexedColors.RED.getIndex() );
		   f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

		   // Set cell style and formatting
		   cs.setFont(f);
		   cs.setDataFormat(df.getFormat("#,##0.0"));

		   // Set the other cell style and formatting
		   cs2.setBorderBottom(CellStyle.BORDER_THIN);
		   cs2.setDataFormat(df.getFormat("text"));
		   cs2.setFont(f2);


		   // Define a few rows ����30�����ݣ� 10��
		   for(int rownum = 0; rownum < 30; rownum++) 
		   {
			   Row r1 = s.createRow(rownum);
			   //ÿ���������е�����
			   for(int cellnum = 0; cellnum < 10; cellnum += 2)
			   {
				   Cell c1 = r1.createCell(cellnum);
				   Cell c2 = r1.createCell(cellnum+1);
				   
				   
				   c1.setCellValue((double)rownum + (cellnum/10));
				   c2.setCellValue(createHelper.createRichTextString("Hello! " + cellnum));
				    CellStyle style = wb.createCellStyle();
				    style.setBorderBottom(CellStyle.BORDER_THIN);
				    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				    style.setBorderLeft(CellStyle.BORDER_THIN);
				    style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
				    style.setBorderRight(CellStyle.BORDER_THIN);
				    style.setRightBorderColor(IndexedColors.BLUE.getIndex());
				    style.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
				    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
				    c1.setCellStyle(style);
			   }
		   }
		   
		   // Save
		   String filename = "e://workbook1.xls";
		   //�������ִ�У�
		   if(wb instanceof XSSFWorkbook) 
		   {
		     filename = filename + "x";
		   }
		 
		   FileOutputStream out =null;
			try
			{
				out = new FileOutputStream(filename);
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wb.write(out);
			out.close();
		}
	}

}