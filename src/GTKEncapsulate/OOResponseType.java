
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月8日下午4:07:49
 * @version   GTKEncapsulateOOResponseType V1.0  用途 简化对话框返回值的判断
 *                                   V2.0 parseResponseType 替换掉parseInt这样更加符合常规
 *                                        封装返回值的一个返回值，不至于说传递的值是非法（非法值有提醒）
 *                                        增加程序的稳定性
 */
public enum OOResponseType
{
	//对话框几种可能的返回值类型
	//GTK_RESPONSE_HELP    GTK_RESPONSE_DELETE_EVENT   GTK_RESPONSE_NONE
	OK(GTK.GTK_RESPONSE_OK),  //最常用放在第一个
	YES(GTK.GTK_RESPONSE_YES),
	NO(GTK.GTK_RESPONSE_NO),
	CANCEL(GTK.GTK_RESPONSE_CANCEL),
	ACCEPT(GTK.GTK_RESPONSE_ACCEPT), //如果改为分号则报错
	APPLY(GTK.GTK_RESPONSE_APPLY),
	REJECT(GTK.GTK_RESPONSE_REJECT),
	NONE(GTK.GTK_RESPONSE_NONE),	
	HELP(GTK.GTK_RESPONSE_HELP),
	DELETE_EVENT(GTK.GTK_RESPONSE_DELETE_EVENT);
	
	
	private  int value = 0;
	/**
	 * 
	 * @param value  构造函数的参数
	 */
	private OOResponseType(int value)
	{
		this.value = value;
	}
	/**
	 * 
	 * @return  返回OOResponseType对象的int值
	 */
	public int getValue()
	{
		return this.value;
	}
	/**
	 * 
	 * @param value   带解析的数字 在OOResponse枚举类中代表的枚举值
	 * @return        返回一个OOResponseType对象
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
		throw new IllegalArgumentException("ResponseValue = "+value+"是个不合法的参数");
	}
}
