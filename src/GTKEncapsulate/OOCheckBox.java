/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������2:31:05
 * @version   GTKEncapsulateOOCheckBox V1.0  ���ֹ��캯����һ���Ƿ�ѡ�ķ���
 */
public class OOCheckBox extends OOToggleButton
{
	public OOCheckBox()
	{
		setId(GTK.gtk_check_button_new());
	}
	
	public OOCheckBox(String man)
	{
		setId(GTK.gtk_check_button_new_with_label(man));
	}
	
    public OOCheckBox(String label,boolean ismnemonic)
    {
        int id;
        if(ismnemonic)
        {
            id = GTK.gtk_check_button_new_with_mnemonic(label);
        }
        else
        {
            id = GTK.gtk_check_button_new_with_label(label);
        }
        setId(id);
    }
	
/*   ���ڼ̳���OOToggleButton���Կ���ʡȥ����һ��getActive ����Ϊ����togglebutton
 * 	*//**
	 * 
	 * @return  ����CheckButton�Ƿ񱻹�ѡ
	 *//*
	public boolean getActive()
	{
		return GTK.gtk_toggle_button_get_active(this.getId());
	}*/
}