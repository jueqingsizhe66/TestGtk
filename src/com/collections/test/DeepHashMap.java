/**
 * 
 */
package com.collections.test;

import java.util.HashMap;
import java.util.HashSet;



/**
 * @author    叶昭良
 * @time      2015年2月25日上午11:06:09
 * @version   com.collections.testDeepHashMap V1.0
 */
public class DeepHashMap
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
/**
 *  Long类型的equals的实现
 *     public boolean equals(Object obj) {
        if (obj instanceof Long) {
            return value == ((Long)obj).longValue();
        }
        return false;
    }
 */
		System.out.println(new Long(1).equals(new Integer(1)));
		System.out.println(new Long(1).intValue()==new Integer(1).intValue());
		System.out.println(new Long(1).longValue()==new Integer(1).intValue());
		System.out.println(new Long(1).longValue());
		System.out.println(new Integer(1).intValue());
		System.out.println(new Long(100).hashCode());
		System.out.println(new Integer(100).hashCode());
//		System.out.println(new Integer(100).hashCode(100));
		HashMap<Integer,String> hm = new HashMap<Integer,String>();
		System.out.println(hm.hashCode());
//		HashSet<String> hs ;
		System.out.println( (1 << 30)/1024/1024/1024); //1G
		System.out.println(8>>>2); //+++
		System.out.printf("%o -- %x  --- %X\n ----\n",-14,-14,-14);
		System.out.println(-14>>>2);
		System.out.println(Integer.toBinaryString(-14));
		System.out.println(-14>>2);
		//把一个字符串变为 奇数
		//  >>>
		System.out.println(Integer.parseInt("00111111111111111111111111111100",2));
		//  >>
		//Integer.parseInt("-010101010",2) 可以带符号
		System.out.println(Integer.toBinaryString(14));
		//~取反运算 仅仅针对 整数
		System.out.println(~14);
		System.out.println(Integer.toBinaryString(~14));
		//如何解析带符号位的
		System.out.println("2的3次方 的错误答案"+(2^3)); // ^在java当中是异或操作
		System.out.println("2的3次方 的正确答案"+Math.pow(2, 3)); 
		//System.out.println(11111111111111111111111111110010);
		/*
		 * 封存在一个函数内部
		 * System.out.println("通过异或的方式进行验证");
		System.out.println(Long.parseLong("11111111111111111111111111111111",2)^(Long.parseLong("11111111111111111111111111110010",2)));
		// 1101  8+4+1 = 13
		System.out.println((Long.parseLong("11111111111111111111111111111111",2)^(Long.parseLong("11111111111111111111111111110010",2)))+1);
		System.out.println(-((Long.parseLong("11111111111111111111111111111111",2)^(Long.parseLong("11111111111111111111111111110010",2)))+1));
		*/
		System.out.println("通过自定义的获取负数的方法:"+parseNegativeInteger("11111111111111111111111111110010"));
		//负数的补码的计算是取反加1（然后在最后的数上加上负数）   正数的补码是直接计算
		System.out.println(Integer.parseInt("100",2)); //把100这个二进制数按照2进制方式解析
		
		//String.
		System.out.println(100);
		System.out.println("开始从高到低进行打印");
		//格式化输出 只有 %d  %o  %x  二进制得用toBinaryString
		for(byte b1 :longToByte8(100)) 
		{
			System.out.printf("%d:",b1);
		}
		//我现在是取反
		
		// returns the number of one-bits 
		System.out.println(Integer.toBinaryString(170));
	    System.out.println("Number of one bits = " + Integer.bitCount(170));
	    
	    // 牛逼算法！ 让n最终为 某一个范围内的两端值
	    System.out.println(15|15>>>1);
	    int n = 170; //使得 n都是
	    /**
	     * 或的运算就是保持最大值（与的运算是为了保持原样（当和0xff))
	     *  13 :00000000 00000000 00000000  00001101
	     *   6  00000000 00000000 00000000  00000110  
	     * 15:  00000000 00000000 00000000  00001111
	     *   一直保持15   当时最大值 则保持一样了
	     */
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16; // 2^16
        System.out.println("Pow(2,16):"+Math.pow(2, 16));
        System.out.println(n);
        System.out.println(170>>>8);
        
        int tb=0;
        //由此可见 If的赋值是会影响到else的！！！
        //并且是有先后顺序的，这是在看hashmap的时候学到的
        if((tb=4) ==3)
        {
        	System.out.println("In the if"+tb);
        }else 
        {
        	System.out.println("In the else"+tb);
        }
        
	}
	/**
	 * 计算机内部负数是用补码表示的，正数的原码与补码相同 (也就是所有数都以补码存在)
	 * 你不要用这种连写的方式，改成：
	 *   5  0101    3: 0011
	 *       
	 *        1:  0101 ^ 0011 =  0110
	 *        2:  0110 ^ 0011 =  0101  == 5
	 *        3:  0110 ^ 0101 =  0011  == 3  
	 *                                      真心妙的操作
		i = i^j;
		j = i^j;
		i = i^j; 
		这样就可进行两个数的交换
	 * @param sum
	 * @return
	 */
	public static long parseNegativeInteger(String number)
	{
		return -((Long.parseLong("11111111111111111111111111111111",2)^(Long.parseLong(number,2)))+1);
	}
    public static byte[] longToByte8(long sum) {
        byte[] arr = new byte[8];
        //一个字节有8 位   long是8个字节的
        arr[0] = (byte) (sum >> 56); //先提取最高位
        arr[1] = (byte) (sum >> 48); //第二高位
        arr[2] = (byte) (sum >> 40); //第三高位
        arr[3] = (byte) (sum >> 32);
        arr[4] = (byte) (sum >> 24);
        arr[5] = (byte) (sum >> 16);
        arr[6] = (byte) (sum >> 8);
        arr[7] = (byte) (sum & 0xff); //不超过258 15*16+16 =256
        return arr;
    }


}

//class 
