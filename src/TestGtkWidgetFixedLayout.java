
/**
 * @author Ҷ����
 * @version  v1.0
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import com.rupeng.gtk4j.Utils;
public class TestGtkWidgetFixedLayout
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
	    GTK.gtk_widget_show(label1);
	    
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
	    int house = GTK.gtk_fixed_new();
	    GTK.gtk_fixed_put(house, entry1,0,0);
	    GTK.gtk_fixed_put(house,button1,50,0);
	    GTK.gtk_fixed_put(house,label1,0,100); //������Ӧ��Ļ��С��������һ�������
	    GTK.gtk_widget_show(house);
	    
	    GTK.gtk_container_add(window,house);
	    
	    GTK.gtk_main();
	}

}