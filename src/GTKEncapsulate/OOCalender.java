
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * @author    叶昭良
 * @time      2015年2月5日下午3:24:04
 * @version   GTKEncapsulateOOCalender V1.0 继承自OOWIdget
 *                                     V2.0 继承自OODialog
 */
public class OOCalender extends OODialog
{
	public OOCalender()
	{
		setId(GTK.gtk_calendar_new());
	}
	/**
	 * 
	 * @return    返回当前选中的天数
	 */
	public int getDay()
	{
		return GTK.gtk_calendar_get_day(this.getId());
	}
	/**
	 * 
	 * @return    返回当前选中的月数
	 */
	public int getMonth()
	{
		return GTK.gtk_calendar_get_month(this.getId());
	}
	/**
	 * 
	 * @return     返回当前选中的年数
	 */
	public int getYear()
	{
	    return GTK.gtk_calendar_get_year(this.getId());
	}
	/**
	 * 
	 * @param callback    天数选择时候的回调函数
	 */
	public void addDaySelectedListener(IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "day-selected", callback, null);
	}
	/**
	 * 
	 * @param callback     双击天数的回调函数
	 */
	public void addDaySelectedDoubleClickedListener(IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "day-selected-double-click", callback, null);
	}
}
