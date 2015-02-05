/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午4:14:03
 * @version   GTKEncapsulateOOCombo V1.0
 */
public class OOCombo extends OOBin
{
		public OOCombo()
		{
			setId(GTK.gtk_combo_box_new());
		}
		/**
		 *  
		 * @return  返回Active的序号
		 */
		public int getActive()
		{
			return GTK.gtk_combo_box_get_active(this.getId());
		}
		/**
		 * 
		 * @return  获得Actie的Id号
		 */ 
		public String getActiveId()
		{
			return GTK.gtk_combo_box_get_active_id(this.getId());
		}
		/**
		 * 
		 * @param index  设置某个被选中
		 */
		public void setActive(int index)
		{
			GTK.gtk_combo_box_set_active(this.getId(), index);
		}
		/**
		 * 
		 * @param Id    设置某个Id被选中
		 */
		public void setActive(String Id)
		{
			GTK.gtk_combo_box_set_active_id(this.getId(), Id);
		}
		/**
		 * 唯一一个借用我之前写的OOComboBox的地方，其他都和OOComboBoxText不一样
		 * @param callback  回调函数 用于响应Combobox的changed事件
		 */
		public void addChangedListern(IGCallBack callback)
		{
			GTK.g_signal_connect(this.getId(), "changed", callback, null);
		}
}
