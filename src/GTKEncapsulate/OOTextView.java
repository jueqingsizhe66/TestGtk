
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午11:08:44
 * @version   GTKEncapsulateOOTextView V1.0
 */
public class OOTextView  extends OOContainer
{
	public OOTextView()
	{
		//new OOWidget().setId(GTK.gtk_text_view_new());  方法只能是对象进行调用，除了静态方法
		//当你继承了一个类 ，才可以直接使用该方法
		setId(GTK.gtk_text_view_new());
	}
	
/*	// GtkWrapMode start
	public static final int GTK_WRAP_NONE = findConst("GTK_WRAP_NONE");
	public static final int GTK_WRAP_CHAR = findConst("GTK_WRAP_CHAR");
	public static final int GTK_WRAP_WORD = findConst("GTK_WRAP_WORD");
	public static final int GTK_WRAP_WORD_CHAR = findConst("GTK_WRAP_WORD_CHAR");*/
	/**
	 * 
	 * @param tvType  设置textview现实文本的模式，一般有四种类型，最常用GTK_WRAP_WORD
	 */
	public void setType(int tvType)
	{
		GTK.gtk_text_view_set_wrap_mode(this.getId(), tvType);
	}
	public void setType()
	{
		GTK.gtk_text_view_set_wrap_mode(this.getId(), GTK.GTK_WRAP_WORD_CHAR);
	}
	/**
	 * 
	 * @param isEditable  Textview是否可以改写
	 */
	public void setEditable(boolean isEditable)
	{
		GTK.gtk_text_view_set_editable(getId(), isEditable);
	}
	
	/**
	 *   获取textview的所有信息(不能逐行 是我有所不解的)
	 */
	public String  getText()
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		return GTK.gtk_text_buffer_get_text(buffer);
	}
	/**
	 * 
	 * @param message   设置消息 到textview当中
	 */
	public void setText(String message)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		GTK.gtk_text_buffer_set_text(buffer, message);
	}
	/**
	 * 
	 * @param width   滚动条的高度（超过则出现）
	 * @param height  滚动条的高度（超过则出现）
	 */
	public void addScrollBar(OOGrid og,int start,int width, int height)
	{
		OOScrollBar osb = new OOScrollBar();
		osb.setWidgetSize(width, height);
		osb.addView(this);
		osb.show();
		og.add(osb, start);
		
	}
	

	
	/**
	 * 
	 * @param message  插入信息到textview当中
	 */
	public void insertTextAtEnd(String message)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		OOTextIter ooiApple = new OOTextIter();
		int iter = ooiApple.getId();
		GTK.gtk_text_buffer_get_end_iter(buffer, iter);
		GTK.gtk_text_buffer_insert(buffer, iter, message);
		//每一次使用完TextIter都得释放
		ooiApple.free();
	}
	/**
	 * 
	 * @param message  在开头处插入信息
	 */
	public void insertTextAtStart(String message)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		OOTextIter ooiApple = new OOTextIter();
		int iter = ooiApple.getId();
		GTK.gtk_text_buffer_get_start_iter(buffer, iter);
		GTK.gtk_text_buffer_insert(buffer, iter, message);
		//每一次使用完TextIter都得释放
		ooiApple.free();
	}
	
	/**
	 * 
	 * @param message  要插入的信息
	 * @param number   从头开始算的第几个字符
	 */
	public void insertTextAtOffsetChar(String message, int number)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		OOTextIter ooiApple = new OOTextIter();
		int iter = ooiApple.getId();
		GTK.gtk_text_buffer_get_iter_at_offset(buffer, iter, number);
		GTK.gtk_text_buffer_insert(buffer, iter, message);
		//每一次使用完TextIter都得释放
		ooiApple.free();
	}
	/**
	 * 
	 * @param message      待插入的消息
	 * @param lineNumber   buffer中行数(\n)
	 */
	public  void insertTextAtLine(String message,int lineNumber)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		OOTextIter ooiApple = new OOTextIter();
		int iter = ooiApple.getId();
		GTK.gtk_text_buffer_get_iter_at_line(buffer, iter, lineNumber);
		GTK.gtk_text_buffer_insert(buffer, iter, message);
		//每一次使用完TextIter都得释放
		ooiApple.free();
	}
	
	
	


}
