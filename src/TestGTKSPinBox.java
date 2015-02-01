import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author 叶昭良
 * @version SpinBoxv1.0
 */
public class TestGTKSPinBox
{

	/**
	 * @param args
	 *  
	 */
	static int window;
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		GTK.gtk_init();
		window  = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "测试SpinBox");
		GTK.g_signal_connect(window, "destroy", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
		GTK.gtk_widget_show(window);
		
		
		//开始建立布局
		 gridHouse = GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		//开始插入控件
		int start = 0 ;
		createSpinbox(window,gridHouse,start);
		
		GTK.gtk_container_add(window, gridHouse);
		GTK.gtk_main();
	}
	
	public static void createSpinbox(int window,int gridHouse,int start)
	{
		//定义控件
		int label1 = GTK.gtk_label_new("数字：0-9");
		int label2 = GTK.gtk_label_new("范围: -10~10");
		final int labelSpinbox = GTK.gtk_label_new("");
		final int labelSpinboxApple = GTK.gtk_label_new("");
		final int spinbox = GTK.gtk_spin_button_new_with_range(0, 9, 1);
		final int spinboxApple = GTK.gtk_spin_button_new_with_range(-10, 10, 0.1);
		
	
		//添加控件到整租房间
		GTK.gtk_grid_attach(gridHouse, label1, 0, start, 1, 1); 
		GTK.gtk_grid_attach(gridHouse, spinbox, 1, start, 1, 1); 
		GTK.gtk_grid_attach(gridHouse, label2, 0, start+1, 1, 1); 
		GTK.gtk_grid_attach(gridHouse, spinboxApple, 1, start+1, 1, 1);
		
		GTK.gtk_grid_attach(gridHouse, labelSpinbox, 0, start+3, 1, 1);
		GTK.gtk_grid_attach(gridHouse, labelSpinboxApple, 0, start+4, 1, 1);
		//显示控件
		GTK.gtk_widget_show(label1);
		GTK.gtk_widget_show(spinbox);
		GTK.gtk_widget_show(label2);
		GTK.gtk_widget_show(spinboxApple);
		GTK.gtk_widget_show(labelSpinbox);
		GTK.gtk_widget_show(labelSpinboxApple);
		GTK.gtk_widget_show(gridHouse);
		
		
		//添加事件
		GTK.g_signal_connect(spinbox, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				double apple = GTK.gtk_spin_button_get_value(spinbox);
				GTK.gtk_label_set_text(labelSpinbox, "您当前选择的数字式："+Double.toString(apple));
				
			}
		}, null);
		
		GTK.g_signal_connect(spinboxApple, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				double apple = GTK.gtk_spin_button_get_value(spinboxApple);
				GTK.gtk_label_set_text(labelSpinboxApple, "您当前选择的数字式："+Double.toString(apple));
				
			}
		}, null);
	}

}
