/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午3:58:13
 * @version   GTKEncapsulateOOStockImage V1.0  添加了常用的控件图片
 */
public enum OOStockImage
{
	ABOUT(GTK.GTK_STOCK_ABOUT),
	CANCEL(GTK.GTK_STOCK_CANCEL),
	CLOSE(GTK.GTK_STOCK_CLOSE),
	COPY(GTK.GTK_STOCK_COPY),
	CUT(GTK.GTK_STOCK_CUT),
	DELETE(GTK.GTK_STOCK_DELETE),
	EDIT(GTK.GTK_STOCK_EDIT),
	FILE(GTK.GTK_STOCK_FILE),
	FIND(GTK.GTK_STOCK_FIND),
	FINDREPLACE(GTK.GTK_STOCK_FIND_AND_REPLACE),
	FULLSCREEN(GTK.GTK_STOCK_FULLSCREEN),
	HELP(GTK.GTK_STOCK_HELP),
	ITATIC(GTK.GTK_STOCK_ITALIC),
	HOME(GTK.GTK_STOCK_HOME),
	NEW(GTK.GTK_STOCK_NEW),
	NO(GTK.GTK_STOCK_NO),
	OPEN(GTK.GTK_STOCK_OPEN),
	PASTE(GTK.GTK_STOCK_PASTE),
	PRINT(GTK.GTK_STOCK_PRINT),
	QUIT(GTK.GTK_STOCK_QUIT),
	REDO(GTK.GTK_STOCK_REDO),
	SAVE(GTK.GTK_STOCK_SAVE),
	//dialog
	WARNING(GTK.GTK_STOCK_DIALOG_WARNING),
	INFO(GTK.GTK_STOCK_DIALOG_INFO),
	QUESTION(GTK.GTK_STOCK_DIALOG_QUESTION),
	ERROR(GTK.GTK_STOCK_DIALOG_ERROR);
	
	private String stockImage;
	private OOStockImage(String stockImage)
	{
		this.stockImage = stockImage;
	}
	public String getStockImage()
	{
		return this.stockImage;
	}
	public OOStockImage parseOOStockImage(String stockImage)
	{
		OOStockImage[] apples = OOStockImage.values();
		for(int i = 0 ;i < apples.length;i++)
		{
			if(stockImage.equalsIgnoreCase(apples[i].getStockImage()))
			{
				return apples[i];
			}
		}
		throw new IllegalArgumentException("stockImage=" +stockImage+"不合法的图片");
	}
}
