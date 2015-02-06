/**
 * 
 */
package GTKEncapsulate;



import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��6������9:22:50
 * @version   GTKEncapsulateOOMenuVegetable V1.0
 * 	                   Ϊ����menu���Ա��¼����������������
 */
public class OOMenuVegetable extends OOContainer
{
	private String vegetableName;
	public OOMenuVegetable(String vegetableName)
	{
		this.vegetableName = vegetableName;
		setId(GTK.gtk_menu_item_new_with_label(vegetableName));
	}
	
	public String getText()
	{
		return this.vegetableName;
	}
	public void addActivateListener(IGCallBack callback)
	{
		//this.Id Ҳ������ getId�ķ���  ������û����� getId��Ϊ�������쳣����
		//GTK.g_signal_connect(this.Id, "click", callback, null); 
		GTK.g_signal_connect(this.getId(), "activate", callback, null);
	}
	
	
	
}