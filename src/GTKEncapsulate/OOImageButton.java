/**
 * 
 */
package GTKEncapsulate;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午3:51:16
 * @version   GTKEncapsulateOOImageButton V1.0  通过两个枚举类 OOStockImage OOStockSize 设置了Stock信息
 */
public class OOImageButton extends OOButton
{
	//定义一个image对象 用于添加图像
	private OOImage oim ;
	public OOImageButton()
	{
		//默认调用父类的构造函数
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
