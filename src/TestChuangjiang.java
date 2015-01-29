import java.util.Arrays;

/**
 * @author 叶昭良
 *
 */
public class TestChuangjiang
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		tellStory(1);
		fact(5);
		int  i = 0;
		i++;
		i=i+1;
		i+=1;
		
		System.out.println(fib1(8));
		fib2(8);
		System.out.println(fib3(8));
		
		testString();
		TestInteger();
	}
	
	// 递归
	//case 1 讲故事
/*	// 错误 栈溢出 ！ 原因？ 改为 ++i	
	public static void tellStory(int i)
	{
		System.out.println("从前有座山，山里有座庙....."+i);
		if(i < 3)
		{
			tellStory(i++);  // 因为i++  是i=i+1  但是表达式的值依然为1
		} // ++i 即可以！！
		System.out.println("Time Over in "+i);
		
	}*/
	/**
	 * 
	 * @param i  讲故事的次数
	 */
	public static void tellStory(int i)
	{
		System.out.println("从前有座山，山里有座庙.....");
		if(i < 3)
		{
			tellStory(++i);  // i+1
		}	
		System.out.println("Time Over in "+i);
		
	}
	//case 2 阶乘
	/**
	 * 
	 * @param n    待计算阶乘的数
	 * @return     返回阶乘计算结果
	 */
	public static int fact(int n)
	{
		if(n < 0)
		{
			throw new IllegalArgumentException();
		}
		if(1 == n)
		{
			return  1;
		}else
		{
			return n*fact(n-1);
		}
	}
	
	//case 3 Fibnacci
	/**
	 * 
	 * @param a   fibnacci的计算范围
	 * @return    对应a的fibnacci数
	 */
	public static int fib1(int a)
	{
		if(a < 0) //很重要，一定要对参数进行判断
		{
			throw new IllegalArgumentException("a<0");
		}
		if(1 == a || 0 == a)
		{
			return 1;
		}else
		{
			return fib1(a-1)+fib1(a-2);
		}
	}
	
	//case31  Fibnacci2
	/**
	 * 
	 * @param n   比如1  1  2 3 5 8 。。。。 需要计算第五个 则 n=5
	 * @return  fibnacci数
	 */
	public static int  fib2(int n	)
	{
		int[]  nums = new int[n+1];
		nums[0] = 1; 
		nums[1] = 1;
		System.out.println(Arrays.toString(nums));
		for(int  i = 2 ; i < n+1 ; i++)
		{
			nums[i] = nums[i-1] + nums[i-2];
			System.out.println(Arrays.toString(nums));
		}
		return nums[n];
	}
	
	//case 32  Fibnacci3
	/**
	 * 
	 * @param n  表示准备计算的阶乘数 不然5！ 则n = 5
	 * @return  阶乘的结果
	 */
	public static int fib3(int n)
	{
		int a = 1;
		int b = 1;
		int temp = 1;
		for(int  i= 2;  i <= n ; i++)
		{
			temp = a + b;
			a = b ;
			b = temp;
		}
		return temp;
	}
	
	
	public static void testString()
	{
		String apple= "rupeng.com";
		String banana = "rupeng"+".com";
		String orangeban = "rupeng";
		String orangege = ".com";
		String peach = new String("rupeng.com");
		String bitch = new String("rupeng.com");
		
		System.out.println("用于查看编译器优化"+(apple == banana));
		System.out.println("用于观看字符串的线程池的作用"+(peach == bitch));
		System.out.println("用于查看字符串的临时变量的作用"+((orangeban+orangege)==apple));
		System.out.println("通过上面三种比较，知道字符串有一个字符串常量池，并有时候需要考虑编译器优化问题");
		//进行Person进行测试equals的过程
		//Person person1  = new Person(3);
		Person person2 =  new TestChuangjiang().new Person(3);
		Person person1 = new TestChuangjiang().new Person(3);
		System.out.println("新建的内部类的对象比较:"+(person2==person1));
		System.out.println("新建的内部类的对象equals比较:"+person2.equals(person1));
		System.out.println("通过上面两种方式的比较，知道equals可以控制类的比较");
		
	}
	
	 class Person
	 {
		private int age = 0;
		public Person(int age)
		{
			this.setAge(age);
		}
		
		
		public int getAge()
		{
			return age;
		}
		public void setAge(int age)
		{
			this.age = age;
		}
		
		public boolean equals(Object obj)
		{
			//if(!(obj == Person))
			if(!(obj instanceof Person))
			{
				return false;
			}
			Person p2 =(Person)(obj);
			return p2.age==this.age;
		}
	}
	 
	 public static void TestInteger()
	 {
		 Integer  iceCream = 199;  // 编译器 自动转化为 Integer.valueOf(199)
		 Integer iceCream1 = 199;
		 System.out.println("iceCream == iceCream1? :"+ (iceCream == iceCream1));
		 System.out.println("iceCream equals iceCream1? :"+ (iceCream.equals(iceCream1))); //因为Integer
		 //类已经重写了 equals方法
		 
		 Integer  iceCream2 = 8; // 默认是缓存-128-127 个Integer
		 Integer iceCream3 = 8;
		 System.out.println("iceCream2 == iceCream3? :"+ (iceCream2 == iceCream3));
		 System.out.println("iceCream2 equals iceCream3? :"+ (iceCream2.equals(iceCream3)));
		 
		 Integer  iLoveYou = new Integer(8); // 不能针对构造函数，构造函数内未返回缓冲的-128-127对象
		 Integer iHateYou = new Integer(8);
		 System.out.println("iLoveYou == iHateYou? :"+ (iLoveYou == iHateYou));
		 System.out.println("iLoveYou equals iHateYou? :"+ (iLoveYou.equals(iHateYou)));
		 System.out.println("通过上面三种比较，的确构造函数不体现自动装箱，自动装箱的体现在于valueOf");
		 
		 MyInteger maple = new MyInteger(9);
		 MyInteger manner = new MyInteger(9);
		 System.out.println("maple(9) == manner(9)? :"+(maple== manner));
		 System.out.println("maple(9).equals(manner(9))? :"+maple.equals(manner));
		 
		 MyInteger maple1 = MyInteger.valueOf(9);
		 MyInteger manner1 = MyInteger.valueOf(9);
		 System.out.println("maple1(9) == manner1(9)? :"+(maple1== manner1));
		 System.out.println("maple1(9).equals(manner1(9))? :"+maple1.equals(manner1));
		 
		 MyInteger margin = new MyInteger(99);
		 MyInteger much = new MyInteger(99);
		 System.out.println("margin(99) == much(99)? :"+(margin== much));
		 System.out.println("margin(99).equals(much(99))? :"+margin.equals(much));
		 
		 // 自动装箱的过程是和valueOf函数绑定在一起的。。。。。
		 // 就好像每一个类的toString是和println绑定输出一样。
		 MyInteger margin1 = MyInteger.valueOf(99);
		 MyInteger much1 =  MyInteger.valueOf(99);
		 System.out.println("margin1(99) == much1(99)? :"+(margin1== much1));
		 System.out.println("margin1(99).equals(much1(99))? :"+margin1.equals(much1));
		 System.out.println("通过上面四种比较，自定义的MyInteger类在一定的数值范围内，存在缓冲区对象");
		 
		 
	 }
	 

