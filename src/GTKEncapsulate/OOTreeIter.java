/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��9������2:09:04
 * @version   GTKEncapsulateOOTreeIter V1.0
 */
public class OOTreeIter extends OOWidget
{
	public OOTreeIter()
	{
		setId(GTK.gtk_tree_iter_new());
	}
	
	public void free()
	{
		GTK.gtk_tree_iter_free(this.getId());
	}
}
