/**
 * 
 */
package GTKEncapsulate;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午2:12:30
 * @version   GTKEncapsulateOOStatusIcon V1.0
 */
public class OOStatusIcon extends  OOWidget
{
	/**
	 *   statusIcon的构造器
	 */
	public OOStatusIcon()
	{
		setId(GTK.gtk_status_icon_new());
	}
	/*
	 * 
	 * public static final String GTK_STOCK_CANCEL = "gtk-cancel";
		public static final String GTK_STOCK_CAPS_LOCK_WARNING = "gtk-caps-lock-warning";
		public static final String GTK_STOCK_CDROM = "gtk-cdrom";
		public static final String GTK_STOCK_CLEAR = "gtk-clear";
		public static final String GTK_STOCK_CLOSE = "gtk-close";
		public static final String GTK_STOCK_COLOR_PICKER = "gtk-color-picker"; ....
	 * */
	/**
	 * 
	 * @param stock_id    GTK自带的图片文件
	 */
	public void setIconFromStock(String stock_id)
	{
		GTK.gtk_status_icon_set_from_stock(this.getId(), stock_id);
	}
	/**
	 * 
	 * @param filename     硬盘文件名字
	 *              设置图片从硬盘文件加载
	 */
	public void setIconFromFile(String filename)
	{
		GTK.gtk_status_icon_set_from_file(this.getId(), filename);
	}
	/**
	 * 
	 * @param resource   项目文件的资源
	 */
	public void setIconFromResource(String resource)
	{
		GTK.gtk_status_icon_set_from_resource(this.getId(), resource);
	}
	
	/**
	 * 
	 * @param tipName  甚至显示的名字
	 */
	public void setText(String tipName)
	{
		GTK.gtk_status_icon_set_tooltip_text(this.getId(), tipName);
	}
	
	/**
	 * 
	 * @param isVisible  true则可见  false则不可见
	 */
	public void setVisible(boolean isVisible)
	{
		GTK.gtk_status_icon_set_visible(this.getId(), isVisible);
	}
	
	/**
	 * 
	 * @param callback   右键菜单的监听器  
	 */
	public void addPopUpListerner(IGCallBack  callback)
	{
		GTK.g_signal_connect(this.getId(), "popup-menu", callback, null);
	}
}
