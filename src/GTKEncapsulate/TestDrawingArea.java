/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月8日上午10:57:47
 * @version   GTKEncapsulateTestDrawingArea V1.0
 *                              测试了OODrawingArea   OODrawCallback 类
 */
public class TestDrawingArea
{

	/**
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		OOWindow owApple = new OOWindow();
		owApple.show();
		owApple.setExitAfterDestroy(true);
		owApple.setTitle("测试绘图接口");
		
		
		///设置网格布局
		OOGrid gridApple  =  new OOGrid();
		gridApple.show();
 		owApple.add(gridApple);
		
		//创建绘图
 		//1 面向对象的方法
		OODrawingArea odaApple = new OODrawingArea();
		odaApple.show();
		//必须setSize否则看不到  这点一定要注意了
		odaApple.setWidgetSize(300, 300);
		gridApple.add(odaApple, 0);
		// 第一种情况重画： 画面被挡住了，揭开画面时候
		// 第二种情况重画： 打开画面饿时候
		odaApple.addDrawListener(new OODrawCallback()
		{
			
			@Override
			public void drawCairo(OOCairo cairo)
			{
				// TODO Auto-generated method stub
				cairo.setPenColor(OORGB.RED);
				cairo.drawRectangle(20, 20, 100, 100);
				cairo.fill();
				cairo.drawCircle(200, 200, 50);
				cairo.stroke();
			}
		});
		
		//原先的面向过程的方法
		int drawpaint = GTK.gtk_drawing_area_new();
		///也需要set_size  默认为0
		GTK.gtk_widget_set_size_request(drawpaint, 100, 100);
		GTK.gtk_widget_show(drawpaint);
		GTK.g_signal_connect(drawpaint, "draw", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				OOCairo oc = new OOCairo(eventData);
				oc.drawCircle(50, 50, 20);
				oc.stroke();
			}
		}, null);
		
		gridApple.add(drawpaint, 1);
		
	
		GTK.gtk_main();
	}

}
