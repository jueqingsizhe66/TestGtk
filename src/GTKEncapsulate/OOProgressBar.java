/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午2:21:50
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
	 * @param progress  设置进度
	 */
	public void setProgress(double progress)
	{
		GTK.gtk_progress_bar_set_fraction(this.getId(), progress);
	}
	/**
	 * 
	 * @param text   设置文本
	 *            必须showtext才能显示文本
	 */
	public void setText(String text)
	{
		GTK.gtk_progress_bar_set_text(this.getId(), text);
	}
	/**
	 *  
	 * @param text   设置文本的可见
	 */
	public void showText(String text)
	{
		GTK.gtk_progress_bar_set_show_text(this.getId(), true);
	}
}
