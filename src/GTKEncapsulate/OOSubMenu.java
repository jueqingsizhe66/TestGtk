/**
 * 
 */
package GTKEncapsulate;

import GTKEncapsulate.OOMenu.Menua;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日下午10:02:58
 * @version   GTKEncapsulateOOSubMenu V1.0
 */
public class OOSubMenu extends OOContainer
{
	public OOSubMenu(String vertivalMenuName)
	{
		setId(GTK.gtk_menu_item_new_with_mnemonic(vertivalMenuName));
	}
	//导入Menu对象！
	//必须在addSubMenuToMenuBar之前添加fillSubMenu的调用	
	public void fillSubMenu(OOSingleMenu menuApple)
	{
		this.show();
		GTK.gtk_menu_item_set_submenu(this.getId(), menuApple.getId());
	}
}
