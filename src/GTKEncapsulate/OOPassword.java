/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������12:52:41
 * @version   GTKEncapsulateOOPassword V1.0
 */
public class OOPassword extends OOEntry
{
	//Ĭ���ǻ���ø���Ĺ��췽��
	
	public OOPassword()
	{
		//super();
		GTK.gtk_entry_set_visibility(this.getId(), false);
	}
	/**
	 * 
	 * @param apple  ���ñ��ܵ�������ʽ
	 */
	public void setInvisibleChar(char apple)
	{
		GTK.gtk_entry_set_invisible_char(this.getId(), apple);
	}
}