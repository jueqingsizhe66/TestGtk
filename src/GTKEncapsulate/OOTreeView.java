
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月5日上午12:32:21
 * @version   GTKEncapsulateOOTreeView V1.0
 *                       V2.0  利用新建的OOListStore  OOListIter  OOColumn重新改写
 *                       V3.0  改进了printRecored的方法  增加了OOSelection内部类
 *                       并 增加了setmultipelselect 。 增加了双击的时间监听，利用内部类实现接口（高级技术）
 *                             
 */
public class OOTreeView extends OOContainer
{
	private static OOListStore liststore;
	private static OOTreeIter listiter;
	/**
	 * 
	 */
	public OOTreeView()
	{
		//可以增加滚动条。。。。谁的getId
		//谁是核心！
		setId(GTK.gtk_tree_view_new());
	}
	
	public OOListStore getliststore()
	{
		return OOTreeView.liststore;
	}
	//MVC 设计模式  V
	/**
	 * 增加字段
	 * @param fieldName
	 * @param nthcolumn
	 */
	public void addField(String fieldName,int nthcolumn)
	{
		OOColumn ocColumn = new OOColumn(fieldName, nthcolumn);
		/*int column = GTK.gtk_tree_view_column_new_with_attributes(fieldName,
				GTK.gtk_cell_renderer_text_new(), nthcolumn);*/
		GTK.gtk_tree_view_append_column(this.getId(), ocColumn.getId());
	}
	
	//MVC 设计模式  M
	/**
	 * 
	 * @param nthcolumn
	 */
	public void createModel(int nthcolumn)
	{
		liststore = new OOListStore(nthcolumn);
		
		listiter = new OOTreeIter();
	}
	//假设有三个字段
	/**
	 * 只是在初始是会使用，因为使用的是默认的listiter.再赋值玩一次后，则被废弃
	 * @param Id
	 * @param Name
	 * @param Age
	 */
	public void setFieldValue(String Id,  String Name,String Age)
	{
/*		GTK.gtk_list_store_append(liststore, listiter); //指针开始指到第一个位置
		GTK.gtk_list_store_set_value(liststore, listiter, 0, Id);
		GTK.gtk_list_store_set_value(liststore, listiter, 1, Name);
		GTK.gtk_list_store_set_value(liststore, listiter, 2, Age);*/
		liststore.addValue(listiter,Id,Name,Age);
	}
	public void setFieldValue(OOTreeIter listiter,String Id,  String Name,String Age)
	{
/*		GTK.gtk_list_store_append(liststore, listiter); //指针开始指到第一个位置
		GTK.gtk_list_store_set_value(liststore, listiter, 0, Id);
		GTK.gtk_list_store_set_value(liststore, listiter, 1, Name);
		GTK.gtk_list_store_set_value(liststore, listiter, 2, Age);*/
		liststore.addValue(listiter,Id,Name,Age);
	}
	/**
	 *  只用于free掉treeview的第一次赋值的地带器
	 * @param ooti
	 * yang  写成了setModel
	 * 我觉得fillModel挺好的
	 */
	public void fillModel()
	{
		listiter.free();
		GTK.gtk_tree_view_set_model(this.getId(), liststore.getId());
	}
	
	/**
	 * 
	 * @param ooti
	 */
	public void fillModel(OOTreeIter ooti)
	{
		ooti.free();
		GTK.gtk_tree_view_set_model(this.getId(), liststore.getId());
	}
	
	//MVC 设计模式  C
	
	/**
	 *  设置多选
	 */
	public void setMultipleSelect()
	{
		OOSelection os = new OOSelection(this);
		os.setMultipleSelect(true);
	}
	
