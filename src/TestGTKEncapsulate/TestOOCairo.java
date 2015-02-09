
package TestGTKEncapsulate;

/**
 * @author    Ҷ����
 * @time      2015��2��4������4:17:01
 * @version   GTKEncapsulateTestOOCairo V1.0
 */
import com.rupeng.gtk4j.Cairo;
import GTKEncapsulate.*;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
public class TestOOCairo
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static int circlePoint=200;
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//GTK��ʼ��
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
		
		gridHouse= GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		GTK.gtk_container_add(window, gridHouse);
		
		
		//�����ؼ�
		int start = 3;
		createDrawLaughFace(window,gridHouse,start,circlePoint);
		
		//����ѭ��
		GTK.gtk_main();
	}
	
	
	//����Ц��
		/**
		 * 
		 * @param window       ���ڶ����ʶ
		 * @param gridHouse    ��������ʶ
		 * @param start        �������������񲼾ֵ���ʼλ��
		 * @param circlePoint  Ц����Բ����
		 * @purpose            ����һ��Ц��
		 */
		public static void createDrawLaughFace(int window,int gridHouse,int start, final int circlePoint) 
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
					OOCairo oocApple = new OOCairo(eventData);
					oocApple.setPenColor(OORGB.BLUE);
					//Cairo.cairo_set_source_rgb(eventData, 0.5, 0.6, 0.3);
					//���û��ʵĴ�С
					//Cairo.cairo_set_line_width(eventData, 3);	
					oocApple.setPenSize(3);
		
					//��һ��Բ
					oocApple.drawCircle(circlePoint, circlePoint, 60);
					//Cairo.cairo_arc(eventData, circlePoint, circlePoint, 60, 0, 2*3.1415926);
					//��һ��Բ��
					//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
					//��ʾ����
					oocApple.stroke();
					//��һ��Բ��  ������ʵ��֪���Ǵ�x����˳ʱ�뿪ʼ��ͼ
					//��͵Ļ���
					double Pi = 3.1415926;
					oocApple.drawArcByClockwise(circlePoint, circlePoint, 40, 0.25*Pi, 0.75*Pi);
				//  Cairo.cairo_arc(eventData, circlePoint, circlePoint, 40, 0.25*Pi, 0.75*Pi);
					oocApple.fill();
					//Cairo.cairo_fill(eventData);
					//������ֻ�۾�
					oocApple.drawArcByClockwise(circlePoint-20, circlePoint, 10, 0, 2*Pi);
					
					oocApple.drawArcByClockwise(circlePoint+20, circlePoint, 10, 0, 2*Pi);
					oocApple.fill();
					//Cairo.cairo_arc(eventData, circlePoint-20, circlePoint-20, 10, 0, 2*Pi);
					//Cairo.cairo_arc(eventData, circlePoint+20, circlePoint-20, 10, 0, 2*Pi);
					//Cairo.cairo_fill(eventData);
				}
			}, null);
		}
		/**
		 * 
		 * @param window          ���ڲ��ֶ����ʶ
		 * @param gridHouse       ���񲼾ֶ����ʶ
		 * @param start           ��ë�����ڵ����񲼾ֵ���ʼλ��
		 * @purpose               ������ë
		 * 
		 */
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
					//Ψһһ��������eventData
					OOCairo oocBanana = new OOCairo(eventData);
					//���û��ʵ���ɫ
					
					oocBanana.setPenColor(OORGB.GREEN);
					//Cairo.cairo_set_source_rgb(eventData, 0.5, 0.6, 0.3);
					//���û��ʵĴ�С
					oocBanana.setPenSize(3);
					//Cairo.cairo_set_line_width(eventData, 3);
					//�ƶ����ʵ�   ����Ĭ��λ�ò����ڣ�0,0��move_to������
					//Cairo.cairo_move_to(eventData, 0, 0);

					//��һ��Բ
					oocBanana.drawCircle(180, 180, 60);
					//Cairo.cairo_arc(eventData, 180, 180, 60, 0, 2*3.1415926);
					//��һ��Բ��
					oocBanana.drawArcByClockwise(200, 200, 70, 1.5*3.1415925, 2*3.1415925);
					//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
					//��ʾ����
					oocBanana.stroke();
					//Cairo.cairo_stroke(eventData); //Cairo.cairo_fill(eventData)��ͬ��Ч��
					//��һ��Բ��  ������ʵ��֪���Ǵ�x����˳ʱ�뿪ʼ��ͼ
					//��͵Ļ���
					double Pi = 3.1415926;
					oocBanana.drawArcByClockwise( 180, 180, 40, 0.25*Pi, 0.75*Pi);
					//Cairo.cairo_arc(eventData, 180, 180, 40, 0.25*Pi, 0.75*Pi);
					oocBanana.fill();
					//Cairo.cairo_fill(eventData);
					
					//������ֻ�۾�
					oocBanana.drawArcByClockwise(160, 160, 10, 0, 2*Pi);
					oocBanana.drawArcByClockwise(200, 160, 10, 0, 2*Pi);
					oocBanana.fill();
				/*	Cairo.cairo_arc(eventData, 160, 160, 10, 0, 2*Pi);
					Cairo.cairo_arc(eventData, 200, 160, 10, 0, 2*Pi);
					Cairo.cairo_fill(eventData);*/
					//���ƺ���
					oocBanana.drawArcByClockwise(130, 160, 50, 0.1666668*Pi, 0.6777777*Pi);
					oocBanana.stroke();
					oocBanana.drawArcByClockwise(230, 160, 50, 0.333333*Pi, 0.8333333*Pi);
					oocBanana.stroke();
					/*Cairo.cairo_arc(eventData, 130, 160, 50, 0.166667*Pi, 0.6777777*Pi);
					Cairo.cairo_stroke(eventData);
					Cairo.cairo_arc(eventData, 230, 160, 50, 0.333333*Pi, 0.8333333*Pi);
					Cairo.cairo_stroke(eventData);*/
					//����ͷ��
					oocBanana.drawArcByClockwise(133.6, 136.72, 30, 1.3333333*Pi, 1.8333333*Pi);
					oocBanana.stroke();
					oocBanana.drawArcByClockwise(224.4, 136.72, 30, 1.1666667*Pi, 1.6777777*Pi);
					oocBanana.stroke();
