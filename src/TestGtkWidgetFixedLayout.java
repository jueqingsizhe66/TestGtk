
/**
 * @author 叶昭良
 * @version  v1.0
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import com.rupeng.gtk4j.Utils;
public class TestGtkWidgetFixedLayout
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		GTK.gtk_init();
	    int window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL); // 原来还有个POPUP估计是右键菜单
	    GTK.gtk_widget_show(window);
	    
	    GTK.gtk_window_set_title(window, "霜雪"); // widget下没有 只有在window下有。
	   // GTK.gtk_window_set_position(window, GTK.GTK_WIN_POS_CENTER);
	    
	    GTK.gtk_window_set_position(window, GTK.GTK_WIN_POS_CENTER_ALWAYS);
	    //GTK.gtk_window_set_resizable(window, true);  //默认可以拉伸
	    // 默认的窗口可以最大化， GTK.gtk_widget_set_maximize(widget)
	   
	    //新建了一个文本框
	    int entry1  = GTK.gtk_entry_new();
	    GTK.gtk_widget_show(entry1);
	    //新建了一个button 
	    int button1 = GTK.gtk_button_new_with_label("click me");
	    GTK.gtk_widget_show(button1);
	    
	    //新建了一个标签
	    int label1 =  GTK.gtk_label_new("请输入：");
	    GTK.gtk_widget_show(label1);
	    
	    // 安静的关闭对话框
	    GTK.g_signal_connect(window, "destroy", new IGCallBack()
	    {
	    	@Override
	    	public void execute(int instance, int eventData, Object object)
	    	{
	    		// TODO 自动生成的方法存根
	    		GTK.gtk_main_quit();
	    	}
	    }, null);
	    
	    //int box1= GTK.gtk_box_new(GTK., spacing)
	    //新加了一个house  用来整租给文本框 +button+label
	    int house = GTK.gtk_fixed_new();
	    GTK.gtk_fixed_put(house, entry1,0,0);
	    GTK.gtk_fixed_put(house,button1,50,0);
	    GTK.gtk_fixed_put(house,label1,0,100); //很难适应屏幕大小！！所以一般很少用
	    GTK.gtk_widget_show(house);
	    
	    GTK.gtk_container_add(window,house);
	    
	    GTK.gtk_main();
	}

}