	public int[] getMultipleSelectRows()
	{
		OOSelection os = new OOSelection(this);
		return os.getMultipleIndex();
	}
	/**
	 * 
	 * @param callback  IGCallback的接口类 ，需要实现execute的接口，也可以自己定义一个接口类
	 */
	//当然callback也可以自定定义一个类似execute的接口类，比如定义为 ISpeable类的speak函数也是可以的
	public void addDoubleClickedListener(final IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "button-press-event", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				if(GTK.gdk_event_get_type(eventData)==GTK.GDK_2BUTTON_PRESS)
				{
					//调用时候 需要再次重写他。
					callback.execute(instance, eventData, object);
				}
			}
		}, null);
	}
	/**
	 * 
	 * @param width   滚动条的高度（超过则出现）
	 * @param height  滚动条的高度（超过则出现）
	 * 默认设置了 Grid网格布局的方式
	 */
	public void addScrollBar(OOGrid oogrid, int start, int width, int height)
	{
		OOScrollBar osb = new OOScrollBar();
		osb.show();
		osb.setWidgetSize(width, height);
		osb.addView(this);
		oogrid.add(osb, start);
	}
	
	/**
	 * 
	 * @param nthColumn   第几列字段
	 *                    隐藏第几列字段
	 */
	public void setHideColumn(int nthColumn)
	{
		//沟通treeview和column 只好放在treeView这一层级，不要放在OOColumn
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_visible(column, false);
	}
	/**
	 * 
	 * @param nthColumn   第几列字段
	 *                    显示第几列字段
	 */
	public void setVisibleColumn(int nthColumn)
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
	public  void printRecord(OOListStore liststore)
	{
		//必须新建，不能利用原先的listiter,哪边需要用控制，就需要建一个控件。！！！否则报错！！不能用全局的 listiter
		OOTreeIter ooti = new OOTreeIter();
		int column = liststore.getColumn();
		String[] apple = new String[column];
		// 减少代码的深度。
		if(!liststore.moveFirst(ooti))
		{
			System.out.println("treeview没有数据  请添加记录");
			return;
		}
		do
		{
			//获取全部的字段的值
			for(int i =0; i< column; i++)
			{
				apple[i] = liststore.getValue(ooti, i);
			}
			/*String ID = liststore.getValue(ooti, 0);
//GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 0);
			String Names = liststore.getValue(ooti, 1);
//GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 1);
			String Ages = liststore.getValue(ooti, 2);
//GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 2);
*/			//showInfo("这个员工的ID是"+ID+",他的名字是"+Names+",并且它的年龄是"+Ages,"显示员工信息");
			System.out.println("这个员工的ID是"+apple[0]+",他的名字是"+apple[1]+",并且它的年龄是"+apple[2]);
			
		}while(liststore.moveNext(ooti));
		
		ooti.free();
		//GTK.gtk_tree_iter_free(ooti.getId());//释放了 还是有错误。。。因为之前用的不是TreeIter而是TextIter
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
		OOTreeIter ooti = new OOTreeIter();
		int iter = ooti.getId();
		if(!liststore.moveFirst(ooti))
		{
			setFieldValue(ooti,ID, Name, Age);
		}
		else
		{
			do
			{
				//System.out.println("hello");
			}while(liststore.moveNext(ooti));
			//while(GTK.gtk_tree_model_iter_next(liststore, iter)); 
			// 记一次调bug经理，，经常把iter_next写成了 iter_first导致不断的循环
			//出现无限循环，最后嵌入 system打印出来即可。
			setFieldValue(ooti,ID, Name, Age);
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
		if(0 == liststore.getNumberOfListstore())
		{
			System.out.println("100块都不给 还想查！");
			return ;
		}
//		if(row > getNumberOfListstore(liststore))
		if(row > liststore.getNumberOfListstore())
		{
			System.out.println("记录数没有！  请重新查询");
			return;
		}
		OOTreeIter ooti  =  new OOTreeIter();
		liststore.moveFirst(ooti);
		do
		{
			if(i == row)
			{
				//另外一种多态。。。。的getValue
				String temp = liststore.getValue(ooti, column);
				System.out.println(temp);
				OOMessageDialog.showInfo(temp,"显示信息");
				return;
			}
			i++;
		}while(liststore.moveNext(ooti));
		ooti.free();
		
	}
	
	class OOColumn extends OOContainer
	{
		private String fieldName;
	//	private String fieldValue;
		public OOColumn(String fieldName,int nthcolumn)
		{
			this.fieldName = fieldName;
		//	this.fieldValue = fieldValue;
			setId(GTK.gtk_tree_view_column_new_with_attributes(fieldName, GTK.gtk_cell_renderer_text_new(), nthcolumn));
		}
	}
	class OOSelection extends OOWidget
	{
		private int selection;
		private OOTreeView ooti;
		public OOSelection(OOTreeView ooti)
		{
			this.ooti = ooti;
			this.selection = GTK.gtk_tree_view_get_selection(ooti.getId());
		}
		
		public int getSelection()
		{
			return this.selection;
		}
		/**
		 * 
		 * @param multiple  布尔值 true设置多选
		 */
		public void setMultipleSelect(boolean multiple)
		{
			GTK.gtk_tree_selection_set_mode(this.getSelection(), multiple?GTK.GTK_SELECTION_MULTIPLE:GTK.GTK_SELECTION_SINGLE);
		}
		
		public int  getMutltipleSelect()
		{
			return GTK.gtk_tree_selection_get_mode(this.getSelection());
		}
		
		public int[] getMultipleIndex()
		{
			int[] apple = GTK.gtk_tree_view_get_selection_indices(ooti.getId());
			return apple;
		}
		
		public String[] getSelectValues()
		{

			int[] apple = this.getMultipleIndex();
			String[] toilet = new String[apple.length];
			//int rowIndex = apple[0]  设置第0行。。
			for(int i = 0 ; i < apple.length; i++)
			{
				toilet[i] = liststore.getValue(apple[i], 2);
			}
			return toilet;
		}
	}

}


