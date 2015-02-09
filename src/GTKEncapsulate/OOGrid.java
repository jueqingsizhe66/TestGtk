/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午11:45:20
 * @version   GTKEncapsulateOOGrid V1.0  
 *                           V2.0  增加了多态的add方法，使得直接添加控件和对象编号都是可以的
 *                                 
 */
public class OOGrid extends OOContainer
{
	public OOGrid()
	{
		setId(GTK.gtk_grid_new());
	}
	
	/**
	 * 
	 * @param widget  控件标识
	 * @param start   控件在网格当中的起始行
	 */
/*	public void add(int widget,int start)
	{
		GTK.gtk_grid_attach(getId(), widget, 0, start, 1, 1);
	}*/
	public void add(OOWidget widget,int start)
	{
		GTK.gtk_grid_attach(getId(), widget.getId(), 0, start, 1, 1);
	}
	public void add(int widget,int start)
	{
		GTK.gtk_grid_attach(getId(), widget, 0, start, 1, 1);
	}
	/**
	 * 
	 * @param widget  控件标识
	 * @param start   控件在网格当中的起始行
	 * @param column  控件在网格当中的某行的起始列
	 */
	public void add(OOWidget widget,int start, int column)
	{
		GTK.gtk_grid_attach(getId(), widget.getId(), column, start, 1, 1);
	}
	public void add(int widget,int start, int column)
	{
		GTK.gtk_grid_attach(getId(), widget, column, start, 1, 1);
	}
	/**
	 * 
	 * @param widget  控件标识
	 * @param start   控件在网格布局当中的起始行
	 * @param column  控件在网格布局当中的某行的起始列
	 * @param width   控件在网格布局当中的宽度
	 * @param height  控件在网格布局当中的长度
	 */
	public void add(OOWidget widget,int start, int column,int width,int height)
	{
		GTK.gtk_grid_attach(getId(), widget.getId(), column, start, width, height);
	}
	public void add(int widget,int start, int column,int width,int height)
	{
		GTK.gtk_grid_attach(getId(), widget, column, start, width, height);
	}
}
