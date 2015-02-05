/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��4������11:31:13
 * @version   GTKEncapsulateOOLabel V1.0
 */
public class OOLabel extends OOWidget
{
	/**
	 * 
	 * @param labelName   label������
	 */
	public OOLabel(String labelName)
	{
		setId(GTK.gtk_label_new(labelName));
	}
	
	/**
	 * 
	 * @param labelName  ����label�ı�ǩ����
	 */
	public void setText(String labelName)
	{
		GTK.gtk_label_set_text(getId(), labelName);
	}
	/**
	 * 
	 * @return  ����label������
	 */
	public String getText()
	{
		return GTK.gtk_label_get_text(getId());
	}
}