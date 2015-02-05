/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午11:51:41
 * @version   GTKEncapsulateOOTextIter V1.0
 */
public class OOTextIter extends OOWidget
{
	/**
	 *   TextIter的构造函数
	 */
	public OOTextIter()
	{
		setId(GTK.gtk_text_iter_new());
	}
	
	/**
	 *    向头部移动一个字符
	 */
	public void back()
	{
		GTK.gtk_text_iter_backward_char(getId());
	}
	/**
	 *     从当前的迭代器向尾部移动一个字符
	 */
	public void forward()
	{
		GTK.gtk_text_iter_forward_char(getId());
	}
	/**
	 *     从当前的迭代器移动到Text的最后面
	 */
	public void forwardToEnd()
	{
		GTK.gtk_text_iter_forward_to_end(getId());
	}
	
	/**
	 *  每次使用玩text_iter都必须释放。（一次定义 一次释放，只能使用一次）
	 */
	public  void free()
	{
		GTK.gtk_text_iter_free(getId());
	}
}
