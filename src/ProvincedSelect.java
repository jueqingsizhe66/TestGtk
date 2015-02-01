import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * 
 */

/**
 * @author 叶昭良
 * @version 省市联动器 V1.0
 *
 */
public class ProvincedSelect
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static int cmbProvince;
	static int cmbCity;
	static int labelShow;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		//初始化
		GTK.gtk_init();
		//建立窗口
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//显示窗口
		GTK.gtk_widget_show(window);
		// 安静关闭
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
			
		}, null);
		//布局设置
		gridHouse = GTK.gtk_grid_new();			
		GTK.gtk_widget_show(gridHouse);	
		//包含整租房
		GTK.gtk_container_add(window, gridHouse);
		//创建控件
		//
		labelShow = GTK.gtk_label_new("");
		cmbProvince = GTK.gtk_combo_box_text_new();
		cmbCity     = GTK.gtk_combo_box_text_new();

		
		int start =  0;
		//添加控件
		GTK.gtk_grid_attach(gridHouse, cmbProvince, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, cmbCity, 0,start+1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, labelShow, 1,start+2, 1, 1);

		//显示控件

		GTK.gtk_widget_show(cmbProvince);
		GTK.gtk_widget_show(cmbCity);
		GTK.gtk_widget_show(labelShow);
		createProvince(window);
		

		
		//启动循环
		GTK.gtk_main();

	}
	
	public static void createProvince(int window)
	{
		

		GTK.gtk_combo_box_text_append(cmbProvince, "fj", "福建");
		GTK.gtk_combo_box_text_append(cmbProvince, "bj", "北京");
		//GTK.gtk_combo_box_text_append(cmbProvince, "sh", "上海");
		GTK.gtk_combo_box_text_append(cmbProvince, "hn", "河南");
		GTK.gtk_combo_box_text_append(cmbProvince, "hb", "河北");
		GTK.gtk_combo_box_text_append(cmbProvince, "sd","山东");
		
		GTK.gtk_combo_box_set_active_id(cmbProvince, "bj");

//添加事件
		GTK.g_signal_connect(cmbProvince, "changed", new IGCallBack() 
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				
				String province  =  GTK.gtk_combo_box_get_active_id(cmbProvince);
				if(province==null)return;
				System.out.println(province);
				GTK.gtk_combo_box_text_remove_all(cmbCity); //删除所有项
				//GTK.gtk_combo_box_text_remove_all(cmbCity); //删除所有项
				if(province.equals("fj"))
				{
					System.out.println("福建测试中");
					GTK.gtk_combo_box_text_append(cmbCity, "zhz", "漳州");
					GTK.gtk_combo_box_text_append(cmbCity, "xm", "厦门");
					GTK.gtk_combo_box_text_append(cmbCity, "fz", "福州");
					GTK.gtk_combo_box_text_append(cmbCity, "qz", "泉州");
					
				}else if(province.equals("bj"))
				{
					System.out.println("福建测试中");
					GTK.gtk_combo_box_text_append(cmbCity, "cp", "昌平区");
					GTK.gtk_combo_box_text_append(cmbCity, "hd", "海淀区");
					GTK.gtk_combo_box_text_append(cmbCity, "tz", "通州区");
					GTK.gtk_combo_box_text_append(cmbCity, "cy", "朝阳区");
				}else if(province.equals("hn"))
				{
					System.out.println("福建测试中");
			       GTK.gtk_combo_box_text_append(cmbCity, "zz", "郑州");
			       GTK.gtk_combo_box_text_append(cmbCity, "zmd", "驻马店");
			       GTK.gtk_combo_box_text_append(cmbCity, "ny", "南阳");
				}else if(province.equals("hb"))
				{
					System.out.println("福建测试中");
			       GTK.gtk_combo_box_text_append(cmbCity, "sjz", "石家庄");
			       GTK.gtk_combo_box_text_append(cmbCity, "ts", "唐山");
			       GTK.gtk_combo_box_text_append(cmbCity, "qhd", "秦皇岛");
				}else if(province.equals("sd"))
				{
					System.out.println("福建测试中");
				   GTK.gtk_combo_box_text_append(cmbCity, "jn", "济南");
			       GTK.gtk_combo_box_text_append(cmbCity, "qd", "青岛");
			       GTK.gtk_combo_box_text_append(cmbCity, "yt", "烟台");
				}
			}	
		}, null);
		
		//原来remove之后 也是会促发cmbCity的 信号  而导致改变 ，根源在于这个
		GTK.g_signal_connect(cmbCity, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
 				String tempProvince = GTK.gtk_combo_box_text_get_active_text(cmbProvince);
				
				int  apple  = GTK.gtk_combo_box_get_active(cmbCity);
				if(apple < 0)
				{
					System.out.println("nothing in the city");
					return;
				}
				
				String tempCity = GTK.gtk_combo_box_text_get_active_text(cmbCity);
				if(tempProvince==null||tempCity==null)return;
				//GTK.gtk_label_set_text(labelShow, "你准备去"+tempProvince+tempCity);
				GTK.gtk_label_set_text(labelShow,tempProvince+tempCity);
				System.out.println(tempProvince);
				System.out.println(tempCity);

			}
		}, null);
		
		
	}

}
