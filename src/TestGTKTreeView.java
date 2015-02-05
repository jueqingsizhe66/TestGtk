import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * 
 */

/**
 * @author    Ҷ����
 * @time      2015��2��2������6:02:11
 * @version   TestGTKTreeView V1.0  һ��������treeview��ʹ������  + �ֶδ���+��¼����
 * @version   TestGTKTreeView V2.0  �����˱������� �Լ���ʾĳ��ĳ�е���Ϣ
 * @version   TestGTKTreeView V3.0  �����ˡ��п��ơ� ����treeview ��ĳ�п���ʾ�������� �������򣬿ɵ���
 *                                  �п���ֻ�Ǹı�treeview�ı��֣�������ı�liststore��model���ڲ�ֵ
 * @version   TestGTKTreeView V4.0  ���������Ӽ�¼�Ĺ���         
 * @version   TestGTKTreeView V5.0  ������getSelection �����в����� ��δȡ�����ݣ�Ҳ��δɾ������                 
 */ 
public class TestGTKTreeView
{

	/**
	 * @param args
	 */
	static int window;
	static int gridHouse;
	static int liststore;
	static int listiter;
	static boolean columnVisible = false; //��static ����  ��Ҫ��final������
	static boolean columnResize = true; //��static ����  ��Ҫ��final������
	static boolean columnRecordable = true; // ���ÿ�����
	static int entryID;
	static int entryName;
	static int entryAge;
	static int treeViewApple;
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_widget_show(window);
		GTK.gtk_window_set_title(window, "TreeView TestVersion");
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				GTK.gtk_main_quit();
			}
		}, null);
		int start = 0;
		//���������Ӳ���
		gridHouse = GTK.gtk_grid_new();
		GTK.gtk_container_add(window, gridHouse);
		GTK.gtk_widget_show(gridHouse);
		//3  treeview   ��һ�����棬�൱����һ���Ա�ǰ̨�� V  V������
		treeViewApple = GTK.gtk_tree_view_new();
		//4 �����ֶ�  �ȽϷѾ�
		//4.1�����ֶ�
		int columnID = GTK.gtk_tree_view_column_new_with_attributes("ID", GTK.gtk_cell_renderer_text_new(), 0);
		//4.2�����ֶ�
		GTK.gtk_tree_view_append_column(treeViewApple, columnID);
		//4.3�����ֶ�
		int columnName = GTK.gtk_tree_view_column_new_with_attributes("Names",GTK.gtk_cell_renderer_text_new(),1);
		//4.4�����ֶ�
		GTK.gtk_tree_view_append_column(treeViewApple,columnName);
		//4.5�����ֶ�
		int  columnAge = GTK.gtk_tree_view_column_new_with_attributes("Ages",GTK.gtk_cell_renderer_text_new(),2);
		GTK.gtk_tree_view_append_column(treeViewApple,columnAge);
		
		//�����ؼ�
		//1  list_store  ��һ�����ݽṹ�⣬�൱��һ���ֿ�  M model������
		liststore = GTK.gtk_list_store_new(3); //3���������ֶΣ�ID  Names   Age
	//	GTK.gtk_list_store_append(list_store, iter);
		//2  list_iter,list_iter��һ���ڲ������ݵ����Ŀ��ƹ��̣�����Ҫ��ʾ C�����ã�����
		listiter = GTK.gtk_tree_iter_new();

		//5  ���岽 �� iter��listStore��ϵ����
		          //GTK.gtk_text_buffer_get_end_iter ������treeview��buffer��iter��ϵ����
		           // �Ժ�Ϳ���ͨ��GTK.gtk_text_buffer_insertĬ����һ��һ��iter��ִ�С�
		GTK.gtk_list_store_append(liststore, listiter); //ָ�뿪ʼָ����һ��λ��
		// 6 ���iter�������ݵ�liststore����:ÿ��iter����˼���������л��������¼��
		GTK.gtk_list_store_set_value(liststore, listiter, 0, "001");
		GTK.gtk_list_store_set_value(liststore, listiter, 1, "YinMuHuaDao");
		GTK.gtk_list_store_set_value(liststore, listiter, 2, "35");
		GTK.gtk_list_store_append(liststore, listiter); //��������룬ָ�벻���ƣ�ֻ�Ḳ�ǵ�ǰ�������
		GTK.gtk_list_store_set_value(liststore, listiter, 0, "002");
		GTK.gtk_list_store_set_value(liststore, listiter, 1, "Taiyanghua");
		GTK.gtk_list_store_set_value(liststore, listiter, 2, "29");
		GTK.gtk_list_store_append(liststore, listiter);
		GTK.gtk_list_store_set_value(liststore, listiter, 0 ,"003");
		GTK.gtk_list_store_set_value(liststore, listiter, 1 ,"Xiaojun");
		GTK.gtk_list_store_set_value(liststore, listiter, 2 ,"10");
		// ֮��������9������Ϊ  3���ֶ�*3����¼ == 9
		
		//7  ʹ����iter֮��һ��Ҫ�ǵ� �ص���
		GTK.gtk_tree_iter_free(listiter);
		
		//8 �ؼ���һ������������ʾ����
		GTK.gtk_tree_view_set_model(treeViewApple, liststore);
		
		//��ͬʱ������ʾ������ǰ̨������ʾ
		GTK.gtk_widget_show(treeViewApple);
		//������ӵ����ⷿ��
		//GTK.gtk_grid_attach(gridHouse, treeViewApple, 0, 1, 3, 3);
		
		
		start = 1;
		createScrolledBar(gridHouse,start,treeViewApple);
		start = 4;
		createPrintButton(gridHouse,start);
		createLabel(gridHouse, start, "ID");
		//createEntry(entryID, gridHouse, start);
		entryID = GTK.gtk_entry_new();
		GTK.gtk_entry_set_max_length(entryID, 30);
		GTK.gtk_widget_show(entryID);
		GTK.gtk_grid_attach(gridHouse, entryID, 5, start, 1, 1);
		start = 5;
		createShowButton(gridHouse,start,1,1);
		createLabel(gridHouse, start, "����");
		//createEntry(entryName, gridHouse, start);
		entryName = GTK.gtk_entry_new();
		GTK.gtk_entry_set_max_length(entryName, 30);
		GTK.gtk_widget_show(entryName);
		GTK.gtk_grid_attach(gridHouse, entryName, 5, start, 1, 1);
		start = 6 ; 
		createHideColumnButton(gridHouse,treeViewApple,start,1);
		createLabel(gridHouse, start, "����");
		//createEntry(entryAge, gridHouse, start);
		entryAge = GTK.gtk_entry_new();
		GTK.gtk_entry_set_max_length(entryAge, 30);
		GTK.gtk_widget_show(entryAge);
		GTK.gtk_grid_attach(gridHouse, entryAge, 5, start, 1, 1);
		start = 7 ; 
		createResizeColumnButton(gridHouse,treeViewApple,start,1);
		createInsertRecordButton(gridHouse,start);
		start = 8;
		createRecoredColumnButton(gridHouse,treeViewApple,start,1);
		start = 9;
		createSortColumnButton(gridHouse,treeViewApple,start,1);
		
		//������ѡ���¼�
		rowSelection();
		//3
		
		//���ӿؼ�
		
		//��ʾ�ؼ�
		//GTK.gtk_widget_show(liststore);
		
		//����ѭ��
		GTK.gtk_main();
	}
	
	/**
	 * 
	 * @param gridHouse     ������󲼾ֵı�ʶ
	 * @param start         ���񲼾ֵ���������
	 * @param treeview      treeview�Ķ����ʶ
	 */
	public static void createScrolledBar(int gridHouse,int start,int treeview)
	{
		int scrollbar = GTK.gtk_scrolled_window_new();
		GTK.gtk_container_add(scrollbar, treeview);
		GTK.gtk_widget_set_size_request(scrollbar, 200, 200);
		GTK.gtk_widget_show(scrollbar);
		GTK.gtk_grid_attach(gridHouse, scrollbar, 0, start, 3, 3);
	}
	/**
	 * 
	 * @param treeView       treeView�����ʶ
	 * @param nth_columns    �����ֶε��ڼ���
	 * @param column_name    �ֶε�����
	 * @note  һ����һ�δ�����¼
	 */
	public static void createColumn(int treeView, int nth_columns, String column_name)
	{
		int temp_column = GTK.gtk_tree_view_column_new_with_attributes(column_name, GTK.gtk_cell_renderer_text_new(), nth_columns);
		GTK.gtk_tree_view_append_column(treeView, temp_column);
	}
	/**
	 * 
	 * @param ID      ��¼��ID��
	 * @param Names   ��¼������
	 * @param Age     ��¼������
	 * @note      ��Ҫ��δ�����¼
	 */
	public static void createRecord(String ID, String Names, String Age,int listiter)
	{
		
		GTK.gtk_list_store_append(liststore, listiter); //ָ����һ�������ӵ�����
		//append�������� 1������ 2��iterָ������
		GTK.gtk_list_store_set_value(liststore, listiter, 0, ID);
		GTK.gtk_list_store_set_value(liststore, listiter, 1, Names);
		GTK.gtk_list_store_set_value(liststore, listiter, 2, Age);
		
	}
	
	public static void printRecord(int liststore)
	{
		//�����½�����������ԭ�ȵ�listiter,�ı���Ҫ�ÿ��ƣ�����Ҫ��һ���ؼ������������򱨴�����������ȫ�ֵ� listiter
		int iter = GTK.gtk_tree_iter_new();
/*		if(GTK.gtk_tree_model_get_iter_first(liststore, listiter))
		{
			do
			{
				String ID = GTK.gtk_tree_model_get_value(liststore, listiter, 0);
				String Names = GTK.gtk_tree_model_get_value(liststore, listiter, 1);
				String Ages = GTK.gtk_tree_model_get_value(liststore, listiter, 2);
				//showInfo("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages,"��ʾԱ����Ϣ");
				System.out.println("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages);
				
			}while(GTK.gtk_tree_model_iter_next(liststore,listiter));
		}else
		{
			System.out.println("����������");
		}*/
		// ���ٴ������ȡ�
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			System.out.println("treeviewû������  �����Ӽ�¼");
			return;
		}
		do
		{
			String ID = GTK.gtk_tree_model_get_value(liststore, iter, 0);
			String Names = GTK.gtk_tree_model_get_value(liststore, iter, 1);
			String Ages = GTK.gtk_tree_model_get_value(liststore, iter, 2);
			//showInfo("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages,"��ʾԱ����Ϣ");
			System.out.println("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages);
			
		}while(GTK.gtk_tree_model_iter_next(liststore,iter));
		
		GTK.gtk_tree_iter_free(iter);//�ͷ��� �����д��󡣡���
	}
	
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
	public static boolean showWarning(  String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_WARNING, GTK.GTK_BUTTONS_OK,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		GTK.gtk_widget_destroy(msgDlg);
		return ret == GTK.GTK_RESPONSE_OK;
	}
	public static void createPrintButton(int gridHouse,int start)
	{
		int btnApple = GTK.gtk_button_new_with_label("����");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertButtonEvent(btnApple);
	}
	public static void createShowButton(int gridHouse,int start,int row, int column)
	{
		int btnApple = GTK.gtk_button_new_with_label("��ȡ����"+row+"��"+"��"+column+"��");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertShowEvent(btnApple,row,column);
	}
	public static void createHideColumnButton(int gridHouse,int treeViewApple,int start,int nthcolumn)
	{
		int btnApple = GTK.gtk_button_new_with_label("����|��ʾ��"+nthcolumn+"��");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertHideEvent(treeViewApple, btnApple, nthcolumn);
	}
	
	public static void createResizeColumnButton(int gridHouse,int treeViewApple,int start,int nthcolumn)
	{
		int btnApple = GTK.gtk_button_new_with_label("�����"+nthcolumn+"�пɵ�����С��Ȩ��");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertResizeEvent(treeViewApple, btnApple, nthcolumn);
	}
	public static void createRecoredColumnButton(int gridHouse,int treeViewApple,int start,int nthcolumn)
	{
		int btnApple = GTK.gtk_button_new_with_label("�����"+nthcolumn+"�п��ƶ���Ȩ��");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertRecordableEvent(treeViewApple, btnApple, nthcolumn);
	}
	public static void createSortColumnButton(int gridHouse,int treeViewApple,int start,int nthcolumn)
	{
		int btnApple = GTK.gtk_button_new_with_label("�����"+nthcolumn+"�п������Ȩ��");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertSortEvent(treeViewApple, btnApple, nthcolumn);
	}
	public static void createInsertRecordButton(int gridHouse,int start)
	{
		int btnApple = GTK.gtk_button_new_with_label("��������");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 5, start, 1, 1);
		insertRecordEvent(btnApple);
	}
	public static void createLabel(int gridHouse, int start,String labelName)
	{
		int labelApple = GTK.gtk_label_new(labelName);
		GTK.gtk_widget_show(labelApple);
		GTK.gtk_grid_attach(gridHouse, labelApple, 4, start, 1, 1);
		
	}
	public static void createEntry(int entryApple,int gridHouse, int start)
	{
		entryApple = GTK.gtk_entry_new();
		GTK.gtk_entry_set_max_length(entryApple, 30);
		GTK.gtk_widget_show(entryApple);
		GTK.gtk_grid_attach(gridHouse, entryApple, 5, start, 1, 1);
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
				printRecord(liststore);
			}
		}, null);
	}
	public static void insertShowEvent(final int btnSin,final int row, final int column)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				showColumnRow(row,column);
			}
		}, null);
	}
	public static void insertHideEvent(final int treeViewApple,final int btnSin, final int nthcolumn)
	{
		//final boolean columnVisible = true;
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				tree_view_column_set_hide(treeViewApple,nthcolumn,columnVisible);
				columnVisible = !columnVisible;
			}
		}, null);
	}
	public static void insertResizeEvent(final int treeViewApple,final int btnSin, final int nthcolumn)
	{
		//final boolean columnVisible = true;
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				tree_view_column_resize(treeViewApple,nthcolumn,columnResize);
				columnResize = !columnResize;
			}
		}, null);
	}
	
	public static void insertRecordableEvent(final int treeViewApple,final int btnSin, final int nthcolumn)
	{
		//final boolean columnVisible = true;
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				tree_view_column_recordabel(treeViewApple,nthcolumn,columnRecordable);
				columnRecordable = !columnRecordable;
			}
		}, null);
	}
	public static void insertSortEvent(final int treeViewApple,final int btnSin, final int nthcolumn)
	{
		//final boolean columnVisible = true;
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				tree_view_column_sort(treeViewApple,nthcolumn,1);
				//columnRecordable = !columnRecordable;
			}
		}, null);
	}
	public static void insertRecordEvent(final int btnSin)
	{
		//final boolean columnVisible = true;
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				String ID = GTK.gtk_entry_get_text(entryID);
				System.out.println(ID);
				if(ID.equalsIgnoreCase(""))
				{
					showWarning("IDΪ�գ�����������","����");
					return;
				}
				String Name = GTK.gtk_entry_get_text(entryName);
				if(Name.equalsIgnoreCase(""))
				{
					showWarning("NameΪ�գ�����������","����");
					return;
				}
				String Age = GTK.gtk_entry_get_text(entryAge);
				if(Age.equalsIgnoreCase(""))
				{
					showWarning("AgeΪ�գ�����������","����");
					return;
				}
				insertRecord(ID,Name, Age, treeViewApple);
				 
				
			}
		}, null);
	}
	public static void insertRecord(String ID,String Name,String Age,int treeViewApple)
	{
		int iter = GTK.gtk_text_iter_new();
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			createRecord( ID,  Name,  Age,iter);
		}
		else
		{
			do
			{
				//System.out.println("hello");
			}while(GTK.gtk_tree_model_iter_next(liststore, iter));
			//while(GTK.gtk_tree_model_iter_next(liststore, iter)); 
			// ��һ�ε�bug��������������iter_nextд���� iter_first���²��ϵ�ѭ��
			//��������ѭ�������Ƕ�� system��ӡ�������ɡ�
			createRecord( ID,  Name,  Age,iter);
		}
		GTK.gtk_text_iter_free(iter);
		GTK.gtk_tree_view_set_model(treeViewApple, liststore);
	}
	public static void showColumnRow(int row, int column)
	{
		int i = 0;
		if(0 == getNumberOfListstore(liststore))
		{
			System.out.println("100�鶼���� ����飡");
			return ;
		}
		if(row > getNumberOfListstore(liststore))
		{
			System.out.println("��¼��û�У�  �����²�ѯ");
			return;
		}
		int iter  = GTK.gtk_tree_iter_new();
		GTK.gtk_tree_model_get_iter_first(liststore, iter);
		do
		{
			if(i == row)
			{
				String temp = GTK.gtk_tree_model_get_value(liststore, iter, column);
				System.out.println(temp);
				showInfo(temp,"��ʾ��Ϣ");
				return;
			}
			i++;
		}while(GTK.gtk_tree_model_iter_next(liststore, iter));
		
	}
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
	
	public static void tree_view_column_set_hide(int treeViewApple,int nthColumn,boolean visible)
	{
		int column =GTK.gtk_tree_view_get_column(treeViewApple, nthColumn);
		GTK.gtk_tree_view_column_set_visible(column, visible);
	}
	public static void tree_view_column_resize(int treeViewApple,int nthColumn,boolean visible)
	{
		int column =GTK.gtk_tree_view_get_column(treeViewApple, nthColumn);
		GTK.gtk_tree_view_column_set_resizable(column, visible);
	}
	public static void tree_view_column_recordabel(int treeViewApple,int nthColumn,boolean visible)
	{
		int column =GTK.gtk_tree_view_get_column(treeViewApple, nthColumn);
		GTK.gtk_tree_view_column_set_reorderable(column, visible);
	}
	public static void tree_view_column_sort(int treeViewApple,int nthColumn,int sortcolumnid)
	{
		int column =GTK.gtk_tree_view_get_column(treeViewApple, nthColumn);
		GTK.gtk_tree_view_column_set_sort_column_id(column, sortcolumnid);
	}
	
	public static void rowSelection()
	{
		int selection = GTK.gtk_tree_view_get_selection(treeViewApple);
		GTK.gtk_tree_selection_set_mode(selection, GTK.GTK_SELECTION_MULTIPLE);
		GTK.g_signal_connect(treeViewApple, "button-press-event", new IGCallBack()
		{ 
		 @Override
		 public void execute(int instance, int eventData, Object object)
		 {
		  if(GTK.gdk_event_get_type(eventData)==GTK.GDK_2BUTTON_PRESS)
		  {
		   int[] indices = GTK.gtk_tree_view_get_selection_indices(treeViewApple);
		   System.out.println(indices[0]);
		  }
		 }
		}, null);
	}
	
/*	public static void deleteOneRecore()
	{
		int selection = GTK.gtk_tree_view_get_selection(treeViewApple);
		
	}*/
}