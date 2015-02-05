
package GTKEncapsulate;

/**
 * @author    Ҷ����
 * @time      2015��2��4������10:52:17
 * @version   GTKEncapsulateOOEntry V1.0
 */

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
public class OOEntry extends OOWidget
{
	//Ŀ������GtkEntry�ؼ��ĳ��÷���
	/**
	 * OOEntry�Ĺ��캯��
	 */
	public OOEntry()
	{
		setId(GTK.gtk_entry_new());
	}
	
	/**
	 * @param length    �����ı����ı�������볤��
	 */
	public void setTextMaxLength(int length)
	{
		if(length <= 0)
		{
			throw new IllegalArgumentException("length ����С�ڵ���0");
		}
		GTK.gtk_entry_set_max_length(getId(), length);
	}
	/**
	 *    ��ȡ�ı���������볤��
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
	 *   ��ȡ�ı�����ı�
	 */
	public void getText()
	{
		GTK.gtk_entry_get_text(getId());
	}
}