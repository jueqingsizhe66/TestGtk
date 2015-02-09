/**
 * 
 */
package TestGTKEncapsulate;
import GTKEncapsulate.*;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��6������12:59:42
 * @version   GTKEncapsulateTestPasswordAndBox V1.0
 *               ������ OOPassword   OOEntry   OOLable  OOBox�Ƿ���� 
 *               ����������� ���޸���OOWidget���ڲ���click�¼� �źŴ�click ��Ϊclicked
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
		
		ob.addWidget(op);
		
		OOLabel ol = new OOLabel("");
		ol.show();
		ol.setText(op.getText());
		
		ob.addWidget(ol);
		
		OOButton btnApple = new OOButton("��ʾ������ֵ");
		btnApple.show();
		btnApple.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				OOMessageDialog.showInfo(op.getText(), "������ֵ");
			}
		});
		ob.addWidget(btnApple);
		
		
		GTK.gtk_main();
	}

}