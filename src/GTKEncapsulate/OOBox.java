
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日上午1:38:51
 * @version   GTKEncapsulateOOBox V1.0
 */
public class OOBox extends OOWidget
{
	final static int HORIZONTAL = GTK.GTK_ORIENTATION_HORIZONTAL;
	final static int VERTICAL   = GTK.GTK_ORIENTATION_VERTICAL;
	public OOBox(int orientation,int spacing)
	{
		setId(GTK.gtk_box_new(orientation, spacing));
	}
	public OOBox(int orientation)
	{
		setId(GTK.gtk_box_new(orientation,0));
	}
	public OOBox()
	{
		setId(GTK.gtk_box_new(HORIZONTAL,0));
	}
	
	public void addWidget(int widget)
	{
		GTK.gtk_box_pack_start(this.getId(), widget, false, false, 0);
	}
}
