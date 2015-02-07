
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午5:34:51
 * @version   GTKEncapsulateOODialog V1.0
 *                                V2.0修改 为OOWindow 使他变成单容器控件
 */
public class OODialog extends OOWindow
{
	public OODialog()
	{
		setId(GTK.gtk_dialog_new());
	}
	/**
	 *    在一切设置完毕后  必须要让他run起来，类似于线程的做法,并且一定要摧毁它 this.destroy..
	 */
	public  int run()
	{
		int response =  GTK.gtk_dialog_run(this.getId());
		return response;
	}
	
}
