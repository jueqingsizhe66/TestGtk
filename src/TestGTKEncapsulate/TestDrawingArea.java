
package TestGTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import GTKEncapsulate.*;
/**
 * @author    Ҷ����
 * @time      2015��2��8������10:57:47
 * @version   GTKEncapsulateTestDrawingArea V1.0
 *                              ������OODrawingArea   OODrawCallback ��
 */
public class TestDrawingArea
{

	/**
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		OOWindow owApple = new OOWindow();
		owApple.show();
		owApple.setExitAfterDestroy(true);
		owApple.setTitle("���Ի�ͼ�ӿ�");
		
		
		///�������񲼾�
		OOGrid gridApple  =  new OOGrid();
		gridApple.show();
 		owApple.add(gridApple);
		
		//������ͼ
 		//1 �������ķ���
		OODrawingArea odaApple = new OODrawingArea();
		odaApple.show();
		//����setSize���򿴲���  ���һ��Ҫע����
		odaApple.setWidgetSize(300, 300);
		gridApple.add(odaApple, 0);
		// ��һ������ػ��� ���汻��ס�ˣ��ҿ�����ʱ��
		// �ڶ�������ػ��� �򿪻����ʱ��
		odaApple.addDrawListener(new OODrawCallback()
		{
			
			@Override
			public void drawCairo(OOCairo cairo)
			{
				// TODO Auto-generated method stub
				cairo.setPenColor(OORGB.RED);
				cairo.drawRectangle(20, 20, 100, 100);
				cairo.fill();
				cairo.drawCircle(200, 200, 50);
				cairo.stroke();
			}
		});
		
		//ԭ�ȵ�������̵ķ���
		int drawpaint = GTK.gtk_drawing_area_new();
		///Ҳ��Ҫset_size  Ĭ��Ϊ0
		GTK.gtk_widget_set_size_request(drawpaint, 100, 100);
		GTK.gtk_widget_show(drawpaint);
		GTK.g_signal_connect(drawpaint, "draw", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				OOCairo oc = new OOCairo(eventData);
				oc.drawCircle(50, 50, 20);
				oc.stroke();
			}
		}, null);
		
		gridApple.add(drawpaint, 1);
		
	
		GTK.gtk_main();
	}

}