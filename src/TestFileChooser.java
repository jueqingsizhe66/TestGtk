

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;




/**
 * @author    叶昭良
 * @time      2015年2月1日下午5:56:29
 * @version   TestFileChooser V1.0  简易记事本  打开文件 保存文件 显示照片
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
		int btnOrange = GTK.gtk_button_new_with_label("显示图片文件");
		//创建布局
		gridHouse= GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		GTK.gtk_container_add(window, gridHouse);
		
		//GTK.gtk_widget_set_size_request(window, 500, 500);
		//添加控件到合租房
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, 0, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnBanana, 1, 0, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnOrange, 2, 0, 1, 1);
		createTextView(window,gridHouse,1);
		
		//显示控件
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_widget_show(btnBanana);
		GTK.gtk_widget_show(btnOrange);
		
		//添加按钮事件
		 insertButtonEvent(btnApple);
		 insertButtonSaveEvent(btnBanana);
		 insertButtonPictureEvent(btnOrange);
		//启动循环
		GTK.gtk_main();
	}
	public static void saveFile(int textview) 
	{
		int dlg1 = GTK.gtk_file_chooser_dialog_new("保存文件", 0, GTK.GTK_FILE_CHOOSER_ACTION_SAVE,"保存");
		GTK.gtk_file_chooser_set_do_overwrite_confirmation(dlg1, true);
		//String filename = GTK.gtk_file_chooser_get_filename(dlg1);
		System.out.println("已进入save");
		//SaveOneFile(filename,textview);
		int ret = GTK.gtk_dialog_run(dlg1);
		if(ret == GTK.GTK_RESPONSE_CANCEL)
		{
			GTK.gtk_widget_destroy(dlg1);
		}else 
		{
			String filename = GTK.gtk_file_chooser_get_filename(dlg1);
			System.out.println(filename);
			SaveOneFile(filename,textview);
			GTK.gtk_widget_destroy(dlg1);
		}
		
	}
	public static String[] selectFile() 
	{
		int dlg = GTK.gtk_file_chooser_dialog_new("打开文件", 0, GTK.GTK_FILE_CHOOSER_ACTION_OPEN,"打开");
		GTK.gtk_file_chooser_set_select_multiple(dlg, true);
		
		//创建过滤器
		int filter = GTK.gtk_file_filter_new();
		GTK.gtk_file_filter_add_pattern(filter, "*.txt");
		GTK.gtk_file_filter_add_pattern(filter, "*.java");
		GTK.gtk_file_filter_set_name(filter, "文本文件");
		GTK.gtk_file_chooser_add_filter(dlg, filter);
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
	public static void selectFile(int gridHouse,int start) 
	{
		int dlg = GTK.gtk_file_chooser_dialog_new("打开文件", 0, GTK.GTK_FILE_CHOOSER_ACTION_OPEN,"打开");
		GTK.gtk_file_chooser_set_select_multiple(dlg, true);
		
		//创建过滤器
		int filter = GTK.gtk_file_filter_new();
		GTK.gtk_file_filter_add_pattern(filter, "*.jpg");
		GTK.gtk_file_filter_add_pattern(filter, "*.png");
		GTK.gtk_file_filter_add_pattern(filter, "*.gif");
		GTK.gtk_file_filter_set_name(filter, "图片文件");
		GTK.gtk_file_chooser_add_filter(dlg, filter);
		int ret = GTK.gtk_dialog_run(dlg);
		String[] filenames = null;
		if(ret == GTK.GTK_RESPONSE_OK) 
		{
			filenames = GTK.gtk_file_chooser_get_filenames(dlg);
			for(int i = 0 ; i< filenames.length ; i++)
			{

				System.out.println("选中文件名"+i+": "+filenames[i]);
				int temp = GTK.gtk_image_new_from_file(filenames[i]);
				GTK.gtk_grid_attach(gridHouse, temp, i, start, 3, 1);
				GTK.gtk_widget_show(temp);
			}
			GTK.gtk_widget_destroy(dlg);
		}else
		{
			GTK.gtk_widget_destroy(dlg);
		}

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
			
			String  temp= filename.substring(filename.lastIndexOf('\\')+1);
			System.out.println(temp);
			InsertStringToTextViewFunction(textview,"******************\n当期文件为"+filename+"\n******************\n\n"+temp+" 文件内容如下：\n+---------------------------------------------------------------------------+\n");
			String content = null;
			while((content = br.readLine())!=null) // -1读取完毕
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				InsertStringToTextViewFunction(textview,new String(content));
			}
			InsertStringToTextViewFunction(textview,"\n+---------------------------------------------------------------------------+\n******************\n文件"+filename+"读取结束\n******************\n");
		}
		catch(IOException e)
		{
			System.out.println("文件读入异常");
		}
		
	}
	
	public static void SaveOneFile(String filename, int textview)
	{
		try
		(
			OutputStream os = new FileOutputStream(filename);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
		)
		{
			
			String  temp= filename.substring(filename.lastIndexOf('\\')+1);
			System.out.println(temp);
			
			int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
			int textIter = GTK.gtk_text_iter_new();  //这是一个空的iter，需要用textBuffer进行赋值
			GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// 或者textview的textBuffer的末尾！
			String tempText = GTK.gtk_text_buffer_get_text(textBuffer);
			
			//while(tempText != null) // -1读取完毕
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				bw.write(tempText);
				bw.newLine();
			//	GTK.gtk_text_iter_backward_char(textIter);
			//	tempText = GTK.gtk_text_buffer_get_text(textBuffer);
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
		//GTK.gtk_text_view_set_wrap_mode(textview,GTK.GTK_WRAP_WORD);
		GTK.gtk_text_view_set_wrap_mode(textview,GTK.GTK_WRAP_WORD_CHAR);
		GTK.gtk_widget_show(textview);
	}
	public static void createScrolledBar(int window, int textview, int gridHouse,int start)
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrolledBar);
		GTK.gtk_widget_set_size_request(scrolledBar, 200, 200);
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
				GTK.gtk_widget_show(scrolledBar);
				GTK.gtk_widget_set_size_request(scrolledBar, 400, 400);
				String temp = GTK.gtk_button_get_label(btnSin);
				selectFiles =  selectFile() ;
				//isEnter = false;
				showAllFiles(selectFiles, textview);
			}
		}, null);
	}
	public static void insertButtonSaveEvent(final int btnSin)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				saveFile(textview);
			}
		}, null);
	}
	public static void insertButtonPictureEvent(final int btnSin)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				GTK.gtk_widget_hide(scrolledBar);
				selectFile(gridHouse, 2) ;
			}
		}, null);
	}


}
