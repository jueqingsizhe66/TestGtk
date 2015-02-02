import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * 
 */

/**
 * @author    叶昭良
 * @time      2015年2月2日下午6:02:11
 * @version   TestGTKTreeView V1.0  一个基本的treeview的使用流程  + 字段创建+记录添加
 * @version   TestGTKTreeView V2.0  增加了遍历处理 以及显示某行某列的信息
 * @version   TestGTKTreeView V3.0  增加了”列控制“ ！！treeview 的某列可显示，可拉伸 ，可排序，可调整
 *                                  列控制只是改变treeview的表现，而不会改变liststore的model的内部值
 * @version   TestGTKTreeView V4.0  增加了添加记录的功能         
 * @version   TestGTKTreeView V5.0  增加了getSelection 进行行操作， 并未取出数据，也并未删除数据                 
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
	static boolean columnVisible = false; //用static 变量  不要用final变量。
	static boolean columnResize = true; //用static 变量  不要用final变量。
	static boolean columnRecordable = true; // 设置可拉动
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
		//创建并添加布局
		gridHouse = GTK.gtk_grid_new();
		GTK.gtk_container_add(window, gridHouse);
		GTK.gtk_widget_show(gridHouse);
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
		//最后添加到合租房中
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
		createLabel(gridHouse, start, "名字");
		//createEntry(entryName, gridHouse, start);
		entryName = GTK.gtk_entry_new();
		GTK.gtk_entry_set_max_length(entryName, 30);
		GTK.gtk_widget_show(entryName);
		GTK.gtk_grid_attach(gridHouse, entryName, 5, start, 1, 1);
		start = 6 ; 
		createHideColumnButton(gridHouse,treeViewApple,start,1);
		createLabel(gridHouse, start, "年龄");
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
		
		//加入行选择事件
		rowSelection();
		//3
		
		//添加控件
		
		//显示控件
		//GTK.gtk_widget_show(liststore);
		
		//启动循环
		GTK.gtk_main();
	}
	
	/**
	 * 
	 * @param gridHouse     网格对象布局的标识
	 * @param start         网格布局的所处行数
	 * @param treeview      treeview的对象标识
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
	 * @param treeView       treeView对象标识
	 * @param nth_columns    添加字段到第几列
	 * @param column_name    字段的名字
	 * @note  一般是一次创建记录
	 */
	public static void createColumn(int treeView, int nth_columns, String column_name)
	{
		int temp_column = GTK.gtk_tree_view_column_new_with_attributes(column_name, GTK.gtk_cell_renderer_text_new(), nth_columns);
		GTK.gtk_tree_view_append_column(treeView, temp_column);
	}
	/**
	 * 
	 * @param ID      记录的ID号
	 * @param Names   记录的名字
	 * @param Age     记录的年龄
	 * @note      需要多次创建记录
	 */
	public static void createRecord(String ID, String Names, String Age,int listiter)
	{
		
		GTK.gtk_list_store_append(liststore, listiter); //指针下一继续添加的作用
		//append两个作用 1：添加 2：iter指针下移
		GTK.gtk_list_store_set_value(liststore, listiter, 0, ID);
		GTK.gtk_list_store_set_value(liststore, listiter, 1, Names);
		GTK.gtk_list_store_set_value(liststore, listiter, 2, Age);
		
	}
	
	public static void printRecord(int liststore)
	{
		//必须新建，不能利用原先的listiter,哪边需要用控制，就需要建一个控件。！！！否则报错！！不能用全局的 listiter
		int iter = GTK.gtk_tree_iter_new();
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
			String ID = GTK.gtk_tree_model_get_value(liststore, iter, 0);
			String Names = GTK.gtk_tree_model_get_value(liststore, iter, 1);
			String Ages = GTK.gtk_tree_model_get_value(liststore, iter, 2);
			//showInfo("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages,"显示员工信息");
			System.out.println("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages);
			
		}while(GTK.gtk_tree_model_iter_next(liststore,iter));
		
		GTK.gtk_tree_iter_free(iter);//释放了 还是有错误。。。
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
		int btnApple = GTK.gtk_button_new_with_label("遍历");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertButtonEvent(btnApple);
	}
	public static void createShowButton(int gridHouse,int start,int row, int column)
	{
		int btnApple = GTK.gtk_button_new_with_label("获取表第"+row+"行"+"第"+column+"列");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertShowEvent(btnApple,row,column);
	}
	public static void createHideColumnButton(int gridHouse,int treeViewApple,int start,int nthcolumn)
	{
		int btnApple = GTK.gtk_button_new_with_label("隐藏|显示第"+nthcolumn+"列");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertHideEvent(treeViewApple, btnApple, nthcolumn);
	}
	
	public static void createResizeColumnButton(int gridHouse,int treeViewApple,int start,int nthcolumn)
	{
		int btnApple = GTK.gtk_button_new_with_label("赋予第"+nthcolumn+"列可调整大小的权限");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertResizeEvent(treeViewApple, btnApple, nthcolumn);
	}
	public static void createRecoredColumnButton(int gridHouse,int treeViewApple,int start,int nthcolumn)
	{
		int btnApple = GTK.gtk_button_new_with_label("赋予第"+nthcolumn+"列可移动的权限");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertRecordableEvent(treeViewApple, btnApple, nthcolumn);
	}
	public static void createSortColumnButton(int gridHouse,int treeViewApple,int start,int nthcolumn)
	{
		int btnApple = GTK.gtk_button_new_with_label("赋予第"+nthcolumn+"列可排序的权限");
		GTK.gtk_widget_show(btnApple);
		GTK.gtk_grid_attach(gridHouse, btnApple, 0, start, 1, 1);
		insertSortEvent(treeViewApple, btnApple, nthcolumn);
	}
	public static void createInsertRecordButton(int gridHouse,int start)
	{
		int btnApple = GTK.gtk_button_new_with_label("插入数据");
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
				// TODO 自动生成的方法存根
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
				// TODO 自动生成的方法存根
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
				// TODO 自动生成的方法存根
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
				// TODO 自动生成的方法存根
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
				// TODO 自动生成的方法存根
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
				// TODO 自动生成的方法存根
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
					showWarning("ID为空，请重新输入","警告");
					return;
				}
				String Name = GTK.gtk_entry_get_text(entryName);
				if(Name.equalsIgnoreCase(""))
				{
					showWarning("Name为空，请重新输入","警告");
					return;
				}
				String Age = GTK.gtk_entry_get_text(entryAge);
				if(Age.equalsIgnoreCase(""))
				{
					showWarning("Age为空，请重新输入","警告");
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
			// 记一次调bug经理，，经常把iter_next写成了 iter_first导致不断的循环
			//出现无限循环，最后嵌入 system打印出来即可。
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
			System.out.println("100块都不给 还想查！");
			return ;
		}
		if(row > getNumberOfListstore(liststore))
		{
			System.out.println("记录数没有！  请重新查询");
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
				showInfo(temp,"显示信息");
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
