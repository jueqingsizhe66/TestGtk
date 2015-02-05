/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������1:15:05
 * @version   GTKEncapsulateOOImage V1.0
 */
public class OOImage extends OOWidget
{
	public OOImage()
	{
		setId(GTK.gtk_image_new());
	}
	/**
	 * 
	 * @param Filename   Ӳ���ļ���·��
	 *                   ��ʾӲ��ͼƬ�ļ�
	 */
	public void setFileImage(String Filename)
	{
		GTK.gtk_image_set_from_file(this.getId(), Filename);
	}
	
	/**
	 * 
	 * @param Filename  һ�����ʽΪ  ����GTKEncapsulate�µ�hunsha.jpg��Ϊ GTKEncapsulate/hunsha.jpg
	 *                  ��ʾ��Ŀ����Դ�ļ�	
	 */
	public void setResourceImage(String Filename)
	{
		GTK.gtk_image_set_from_resource(this.getId(), Filename);
	}
	
	/**
	 * 
	 * @param stock_id   String GTK_STOCK_QUIT     GTK_STOCK_PRINT_PAUSED
	 *                   String GTK_STOCK_EDIT
	 * @param size   int GTK_ICON_SIZE_MENU  int GTK_ICON_SIZE_SMALL_TOOLBAR 
	 *               int GTK_ICON_SIZE_BUTTON   int GTK_ICON_SIZE_DIALOG
	 */
	public void setStockImage(String stock_id,int size)
	{
		GTK.gtk_image_set_from_stock(this.getId(), stock_id, size);
	}
}