
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��4������11:08:44
 * @version   GTKEncapsulateOOTextView V1.0
 */
public class OOTextView  extends OOWidget
{
	public OOTextView()
	{
		//new OOWidget().setId(GTK.gtk_text_view_new());  ����ֻ���Ƕ�����е��ã����˾�̬����
		//����̳���һ���� ���ſ���ֱ��ʹ�ø÷���
		setId(GTK.gtk_text_view_new());
	}
	
/*	// GtkWrapMode start
	public static final int GTK_WRAP_NONE = findConst("GTK_WRAP_NONE");
	public static final int GTK_WRAP_CHAR = findConst("GTK_WRAP_CHAR");
	public static final int GTK_WRAP_WORD = findConst("GTK_WRAP_WORD");
	public static final int GTK_WRAP_WORD_CHAR = findConst("GTK_WRAP_WORD_CHAR");*/
	/**
	 * 
	 * @param tvType  ����textview��ʵ�ı���ģʽ��һ�����������ͣ����GTK_WRAP_WORD
	 */
	public void setType(int tvType)
	{
		GTK.gtk_text_view_set_wrap_mode(getId(), tvType);
	}
	/**
	 * 
	 * @param isEditable  Textview�Ƿ���Ը�д
	 */
	public void setEditable(boolean isEditable)
	{
		GTK.gtk_text_view_set_editable(getId(), isEditable);
	}
	
	/**
	 *   ��ȡtextview��������Ϣ(�������� �������������)
	 */
	public String  getText()
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		return GTK.gtk_text_buffer_get_text(buffer);
	}
	/**
	 * 
	 * @param message   ������Ϣ ��textview����
	 */
	public void setText(String message)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		GTK.gtk_text_buffer_set_text(buffer, message);
	}
	/**
	 * 
	 * @param width   �������ĸ߶ȣ���������֣�
	 * @param height  �������ĸ߶ȣ���������֣�
	 */
	public void addScrollBar(int width, int height)
	{
		OOScrollBar osb = new OOScrollBar();
		osb.setWidgetSize(width, height);
		osb.addView(this.getId());
	}
	

	
	/**
	 * 
	 * @param message  ������Ϣ��textview����
	 */
	public void insertTextAtEnd(String message)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		OOTextIter ooiApple = new OOTextIter();
		int iter = ooiApple.getId();
		GTK.gtk_text_buffer_get_end_iter(buffer, iter);
		GTK.gtk_text_buffer_insert(buffer, iter, message);
		//ÿһ��ʹ����TextIter�����ͷ�
		ooiApple.free();
	}
	/**
	 * 
	 * @param message  �ڿ�ͷ��������Ϣ
	 */
	public void insertTextAtStart(String message)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		OOTextIter ooiApple = new OOTextIter();
		int iter = ooiApple.getId();
		GTK.gtk_text_buffer_get_start_iter(buffer, iter);
		GTK.gtk_text_buffer_insert(buffer, iter, message);
		//ÿһ��ʹ����TextIter�����ͷ�
		ooiApple.free();
	}
	
	/**
	 * 
	 * @param message  Ҫ�������Ϣ
	 * @param number   ��ͷ��ʼ��ĵڼ����ַ�
	 */
	public void insertTextAtOffsetChar(String message, int number)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		OOTextIter ooiApple = new OOTextIter();
		int iter = ooiApple.getId();
		GTK.gtk_text_buffer_get_iter_at_offset(buffer, iter, number);
		GTK.gtk_text_buffer_insert(buffer, iter, message);
		//ÿһ��ʹ����TextIter�����ͷ�
		ooiApple.free();
	}
	/**
	 * 
	 * @param message      ���������Ϣ
	 * @param lineNumber   buffer������(\n)
	 */
	public  void insertTextAtLine(String message,int lineNumber)
	{
		int buffer = GTK.gtk_text_view_get_buffer(this.getId());
		OOTextIter ooiApple = new OOTextIter();
		int iter = ooiApple.getId();
		GTK.gtk_text_buffer_get_iter_at_line(buffer, iter, lineNumber);
		GTK.gtk_text_buffer_insert(buffer, iter, message);
		//ÿһ��ʹ����TextIter�����ͷ�
		ooiApple.free();
	}
	
	
	


}