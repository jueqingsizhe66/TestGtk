/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��4������11:05:00
 * @version   GTKEncapsulateOOButton V1.0
 */
public class OOButton extends OOWidget
{
	public OOButton()
	{
		setId(GTK.gtk_button_new());
	}
	
	/**
	 * 
	 * @param buttonName  �ڶ��ֹ��췽����  ��ť��ʾ������
	 */
	public OOButton(String buttonName)
	{
		setId(GTK.gtk_button_new_with_label(buttonName));
	}
	
	//����Ҫ�¼�����  �Ѿ���widget��������
	
}