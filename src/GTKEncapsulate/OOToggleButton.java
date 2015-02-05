/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������4:27:03
 * @version   GTKEncapsulateOOToggleButton V1.0
 */
public class OOToggleButton extends OOButton
{
	/**
	 * �޲ι��캯��
	 */
	public OOToggleButton()
	{
		setId(GTK.gtk_toggle_button_new());
	}
	/**
	 * 
	 * @param label      ��ť������
	 * @param ismnemonic ��ť�Ŀ�ݼ�
	 */
   public OOToggleButton(String label,boolean ismnemonic)
    {
        setId(ismnemonic?GTK.gtk_toggle_button_new_with_mnemonic(label)
                :GTK.gtk_toggle_button_new_with_label(label));
    }
   /**
    * 
    * @return ���ذ�ť�Ƿ񼤻�״̬
    */
   public boolean getActive()
   {
       return GTK.gtk_toggle_button_get_active(getId());
   }
    /**
     * 
     * @param is_active  ���ð�ť���ڼ���״̬
     */
   public void setActive(boolean is_active)
   {
       GTK.gtk_toggle_button_set_active(getId(), is_active);
   }
}