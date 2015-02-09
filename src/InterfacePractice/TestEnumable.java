/**
 * 
 */
package InterfacePractice;

/**
 * @author    叶昭良
 * @time      2015年2月8日下午3:47:29
 * @version   InterfacePracticeTestEnumable V1.0
 */
public class TestEnumable
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Enumable enumApple = Enumable.EAST; //无法调用构造函数
		Enumable enumBanana = Enumable.parseInt(3);
		
		System.out.println("enumApple 的 "+enumApple+ " 为 "+enumApple.getValue());
		System.out.println("enumBanana = "+enumBanana+"并且它的值为 "+enumBanana.getValue());
		
	}

}
