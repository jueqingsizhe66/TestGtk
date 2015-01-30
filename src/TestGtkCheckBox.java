import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * 
 */

/**
 * @author Ҷ����
 * @version  v1.0
 */
public class TestGtkCheckBox
{

	/**
	 * @param args
	 */
	private static int window ;
	private static int box;
	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������

	//	gtkHead(window,"gtkCheckbox����");
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "hello");
		GTK.gtk_widget_show(window);
		GTK.g_signal_connect(window,"destroy",new IGCallBack() 
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
			
		},null);
		box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
		int box2= GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL,0);
		int box1= GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL,0);
		testGtkCheckBox(window,box2);
		testGtkCheckBoxInstall(window,box1);
		GTK.gtk_box_pack_start(box, box2, false, false, 0);
		GTK.gtk_box_pack_start(box, box1, false, false, 0);

		GTK.gtk_container_add(window, box);
		GTK.gtk_widget_show(box);
		GTK.gtk_main();
		
	}

	
	public static void testGtkCheckBox(int window,int box2)
	{
	
		int cbMan = GTK.gtk_check_button_new_with_label("��");
		int cbWoman = GTK.gtk_check_button_new_with_label("Ů");
		
		
		GTK.gtk_box_pack_start(box2, cbMan, false,false	, 0);
		GTK.gtk_box_pack_start(box2, cbWoman, false,false	, 0);
		
		
		GTK.gtk_widget_show(cbMan);
		GTK.gtk_widget_show(cbWoman);
		GTK.gtk_widget_show(box2);
	
	}
	public static void testGtkCheckBoxInstall(int window,int box1)
	{
		int entryLicense = GTK.gtk_entry_new();
		GTK.gtk_entry_set_text(entryLicense, "��Ʒʹ������:\n ����ISIS���ʱ�׼");

		//����ؼ�
		int boxInner = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		//ΪʲôҪ�޸�Ϊ��̬
		final int cbAgree = GTK.gtk_check_button_new_with_label("��ͬ�������Э��");
		final int btnInstall = GTK.gtk_button_new_with_label("install");
		int btnClosed  = GTK.gtk_button_new_with_label("close");
		GTK.gtk_widget_set_sensitive(btnInstall, false);
		
		//���ӿؼ�
		GTK.gtk_box_pack_start(box1, entryLicense, false,false	, 0);
		GTK.gtk_box_pack_start(box1, cbAgree, false,false, 0);
		GTK.gtk_box_pack_start(boxInner, btnInstall, false,false,0);
		GTK.gtk_box_pack_start(boxInner, btnClosed, false,false,0);
		GTK.gtk_box_pack_start(box1, boxInner, false,false	, 0);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(entryLicense);
		GTK.gtk_widget_show(cbAgree);
		GTK.gtk_widget_show(btnInstall);
		GTK.gtk_widget_show(btnClosed);
		GTK.gtk_widget_show(boxInner);
		GTK.gtk_widget_show(box1);
		
		//�����¼�
		GTK.g_signal_connect(cbAgree, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				boolean isChecked =  GTK.gtk_toggle_button_get_active(cbAgree);
				
				//д��gtk_checkbox����û��set���� ��������widget..
				GTK.gtk_widget_set_sensitive(btnInstall, isChecked);
			}
		}, null);
	}

}