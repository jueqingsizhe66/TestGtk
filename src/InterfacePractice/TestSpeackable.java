/**
 * 
 */
package InterfacePractice;

/**
 * @author    Ҷ����
 * @time      2015��2��8������9:30:40
 * @version   InterfacePracticeTestSpeackable V1.0
 */
public class TestSpeackable implements Speakable
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new TestSpeackable().speak();
		new TestSpeackable().look();
		//���ö�̬��ʵ��	
		Speakable sp = new TestSpeackable();
		sp.speak();
		
		//����������ķ�ʽ ��������Speakable�ӿڣ�����������
		Speakable sp1 = new Speakable()
		{
			
			@Override
			public void speak()
			{
				// TODO Auto-generated method stub
				System.out.println("��Ҳ����");
			}
			
			@Override
			public void look()
			{
				// TODO Auto-generated method stub
				System.out.println("��Ҳ����");
			}
		};
		
		sp1.speak();
		sp1.look();
		
	}

	@Override
	public  void speak()
	{
		// TODO Auto-generated method stub
		System.out.println("yamadie");
	}

	@Override
	public void look()
	{
		// TODO Auto-generated method stub
		System.out.println("����ȥ������");
	}

}