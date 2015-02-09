/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月7日下午12:11:33
 * @version   GTKEncapsulateOOMessageDialog V1.0
 *                                          V2.0 利用改进后的OOResponseType类型 显得更加面向对象化
 *                                          V3.0  利用增加的OOMessageType and OOButtonsType to  modify the OODialog
 */
public class OOMessageDialog extends OODialog
{
	public OOMessageDialog(int parent, int flags,OOMessageType type, OOButtonsType buttons, String message)
	{
		setId(GTK.gtk_message_dialog_new(parent, flags, type.getValue(), buttons.getValue(), message));
	}
	/**
	 * 
	 * @param message  默认输入消息为消息窗口
	 */
	public OOMessageDialog(String message)
	{
	/*	this(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);*/
		this(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL,OOMessageType.INFO,OOButtonsType.OK,message);
	}
	/**
	 * 
	 * @param question  GTK.GTK_MESSAGE_QUESTION
	 * @param yesno      GTK.GTK_BUTTONS_YESNO
	 * @param message
	 */
	public  OOMessageDialog(OOMessageType question,OOButtonsType yesno,String message)
	{
		this(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,question, yesno,message);
	}
	
	public static void showInfo(String message,String title)
	{
		OOMessageDialog msg = new OOMessageDialog(message);
		msg.setTitle(title);
		msg.run();
		msg.destroy();
	}
	public static void showError(String message,String title)
	{
		OOMessageDialog err = new OOMessageDialog(message);
		err.setTitle(title);
		err.run();
		err.destroy();
	}
	
	public static boolean showYesNo(String message,String title)
	{
		OOMessageDialog ques = new OOMessageDialog(OOMessageType.QUESTION, OOButtonsType.YESNO, message);
		ques.setTitle(title);
		OOResponseType os = ques.run();
		ques.destroy();
		return os == OOResponseType.YES;
	}
	public static boolean showOKCANCEL(String message,String title)
	{
		OOMessageDialog ques = new OOMessageDialog(OOMessageType.QUESTION, OOButtonsType.OKCANCEL, message);
		ques.setTitle(title);
		OOResponseType os = ques.run();
		ques.destroy();
		return os == OOResponseType.OK;
	}
/*	public  boolean getInfoResponse()
	{
		
		int ret  = this.run().getValue();
		this.destroy();
		//利用枚举类  OOResponseType
		//return ret == GTK.GTK_RESPONSE_OK;
		return ret == OOResponseType.OK.getValue();
	}
	
	public  boolean getQuestionResponse()
	{
		int ret  = this.run().getValue();
		this.destroy();
		//return ret == GTK.GTK_RESPONSE_YES;
		return ret == OOResponseType.YES.getValue();
	}
	*/
}
