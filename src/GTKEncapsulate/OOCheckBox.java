/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午2:31:05
 * @version   GTKEncapsulateOOCheckBox V1.0  两种构造函数和一个是否勾选的方法
 */
public class OOCheckBox extends OOContainer
{
	public OOCheckBox()
	{
		setId(GTK.gtk_check_button_new());
	}
	
	public OOCheckBox(String man)
	{
		setId(GTK.gtk_check_button_new_with_label(man));
	}
	
	/**
	 * 
	 * @return  返回CheckButton是否被勾选
	 */
	public boolean getActive()
	{
		return GTK.gtk_toggle_button_get_active(this.getId());
	}
}
