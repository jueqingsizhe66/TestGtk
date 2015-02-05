/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午3:33:43
 * @version   GTKEncapsulateOOBin V1.0
 */
public class OOBin extends OOContainer
{
	@Override
	public void add(OOWidget ow)
	{
		int[] children = GTK.gtk_container_get_children(this.getId());
		if(children.length <= 0 )
		{
			//调用父类的add方法
			super.add(ow);
			//super.add  调用父类
			//this.add   调用本类
			//super() 调用父类构造方法(在构造函数中)
			//this()  调用本类的构造方法(在构造函数中，记得我为什写上括号)
		}else
		{
			throw new IllegalArgumentException("容器中已经有了一个对象");
		}
	}
}
