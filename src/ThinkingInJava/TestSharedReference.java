package ThinkingInJava;
/**
 *  ע��۲� ���õĹ۲죡�����⾲̬�����ĵ��á���ͨ��һ���ⲿ��shared��
 *    ��keep track of  �����˼�������
 * @author    Ҷ����
 * @time      2015��3��3������12:31:47
 * @version   ThinkingInJavaTestSharedReference V1.0
 */
public class TestSharedReference
{
	public static void main(String[] args)
	{
		Shared shared = new Shared();
		Composing[] composing = { new Composing(shared),
		new Composing(shared), new Composing(shared),
		new Composing(shared), new Composing(shared) };
		for(Composing c : composing)
		c.dispose();
	}
}
class Shared 
{
	//���ڱ����¼�ĸ���
	private int refcount = 0;
	//counter���ڱ����¼�ĸ��� 
	//private static long counter = 0;
	private static long counter = 0;
	private final long id = counter++;
	public Shared() 
	{
		System.out.println("Creating In shared" + this);
	}
	public void addRef() 
	{
		refcount++; 
	}
	protected void dispose() 
	{
		if(--refcount == 0)
		{
			System.out.println("Disposing In shared " + this);
		}

	}
	public String toString() 
	{ 
		return "Shared  In shared" + id;
	}
}	

class Composing
{
	private Shared shared;
	//private  long counter = 0;
	//����ǲ���static�������ÿ�δ��������ʱ�����³�ʼ����
	//����static ����Ҳ����������� ��������������ڣ��������ڴ�������Ĳ�������
	//ֻҪ�ڹ��캯�����������������й���������ȿ��Կ��ƶ���ĸ�����״̬
	private static long counter = 0;
	private final long id = counter++;
	public Composing(Shared shared) 
	{
		System.out.println("Creating In composing " + this);
		this.shared = shared;
		this.shared.addRef();
	}
	protected void dispose() 
	{
		System.out.println("disposing  In compoising" + this);
		shared.dispose();
	}
	public String toString()
	{ 
		return "Composing " + id; 
	}
}