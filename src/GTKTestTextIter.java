import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;



/**
 * @author Ҷ����
 * @version  TestView+TestIter v1.0
 * @version  TestView+TestIter v1.1 �����˹�����������
 */
public class GTKTestTextIter
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
		GTK.gtk_window_set_title(window, "Ů���Ѹ������ V1.0");
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
		
		int start  = 0;
		createTextView(window,gridHouse,start);
		GTK.gtk_container_add(window,gridHouse);
		//����ѭ��
		GTK.gtk_main();
	}
	
	public static void createTextView(int window, int gridHouse, int start)
	{
		int imgYumu = GTK.gtk_image_new_from_resource("yumufeng.jpg");
		final int tvGirl = GTK.gtk_text_view_new();
		final int label = GTK.gtk_label_new("");
		final int labelIter = GTK.gtk_label_new("");
		int btnShow = GTK.gtk_button_new_with_label("��ʾ");
		int btnShowIter = GTK.gtk_button_new_with_label("����������");
		GTK.gtk_text_view_set_wrap_mode(tvGirl, GTK.GTK_WRAP_WORD);
		int scrollBar = 0 ;
		//���ӿؼ�
		GTK.gtk_grid_attach(gridHouse, imgYumu, 0, start, 1, 1);
		createScrollBar(scrollBar,tvGirl,gridHouse,start);
		//GTK.gtk_grid_attach(gridHouse, tvGirl, 0, start+1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, label, 0 ,start+2, 1,1);
		GTK.gtk_grid_attach(gridHouse, labelIter, 0 ,start+3, 1,1);
		GTK.gtk_grid_attach(gridHouse, btnShow, 1 ,start+2, 1,1);
		GTK.gtk_grid_attach(gridHouse, btnShowIter, 1 ,start+3, 1,1);
		//��ʾ�ؼ�
		GTK.gtk_widget_show(imgYumu);
		GTK.gtk_widget_show(tvGirl);
		GTK.gtk_widget_show(label);
		GTK.gtk_widget_show(btnShow);
		GTK.gtk_widget_show(labelIter);
		GTK.gtk_widget_show(btnShowIter);
		
		//��ȡ�ı������������ ��ֻ����С�����ı���һ���õ�����
		//����1   �ȴ�TextView��ȡint TextBuffer 
		    //    Ȼ���ٴ�TextBuffer��ȡtext
		final int textbuffer= GTK.gtk_text_view_get_buffer(tvGirl);
		String loveWords = "��������������ǣ���׺�����������ǿգ��貶�Ŀ��"
				+ "��Ը����Я�ֹ�ͬ�ܽ���ԭ����㹲�����ǵ�sons and grils,"
				+ "������һ���Ҹ��ļ�ͥ";
		
		//����ֱ��ͨ�����������  ������Ϣ
		GTK.gtk_text_buffer_set_text(textbuffer, loveWords);
		//���Ӱ�ť�¼�
		GTK.g_signal_connect(btnShow, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				String text = GTK.gtk_text_buffer_get_text(textbuffer);
				GTK.gtk_label_set_text(label, text);
			}
		}, null);
		
		
		GTK.g_signal_connect(btnShowIter, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				helpFunction(tvGirl);
			}
		}, null);
		
	}
	//��α�����ȡtextview��Ϣ���� ��ǰֻ�ܲ��룿
	public static void helpFunction(int textview)
	{
		//TextIter��һ��TextView�ĵ�������
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //����һ���յ�iter����Ҫ��textBuffer���и�ֵ
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// ����textview��textBuffer��ĩβ��
		GTK.gtk_text_buffer_insert(textBuffer, textIter, "I love you! Xinran");
		
		//GTK.gtk_text_buffer_g
		GTK.gtk_text_iter_free(textIter);
		
	}
	
	public static void createScrollBar(int scrolledBar,int textview,int gridHouse,int start)
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrolledBar);
		GTK.gtk_grid_attach(gridHouse, scrolledBar, 0, start+1, 1, 1);
		GTK.gtk_widget_set_size_request(scrolledBar, 300, 50);
		GTK.gtk_container_add(scrolledBar,textview);
	}

}