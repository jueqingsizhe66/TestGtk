package GTKEncapsulate;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午10:30:18
 * @version   GTKEncapsulateOOEntry V1.0  显示、隐藏、摧毁； 两个事件监听；最为基本的setID getID
 *                                  V2.0  修改了click时间的信号从click到clicked
 *                                  V3.0  把OOWidget 标记为抽象类！ 这样防止用户
 *                                        直接OOWidget ow = new OOWidget() ; ow.show()
 *                                        这完全是无意义的工作
 *                                  V4.0  注释掉MessageDialog的过程，这部分在新的OODialog已经有了
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
public abstract class OOWidget
{
	//每一个控件都需要通过控件句柄进行唯一的标识
	private int Id;
	public IGCallBack IGCquit = new IGCallBack()
	{
		
		@Override
		public void execute(int instance, int eventData, Object object)
		{
			// TODO Auto-generated method stub
			GTK.gtk_main_quit();
		}
	};
	

	/**
	 * 
	 * @return  返回GTKWidget的ID或者叫做对象标识  ，C语言的指针 句柄
	 */
	public int getId()
	{
		if(this.Id == 0)
		{
			throw new IllegalArgumentException();
		}
		return this.Id;
	}
	
	////protected只能被子类或者同包(package)中的类访问，其他都不可以访问
	// 高，妙。。。
	//public void setId(int Id)
	protected  void setId(int Id)
	{
		this.Id = Id;
	}
	
	//封装常用的widget功能 ，讨厌下划线
	//设置类
	//------------------------------------------------
	/**
	 * 
	 * @param width     设置控件的宽度
	 * @param height    设置空间的高度
	 * 
	 */
	public void setWidgetSize(int width, int height)
	{
		GTK.gtk_widget_set_size_request(this.Id, width, height);
	}
	
	/**
	 * 
	 * @param isSensitive   设置控件是否启用（还是禁用）
	 */
	public void setWidgetSensitive(boolean isSensitive)
	{
		GTK.gtk_widget_set_sensitive(this.Id, isSensitive);
	}
	// 显示   隐藏   摧毁 类 
	//------------------------------------------------
	/**
	 *   显示控件
	 */
	public void show()
	{
		GTK.gtk_widget_show(this.getId());
	}
	/**
	 *   隐藏控件
	 */
	public void hide()
	{
		GTK.gtk_widget_show(getId());
	}
	
	/**
	 *    摧毁控件
	 */
	public void destroy()
	{
		GTK.gtk_widget_destroy(getId());
	}
	
	
	// 监听事件类 ,的确是体现着封装的思想
	// add***Listener  增加了监听器
	//------------------------------------------------
	/**
	 * 
	 * @param callback   一个IGCallback的会掉泪对象  需要实现execute方法
	 *                   增加clicked的控件事件监听，所有的widget
	 *                   都可以增加事件监听
	 */
	public void addClickedListener(IGCallBack callback)
	{
		//this.Id 也可以用 getId的方法  但是最好还是用 getId因为里面有异常机制
		//GTK.g_signal_connect(this.Id, "click", callback, null); 
		GTK.g_signal_connect(this.getId(), "clicked", callback, null);
	}
	/**
	 * 
	 * @param callback   一个IGCallback的会掉泪对象  需要实现execute方法
	 *                   增加控件的destroy的事件监听
	 */
	public void addDestroyListener(IGCallBack callback)
	{
		GTK.g_signal_connect(getId(),"destroy",callback,null);
	}
	
/*	//////////我想着每一个空间应该都是能够创建消息的小窗口的，当你输入不对的时候
	*//**
	 * 
	 * @param message    消息窗口的消息
	 * @param title      消息窗口的消息
	 * @return           弹出一个消息窗口  返回值为ok
	 *//*
	public static boolean showInfo(  String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}
	*//**
	 * 
	 * @param message    问题窗口的消息
	 * @param title      问题窗口的消息
	 * @return           弹出一个YESNO 问题窗口  范围值为是不是
	 *//*
	public static boolean showYesNo( String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_YES_NO,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret=GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_YES;
	}
	*//**
	 * 
	 * @param message    确认窗口的消息
	 * @param title      确认窗口的消息
	 * @return           弹出一个OKCANCEL 确认窗口  返回值为是否确认
	 *//*
	public static boolean showOkCancel( String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_OK_CANCEL,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}
*/
}
