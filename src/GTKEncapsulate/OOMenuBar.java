/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������9:56:50
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
		//���ӵ�ˮƽ�Ĳ˵�����
		GTK.gtk_menu_shell_append(this.getId(), apple.getId());
	}
}