/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��9������3:47:17
 * @version   GTKEncapsulateOOToolItem V1.0  ������OOToolButton  ��ͬ�Ĺ����ǿ�������ʾ������
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