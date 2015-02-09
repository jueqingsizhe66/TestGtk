/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午1:50:33
 * @version   GTKEncapsulateOOContainer V1.0   更改public为protected
 *                               看视频看到，！！这个是老祖宗
 */
public class OOContainer extends OOWidget
{
	/**
	 *   防止用户调用无参的构造函数而出错？？？！！！
	 *    如果你去掉这个无参构造函数，那么如果子类没有定义构造函数
	 *    默认会添加
	 *    public 子类()
	 *    {
	 *      super() ; //调用无参
	 *    }
	 *    
	 *    2：如果你已经定义了有参构造函数，那么无参构造函数就会取消，也就是super()
	 *    根本就没有函数给你调用，所以肯定是报错。（比如OOBin并没有构造函数 只是重载了add方法）
	 */
	protected OOContainer()
	{
		
	}
	/**
	 * 
	 * @param id   设置一个Container的id值 ，用于OODialog的contentarea的封装
	 */
	protected OOContainer(int id)
	{
		setId(id);
	}
	
	/**
	 *  
	 * @param widget  OOWidget类 或者OOWidget的子类
	 *    添加一个OOwidget类或者子类的对象
	 */
	public void add(OOWidget widget)
	{
		GTK.gtk_container_add(this.getId(), widget.getId());
	}
	/**
	 * 
	 * @param widget  OOWidget类 或者OOWidget的子类
	 *    删除一个OOwidget类或者子类的对象
	 */
	public void remove(OOWidget widget)
	{
		GTK.gtk_container_remove(this.getId(), widget.getId());
	}
	/**
	 * 删除所有Owidget类或者子类的对象
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
