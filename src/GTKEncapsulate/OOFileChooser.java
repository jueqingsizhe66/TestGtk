
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��5������4:38:57
 * @version   GTKEncapsulateOOFileChooser V1.0
 */
public abstract class OOFileChooser extends OOWidget
{
	private static Filter fi ;
	private static int response;  //�Ի������Ӧ
	/**
	 * 
	 * @param title     �вι��캯���Ĵ��ڱ���
	 * @param action    �вι��캯���� ��Ϊ���򿪡����档��
	 * @param buttonText  �вι��캯���� ��ť�ı�ǩ���� 
	 *  ����һ���ļ��Ի��򣬶Ի���һ��Ҫrunһ�¡��������� ����һ��Ҫ�ݻ���
	 */
	public OOFileChooser(String title, int action, String buttonText)
	{
		setId(GTK.gtk_file_chooser_dialog_new(title, 0, action, buttonText));
	}
	
	/**
	 *   �޲ι��캯��  Ĭ��Ϊ���ļ��Ի���˵
	 */
	public OOFileChooser()
	{
		setId(GTK.gtk_file_chooser_dialog_new("���ļ�", 0, GTK.GTK_FILE_CHOOSER_ACTION_OPEN,"��"));
		
	}
	/**
	 *   �����ļ��򿪴��ڵĶ�ѡ
	 */
	public void setMultipleSelect() 
	{
		GTK.gtk_file_chooser_set_select_multiple(this.getId(), true);
	}
	
	/**
	 *   ����һ��������
	 */
	public void createFilter()
	{
		fi = new Filter();
	}
	/**
	 * 
	 * @param text     ���ù�����������
	 */
	public void nameFilter(String text)
	{
		fi.setFilterName(text);
	}
	/**
	 * 
	 * @param pattern   ���ӹ������ĺ�׺
	 */
	public void editFilter(String pattern)
	{
		fi.addFilterPattern(pattern);
	}
	
	/**
	 *    ��һ��������Ϻ�  ����Ҫ����run�������������̵߳�����,����һ��Ҫ�ݻ��� this.destroy..
	 */
	public  void  run()
	{
		response =  GTK.gtk_dialog_run(this.getId());
	}
	
	
	public String[]  getFileNames()
	{
		return GTK.gtk_file_chooser_get_filenames(this.getId());
	}
	
	/**
	 *   һ�����󷽷� ��Ҫ��̳���ȥʵ����
	 */
	public abstract void processResponse();
	/**
	 * 
	 * @author    Ҷ����
	 * @time      2015��2��5������5:07:17
	 * @version   GTKEncapsulateFilter V1.0
	 */
	class Filter extends OOWidget
	{
		public Filter()
		{
			setId(GTK.gtk_file_filter_new());
		}
		/**
		 * 
		 * @param text   ���ù�����������
		 */
		public void setFilterName(String text)
		{
			GTK.gtk_file_filter_set_name(this.getId(), text);
		}
		/**
		 * 
		 * @param pattern  ���ù������Ĺ�������
		 */
		public void addFilterPattern(String pattern)
		{
			GTK.gtk_file_filter_add_pattern(this.getId(), pattern);
		}
	}
}