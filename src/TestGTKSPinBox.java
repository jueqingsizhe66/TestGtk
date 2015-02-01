import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author Ҷ����
 * @version SpinBoxv1.0
 */
public class TestGTKSPinBox
{

	/**
	 * @param args
	 *  
	 */
	static int window;
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		GTK.gtk_init();
		window  = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "����SpinBox");
		GTK.g_signal_connect(window, "destroy", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
		}, null);
		GTK.gtk_widget_show(window);
		
		
		//��ʼ��������
		 gridHouse = GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		//��ʼ����ؼ�
		int start = 0 ;
		createSpinbox(window,gridHouse,start);
		
		GTK.gtk_container_add(window, gridHouse);
		GTK.gtk_main();
	}
	
	public static void createSpinbox(int window,int gridHouse,int start)
	{
		//����ؼ�
		int label1 = GTK.gtk_label_new("���֣�0-9");
		int label2 = GTK.gtk_label_new("��Χ: -10~10");
		final int labelSpinbox = GTK.gtk_label_new("");
		final int labelSpinboxApple = GTK.gtk_label_new("");
		final int spinbox = GTK.gtk_spin_button_new_with_range(0, 9, 1);
		final int spinboxApple = GTK.gtk_spin_button_new_with_range(-10, 10, 0.1);
		
	
		//���ӿؼ������ⷿ��
		GTK.gtk_grid_attach(gridHouse, label1, 0, start, 1, 1); 
		GTK.gtk_grid_attach(gridHouse, spinbox, 1, start, 1, 1); 
		GTK.gtk_grid_attach(gridHouse, label2, 0, start+1, 1, 1); 
		GTK.gtk_grid_attach(gridHouse, spinboxApple, 1, start+1, 1, 1);
		
		GTK.gtk_grid_attach(gridHouse, labelSpinbox, 0, start+3, 1, 1);
		GTK.gtk_grid_attach(gridHouse, labelSpinboxApple, 0, start+4, 1, 1);
		//��ʾ�ؼ�
		GTK.gtk_widget_show(label1);
		GTK.gtk_widget_show(spinbox);
		GTK.gtk_widget_show(label2);
		GTK.gtk_widget_show(spinboxApple);
		GTK.gtk_widget_show(labelSpinbox);
		GTK.gtk_widget_show(labelSpinboxApple);
		GTK.gtk_widget_show(gridHouse);
		
		
		//�����¼�
		GTK.g_signal_connect(spinbox, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				double apple = GTK.gtk_spin_button_get_value(spinbox);
				GTK.gtk_label_set_text(labelSpinbox, "����ǰѡ�������ʽ��"+Double.toString(apple));
				
			}
		}, null);
		
		GTK.g_signal_connect(spinboxApple, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				double apple = GTK.gtk_spin_button_get_value(spinboxApple);
				GTK.gtk_label_set_text(labelSpinboxApple, "����ǰѡ�������ʽ��"+Double.toString(apple));
				
			}
		}, null);
	}

}