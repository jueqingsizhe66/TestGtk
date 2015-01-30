
/**
 * @author Ҷ����
 * @version  v1.0
 */
import java.awt.AWTException;
import java.awt.Robot;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import com.rupeng.gtk4j.Utils;
public class TestGtkWidgetBoxLayout
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
	    //�¼���һ��house  ����������ı��� +button+label
	    // gtk_box_new �ĵ�һ������ һ�������ָ�ֵ��ʽGTK.GTK_ORIENTATION_HORIZONTAL��_VERTICAL
	    // �ڶ���������ʾ�ļ��space,�������Ϊ5��Ϊ5pt�ļ��
	    int houseApple = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
	    int houseBanana = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
	    int houseTree = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
	    //ˮƽhouse
	    GTK.gtk_box_pack_start(houseApple, label1, true, true, 0);
	    GTK.gtk_box_pack_start(houseApple,entry1 , true, true, 0);
	    GTK.gtk_box_pack_start(houseBanana,label2 , true, true, 0);
	    GTK.gtk_box_pack_start(houseBanana,button1 , true, true, 0);
	    GTK.gtk_box_pack_start(houseBanana,label3 , true, true, 0);
	    //��ֱhouse
	    GTK.gtk_box_pack_start(houseTree, houseApple, true, true, 0);
	    GTK.gtk_box_pack_start(houseTree, houseBanana, true, true, 0);
	    

	    // ��ʾ�������� ��house
	    GTK.gtk_widget_show(houseApple);
	    GTK.gtk_widget_show(houseBanana);
	    GTK.gtk_widget_show(houseTree);
	    
	    GTK.gtk_container_add(window,houseTree);
	    
	    GTK.gtk_main();
	    
	    
	}

}