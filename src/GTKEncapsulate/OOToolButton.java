package GTKEncapsulate;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;


/**
 * @author    叶昭良
 * @time      2015年2月9日下午1:59:16
 * @version   GTKEncapsulateOOToolButton V1.0  改进了OOToolBar. 是的OOToolButton对外包可视化
 */
public class OOToolButton extends OOContainer
{
	public OOToolButton(String toolName)
	{
		setId(GTK.gtk_tool_button_new(0, toolName));
	}
	
	public void setStock(String stock_id)
	{
		GTK.gtk_tool_button_set_stock_id(this.getId(), stock_id);
	}
}
