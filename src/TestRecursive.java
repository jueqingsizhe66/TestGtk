import java.math.BigInteger;

/**
 * @author 叶昭良
 *
 */	
public class TestRecursive
{


	/**
	 * 
	 * @param args
	 */
	public static void tellStory(int i)
	{
		System.out.println("从前有座山，山里有座庙，。。。你讲的是纳尼？");
		if(i < 3)
		{
//			i++;
			tellStory(i+1);
		}
		System.out.println("哦了");
	}
	public static void fib2(int i)
	{
		System.out.println("从前有座山，山里有座庙，。。。你讲的是纳尼？");
		if(i < 3)
		{
//			i++;
			tellStory(i+1);
		}
		System.out.println("哦了");
	}
	// 用两个变量方法
	static int fib(int n)
    {
        int a = 1;
        int  b = 1;
        int  temp=1;
        for (int i=1; i < n;i++ )
        {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }
	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		
/*		BigInteger Apple =fact(100l,BigInteger.valueOf(1));
		System.out.println("Apple get by 尾递归："+Apple);
		BigInteger Banana =facttail(100l);
		System.out.println("Banana get by  递归："+Banana);*/
		
		char [] ch = {'a','b','c'};
		String s1  = new String(ch);
		String s2 = new String(ch);

	   System.out.println(s1.equals(s2));

	   System.out.println(fib(5));
	   System.out.println(facttail1(5,1));
	}
	/**
	 *  
	 * @param n          计算5！ 则n =5
	 * @param product    一般是设置为1  比如 计算5! factail1(5,1)
	 * @return           返回递归结果
	 */
    public static long facttail1(int n, int product)
    {
          if(n<0)
         {
            return 0;
         }
         if(1 == n)
         {
                return product;
         }
        else
        {
                return  facttail1(n-1, product*n);
        }
    }
    
	// 尾递归方式
	/**
	 * 
	 * @param n        待计算的阶乘  比如5！ 则n=5
	 * @param product  中间变量
	 * @return         一个BigInteger对象,包含阶乘的计算结果
	 */
/*	@Override
	public boolean equals(Object obj)
	{
		// TODO 自动生成的方法存根
		return super.equals(obj);
	}*/
	public static BigInteger facttail(long n, BigInteger product)
	{
		if(n<0)
		{
			return BigInteger.valueOf(0);
		}
		else if(n==1)
		    return product;
		else
			return facttail(n-1,product.multiply(BigInteger.valueOf(n)));
	}
	//递归方式
	/**
	 * 
	 * @param n   5! 则 n = 5
	 * @return    返回阶乘的计算结果
	 */
    public static long facttail1(int n)
    {
		 long a = 0;// 用于调试，可注释
		 if(n==1)
		 {
		     a = 1;// 用于调试，可注释
		    return 1;
		 }
		     a =n*facttail1(n-1);  // 用于调试，可注释
		    return n*facttail1(n-1);
    }
	/**
	 * 
	 * @param n   待计算的阶乘  比如5！ 则n=5
	 * @return    一个BigInteger对象,包含阶乘的计算结果
	 */
	public static BigInteger fact(long n)
	{
		if(n==1)
		{
			return BigInteger.valueOf(1);
		}else
		{
			//a =n*facttail1(n-1);
			return fact(n-1).multiply(BigInteger.valueOf(n));
		}

	}	

}
