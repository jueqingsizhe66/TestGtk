
/**
 * @author 叶昭良
 *
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import com.rupeng.gtk4j.Utils;
public class TestGtkWidget
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
	    GTK.gtk_main();
	}

}
