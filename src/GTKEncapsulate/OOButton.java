/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午11:05:00
 * @version   GTKEncapsulateOOButton V1.0
 */
public class OOButton extends OOWidget
{
	public OOButton()
	{
		setId(GTK.gtk_button_new());
	}
	
	/**
	 * 
	 * @param buttonName  第二种构造方法的  按钮显示的名字
	 */
	public OOButton(String buttonName)
	{
		setId(GTK.gtk_button_new_with_label(buttonName));
	}
	
	//不需要事件监听  已经在widget中有体现
	
}
