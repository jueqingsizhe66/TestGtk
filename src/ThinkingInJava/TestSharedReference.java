package ThinkingInJava;
/**
 *  注意观察 引用的观察！！理解静态变量的调用　！通过一个外部的shared类
 *    来keep track of  创作了几个！！
 * @author    叶昭良
 * @time      2015年3月3日上午12:31:47
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
	//用于保存记录的个数
	private int refcount = 0;
	//counter用于保存记录的个数 
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
	//如果是不是static！则会在每次创建对象的时候重新初始化！
	//所以static 变量也叫做类变量！ 在类的生命周期内！可以用于创建对象的产生个数
	//只要在构造函数或者析构函数进行管理类变量既可以控制对象的个数的状态
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