/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��4������11:57:32
 * @version   GTKEncapsulateOOScrollBar V1.0
 */
public class OOScrollBar extends OOWidget
{
	//ʹ��ʱ ��������ô�С��ͨ��setSize �̳���OOWidget
	public OOScrollBar()
	{
		setId(GTK.gtk_scrolled_window_new());
	}
	
	/**
	 * 
	 * @param textView  ��textView���й�����
	 */
	public void addView(int textView)
	{
		GTK.gtk_container_add(getId(), textView);
	}
}