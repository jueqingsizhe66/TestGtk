
/**
 * @author 叶昭良
 * @version GTK+Cairo v1.0
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.Cairo;
public class GTKTestCairo
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		//GTK初始化
		GTK.gtk_init();
		//窗口标识
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//窗口标题
		GTK.gtk_window_set_title(window, "简易画图板v1.0");
		//显示窗口
		GTK.gtk_widget_show(window);
		
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
		
		
		//设置布局方式
		gridHouse = GTK.gtk_grid_new();
		
		
		//创建控件
		int start = 0;
		createDraw(window, gridHouse, start);
		//circlePoint 原点设置为180
		createDrawLaughFace(window,gridHouse,start+1,180);
		
		
		GTK.gtk_widget_show(gridHouse);
		
		GTK.gtk_container_add(window,gridHouse);
		//启动循环
		GTK.gtk_main();
	}
	
	
	public static void createDraw(int window,int gridHouse,int start) 
	{
		//创建画图板 或者叫画布
		int  dan  = GTK.gtk_drawing_area_new();
		
		int label = GTK.gtk_label_new("三毛");
		//创建源
		//添加控件
		GTK.gtk_grid_attach(gridHouse, label, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, dan, 1, start, 1, 1);
		//显示画布
		GTK.gtk_widget_show(dan);
		GTK.gtk_widget_show(label);
		
		//设置画布的大小
		GTK.gtk_widget_set_size_request(dan, 300, 300);
		
		
		//开始绘制  利用事件draw 来不断的绘制
		
		GTK.g_signal_connect(dan, "draw",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//eventDate才是画布源
				//设置画笔的颜色
				Cairo.cairo_set_source_rgb(eventData, 0.5, 0.6, 0.3);
				//设置画笔的大小
				Cairo.cairo_set_line_width(eventData, 3);
				
				
				
				//移动画笔点   画笔默认位置不是在（0,0）move_to不可少
				//Cairo.cairo_move_to(eventData, 0, 0);
/*				//画一条线七点
				Cairo.cairo_line_to(eventData, 50, 50);
				//画一条线重点
				Cairo.cairo_line_to(eventData,100,100);
				//接着划线
				Cairo.cairo_line_to(eventData, 120, 120);*/
				//画一个圆
				Cairo.cairo_arc(eventData, 180, 180, 60, 0, 2*3.1415926);
				//画一个圆弧
				//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
				//显示画笔
				Cairo.cairo_stroke(eventData); //Cairo.cairo_fill(eventData)不同的效果
				
				
				//画一个圆弧  有下面实验知道是从x轴沿顺时针开始画图
/*				Cairo.cairo_arc(eventData, 220, 220, 30,0, 0.5*3.1415926);

				Cairo.cairo_fill(eventData); //Cairo.cairo_fill(eventData)不同的效果
				
				Cairo.cairo_arc(eventData, 220, 100, 30,0, 3.1415926);
				Cairo.cairo_stroke(eventData);
				Cairo.cairo_arc(eventData, 220, 30, 30,0, 1.5*3.1415926);
				Cairo.cairo_fill(eventData);*/
				//嘴巴的绘制
				double Pi = 3.1415926;
				Cairo.cairo_arc(eventData, 180, 180, 40, 0.25*Pi, 0.75*Pi);
				Cairo.cairo_fill(eventData);
				
				//绘制两只眼睛
				Cairo.cairo_arc(eventData, 160, 160, 10, 0, 2*Pi);
				Cairo.cairo_arc(eventData, 200, 160, 10, 0, 2*Pi);
				Cairo.cairo_fill(eventData);
				
				
				//绘制胡须
				Cairo.cairo_arc(eventData, 130, 160, 50, 0.166667*Pi, 0.6777777*Pi);
				
				Cairo.cairo_stroke(eventData);
				
				Cairo.cairo_arc(eventData, 230, 160, 50, 0.333333*Pi, 0.8333333*Pi);
				Cairo.cairo_stroke(eventData);
				
				//绘制头发
				Cairo.cairo_arc(eventData, 133.6, 136.72, 30,1.3333333*Pi ,1.833333*Pi );
				
				Cairo.cairo_stroke(eventData);
				
				Cairo.cairo_arc(eventData, 224.4, 136.72, 30, 1.166667*Pi, 1.6777777*Pi);
				Cairo.cairo_stroke(eventData);
				
				
				//移动画笔点   画笔默认位置不是在（0,0）move_to不可少
				Cairo.cairo_move_to(eventData, 180, 120);
 			   //画一条线七点
				Cairo.cairo_line_to(eventData, 180, 90);
				Cairo.cairo_stroke(eventData);
			}
		}, null);
		
		
	}
	
	public static void createDrawLaughFace(int window,int gridHouse,int start,final int circlePoint) 
	{
		//创建画图板 或者叫画布
		int  dan  = GTK.gtk_drawing_area_new();
		int label = GTK.gtk_label_new("笑脸");
		//创建源
		//添加控件
		GTK.gtk_grid_attach(gridHouse, label, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, dan, 1, start, 1, 1);
		//显示画布
		GTK.gtk_widget_show(dan);
		GTK.gtk_widget_show(label);

		
		//设置画布的大小
		GTK.gtk_widget_set_size_request(dan, 300, 300);
		
		
		//开始绘制  利用事件draw 来不断的绘制
		
		GTK.g_signal_connect(dan, "draw",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//eventDate才是画布源
				//设置画笔的颜色
				Cairo.cairo_set_source_rgb(eventData, 0.5, 0.6, 0.3);
				//设置画笔的大小
				Cairo.cairo_set_line_width(eventData, 3);			
	
				//画一个圆
				Cairo.cairo_arc(eventData, circlePoint, circlePoint, 60, 0, 2*3.1415926);
				//画一个圆弧
				//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
				//显示画笔
				Cairo.cairo_stroke(eventData); //Cairo.cairo_fill(eventData)不同的效果
				
				
				//画一个圆弧  有下面实验知道是从x轴沿顺时针开始画图

				//嘴巴的绘制
				double Pi = 3.1415926;
				Cairo.cairo_arc(eventData, circlePoint, circlePoint, 40, 0.25*Pi, 0.75*Pi);
				Cairo.cairo_fill(eventData);
				
				//绘制两只眼睛
				Cairo.cairo_arc(eventData, circlePoint-20, circlePoint-20, 10, 0, 2*Pi);
				Cairo.cairo_arc(eventData, circlePoint+20, circlePoint-20, 10, 0, 2*Pi);
				Cairo.cairo_fill(eventData);
			}
		}, null);
		
		
	}

}
