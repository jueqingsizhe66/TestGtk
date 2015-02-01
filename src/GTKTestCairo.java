
/**
 * @author Ҷ����
 * @version GTK+Cairo v1.0
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.Cairo;
public class GTKTestCairo
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		//GTK��ʼ��
		GTK.gtk_init();
		//���ڱ�ʶ
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//���ڱ���
		GTK.gtk_window_set_title(window, "���׻�ͼ��v1.0");
		//��ʾ����
		GTK.gtk_widget_show(window);
		
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
		}, null);
		
		
		//���ò��ַ�ʽ
		gridHouse = GTK.gtk_grid_new();
		
		
		//�����ؼ�
		int start = 0;
		createDraw(window, gridHouse, start);
		//circlePoint ԭ������Ϊ180
		createDrawLaughFace(window,gridHouse,start+1,180);
		
		
		GTK.gtk_widget_show(gridHouse);
		
		GTK.gtk_container_add(window,gridHouse);
		//����ѭ��
		GTK.gtk_main();
	}
	
	
	public static void createDraw(int window,int gridHouse,int start) 
	{
		//������ͼ�� ���߽л���
		int  dan  = GTK.gtk_drawing_area_new();
		
		int label = GTK.gtk_label_new("��ë");
		//����Դ
		//���ӿؼ�
		GTK.gtk_grid_attach(gridHouse, label, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, dan, 1, start, 1, 1);
		//��ʾ����
		GTK.gtk_widget_show(dan);
		GTK.gtk_widget_show(label);
		
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
				Cairo.cairo_set_source_rgb(eventData, 0.5, 0.6, 0.3);
				//���û��ʵĴ�С
				Cairo.cairo_set_line_width(eventData, 3);
				
				
				
				//�ƶ����ʵ�   ����Ĭ��λ�ò����ڣ�0,0��move_to������
				//Cairo.cairo_move_to(eventData, 0, 0);
/*				//��һ�����ߵ�
				Cairo.cairo_line_to(eventData, 50, 50);
				//��һ�����ص�
				Cairo.cairo_line_to(eventData,100,100);
				//���Ż���
				Cairo.cairo_line_to(eventData, 120, 120);*/
				//��һ��Բ
				Cairo.cairo_arc(eventData, 180, 180, 60, 0, 2*3.1415926);
				//��һ��Բ��
				//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
				//��ʾ����
				Cairo.cairo_stroke(eventData); //Cairo.cairo_fill(eventData)��ͬ��Ч��
				
				
				//��һ��Բ��  ������ʵ��֪���Ǵ�x����˳ʱ�뿪ʼ��ͼ
/*				Cairo.cairo_arc(eventData, 220, 220, 30,0, 0.5*3.1415926);

				Cairo.cairo_fill(eventData); //Cairo.cairo_fill(eventData)��ͬ��Ч��
				
				Cairo.cairo_arc(eventData, 220, 100, 30,0, 3.1415926);
				Cairo.cairo_stroke(eventData);
				Cairo.cairo_arc(eventData, 220, 30, 30,0, 1.5*3.1415926);
				Cairo.cairo_fill(eventData);*/
				//��͵Ļ���
				double Pi = 3.1415926;
				Cairo.cairo_arc(eventData, 180, 180, 40, 0.25*Pi, 0.75*Pi);
				Cairo.cairo_fill(eventData);
				
				//������ֻ�۾�
				Cairo.cairo_arc(eventData, 160, 160, 10, 0, 2*Pi);
				Cairo.cairo_arc(eventData, 200, 160, 10, 0, 2*Pi);
				Cairo.cairo_fill(eventData);
				
				
				//���ƺ���
				Cairo.cairo_arc(eventData, 130, 160, 50, 0.166667*Pi, 0.6777777*Pi);
				
				Cairo.cairo_stroke(eventData);
				
				Cairo.cairo_arc(eventData, 230, 160, 50, 0.333333*Pi, 0.8333333*Pi);
				Cairo.cairo_stroke(eventData);
				
				//����ͷ��
				Cairo.cairo_arc(eventData, 133.6, 136.72, 30,1.3333333*Pi ,1.833333*Pi );
				
				Cairo.cairo_stroke(eventData);
				
				Cairo.cairo_arc(eventData, 224.4, 136.72, 30, 1.166667*Pi, 1.6777777*Pi);
				Cairo.cairo_stroke(eventData);
				
				
				//�ƶ����ʵ�   ����Ĭ��λ�ò����ڣ�0,0��move_to������
				Cairo.cairo_move_to(eventData, 180, 120);
 			   //��һ�����ߵ�
				Cairo.cairo_line_to(eventData, 180, 90);
				Cairo.cairo_stroke(eventData);
			}
		}, null);
		
		
	}
	
	public static void createDrawLaughFace(int window,int gridHouse,int start,final int circlePoint) 
	{
		//������ͼ�� ���߽л���
		int  dan  = GTK.gtk_drawing_area_new();
		int label = GTK.gtk_label_new("Ц��");
		//����Դ
		//���ӿؼ�
		GTK.gtk_grid_attach(gridHouse, label, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, dan, 1, start, 1, 1);
		//��ʾ����
		GTK.gtk_widget_show(dan);
		GTK.gtk_widget_show(label);

		
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
				Cairo.cairo_set_source_rgb(eventData, 0.5, 0.6, 0.3);
				//���û��ʵĴ�С
				Cairo.cairo_set_line_width(eventData, 3);			
	
				//��һ��Բ
				Cairo.cairo_arc(eventData, circlePoint, circlePoint, 60, 0, 2*3.1415926);
				//��һ��Բ��
				//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
				//��ʾ����
				Cairo.cairo_stroke(eventData); //Cairo.cairo_fill(eventData)��ͬ��Ч��
				
				
				//��һ��Բ��  ������ʵ��֪���Ǵ�x����˳ʱ�뿪ʼ��ͼ

				//��͵Ļ���
				double Pi = 3.1415926;
				Cairo.cairo_arc(eventData, circlePoint, circlePoint, 40, 0.25*Pi, 0.75*Pi);
				Cairo.cairo_fill(eventData);
				
				//������ֻ�۾�
				Cairo.cairo_arc(eventData, circlePoint-20, circlePoint-20, 10, 0, 2*Pi);
				Cairo.cairo_arc(eventData, circlePoint+20, circlePoint-20, 10, 0, 2*Pi);
				Cairo.cairo_fill(eventData);
			}
		}, null);
		
		
	}

}