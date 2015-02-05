
package GTKEncapsulate;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午4:17:01
 * @version   GTKEncapsulateTestOOCairo V1.0
 */
import com.rupeng.gtk4j.Cairo;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
public class TestOOCairo
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static int circlePoint=200;
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//GTK初始化
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_widget_show(window);
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				GTK.gtk_main_quit();
			}
		}, null);
		
		gridHouse= GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		GTK.gtk_container_add(window, gridHouse);
		
		
		//创建控件
		int start = 3;
		createDrawLaughFace(window,gridHouse,start,circlePoint);
		
		//启动循环
		GTK.gtk_main();
	}
	
	
	//绘制笑脸
		/**
		 * 
		 * @param window       窗口对象标识
		 * @param gridHouse    网格对象标识
		 * @param start        画布对象在网格布局的起始位置
		 * @param circlePoint  笑脸的圆中心
		 * @purpose            绘制一个笑脸
		 */
		public static void createDrawLaughFace(int window,int gridHouse,int start, final int circlePoint) 
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
					OOCairo oocApple = new OOCairo(eventData);
					oocApple.setPenColor(OORGB.BLUE);
					//Cairo.cairo_set_source_rgb(eventData, 0.5, 0.6, 0.3);
					//设置画笔的大小
					//Cairo.cairo_set_line_width(eventData, 3);	
					oocApple.setPenSize(3);
		
					//画一个圆
					oocApple.drawCircle(circlePoint, circlePoint, 60);
					//Cairo.cairo_arc(eventData, circlePoint, circlePoint, 60, 0, 2*3.1415926);
					//画一个圆弧
					//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
					//显示画笔
					oocApple.stroke();
					//画一个圆弧  有下面实验知道是从x轴沿顺时针开始画图
					//嘴巴的绘制
					double Pi = 3.1415926;
					oocApple.drawArcByClockwise(circlePoint, circlePoint, 40, 0.25*Pi, 0.75*Pi);
				//  Cairo.cairo_arc(eventData, circlePoint, circlePoint, 40, 0.25*Pi, 0.75*Pi);
					oocApple.fill();
					//Cairo.cairo_fill(eventData);
					//绘制两只眼睛
					oocApple.drawArcByClockwise(circlePoint-20, circlePoint, 10, 0, 2*Pi);
					
					oocApple.drawArcByClockwise(circlePoint+20, circlePoint, 10, 0, 2*Pi);
					oocApple.fill();
					//Cairo.cairo_arc(eventData, circlePoint-20, circlePoint-20, 10, 0, 2*Pi);
					//Cairo.cairo_arc(eventData, circlePoint+20, circlePoint-20, 10, 0, 2*Pi);
					//Cairo.cairo_fill(eventData);
				}
			}, null);
		}
		/**
		 * 
		 * @param window          窗口布局对象标识
		 * @param gridHouse       网格布局对象标识
		 * @param start           三毛脸所在的网格布局的起始位置
		 * @purpose               绘制三毛
		 * 
		 */
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
					//唯一一个体现着eventData
					OOCairo oocBanana = new OOCairo(eventData);
					//设置画笔的颜色
					
					oocBanana.setPenColor(OORGB.GREEN);
					//Cairo.cairo_set_source_rgb(eventData, 0.5, 0.6, 0.3);
					//设置画笔的大小
					oocBanana.setPenSize(3);
					//Cairo.cairo_set_line_width(eventData, 3);
					//移动画笔点   画笔默认位置不是在（0,0）move_to不可少
					//Cairo.cairo_move_to(eventData, 0, 0);

					//画一个圆
					oocBanana.drawCircle(180, 180, 60);
					//Cairo.cairo_arc(eventData, 180, 180, 60, 0, 2*3.1415926);
					//画一个圆弧
					oocBanana.drawArcByClockwise(200, 200, 70, 1.5*3.1415925, 2*3.1415925);
					//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
					//显示画笔
					oocBanana.stroke();
					//Cairo.cairo_stroke(eventData); //Cairo.cairo_fill(eventData)不同的效果
					//画一个圆弧  有下面实验知道是从x轴沿顺时针开始画图
					//嘴巴的绘制
					double Pi = 3.1415926;
					oocBanana.drawArcByClockwise( 180, 180, 40, 0.25*Pi, 0.75*Pi);
					//Cairo.cairo_arc(eventData, 180, 180, 40, 0.25*Pi, 0.75*Pi);
					oocBanana.fill();
					//Cairo.cairo_fill(eventData);
					
					//绘制两只眼睛
					oocBanana.drawArcByClockwise(160, 160, 10, 0, 2*Pi);
					oocBanana.drawArcByClockwise(200, 160, 10, 0, 2*Pi);
					oocBanana.fill();
				/*	Cairo.cairo_arc(eventData, 160, 160, 10, 0, 2*Pi);
					Cairo.cairo_arc(eventData, 200, 160, 10, 0, 2*Pi);
					Cairo.cairo_fill(eventData);*/
					//绘制胡须
					oocBanana.drawArcByClockwise(130, 160, 50, 0.1666668*Pi, 0.6777777*Pi);
					oocBanana.stroke();
					oocBanana.drawArcByClockwise(230, 160, 50, 0.333333*Pi, 0.8333333*Pi);
					oocBanana.stroke();
					/*Cairo.cairo_arc(eventData, 130, 160, 50, 0.166667*Pi, 0.6777777*Pi);
					Cairo.cairo_stroke(eventData);
					Cairo.cairo_arc(eventData, 230, 160, 50, 0.333333*Pi, 0.8333333*Pi);
					Cairo.cairo_stroke(eventData);*/
					//绘制头发
					oocBanana.drawArcByClockwise(133.6, 136.72, 30, 1.3333333*Pi, 1.8333333*Pi);
					oocBanana.stroke();
					oocBanana.drawArcByClockwise(224.4, 136.72, 30, 1.1666667*Pi, 1.6777777*Pi);
					oocBanana.stroke();
/*					Cairo.cairo_arc(eventData, 133.6, 136.72, 30,1.3333333*Pi ,1.833333*Pi );
					Cairo.cairo_stroke(eventData);
					Cairo.cairo_arc(eventData, 224.4, 136.72, 30, 1.166667*Pi, 1.6777777*Pi);
					Cairo.cairo_stroke(eventData);*/
					//移动画笔点   画笔默认位置不是在（0,0）move_to不可少
					
					oocBanana.drawLine(180, 20, 180, 90);
					oocBanana.stroke();
				/*	Cairo.cairo_move_to(eventData, 180, 120);
	 			   //画一条线七点
					Cairo.cairo_line_to(eventData, 180, 90);
					Cairo.cairo_stroke(eventData);*/
					//画文字
					oocBanana.moveTo(180, 260);
					oocBanana.setPenColor(new OORGB(0.4,0.3,0.2));	
					oocBanana.setFontSize(16);
					oocBanana.setFontType();
					oocBanana.showText("三毛的自画像");
/*					Cairo.cairo_move_to(eventData, 180, 260);
					Cairo.cairo_set_source_rgb(eventData, 0.4, 0.3, 0.2);
					Cairo.cairo_set_font_size(eventData, 16);
					//选择文字类型
					Cairo.cairo_select_font_face(eventData, "宋体", Cairo.CAIRO_FONT_SLANT_ITALIC, Cairo.CAIRO_FONT_WEIGHT_BOLD);
					Cairo.cairo_show_text(eventData,"三毛的自画像");*/
					//Cairo.cairo_stroke(eventData);  //不需要这句话也可以
				}
			}, null);
		}
		

}
