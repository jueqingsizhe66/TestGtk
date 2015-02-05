/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;



/**
 * @author    叶昭良
 * @time      2015年2月6日上午2:56:46
 * @version   GTKEncapsulateOORadioButton V1.0
 */
public class OORadioButton extends OOCheckBox
{
	public  OORadioButton(String name)
	{
		//必须设置为0  类似于c语言的gtk+的NULL的设置
		setId(GTK.gtk_radio_button_new_with_label(0, name));
	}
	
	public void addRaidoButton(String subname)
	{
		GTK.gtk_radio_button_new_with_label_from_widget(this.getId(), subname);
	}
}
