
/**
 * @author    叶昭良
 * @time      2015年2月1日上午11:30:12
 * @version   TestLine V1.0
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.Cairo;

public class TestLine
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static int scrolledBar;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		//初始化
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//添加窗口
		GTK.gtk_widget_show(window);
		//安静关闭
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
/*		//创建布局：
		gridHouse = GTK.gtk_grid_new();*/
		
		int drawArea = GTK.gtk_drawing_area_new();
		GTK.gtk_widget_set_size_request(drawArea, 500, 500);
		//GTK.gtk_container_add(window, gridHouse);
		GTK.gtk_container_add(window, drawArea);
		GTK.g_signal_connect(drawArea, "draw", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				Cairo.cairo_move_to(eventData, 0, 500);
				int[] arrApple = {50, 90, 80, 120, 10, 50};
				
				// 获取最大值
				int max = arrApple[0];
				for(int i = 1; i < arrApple.length; i++)
				{
					if(max < arrApple[i])
					{
						max = arrApple[i];
					}
				}
				// 
				for(int i = 0; i<  arrApple.length; i++)
				{
					// 获取坐标(x,y)
					int  x =  (i+1)*50;
					int  y =  500 - 500*arrApple[i]/max;
					// 绘制坐标点
					Cairo.cairo_line_to(eventData, x, y);
				}
				// 创建绘画
				Cairo.cairo_stroke(eventData);
			}
			
		}, null);
		 createDraw( window, drawArea, gridHouse,0);
		GTK.gtk_widget_show(drawArea);
		//添加循环
		GTK.gtk_main();
	}
	
	public static void createDraw(int window, int drawArea,int gridHouse,int start)
	{
		GTK.g_signal_connect(drawArea, "draw", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				Cairo.cairo_move_to(eventData, 0, 0);
				int[] arrApple = {50, 90, 80, 120, 10, 50};
				
				// 获取最大值
				int max = arrApple[0];
				for(int i = 1; i < arrApple.length; i++)
				{
					if(max < arrApple[i])
					{
						max = arrApple[i];
					}
				}
				// 
				for(int i = 0; i<  arrApple.length; i++)
				{
					// 获取坐标(x,y)
					int  x =  (i+1)*50;
					//int  y =  300 - 300*arrApple[i]/max;
					int  y =  500*arrApple[i]/max;
					// 绘制坐标点
					Cairo.cairo_line_to(eventData, x, y);
				}
				// 创建绘画
				Cairo.cairo_stroke(eventData);
			}
			
		}, null);
		//createScrolledBar(window,drawArea,scrolledBar,start);
	}
/*	 //滚动条无法用于drawArea
	public static void createScrolledBar(int window,int drawArea,int scrolledBar,int start) 
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_set_size_request(scrolledBar, 300, 100);
		GTK.gtk_widget_show(scrolledBar);
		GTK.gtk_container_add(scrolledBar,drawArea);
		GTK.gtk_grid_attach(gridHouse, scrolledBar, 0, start, 1, 1);
		
	}*/

}
