import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * 
 */

/**
 * @author    叶昭良
 * @time      2015年2月1日下午3:54:46
 * @version   TestDialog V1.0
 *             TestDialog V2.0    在errorMessage 变为2层嵌套messagebox 并用GTK_WIDGET_SET_TITLE..设置标题
 */
public class TestDialog
{

	/**
	 * @param args
	 */
	static int window;  
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_widget_show(window);
		GTK.gtk_widget_set_size_request(window,500,500);
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
		//创建网格布局
		gridHouse = GTK.gtk_grid_new();
		
		//创建button控件
		int btnShowDialog = GTK.gtk_button_new_with_label("show Dialog");
		int btnShowMessage = GTK.gtk_button_new_with_label("show MessageBox");
		int btnErrorMessage = GTK.gtk_button_new_with_label("show error");
		int btnQuestionMessage = GTK.gtk_button_new_with_label("show question");
		//添加控件
		GTK.gtk_grid_attach(gridHouse, btnShowDialog, 0, 0, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnShowMessage, 1, 0, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnErrorMessage, 0, 1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnQuestionMessage, 1, 1, 1, 1);
		GTK.gtk_container_add(window, gridHouse);
		
		//显示控件
		GTK.gtk_widget_show(btnShowDialog);
		GTK.gtk_widget_show(btnShowMessage);
		GTK.gtk_widget_show(btnErrorMessage);
		GTK.gtk_widget_show(btnQuestionMessage);
		GTK.gtk_widget_show(gridHouse);
		//添加事件
		//基本对话框事件
		GTK.g_signal_connect(btnShowDialog, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				int dlApple = GTK.gtk_dialog_new();
				//GTK.gtk_widget_show(dlApple); //需要添加  否则无法显示
				GTK.gtk_dialog_add_button(dlApple, "北京欢迎你", 0);
				
				//int btnBanana = GTK.gtk_button_new_with_label("tryut");
				//GTK.gtk_container_add(dlApple, btnBanana);
				//类似于 gkt_main()的作用 启动dialog循环
				GTK.gtk_dialog_run(dlApple); //
				GTK.gtk_widget_destroy(dlApple);
				
			}
		}, null);
		//显示消息信息对话框
		GTK.g_signal_connect(btnShowMessage, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//flags：一般取GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL；
				//type:  GTK_MESSAGE_INFO：消息；
				//       GTK_MESSAGE_WARNING：警告；；
				//       GTK_MESSAGE_QUESTION：提问
				//       GTK_MESSAGE_ERROR：错误。
				//button：  GTK_BUTTONS_OK：确定；GTK_BUTTONS_CLOSE：关闭；  YES NO.. CANCEL
				//message:  字符串
				int dlApple = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL, 
						GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK, "您好");

				//GTK.gtk_widget_show(dlApple); //需要添加  否则无法显示
				GTK.gtk_dialog_add_button(dlApple, "北京欢迎你", 0);
				
				//类似于 gkt_main()的作用 启动dialog循环
				int ret = GTK.gtk_dialog_run(dlApple); //
				if(ret == GTK.GTK_RESPONSE_OK)
				{
					System.out.println("您输入了回车");
					//GTK.gtk_dialog_response(dlApple, ret);
					GTK.gtk_widget_destroy(dlApple); //用于销毁对话框
				}
			}
		}, null);
		
		GTK.g_signal_connect(btnErrorMessage, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//flags：一般取GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL；
				//type影响到形状
				//type:  GTK_MESSAGE_INFO：消息；
				//       GTK_MESSAGE_WARNING：警告；；
				//       GTK_MESSAGE_QUESTION：提问
				//       GTK_MESSAGE_ERROR：错误。
				//button影响到事件
				//button：  GTK_BUTTONS_OK：确定；GTK_BUTTONS_CLOSE：关闭；  YES NO.. CANCEL
				//message:  字符串
				int dlApple = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL, 
						GTK.GTK_MESSAGE_ERROR, GTK.GTK_BUTTONS_YES_NO, "您的系统需要降级");
	
				//类似于 gkt_main()的作用 启动dialog循环
				int ret = GTK.gtk_dialog_run(dlApple); //
				if(ret == GTK.GTK_RESPONSE_YES)
				{
//					System.out.println("您输入了yes");
					int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
							GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,"你真的降了啊！２了八鸡！");
					GTK.gtk_window_set_title(msgDlg, "儿了吧唧");
					GTK.gtk_dialog_run(msgDlg);
					GTK.gtk_widget_destroy(msgDlg);
					//GTK.gtk_widget_destroy(dlApple);
				}else if(ret == GTK.GTK_RESPONSE_NO)
				{
					//采用方法进行封装。。。 简化了很多代码
					showInfo("你真的不降？ 我会一直烦你的","烦死你");
					//GTK.gtk_widget_destroy(dlApple);
				}
				GTK.gtk_widget_destroy(dlApple);
			}
		}, null);
		
		
		GTK.g_signal_connect(btnQuestionMessage, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//flags：一般取GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL；
				//type影响到形状
				//type:  GTK_MESSAGE_INFO：消息；
				//       GTK_MESSAGE_WARNING：警告；；
				//       GTK_MESSAGE_QUESTION：提问
				//       GTK_MESSAGE_ERROR：错误。
				//button影响到事件
				//button：  GTK_BUTTONS_OK：确定；GTK_BUTTONS_CLOSE：关闭；  YES NO.. CANCEL
				//message:  字符串
				int dlApple = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL, 
						GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_OK_CANCEL, "是不是看毛片了？");
	
				//类似于 gkt_main()的作用 启动dialog循环
				int ret = GTK.gtk_dialog_run(dlApple); //
				if(ret == GTK.GTK_RESPONSE_CANCEL)
				{
					System.out.println("您输入了CANCEL");
//					GTK.gtk_widget_destroy(dlApple);
				}else if(ret == GTK.GTK_RESPONSE_OK)
				{
					System.out.println("您输入了OK");
//					GTK.gtk_widget_destroy(dlApple);
				}/*else if(ret == GTK.GTK_RESPONSE_DELETE_EVENT) //点击关闭事件的。。不需要判断
				{
					System.out.println("您关闭了对话框");
				}*/
				GTK.gtk_widget_destroy(dlApple);
			}
		}, null);
		GTK.gtk_main();
	}
	public static boolean showInfo(  String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}
	
	public static boolean showYesNo( String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_YES_NO,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret=GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_YES;
	}
	public static boolean showOkCancel( String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_OK_CANCEL,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}

}
