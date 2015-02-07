/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月7日下午12:11:33
 * @version   GTKEncapsulateOOMessageDialog V1.0
 */
public class OOMessageDialog extends OODialog
{
	public OOMessageDialog(int parent, int flags,int type, int buttons, String message)
	{
		setId(GTK.gtk_message_dialog_new(parent, flags, type, buttons, message));
	}
	/**
	 * 
	 * @param message  默认输入消息为消息窗口
	 */
	public OOMessageDialog(String message)
	{
		this(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);
	}
	/**
	 * 
	 * @param question  GTK.GTK_MESSAGE_QUESTION
	 * @param yesno      GTK.GTK_BUTTONS_YESNO
	 * @param message
	 */
	public  OOMessageDialog(int question,int yesno,String message)
	{
		this(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,question, yesno,message);
	}
	
	
	public  boolean getInfoResponse()
	{
		int ret  = this.run();
		this.destroy();
		return ret == GTK.GTK_RESPONSE_OK;
	}
	
	public  boolean getQuestionResponse()
	{
		int ret  = this.run();
		this.destroy();
		return ret == GTK.GTK_RESPONSE_YES;
	}
}
