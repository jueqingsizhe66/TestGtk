import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author Ҷ����
 * @version ComboBoxText v1.0
 *
 */
public class TestGtkComboBoxText
{

	/**
	 * @param args
	 */
	static int window;
	//static int box;
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL); 
		GTK.gtk_window_set_title(window, "SpinBoxV1.0");
		GTK.gtk_widget_show(window);
		//��ȫ�ر�GTK
		GTK.g_signal_connect(window, "destroy", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
		}, null);
		// ��������
		gridHouse =  GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		createComboBoxText(window,gridHouse,0);
		
		GTK.gtk_container_add(window,gridHouse);
		//����ѭ��
		GTK.gtk_main();
	}
	
	public static void createComboBoxText(int window,int gridHouse,int start)
	{
		final int comboBoxBig = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "ƻ��");
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "�㽶");
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "����");
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "����");
		GTK.gtk_combo_box_text_append_text(comboBoxBig, "����");
		final int comboBoxApple = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxApple, "��ƻ��");
		GTK.gtk_combo_box_text_append_text(comboBoxApple, "��ƻ��");
		GTK.gtk_combo_box_text_append_text(comboBoxApple, "Сƻ��");
		final int comboBoxBanana = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxBanana, "�챦��");
		GTK.gtk_combo_box_text_append_text(comboBoxBanana, "��");
		GTK.gtk_combo_box_text_append_text(comboBoxBanana, "���㽶");
		final int comboBoxGrape = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxGrape, "������");
		GTK.gtk_combo_box_text_append_text(comboBoxGrape, "õ����");
		GTK.gtk_combo_box_text_append_text(comboBoxGrape, "�޷�");
		GTK.gtk_combo_box_text_append_text(comboBoxGrape, "�ĺ�");
		final int comboBoxOrange = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxOrange, "С����");
		GTK.gtk_combo_box_text_append_text(comboBoxOrange, "������");
		GTK.gtk_combo_box_text_append_text(comboBoxOrange, "����");
		final int comboBoxYouzi = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(comboBoxYouzi, "��������");
		GTK.gtk_combo_box_text_append_text(comboBoxYouzi, "��������");
		GTK.gtk_combo_box_text_append_text(comboBoxYouzi, "������");
		
	/*	int label1 = GTK.gtk_label_new("����ǰ��Ҫ�������"+GTK.gtk_combo_box_text_get_active_text(comboBoxBig)
				+"�µ�"+GTK.gtk_combo_box_text_get_active_text(comboBoxApple));*/
		final int label1 = GTK.gtk_label_new("����ǰ��Ҫ�������?");
		
		// ���ӿؼ������ⷿ��
		
		GTK.gtk_grid_attach(gridHouse, comboBoxBig, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxApple, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxBanana, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxGrape, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxOrange, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, comboBoxYouzi, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, label1, 0, start+1, 1, 1);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(comboBoxBig);
		GTK.gtk_widget_show(comboBoxApple);
		GTK.gtk_widget_show(label1);
		
		//����combobox�ļ���״̬
		GTK.gtk_combo_box_set_active(comboBoxBig, 0);
		GTK.gtk_combo_box_set_active(comboBoxApple, 0);
		
		//�����¼�
		GTK.g_signal_connect(comboBoxBig, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				String fruit = GTK.gtk_combo_box_text_get_active_text(comboBoxBig);
				if(fruit.equalsIgnoreCase("ƻ��"))
				{
					helpFunction(comboBoxApple,label1);
					GTK.gtk_widget_hide(comboBoxBanana);
					GTK.gtk_widget_hide(comboBoxGrape);
					GTK.gtk_widget_hide(comboBoxOrange);
					GTK.gtk_widget_hide(comboBoxYouzi);
				}else if(fruit.equalsIgnoreCase("�㽶"))
				{
					helpFunction(comboBoxBanana,label1);
					GTK.gtk_widget_hide(comboBoxApple);
					GTK.gtk_widget_hide(comboBoxGrape);
					GTK.gtk_widget_hide(comboBoxOrange);
					GTK.gtk_widget_hide(comboBoxYouzi);
				}else if(fruit.equalsIgnoreCase("����"))
				{
					helpFunction(comboBoxGrape,label1);
					GTK.gtk_widget_hide(comboBoxApple);
					GTK.gtk_widget_hide(comboBoxBanana);
					GTK.gtk_widget_hide(comboBoxOrange);
					GTK.gtk_widget_hide(comboBoxYouzi);
				}else if(fruit.equalsIgnoreCase("����"))
				{
					helpFunction(comboBoxOrange,label1);
					GTK.gtk_widget_hide(comboBoxApple);
					GTK.gtk_widget_hide(comboBoxBanana);
					GTK.gtk_widget_hide(comboBoxGrape);
					GTK.gtk_widget_hide(comboBoxYouzi);	
				}
				else if(fruit.equalsIgnoreCase("����"))
				{
					helpFunction(comboBoxYouzi,label1);
					GTK.gtk_widget_hide(comboBoxApple);
					GTK.gtk_widget_hide(comboBoxBanana);
					GTK.gtk_widget_hide(comboBoxGrape);
					GTK.gtk_widget_hide(comboBoxOrange);	
				}
			}
		}, null);
		
	}
	
	public static void helpFunction(final int comboBoxText,final int label1)
	{
		GTK.gtk_widget_show(comboBoxText);
		GTK.gtk_combo_box_set_active(comboBoxText, 0);
		GTK.g_signal_connect(comboBoxText, "changed", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object) 
			{
				String fruit = GTK.gtk_combo_box_text_get_active_text(comboBoxText);
				GTK.gtk_label_set_text(label1, "����Ҫ�����"+fruit+"û���ˣ��뵽��ҹ���");
			};
		}, null);
	}
}