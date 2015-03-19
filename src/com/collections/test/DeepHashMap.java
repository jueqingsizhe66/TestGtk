/**
 * 
 */
package com.collections.test;

import java.util.HashMap;
import java.util.HashSet;



/**
 * @author    Ҷ����
 * @time      2015��2��25������11:06:09
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
 *  Long���͵�equals��ʵ��
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
		//��һ���ַ�����Ϊ ����
		//  >>>
		System.out.println(Integer.parseInt("00111111111111111111111111111100",2));
		//  >>
		//Integer.parseInt("-010101010",2) ���Դ�����
		System.out.println(Integer.toBinaryString(14));
		//~ȡ������ ������� ����
		System.out.println(~14);
		System.out.println(Integer.toBinaryString(~14));
		//��ν���������λ��
		System.out.println("2��3�η� �Ĵ����"+(2^3)); // ^��java������������
		System.out.println("2��3�η� ����ȷ��"+Math.pow(2, 3)); 
		//System.out.println(11111111111111111111111111110010);
		/*
		 * �����һ�������ڲ�
		 * System.out.println("ͨ�����ķ�ʽ������֤");
		System.out.println(Long.parseLong("11111111111111111111111111111111",2)^(Long.parseLong("11111111111111111111111111110010",2)));
		// 1101  8+4+1 = 13
		System.out.println((Long.parseLong("11111111111111111111111111111111",2)^(Long.parseLong("11111111111111111111111111110010",2)))+1);
		System.out.println(-((Long.parseLong("11111111111111111111111111111111",2)^(Long.parseLong("11111111111111111111111111110010",2)))+1));
		*/
		System.out.println("ͨ���Զ���Ļ�ȡ�����ķ���:"+parseNegativeInteger("11111111111111111111111111110010"));
		//�����Ĳ���ļ�����ȡ����1��Ȼ�����������ϼ��ϸ�����   �����Ĳ�����ֱ�Ӽ���
		System.out.println(Integer.parseInt("100",2)); //��100���������������2���Ʒ�ʽ����
		
		//String.
		System.out.println(100);
		System.out.println("��ʼ�Ӹߵ��ͽ��д�ӡ");
		//��ʽ����� ֻ�� %d  %o  %x  �����Ƶ���toBinaryString
		for(byte b1 :longToByte8(100)) 
		{
			System.out.printf("%d:",b1);
		}
		//��������ȡ��
		
		// returns the number of one-bits 
		System.out.println(Integer.toBinaryString(170));
	    System.out.println("Number of one bits = " + Integer.bitCount(170));
	    
	    // ţ���㷨�� ��n����Ϊ ĳһ����Χ�ڵ�����ֵ
	    System.out.println(15|15>>>1);
	    int n = 170; //ʹ�� n����
	    /**
	     * ���������Ǳ������ֵ�����������Ϊ�˱���ԭ��������0xff))
	     *  13 :00000000 00000000 00000000  00001101
	     *   6  00000000 00000000 00000000  00000110  
	     * 15:  00000000 00000000 00000000  00001111
	     *   һֱ����15   ��ʱ���ֵ �򱣳�һ����
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
        //�ɴ˿ɼ� If�ĸ�ֵ�ǻ�Ӱ�쵽else�ģ�����
        //���������Ⱥ�˳��ģ������ڿ�hashmap��ʱ��ѧ����
        if((tb=4) ==3)
        {
        	System.out.println("In the if"+tb);
        }else 
        {
        	System.out.println("In the else"+tb);
        }
        
	}
	/**
	 * ������ڲ��������ò����ʾ�ģ�������ԭ���벹����ͬ (Ҳ�������������Բ������)
	 * �㲻Ҫ��������д�ķ�ʽ���ĳɣ�
	 *   5  0101    3: 0011
	 *       
	 *        1:  0101 ^ 0011 =  0110
	 *        2:  0110 ^ 0011 =  0101  == 5
	 *        3:  0110 ^ 0101 =  0011  == 3  
	 *                                      ������Ĳ���
		i = i^j;
		j = i^j;
		i = i^j; 
		�����Ϳɽ����������Ľ���
	 * @param sum
	 * @return
	 */
	public static long parseNegativeInteger(String number)
	{
		return -((Long.parseLong("11111111111111111111111111111111",2)^(Long.parseLong(number,2)))+1);
	}
    public static byte[] longToByte8(long sum) {
        byte[] arr = new byte[8];
        //һ���ֽ���8 λ   long��8���ֽڵ�
        arr[0] = (byte) (sum >> 56); //����ȡ���λ
        arr[1] = (byte) (sum >> 48); //�ڶ���λ
        arr[2] = (byte) (sum >> 40); //������λ
        arr[3] = (byte) (sum >> 32);
        arr[4] = (byte) (sum >> 24);
        arr[5] = (byte) (sum >> 16);
        arr[6] = (byte) (sum >> 8);
        arr[7] = (byte) (sum & 0xff); //������258 15*16+16 =256
        return arr;
    }


}

//class 