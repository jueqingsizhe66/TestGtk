/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月4日下午11:05:00
 * @version   GTKEncapsulateOOButton V1.0  只是两个构造函数
 *                                   V2.0  增加了按钮的图片 和按钮图片的位置控制
 */
public class OOButton extends OOBin
{
	public OOButton()
	{
		setId(GTK.gtk_button_new());
	}
	
	/**
	 * 
	 * @param buttonName  第二种构造方法的  按钮显示的名字
	 */
	public OOButton(String buttonName)
	{
		setId(GTK.gtk_button_new_with_label(buttonName));
	}
	
	//不需要事件监听  已经在widget中有体现
	/**
	 * 
	 * @param oim   图片资源
	 *     使按钮加载图片
	 */
	public void setImage(OOImage oim)
	{
		GTK.gtk_button_set_image(this.getId(), oim.getId());
	}
	
	/**
	 * // GtkPositionType start
		public static final int GTK_POS_TOP = findConst("GTK_POS_TOP");
		public static final int GTK_POS_BOTTOM = findConst("GTK_POS_BOTTOM");
		public static final int GTK_POS_LEFT = findConst("GTK_POS_LEFT");
		public static final int GTK_POS_RIGHT = findConst("GTK_POS_RIGHT");
	 * @param position   GTK_POS_TOP   GTK_POS_BOTTOM    GTK_POS_LEFT  GTK_POS_RIGHT
	 */
	public void setImagePos(int position)
	{
		GTK.gtk_button_set_image_position(this.getId(), position);
	}
}
