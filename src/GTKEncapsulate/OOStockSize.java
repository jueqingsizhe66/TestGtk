/**
 * 
 */
package GTKEncapsulate;
import com.rupeng.gtk4j.*;
/**
 * @author    叶昭良
 * @time      2015年2月9日下午4:16:21
 * @version   GTKEncapsulateOOStockSize V1.0
 */
public enum OOStockSize
{
	DND(GTK.GTK_ICON_SIZE_DND),
	MENU(GTK.GTK_ICON_SIZE_MENU),
	SMALLTOOLBAR(GTK.GTK_ICON_SIZE_SMALL_TOOLBAR),
	LARGETOOLBAR(GTK.GTK_ICON_SIZE_LARGE_TOOLBAR),
	BUTTON(GTK.GTK_ICON_SIZE_BUTTON),
	DIALOG(GTK.GTK_ICON_SIZE_DIALOG);
	private int value;
	private OOStockSize(int value)
	{
		this.value = value;
	}
	public int getOOStockSize()
	{
		return this.value;
	}
	
	public OOStockSize parseOOStockSize(int value)
	{
		OOStockSize[] apples = OOStockSize.values();
		for(int i = 0 ; i < apples.length; i++)
		{
			if(value == apples[i].getOOStockSize())
			{
				return apples[i];
			}
		}
		throw new IllegalArgumentException("value =" + value+"不合法参数");
	}
}
