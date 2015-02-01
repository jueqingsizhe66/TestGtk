/**
 * 
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


import java.io.InputStreamReader;




/**
 * @author    叶昭良
 * @time      2015年2月1日下午5:56:29
 * @version   TestFileChooser V1.0
 */
import com.rupeng.gtk4j.*;
public class TestFileChooser
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static int scrolledBar;
	static int textview;
	static int  btnApple;
	static String[] selectFiles = null;
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_widget_show(window);
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
		int btnApple = GTK.gtk_button_new_with_label("打开文件");
		int btnBanana = GTK.gtk_button_new_with_label("保存文件");
		//创建布局
		gridHouse= GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		GTK.gtk_container_add(window, gridHouse);
		
		//GTK.gtk_widget_set_size_request(window, 500, 500);
		//添加控件到合租房
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, 0, 1, 1);
		createTextView(window,gridHouse,1);
		
		//显示控件
		GTK.gtk_widget_show(btnApple);
		
		//添加按钮事件
		 insertButtonEvent(btnApple);
		//启动循环
		GTK.gtk_main();
	}
	public static String[] selectFile() 
	{
		int dlg = GTK.gtk_file_chooser_dialog_new("打开文件", 0, GTK.GTK_FILE_CHOOSER_ACTION_OPEN,"打开");
		GTK.gtk_file_chooser_set_select_multiple(dlg, true);
		int ret = GTK.gtk_dialog_run(dlg);
		String[] filenames = null;
		if(ret == GTK.GTK_RESPONSE_OK) 
		{
			filenames = GTK.gtk_file_chooser_get_filenames(dlg);
			for(int i = 0 ; i< filenames.length ; i++)
			{
				
				System.out.println("选中文件名"+i+": "+filenames[i]);
			}
			GTK.gtk_widget_destroy(dlg);
		}else
		{
			GTK.gtk_widget_destroy(dlg);
		}
		return filenames;
	}
	
	public static void showAllFiles(String[] filenames,int textview)
	{
		for(int i = 0 ; i < filenames.length; i++)
		{
			showOneFile(filenames[i],textview);
		}
	}
/*	public static void showOneFile(String filename, int textview)
	{
		try
		(
			InputStream fis = new FileInputStream(filename);
			
			BufferedInputStream bis = new BufferedInputStream(fis);
		)
		{
			int len = 0;
			byte[] fileToFile = new byte[512*1024];
			while((len = bis.read(fileToFile))!= -1) // -1读取完毕
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				InsertStringToTextViewFunction(textview,new String(fileToFile,"gb2312"));
			}
		}
		catch(IOException e)
		{
			System.out.println("文件读入异常");
		}
		
	}*/
	public static void showOneFile(String filename, int textview)
	{
		try
		(
			InputStream is = new FileInputStream(filename);
			InputStreamReader osr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(osr);
		)
		{
			String content = null;
			while((content = br.readLine())!=null) // -1读取完毕
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				InsertStringToTextViewFunction(textview,new String(content));
			}
		}
		catch(IOException e)
		{
			System.out.println("文件读入异常");
		}
		
	}
	public static void createTextView(int window, int gridHouse,int start)
	{
		textview =  GTK.gtk_text_view_new();
		createScrolledBar(window,textview,gridHouse,start);
		GTK.gtk_text_view_set_wrap_mode(textview,GTK.GTK_WRAP_WORD);
		GTK.gtk_widget_show(textview);
	}
	public static void createScrolledBar(int window, int textview, int gridHouse,int start)
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrolledBar);
		GTK.gtk_widget_set_size_request(scrolledBar, 500, 500);
		GTK.gtk_grid_attach(gridHouse, scrolledBar, 0, start, 1, 1);
		GTK.gtk_container_add(scrolledBar, textview);
		
	}
	
	//封装了  TextView的迭代器操作。。。
	/**
	 * 
	 * @param textview  多行文本TextView的标识
	 * @param temp      插入TextView 的字符串。
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
	/**
	 * 
	 * @param textview   TextView的多行文本的标识
	 * @return           返回的字符串
	 */
	public static String GetStringFromTextViewFunction(int textview)
	{
		//TextIter是一个TextView的迭代器。
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //这是一个空的iter，需要用textBuffer进行赋值
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// 或者textview的textBuffer的末尾！
		String temp = GTK.gtk_text_buffer_get_text(textBuffer);
		
		String[] splitArray = temp.split("\\n");
		temp = splitArray[splitArray.length-1];
		System.out.println(temp);
		return temp;
		//GTK.gtk_text_buffer_g
		//GTK.gtk_text_iter_free(textIter);
		
	}
	
	/**
	 * 
	 * @param btnSin   按钮的标识
	 * @param tv1      textview的标识
	 */
	public static void insertButtonEvent(final int btnSin,final int tv1)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				String temp = GTK.gtk_button_get_label(btnSin);
				InsertStringToTextViewFunction(tv1,temp);
				//isEnter = false;
			}
		}, null);
	}
	public static void insertButtonEvent(final int btnSin)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				String temp = GTK.gtk_button_get_label(btnSin);
				selectFiles =  selectFile() ;
				//isEnter = false;
				showAllFiles(selectFiles, textview);
			}
		}, null);
	}

}