/*	 class MyInteger
	 {
		 private static MyInteger[] cache = new MyInteger[10];
		 static
		 {
			 for(int i = 0 ;i < 10; i++)
			 {
				 cache[i] = new MyInteger(i);
			 }
		 }
		 private int value;
		 public MyInteger(int value)
		 {
			 this.value = value;
		 }
	 }*/
}
class MyInteger
{
	 private static MyInteger[] cache = new MyInteger[10];
	 static  //
	 {
		 for(int i = 0 ;i < 10; i++)
		 {
			 cache[i] = new MyInteger(i);
		 }
	 }
	 private int value;
	 public MyInteger(int value)
	 {
		 this.value = value;
	 }
	 @Override
	public boolean equals(Object obj)
	{
		// TODO 自动生成的方法存根
/*		 if(!(obj instanceof MyInteger))
		 {
			 return false;
		 }
		MyInteger banana = (MyInteger)obj;
		return banana.getValue()==this.value;*/
		 
		 if(obj instanceof MyInteger)
		 {
			 return ((MyInteger)obj).getValue()==this.value;
		 }
		 return false;
		
	}
	 public static MyInteger valueOf(int  value)
	 {
		 if(value > 0 && value < cache.length)
		 {
			 return cache[value];
		 }
		 return new MyInteger(value);
	 }
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}

}
