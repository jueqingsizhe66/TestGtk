
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月5日上午12:32:21
 * @version   GTKEncapsulateOOTreeView V1.0
 */
public class OOTreeView extends OOContainer
{
	private static int liststore;
	private static int listiter;
	private OOTextIter otiApple;
	/**
	 * 
	 */
	public OOTreeView()
	{
		setId(GTK.gtk_tree_view_new());
	}
	//MVC 设计模式  V
	/**
	 * 
	 * @param fieldName
	 * @param nthcolumn
	 */
	public void addField(String fieldName,int nthcolumn)
	{
		int column = GTK.gtk_tree_view_column_new_with_attributes(fieldName,
				GTK.gtk_cell_renderer_text_new(), nthcolumn);
		GTK.gtk_tree_view_append_column(this.getId(), column);
	}
	
	//MVC 设计模式  M
	/**
	 * 
	 * @param nthcolumn
	 */
	public void createModel(int nthcolumn)
	{
		liststore = GTK.gtk_list_store_new(nthcolumn);
		otiApple = new OOTextIter();
		listiter = otiApple.getId();
	}
	//假设有三个字段
	/**
	 * 
	 * @param Id
	 * @param Name
	 * @param Age
	 */
	public void setFieldValue(String Id,  String Name,String Age)
	{
		GTK.gtk_list_store_append(liststore, listiter); //指针开始指到第一个位置
		GTK.gtk_list_store_set_value(liststore, listiter, 0, Id);
		GTK.gtk_list_store_set_value(liststore, listiter, 1, Name);
		GTK.gtk_list_store_set_value(liststore, listiter, 2, Age);
	}
	/**
	 * 
	 * @param ooti
	 */
	public void fillModel(OOTextIter ooti)
	{
		ooti.free();
		GTK.gtk_tree_view_set_model(this.getId(), liststore);
	}
	
	//MVC 设计模式  C
	
	/**
	 * 
	 * @param width   滚动条的高度（超过则出现）
	 * @param height  滚动条的高度（超过则出现）
	 */
	public void addScrollBar(int width, int height)
	{
		OOScrollBar osb = new OOScrollBar();
		osb.setWidgetSize(width, height);
		osb.addView(this.getId());
	}
	
	/**
	 * 
	 * @param nthColumn   第几列字段
	 *                    隐藏第几列字段
	 */
	public void setHideColumn(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_visible(column, false);
	}
	/**
	 * 
	 * @param nthColumn   第几列字段
	 *                    显示第几列字段
	 */
	public void setSeeColumn(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_visible(column, true);
	}
	/**
	 * 
	 * @param nthColumn   第几列字段
	 *                    可以调整列的大小
	 */
	public void setResizeColumn(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_resizable(column, true);
	}
	/**
	 * 
	 * @param nthColumn   第几列字段
	 *                    
	 */
	public void setRecordColumn(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_reorderable(column, true);
	}
	/**
	 * 
	 * @param nthColumn   第几列字段
	 *                    排序字段
	 */
	public void setColumnSort(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_sort_column_id(column, 1);
	}
	
	/**
	 * 
	 * @param liststore  MVC的Model数据
	 */
	public  void printRecord(int liststore)
	{
		//必须新建，不能利用原先的listiter,哪边需要用控制，就需要建一个控件。！！！否则报错！！不能用全局的 listiter
		OOTextIter ooti = new OOTextIter();
		// 减少代码的深度。
		if(!GTK.gtk_tree_model_get_iter_first(liststore, ooti.getId()))
		{
			System.out.println("treeview没有数据  请添加记录");
			return;
		}
		do
		{
			String ID = GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 0);
			String Names = GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 1);
			String Ages = GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 2);
			//showInfo("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages,"显示员工信息");
			System.out.println("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages);
			
		}while(GTK.gtk_tree_model_iter_next(liststore,ooti.getId()));
		
		GTK.gtk_tree_iter_free(ooti.getId());//释放了 还是有错误。。。
	}
	
	/**
	 * 
	 * @param liststore   MVC模型的M的值
	 * @return            返回模型中的记录数
	 */
	public  int getNumberOfListstore(int liststore)
	{
		int sum = 0;
		OOTextIter ootiApple = new OOTextIter();
		int iter = ootiApple.getId();
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			return sum;
		}
		do
		{
			sum++;
		}while(GTK.gtk_tree_model_iter_next(liststore, iter));
		ootiApple.free();
		return sum;
	}
	
	/**
	 *    设置treeview的行选择模式
	 */
	public  void rowSelection()
	{
		final int treeViewApple = this.getId();
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
	/**
	 * 
	 * @param ID     插入记录的ID
	 * @param Name   插入记录的名字
	 * @param Age    插入记录的年龄
	 *               插入一条记录到liststore
	 */
	public  void insertRecord(String ID,String Name,String Age)
	{
		OOTextIter ooti = new OOTextIter();
		int iter = ooti.getId();
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			setFieldValue(ID, Name, Age);
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
			setFieldValue(ID, Name, Age);
		}
		
		fillModel(ooti);
		
	}
	/**
	 * 
	 * @param row      显示第row行的
	 * @param column   显示第column列的
	 *              显示第row行  第column列的值
	 */
	public  void showColumnRow(int row, int column)
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
		OOTextIter ooti  =  new OOTextIter();
		int iter  = ooti.getId();
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
		ooti.free();
		
	}
	/**
	 * 
	 * @param message   消息框的消息
	 * @param title     消息框的标题
	 *       弹出一个提示消息框
	 */
/*	public  void showInfo(  String  message,String title)
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

	}*/
	
	

	
}


