import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;


/**
 * @author Ҷ����
 * @version GtkImage v1.0
 *
 */
public class TestGtkImage
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		// TODO �Զ����ɵķ������
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL); 
		GTK.gtk_window_set_title(window, "GtkImage V1.0");
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
		createGtkImage(window,gridHouse,start);
		GTK.gtk_container_add(window,gridHouse);
		//����ѭ��
		GTK.gtk_main();
	}
	
	public static void createGtkImage(int window,int gridHouse,int start)
	{
		int imgWall = GTK.gtk_image_new_from_file("E:\\����Ƶ��\\ͼƬƵ��\\xin\\greatWall.jpg");
		int imgHun = GTK.gtk_image_new_from_resource("yumufeng.jpg");
		int imgStock = GTK.gtk_image_new_from_stock(GTK.GTK_STOCK_ABOUT, GTK.GTK_ICON_SIZE_LARGE_TOOLBAR);
		
		
		int labelWall = GTK.gtk_label_new("ͨ�������ļ����볤����Ƭ");
		int labelHun = GTK.gtk_label_new("ͨ����Ŀ�ļ������ɴ��Ƭ");
		int labelStock = GTK.gtk_label_new("ͨ��GTK����ͼƬ�ļ����������Ƭ");
		
		//���ӵ����ⷿ��
		GTK.gtk_grid_attach(gridHouse, imgWall, 0, start+1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, imgHun, 1, start+1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, imgStock, 2, start+1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, labelWall, 0, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, labelHun, 1, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, labelStock, 2, start, 1, 1);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(imgWall);
		GTK.gtk_widget_show(imgHun);
		GTK.gtk_widget_show(imgStock);
		GTK.gtk_widget_show(labelWall);
		GTK.gtk_widget_show(labelHun);
		GTK.gtk_widget_show(labelStock);
	}
}