/**
 * 
 */
package GTKEncapsulate;

import GTKEncapsulate.OOMenu.Menua;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������10:02:58
 * @version   GTKEncapsulateOOSubMenu V1.0
 */
public class OOSubMenu extends OOContainer
{
	public OOSubMenu(String vertivalMenuName)
	{
		setId(GTK.gtk_menu_item_new_with_mnemonic(vertivalMenuName));
	}
	//����Menu����
	//������addSubMenuToMenuBar֮ǰ����fillSubMenu�ĵ���	
	public void fillSubMenu(OOSingleMenu menuApple)
	{
		this.show();
		GTK.gtk_menu_item_set_submenu(this.getId(), menuApple.getId());
	}
}