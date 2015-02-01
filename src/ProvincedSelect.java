import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * 
 */

/**
 * @author Ҷ����
 * @version ʡ�������� V1.0
 *
 */
public class ProvincedSelect
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static int cmbProvince;
	static int cmbCity;
	static int labelShow;
	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		//��ʼ��
		GTK.gtk_init();
		//��������
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//��ʾ����
		GTK.gtk_widget_show(window);
		// �����ر�
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
			
		}, null);
		//��������
		gridHouse = GTK.gtk_grid_new();			
		GTK.gtk_widget_show(gridHouse);	
		//�������ⷿ
		GTK.gtk_container_add(window, gridHouse);
		//�����ؼ�
		//
		labelShow = GTK.gtk_label_new("");
		cmbProvince = GTK.gtk_combo_box_text_new();
		cmbCity     = GTK.gtk_combo_box_text_new();

		
		int start =  0;
		//���ӿؼ�
		GTK.gtk_grid_attach(gridHouse, cmbProvince, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, cmbCity, 0,start+1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, labelShow, 1,start+2, 1, 1);

		//��ʾ�ؼ�

		GTK.gtk_widget_show(cmbProvince);
		GTK.gtk_widget_show(cmbCity);
		GTK.gtk_widget_show(labelShow);
		createProvince(window);
		

		
		//����ѭ��
		GTK.gtk_main();

	}
	
	public static void createProvince(int window)
	{
		

		GTK.gtk_combo_box_text_append(cmbProvince, "fj", "����");
		GTK.gtk_combo_box_text_append(cmbProvince, "bj", "����");
		//GTK.gtk_combo_box_text_append(cmbProvince, "sh", "�Ϻ�");
		GTK.gtk_combo_box_text_append(cmbProvince, "hn", "����");
		GTK.gtk_combo_box_text_append(cmbProvince, "hb", "�ӱ�");
		GTK.gtk_combo_box_text_append(cmbProvince, "sd","ɽ��");
		
		GTK.gtk_combo_box_set_active_id(cmbProvince, "bj");

//�����¼�
		GTK.g_signal_connect(cmbProvince, "changed", new IGCallBack() 
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				
				String province  =  GTK.gtk_combo_box_get_active_id(cmbProvince);
				if(province==null)return;
				System.out.println(province);
				GTK.gtk_combo_box_text_remove_all(cmbCity); //ɾ��������
				//GTK.gtk_combo_box_text_remove_all(cmbCity); //ɾ��������
				if(province.equals("fj"))
				{
					System.out.println("����������");
					GTK.gtk_combo_box_text_append(cmbCity, "zhz", "����");
					GTK.gtk_combo_box_text_append(cmbCity, "xm", "����");
					GTK.gtk_combo_box_text_append(cmbCity, "fz", "����");
					GTK.gtk_combo_box_text_append(cmbCity, "qz", "Ȫ��");
					
				}else if(province.equals("bj"))
				{
					System.out.println("����������");
					GTK.gtk_combo_box_text_append(cmbCity, "cp", "��ƽ��");
					GTK.gtk_combo_box_text_append(cmbCity, "hd", "������");
					GTK.gtk_combo_box_text_append(cmbCity, "tz", "ͨ����");
					GTK.gtk_combo_box_text_append(cmbCity, "cy", "������");
				}else if(province.equals("hn"))
				{
					System.out.println("����������");
			       GTK.gtk_combo_box_text_append(cmbCity, "zz", "֣��");
			       GTK.gtk_combo_box_text_append(cmbCity, "zmd", "פ����");
			       GTK.gtk_combo_box_text_append(cmbCity, "ny", "����");
				}else if(province.equals("hb"))
				{
					System.out.println("����������");
			       GTK.gtk_combo_box_text_append(cmbCity, "sjz", "ʯ��ׯ");
			       GTK.gtk_combo_box_text_append(cmbCity, "ts", "��ɽ");
			       GTK.gtk_combo_box_text_append(cmbCity, "qhd", "�ػʵ�");
				}else if(province.equals("sd"))
				{
					System.out.println("����������");
				   GTK.gtk_combo_box_text_append(cmbCity, "jn", "����");
			       GTK.gtk_combo_box_text_append(cmbCity, "qd", "�ൺ");
			       GTK.gtk_combo_box_text_append(cmbCity, "yt", "��̨");
				}
			}	
		}, null);
		
		//ԭ��remove֮�� Ҳ�ǻ�ٷ�cmbCity�� �ź�  �����¸ı� ����Դ�������
		GTK.g_signal_connect(cmbCity, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
 				String tempProvince = GTK.gtk_combo_box_text_get_active_text(cmbProvince);
				
				int  apple  = GTK.gtk_combo_box_get_active(cmbCity);
				if(apple < 0)
				{
					System.out.println("nothing in the city");
					return;
				}
				
				String tempCity = GTK.gtk_combo_box_text_get_active_text(cmbCity);
				if(tempProvince==null||tempCity==null)return;
				//GTK.gtk_label_set_text(labelShow, "��׼��ȥ"+tempProvince+tempCity);
				GTK.gtk_label_set_text(labelShow,tempProvince+tempCity);
				System.out.println(tempProvince);
				System.out.println(tempCity);

			}
		}, null);
		
		
	}

}