/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������10:04:40
 * @version   GTKEncapsulateOOSingleMenu V1.0
 */
public class OOSingleMenu extends OOContainer
{
	public OOSingleMenu()
	{
		setId(GTK.gtk_menu_new());
	}
	
	//�����߲�
	public void addVegetable(OOMenuVegetable tomato)
	{
		tomato.show();
		GTK.gtk_menu_shell_append(this.getId(), tomato.getId());
		//return tomato;
	}
	
}
