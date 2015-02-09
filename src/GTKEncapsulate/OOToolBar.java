/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��5������2:34:00
 * @version   GTKEncapsulateOOToolBar V1.0
 */
public class OOToolBar extends OOContainer
{
	private static OOToolButton[] tbApple = new OOToolButton[10];
	private static int i = 0;
	public OOToolBar()
	{
		setId(GTK.gtk_toolbar_new());
	}
	
	public OOToolButton addTool(String toolName,String toolPic,int position)
	{
		tbApple[i] = new OOToolButton(toolName);
		tbApple[i].show();
		tbApple[i].setStock(toolPic);
		GTK.gtk_toolbar_insert(this.getId(), tbApple[i].getId(), position);
		i++;
		return tbApple[i-1];
		
	}
	
	/*class ToolButton extends OOWidget
	{
		public ToolButton(String toolName)
		{
			setId( GTK.gtk_tool_button_new(0, toolName));
		}
		
		public void setStock(String stock_id)
		{
			GTK.gtk_tool_button_set_stock_id(this.getId(), stock_id);
		}
	}*/
}
