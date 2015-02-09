/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午11:57:32
 * @version   GTKEncapsulateOOScrollBar V1.0
 *                              V2.0  设置集成来自 OOContainer
 *                              V3.0  设置addView增加了OOGrid和OOBox两种方式
 */
public class OOScrollBar extends OOContainer
{
	//使用时 最好是设置大小，通过setSize 继承自OOWidget
	public OOScrollBar()
	{
		setId(GTK.gtk_scrolled_window_new());
	}
	
	/**
	 * 
	 * @param textView  让textView具有滚动条
	 */
	public void addView(OOWidget textView)
	{
		GTK.gtk_container_add(getId(), textView.getId());
	}
	
	
	public void addView(OOGrid grid, int start,OOContainer textView)
	{
		this.show();
		grid.add(this, start);
		this.addView(textView);//GTK.gtk_container_add(getId(), textView);
	}
	
	public void addView(OOBox ob,OOContainer textView)
	{
		this.show();
		ob.addWidget(textView);
		GTK.gtk_container_add(getId(), textView.getId());
	}
	
}
