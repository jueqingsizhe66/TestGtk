/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午12:52:41
 * @version   GTKEncapsulateOOPassword V1.0
 */
public class OOPassword extends OOEntry
{
	//默认是会调用父类的构造方法
	
	public OOPassword()
	{
		//super();
		GTK.gtk_entry_set_visibility(this.getId(), false);
	}
	/**
	 * 
	 * @param apple  设置保密的密码形式
	 */
	public void setInvisibleChar(char apple)
	{
		GTK.gtk_entry_set_invisible_char(this.getId(), apple);
	}
}
