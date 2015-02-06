/**
 * 
 */
package GTKEncapsulate;



import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月6日下午9:22:50
 * @version   GTKEncapsulateOOMenuVegetable V1.0
 * 	                   为了让menu可以被事件监听增加了这个类
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
		//this.Id 也可以用 getId的方法  但是最好还是用 getId因为里面有异常机制
		//GTK.g_signal_connect(this.Id, "click", callback, null); 
		GTK.g_signal_connect(this.getId(), "activate", callback, null);
	}
	
	
	
}
