/**
 * 
 */
package InterfacePractice;

/**
 * @author    叶昭良
 * @time      2015年2月8日上午9:30:40
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
		//利用多态来实现	
		Speakable sp = new TestSpeackable();
		sp.speak();
		
		//利用匿名类的方式 是吸纳了Speakable接口，并立马返回
		Speakable sp1 = new Speakable()
		{
			
			@Override
			public void speak()
			{
				// TODO Auto-generated method stub
				System.out.println("这也可以");
			}
			
			@Override
			public void look()
			{
				// TODO Auto-generated method stub
				System.out.println("这也可以");
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
		System.out.println("带你去看星星");
	}

}
