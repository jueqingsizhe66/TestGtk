
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午5:34:51
 * @version   GTKEncapsulateOODialog V1.0
 */
public class OODialog extends OOWidget
{
	public OODialog()
	{
		setId(GTK.gtk_dialog_new());
	}
	
	
}
