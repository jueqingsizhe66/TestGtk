/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午2:34:00
 * @version   GTKEncapsulateOOToolBar V1.0
 *                             V2.0 利用OOStockImage改进了addTool 方法
 */
public class OOToolBar extends OOContainer
{
	private static OOToolButton[] tbApple = new OOToolButton[10];
	private static int i = 0;
	public OOToolBar()
	{
		setId(GTK.gtk_toolbar_new());
	}
	/**
	 * 
	 * @param toolName
	 * @param toolPic    OOStockImage枚举类的改进
	 * @param position   tool在toolbar的位置
	 * @return
	 */
	public OOToolButton addTool(String toolName,OOStockImage toolPic,int position)
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
