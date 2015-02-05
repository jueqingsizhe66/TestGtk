
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��5������12:32:21
 * @version   GTKEncapsulateOOTreeView V1.0
 */
public class OOTreeView extends OOWidget
{
	private static int liststore;
	private static int listiter;
	private OOTextIter otiApple;
	/**
	 * 
	 */
	public OOTreeView()
	{
		setId(GTK.gtk_tree_view_new());
	}
	//MVC ���ģʽ  V
	/**
	 * 
	 * @param fieldName
	 * @param nthcolumn
	 */
	public void addField(String fieldName,int nthcolumn)
	{
		int column = GTK.gtk_tree_view_column_new_with_attributes(fieldName,
				GTK.gtk_cell_renderer_text_new(), nthcolumn);
		GTK.gtk_tree_view_append_column(this.getId(), column);
	}
	
	//MVC ���ģʽ  M
	/**
	 * 
	 * @param nthcolumn
	 */
	public void createModel(int nthcolumn)
	{
		liststore = GTK.gtk_list_store_new(nthcolumn);
		otiApple = new OOTextIter();
		listiter = otiApple.getId();
	}
	//�����������ֶ�
	/**
	 * 
	 * @param Id
	 * @param Name
	 * @param Age
	 */
	public void setFieldValue(String Id,  String Name,String Age)
	{
		GTK.gtk_list_store_append(liststore, listiter); //ָ�뿪ʼָ����һ��λ��
		GTK.gtk_list_store_set_value(liststore, listiter, 0, Id);
		GTK.gtk_list_store_set_value(liststore, listiter, 1, Name);
		GTK.gtk_list_store_set_value(liststore, listiter, 2, Age);
	}
	/**
	 * 
	 * @param ooti
	 */
	public void fillModel(OOTextIter ooti)
	{
		ooti.free();
		GTK.gtk_tree_view_set_model(this.getId(), liststore);
	}
	
	//MVC ���ģʽ  C
	
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
	 * @param nthColumn   �ڼ����ֶ�
	 *                    ���صڼ����ֶ�
	 */
	public void setHideColumn(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_visible(column, false);
	}
	/**
	 * 
	 * @param nthColumn   �ڼ����ֶ�
	 *                    ��ʾ�ڼ����ֶ�
	 */
	public void setSeeColumn(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_visible(column, true);
	}
	/**
	 * 
	 * @param nthColumn   �ڼ����ֶ�
	 *                    ���Ե����еĴ�С
	 */
	public void setResizeColumn(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_resizable(column, true);
	}
	/**
	 * 
	 * @param nthColumn   �ڼ����ֶ�
	 *                    
	 */
	public void setRecordColumn(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_reorderable(column, true);
	}
	/**
	 * 
	 * @param nthColumn   �ڼ����ֶ�
	 *                    �����ֶ�
	 */
	public void setColumnSort(int nthColumn)
	{
		int column = GTK.gtk_tree_view_get_column(this.getId(), nthColumn);
		GTK.gtk_tree_view_column_set_sort_column_id(column, 1);
	}
	
