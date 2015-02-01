import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;


/**
 * @author 叶昭良
 * @version ButtonImage v1.0
 *
 */
public class TestGtkButtonImage
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		// TODO 自动生成的方法存根
		// TODO 自动生成的方法存根
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL); 
		GTK.gtk_window_set_title(window, "ButtonImage V1.0");
		GTK.gtk_widget_show(window);
		//安全关闭GTK
		GTK.g_signal_connect(window, "destroy", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
		// 创建布局
		gridHouse =  GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		
		int start  = 0;
		createButtonImage(window,gridHouse,start);
		GTK.gtk_container_add(window,gridHouse);
		//启动循环
		GTK.gtk_main();
	}
	
	public static void createButtonImage(int window,int gridHouse,int start)
	{
		int imgOpen = GTK.gtk_image_new_from_stock(GTK.GTK_STOCK_OPEN, GTK.GTK_ICON_SIZE_BUTTON);
		int imgClosed = GTK.gtk_image_new_from_stock(GTK.GTK_STOCK_CLOSE, GTK.GTK_ICON_SIZE_BUTTON);
		int imgFile = GTK.gtk_image_new_from_stock(GTK.GTK_STOCK_FILE, GTK.GTK_ICON_SIZE_BUTTON);
		int btnCommon = GTK.gtk_button_new_with_label("普通按钮");
		int btnOpen = GTK.gtk_button_new_with_label("打开");
		int btnClosed = GTK.gtk_button_new_with_label("关闭");
		int btnFile = GTK.gtk_button_new_with_label("文件");
		GTK.gtk_button_set_image(btnOpen, imgOpen);
		GTK.gtk_button_set_image(btnClosed, imgClosed);
		GTK.gtk_button_set_image(btnFile, imgFile);
		//添加控件 
		//GTK.gtk_grid_attach(gridHouse, imgOpen, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnCommon, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnOpen, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnClosed,2, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnFile,3, start, 1, 1);
		//显示控件
		GTK.gtk_widget_show(btnOpen);
		GTK.gtk_widget_show(btnClosed);
		GTK.gtk_widget_show(btnCommon);
		GTK.gtk_widget_show(btnFile);
		
		GTK.gtk_button_set_image_position(btnClosed, GTK.GTK_POS_TOP); //GTK_POS
		GTK.gtk_button_set_image_position(btnFile, GTK.GTK_POS_RIGHT);
	}

}
