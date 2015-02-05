/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;



/**
 * @author    Ҷ����
 * @time      2015��2��6������2:56:46
 * @version   GTKEncapsulateOORadioButton V1.0
 */
public class OORadioButton extends OOCheckBox
{
	public  OORadioButton(String name)
	{
		//��������Ϊ0  ������c���Ե�gtk+��NULL������
		setId(GTK.gtk_radio_button_new_with_label(0, name));
	}
	
	public void addRaidoButton(String subname)
	{
		GTK.gtk_radio_button_new_with_label_from_widget(this.getId(), subname);
	}
}