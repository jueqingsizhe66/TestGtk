/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��4������11:51:41
 * @version   GTKEncapsulateOOTextIter V1.0
 */
public class OOTextIter extends OOWidget
{
	/**
	 *   TextIter�Ĺ��캯��
	 */
	public OOTextIter()
	{
		setId(GTK.gtk_text_iter_new());
	}
	
	/**
	 *    ��ͷ���ƶ�һ���ַ�
	 */
	public void back()
	{
		GTK.gtk_text_iter_backward_char(getId());
	}
	/**
	 *     �ӵ�ǰ�ĵ�������β���ƶ�һ���ַ�
	 */
	public void forward()
	{
		GTK.gtk_text_iter_forward_char(getId());
	}
	/**
	 *     �ӵ�ǰ�ĵ������ƶ���Text�������
	 */
	public void forwardToEnd()
	{
		GTK.gtk_text_iter_forward_to_end(getId());
	}
	
	/**
	 *  ÿ��ʹ����text_iter�������ͷš���һ�ζ��� һ���ͷţ�ֻ��ʹ��һ�Σ�
	 */
	public  void free()
	{
		GTK.gtk_text_iter_free(getId());
	}
}