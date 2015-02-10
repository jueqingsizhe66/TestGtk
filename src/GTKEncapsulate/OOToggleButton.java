/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午4:27:03
 * @version   GTKEncapsulateOOToggleButton V1.0
 */
public class OOToggleButton extends OOButton
{
	/**
	 * 无参构造函数
	 */
	public OOToggleButton()
	{
		setId(GTK.gtk_toggle_button_new());
	}
	/**
	 * 
	 * @param label      按钮的名字
	 * @param ismnemonic 按钮的快捷键
	 */
   public OOToggleButton(String label,boolean ismnemonic)
    {
        setId(ismnemonic?GTK.gtk_toggle_button_new_with_mnemonic(label)
                :GTK.gtk_toggle_button_new_with_label(label));
    }
   /**
    * 
    * @return 返回按钮是否激活状态
    */
   public boolean getActive()
   {
       return GTK.gtk_toggle_button_get_active(getId());
   }
    /**
     * 
     * @param is_active  设置按钮处于激活状态
     */
   public void setActive(boolean is_active)
   {
       GTK.gtk_toggle_button_set_active(getId(), is_active);
   }
   

}
