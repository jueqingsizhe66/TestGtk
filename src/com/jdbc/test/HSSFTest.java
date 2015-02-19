/**
 *  用于创建一个excel表格  采用HSSF格式！ 一般是07年之前的
 *  http://poi.apache.org/spreadsheet/converting.html
 */
package com.jdbc.test;

/**
 * @author    叶昭良
 * @time      2015年2月19日下午2:44:44
 * @version   com.jdbc.testHSSFTest V1.0
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


//载入hssf的poifs文件系统的模型
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

public class HSSFTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// import org.apache.poi.hssf.usermodel.*;
		//创建一个poifs的文件系统！ 关于.xls
		HSSFWorkbook wb = new HSSFWorkbook();
		// create a new sheet  创建一个新的表单
		HSSFSheet s = wb.createSheet();
		// declare a row object reference 创建一个新的行引用
		HSSFRow r = null;
		// declare a cell object reference 创建一个新的单元格引用
		HSSFCell c = null;
		// create 2 cell styles 创建单元的风格
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFCellStyle cs2 = wb.createCellStyle();
		//创建单元格的数据格式
		HSSFDataFormat df = wb.createDataFormat();

		// create 2 fonts objects  创建这个POIFS的字体  两个对象
		HSSFFont f = wb.createFont();
		HSSFFont f2 = wb.createFont();

		// Set font 1 to 12 point type, blue and bold
		f.setFontHeightInPoints((short) 12);
		f.setColor( HSSFColor.RED.index );
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// Set font 2 to 10 point type, red and bold
		f2.setFontHeightInPoints((short) 10);
		f2.setColor( HSSFColor.BLUE.index);
		f2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// Set cell style and formatting
		cs.setFont(f);
		cs.setDataFormat(df.getFormat("#,##0.0"));

		// Set the other cell style and formatting
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
		cs2.setFont(f2);


		// Define a few rows
		for(short rownum = (short)0; rownum < 30; rownum++)
		{
			HSSFRow r1 = s.createRow(rownum);
			for(int cellnum = 0; cellnum < 10; cellnum += 2) 
			{
				HSSFCell c1 = r1.createCell(cellnum);
				HSSFCell c2 = r1.createCell(cellnum+1);
				
				
				/*CellStyle style = wb.createCellStyle();
			    style.setBorderBottom(CellStyle.BORDER_THIN);
			    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			    style.setBorderLeft(CellStyle.BORDER_THIN);
			    style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
			    style.setBorderRight(CellStyle.BORDER_THIN);
			    style.setRightBorderColor(IndexedColors.BLUE.getIndex());
			    style.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
			    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			    c1.setCellStyle(style);
			    c2.setCellStyle(style);*/
				c1.setCellValue((double)rownum + (cellnum/10));
				c2.setCellValue(new HSSFRichTextString("Hello! "+cellnum));
			}
		}

		// Save
		FileOutputStream out =  null;;
		try
		{
			out = new FileOutputStream("e://workbook.xls");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			wb.write(out);
			out.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
