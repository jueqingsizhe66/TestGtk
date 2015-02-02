import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * 
 */

/**
 * @author    叶昭良
 * @time      2015年2月3日上午1:24:01
 * @version   TestToolBar V1.0
 * @version   TestToolBar V2.0  增加了一个日历控件,添加了天数变化时间和双击时间
 * @version   TestToolBar V2.0  增加了进度条的创建，和按钮事件 控制进度条
 * @version   TestToolBar V4.0  建议的开关按钮，未实现信号的接受。。。
 * @version   TestToolBar V5.0  增加了一个菜单控件
 * @version   TestToolBar V6.0  增加了一个StatusIcon的简单控件	
 */
public class TestToolBar
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static double progress = 0.0;
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		window= GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_widget_show(window);
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				GTK.gtk_main_quit();
			}
		}, null);
		
		//设置网格布局
		gridHouse = GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		GTK.gtk_container_add(window, gridHouse);
		
		//定义一些常用的控件
		int start = 0;
		createToolbar(gridHouse,start);
		start =1;
		createCalendar(gridHouse,start);
		start = 2;
		createProgressBar(gridHouse, start);
		start = 3;
		createSwitchOn(gridHouse, start);
		start = 4;
		createMenu(gridHouse,start);
		start = 5;
		createStatusIcon(gridHouse,start);
		//启动循环
		GTK.gtk_main();
	}
	
	public static void createToolbar(int gridHouse,int start)
	{
		//创建工具栏容器
		int tbApple = GTK.gtk_toolbar_new();
		GTK.gtk_widget_show(tbApple);
		GTK.gtk_widget_set_size_request(tbApple, 300, 20);
		GTK.gtk_grid_attach(gridHouse, tbApple, 0, start, 1, 1);
		
	  int btnNew = GTK.gtk_tool_button_new(0, "新建");
	  GTK.gtk_tool_button_set_stock_id(btnNew, GTK.GTK_STOCK_NEW);
	  GTK.gtk_widget_show(btnNew);
	  GTK.gtk_toolbar_insert(tbApple, btnNew, 0);
		   
	  int btnOpen = GTK.gtk_tool_button_new(0, "打开");
	  GTK.gtk_tool_button_set_stock_id(btnOpen, GTK.GTK_STOCK_OPEN);
	  GTK.gtk_widget_show(btnOpen);
	  GTK.gtk_toolbar_insert(tbApple, btnOpen, 1);
	  
	  int btnSave = GTK.gtk_tool_button_new(0, "保存");
	  GTK.gtk_tool_button_set_stock_id(btnSave, GTK.GTK_STOCK_SAVE);
	  GTK.gtk_widget_show(btnSave);
	  GTK.gtk_toolbar_insert(tbApple, btnSave, 2);
	}
	
	public static void createCalendar(int gridHouse, int start) 
	{
		final int clApple = GTK.gtk_calendar_new();
		int labelApple = GTK.gtk_label_new("");
		GTK.gtk_widget_show(clApple);
		GTK.gtk_widget_show(labelApple);
		GTK.gtk_grid_attach(gridHouse, clApple, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, clApple, 1, start, 1, 1);
		GTK.gtk_widget_set_size_request(clApple, 200, 200);
		
		GTK.g_signal_connect(clApple, "day-selected", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				int day = GTK.gtk_calendar_get_day(clApple);
				int month = GTK.gtk_calendar_get_month(clApple);
				int year = GTK.gtk_calendar_get_year(clApple);
				System.out.println("用户选择了"+year+"年"+month+"月"+day+"日");
				//showInfo("用户选择了"+year+"年"+month+"月"+day+"日","选择的日期");
			}
		}, null);
		GTK.g_signal_connect(clApple, "day-selected-double-click", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				int day = GTK.gtk_calendar_get_day(clApple);
				int month = GTK.gtk_calendar_get_month(clApple);
				int year = GTK.gtk_calendar_get_year(clApple);
				showInfo("用户选择了"+year+"年"+month+"月"+day+"日","选择的日期");
			}
		}, null);
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
	
	public static void createProgressBar(int gridHouse,int start)
	{
		final int pbApple =GTK.gtk_progress_bar_new();
		int btnApple =GTK.gtk_button_new_with_label("加快进度");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_widget_show(pbApple);
		GTK.gtk_grid_attach(gridHouse, pbApple, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnApple, 1, start, 1, 1);
		
		GTK.gtk_progress_bar_set_fraction(pbApple, progress);
			//GTK.gtk_progress_bar_set_pulse_step()
/*			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		GTK.g_signal_connect(btnApple, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				if(progress <= 1.0)
				{
					progress = progress + 0.2;
					GTK.gtk_progress_bar_set_fraction(pbApple, progress);
				}else
				{
					System.out.println("stop clicked");
				}
				
			}
		}, null);
		
		GTK.gtk_progress_bar_set_text(pbApple, "请稍后");
	}
	
	public static void createSwitchOn(int gridHouse, int start)
	{
		final int switchOff = GTK.gtk_switch_new();
		final int label = GTK.gtk_label_new("");
		GTK.gtk_widget_show(switchOff);
		GTK.gtk_widget_show(label);
		GTK.gtk_grid_attach(gridHouse, switchOff, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, label, 1, start, 1, 1);
		GTK.gtk_switch_set_active(switchOff, true);
		//GTK.g_signal_connect(switchOff, "state-set", new IGCallBack()
		
		GTK.g_signal_connect(switchOff, "activate", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				System.out.println("hello");
					if(GTK.gtk_switch_get_active(switchOff))
					{
						//GTK.gtk_label_set_text(label, "你打开了");
						System.out.println("hello");
					}
					else
					{
						//GTK.gtk_label_set_text(label, "你关闭了");
						System.out.println("fuck");
					}
			}
		}, null);

	}
	public static void createMenu(int gridHouse, int start)
	{
		//分三步
		//1创建单一菜单
		//2创建一个菜单头
		//3创建总的菜单条，把许多菜单头都挂到菜单条上
		//创建一个菜单
		int menu = GTK.gtk_menu_new();
		
		int file = GTK.gtk_menu_item_new_with_label("_F文件");
		int new1  = GTK.gtk_menu_item_new_with_label("新建");
		int open1 = GTK.gtk_menu_item_new_with_label("打开");
		int closed = GTK.gtk_menu_item_new_with_label("关闭");
		int import1 = GTK.gtk_menu_item_new_with_mnemonic("_Import导入");
		//int image = GTK.gtk_menu_tool_button_new_from_stock(GTK.GTK_STOCK_NEW);
		//fengexian 
		GTK.gtk_widget_show(menu);
		GTK.gtk_widget_show(new1);
		GTK.gtk_widget_show(open1);
		GTK.gtk_widget_show(closed);
		GTK.gtk_widget_show(file);
		GTK.gtk_widget_show(import1);
		
		GTK.gtk_menu_shell_append(menu, file);
		GTK.gtk_menu_shell_append(menu, new1);
		GTK.gtk_menu_shell_append(menu, open1);
		GTK.gtk_menu_shell_append(menu, closed);
		GTK.gtk_menu_shell_append(menu, import1);
		// 菜单条
		int menubar = GTK.gtk_menu_bar_new();
		GTK.gtk_widget_show(menubar);
		GTK.gtk_grid_attach(gridHouse, menubar, 0, start, 1, 1);
		
		//2 添加一个menu 到menu小头
		int file_item =  GTK.gtk_menu_item_new_with_label("File");
		GTK.gtk_widget_show(file_item);
		GTK.gtk_menu_item_set_submenu(file_item, menu);
		
		//3 添加一个 menu小头到menubar
		GTK.gtk_menu_shell_append(menubar, file_item);
//		GTK.gtk_menu_attach_to_widget(menubar, attach_widget);
		GTK.g_signal_connect(closed, "activate",new IGCallBack(){
				@Override
				public void execute(int instance, int eventData, Object object)
				{
					// TODO Auto-generated method stub
					GTK.gtk_main_quit();
				}
			}, null);
		}
	
	public static void createStatusIcon(int gridHouse,int start)
	{
		int status = GTK.gtk_status_icon_new();
		//GTK.gtk_widget_show(status);
		//GTK.gtk_grid_attach(gridHouse, status, 0, status, 1, 1);
		GTK.gtk_status_icon_set_from_stock(status, GTK.GTK_STOCK_CONNECT);
		GTK.gtk_status_icon_set_tooltip_text(status, "hello"); //的确是会显示在下方
		GTK.gtk_status_icon_set_visible(status, true);
		
		GTK.g_signal_connect(status, "popup-menu", new IGCallBack() {
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
/*				int menu = GTK.gtk_menu_new();
				
				int file = GTK.gtk_menu_item_new_with_label("_F文件");
				int new1  = GTK.gtk_menu_item_new_with_label("新建");
				int open1 = GTK.gtk_menu_item_new_with_label("打开");
				int closed = GTK.gtk_menu_item_new_with_label("关闭");
				int import1 = GTK.gtk_menu_item_new_with_mnemonic("_Import导入");
				//int image = GTK.gtk_menu_tool_button_new_from_stock(GTK.GTK_STOCK_NEW);
				//fengexian 
				GTK.gtk_widget_show(menu);
				GTK.gtk_widget_show(new1);
				GTK.gtk_widget_show(open1);
				GTK.gtk_widget_show(closed);
				GTK.gtk_widget_show(file);
				GTK.gtk_widget_show(import1);
				
				GTK.gtk_menu_shell_append(menu, file);
				GTK.gtk_menu_shell_append(menu, new1);
				GTK.gtk_menu_shell_append(menu, open1);
				GTK.gtk_menu_shell_append(menu, closed);
				GTK.gtk_menu_shell_append(menu, import1);
				GTK.gtk_menu_popup(menu);*/
				showInfo("hello","wenho");
			}
		}, null);
/*		GTK.g_signal_connect(status, "statusIconActivate", new IGCallBack() {
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				showInfo("hello","wenho");
			  
			}},null);*/
	
	}
}
