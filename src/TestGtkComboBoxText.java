import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author 叶昭良
 * @version ComboBoxText v1.0
 *
 */
public class TestGtkComboBoxText
{

	/**
	 * @param args
	 */
	static int window;
	//static int box;
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL); 
		GTK.gtk_window_set_title(window, "SpinBoxV1.0");
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
		createComboBoxText(window,gridHouse,0);
		
		GTK.gtk_container_add(window,gridHouse);
		//启动循环
		GTK.gtk_main();
	}
	
	public static void createComboBoxText(int window,int gridHouse,int start)
	{
		final int comboBoxBig = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "苹果");
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "香蕉");
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "葡萄");
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "橘子");
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "蜜柚");
		final int comboBoxApple = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxApple, "红苹果");
		GTK.gtk_combo_box_text_append_text(comboBoxApple, "青苹果");
		GTK.gtk_combo_box_text_append_text(comboBoxApple, "小苹果");
		final int comboBoxBanana = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxBanana, "天宝蕉");
		GTK.gtk_combo_box_text_append_text(comboBoxBanana, "竹蕉");
		GTK.gtk_combo_box_text_append_text(comboBoxBanana, "大香蕉");
		final int comboBoxGrape = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxGrape, "青葡萄");
		GTK.gtk_combo_box_text_append_text(comboBoxGrape, "玫瑰香");
		GTK.gtk_combo_box_text_append_text(comboBoxGrape, "巨峰");
		GTK.gtk_combo_box_text_append_text(comboBoxGrape, "夏黑");
		final int comboBoxOrange = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxOrange, "小橘子");
		GTK.gtk_combo_box_text_append_text(comboBoxOrange, "大橘子");
		GTK.gtk_combo_box_text_append_text(comboBoxOrange, "年橘");
		final int comboBoxYouzi = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxYouzi, "白心蜜柚");
		GTK.gtk_combo_box_text_append_text(comboBoxYouzi, "红心蜜柚");
		GTK.gtk_combo_box_text_append_text(comboBoxYouzi, "臭蜜柚");
		
	/*	int label1 = GTK.gtk_label_new("您当前想要购买的是"+GTK.gtk_combo_box_text_get_active_text(comboBoxBig)
				+"下的"+GTK.gtk_combo_box_text_get_active_text(comboBoxApple));*/
		final int label1 = GTK.gtk_label_new("您当前想要购买的是?");
		
		// 添加控件到整租房间
		
		GTK.gtk_grid_attach(gridHouse, comboBoxBig, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxApple, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxBanana, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxGrape, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxOrange, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxYouzi, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, label1, 0, start+1, 1, 1);
		
		//显示控件
		GTK.gtk_widget_show(comboBoxBig);
		GTK.gtk_widget_show(comboBoxApple);
		GTK.gtk_widget_show(label1);
		
		//设置combobox的激活状态
		GTK.gtk_combo_box_set_active(comboBoxBig, 0);
		GTK.gtk_combo_box_set_active(comboBoxApple, 0);
		
		//添加事件
		GTK.g_signal_connect(comboBoxBig, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				String fruit = GTK.gtk_combo_box_text_get_active_text(comboBoxBig);
				if(fruit.equalsIgnoreCase("苹果"))
				{
					helpFunction(comboBoxApple,label1);
					GTK.gtk_widget_hide(comboBoxBanana);
					GTK.gtk_widget_hide(comboBoxGrape);
					GTK.gtk_widget_hide(comboBoxOrange);
					GTK.gtk_widget_hide(comboBoxYouzi);
				}else if(fruit.equalsIgnoreCase("香蕉"))
				{
					helpFunction(comboBoxBanana,label1);
					GTK.gtk_widget_hide(comboBoxApple);
					GTK.gtk_widget_hide(comboBoxGrape);
					GTK.gtk_widget_hide(comboBoxOrange);
					GTK.gtk_widget_hide(comboBoxYouzi);
				}else if(fruit.equalsIgnoreCase("葡萄"))
				{
					helpFunction(comboBoxGrape,label1);
					GTK.gtk_widget_hide(comboBoxApple);
					GTK.gtk_widget_hide(comboBoxBanana);
					GTK.gtk_widget_hide(comboBoxOrange);
					GTK.gtk_widget_hide(comboBoxYouzi);
				}else if(fruit.equalsIgnoreCase("橘子"))
				{
					helpFunction(comboBoxOrange,label1);
					GTK.gtk_widget_hide(comboBoxApple);
					GTK.gtk_widget_hide(comboBoxBanana);
					GTK.gtk_widget_hide(comboBoxGrape);
					GTK.gtk_widget_hide(comboBoxYouzi);	
				}
				else if(fruit.equalsIgnoreCase("蜜柚"))
				{
					helpFunction(comboBoxYouzi,label1);
					GTK.gtk_widget_hide(comboBoxApple);
					GTK.gtk_widget_hide(comboBoxBanana);
					GTK.gtk_widget_hide(comboBoxGrape);
					GTK.gtk_widget_hide(comboBoxOrange);	
				}
			}
		}, null);
		
	}
	
	public static void helpFunction(final int comboBoxText,final int label1)
	{
		GTK.gtk_widget_show(comboBoxText);
		GTK.gtk_combo_box_set_active(comboBoxText, 0);
		GTK.g_signal_connect(comboBoxText, "changed", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object) 
			{
				String fruit = GTK.gtk_combo_box_text_get_active_text(comboBoxText);
				GTK.gtk_label_set_text(label1, "您想要购买的"+fruit+"没有了！请到别家购买");
			};
		}, null);
	}
}
