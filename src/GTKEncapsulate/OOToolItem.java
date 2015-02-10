/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午3:47:17
 * @version   GTKEncapsulateOOToolItem V1.0  类似于OOToolButton  不同的功能是可以有提示！！！
 */
public class OOToolItem extends OOBin
{
	public OOToolItem(int id)
	{
		setId(id);
	}
	public OOToolItem()
	{
		setId(GTK.gtk_tool_item_new());
	}
	
	public void setToolTip(String text)
	{
		GTK.gtk_tool_item_set_tooltip_text(this.getId(), text);
	}
}
