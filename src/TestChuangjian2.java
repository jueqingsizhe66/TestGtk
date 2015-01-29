import java.util.Arrays;


/**
 * @author 叶昭良
 *
 */
public abstract class TestChuangjian2
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		//第一部分 递归
		// case1讲故事
		tellStory(1);
		System.out.println(fact(5));
		System.out.println(fact2(5));
		System.out.println(fact3(5,1));
		System.out.println("fibnacii1 :"+fib1(5));
		System.out.println(fib2(5));
		System.out.println(fib3(5));
	}
	//第一部分  递归
	//case 1*讲故事
	/**
	 * 
	 * @param i  讲故事的次数
	 */
	public static void tellStory(int i)
	{
		System.out.println("从前有座山，山里有座庙，。。。。");
		if(i < 3)
		{
			tellStory(++i);
		}
		System.out.println("完了");
	}
	//case2  阶乘
	/**
	 * 
	 * @param n  计算 5！ 则n=5
	 * @return  返回阶乘的计算结果
	 */
	public static int fact(int n)
	{
		if(n < 0)
		{
			throw new IllegalArgumentException();
		}
		if( 1 == n)
		{
			return 1;
		}else
		{
			return n*fact(n-1);
		}
	}
	
	// case3  无递归的阶乘
	/**
	 * 
	 * @param n   计算 5！ 则n=5
	 * @return
	 */
	public static int fact2(int n)
	{
		if(n < 0)
		{
			throw new IllegalArgumentException();
		}
		int result = 1;
		for(int i = 1; i <= n; i++)
		{
			result = result*i;
		}
		return result;
	}
	// case 4  尾递归方式
	/**
	 * 
	 * @param n      计算 5！ 则n=5
	 * @param result  存储阶乘的计算结果，并作为中间变量
	 * @return       返回阶乘计算结果
	 */
	public static int fact3(int n, int result)
	{
		if(n <= 0)
		{
			throw new IllegalArgumentException("胡算的阶乘数<=0");
		}
		if(1== n)
		{
			return result;
		}else
		{
			return fact3(n-1,result*n);
		}
	}
	
	//case 5 Fibnacci1
	/**
	 * 
	 * @param n  fibnacci的个数n
	 * @return   fibnacci数
	 */
	public static int fib1(int n)
	{
		if(n < 0)
		{
			throw new IllegalArgumentException("n不可以小于0");
		}
		if(1 == n || 2 == n)
		{
			return 1;
		}
		else
		{
			return fib1(n-1)+fib1(n-2);
		}
	}
	/**
	 * 
	 * @param n  fibnacci的计算数
	 * @return   fibanacci数
	 */
	public static int fib2(int n)
	{
		int[] nums = new int[n+1];
		nums[0] = 1;
		nums[1] = 1;
		System.out.println(Arrays.toString(nums));
		for(int i = 2; i < n ; i++)
		{
			nums[i] = nums[i-1]+ nums[i-2];
			System.out.println(Arrays.toString(nums));
		}
		return nums[n-1];
	}
	/**
	 * 
	 * @param n   fibnacci的计算数
	 * @return    fibanacci数
	 */
	public static int fib3(int n)
	{
		int apple = 1;
		int banana = 1;
		int temp = 1;
		for (int i = 2 ; i < n; i++)
		{
			temp = apple + banana;
			banana = apple;
		    apple = temp;
		}
		return temp;
	}
}
