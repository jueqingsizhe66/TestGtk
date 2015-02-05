/**
 * 
 */
package GTKEncapsulate;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��5������2:12:30
 * @version   GTKEncapsulateOOStatusIcon V1.0
 */
public class OOStatusIcon extends  OOWidget
{
	/**
	 *   statusIcon�Ĺ�����
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
	 * @param stock_id    GTK�Դ���ͼƬ�ļ�
	 */
	public void setIconFromStock(String stock_id)
	{
		GTK.gtk_status_icon_set_from_stock(this.getId(), stock_id);
	}
	/**
	 * 
	 * @param filename     Ӳ���ļ�����
	 *              ����ͼƬ��Ӳ���ļ�����
	 */
	public void setIconFromFile(String filename)
	{
		GTK.gtk_status_icon_set_from_file(this.getId(), filename);
	}
	/**
	 * 
	 * @param resource   ��Ŀ�ļ�����Դ
	 */
	public void setIconFromResource(String resource)
	{
		GTK.gtk_status_icon_set_from_resource(this.getId(), resource);
	}
	
	/**
	 * 
	 * @param tipName  ������ʾ������
	 */
	public void setText(String tipName)
	{
		GTK.gtk_status_icon_set_tooltip_text(this.getId(), tipName);
	}
	
	/**
	 * 
	 * @param isVisible  true��ɼ�  false�򲻿ɼ�
	 */
	public void setVisible(boolean isVisible)
	{
		GTK.gtk_status_icon_set_visible(this.getId(), isVisible);
	}
	
	/**
	 * 
	 * @param callback   �Ҽ��˵��ļ�����  
	 */
	public void addPopUpListerner(IGCallBack  callback)
	{
		GTK.g_signal_connect(this.getId(), "popup-menu", callback, null);
	}
}