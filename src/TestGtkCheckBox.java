import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * 
 */

/**
 * @author 叶昭良
 * @version  v1.0
 */
public class TestGtkCheckBox
{

	/**
	 * @param args
	 */
	private static int window ;
	private static int box;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根

	//	gtkHead(window,"gtkCheckbox测试");
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "hello");
		GTK.gtk_widget_show(window);
		GTK.g_signal_connect(window,"destroy",new IGCallBack() 
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
			
		},null);
		box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
		int box2= GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL,0);
		int box1= GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL,0);
		testGtkCheckBox(window,box2);
		testGtkCheckBoxInstall(window,box1);
		GTK.gtk_box_pack_start(box, box2, false, false, 0);
		GTK.gtk_box_pack_start(box, box1, false, false, 0);

		GTK.gtk_container_add(window, box);
		GTK.gtk_widget_show(box);
		GTK.gtk_main();
		
	}

	
	public static void testGtkCheckBox(int window,int box2)
	{
	
		int cbMan = GTK.gtk_check_button_new_with_label("男");
		int cbWoman = GTK.gtk_check_button_new_with_label("女");
		
		
		GTK.gtk_box_pack_start(box2, cbMan, false,false	, 0);
		GTK.gtk_box_pack_start(box2, cbWoman, false,false	, 0);
		
		
		GTK.gtk_widget_show(cbMan);
		GTK.gtk_widget_show(cbWoman);
		GTK.gtk_widget_show(box2);
	
	}
	public static void testGtkCheckBoxInstall(int window,int box1)
	{
		int entryLicense = GTK.gtk_entry_new();
		GTK.gtk_entry_set_text(entryLicense, "产品使用声明:\n 尊重ISIS国际标准");

		//定义控件
		int boxInner = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		//为什么要修改为终态
		final int cbAgree = GTK.gtk_check_button_new_with_label("我同意上面的协议");
		final int btnInstall = GTK.gtk_button_new_with_label("install");
		int btnClosed  = GTK.gtk_button_new_with_label("close");
		GTK.gtk_widget_set_sensitive(btnInstall, false);
		
		//添加控件
		GTK.gtk_box_pack_start(box1, entryLicense, false,false	, 0);
		GTK.gtk_box_pack_start(box1, cbAgree, false,false, 0);
		GTK.gtk_box_pack_start(boxInner, btnInstall, false,false,0);
		GTK.gtk_box_pack_start(boxInner, btnClosed, false,false,0);
		GTK.gtk_box_pack_start(box1, boxInner, false,false	, 0);
		
		//显示控件
		GTK.gtk_widget_show(entryLicense);
		GTK.gtk_widget_show(cbAgree);
		GTK.gtk_widget_show(btnInstall);
		GTK.gtk_widget_show(btnClosed);
		GTK.gtk_widget_show(boxInner);
		GTK.gtk_widget_show(box1);
		
		//添加事件
		GTK.g_signal_connect(cbAgree, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				boolean isChecked =  GTK.gtk_toggle_button_get_active(cbAgree);
				
				//写了gtk_checkbox发现没有set命令 于是用了widget..
				GTK.gtk_widget_set_sensitive(btnInstall, isChecked);
			}
		}, null);
	}

}
