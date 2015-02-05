/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日上午1:36:02
 * @version   GTKEncapsulateOOSpinBox V1.0
 */
public class OOSpinBox extends OOWidget
{
	 
	public OOSpinBox(double min,double max, double step)
	{
		setId(GTK.gtk_spin_button_new_with_range(min, max, step));
	}
}
