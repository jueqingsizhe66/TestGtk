package GTKEncapsulate;

/**
 * @author    Ҷ����
 * @time      2015��2��4������10:30:18
 * @version   GTKEncapsulateOOEntry V1.0
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
public class OOWidget
{
	//ÿһ���ؼ�����Ҫͨ���ؼ��������Ψһ�ı�ʶ
	private int Id;
	
	/**
	 * 
	 * @return  ����GTKWidget��ID���߽��������ʶ  ��C���Ե�ָ�� ���
	 */
	public int getId()
	{
		if(this.Id == 0)
		{
			throw new IllegalArgumentException();
		}
		return this.Id;
	}
	
	////protectedֻ�ܱ��������ͬ��(package)�е�����ʣ������������Է���
	// �ߣ������
	//public void setId(int Id)
	protected  void setId(int Id)
	{
		this.Id = Id;
	}
	
	//��װ���õ�widget���� �������»���
	//������
	//------------------------------------------------
	/**
	 * 
	 * @param width     ���ÿؼ��Ŀ���
	 * @param height    ���ÿռ�ĸ߶�
	 * 
	 */
	public void setWidgetSize(int width, int height)
	{
		GTK.gtk_widget_set_size_request(this.Id, width, height);
	}
	
	/**
	 * 
	 * @param isSensitive   ���ÿؼ��Ƿ����ã����ǽ��ã�
	 */
	public void setWidgetSensitive(boolean isSensitive)
	{
		GTK.gtk_widget_set_sensitive(this.Id, isSensitive);
	}
	// ��ʾ   ����   �ݻ� �� 
	//------------------------------------------------
	/**
	 *   ��ʾ�ؼ�
	 */
	public void show()
	{
		GTK.gtk_widget_show(getId());
	}
	/**
	 *   ���ؿؼ�
	 */
	public void hide()
	{
		GTK.gtk_widget_show(getId());
	}
	
	/**
	 *    �ݻٿؼ�
	 */
	public void destroy()
	{
		GTK.gtk_widget_destroy(getId());
	}
	
	
	// �����¼��� ,��ȷ�������ŷ�װ��˼��
	// add***Listener  �����˼�����
	//------------------------------------------------
	/**
	 * 
	 * @param callback   һ��IGCallback�Ļ�������  ��Ҫʵ��execute����
	 *                   ����clicked�Ŀؼ��¼����������е�widget
	 *                   �����������¼�����
	 */
	public void addClickedListener(IGCallBack callback)
	{
		//this.Id Ҳ������ getId�ķ���  ������û����� getId��Ϊ�������쳣����
		//GTK.g_signal_connect(this.Id, "click", callback, null); 
		GTK.g_signal_connect(getId(), "click", callback, null);
	}
	/**
	 * 
	 * @param callback   һ��IGCallback�Ļ�������  ��Ҫʵ��execute����
	 *                   ���ӿؼ���destroy���¼�����
	 */
	public void addDestroyListener(IGCallBack callback)
	{
		GTK.g_signal_connect(getId(),"destroy",callback,null);
	}
	
	//////////������ÿһ���ռ�Ӧ�ö����ܹ�������Ϣ��С���ڵģ��������벻�Ե�ʱ��
	/**
	 * 
	 * @param message    ��Ϣ���ڵ���Ϣ
	 * @param title      ��Ϣ���ڵ���Ϣ
	 * @return           ����һ����Ϣ����  ����ֵΪok
	 */
	public static boolean showInfo(  String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}
	/**
	 * 
	 * @param message    ���ⴰ�ڵ���Ϣ
	 * @param title      ���ⴰ�ڵ���Ϣ
	 * @return           ����һ��YESNO ���ⴰ��  ��ΧֵΪ�ǲ���
	 */
	public static boolean showYesNo( String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_YES_NO,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret=GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_YES;
	}
	/**
	 * 
	 * @param message    ȷ�ϴ��ڵ���Ϣ
	 * @param title      ȷ�ϴ��ڵ���Ϣ
	 * @return           ����һ��OKCANCEL ȷ�ϴ���  ����ֵΪ�Ƿ�ȷ��
	 */
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