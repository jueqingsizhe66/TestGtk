/**
 * @author 叶昭良
 *
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class TestGtk
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
        GTK.gtk_init(); // initialize
        int window  = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL); //create new window 
        /*
        w.connect(new Window.DeleteEvent(){
            publi	c boolean onDeleteEvent(Widget source,Event event)
            {
                Gtk.mainQuit();
            }
        })
        */
        // 设置窗口的名字
        GTK.gtk_window_set_title(window,"冰封");
        // 设置窗口的显示位置
        GTK.gtk_window_set_position(window,GTK.GTK_WIN_POS_CENTER_ALWAYS);
        //每一个空间都得show出来
        GTK.gtk_widget_show(window); // show it
        //事件的创建，当点击删除按钮时候 摧毁对话框
        GTK.g_signal_connect(window,"destroy",new IGCallBack()
                {
                    @Override
                    public void execute(int instance,int eventData,Object object)
                    {
                        GTK.gtk_main_quit();
                    }
                },null);
        //创建一个box用于盛放控件
        int box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL,0);
        // 在gtk_container_add只能盛放一个容器或者控件
        GTK.gtk_container_add(window,box);
        //显示box
        GTK.gtk_widget_show(box);
        //GTK的主循环程序 gtk_main();
        GTK.gtk_main();

	}

}
