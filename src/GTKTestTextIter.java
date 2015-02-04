import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;



/**
 * @author 叶昭良
 * @version  TestView+TestIter v1.0
 * @version  TestView+TestIter v2.0 加入了滚动条操作。
 */
public class GTKTestTextIter
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static int scrolledBar;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL); 
		GTK.gtk_window_set_title(window, "女朋友告白神器 V1.0");
		GTK.gtk_widget_show(window);
		//安全关闭GTK
		GTK.g_signal_connect(window, "destroy", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
		// 创建布局
		gridHouse =  GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		
		int start  = 0;
		createTextView(window,gridHouse,start);
		GTK.gtk_container_add(window,gridHouse);
		//启动循环
		GTK.gtk_main();
	}
	
	public static void createTextView(int window, int gridHouse, int start)
	{
		int imgYumu = GTK.gtk_image_new_from_resource("yumufeng.jpg");
		final int tvGirl = GTK.gtk_text_view_new();
		final int label = GTK.gtk_label_new("");
		final int labelIter = GTK.gtk_label_new("");
		int btnShow = GTK.gtk_button_new_with_label("显示");
		int btnShowIter = GTK.gtk_button_new_with_label("迭代器插入");
		GTK.gtk_text_view_set_wrap_mode(tvGirl, GTK.GTK_WRAP_WORD);
		int scrollBar = 0 ;
		//添加控件
		GTK.gtk_grid_attach(gridHouse, imgYumu, 0, start, 1, 1);
		createScrollBar(scrollBar,tvGirl,gridHouse,start);
		//GTK.gtk_grid_attach(gridHouse, tvGirl, 0, start+1, 1, 1);
		GTK.gtk_grid_attach(gridHouse, label, 0 ,start+2, 1,1);
		GTK.gtk_grid_attach(gridHouse, labelIter, 0 ,start+3, 1,1);
		GTK.gtk_grid_attach(gridHouse, btnShow, 1 ,start+2, 1,1);
		GTK.gtk_grid_attach(gridHouse, btnShowIter, 1 ,start+3, 1,1);
		//显示控件
		GTK.gtk_widget_show(imgYumu);
		GTK.gtk_widget_show(tvGirl);
		GTK.gtk_widget_show(label);
		GTK.gtk_widget_show(btnShow);
		GTK.gtk_widget_show(labelIter);
		GTK.gtk_widget_show(btnShowIter);
		
		//读取文本框里面的内容 ，只适用小量的文本，一般用迭代器
		//方法1   先从TextView获取int TextBuffer 
		    //    然后再从TextBuffer获取text
		final int textbuffer= GTK.gtk_text_view_get_buffer(tvGirl);
		String loveWords = "你就像那天上星星，点缀着我们两的星空，璀璨夺目；"
				+ "我愿与你携手共同奋进，原因和你共育我们的sons and grils,"
				+ "建立起一个幸福的家庭";
		
		//可以直接通过缓冲区编号  设置信息
		GTK.gtk_text_buffer_set_text(textbuffer, loveWords);
		//添加按钮事件
		GTK.g_signal_connect(btnShow, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				String text = GTK.gtk_text_buffer_get_text(textbuffer);
				GTK.gtk_label_set_text(label, text);
			}
		}, null);
		
		
		GTK.g_signal_connect(btnShowIter, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				helpFunction(tvGirl);
			}
		}, null);
		
	}
	//如何遍历读取textview信息？？ 当前只能插入？
	public static void helpFunction(int textview)
	{
		//TextIter是一个TextView的迭代器。
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //这是一个空的iter，需要用textBuffer进行赋值
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// 或者textview的textBuffer的末尾！
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
	
	public static void createSingleTextView(int window, int gridHouse, int start)
	{

		final int tvGirl = GTK.gtk_text_view_new();



		GTK.gtk_text_view_set_wrap_mode(tvGirl, GTK.GTK_WRAP_WORD);
		int scrollBar = 0 ;
		//添加控件
	
		createScrollBar(scrollBar,tvGirl,gridHouse,start);
		//GTK.gtk_grid_attach(gridHouse, tvGirl, 0, start+1, 1, 1);

		//显示控件

		GTK.gtk_widget_show(tvGirl);

		
		//读取文本框里面的内容 ，只适用小量的文本，一般用迭代器
		//方法1   先从TextView获取int TextBuffer 
		    //    然后再从TextBuffer获取text
		final int textbuffer= GTK.gtk_text_view_get_buffer(tvGirl);
		String loveWords = "你就像那天上星星，点缀着我们两的星空，璀璨夺目；"
				+ "我愿与你携手共同奋进，原因和你共育我们的sons and grils,"
				+ "建立起一个幸福的家庭";
		
		//可以直接通过缓冲区编号  设置信息
		GTK.gtk_text_buffer_set_text(textbuffer, loveWords);
		

	}

}
