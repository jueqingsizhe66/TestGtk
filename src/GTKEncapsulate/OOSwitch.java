/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��5������3:21:52
 * @version   GTKEncapsulateOOSwitch V1.0
 */
public class OOSwitch extends OOWidget
{
	public OOSwitch()
	{
		setId(GTK.gtk_switch_new());
	}
	
	public void setActive()
	{
		GTK.gtk_switch_set_active(this.getId(), true);
	}
	public void setInActive()
	{
		GTK.gtk_switch_set_active(this.getId(), false);
	}
}
