/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��5������2:21:50
 * @version   GTKEncapsulateOOProgressBar V1.0
 */
public class OOProgressBar extends OOWidget
{
	public OOProgressBar()
	{
		setId(GTK.gtk_progress_bar_new());
	}
	/**
	 * 
	 * @param progress  ���ý���
	 */
	public void setProgress(double progress)
	{
		GTK.gtk_progress_bar_set_fraction(this.getId(), progress);
	}
	/**
	 * 
	 * @param text   �����ı�
	 *            ����showtext������ʾ�ı�
	 */
	public void setText(String text)
	{
		GTK.gtk_progress_bar_set_text(this.getId(), text);
	}
	/**
	 *  
	 * @param text   �����ı��Ŀɼ�
	 */
	public void showText(String text)
	{
		GTK.gtk_progress_bar_set_show_text(this.getId(), true);
	}
}