/*					Cairo.cairo_arc(eventData, 133.6, 136.72, 30,1.3333333*Pi ,1.833333*Pi );
					Cairo.cairo_stroke(eventData);
					Cairo.cairo_arc(eventData, 224.4, 136.72, 30, 1.166667*Pi, 1.6777777*Pi);
					Cairo.cairo_stroke(eventData);*/
					//�ƶ����ʵ�   ����Ĭ��λ�ò����ڣ�0,0��move_to������
					
					oocBanana.drawLine(180, 20, 180, 90);
					oocBanana.stroke();
				/*	Cairo.cairo_move_to(eventData, 180, 120);
	 			   //��һ�����ߵ�
					Cairo.cairo_line_to(eventData, 180, 90);
					Cairo.cairo_stroke(eventData);*/
					//������
					oocBanana.moveTo(180, 260);
					oocBanana.setPenColor(new OORGB(0.4,0.3,0.2));	
					oocBanana.setFontSize(16);
					oocBanana.setFontType();
					oocBanana.showText("��ë���Ի���");
/*					Cairo.cairo_move_to(eventData, 180, 260);
					Cairo.cairo_set_source_rgb(eventData, 0.4, 0.3, 0.2);
					Cairo.cairo_set_font_size(eventData, 16);
					//ѡ����������
					Cairo.cairo_select_font_face(eventData, "����", Cairo.CAIRO_FONT_SLANT_ITALIC, Cairo.CAIRO_FONT_WEIGHT_BOLD);
					Cairo.cairo_show_text(eventData,"��ë���Ի���");*/
					//Cairo.cairo_stroke(eventData);  //����Ҫ��仰Ҳ����
				}
			}, null);
		}
		

}