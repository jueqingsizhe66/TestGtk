/**
 * 
 */
package InterfacePractice;

/**
 * @author    叶昭良
 * @time      2015年2月8日下午3:48:21
 * @version   InterfacePracticeEnumable V1.0
 */
public enum Enumable
{
	EAST(1),NORTH(2),SOUTH(3),WEST(4); //相当于建立了四个Enumable对象
	private  int value = 0;
	private Enumable(int value) //枚举类一个特性 构造函数只能为private
	{
		this.value = value;
	}
	
	//枚举转化为int
	public int getValue()
	{
		return this.value;
	}
	
	//int转化为枚举
	public static Enumable parseInt(int value)
	{
		Enumable[] banana = Enumable.values();
		for(int i = 0 ; i < banana.length; i++)
		{
			if(value == banana[i].getValue())
			{
				return banana[i];
			}
		}
		throw new IllegalArgumentException("不合法的值");
	}
	
	
}
