
/**
 * @author Ҷ����
 * @version v1.0
 * @version v1.1 �Ľ��������  
 */
import java.awt.AWTException;
import java.awt.Robot;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import com.rupeng.gtk4j.Utils;
public class TestGtkWidgetGridLayout
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		GTK.gtk_init();
	    int window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL); // ԭ�����и�POPUP�������Ҽ��˵�
	    GTK.gtk_widget_show(window);
	    
	    GTK.gtk_window_set_title(window, "˪ѩ"); // widget��û�� ֻ����window���С�
	   // GTK.gtk_window_set_position(window, GTK.GTK_WIN_POS_CENTER);
	    
	    GTK.gtk_window_set_position(window, GTK.GTK_WIN_POS_CENTER_ALWAYS);
	    //GTK.gtk_window_set_resizable(window, true);  //Ĭ�Ͽ�������
	    // Ĭ�ϵĴ��ڿ�����󻯣� GTK.gtk_widget_set_maximize(widget)
	   
	 
	    // �����ĹرնԻ���
	    GTK.g_signal_connect(window, "destroy", new IGCallBack()
	    {
	    	@Override
	    	public void execute(int instance, int eventData, Object object)
	    	{
	    		// TODO �Զ����ɵķ������
	    		GTK.gtk_main_quit();
	    	}
	    }, null);
	    
	    //int box1= GTK.gtk_box_new(GTK., spacing)
	    //�¼���һ����������
	    int houseGrid = GTK.gtk_grid_new();




	    // ��ʾ ��houseGrid
	    GTK.gtk_widget_show(houseGrid);
	    // 0��ͷ�ǳ���Ա�Ĺ���
	    testGtk(houseGrid,0);
	    landInternet(houseGrid,2);
	    

	    
	    GTK.gtk_container_add(window,houseGrid);
	    GTK.gtk_main();
	    
	    
	}
	//�½�һ�����ԵĽ���
	/**
	 * 
	 * @param houseGrid  ����Ĵ���  ��ʾgtk_grid_new�ķ���ֵ
	 * @param start      ��houseGrid���䵽�ĵڼ��п�ʼ���
	 */
	public static void testGtk(int houseGrid,int start)
	{
		   //�½���һ���ı���
	    int entry1  = GTK.gtk_entry_new();
	    GTK.gtk_widget_show(entry1);
	    //�½���һ��button 
	    int button1 = GTK.gtk_button_new_with_label("click me");
	    GTK.gtk_widget_show(button1);
	    
	    //�½���һ����ǩ
	    int label1 =  GTK.gtk_label_new("�����룺");
	    int label2 =  GTK.gtk_label_new("");
	    int label3 =  GTK.gtk_label_new("");
	    
	    // ��ʾ��ǩ
	    GTK.gtk_widget_show(label1);
	    GTK.gtk_widget_show(label2);
	    GTK.gtk_widget_show(label3);
	    
	    //���⵽housegrid
	    // gtk_grid_attach(int grid, int child, int left,int top, int width, int height);
	    GTK.gtk_grid_attach(houseGrid, label1, 0, start, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, entry1, 1, start, 1, 1);
	    
	    GTK.gtk_grid_attach(houseGrid, button1, 1, start+1, 1, 1);
	}
	
    //�½�һ����½�����GTK
	public static void landInternet(int houseGrid,int start) 
	{
		   //�½���һ���ı���
	    int entry1  = GTK.gtk_entry_new();

	    int entry2  = GTK.gtk_entry_new();
	    
	    //�������ı��������������y2, false);
	    //����������ʾ��
	    GTK.gtk_entry_set_visibility(entry2,false);  //�ַ� Ĭ�ϵ������Ϊ*
	    GTK.gtk_entry_set_invisible_char(entry2, '1'); //ֻ�����ַ�  ������"1"

	    //�½���һ��button 
	    int buttonEnter = GTK.gtk_button_new_with_label("��½");

	    int buttonClosed = GTK.gtk_button_new_with_label("ȡ��");

	    int checkbox1= GTK.gtk_check_button_new_with_label("�Ƿ񱣴�����");
	    //�½���һ����ǩ
	    int label1 =  GTK.gtk_label_new("�û�����");
	    int label2 =  GTK.gtk_label_new("����  ��");
	    
	    //ΪʲôҪ����Ϊfinal��ʱ�����ر����
	    final int label3 =  GTK.gtk_label_new("");
	    
	    // ��ʾ��ǩ
	    GTK.gtk_widget_show(entry1);
	    GTK.gtk_widget_show(entry2);
	    GTK.gtk_widget_show(label1);
	    GTK.gtk_widget_show(label2);
	    GTK.gtk_widget_show(label3);
	    GTK.gtk_widget_show(checkbox1);
	    GTK.gtk_widget_show(buttonEnter);
	    GTK.gtk_widget_show(buttonClosed);
	    
	    //���⵽houseGrid
	    GTK.gtk_grid_attach(houseGrid, label1, 0, start, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, entry1, 1, start, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, label2, 0, start+1, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, entry2, 1, start+1, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, checkbox1, 1, start+2, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, buttonEnter, 1, start+3, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, buttonClosed, 2, start+3, 1, 1);
	    GTK.gtk_grid_attach(houseGrid, label3, 1, start+5, 1, 1);
	    
	    
	    // ���а�ť���¼�����
	    GTK.g_signal_connect(buttonEnter,"clicked",new IGCallBack()
	    {
	    	@Override
	    	public void execute(int instance, int eventData, Object object)
	    	{
	    		// TODO �Զ����ɵķ������
	    		System.out.println("�����˵�½");
	    		GTK.gtk_label_set_text(label3, "�����˵�½");
	    	}
	    },null);
	    
	    GTK.g_signal_connect(buttonClosed,"clicked",new IGCallBack()
	    {
	    	@Override
	    	public void execute(int instance, int eventData, Object object)
	    	{
	    		// TODO �Զ����ɵķ������
	    		System.out.println("�����˹ر�");
	    		GTK.gtk_label_set_text(label3, "�����˹ر�");
	    	}
	    },null);
	}

}