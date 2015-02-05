import com.rupeng.gtk4j.Cairo;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��3������10:24:23
 * @version   TestTeacher V1.0   һ�����׵Ĵ�������ģ�ⴰ�ڣ�δ�������ĸ���������ͼƬ����
 *                               ��Ҫ����textview  treeview    cairo���������ؼ���
 */
public class TestTeacher
{

	/**
	 * @param args
	 */
	//����������Ķ���
	static int window;
	static int gridHouse;

	static int liststore;
	static int listiter;
	static int treeViewApple;
	static int imagePosition = 0;
	static int rowPosition = 0;
	//static int dan ;
	static int start = 0;
	static int circlePointx = 200;
	static int circlePointy = 45;
	
	static int circleRectangex = 200;
	static int circleRectangey = 0;
	
	static int EndCirclex = 0;
	static int EndCircley = 0;
	static String note = "ָ������";
	static int labelResult;
	
	//static String loveWords;
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//GTK��ʼ��
		GTK.gtk_init();
		//���ڶ����ʶ�Ĵ���
		window= GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//��ʾ����
		GTK.gtk_widget_show(window);
		//�����رմ���
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				GTK.gtk_main_quit();
			}
		}, null);
		
		//�������񲼾�
		gridHouse = GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		//�������񲼾ֵ�window��
		GTK.gtk_container_add(window, gridHouse);
		
		//����һЩ���õĿؼ�
		
		//����һ��treeview ������ʾ�û���Ϣ
		createSingleTreeView(gridHouse,start);
		//����һ��Ц��
		createDrawLaughFace(window,gridHouse,start,circlePointx,circlePointy);
		
		start = 3;
		//����һ����ǩ
		createLabel("Դ����------>",gridHouse,start);
		start = 4;
		//����һ��Դ���textview���ڣ����ɱ༭
		createSingleTextView(window,gridHouse,start);
		createRectangleFace(window,gridHouse,3,circlePointx,circlePointy);

		start = 4;
		//����һ����ť
		int textViewOutput = GTK.gtk_text_view_new();
		createNextButton(gridHouse,textViewOutput,start);
		
		start =3;
		//����һ����ǩ
		labelResult = GTK.gtk_label_new("��������");
		GTK.gtk_widget_show(labelResult);
		GTK.gtk_grid_attach(gridHouse, labelResult,7, start, 1, 1);
		start = 4;

		//����һ��������棬������ʾԴ������ؽ��
		createOutputTextView(window,gridHouse,start,textViewOutput);
		//����ѭ��
		GTK.gtk_main();
	}
	/**
	 * 
	 * @param labelName  ��ǩ��ʾ���ַ���
	 * @param gridHouse  ��������ʶ
	 * @param start      ��ǩ����������ʶ����ʼλ��
	 * @purpose          ����һ����ǩ�����ʾ������ʾ�ڴ�����
	 */
	public static void createLabel(String labelName,int gridHouse,int start)
	{
		int labelTitle = GTK.gtk_label_new(labelName);
		GTK.gtk_widget_show(labelTitle);
		GTK.gtk_grid_attach(gridHouse, labelTitle, 0, start, 1, 1);

	}
	/**
	 * 
	 * @param scrolledBar    �����������ʶ
	 * @param textview        textview�Ķ����ʶ
	 * @param gridHouse       �������Ķ����ʶ
	 * @param start          textview��������󲼾ֵ���ʼλ��
	 * @purpose              ����textView�Ĺ�����
	 */
	public static void createTextviewScrollBar(int scrolledBar,int textview,int gridHouse,int start)
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrolledBar);
		GTK.gtk_grid_attach(gridHouse, scrolledBar, 0, start, 2, 2);
		GTK.gtk_widget_set_size_request(scrolledBar, 400, 400);
		GTK.gtk_container_add(scrolledBar,textview);
	}
	/**
	 * 
	 * @param scrolledBar     �����������ʶ
	 * @param textview        textview�Ķ����ʶ
	 * @param gridHouse       �������Ķ����ʶ
	 * @param start          textview��������󲼾ֵ���ʼλ��
	 * @purpose               ��������������treeview�����ʶ,���������Ϣ
	 */
	public static void createTreeviewScrollBar(int scrolledBar,int textview,int gridHouse,int start)
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrolledBar);
		GTK.gtk_grid_attach(gridHouse, scrolledBar, 0, start, 2, 3);
		GTK.gtk_widget_set_size_request(scrolledBar, 200, 200);
		GTK.gtk_container_add(scrolledBar,textview);
	}
	/**
	 * 
	 * @param scrolledBar    �����������ʶ
	 * @param textview       textview�Ķ����ʶ
	 * @param gridHouse      �������Ķ����ʶ
	 * @param start          textview��������󲼾ֵ���ʼλ��
	 * @purpose              ��������������treeview�����ʶ��������ʾ��Ϣ
	 */
	public static void createTreeviewOutputScrollBar(int scrolledBar,int textview,int gridHouse,int start)
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrolledBar);
		GTK.gtk_grid_attach(gridHouse, scrolledBar,8, start, 2, 3);
		GTK.gtk_widget_set_size_request(scrolledBar, 200, 200);
		GTK.gtk_container_add(scrolledBar,textview);
	}
	/**
	 * 
	 * @param window     ���ڶ����ʶ
	 * @param gridHouse  ���񲼾ֶ����ʶ
	 * @param start       textview���������񲼾ֶ����е���ʼλ��
	 * @purpose          ����һ��textview��Դ���봰�ڶ���
	 */
	public static void createSingleTextView(int window, int gridHouse, int start)
	{

		final int tvGirl = GTK.gtk_text_view_new();
		GTK.gtk_text_view_set_wrap_mode(tvGirl, GTK.GTK_WRAP_WORD);
		int scrollBar = 0 ;
		//���ӿؼ�
		GTK.gtk_text_view_set_editable(tvGirl, false);
		createTextviewScrollBar(scrollBar,tvGirl,gridHouse,start);
		//GTK.gtk_grid_attach(gridHouse, tvGirl, 0, start+1, 1, 1);

		//��ʾ�ؼ�

		GTK.gtk_widget_show(tvGirl);

		String loveWords = "if(!GTK.gtk_tree_model_get_iter_first(liststore.listiter))"
				+ "\n{\n\tSystem.out.println(\"100��Ǯ���������滵��\");\n}\nelse\n{\n\tdo"
				+ "\n\t{\n\t\tSystem.out.println(\"����doѭ������\");\n\t}while(GTK.gtk_tree_model_iter_next(liststore, iter));"
				+ "\n}\nGTK.gtk_text_iter_free(iter);";
		//��ȡ�ı������������ ��ֻ����С�����ı���һ���õ�����
		//����1   �ȴ�TextView��ȡint TextBuffer 
		    //    Ȼ���ٴ�TextBuffer��ȡtext
		final int textbuffer= GTK.gtk_text_view_get_buffer(tvGirl);

		
		//����ֱ��ͨ�����������  ������Ϣ
		GTK.gtk_text_buffer_set_text(textbuffer, loveWords);
		
		
	}
	/**
	 * 
	 * @param window     ���ڶ����ʶ����ʡ�ԣ�
	 * @param gridHouse  ��������ʶ
	 * @param start      ����������ʼλ�ñ�ʶ
	 * @param tvGirl     treeview�����ʶ
	 * @purpose          ����һ��TextView�����ʶ�����������������ʾ��
	 */
	public static void createOutputTextView(int window, int gridHouse, int start,int tvGirl)
	{

		//tvGirl = GTK.gtk_text_view_new();
		GTK.gtk_text_view_set_wrap_mode(tvGirl, GTK.GTK_WRAP_WORD);
		int scrollBar = 0 ;
		//���ӿؼ�
		GTK.gtk_text_view_set_editable(tvGirl, false);
		createTreeviewOutputScrollBar(scrollBar,tvGirl,gridHouse,start);
		//GTK.gtk_grid_attach(gridHouse, tvGirl, 0, start+1, 1, 1);

		//��ʾ�ؼ�

		GTK.gtk_widget_show(tvGirl);

		String loveWords = "";
		//��ȡ�ı������������ ��ֻ����С�����ı���һ���õ�����
		//����1   �ȴ�TextView��ȡint TextBuffer 
		    //    Ȼ���ٴ�TextBuffer��ȡtext
		final int textbuffer= GTK.gtk_text_view_get_buffer(tvGirl);

		
		//����ֱ��ͨ�����������  ������Ϣ
		GTK.gtk_text_buffer_set_text(textbuffer, loveWords);
		
		
	}
	/**
	 * 
	 * @param gridHouse  ��������ʶ
	 * @param start      ����������ʼλ�ñ�ʶ
	 * @purpose          ����һ��TreeView�����ʶ��д���ֶΣ����Ӽ�¼�����������������ʾ��
	 */
	public static void createSingleTreeView(int gridHouse,int start)
	{
		//3  treeview   ��һ�����棬�൱����һ���Ա�ǰ̨�� V  V������
				treeViewApple = GTK.gtk_tree_view_new();
				//4 �����ֶ�  �ȽϷѾ�
				//4.1�����ֶ�
				int columnID = GTK.gtk_tree_view_column_new_with_attributes("ID", GTK.gtk_cell_renderer_text_new(), 0);
				//4.2�����ֶ�
				GTK.gtk_tree_view_append_column(treeViewApple, columnID);
				//4.3�����ֶ�
				int columnName = GTK.gtk_tree_view_column_new_with_attributes("Names",GTK.gtk_cell_renderer_text_new(),1);
				//4.4�����ֶ�
				GTK.gtk_tree_view_append_column(treeViewApple,columnName);
				//4.5�����ֶ�
				int  columnAge = GTK.gtk_tree_view_column_new_with_attributes("Ages",GTK.gtk_cell_renderer_text_new(),2);
				GTK.gtk_tree_view_append_column(treeViewApple,columnAge);
				
				//�����ؼ�
				//1  list_store  ��һ�����ݽṹ�⣬�൱��һ���ֿ�  M model������
				liststore = GTK.gtk_list_store_new(3); //3���������ֶΣ�ID  Names   Age
			//	GTK.gtk_list_store_append(list_store, iter);
				//2  list_iter,list_iter��һ���ڲ������ݵ����Ŀ��ƹ��̣�����Ҫ��ʾ C�����ã�����
				listiter = GTK.gtk_tree_iter_new();

				//5  ���岽 �� iter��listStore��ϵ����
				          //GTK.gtk_text_buffer_get_end_iter ������treeview��buffer��iter��ϵ����
				           // �Ժ�Ϳ���ͨ��GTK.gtk_text_buffer_insertĬ����һ��һ��iter��ִ�С�
				GTK.gtk_list_store_append(liststore, listiter); //ָ�뿪ʼָ����һ��λ��
				// 6 ���iter�������ݵ�liststore����:ÿ��iter����˼���������л��������¼��
				GTK.gtk_list_store_set_value(liststore, listiter, 0, "001");
				GTK.gtk_list_store_set_value(liststore, listiter, 1, "YinMuHuaDao");
				GTK.gtk_list_store_set_value(liststore, listiter, 2, "35");
				GTK.gtk_list_store_append(liststore, listiter); //��������룬ָ�벻���ƣ�ֻ�Ḳ�ǵ�ǰ�������
				GTK.gtk_list_store_set_value(liststore, listiter, 0, "002");
				GTK.gtk_list_store_set_value(liststore, listiter, 1, "Taiyanghua");
				GTK.gtk_list_store_set_value(liststore, listiter, 2, "29");
				GTK.gtk_list_store_append(liststore, listiter);
				GTK.gtk_list_store_set_value(liststore, listiter, 0 ,"003");
				GTK.gtk_list_store_set_value(liststore, listiter, 1 ,"Xiaojun");
				GTK.gtk_list_store_set_value(liststore, listiter, 2 ,"10");
				// ֮��������9������Ϊ  3���ֶ�*3����¼ == 9
				
				//7  ʹ����iter֮��һ��Ҫ�ǵ� �ص���
				GTK.gtk_tree_iter_free(listiter);
				
				//8 �ؼ���һ������������ʾ����
				GTK.gtk_tree_view_set_model(treeViewApple, liststore);

				//��ͬʱ������ʾ������ǰ̨������ʾ
				GTK.gtk_widget_show(treeViewApple);
				
				//���ӵ���������
				int scrolledbar2 = 0;
				createTreeviewScrollBar(scrolledbar2,treeViewApple,gridHouse,start);
				
				//���ò��ֲ���
				int column =GTK.gtk_tree_view_get_column(treeViewApple, 1);
				GTK.gtk_tree_view_column_set_sort_column_id(column, 1);
				GTK.gtk_tree_view_column_set_resizable(column, true);
				GTK.gtk_tree_view_column_set_reorderable(column, true);
	}
	
	/**
	 * 
	 * @param window          ���ڵĶ����ʶ
	 * @param gridHouse       ���񲼾ֵĶ����ʶ
	 * @param start           �������ڵ����񲼾���ʼλ��
	 * @param circlePointx    ���ʵ�x��λ��
	 * @param circlePointy    ���ʵ�y��λ��
	 * @purpose               ����һ��Ц��
	 */
	public static void createDrawLaughFace(int window,int gridHouse,int start,final int circlePointx,final int circlePointy) 
	{
		//������ͼ�� ���߽л���
		int dan  = GTK.gtk_drawing_area_new();
		
		//����Դ
		//���ӿؼ�
		start = 0;
		GTK.gtk_grid_attach(gridHouse, dan, 1, start, 1, 1);
		//��ʾ����
		GTK.gtk_widget_show(dan);
		

		
		//���û����Ĵ�С
		GTK.gtk_widget_set_size_request(dan, 300, 300);
		
		
		//��ʼ����  �����¼�draw �����ϵĻ���
		
		GTK.g_signal_connect(dan, "draw",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//eventDate���ǻ���Դ
				//���û��ʵ���ɫ
				//Cairo.cairo_destroy(eventData);
				Cairo.cairo_set_source_rgb(eventData, 1, 0, 0);
				//���û��ʵĴ�С
				Cairo.cairo_set_line_width(eventData, 3);			
				Cairo.cairo_move_to(eventData, circlePointx-20, circlePointy);
				Cairo.cairo_line_to(eventData, circlePointx-70, circlePointy);
				Cairo.cairo_line_to(eventData, circlePointx-60, circlePointy-5);
				Cairo.cairo_move_to(eventData, circlePointx-70, circlePointy);
				Cairo.cairo_line_to(eventData, circlePointx-60, circlePointy+5);
				Cairo.cairo_stroke(eventData);
				
				//��һ��Բ
				Cairo.cairo_arc(eventData, circlePointx, circlePointy, 10, 0, 2*3.1415926);
				//��һ��Բ��
				//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
				//��ʾ����
				Cairo.cairo_stroke(eventData); //Cairo.cairo_fill(eventData)��ͬ��Ч��
				
				
				//��һ��Բ��  ������ʵ��֪���Ǵ�x����˳ʱ�뿪ʼ��ͼ

				//��͵Ļ���
				double Pi = 3.1415926;
				Cairo.cairo_arc(eventData, circlePointx, circlePointy, 5, 0.25*Pi, 0.75*Pi);
				Cairo.cairo_fill(eventData);
				
				//������ֻ�۾�
				Cairo.cairo_arc(eventData, circlePointx-5, circlePointy-5, 3, 0, 2*Pi);
				Cairo.cairo_arc(eventData, circlePointx+5, circlePointy-5, 3, 0, 2*Pi);
				Cairo.cairo_fill(eventData);
				
				

				//������
				Cairo.cairo_move_to(eventData, circlePointx+10, circlePointy+5);
				Cairo.cairo_set_source_rgb(eventData, 0.4, 0.3, 0.2);
				Cairo.cairo_set_font_size(eventData, 16);
				//ѡ����������
				Cairo.cairo_select_font_face(eventData, "����", Cairo.CAIRO_FONT_SLANT_ITALIC, Cairo.CAIRO_FONT_WEIGHT_BOLD);
				Cairo.cairo_show_text(eventData,note);
				//Cairo.cairo_stroke(eventData);  //����Ҫ��仰Ҳ����
				
			}
		}, null);
		
		
	}
	/**
	 * 
	 * @param window          ���ڵĶ����ʶ
	 * @param gridHouse       ���񲼾ֵĶ����ʶ
	 * @param start           ���ζ������ڵ����񲼾���ʼλ��
	 * @param circlePointx    ���ʵ�x��λ��
	 * @param circlePointy    ���ʵ�y��λ��
	 * @purpose               ����һ������ͼ��
	 */
	public static void createRectangleFace(int window,int gridHouse,int start,final int circlePointx,final int circlePointy) 
	{
		//������ͼ�� ���߽л���
		int dan  = GTK.gtk_drawing_area_new();
		
		//����Դ
		//���ӿؼ�
		start = 4;
		GTK.gtk_grid_attach(gridHouse, dan, 0, start, 1, 1);
		//��ʾ����
		GTK.gtk_widget_show(dan);
		

		
		//���û����Ĵ�С
		GTK.gtk_widget_set_size_request(dan, 300, 300);
		
		
		//��ʼ����  �����¼�draw �����ϵĻ���
		
		GTK.g_signal_connect(dan, "draw",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//eventDate���ǻ���Դ
				//���û��ʵ���ɫ
				//Cairo.cairo_destroy(eventData);
				Cairo.cairo_set_source_rgb(eventData, 0, 1, 0);
				//���û��ʵĴ�С
				Cairo.cairo_set_line_width(eventData, 3);			
				//Cairo.cairo_move_to(eventData, circlePointx-20, circlePointy);

				
				//��һ������
				Cairo.cairo_rectangle(eventData, circleRectangex-150, circleRectangey+120, 250, 20);
				Cairo.cairo_stroke(eventData);
				//������
				Cairo.cairo_move_to(eventData, circleRectangex+10, circleRectangey+100);
				Cairo.cairo_set_source_rgb(eventData, 0, 0, 1);
				Cairo.cairo_set_font_size(eventData, 18);
				//ѡ����������
				Cairo.cairo_select_font_face(eventData, "����", Cairo.CAIRO_FONT_SLANT_NORMAL, Cairo.CAIRO_FONT_WEIGHT_BOLD);
				Cairo.cairo_show_text(eventData,"ѭ����"+Integer.toString(rowPosition)+"��");//
				//Cairo.cairo_stroke(eventData);  //����Ҫ��仰Ҳ����
				
			}
		}, null);
		
		
	}
	/**
	 * 
	 * @param window          ���ڵĶ����ʶ
	 * @param gridHouse       ���񲼾ֵĶ����ʶ
	 * @param start           ���ζ������ڵ����񲼾���ʼλ��
	 * @param circlePointx    ���ʵ�x��λ��
	 * @param circlePointy    ���ʵ�y��λ��
	 * @purpose               ����һ��ԲȦͼ�� + ��ֹ��ʾ��
	 */
	public static void createCircleFace(int window,int gridHouse,int start,final int circlePointx,final int circlePointy) 
	{
		//������ͼ�� ���߽л���
		int dan  = GTK.gtk_drawing_area_new();
		
		//����Դ
		//���ӿؼ�
		start = 4;
		GTK.gtk_grid_attach(gridHouse, dan, 0, start, 1, 1);
		//��ʾ����
		GTK.gtk_widget_show(dan);
		

		
		//���û����Ĵ�С
		GTK.gtk_widget_set_size_request(dan, 300, 300);
		
		
		//��ʼ����  �����¼�draw �����ϵĻ���
		
		GTK.g_signal_connect(dan, "draw",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//eventDate���ǻ���Դ
				//���û��ʵ���ɫ
				//Cairo.cairo_destroy(eventData);
				Cairo.cairo_set_source_rgb(eventData, 1, 0, 1);
				//���û��ʵĴ�С
				Cairo.cairo_set_line_width(eventData, 3);			
				//Cairo.cairo_move_to(eventData, circlePointx-20, circlePointy);

				
				//��һ������
				Cairo.cairo_arc(eventData, EndCirclex+150, EndCircley+200, 100, 0, 6.28);
				Cairo.cairo_stroke(eventData);
				//������
				Cairo.cairo_move_to(eventData, EndCirclex+50, EndCircley+200);
				Cairo.cairo_set_source_rgb(eventData, 1, 0, 1);
				Cairo.cairo_set_font_size(eventData, 18);
				//ѡ����������
				Cairo.cairo_select_font_face(eventData, "����", Cairo.CAIRO_FONT_SLANT_NORMAL, Cairo.CAIRO_FONT_WEIGHT_BOLD);
				Cairo.cairo_show_text(eventData,"ѭ����"+Integer.toString(rowPosition+1)+"��,������ֹ�˳���");//
				//Cairo.cairo_stroke(eventData);  //����Ҫ��仰Ҳ����
				
			}
		}, null);
		
		
	}
	/**
	 * 
	 * @param gridHouse         ���񲼾ֶ����ʶ
	 * @param textViewOutput    textview�Ķ����ʶ
	 * @param start             textview���������񲼾ֵ���ʼλ��
	 * @purpose                 ����һ����һ��button���������¼�����
	 */
	public static void createNextButton(int gridHouse,int textViewOutput,int start)
	{
		int btnApple = GTK.gtk_button_new_with_label("��һ��");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 6, start, 1, 1);
		insertNextEvent(btnApple,textViewOutput);
	}
	/**
	 * 
	 * @param btnSin            button��һ���Ķ����ʶ
	 * @param textViewOutput    textview�Ķ����ʶ
	 * @purpose                 ����button��һ�����¼�����
	 */
	public static void insertNextEvent(final int btnSin,final int textViewOutput)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				if(rowPosition < getNumberOfListstore(liststore))
				{
					circlePointy = circlePointy+20;
					//����Ц��
					createDrawLaughFace(window,gridHouse,start,circlePointx,circlePointy);
					//�������ο�
					createRectangleFace(window,gridHouse,start+1,circleRectangex,circleRectangey);
					printRecord(liststore);
					InsertStringToTextViewFunction(textViewOutput,"����doѭ������  \n");
					rowPosition =  rowPosition +1;
				}
				else 
				{
					
					//note = "ָ���޷�����";
					circlePointy = circlePointy+20;
					if(rowPosition > getNumberOfListstore(liststore))
					{
						createDrawLaughFace(window,gridHouse,start,circlePointx,circlePointy);
					}
					
					createRectangleFace(window,gridHouse,start+1,circleRectangex,circleRectangey);
					createCircleFace(window,gridHouse,start,EndCirclex,EndCircley);
					InsertStringToTextViewFunction(textViewOutput,"�����ˣ�����û��������\n");
					showInfo("û��������","ָ���޷�����");
				}

			}
		}, null);
	}
	/**
	 * 
	 * @param liststore   liststore��treeview��ԭʼ����
	 * @return            ����liststore�ļ�¼����
	 */
	public static int getNumberOfListstore(int liststore)
	{
		int sum = 0;
		int iter = GTK.gtk_tree_iter_new();
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			return sum;
		}
		do
		{
			sum++;
		}while(GTK.gtk_tree_model_iter_next(liststore, iter));
		return sum;
	}
	/**
	 * 
	 * @param liststore   liststore��treeview��ԭʼ����
	 * @purpose           ��ӡliststore����������
	 */
	public static void printRecord(int liststore)
	{
		//�����½�����������ԭ�ȵ�listiter,�ı���Ҫ�ÿ��ƣ�����Ҫ��һ���ؼ������������򱨴�����������ȫ�ֵ� listiter
		int iter = GTK.gtk_tree_iter_new();
		int i  =0;
/*		if(GTK.gtk_tree_model_get_iter_first(liststore, listiter))
		{
			do
			{
				String ID = GTK.gtk_tree_model_get_value(liststore, listiter, 0);
				String Names = GTK.gtk_tree_model_get_value(liststore, listiter, 1);
				String Ages = GTK.gtk_tree_model_get_value(liststore, listiter, 2);
				//showInfo("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages,"��ʾԱ����Ϣ");
				System.out.println("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages);
				
			}while(GTK.gtk_tree_model_iter_next(liststore,listiter));
		}else
		{
			System.out.println("����������");
		}*/
		// ���ٴ������ȡ�
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			System.out.println("treeviewû������  �����Ӽ�¼");
			return;
		}
		do
		{
			if(rowPosition == i)
			{
				String ID = GTK.gtk_tree_model_get_value(liststore, iter, 0);
				String Names = GTK.gtk_tree_model_get_value(liststore, iter, 1);
				String Ages = GTK.gtk_tree_model_get_value(liststore, iter, 2);
				showInfo("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages,"��ʾԱ����Ϣ");
				//System.out.println("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages);
				return;
			}
			i++;
			
		}while(GTK.gtk_tree_model_iter_next(liststore,iter));
		
		GTK.gtk_tree_iter_free(iter);//�ͷ��� �����д��󡣡���
	}
	/**
	 * 
	 * @param message    ����һ����Ϣ����ʾ����Ϣ��
	 * @param title      ��Ϣ��ı���
	 * @purpose          ����һ����Ϣ��ʾ�Ի���
	 */
	public static void showInfo(  String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		 
		if( ret == GTK.GTK_RESPONSE_OK)
		{
			GTK.gtk_widget_destroy(msgDlg);
		}else
		{
			GTK.gtk_widget_destroy(msgDlg);
		}

	}
	
	public static void createSelection(int gridHouse,int start)
	{
		
	}
	/**
	 * 
	 * @param textview   textview�����ʶ
	 * @param temp       ������textview���ַ���
	 * @purpose          ����һ�����ݵ�textview����
	 */
	public static void InsertStringToTextViewFunction(int textview,String temp)
	{
		//TextIter��һ��TextView�ĵ�������
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //����һ���յ�iter����Ҫ��textBuffer���и�ֵ
		//GTK.gtk_text_iter_forward_to_end(textIter);
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// ����textview��textBuffer��ĩβ��
		GTK.gtk_text_buffer_insert(textBuffer, textIter, temp);
		
		//GTK.gtk_text_buffer_g
		GTK.gtk_text_iter_free(textIter);
		
	}
	
	
}