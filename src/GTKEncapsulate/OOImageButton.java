/**
 * 
 */
package GTKEncapsulate;

/**
 * @author    Ҷ����
 * @time      2015��2��9������3:51:16
 * @version   GTKEncapsulateOOImageButton V1.0  ͨ������ö���� OOStockImage OOStockSize ������Stock��Ϣ
 */
public class OOImageButton extends OOButton
{
	//����һ��image���� ��������ͼ��
	private OOImage oim ;
	public OOImageButton()
	{
		//Ĭ�ϵ��ø���Ĺ��캯��
		oim = new OOImage();
		oim.show();
		this.setImage(oim);
	}
	public void setImageFromFile(String filename)
	{
		oim.setFileImage(filename);
	}
	public void setImageFromResource(String filename)
	{
		oim.setResourceImage(filename);
	}
	public void setImageFromeStock(OOStockImage stock,OOStockSize size)
	{
		oim.setStockImage(stock, size);
	}
	
	
}