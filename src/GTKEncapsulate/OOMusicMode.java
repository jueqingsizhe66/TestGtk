/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午11:52:55
 * @version   GTKEncapsulateOOMusicMode V1.0
 */
public enum OOMusicMode
{
	OPEN(GTK.MCI_MODE_OPEN),
	PLAY(GTK.MCI_MODE_PLAY),
	PAUSE(GTK.MCI_MODE_PAUSE),
	STOP(GTK.MCI_MODE_STOP),
	RECORD(GTK.MCI_MODE_RECORD),
	SEEK(GTK.MCI_MODE_SEEK),
	UNREADY(GTK.MCI_MODE_NOT_READY);
	private int mode;
	private OOMusicMode(int mode)
	{
		this.mode = mode;
	}
	public int getMode()
	{
		return this.mode;
	}
	public OOMusicMode parseOOMusicMode(int value)
	{
		OOMusicMode[] omm = OOMusicMode.values();
		for(int i = 0; i< omm.length; i++)
		{
			if(value == omm[i].getMode())
			{
				return omm[i];
			}
		}
		throw new IllegalArgumentException(value+"是个不合法的参数啊！");
	}
}
