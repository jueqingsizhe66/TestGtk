/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日下午9:56:50
 * @version   GTKEncapsulateOOMenuBar V1.0
 */
public class OOMenuBar extends OOContainer
{
	public OOMenuBar()
	{
		setId(GTK.gtk_menu_bar_new());
	}
	
	public void addSubMenuToBar(OOSubMenu apple)
	{
		//添加到水平的菜单条中
		GTK.gtk_menu_shell_append(this.getId(), apple.getId());
	}
}
