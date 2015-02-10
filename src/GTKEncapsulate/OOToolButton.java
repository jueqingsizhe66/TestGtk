package GTKEncapsulate;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;


/**
 * @author    叶昭良
 * @time      2015年2月9日下午1:59:16
 * @version   GTKEncapsulateOOToolButton V1.0  改进了OOToolBar. 是的OOToolButton对外包可视化
 *                    V2.0  改进了构造函数的方法  利用this函数进行重新实现
 *                          增加了setlabel方法  SetIConWidget方法  利用OOStockImage改进了SetStockImage方法
 *                         
 */
public class OOToolButton extends OOContainer
{
	/**
	 * V2.0  新的构造方法
	 * @param icon_widget
	 * @param label
	 */
	public OOToolButton(OOWidget icon_widget,String label)
	{
		//如果为0 则工具栏控件没有图片
		int icon_widgetID = icon_widget==null?0:icon_widget.getId();
		GTK.gtk_tool_button_new(icon_widgetID, label);
	}
	/*V1.0
	 * public OOToolButton(String toolName)
	{
		setId(GTK.gtk_tool_button_new(0, toolName));
	}*/
	public OOToolButton(String label)
	{
		this(null,label);
	}
	/**
	 * 
	 * @param stock_id    OOStockImage的枚举类对象  减少用户输入的错误
	 */
	public void setStock(OOStockImage stock_id)
	{
		GTK.gtk_tool_button_set_stock_id(this.getId(), stock_id.getStockImage());
	}
	
	
}
