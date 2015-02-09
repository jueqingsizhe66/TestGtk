/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��9������2:05:28
 * @version   GTKEncapsulateOOListStore V1.0  �����˿ɱ�����ļ���
 *                             V2.0 ������getValue�Ķ�̬��ʽ ����getValue(row,column)����Ӧ˫���¼���
 *                                  ���TestOOTreeView
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
		this.append(ootiTemp); //ָ������
		for(int i = 0 ;  i < apples.length; i++)
		{
			if(i < column) //�������̫���ֵ ����Ч
			{
				
				this.setValue(ootiTemp, i, apples[i]); //�������ݣ�����һ�����صķ���
			}else
			{
				break; //�����˳�
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
	 * @param ootiTemp  ������
	 * @return   ����ֵ��Ϊ�٣����ʾliststore��ģ��һ�����ݶ�û��
	 *                ��Ϊ�棬���ʾ������
	 */
	public boolean moveFirst(OOTreeIter ootiTemp)
	{
		//���һ�ж�û�� �򷵻ؼ�  �������Ϊ��
		return GTK.gtk_tree_model_get_iter_first(this.getId(),ootiTemp.getId());
	}
	/**
	 * 
	 * @param ootiTemp
	 * @return    ���Ϊ�� �����������µ�λ�ã������Ϊ�٣����õ�������ǰ����һ��
	 */
	public boolean moveNext(OOTreeIter ootiTemp)
	{
		return GTK.gtk_tree_model_iter_next(this.getId(), ootiTemp.getId());
	}
	/**
	 * 
	 * @param ootiTemp
	 * @return   ���Ϊ�� ����������ͷ��λ�ã������Ϊ�٣����õ�������������һ��
	 */
	public boolean moveBack(OOTreeIter ootiTemp)
	{
		return GTK.gtk_tree_model_iter_previous(this.getId(), ootiTemp.getId());
	}
	/**
	 * 
	 * @param nthrow      ��n��
	 * @param nthcolumn   ��n��
	 *         ����ĳ��ĳ�е�ֵ�������޸�
	 */
	public void setValue(int nthrow, int nthcolumn,String value)
	{
		OOTreeIter ootiTemp = new OOTreeIter();
		int i = 1;
		//int iter = GTK.gtk_text_iter_new();
		if(!this.moveFirst(ootiTemp))
		{
			OOMessageDialog.showError("100��Ǯ��û�� ��ȥ�ֵ�", "������");
			return;
		}
		if(nthrow > getNumberOfListstore())
		{
			System.out.println("��¼��û�У�  �����²�ѯ");
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
	 *  һ�����ڸ�ֵ����liststore��ʼ����ֵ
	 * @param ootiTemp    ��������������
	 * @param nthcolumn   �ڼ����ֶ�
	 * @param value       �����������
	 */
	public void setValue(OOTreeIter ootiTemp, int nthcolumn,String value)
	{
		GTK.gtk_list_store_set_value(this.getId(), ootiTemp.getId(), nthcolumn, value);
	}
	
	/**
	 *  ���ص�ǰ���������ڵ�ĳ���ֶε�ֵ
	 * @param ootiTemp      ������
	 * @param nthcolumn     �ڼ����ֶ�
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
			OOMessageDialog.showError("100��Ǯ��û�� ��ȥ�ֵ�", "������");
			return null;
		}
		if(row > getNumberOfListstore())
		{
			System.out.println("��¼��û�У�  �����²�ѯ");
			return null;
		}
		do
		{
			if(row == i)
			{
				apple = this.getValue(ootiTemp, nthcolumn);
				//System.out.println("���ڲ��Ի�����"+i+"ff"+row);
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
	 * @return  ����liststore������
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
	 * @param ootiTemp   �õ�������1 �ڵ�ǰ��iterλ�� ׷�����ݵ�liststore�У���������֮ǰ����appendһ��
	 */
	public void append(OOTreeIter ootiTemp)
	{
		GTK.gtk_list_store_append(this.getId(), ootiTemp.getId());
	}

}