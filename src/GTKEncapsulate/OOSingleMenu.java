/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日下午10:04:40
 * @version   GTKEncapsulateOOSingleMenu V1.0
 */
public class OOSingleMenu extends OOContainer
{
	public OOSingleMenu()
	{
		setId(GTK.gtk_menu_new());
	}
	
	//添加蔬菜
	public void addVegetable(OOMenuVegetable tomato)
	{
		tomato.show();
		GTK.gtk_menu_shell_append(this.getId(), tomato.getId());
		//return tomato;
	}
	
}
