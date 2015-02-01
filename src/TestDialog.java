import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
/**
 * 
 */

/**
 * @author    Ҷ����
 * @time      2015��2��1������3:54:46
 * @version   TestDialog V1.0
 *             TestDialog V2.0    ��errorMessage ��Ϊ2��Ƕ��messagebox ����GTK_WIDGET_SET_TITLE..���ñ���
 */
public class TestDialog
{

	/**
	 * @param args
	 */
	static int window;  
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_widget_show(window);
		GTK.gtk_widget_set_size_request(window,500,500);
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
		}, null);
		//�������񲼾�
		gridHouse = GTK.gtk_grid_new();
		
		//����button�ؼ�
		int btnShowDialog = GTK.gtk_button_new_with_label("show Dialog");
		int btnShowMessage = GTK.gtk_button_new_with_label("show MessageBox");
		int btnErrorMessage = GTK.gtk_button_new_with_label("show error");
		int btnQuestionMessage = GTK.gtk_button_new_with_label("show question");
		//���ӿؼ�
		GTK.gtk_grid_attach(gridHouse, btnShowDialog, 0, 0, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnShowMessage, 1, 0, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnErrorMessage, 0, 1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnQuestionMessage, 1, 1, 1, 1);
		GTK.gtk_container_add(window, gridHouse);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(btnShowDialog);
		GTK.gtk_widget_show(btnShowMessage);
		GTK.gtk_widget_show(btnErrorMessage);
		GTK.gtk_widget_show(btnQuestionMessage);
		GTK.gtk_widget_show(gridHouse);
		//�����¼�
		//�����Ի����¼�
		GTK.g_signal_connect(btnShowDialog, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				int dlApple = GTK.gtk_dialog_new();
				//GTK.gtk_widget_show(dlApple); //��Ҫ����  �����޷���ʾ
				GTK.gtk_dialog_add_button(dlApple, "������ӭ��", 0);
				
				//int btnBanana = GTK.gtk_button_new_with_label("tryut");
				//GTK.gtk_container_add(dlApple, btnBanana);
				//������ gkt_main()������ ����dialogѭ��
				GTK.gtk_dialog_run(dlApple); //
				GTK.gtk_widget_destroy(dlApple);
				
			}
		}, null);
		//��ʾ��Ϣ��Ϣ�Ի���
		GTK.g_signal_connect(btnShowMessage, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//flags��һ��ȡGTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL��
				//type:  GTK_MESSAGE_INFO����Ϣ��
				//       GTK_MESSAGE_WARNING�����棻��
				//       GTK_MESSAGE_QUESTION������
				//       GTK_MESSAGE_ERROR������
				//button��  GTK_BUTTONS_OK��ȷ����GTK_BUTTONS_CLOSE���رգ�  YES NO.. CANCEL
				//message:  �ַ���
				int dlApple = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL, 
						GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK, "����");

				//GTK.gtk_widget_show(dlApple); //��Ҫ����  �����޷���ʾ
				GTK.gtk_dialog_add_button(dlApple, "������ӭ��", 0);
				
				//������ gkt_main()������ ����dialogѭ��
				int ret = GTK.gtk_dialog_run(dlApple); //
				if(ret == GTK.GTK_RESPONSE_OK)
				{
					System.out.println("�������˻س�");
					//GTK.gtk_dialog_response(dlApple, ret);
					GTK.gtk_widget_destroy(dlApple); //�������ٶԻ���
				}
			}
		}, null);
		
		GTK.g_signal_connect(btnErrorMessage, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//flags��һ��ȡGTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL��
				//typeӰ�쵽��״
				//type:  GTK_MESSAGE_INFO����Ϣ��
				//       GTK_MESSAGE_WARNING�����棻��
				//       GTK_MESSAGE_QUESTION������
				//       GTK_MESSAGE_ERROR������
				//buttonӰ�쵽�¼�
				//button��  GTK_BUTTONS_OK��ȷ����GTK_BUTTONS_CLOSE���رգ�  YES NO.. CANCEL
				//message:  �ַ���
				int dlApple = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL, 
						GTK.GTK_MESSAGE_ERROR, GTK.GTK_BUTTONS_YES_NO, "����ϵͳ��Ҫ����");
	
				//������ gkt_main()������ ����dialogѭ��
				int ret = GTK.gtk_dialog_run(dlApple); //
				if(ret == GTK.GTK_RESPONSE_YES)
				{
//					System.out.println("��������yes");
					int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
							GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,"����Ľ��˰������˰˼���");
					GTK.gtk_window_set_title(msgDlg, "���˰���");
					GTK.gtk_dialog_run(msgDlg);
					GTK.gtk_widget_destroy(msgDlg);
					//GTK.gtk_widget_destroy(dlApple);
				}else if(ret == GTK.GTK_RESPONSE_NO)
				{
					//���÷������з�װ������ ���˺ܶ����
					showInfo("����Ĳ����� �һ�һֱ�����","������");
					//GTK.gtk_widget_destroy(dlApple);
				}
				GTK.gtk_widget_destroy(dlApple);
			}
		}, null);
		
		
		GTK.g_signal_connect(btnQuestionMessage, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//flags��һ��ȡGTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL��
				//typeӰ�쵽��״
				//type:  GTK_MESSAGE_INFO����Ϣ��
				//       GTK_MESSAGE_WARNING�����棻��
				//       GTK_MESSAGE_QUESTION������
				//       GTK_MESSAGE_ERROR������
				//buttonӰ�쵽�¼�
				//button��  GTK_BUTTONS_OK��ȷ����GTK_BUTTONS_CLOSE���رգ�  YES NO.. CANCEL
				//message:  �ַ���
				int dlApple = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL, 
						GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_OK_CANCEL, "�ǲ��ǿ�ëƬ�ˣ�");
	
				//������ gkt_main()������ ����dialogѭ��
				int ret = GTK.gtk_dialog_run(dlApple); //
				if(ret == GTK.GTK_RESPONSE_CANCEL)
				{
					System.out.println("��������CANCEL");
//					GTK.gtk_widget_destroy(dlApple);
				}else if(ret == GTK.GTK_RESPONSE_OK)
				{
					System.out.println("��������OK");
//					GTK.gtk_widget_destroy(dlApple);
				}/*else if(ret == GTK.GTK_RESPONSE_DELETE_EVENT) //����ر��¼��ġ�������Ҫ�ж�
				{
					System.out.println("���ر��˶Ի���");
				}*/
				GTK.gtk_widget_destroy(dlApple);
			}
		}, null);
		GTK.gtk_main();
	}
	public static boolean showInfo(  String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}
	
	public static boolean showYesNo( String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_YES_NO,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret=GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_YES;
	}
	public static boolean showOkCancel( String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_OK_CANCEL,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}

}