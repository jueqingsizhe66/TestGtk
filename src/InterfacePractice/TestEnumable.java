/**
 * 
 */
package InterfacePractice;

/**
 * @author    Ҷ����
 * @time      2015��2��8������3:47:29
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
		Enumable enumApple = Enumable.EAST; //�޷����ù��캯��
		Enumable enumBanana = Enumable.parseInt(3);
		
		System.out.println("enumApple �� "+enumApple+ " Ϊ "+enumApple.getValue());
		System.out.println("enumBanana = "+enumBanana+"��������ֵΪ "+enumBanana.getValue());
		
	}

}