/**
 * 
 */
package com.original.sort;

/**
 * @author    Ҷ����
 * @time      2015��2��27������11:50:22
 * @version   com.original.sortTestSort V1.0
 */
public class TestSort
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Buddle b1 = new Buddle();
		//��Ȼforget how to define the array
		int[] arr = {3,5,-23,235,3,5};
		System.out.println("����ǰ��arr:");
		for(int temp:arr)
		{
			System.out.println(temp);
		}
		//b1.sortBuddle(arr);
		b1.sortBuddle1(arr);
		System.out.println("������arr:");
		for(int temp:arr)
		{
			System.out.println(temp);
		}
		
		int[] banana = {3,5,-23,235,3,5};
		//ʹ�õ�ʱ�� ��������-1  ���򳬳��� ����ķ�Χ
		b1.quicksort(banana,0,banana.length-1);
		System.out.println("������banana:");
		for(int temp:banana)
		{
			System.out.println(temp);
		}
	}

}
class Buddle
{
	//ð�ݷ�
	public void sortBuddle(int[] arr)
	{
		int temp = 0;
		//��ʾ��k�ˣ� ÿһ�ö��ѳ����ֵ��
		for(int k = 0; k < arr.length; k++)
		{
			for(int i = 0 ; i < arr.length - 1 -k ; i++)
				if(arr[i] > arr[i+1]) // < ��ʾ��������
				{
					temp = arr[i];
					arr[i]  = arr[i+1];
					arr[i+1] = temp;
				}
		}
	}
	//����һ��ð�ݷ���
	public void sortBuddle1(int[] arr)
	{
		//��ʾ��k�ˣ� ÿһ�ö��ѳ����ֵ��
		for(int k = 0; k < arr.length; k++)
		{//��ÿһ�˵Ĺ�����ѡȡ���е����ֵ
			for(int i = 0 ; i < arr.length - 1 -k ; i++)
				if(arr[i] > arr[i+1]) // < ��ʾ��������
				{
					arr[i]  = arr[i] ^ arr[i+1];
					arr[i+1]  = arr[i] ^ arr[i+1];
					arr[i]  = arr[i] ^ arr[i+1];
				}
		}
	}
	//��������
	public void quicksort(int[] apple, int low, int high)
	{
		int pos ;
		if(low < high)
		{
			pos = findOps(apple,low,high);
			quicksort(apple, low, pos-1);
			quicksort(apple, pos+1,high);
		}
	}
	
	public int findOps(int[] apple, int low, int high)
	{
		//����  a[low]��  ֵ �����Ƶ����м��λ�ã����������С����  �����ұߴ�����
		int val = apple[low];
		while(low < high)
		{
			//��߲��ǿ��ŵĺ��ĵش�   ���������ƶ�ָ��H Ȼ���Ʋ����Ļ� ��ֵ��Lָ�� Ȼ���L�ƶ�  
			   while(low<high && val <= apple[high])  //a[0] ����val   ����е㿼�ǲ���  �����ǲ�����a[0]������val 
			   {
				   high--;
			   }
			     apple[low] = apple[high]; //û����ߵ� 
			   while(low < high && val >= apple[low]) 
			   {
				   low++;  //һֱ�����ƶ�   ��ߵ�ʱ���ܿ��ٷ�Ӧ  �����ǽӴ��ò��࣡
			   }   
				 apple[high] = apple[low]; 
			}
			 //val = a[low];  ��ߵ�ֵ�����޸�
			 apple[low] = val; 
			 return high;  //���Ը�Ϊreturn low�� ��Ϊ���� low == high 
	}
}