/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��6������3:00:26
 * @version   GTKEncapsulateOOComboBox V1.0
 *                                    V2.0  ���ü�������OOCombo
 */
public class OOComboBox extends OOCombo
{
	/**
	 *  ComboBox�Ĺ��캯��
	 */
	public  OOComboBox()
	{
		setId(GTK.gtk_combo_box_text_new()); //����text_new ������gtk_combo_box_new
	}
	/**
	 * 
	 * @param item  ׷���������
	 */
	public void append(String item)
	{
		GTK.gtk_combo_box_text_append_text(this.getId(), item);
	}
	/**
	 * 
	 * @param Id    ׷�����Id��
 	 * @param item  ׷���������
 	 *     ����һ��Item��combobox��
	 */
	public void append(String Id,String item)
	{
		GTK.gtk_combo_box_text_append(this.getId(), Id, item);
	}

	
	/**
	 * 
	 * @return  ��ȡcombobox��ѡ����ı�
	 */
	public String getActiveText()
	{
		return GTK.gtk_combo_box_text_get_active_text(this.getId());
	}
	
	/**
	 *   
	 * @param number  ����ĳ�б�ѡ��
	 */
	public void setActive(int number)
	{
		GTK.gtk_combo_box_set_active(this.getId(), number);
	}
	/**
	 *   Ĭ�����õ�0�б�ѡ��
	 */
	public void setActive()
	{
		GTK.gtk_combo_box_set_active(this.getId(), 0);
	}
	
	/**
	 * 
	 * @param Id ����Id�������Ƿ�ѡ�е�״̬
	 */
	public void setActive(String Id)
	{
		GTK.gtk_combo_box_set_active_id(this.getId(), Id);
	}
	/**
	 * 
	 * @param position  ��ɾ����λ��
	 *   ɾ��ĳ��λ�õ��
	 */
	public void remove(int position)
	{
		GTK.gtk_combo_box_text_remove(this.getId(),position);
	}
	/**
	 *   ȥ�����е�Item��
	 */
	public void removeAll()
	{
		GTK.gtk_combo_box_text_remove_all(this.getId());
	}
}