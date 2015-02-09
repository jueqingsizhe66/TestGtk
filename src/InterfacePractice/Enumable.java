/**
 * 
 */
package InterfacePractice;

/**
 * @author    Ҷ����
 * @time      2015��2��8������3:48:21
 * @version   InterfacePracticeEnumable V1.0
 */
public enum Enumable
{
	EAST(1),NORTH(2),SOUTH(3),WEST(4); //�൱�ڽ������ĸ�Enumable����
	private  int value = 0;
	private Enumable(int value) //ö����һ������ ���캯��ֻ��Ϊprivate
	{
		this.value = value;
	}
	
	//ö��ת��Ϊint
	public int getValue()
	{
		return this.value;
	}
	
	//intת��Ϊö��
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
		throw new IllegalArgumentException("���Ϸ���ֵ");
	}
	
	
}