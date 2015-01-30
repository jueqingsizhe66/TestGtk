
/**
 * @author 叶昭良
 * @version v1.0
 * @version v1.1 改进了密码框  
 */
import java.awt.AWTException;
import java.awt.Robot;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import com.rupeng.gtk4j.Utils;
public class TestGtkWidgetGridLayout
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
	    //新加了一个网格屋子
	    int houseGrid = GTK.gtk_grid_new();




	    // 显示 的houseGrid
	    GTK.gtk_widget_show(houseGrid);
	    // 0开头是程序员的惯例
	    testGtk(houseGrid,0);
	    landInternet(houseGrid,2);
	    

	    
	    GTK.gtk_container_add(window,houseGrid);
	    GTK.gtk_main();
	    
	    
	}
	//新建一个测试的界面
	/**
	 * 
	 * @param houseGrid  整租的大房子  表示gtk_grid_new的返回值
	 * @param start      在houseGrid分配到的第几行开始编号
	 */
	public static void testGtk(int houseGrid,int start)
	{
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
	    
	    //整租到housegrid
	    // gtk_grid_attach(int grid, int child, int left,int top, int width, int height);
	    GTK.gtk_grid_attach(houseGrid, label1, 0, start, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, entry1, 1, start, 1, 1);
	    
	    GTK.gtk_grid_attach(houseGrid, button1, 1, start+1, 1, 1);
	}
	
    //新建一个登陆界面的GTK
	public static void landInternet(int houseGrid,int start) 
	{
		   //新建了一个文本框
	    int entry1  = GTK.gtk_entry_new();

	    int entry2  = GTK.gtk_entry_new();
	    
	    //增加了文本框的密码框的设置y2, false);
	    //可以设置显示的
	    GTK.gtk_entry_set_visibility(entry2,false);  //字符 默认的密码框为*
	    GTK.gtk_entry_set_invisible_char(entry2, '1'); //只能是字符  不能是"1"

	    //新建了一个button 
	    int buttonEnter = GTK.gtk_button_new_with_label("登陆");

	    int buttonClosed = GTK.gtk_button_new_with_label("取消");

	    int checkbox1= GTK.gtk_check_button_new_with_label("是否保存密码");
	    //新建了一个标签
	    int label1 =  GTK.gtk_label_new("用户名：");
	    int label2 =  GTK.gtk_label_new("密码  ：");
	    
	    //为什么要声明为final暂时不是特别清楚
	    final int label3 =  GTK.gtk_label_new("");
	    
	    // 显示标签
	    GTK.gtk_widget_show(entry1);
	    GTK.gtk_widget_show(entry2);
	    GTK.gtk_widget_show(label1);
	    GTK.gtk_widget_show(label2);
	    GTK.gtk_widget_show(label3);
	    GTK.gtk_widget_show(checkbox1);
	    GTK.gtk_widget_show(buttonEnter);
	    GTK.gtk_widget_show(buttonClosed);
	    
	    //整租到houseGrid
	    GTK.gtk_grid_attach(houseGrid, label1, 0, start, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, entry1, 1, start, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, label2, 0, start+1, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, entry2, 1, start+1, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, checkbox1, 1, start+2, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, buttonEnter, 1, start+3, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, buttonClosed, 2, start+3, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, label3, 1, start+5, 1, 1);
	    
	    
	    // 进行按钮的事件连接
	    GTK.g_signal_connect(buttonEnter,"clicked",new IGCallBack()
	    {
	    	@Override
	    	public void execute(int instance, int eventData, Object object)
	    	{
	    		// TODO 自动生成的方法存根
	    		System.out.println("你点击了登陆");
	    		GTK.gtk_label_set_text(label3, "你点击了登陆");
	    	}
	    },null);
	    
	    GTK.g_signal_connect(buttonClosed,"clicked",new IGCallBack()
	    {
	    	@Override
	    	public void execute(int instance, int eventData, Object object)
	    	{
	    		// TODO 自动生成的方法存根
	    		System.out.println("你点击了关闭");
	    		GTK.gtk_label_set_text(label3, "你点击了关闭");
	    	}
	    },null);
	}

}
