/**
 * http://www.cnblogs.com/fangfan/p/4086662.html
 */
package com.collections.test;

import java.util.Arrays;

/**
 * @author    叶昭良
 * @time      2015年2月25日下午11:56:33
 * @version   com.collections.testByteUtils V1.0
 */
public class ByteUtils
{

	/**
	 * @param args
	 */
	  /**
     * 
     * <pre>
     * 将4个byte数字组成的数组合并为一个float数.
     * </pre>
     * 
     * @param arr
     * @return
     */
    public static float byte4ToFloat(byte[] arr) {
        if (arr == null || arr.length != 4) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是4位!");
        }
        int i = byte4ToInt(arr);
        return Float.intBitsToFloat(i);
    }

    /**
     * 
     * <pre>
     * 将一个float数字转换为4个byte数字组成的数组.
     * </pre>
     * 
     * @param f
     * @return
     */
    public static byte[] floatToByte4(float f) {
        int i = Float.floatToIntBits(f);
        return intToByte4(i);
    }

    /**
     * 
     * <pre>
     * 将八个byte数字组成的数组转换为一个double数字.
     * </pre>
     * 
     * @param arr
     * @return
     */
    public static double byte8ToDouble(byte[] arr) {
        if (arr == null || arr.length != 8) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是8位!");
        }
        long l = byte8ToLong(arr);
        return Double.longBitsToDouble(l);
    }

    /**
     * 
     * <pre>
     * 将一个double数字转换为8个byte数字组成的数组.
     * </pre>
     * 
     * @param i
     * @return
     */
    public static byte[] doubleToByte8(double i) {
        long j = Double.doubleToLongBits(i);
        return longToByte8(j);
    }

    /**
     * 
     * <pre>
     * 将一个char字符转换为两个byte数字转换为的数组.
     * </pre>
     * 
     * @param c
     * @return
     */
    public static byte[] charToByte2(char c) {
        byte[] arr = new byte[2];
        arr[0] = (byte) (c >> 8);
        arr[1] = (byte) (c & 0xff);
        return arr;
    }

    /**
     * 
     * <pre>
     * 将2个byte数字组成的数组转换为一个char字符.
     * </pre>
     * 
     * @param arr
     * @return
     */
    public static char byte2ToChar(byte[] arr) {
        if (arr == null || arr.length != 2) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是2位!");
        }
        return (char) (((char) (arr[0] << 8)) | ((char) arr[1]));
    }

    /**
     * 
     * <pre>
     * 将一个16位的short转换为长度为2的8位byte数组.
     * </pre>
     * 
     * @param s
     * @return
     */
    public static byte[] shortToByte2(Short s) {
        byte[] arr = new byte[2];
        arr[0] = (byte) (s >> 8);
        arr[1] = (byte) (s & 0xff);
        return arr;
    }

    /**
     * 
     * <pre>
     * 长度为2的8位byte数组转换为一个16位short数字.
     * </pre>
     * 
     * @param arr
     * @return
     */
    public static short byte2ToShort(byte[] arr) {
        if (arr != null && arr.length != 2) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是2位!");
        }
        return (short) (((short) arr[0] << 8) | ((short) arr[1] & 0xff));
    }

    /**
     * 
     * <pre>
     * 将short转换为长度为16的byte数组.
     * 实际上每个8位byte只存储了一个0或1的数字
     * 比较浪费.
     * </pre>
     * 
     * @param s
     * @return
     */
    public static byte[] shortToByte16(short s) {
        byte[] arr = new byte[16];
        for (int i = 15; i >= 0; i--) {
            arr[i] = (byte) (s & 1);
            s >>= 1;
        }
        return arr;
    }

    public static short byte16ToShort(byte[] arr) {
        if (arr == null || arr.length != 16) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度为16!");
        }
        short sum = 0;
        for (int i = 0; i < 16; ++i) {
            sum |= (arr[i] << (15 - i));
        }
        return sum;
    }

    /**
     * 
     * <pre>
     * 将32位int转换为由四个8位byte数字.
     * </pre>
     * 
     * @param sum
     * @return
     */
    public static byte[] intToByte4(int sum) {
        byte[] arr = new byte[4];
        arr[0] = (byte) (sum >> 24);
        arr[1] = (byte) (sum >> 16);
        arr[2] = (byte) (sum >> 8);
        arr[3] = (byte) (sum & 0xff);
        return arr;
    }

    /**
     * <pre>
     * 将长度为4的8位byte数组转换为32位int.
     * </pre>
     * 
     * @param arr
     * @return
     */
    public static int byte4ToInt(byte[] arr) {
        if (arr == null || arr.length != 4) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是4位!");
        }
        return (int) (((arr[0] & 0xff) << 24) | ((arr[1] & 0xff) << 16) | ((arr[2] & 0xff) << 8) | ((arr[3] & 0xff)));
    }

    /**
     * 
     * <pre>
     * 将长度为8的8位byte数组转换为64位long.
     * </pre>
     * 
     * 0xff对应16进制,f代表1111,0xff刚好是8位 byte[]
     * arr,byte[i]&0xff刚好满足一位byte计算,不会导致数据丢失. 如果是int计算. int[] arr,arr[i]&0xffff
     * 
     * @param arr
     * @return
     */
    public static long byte8ToLong(byte[] arr) {
        if (arr == null || arr.length != 8) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是8位!");
        }
        return (long) (((long) (arr[0] & 0xff) << 56) | ((long) (arr[1] & 0xff) << 48) | ((long) (arr[2] & 0xff) << 40)
                        | ((long) (arr[3] & 0xff) << 32) | ((long) (arr[4] & 0xff) << 24)
                        | ((long) (arr[5] & 0xff) << 16) | ((long) (arr[6] & 0xff) << 8) | ((long) (arr[7] & 0xff)));
    }

    /**
     * 将一个long数字转换为8个byte数组组成的数组.
     */
    public static byte[] longToByte8(long sum) {
        byte[] arr = new byte[8];
        arr[0] = (byte) (sum >> 56);
        arr[1] = (byte) (sum >> 48);
        arr[2] = (byte) (sum >> 40);
        arr[3] = (byte) (sum >> 32);
        arr[4] = (byte) (sum >> 24);
        arr[5] = (byte) (sum >> 16);
        arr[6] = (byte) (sum >> 8);
        arr[7] = (byte) (sum & 0xff);
        return arr;
    }

    /**
     * 
     * <pre>
     * 将int转换为32位byte.
     * 实际上每个8位byte只存储了一个0或1的数字
     * 比较浪费.
     * </pre>
     * 
     * @param num
     * @return
     */
    public static byte[] intToByte32(int num) {
        byte[] arr = new byte[32];
        for (int i = 31; i >= 0; i--) {
            // &1 也可以改为num&0x01,表示取最地位数字.
            arr[i] = (byte) (num & 1);
            // 右移一位.
            num >>= 1;
        }
        return arr;
    }

    /**
     * 
     * <pre>
     * 将长度为32的byte数组转换为一个int类型值.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     * 
     * @param arr
     * @return
     */
    public static int byte32ToInt(byte[] arr) {
        if (arr == null || arr.length != 32) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度是32!");
        }
        int sum = 0;
        for (int i = 0; i < 32; ++i) {
            sum |= (arr[i] << (31 - i));
        }
        return sum;
    }

    /**
     * 
     * <pre>
     * 将长度为64的byte数组转换为一个long类型值.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     * 
     * @param arr
     * @return
     */
    public static long byte64ToLong(byte[] arr) {
        if (arr == null || arr.length != 64) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度是64!");
        }
        long sum = 0L;
        for (int i = 0; i < 64; ++i) {
            sum |= ((long) arr[i] << (63 - i));
        }
        return sum;
    }

    /**
     * 
     * <pre>
     * 将一个long值转换为长度为64的8位byte数组.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     * 
     * @param sum
     * @return
     */
    public static byte[] longToByte64(long sum) {
        byte[] arr = new byte[64];
        for (int i = 63; i >= 0; i--) {
            arr[i] = (byte) (sum & 1);
            sum >>= 1;
        }
        return arr;
    }
    
   
    public static void showMaxValAndminVal() {
        // 127
        System.out.println("Byte.Max_Value:"+Byte.MAX_VALUE);
        // -128
        System.out.println("Byte.MIN_VALUE:"+Byte.MIN_VALUE);

        // 32767
        System.out.println("Short.MAX_VALUE:"+Short.MAX_VALUE);
        // -32768
        System.out.println("Short.MIN_VALUE:"+Short.MIN_VALUE);

        // 65535 2的16次方-1
        System.out.println("(int) Character.MAX_VALUE:"+(int) Character.MAX_VALUE);
        // 0
        System.out.println("(int) Character.MIN_VALUE:"+(int) Character.MIN_VALUE);

        // 2147483647
        System.out.println("Integer.MAX_VALUE:"+Integer.MAX_VALUE);
        // -2147483648
        System.out.println("Integer.MIN_VALUE:"+Integer.MIN_VALUE);

        // 科学计数法.
        // 3.4028235E38
        System.out.println("Float.MAX_VALUE:"+Float.MAX_VALUE);
        // 1.4E-45
        System.out.println("Float.MIN_VALUE:"+Float.MIN_VALUE);

        // 9223372036854775807
        System.out.println("Long.MAX_VALUE:"+Long.MAX_VALUE);
        // -9223372036854775808
        System.out.println("Long.MIN_VALUE:"+Long.MIN_VALUE);

        // 科学计数法.
        // 1.7976931348623157E308
        System.out.println("Double.MAX_VALUE:"+Double.MAX_VALUE);
        // 4.9E-324
        System.out.println("Double.MIN_VALUE:"+Double.MIN_VALUE);
    }
    
    public static void transByte() {
        char c = 'z';
        byte[] charToByte2Arr = ByteUtils.charToByte2(c);
       

        short s = Short.MAX_VALUE;
        // System.out.println("Short.MAX_VALUE:" + s);
        byte[] shortToByte2Arr = ByteUtils.shortToByte2(s);
       

        byte[] shortToByte16 = ByteUtils.shortToByte16(s);
        System.out.println(Arrays.toString(shortToByte16));
        System.out.println(ByteUtils.byte16ToShort(shortToByte16));

        int i = Integer.MAX_VALUE;
        // System.out.println("Integer.MAX_VALUE:" + i);
        byte[] intToByte4Arr = ByteUtils.intToByte4(i);
        

        byte[] intToByte32Arr = ByteUtils.intToByte32(i);
        System.out.println(Arrays.toString(intToByte32Arr));
        System.out.println(ByteUtils.byte32ToInt(intToByte32Arr));

        long j = Long.MAX_VALUE;
        // System.out.println("Long.MAX_VALUE:" + j);
        byte[] longToByte8Arr = ByteUtils.longToByte8(j);
      

        byte[] longToByte64Arr = ByteUtils.longToByte64(j);
        System.out.println(Arrays.toString(longToByte64Arr));
        System.out.println(ByteUtils.byte64ToLong(longToByte64Arr));

        double d = 2.34;
        byte[] doubleToByte8Arr = ByteUtils.doubleToByte8(d);
       

        float f = 1.2f;
        byte[] floatToByte4Arr = ByteUtils.floatToByte4(f);
       
    }

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
//		showMaxValAndminVal();
		transByte();
	}

}
