/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月8日下午9:28:27
 * @version   GTKEncapsulateOOButtonsType V1.0
 */
public enum OOButtonsType
{
	YESNO(GTK.GTK_BUTTONS_YES_NO),
	OK(GTK.GTK_BUTTONS_OK),
	CANCEL(GTK.GTK_BUTTONS_CANCEL),
	OKCANCEL(GTK.GTK_BUTTONS_OK_CANCEL),
	CLOSE(GTK.GTK_BUTTONS_CLOSE),
	NONE(GTK.GTK_BUTTONS_NONE);
	
	private int type;
	private OOButtonsType(int type)
	{
		this.type = type;
	}
	
	public int getValue()
	{
		return this.type;
	}
	
	public OOButtonsType parseOOButtonsType(int type)
	{
		OOButtonsType[] obt = OOButtonsType.values();
		for(int i = 0 ; i < obt.length; i++)
		{
			if(obt[i].getValue() == type)
			{
				return obt[i];
			}
		}
		throw new IllegalArgumentException("ButtonsType = "+type+"是个不合法的参数");
	}
}