	/**
	 * 
	 * @param liststore  MVC��Model����
	 */
	public  void printRecord(int liststore)
	{
		//�����½�����������ԭ�ȵ�listiter,�ı���Ҫ�ÿ��ƣ�����Ҫ��һ���ؼ������������򱨴�����������ȫ�ֵ� listiter
		OOTextIter ooti = new OOTextIter();
		// ���ٴ������ȡ�
		if(!GTK.gtk_tree_model_get_iter_first(liststore, ooti.getId()))
		{
			System.out.println("treeviewû������  �����Ӽ�¼");
			return;
		}
		do
		{
			String ID = GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 0);
			String Names = GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 1);
			String Ages = GTK.gtk_tree_model_get_value(liststore, ooti.getId(), 2);
			//showInfo("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages,"��ʾԱ����Ϣ");
			System.out.println("���Ա����ID��"+ID+",����������"+Names+",��������������"+Ages);
			
		}while(GTK.gtk_tree_model_iter_next(liststore,ooti.getId()));
		
		GTK.gtk_tree_iter_free(ooti.getId());//�ͷ��� �����д��󡣡���
	}
	
	/**
	 * 
	 * @param liststore   MVCģ�͵�M��ֵ
	 * @return            ����ģ���еļ�¼��
	 */
	public  int getNumberOfListstore(int liststore)
	{
		int sum = 0;
		OOTextIter ootiApple = new OOTextIter();
		int iter = ootiApple.getId();
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			return sum;
		}
		do
		{
			sum++;
		}while(GTK.gtk_tree_model_iter_next(liststore, iter));
		ootiApple.free();
		return sum;
	}
	
	/**
	 *    ����treeview����ѡ��ģʽ
	 */
	public  void rowSelection()
	{
		final int treeViewApple = this.getId();
		int selection = GTK.gtk_tree_view_get_selection(treeViewApple);
		GTK.gtk_tree_selection_set_mode(selection, GTK.GTK_SELECTION_MULTIPLE);
		GTK.g_signal_connect(treeViewApple, "button-press-event", new IGCallBack()
		{ 
		 @Override
		 public void execute(int instance, int eventData, Object object)
		 {
		  if(GTK.gdk_event_get_type(eventData)==GTK.GDK_2BUTTON_PRESS)
		  {
		   int[] indices = GTK.gtk_tree_view_get_selection_indices(treeViewApple);
		   System.out.println(indices[0]);
		  }
		 }
		}, null);
	}
	/**
	 * 
	 * @param ID     �����¼��ID
	 * @param Name   �����¼������
	 * @param Age    �����¼������
	 *               ����һ����¼��liststore
	 */
	public  void insertRecord(String ID,String Name,String Age)
	{
		OOTextIter ooti = new OOTextIter();
		int iter = ooti.getId();
		if(!GTK.gtk_tree_model_get_iter_first(liststore, iter))
		{
			setFieldValue(ID, Name, Age);
		}
		else
		{
			do
			{
				//System.out.println("hello");
			}while(GTK.gtk_tree_model_iter_next(liststore, iter));
			//while(GTK.gtk_tree_model_iter_next(liststore, iter)); 
			// ��һ�ε�bug��������������iter_nextд���� iter_first���²��ϵ�ѭ��
			//��������ѭ�������Ƕ�� system��ӡ�������ɡ�
			setFieldValue(ID, Name, Age);
		}
		
		fillModel(ooti);
		
	}
	/**
	 * 
	 * @param row      ��ʾ��row�е�
	 * @param column   ��ʾ��column�е�
	 *              ��ʾ��row��  ��column�е�ֵ
	 */
	public  void showColumnRow(int row, int column)
	{
		int i = 0;
		if(0 == getNumberOfListstore(liststore))
		{
			System.out.println("100�鶼���� ����飡");
			return ;
		}
		if(row > getNumberOfListstore(liststore))
		{
			System.out.println("��¼��û�У�  �����²�ѯ");
			return;
		}
		OOTextIter ooti  =  new OOTextIter();
		int iter  = ooti.getId();
		GTK.gtk_tree_model_get_iter_first(liststore, iter);
		do
		{
			if(i == row)
			{
				String temp = GTK.gtk_tree_model_get_value(liststore, iter, column);
				System.out.println(temp);
				showInfo(temp,"��ʾ��Ϣ");
				return;
			}
			i++;
		}while(GTK.gtk_tree_model_iter_next(liststore, iter));
		ooti.free();
		
	}
	/**
	 * 
	 * @param message   ��Ϣ�����Ϣ
	 * @param title     ��Ϣ��ı���
	 *       ����һ����ʾ��Ϣ��
	 */
/*	public  void showInfo(  String  message,String title)
	{
		int msgDlg = GTK.gtk_message_dialog_new(0, GTK.GTK_DIALOG_DESTROY_WITH_PARENT|
				GTK.GTK_DIALOG_MODAL,GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK,message);
		GTK.gtk_window_set_title(msgDlg, title);
		int ret = GTK.gtk_dialog_run(msgDlg);
		 
		if( ret == GTK.GTK_RESPONSE_OK)
		{
			GTK.gtk_widget_destroy(msgDlg);
		}else
		{
			GTK.gtk_widget_destroy(msgDlg);
		}

	}*/
	
	

	
}

