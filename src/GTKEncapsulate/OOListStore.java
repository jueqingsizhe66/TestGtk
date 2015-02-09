/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月9日上午2:05:28
 * @version   GTKEncapsulateOOListStore V1.0  利用了可变参数的技术
 *                             V2.0 增加了getValue的多态形式 利用getValue(row,column)，响应双击事件。
 *                                  详见TestOOTreeView
 */
public class OOListStore extends OOContainer
{
	private int column;
	//private OOTreeIter otIter = null;
	public OOListStore(int column)
	{
		this.column = column;
		setId(GTK.gtk_list_store_new(column));
	}
	public int getColumn()
	{
		return this.column;
	}
	public void addValue(OOTreeIter ootiTemp,String... apples)
	{
		//OOTreeIter ootiTemp = new OOTreeIter();
		this.append(ootiTemp); //指针下移
		for(int i = 0 ;  i < apples.length; i++)
		{
			if(i < column) //如果赋了太多的值 则无效
			{
				
				this.setValue(ootiTemp, i, apples[i]); //添加数据，这是一个重载的方法
			}else
			{
				break; //否则退出
			}
		}
//		ootiTemp.free();
	}
	
	public void clear()
	{
		GTK.gtk_list_store_clear(this.getId());
	}
	
	/**
	 * 
	 * @param ootiTemp  迭代器
	 * @return   返回值若为假，则表示liststore的模型一行数据都没有
	 *                若为真，则表示有数据
	 */
	public boolean moveFirst(OOTreeIter ootiTemp)
	{
		//如果一行都没有 则返回假  如果有则为真
		return GTK.gtk_tree_model_get_iter_first(this.getId(),ootiTemp.getId());
	}
	/**
	 * 
	 * @param ootiTemp
	 * @return    如果为假 表明到了最新的位置，如果不为假，则让迭代器往前走了一行
	 */
	public boolean moveNext(OOTreeIter ootiTemp)
	{
		return GTK.gtk_tree_model_iter_next(this.getId(), ootiTemp.getId());
	}
	/**
	 * 
	 * @param ootiTemp
	 * @return   如果为假 表明到了最头的位置，如果不为假，则让迭代器往后退了一行
	 */
	public boolean moveBack(OOTreeIter ootiTemp)
	{
		return GTK.gtk_tree_model_iter_previous(this.getId(), ootiTemp.getId());
	}
	/**
	 * 
	 * @param nthrow      第n行
	 * @param nthcolumn   第n列
	 *         设置某行某列的值，用于修改
	 */
	public void setValue(int nthrow, int nthcolumn,String value)
	{
		OOTreeIter ootiTemp = new OOTreeIter();
		int i = 1;
		//int iter = GTK.gtk_text_iter_new();
		if(!this.moveFirst(ootiTemp))
		{
			OOMessageDialog.showError("100块钱都没有 回去种地", "滚开地");
			return;
		}
		if(nthrow > getNumberOfListstore())
		{
			System.out.println("记录数没有！  请重新查询");
			return;
		}
		do
		{
			if(nthrow == i)
			{
				GTK.gtk_list_store_set_value(this.getId(), ootiTemp.getId(), nthcolumn, value);
				return;
			}
			i++;
		}while(this.moveNext(ootiTemp));
	}
	
	/**
	 *  一般用于赋值，网liststore初始化数值
	 * @param ootiTemp    迭代器所在行数
	 * @param nthcolumn   第几个字段
	 * @param value       待加入的数字
	 */
	public void setValue(OOTreeIter ootiTemp, int nthcolumn,String value)
	{
		GTK.gtk_list_store_set_value(this.getId(), ootiTemp.getId(), nthcolumn, value);
	}
	
	/**
	 *  返回当前迭代器所在的某个字段的值
	 * @param ootiTemp      迭代器
	 * @param nthcolumn     第几个字段
	 * @return
	 */
	public String getValue(int row,int nthcolumn)
	{
		//return GTK.gtk_tree_model_get_value(this.getId(), ootiTemp.getId(), nthcolumn);
		String apple = null;
		OOTreeIter ootiTemp = new OOTreeIter();
		int i = 0;
		//int iter = GTK.gtk_text_iter_new();
		if(!this.moveFirst(ootiTemp))
		{
			OOMessageDialog.showError("100块钱都没有 回去种地", "滚开地");
			return null;
		}
		if(row > getNumberOfListstore())
		{
			System.out.println("记录数没有！  请重新查询");
			return null;
		}
		do
		{
			if(row == i)
			{
				apple = this.getValue(ootiTemp, nthcolumn);
				//System.out.println("我在测试环境中"+i+"ff"+row);
				 //apple= GTK.gtk_tree_model_get_value(this.getId(), ootiTemp.getId(), nthcolumn);
				//GTK.gtk_list_store_set_value(this.getId(), ootiTemp.getId(), nthcolumn, value);
				return apple;
			}
			i++;
		}while(this.moveNext(ootiTemp));
		return null;
	}
	
	public String getValue(OOTreeIter ootiTemp,int nthcolumn)
	{
		return GTK.gtk_tree_model_get_value(this.getId(), ootiTemp.getId(), nthcolumn);
	}
	/**
	 * 
	 * @return  返回liststore的行数
	 */
	public  int getNumberOfListstore()
	{
		int sum = 0;
		OOTreeIter ootiTemp = new OOTreeIter();
		if(!this.moveFirst(ootiTemp))
		{
			return sum;
		}
		do
		{
			sum++;
		}while(this.moveNext(ootiTemp));
		ootiTemp.free();
		return sum;
	}
	/**
	 * 
	 * @param ootiTemp   让迭代器加1 在当前的iter位置 追加数据到liststore中，添加数据之前都得append一下
	 */
	public void append(OOTreeIter ootiTemp)
	{
		GTK.gtk_list_store_append(this.getId(), ootiTemp.getId());
	}

}
