/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��4������11:45:20
 * @version   GTKEncapsulateOOGrid V1.0
 */
public class OOGrid extends OOWidget
{
	public OOGrid()
	{
		setId(GTK.gtk_grid_new());
	}
	
	/**
	 * 
	 * @param widget  �ؼ���ʶ
	 * @param start   �ؼ��������е���ʼ��
	 */
	public void add(int widget,int start)
	{
		GTK.gtk_grid_attach(getId(), widget, 0, start, 1, 1);
	}
	/**
	 * 
	 * @param widget  �ؼ���ʶ
	 * @param start   �ؼ��������е���ʼ��
	 * @param column  �ؼ��������е�ĳ�е���ʼ��
	 */
	public void add(int widget,int start, int column)
	{
		GTK.gtk_grid_attach(getId(), widget, column, start, 1, 1);
	}
	/**
	 * 
	 * @param widget  �ؼ���ʶ
	 * @param start   �ؼ������񲼾ֵ��е���ʼ��
	 * @param column  �ؼ������񲼾ֵ��е�ĳ�е���ʼ��
	 * @param width   �ؼ������񲼾ֵ��еĿ���
	 * @param height  �ؼ������񲼾ֵ��еĳ���
	 */
	public void add(int widget,int start, int column,int width,int height)
	{
		GTK.gtk_grid_attach(getId(), widget, column, start, width, height);
	}
}