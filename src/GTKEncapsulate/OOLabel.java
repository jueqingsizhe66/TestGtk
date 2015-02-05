/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午11:31:13
 * @version   GTKEncapsulateOOLabel V1.0
 */
public class OOLabel extends OOWidget
{
	/**
	 * 
	 * @param labelName   label的名字
	 */
	public OOLabel(String labelName)
	{
		setId(GTK.gtk_label_new(labelName));
	}
	
	/**
	 * 
	 * @param labelName  设置label的标签名字
	 */
	public void setText(String labelName)
	{
		GTK.gtk_label_set_text(getId(), labelName);
	}
	/**
	 * 
	 * @return  返回label的名字
	 */
	public String getText()
	{
		return GTK.gtk_label_get_text(getId());
	}
}
