
package GTKEncapsulate;

import java.util.Calendar;
import java.util.Date;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * @author    Ҷ����
 * @time      2015��2��5������3:24:04
 * @version   GTKEncapsulateOOCalender V1.0 �̳���OOWIdget
 *                                     V2.0 �̳���OODialog
 *                                     V3.0 ����������response��������ʲô�������ӵ�OODialog�Ŀؼ�����ʲô���źţ�
 *                                         ����������OOButton�ռ䵽OODialog���������ť������һ��OOResponseTyep
 *                                         ��ok�źžͿ����� this.response(OOResponseType.OK)��ִ��
 *                                         Ȼ����Ϳ���������Ի����run���������ܷ���ֵ���źţ��жϵ���Ҫ��ʲô��Ϊ
 *                                     V4.0 �����ַ��� ����ȫ ���·�װΪ  Date����
 *                                     V5.0 cal.set(year, month+1, day); ��1 ��Ϊmonth��0��ʼ������getMOntH
 */
public class OOCalendar extends OODialog
{
	public OOCalendar()
	{
		setId(GTK.gtk_calendar_new());
	}
	/**
	 * 
	 * @return �����ַ���
	 *   V2.0 �����ַ��� ����ȫ ���·�װΪ  Date����
	 */
	public Date getDate()
	{
		int year = this.getYear();
        //gtk_calendar_get_monthҲ�Ǵ�0��ʼ��
        int month =this.getMonth();
        int day = this.getDay();
         
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
	}
/*	public String getDate()
	{
		
		return "��ǰ����Ϊ"+this.getYear()+"��"+this.getMonth()+"��"+this.getDay()+"��";
	}*/
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