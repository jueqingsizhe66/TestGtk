/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��8������9:20:23
 * @version   GTKEncapsulateOOMessageType V1.0
 */
public enum OOMessageType
{
	INFO(GTK.GTK_MESSAGE_INFO),
	ERROR(GTK.GTK_MESSAGE_ERROR),
	QUESTION(GTK.GTK_MESSAGE_QUESTION),
	WARNING(GTK.GTK_MESSAGE_WARNING);
	
	private int type;
	private OOMessageType(int type)
	{
	   this.type = type;	
	}
	
	public int getValue()
	{
		return this.type;
	}
	
	public OOMessageType parseOOMessageType(int type)
	{
		OOMessageType[] msgType = OOMessageType.values();
		for(int i =0; i < msgType.length; i++)
		{
			if(msgType[i].getValue() == type)
			{
				return msgType[i];
			}
		}
		throw new IllegalArgumentException("Messagetype = "+type+"�Ǹ����Ϸ�����");
	}
}