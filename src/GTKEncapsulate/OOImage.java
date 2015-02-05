/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午1:15:05
 * @version   GTKEncapsulateOOImage V1.0
 */
public class OOImage extends OOWidget
{
	public OOImage()
	{
		setId(GTK.gtk_image_new());
	}
	/**
	 * 
	 * @param Filename   硬盘文件名路径
	 *                   显示硬盘图片文件
	 */
	public void setFileImage(String Filename)
	{
		GTK.gtk_image_set_from_file(this.getId(), Filename);
	}
	
	/**
	 * 
	 * @param Filename  一般的形式为  比如GTKEncapsulate下的hunsha.jpg则为 GTKEncapsulate/hunsha.jpg
	 *                  显示项目的资源文件	
	 */
	public void setResourceImage(String Filename)
	{
		GTK.gtk_image_set_from_resource(this.getId(), Filename);
	}
	
	/**
	 * 
	 * @param stock_id   String GTK_STOCK_QUIT     GTK_STOCK_PRINT_PAUSED
	 *                   String GTK_STOCK_EDIT
	 * @param size   int GTK_ICON_SIZE_MENU  int GTK_ICON_SIZE_SMALL_TOOLBAR 
	 *               int GTK_ICON_SIZE_BUTTON   int GTK_ICON_SIZE_DIALOG
	 */
	public void setStockImage(String stock_id,int size)
	{
		GTK.gtk_image_set_from_stock(this.getId(), stock_id, size);
	}
}
