
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
 * @author    Ҷ����
 * @time      2015��2��1������5:56:29
 * @version   TestFileChooser V1.0  ���׼��±�  ���ļ� �����ļ� ��ʾ��Ƭ
 * @version   TestFileChooser V2.0  �����˴򿪶Ի���  �������˶Ի����ڲ��ؼ����¼�
 * @version   TestFileChooser V3.0  �����˹�����
 */
import com.rupeng.gtk4j.*;
public class TestFileChooser2
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
		int btnOrange = GTK.gtk_button_new_with_label("��ʾͼƬ�ļ�");
		int btnPeal   = GTK.gtk_button_new_with_label("�򿪶Ի���");
		//��������
		gridHouse= GTK.gtk_grid_new();
		GTK.gtk_widget_show(gridHouse);
		GTK.gtk_container_add(window, gridHouse);
		
		//GTK.gtk_widget_set_size_request(window, 500, 500);
		//���ӿؼ������ⷿ
		int start = 0;
		createToolbar(gridHouse,start);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, 2, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnBanana, 1, 2, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnOrange, 2, 2, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnPeal, 3, 2, 1, 1);
		createTextView(window,gridHouse,3);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_widget_show(btnBanana);
		GTK.gtk_widget_show(btnOrange);
		GTK.gtk_widget_show(btnPeal);
		
		//���Ӱ�ť�¼�
		 insertButtonEvent(btnApple);
		 insertButtonSaveEvent(btnBanana);
		 insertButtonPictureEvent(btnOrange);
		 insertButtonDialogEvent(btnPeal);
		//����ѭ��
		GTK.gtk_main();
	}
	public static void saveFile(int textview) 
	{
		int dlg1 = GTK.gtk_file_chooser_dialog_new("�����ļ�", 0, GTK.GTK_FILE_CHOOSER_ACTION_SAVE,"����");
		GTK.gtk_file_chooser_set_do_overwrite_confirmation(dlg1, true);
		//String filename = GTK.gtk_file_chooser_get_filename(dlg1);
		System.out.println("�ѽ���save");
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
		int dlg = GTK.gtk_file_chooser_dialog_new("���ļ�", 0, GTK.GTK_FILE_CHOOSER_ACTION_OPEN,"��");
		GTK.gtk_file_chooser_set_select_multiple(dlg, true);
		
		//����������
		int filter = GTK.gtk_file_filter_new();
		GTK.gtk_file_filter_add_pattern(filter, "*.txt");
		GTK.gtk_file_filter_add_pattern(filter, "*.java");
		GTK.gtk_file_filter_set_name(filter, "�ı��ļ�");
		GTK.gtk_file_chooser_add_filter(dlg, filter);
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
	public static void selectFile(int gridHouse,int start) 
	{
		int dlg = GTK.gtk_file_chooser_dialog_new("���ļ�", 0, GTK.GTK_FILE_CHOOSER_ACTION_OPEN,"��");
		GTK.gtk_file_chooser_set_select_multiple(dlg, true);
		
		//����������
		int filter = GTK.gtk_file_filter_new();
		GTK.gtk_file_filter_add_pattern(filter, "*.jpg");
		GTK.gtk_file_filter_add_pattern(filter, "*.png");
		GTK.gtk_file_filter_add_pattern(filter, "*.gif");
		GTK.gtk_file_filter_set_name(filter, "ͼƬ�ļ�");
		GTK.gtk_file_chooser_add_filter(dlg, filter);
		int ret = GTK.gtk_dialog_run(dlg);
		String[] filenames = null;
		if(ret == GTK.GTK_RESPONSE_OK) 
		{
			filenames = GTK.gtk_file_chooser_get_filenames(dlg);
			for(int i = 0 ; i< filenames.length ; i++)
			{

				System.out.println("ѡ���ļ���"+i+": "+filenames[i]);
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
			
			String  temp= filename.substring(filename.lastIndexOf('\\')+1);
			System.out.println(temp);
			InsertStringToTextViewFunction(textview,"******************\n�����ļ�Ϊ"+filename+"\n******************\n\n"+temp+" �ļ��������£�\n+---------------------------------------------------------------------------+\n");
			String content = null;
			while((content = br.readLine())!=null) // -1��ȡ���
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				InsertStringToTextViewFunction(textview,new String(content));
			}
			InsertStringToTextViewFunction(textview,"\n+---------------------------------------------------------------------------+\n******************\n�ļ�"+filename+"��ȡ����\n******************\n");
		}
		catch(IOException e)
		{
			System.out.println("�ļ������쳣");
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
			int textIter = GTK.gtk_text_iter_new();  //����һ���յ�iter����Ҫ��textBuffer���и�ֵ
			GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// ����textview��textBuffer��ĩβ��
			String tempText = GTK.gtk_text_buffer_get_text(textBuffer);
			
			//while(tempText != null) // -1��ȡ���
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
			System.out.println("�ļ������쳣");
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
	public static boolean showInfo(  String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}
	public static void createDialog()
	{
		int dialogApple = GTK.gtk_dialog_new();
		int btnApple = GTK.gtk_button_new_with_label("������");
		
		//action_are���һ�� ����box,grid�����ã�������������
		int areaDialog = GTK.gtk_dialog_get_action_area(dialogApple);
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_container_add(areaDialog, btnApple);
		
		GTK.g_signal_connect(btnApple, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				showInfo("��Ȼ�ǲ��Ǻ���","�ǿɲ�");
			}
		}, null);
		
		int ret = GTK.gtk_dialog_run(dialogApple);
		if(ret == GTK.GTK_RESPONSE_OK)
		{
			System.out.println("�ټ�");
			GTK.gtk_widget_destroy(dialogApple);
		}
		GTK.gtk_widget_destroy(dialogApple);
	}

	//     ********************�¼����崦�� ����***************************
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
				// TODO �Զ����ɵķ������
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
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				GTK.gtk_widget_hide(scrolledBar);
				selectFile(gridHouse, 2) ;
			}
		}, null);
	}
	
	public static void insertButtonDialogEvent(final int btnSin)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				createDialog();
			}
		}, null);
	}

	public static void createToolbar(int gridHouse,int start)
	{
		//��������������
		int tbApple = GTK.gtk_toolbar_new();
		GTK.gtk_widget_show(tbApple);
		GTK.gtk_widget_set_size_request(tbApple, 300, 20);
		GTK.gtk_grid_attach(gridHouse, tbApple, 0, start, 1, 1);
		
	  int btnNew = GTK.gtk_tool_button_new(0, "�½�");
	  GTK.gtk_tool_button_set_stock_id(btnNew, GTK.GTK_STOCK_NEW);
	  GTK.gtk_widget_show(btnNew);
	  GTK.gtk_toolbar_insert(tbApple, btnNew, 0);
		   
	  final int btnOpen = GTK.gtk_tool_button_new(0, "��");
	  GTK.gtk_tool_button_set_stock_id(btnOpen, GTK.GTK_STOCK_OPEN);
	  GTK.gtk_widget_show(btnOpen);
	  GTK.gtk_toolbar_insert(tbApple, btnOpen, 1);

	  GTK.g_signal_connect(btnOpen, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				System.out.println("hello");
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				GTK.gtk_widget_show(scrolledBar);
				GTK.gtk_widget_set_size_request(scrolledBar, 400, 400);
				//String temp = GTK.gtk_button_get_label(btnOpen);
				selectFiles =  selectFile() ;
				//isEnter = false;
				showAllFiles(selectFiles, textview);
			}
		}, null);

	  int btnSave = GTK.gtk_tool_button_new(0, "����");
	  GTK.gtk_tool_button_set_stock_id(btnSave, GTK.GTK_STOCK_SAVE);
	  GTK.gtk_widget_show(btnSave);
	  GTK.gtk_toolbar_insert(tbApple, btnSave, 2);
	  GTK.g_signal_connect(btnSave, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				saveFile(textview);
			}
		}, null);
	  
	  
	}
}