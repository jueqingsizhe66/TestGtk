
/**
 * @author 叶昭良
 * @version  v1.0
 */
import java.awt.AWTException;
import java.awt.Robot;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import com.rupeng.gtk4j.Utils;
public class TestGtkWidgetBoxLayout
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
	    int label2 =  GTK.gtk_label_new("");
	    int label3 =  GTK.gtk_label_new("");
	    
	    // 显示标签
	    GTK.gtk_widget_show(label1);
	    GTK.gtk_widget_show(label2);
	    GTK.gtk_widget_show(label3);
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
	    // gtk_box_new 的第一个参数 一般是两种赋值方式GTK.GTK_ORIENTATION_HORIZONTAL和_VERTICAL
	    // 第二个参数表示的间隔space,如果设置为5则为5pt的间隔
	    int houseApple = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
	    int houseBanana = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
	    int houseTree = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
	    //水平house
	    GTK.gtk_box_pack_start(houseApple, label1, true, true, 0);
	    GTK.gtk_box_pack_start(houseApple,entry1 , true, true, 0);
	    GTK.gtk_box_pack_start(houseBanana,label2 , true, true, 0);
	    GTK.gtk_box_pack_start(houseBanana,button1 , true, true, 0);
	    GTK.gtk_box_pack_start(houseBanana,label3 , true, true, 0);
	    //垂直house
	    GTK.gtk_box_pack_start(houseTree, houseApple, true, true, 0);
	    GTK.gtk_box_pack_start(houseTree, houseBanana, true, true, 0);
	    

	    // 显示三个容器 的house
	    GTK.gtk_widget_show(houseApple);
	    GTK.gtk_widget_show(houseBanana);
	    GTK.gtk_widget_show(houseTree);
	    
	    GTK.gtk_container_add(window,houseTree);
	    
	    GTK.gtk_main();
	    
	    
	}

}
