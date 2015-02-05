/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午12:59:42
 * @version   GTKEncapsulateTestPasswordAndBox V1.0
 *               测试了 OOPassword   OOEntry   OOLable  OOBox是否可用 
 *               结果可以运行 并修改了OOWidget的内部的click事件 信号从click 变为clicked
 */
public class TestPasswordAndBox
{

	/**
	 * @param args
	 */
	static int window;
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
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
		
		
		OOBox ob = new OOBox();
		ob.show();
		GTK.gtk_container_add(window, ob.getId());
		
		final OOPassword op = new OOPassword();
		op.show();
		op.setText("hello");
		
		ob.addWidget(op.getId());
		
		OOLabel ol = new OOLabel("");
		ol.show();
		ol.setText(op.getText());
		
		ob.addWidget(ol.getId());
		
		OOButton btnApple = new OOButton("显示密码框的值");
		btnApple.show();
		btnApple.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				OOWidget.showInfo(op.getText(), "密码框的值");
			}
		});
		ob.addWidget(btnApple.getId());
		
		
		GTK.gtk_main();
	}

}
