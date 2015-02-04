import com.rupeng.gtk4j.Cairo;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月3日上午10:24:23
 * @version   TestTeacher V1.0   一个简易的代码运行模拟窗口，未加入代码的高亮，采用图片控制
 *                               主要采用textview  treeview    cairo三个技术关键点
 */
public class TestTeacher
{

	/**
	 * @param args
	 */
	//部分类变量的定义
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
	static String note = "指针下移";
	static int labelResult;
	
	//static String loveWords;
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//GTK初始化
		GTK.gtk_init();
		//窗口对象标识的创建
		window= GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//显示窗口
		GTK.gtk_widget_show(window);
		//安静关闭窗口
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				GTK.gtk_main_quit();
			}
		}, null);
		
		//设置网格布局
		gridHouse = GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		//添加网格布局到window中
		GTK.gtk_container_add(window, gridHouse);
		
		//定义一些常用的控件
		
		//创建一个treeview 用于显示用户信息
		createSingleTreeView(gridHouse,start);
		//创建一个笑脸
		createDrawLaughFace(window,gridHouse,start,circlePointx,circlePointy);
		
		start = 3;
		//创建一个标签
		createLabel("源代码------>",gridHouse,start);
		start = 4;
		//创建一个源码的textview窗口，不可编辑
		createSingleTextView(window,gridHouse,start);
		createRectangleFace(window,gridHouse,3,circlePointx,circlePointy);

		start = 4;
		//创建一个按钮
		int textViewOutput = GTK.gtk_text_view_new();
		createNextButton(gridHouse,textViewOutput,start);
		
		start =3;
		//创建一个标签
		labelResult = GTK.gtk_label_new("输出结果：");
		GTK.gtk_widget_show(labelResult);
		GTK.gtk_grid_attach(gridHouse, labelResult,7, start, 1, 1);
		start = 4;

		//创建一个输出界面，用于显示源码输出地结果
		createOutputTextView(window,gridHouse,start,textViewOutput);
		//启动循环
		GTK.gtk_main();
	}
	/**
	 * 
	 * @param labelName  标签显示的字符串
	 * @param gridHouse  网格对象标识
	 * @param start      标签在网格对象标识的起始位置
	 * @purpose          创建一个标签对象表示，并显示在窗口中
	 */
	public static void createLabel(String labelName,int gridHouse,int start)
	{
		int labelTitle = GTK.gtk_label_new(labelName);
		GTK.gtk_widget_show(labelTitle);
		GTK.gtk_grid_attach(gridHouse, labelTitle, 0, start, 1, 1);

	}
	/**
	 * 
	 * @param scrolledBar    滚动条对象标识
	 * @param textview        textview的对象标识
	 * @param gridHouse       网格对象的对象标识
	 * @param start          textview在网格对象布局的起始位置
	 * @purpose              创建textView的滚动条
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
	 * @param scrolledBar     滚动条对象标识
	 * @param textview        textview的对象标识
	 * @param gridHouse       网格对象的对象标识
	 * @param start          textview在网格对象布局的起始位置
	 * @purpose               创建带滚动条的treeview对象标识,用于输出信息
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
	 * @param scrolledBar    滚动条对象标识
	 * @param textview       textview的对象标识
	 * @param gridHouse      网格对象的对象标识
	 * @param start          textview在网格对象布局的起始位置
	 * @purpose              创建带滚动条的treeview对象标识，用于显示信息
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
	 * @param window     窗口对象标识
	 * @param gridHouse  网格布局对象标识
	 * @param start       textview对象在网格布局对象中的起始位置
	 * @purpose          创建一个textview的源代码窗口对象
	 */
	public static void createSingleTextView(int window, int gridHouse, int start)
	{

		final int tvGirl = GTK.gtk_text_view_new();
		GTK.gtk_text_view_set_wrap_mode(tvGirl, GTK.GTK_WRAP_WORD);
		int scrollBar = 0 ;
		//添加控件
		GTK.gtk_text_view_set_editable(tvGirl, false);
		createTextviewScrollBar(scrollBar,tvGirl,gridHouse,start);
		//GTK.gtk_grid_attach(gridHouse, tvGirl, 0, start+1, 1, 1);

		//显示控件

		GTK.gtk_widget_show(tvGirl);

		String loveWords = "if(!GTK.gtk_tree_model_get_iter_first(liststore.listiter))"
				+ "\n{\n\tSystem.out.println(\"100块钱都不给，真坏！\");\n}\nelse\n{\n\tdo"
				+ "\n\t{\n\t\tSystem.out.println(\"我在do循环里面\");\n\t}while(GTK.gtk_tree_model_iter_next(liststore, iter));"
				+ "\n}\nGTK.gtk_text_iter_free(iter);";
		//读取文本框里面的内容 ，只适用小量的文本，一般用迭代器
		//方法1   先从TextView获取int TextBuffer 
		    //    然后再从TextBuffer获取text
		final int textbuffer= GTK.gtk_text_view_get_buffer(tvGirl);

		
		//可以直接通过缓冲区编号  设置信息
		GTK.gtk_text_buffer_set_text(textbuffer, loveWords);
		
		
	}
	/**
	 * 
	 * @param window     窗口对象标识（可省略）
	 * @param gridHouse  网格对象标识
	 * @param start      网格对象的起始位置标识
	 * @param tvGirl     treeview对象标识
	 * @purpose          创建一个TextView对象标识，加入滚动条，并显示。
	 */
	public static void createOutputTextView(int window, int gridHouse, int start,int tvGirl)
	{

		//tvGirl = GTK.gtk_text_view_new();
		GTK.gtk_text_view_set_wrap_mode(tvGirl, GTK.GTK_WRAP_WORD);
		int scrollBar = 0 ;
		//添加控件
		GTK.gtk_text_view_set_editable(tvGirl, false);
		createTreeviewOutputScrollBar(scrollBar,tvGirl,gridHouse,start);
		//GTK.gtk_grid_attach(gridHouse, tvGirl, 0, start+1, 1, 1);

		//显示控件

		GTK.gtk_widget_show(tvGirl);

		String loveWords = "";
		//读取文本框里面的内容 ，只适用小量的文本，一般用迭代器
		//方法1   先从TextView获取int TextBuffer 
		    //    然后再从TextBuffer获取text
		final int textbuffer= GTK.gtk_text_view_get_buffer(tvGirl);

		
		//可以直接通过缓冲区编号  设置信息
		GTK.gtk_text_buffer_set_text(textbuffer, loveWords);
		
		
	}
	/**
	 * 
	 * @param gridHouse  网格对象标识
	 * @param start      网格对象的起始位置标识
	 * @purpose          创建一个TreeView对象标识，写入字段，添加记录，加入滚动条，并显示。
	 */
	public static void createSingleTreeView(int gridHouse,int start)
	{
		//3  treeview   是一个界面，相当于是一个淘宝前台， V  V的作用
				treeViewApple = GTK.gtk_tree_view_new();
				//4 建立字段  比较费劲
				//4.1创建字段
				int columnID = GTK.gtk_tree_view_column_new_with_attributes("ID", GTK.gtk_cell_renderer_text_new(), 0);
				//4.2添加字段
				GTK.gtk_tree_view_append_column(treeViewApple, columnID);
				//4.3创建字段
				int columnName = GTK.gtk_tree_view_column_new_with_attributes("Names",GTK.gtk_cell_renderer_text_new(),1);
				//4.4添加字段
				GTK.gtk_tree_view_append_column(treeViewApple,columnName);
				//4.5创建字段
				int  columnAge = GTK.gtk_tree_view_column_new_with_attributes("Ages",GTK.gtk_cell_renderer_text_new(),2);
				GTK.gtk_tree_view_append_column(treeViewApple,columnAge);
				
				//创建控件
				//1  list_store  是一个数据结构库，相当于一个仓库  M model的作用
				liststore = GTK.gtk_list_store_new(3); //3代表三个字段：ID  Names   Age
			//	GTK.gtk_list_store_append(list_store, iter);
				//2  list_iter,list_iter是一个内部的数据迭代的控制过程，不需要显示 C的作用，控制
				listiter = GTK.gtk_tree_iter_new();

				//5  第五步 把 iter和listStore联系起来
				          //GTK.gtk_text_buffer_get_end_iter 类似于treeview的buffer和iter联系起来
				           // 以后就可以通过GTK.gtk_text_buffer_insert默认是一个一个iter的执行。
				GTK.gtk_list_store_append(liststore, listiter); //指针开始指到第一个位置
				// 6 逐个iter添加数据到liststore当中:每个iter的意思，就是逐行或者逐个记录。
				GTK.gtk_list_store_set_value(liststore, listiter, 0, "001");
				GTK.gtk_list_store_set_value(liststore, listiter, 1, "YinMuHuaDao");
				GTK.gtk_list_store_set_value(liststore, listiter, 2, "35");
				GTK.gtk_list_store_append(liststore, listiter); //如果不加入，指针不下移，只会覆盖掉前面的数据
				GTK.gtk_list_store_set_value(liststore, listiter, 0, "002");
				GTK.gtk_list_store_set_value(liststore, listiter, 1, "Taiyanghua");
				GTK.gtk_list_store_set_value(liststore, listiter, 2, "29");
				GTK.gtk_list_store_append(liststore, listiter);
				GTK.gtk_list_store_set_value(liststore, listiter, 0 ,"003");
				GTK.gtk_list_store_set_value(liststore, listiter, 1 ,"Xiaojun");
				GTK.gtk_list_store_set_value(liststore, listiter, 2 ,"10");
				// 之所以添加9次是因为  3个字段*3条记录 == 9
				
				//7  使用完iter之后一定要记得 关掉它
				GTK.gtk_tree_iter_free(listiter);
				
				//8 关键的一步，把数字显示出来
				GTK.gtk_tree_view_set_model(treeViewApple, liststore);

				//并同时把它显示出来，前台必须显示
				GTK.gtk_widget_show(treeViewApple);
				
				//添加到滚动条中
				int scrolledbar2 = 0;
				createTreeviewScrollBar(scrolledbar2,treeViewApple,gridHouse,start);
				
				//设置部分参数
				int column =GTK.gtk_tree_view_get_column(treeViewApple, 1);
				GTK.gtk_tree_view_column_set_sort_column_id(column, 1);
				GTK.gtk_tree_view_column_set_resizable(column, true);
				GTK.gtk_tree_view_column_set_reorderable(column, true);
	}
	
	/**
	 * 
	 * @param window          窗口的对象标识
	 * @param gridHouse       网格布局的对象标识
	 * @param start           对象所在的网格布局起始位置
	 * @param circlePointx    画笔的x轴位置
	 * @param circlePointy    画笔的y轴位置
	 * @purpose               绘制一个笑脸
	 */
	public static void createDrawLaughFace(int window,int gridHouse,int start,final int circlePointx,final int circlePointy) 
	{
		//创建画图板 或者叫画布
		int dan  = GTK.gtk_drawing_area_new();
		
		//创建源
		//添加控件
		start = 0;
		GTK.gtk_grid_attach(gridHouse, dan, 1, start, 1, 1);
		//显示画布
		GTK.gtk_widget_show(dan);
		

		
		//设置画布的大小
		GTK.gtk_widget_set_size_request(dan, 300, 300);
		
		
		//开始绘制  利用事件draw 来不断的绘制
		
		GTK.g_signal_connect(dan, "draw",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//eventDate才是画布源
				//设置画笔的颜色
				//Cairo.cairo_destroy(eventData);
				Cairo.cairo_set_source_rgb(eventData, 1, 0, 0);
				//设置画笔的大小
				Cairo.cairo_set_line_width(eventData, 3);			
				Cairo.cairo_move_to(eventData, circlePointx-20, circlePointy);
				Cairo.cairo_line_to(eventData, circlePointx-70, circlePointy);
				Cairo.cairo_line_to(eventData, circlePointx-60, circlePointy-5);
				Cairo.cairo_move_to(eventData, circlePointx-70, circlePointy);
				Cairo.cairo_line_to(eventData, circlePointx-60, circlePointy+5);
				Cairo.cairo_stroke(eventData);
				
				//画一个圆
				Cairo.cairo_arc(eventData, circlePointx, circlePointy, 10, 0, 2*3.1415926);
				//画一个圆弧
				//Cairo.cairo_arc(eventData, 200, 200, 70,1.5*3.1415925, 2*3.1415926);
				//显示画笔
				Cairo.cairo_stroke(eventData); //Cairo.cairo_fill(eventData)不同的效果
				
				
				//画一个圆弧  有下面实验知道是从x轴沿顺时针开始画图

				//嘴巴的绘制
				double Pi = 3.1415926;
				Cairo.cairo_arc(eventData, circlePointx, circlePointy, 5, 0.25*Pi, 0.75*Pi);
				Cairo.cairo_fill(eventData);
				
				//绘制两只眼睛
				Cairo.cairo_arc(eventData, circlePointx-5, circlePointy-5, 3, 0, 2*Pi);
				Cairo.cairo_arc(eventData, circlePointx+5, circlePointy-5, 3, 0, 2*Pi);
				Cairo.cairo_fill(eventData);
				
				

				//画文字
				Cairo.cairo_move_to(eventData, circlePointx+10, circlePointy+5);
				Cairo.cairo_set_source_rgb(eventData, 0.4, 0.3, 0.2);
				Cairo.cairo_set_font_size(eventData, 16);
				//选择文字类型
				Cairo.cairo_select_font_face(eventData, "宋体", Cairo.CAIRO_FONT_SLANT_ITALIC, Cairo.CAIRO_FONT_WEIGHT_BOLD);
				Cairo.cairo_show_text(eventData,note);
				//Cairo.cairo_stroke(eventData);  //不需要这句话也可以
				
			}
		}, null);
		
		
	}
	/**
	 * 
	 * @param window          窗口的对象标识
	 * @param gridHouse       网格布局的对象标识
	 * @param start           矩形对象所在的网格布局起始位置
	 * @param circlePointx    画笔的x轴位置
	 * @param circlePointy    画笔的y轴位置
	 * @purpose               绘制一个矩形图像
	 */
	public static void createRectangleFace(int window,int gridHouse,int start,final int circlePointx,final int circlePointy) 
	{
		//创建画图板 或者叫画布
		int dan  = GTK.gtk_drawing_area_new();
		
		//创建源
		//添加控件
		start = 4;
		GTK.gtk_grid_attach(gridHouse, dan, 0, start, 1, 1);
		//显示画布
		GTK.gtk_widget_show(dan);
		

		
		//设置画布的大小
		GTK.gtk_widget_set_size_request(dan, 300, 300);
		
		
		//开始绘制  利用事件draw 来不断的绘制
		
		GTK.g_signal_connect(dan, "draw",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//eventDate才是画布源
				//设置画笔的颜色
				//Cairo.cairo_destroy(eventData);
				Cairo.cairo_set_source_rgb(eventData, 0, 1, 0);
				//设置画笔的大小
				Cairo.cairo_set_line_width(eventData, 3);			
				//Cairo.cairo_move_to(eventData, circlePointx-20, circlePointy);

				
				//画一个矩形
				Cairo.cairo_rectangle(eventData, circleRectangex-150, circleRectangey+120, 250, 20);
				Cairo.cairo_stroke(eventData);
				//画文字
				Cairo.cairo_move_to(eventData, circleRectangex+10, circleRectangey+100);
				Cairo.cairo_set_source_rgb(eventData, 0, 0, 1);
				Cairo.cairo_set_font_size(eventData, 18);
				//选择文字类型
				Cairo.cairo_select_font_face(eventData, "宋体", Cairo.CAIRO_FONT_SLANT_NORMAL, Cairo.CAIRO_FONT_WEIGHT_BOLD);
				Cairo.cairo_show_text(eventData,"循环第"+Integer.toString(rowPosition)+"次");//
				//Cairo.cairo_stroke(eventData);  //不需要这句话也可以
				
			}
		}, null);
		
		
	}
	/**
	 * 
	 * @param window          窗口的对象标识
	 * @param gridHouse       网格布局的对象标识
	 * @param start           矩形对象所在的网格布局起始位置
	 * @param circlePointx    画笔的x轴位置
	 * @param circlePointy    画笔的y轴位置
	 * @purpose               绘制一个圆圈图像 + 终止表示！
	 */
	public static void createCircleFace(int window,int gridHouse,int start,final int circlePointx,final int circlePointy) 
	{
		//创建画图板 或者叫画布
		int dan  = GTK.gtk_drawing_area_new();
		
		//创建源
		//添加控件
		start = 4;
		GTK.gtk_grid_attach(gridHouse, dan, 0, start, 1, 1);
		//显示画布
		GTK.gtk_widget_show(dan);
		

		
		//设置画布的大小
		GTK.gtk_widget_set_size_request(dan, 300, 300);
		
		
		//开始绘制  利用事件draw 来不断的绘制
		
		GTK.g_signal_connect(dan, "draw",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//eventDate才是画布源
				//设置画笔的颜色
				//Cairo.cairo_destroy(eventData);
				Cairo.cairo_set_source_rgb(eventData, 1, 0, 1);
				//设置画笔的大小
				Cairo.cairo_set_line_width(eventData, 3);			
				//Cairo.cairo_move_to(eventData, circlePointx-20, circlePointy);

				
				//画一个矩形
				Cairo.cairo_arc(eventData, EndCirclex+150, EndCircley+200, 100, 0, 6.28);
				Cairo.cairo_stroke(eventData);
				//画文字
				Cairo.cairo_move_to(eventData, EndCirclex+50, EndCircley+200);
				Cairo.cairo_set_source_rgb(eventData, 1, 0, 1);
				Cairo.cairo_set_font_size(eventData, 18);
				//选择文字类型
				Cairo.cairo_select_font_face(eventData, "宋体", Cairo.CAIRO_FONT_SLANT_NORMAL, Cairo.CAIRO_FONT_WEIGHT_BOLD);
				Cairo.cairo_show_text(eventData,"循环第"+Integer.toString(rowPosition+1)+"次,程序终止退出！");//
				//Cairo.cairo_stroke(eventData);  //不需要这句话也可以
				
			}
		}, null);
		
		
	}
	/**
	 * 
	 * @param gridHouse         网格布局对象标识
	 * @param textViewOutput    textview的对象标识
	 * @param start             textview对象在网格布局的起始位置
	 * @purpose                 创建一个下一步button，并添加事件监听
	 */
	public static void createNextButton(int gridHouse,int textViewOutput,int start)
	{
		int btnApple = GTK.gtk_button_new_with_label("下一步");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 6, start, 1, 1);
		insertNextEvent(btnApple,textViewOutput);
	}
	/**
	 * 
	 * @param btnSin            button下一步的对象标识
	 * @param textViewOutput    textview的对象标识
	 * @purpose                 建立button下一步的事件监听
	 */
	public static void insertNextEvent(final int btnSin,final int textViewOutput)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				
				// TODO 自动生成的方法存根
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				if(rowPosition < getNumberOfListstore(liststore))
				{
					circlePointy = circlePointy+20;
					//创建笑脸
					createDrawLaughFace(window,gridHouse,start,circlePointx,circlePointy);
					//创建矩形框
					createRectangleFace(window,gridHouse,start+1,circleRectangex,circleRectangey);
					printRecord(liststore);
					InsertStringToTextViewFunction(textViewOutput,"我在do循环里面  \n");
					rowPosition =  rowPosition +1;
				}
				else 
				{
					
					//note = "指针无法下移";
					circlePointy = circlePointy+20;
					if(rowPosition > getNumberOfListstore(liststore))
					{
						createDrawLaughFace(window,gridHouse,start,circlePointx,circlePointy);
					}
					
					createRectangleFace(window,gridHouse,start+1,circleRectangex,circleRectangey);
					createCircleFace(window,gridHouse,start,EndCirclex,EndCircley);
					InsertStringToTextViewFunction(textViewOutput,"别嗯了！程序没有数据了\n");
					showInfo("没有数据了","指针无法下移");
				}

			}
		}, null);
	}
	/**
	 * 
	 * @param liststore   liststore是treeview的原始数据
	 * @return            返回liststore的记录个数
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
	 * @param liststore   liststore是treeview的原始数据
	 * @purpose           打印liststore的所有内容
	 */
	public static void printRecord(int liststore)
	{
		//必须新建，不能利用原先的listiter,哪边需要用控制，就需要建一个控件。！！！否则报错！！不能用全局的 listiter
		int iter = GTK.gtk_tree_iter_new();
		int i  =0;
/*		if(GTK.gtk_tree_model_get_iter_first(liststore, listiter))
		{
			do
			{
				String ID = GTK.gtk_tree_model_get_value(liststore, listiter, 0);
				String Names = GTK.gtk_tree_model_get_value(liststore, listiter, 1);
				String Ages = GTK.gtk_tree_model_get_value(liststore, listiter, 2);
				//showInfo("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages,"显示员工信息");
				System.out.println("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages);
				
			}while(GTK.gtk_tree_model_iter_next(liststore,listiter));
		}else
		{
			System.out.println("表中无数据");
		}*/
		// 减少代码的深度。
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			System.out.println("treeview没有数据  请添加记录");
			return;
		}
		do
		{
			if(rowPosition == i)
			{
				String ID = GTK.gtk_tree_model_get_value(liststore, iter, 0);
				String Names = GTK.gtk_tree_model_get_value(liststore, iter, 1);
				String Ages = GTK.gtk_tree_model_get_value(liststore, iter, 2);
				showInfo("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages,"显示员工信息");
				//System.out.println("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages);
				return;
			}
			i++;
			
		}while(GTK.gtk_tree_model_iter_next(liststore,iter));
		
		GTK.gtk_tree_iter_free(iter);//释放了 还是有错误。。。
	}
	/**
	 * 
	 * @param message    输入一个消息，显示在消息框
	 * @param title      消息框的标题
	 * @purpose          创建一个消息提示对话框
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
	 * @param textview   textview对象标识
	 * @param temp       待插入textview的字符串
	 * @purpose          插入一行数据岛textview当中
	 */
	public static void InsertStringToTextViewFunction(int textview,String temp)
	{
		//TextIter是一个TextView的迭代器。
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //这是一个空的iter，需要用textBuffer进行赋值
		//GTK.gtk_text_iter_forward_to_end(textIter);
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// 或者textview的textBuffer的末尾！
		GTK.gtk_text_buffer_insert(textBuffer, textIter, temp);
		
		//GTK.gtk_text_buffer_g
		GTK.gtk_text_iter_free(textIter);
		
	}
	
	
}
