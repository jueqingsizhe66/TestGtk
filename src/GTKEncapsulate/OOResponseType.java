
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��8������4:07:49
 * @version   GTKEncapsulateOOResponseType V1.0  ��; �򻯶Ի��򷵻�ֵ���ж�
 *                                   V2.0 parseResponseType �滻��parseInt�������ӷ��ϳ���
 *                                        ��װ����ֵ��һ������ֵ��������˵���ݵ�ֵ�ǷǷ����Ƿ�ֵ�����ѣ�
 *                                        ���ӳ�����ȶ���
 */
public enum OOResponseType
{
	//�Ի����ֿ��ܵķ���ֵ����
	//GTK_RESPONSE_HELP    GTK_RESPONSE_DELETE_EVENT   GTK_RESPONSE_NONE
	OK(GTK.GTK_RESPONSE_OK),  //��÷��ڵ�һ��
	YES(GTK.GTK_RESPONSE_YES),
	NO(GTK.GTK_RESPONSE_NO),
	CANCEL(GTK.GTK_RESPONSE_CANCEL),
	ACCEPT(GTK.GTK_RESPONSE_ACCEPT), //�����Ϊ�ֺ��򱨴�
	APPLY(GTK.GTK_RESPONSE_APPLY),
	REJECT(GTK.GTK_RESPONSE_REJECT),
	NONE(GTK.GTK_RESPONSE_NONE),	
	HELP(GTK.GTK_RESPONSE_HELP),
	DELETE_EVENT(GTK.GTK_RESPONSE_DELETE_EVENT);
	
	
	private  int value = 0;
	/**
	 * 
	 * @param value  ���캯���Ĳ���
	 */
	private OOResponseType(int value)
	{
		this.value = value;
	}
	/**
	 * 
	 * @return  ����OOResponseType�����intֵ
	 */
	public int getValue()
	{
		return this.value;
	}
	/**
	 * 
	 * @param value   ������������ ��OOResponseö�����д�����ö��ֵ
	 * @return        ����һ��OOResponseType����
	 */
	public static OOResponseType parseResponseType(int value)
	{
		OOResponseType[] apple = OOResponseType.values();
		for(int i = 0 ; i < apple.length; i++)
		{
			if(value == apple[i].getValue())
			{
				return  apple[i];
			}
		}
		throw new IllegalArgumentException("ResponseValue = "+value+"�Ǹ����Ϸ��Ĳ���");
	}
}