/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������1:50:33
 * @version   GTKEncapsulateOOContainer V1.0   ����publicΪprotected
 *                               ����Ƶ���������������������
 */
public class OOContainer extends OOWidget
{
	/**
	 *   ��ֹ�û������޲εĹ��캯��������������������
	 */
	protected OOContainer()
	{
		
	}
	/**
	 * 
	 * @param id   ����һ��Container��idֵ
	 */
	protected OOContainer(int id)
	{
		setId(id);
	}
	
	/**
	 *  
	 * @param widget  OOWidget�� ����OOWidget������
	 *    ����һ��OOwidget���������Ķ���
	 */
	public void add(OOWidget widget)
	{
		GTK.gtk_container_add(this.getId(), widget.getId());
	}
	/**
	 * 
	 * @param widget  OOWidget�� ����OOWidget������
	 *    ɾ��һ��OOwidget���������Ķ���
	 */
	public void remove(OOWidget widget)
	{
		GTK.gtk_container_remove(this.getId(), widget.getId());
	}
	/**
	 * ɾ������Owidget���������Ķ���
	 */
	public void removeAll()
	{
		int[] children = GTK.gtk_container_get_children(this.getId());
		for(int i = 0 ; i < children.length; i++)
		{
			GTK.gtk_container_remove(this.getId(), children[i]);
		}
	}
}