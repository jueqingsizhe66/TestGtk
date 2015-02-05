/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午3:00:26
 * @version   GTKEncapsulateOOComboBox V1.0
 *                                    V2.0  设置集成来自OOCombo
 */
public class OOComboBox extends OOCombo
{
	/**
	 *  ComboBox的构造函数
	 */
	public  OOComboBox()
	{
		setId(GTK.gtk_combo_box_text_new()); //利用text_new 而不是gtk_combo_box_new
	}
	/**
	 * 
	 * @param item  追加项的名称
	 */
	public void append(String item)
	{
		GTK.gtk_combo_box_text_append_text(this.getId(), item);
	}
	/**
	 * 
	 * @param Id    追加项的Id号
 	 * @param item  追加项的名字
 	 *     添加一个Item到combobox中
	 */
	public void append(String Id,String item)
	{
		GTK.gtk_combo_box_text_append(this.getId(), Id, item);
	}

	
	/**
	 * 
	 * @return  获取combobox被选择的文本
	 */
	public String getActiveText()
	{
		return GTK.gtk_combo_box_text_get_active_text(this.getId());
	}
	
	/**
	 *   
	 * @param number  设置某行被选中
	 */
	public void setActive(int number)
	{
		GTK.gtk_combo_box_set_active(this.getId(), number);
	}
	/**
	 *   默认设置第0行被选中
	 */
	public void setActive()
	{
		GTK.gtk_combo_box_set_active(this.getId(), 0);
	}
	
	/**
	 * 
	 * @param Id 根据Id号设置是否选中的状态
	 */
	public void setActive(String Id)
	{
		GTK.gtk_combo_box_set_active_id(this.getId(), Id);
	}
	/**
	 * 
	 * @param position  待删除的位置
	 *   删除某个位置的项。
	 */
	public void remove(int position)
	{
		GTK.gtk_combo_box_text_remove(this.getId(),position);
	}
	/**
	 *   去除所有的Item项
	 */
	public void removeAll()
	{
		GTK.gtk_combo_box_text_remove_all(this.getId());
	}
}
