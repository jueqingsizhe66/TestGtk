package GTKEncapsulate;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;


/**
 * @author    Ҷ����
 * @time      2015��2��9������1:59:16
 * @version   GTKEncapsulateOOToolButton V1.0  �Ľ���OOToolBar. �ǵ�OOToolButton��������ӻ�
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