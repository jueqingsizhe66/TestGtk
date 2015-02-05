/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月6日上午3:48:17
 * @version   GTKEncapsulateOOWindow V1.0
 */
public class OOWindow extends OOBin
//这时候的window.add()只能一次！！！这就满足了window的实际需要
{
	private boolean exitAfterDestroy  = false;
	public OOWindow()
	{
		setId(GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL));
		GTK.g_signal_connect(this.getId(), "destroy", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				if(exitAfterDestroy)
				{			
					GTK.gtk_main_quit();
				}

			}
		}, null);
	}
	
	public void setTitle(String title)
	{
		GTK.gtk_window_set_title(this.getId(),title);
	}
	
	public String getTitle()
	{
		return GTK.gtk_window_get_title(this.getId());
	}
	public void setResize(boolean resizable)
	{
		GTK.gtk_window_set_resizable(this.getId(), resizable);
	}
	
	public void setMax()
	{
		GTK.gtk_window_maximize(this.getId());
	}
	
	public void setCenter()
	{
		GTK.gtk_window_set_position(this.getId(), GTK.GTK_WIN_POS_CENTER_ALWAYS);
	}
	
	public void fullScreen()
	{
		GTK.gtk_window_fullscreen(this.getId());
	}
	
	//也就是当收到 destroy信号的时候 不一定是关闭的（一般我们写的时候是关闭的）
	public void setExitAfterDestroy(boolean value)
	{
		this.exitAfterDestroy  = true;
	}
}
