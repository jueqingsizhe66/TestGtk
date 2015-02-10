/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月10日上午12:00:47
 * @version   GTKEncapsulateOOMusicStatus V1.0
 */
public enum OOMusicStatus
{
	POSITION(GTK.MCI_STATUS_POSITION),
	ITEM(GTK.MCI_STATUS_ITEM),
	START(GTK.MCI_STATUS_START),
	READY(GTK.MCI_STATUS_READY),
	MODE(GTK.MCI_STATUS_MODE),
	LENGTH(GTK.MCI_STATUS_LENGTH),
	CURRENTTRACK(GTK.MCI_STATUS_CURRENT_TRACK),
	MEDIA_PRESENT(GTK.MCI_STATUS_MEDIA_PRESENT),
	NUMBER_OF_TRACK(GTK.MCI_STATUS_NUMBER_OF_TRACKS),
	TIME_FORMAT(GTK.MCI_STATUS_TIME_FORMAT);
	
	
	private int status;
	private OOMusicStatus(int status)
	{
		this.status = status;
	}
	public int getStatus()
	{
		return this.status;
	}
	public OOMusicStatus parseOOMusicStatus(int value)
	{
		OOMusicStatus[] oms  = OOMusicStatus.values();
		for(int i = 0 ; i < oms.length; i++)
		{
			if(oms[i].getStatus() == value)
			{
				return oms[i];
			}
		}
		throw new IllegalArgumentException(value+"  is  a invalid argument");
	}
}
