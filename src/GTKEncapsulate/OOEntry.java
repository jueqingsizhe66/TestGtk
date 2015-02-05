
package GTKEncapsulate;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午10:52:17
 * @version   GTKEncapsulateOOEntry V1.0
 */

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
public class OOEntry extends OOWidget
{
	//目的设置GtkEntry控件的常用方法
	/**
	 * OOEntry的构造函数
	 */
	public OOEntry()
	{
		setId(GTK.gtk_entry_new());
	}
	
	/**
	 * @param length    设置文本框文本最长的输入长度
	 */
	public void setTextMaxLength(int length)
	{
		if(length <= 0)
		{
			throw new IllegalArgumentException("length 不能小于等于0");
		}
		GTK.gtk_entry_set_max_length(getId(), length);
	}
	/**
	 *    获取文本的最长的输入长度
	 */
	public void getTextMaxLength()
	{
		GTK.gtk_entry_get_max_length(getId());
	}
	
	public void setText(String text)
	{
/*		if(text == null)
		{
			GTK.gtk_entry_set_text(getId(),"");
		}else
		{
			GTK.gtk_entry_set_text(getId(),text);
		}*/
		GTK.gtk_entry_set_text(getId(),text==null?"":text);
	}
	/**
	 *   读取文本框的文本
	 */
	public void getText()
	{
		GTK.gtk_entry_get_text(getId());
	}
}
