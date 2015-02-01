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
 * @author    Ҷ����
 * @time      2015��2��1������5:56:29
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
		// TODO �Զ����ɵķ������
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_widget_show(window);
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
		}, null);
		int btnApple = GTK.gtk_button_new_with_label("���ļ�");
		int btnBanana = GTK.gtk_button_new_with_label("�����ļ�");
		//��������
		gridHouse= GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		GTK.gtk_container_add(window, gridHouse);
		
		//GTK.gtk_widget_set_size_request(window, 500, 500);
		//���ӿؼ������ⷿ
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, 0, 1, 1);
		createTextView(window,gridHouse,1);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(btnApple);
		
		//���Ӱ�ť�¼�
		 insertButtonEvent(btnApple);
		//����ѭ��
		GTK.gtk_main();
	}
	public static String[] selectFile() 
	{
		int dlg = GTK.gtk_file_chooser_dialog_new("���ļ�", 0, GTK.GTK_FILE_CHOOSER_ACTION_OPEN,"��");
		GTK.gtk_file_chooser_set_select_multiple(dlg, true);
		int ret = GTK.gtk_dialog_run(dlg);
		String[] filenames = null;
		if(ret == GTK.GTK_RESPONSE_OK) 
		{
			filenames = GTK.gtk_file_chooser_get_filenames(dlg);
			for(int i = 0 ; i< filenames.length ; i++)
			{
				
				System.out.println("ѡ���ļ���"+i+": "+filenames[i]);
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
			while((len = bis.read(fileToFile))!= -1) // -1��ȡ���
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				InsertStringToTextViewFunction(textview,new String(fileToFile,"gb2312"));
			}
		}
		catch(IOException e)
		{
			System.out.println("�ļ������쳣");
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
			while((content = br.readLine())!=null) // -1��ȡ���
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				InsertStringToTextViewFunction(textview,new String(content));
			}
		}
		catch(IOException e)
		{
			System.out.println("�ļ������쳣");
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
	
	//��װ��  TextView�ĵ���������������
	/**
	 * 
	 * @param textview  �����ı�TextView�ı�ʶ
	 * @param temp      ����TextView ���ַ�����
	 */
	public static void InsertStringToTextViewFunction(int textview,String temp)
	{
		//TextIter��һ��TextView�ĵ�������
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //����һ���յ�iter����Ҫ��textBuffer���и�ֵ
		//GTK.gtk_text_iter_forward_to_end(textIter);
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// ����textview��textBuffer��ĩβ��
		GTK.gtk_text_buffer_insert(textBuffer, textIter, temp);
		
		//GTK.gtk_text_buffer_g
		GTK.gtk_text_iter_free(textIter);
		
	}
	/**
	 * 
	 * @param textview   TextView�Ķ����ı��ı�ʶ
	 * @return           ���ص��ַ���
	 */
	public static String GetStringFromTextViewFunction(int textview)
	{
		//TextIter��һ��TextView�ĵ�������
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //����һ���յ�iter����Ҫ��textBuffer���и�ֵ
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// ����textview��textBuffer��ĩβ��
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
	 * @param btnSin   ��ť�ı�ʶ
	 * @param tv1      textview�ı�ʶ
	 */
	public static void insertButtonEvent(final int btnSin,final int tv1)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
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
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				String temp = GTK.gtk_button_get_label(btnSin);
				selectFiles =  selectFile() ;
				//isEnter = false;
				showAllFiles(selectFiles, textview);
			}
		}, null);
	}

}