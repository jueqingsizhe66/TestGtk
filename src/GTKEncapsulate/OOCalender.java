
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * @author    Ҷ����
 * @time      2015��2��5������3:24:04
 * @version   GTKEncapsulateOOCalender V1.0
 */
public class OOCalender extends OOWidget
{
	public OOCalender()
	{
		setId(GTK.gtk_calendar_new());
	}
	/**
	 * 
	 * @return    ���ص�ǰѡ�е�����
	 */
	public int getDay()
	{
		return GTK.gtk_calendar_get_day(this.getId());
	}
	/**
	 * 
	 * @return    ���ص�ǰѡ�е�����
	 */
	public int getMonth()
	{
		return GTK.gtk_calendar_get_month(this.getId());
	}
	/**
	 * 
	 * @return     ���ص�ǰѡ�е�����
	 */
	public int getYear()
	{
	    return GTK.gtk_calendar_get_year(this.getId());
	}
	/**
	 * 
	 * @param callback    ����ѡ��ʱ��Ļص�����
	 */
	public void addDaySelectedListener(IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "day-selected", callback, null);
	}
	/**
	 * 
	 * @param callback     ˫�������Ļص�����
	 */
	public void addDaySelectedDoubleClickedListener(IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "day-selected-double-click", callback, null);
	}
}