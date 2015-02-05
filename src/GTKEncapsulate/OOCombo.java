/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��6������4:14:03
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
		 * @return  ����Active�����
		 */
		public int getActive()
		{
			return GTK.gtk_combo_box_get_active(this.getId());
		}
		/**
		 * 
		 * @return  ���Actie��Id��
		 */ 
		public String getActiveId()
		{
			return GTK.gtk_combo_box_get_active_id(this.getId());
		}
		/**
		 * 
		 * @param index  ����ĳ����ѡ��
		 */
		public void setActive(int index)
		{
			GTK.gtk_combo_box_set_active(this.getId(), index);
		}
		/**
		 * 
		 * @param Id    ����ĳ��Id��ѡ��
		 */
		public void setActive(String Id)
		{
			GTK.gtk_combo_box_set_active_id(this.getId(), Id);
		}
		/**
		 * Ψһһ��������֮ǰд��OOComboBox�ĵط�����������OOComboBoxText��һ��
		 * @param callback  �ص����� ������ӦCombobox��changed�¼�
		 */
		public void addChangedListern(IGCallBack callback)
		{
			GTK.g_signal_connect(this.getId(), "changed", callback, null);
		}
}