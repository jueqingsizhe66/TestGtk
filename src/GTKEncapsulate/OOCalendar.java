
package GTKEncapsulate;

import java.util.Calendar;
import java.util.Date;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * @author    叶昭良
 * @time      2015年2月5日下午3:24:04
 * @version   GTKEncapsulateOOCalender V1.0 继承自OOWIdget
 *                                     V2.0 继承自OODialog
 *                                     V3.0 终于明白了response的作用是什么，让添加到OODialog的控件具有什么的信号！
 *                                         比如你添加OOButton空间到OODialog，当点击按钮，发出一个OOResponseTyep
 *                                         的ok信号就可以用 this.response(OOResponseType.OK)来执行
 *                                         然后你就可以让这个对话框给run起来，接受返回值的信号！判断到底要做什么行为
 *                                     V4.0 发现字符串 不安全 重新封装为  Date对象
 *                                     V5.0 cal.set(year, month+1, day); 加1 因为month从0开始，放在getMOntH
 */
public class OOCalendar extends OODialog
{
	public OOCalendar()
	{
		setId(GTK.gtk_calendar_new());
	}
	/**
	 * 
	 * @return 返回字符串
	 *   V2.0 发现字符串 不安全 重新封装为  Date对象
	 */
	public Date getDate()
	{
		int year = this.getYear();
        //gtk_calendar_get_month也是从0开始的
        int month =this.getMonth();
        int day = this.getDay();
         
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
	}
/*	public String getDate()
	{
		
		return "当前日期为"+this.getYear()+"年"+this.getMonth()+"月"+this.getDay()+"日";
	}*/
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
