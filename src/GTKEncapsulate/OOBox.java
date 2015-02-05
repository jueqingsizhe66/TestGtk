
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日上午1:38:51
 * @version   GTKEncapsulateOOBox V1.0   
 *                               V2.0  构造函数的一大改进，使用了boolean变量定义构造函数
 *                                     并添加了标注
 */
public class OOBox extends OOContainer
{
	final static int HORIZONTAL = GTK.GTK_ORIENTATION_HORIZONTAL;
	final static int VERTICAL   = GTK.GTK_ORIENTATION_VERTICAL;
	/**
	 * 
	 * @param orientation  方向
	 * @param spacing      间隔
	 */
	public OOBox(int orientation,int spacing)
	{
		setId(GTK.gtk_box_new(orientation, spacing));
	}
	/**
	 * 
	 * @param orientation   方向的说明
	 */
	public OOBox(int orientation)
	{
		setId(GTK.gtk_box_new(orientation,0));
	}
	/**
	 *   无参构造函数的默认是水平布局
	 */
	public OOBox()
	{
		setId(GTK.gtk_box_new(HORIZONTAL,0));
	}
	/**
	 * 
	 * @param isHorizontal   是否是水平布局
	 * @param spacing        设置控件间的间隔
	 */
	public OOBox(boolean isHorizontal, int spacing)
	{
		int dir = isHorizontal?HORIZONTAL:VERTICAL;
		int id = GTK.gtk_box_new(dir, spacing);
		setId(id);	
	}
	/**
	 * 
	 * @param isHorizontal   是否是水平布局  
	 *                 也就是说可以通过 OOBox ob = new OOBox(true);的方式继承
	 */
	public OOBox(boolean isHorizontal)
	{
/*		int dir = isHorizontal?GTK.GTK_ORIENTATION_HORIZONTAL:GTK.GTK_ORIENTATION_VERTICAL;
		int id = GTK.gtk_box_new(dir, 0);
		setId(id);*/	
		//调用本类的构造函数
		this(isHorizontal,0);
	}
	/**
	 * 
	 * @param widget  待添加的控件标识符
	 */
	public void addWidget(int widget)
	{
		GTK.gtk_box_pack_start(this.getId(), widget, false, false, 0);
	}
}
