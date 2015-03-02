/**
 * 
 */
package com.original.sort;

/**
 * @author    叶昭良
 * @time      2015年2月27日上午11:50:22
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
		//居然forget how to define the array
		int[] arr = {3,5,-23,235,3,5};
		System.out.println("排序前的arr:");
		for(int temp:arr)
		{
			System.out.println(temp);
		}
		//b1.sortBuddle(arr);
		b1.sortBuddle1(arr);
		System.out.println("排序后的arr:");
		for(int temp:arr)
		{
			System.out.println(temp);
		}
		
		int[] banana = {3,5,-23,235,3,5};
		//使用的时候 必须是用-1  否则超出了 数组的范围
		b1.quicksort(banana,0,banana.length-1);
		System.out.println("排序后的banana:");
		for(int temp:banana)
		{
			System.out.println(temp);
		}
	}

}
class Buddle
{
	//冒泡法
	public void sortBuddle(int[] arr)
	{
		int temp = 0;
		//表示第k趟， 每一堂都搜出最大值！
		for(int k = 0; k < arr.length; k++)
		{
			for(int i = 0 ; i < arr.length - 1 -k ; i++)
				if(arr[i] > arr[i+1]) // < 表示降序排列
				{
					temp = arr[i];
					arr[i]  = arr[i+1];
					arr[i+1] = temp;
				}
		}
	}
	//另外一种冒泡法！
	public void sortBuddle1(int[] arr)
	{
		//表示第k趟， 每一堂都搜出最大值！
		for(int k = 0; k < arr.length; k++)
		{//在每一趟的过程中选取其中的最大值
			for(int i = 0 ; i < arr.length - 1 -k ; i++)
				if(arr[i] > arr[i+1]) // < 表示降序排列
				{
					arr[i]  = arr[i] ^ arr[i+1];
					arr[i+1]  = arr[i] ^ arr[i+1];
					arr[i]  = arr[i] ^ arr[i+1];
				}
		}
	}
	//快速排序法
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
		//保存  a[low]的  值 让他移到最中间的位置！！他的左边小于他  他的右边大于他
		int val = apple[low];
		while(low < high)
		{
			//这边才是快排的核心地带   我们首先移动指针H 然后移不动的话 赋值给L指向处 然后给L移动  
			   while(low<high && val <= apple[high])  //a[0] 就是val   这边有点考虑不周  到底是不是用a[0]来当做val 
			   {
				   high--;
			   }
			     apple[low] = apple[high]; //没错这边的 
			   while(low < high && val >= apple[low]) 
			   {
				   low++;  //一直让他移动   这边当时不能快速反应  估计是接触得不多！
			   }   
				 apple[high] = apple[low]; 
			}
			 //val = a[low];  这边的值必须修改
			 apple[low] = val; 
			 return high;  //可以改为return low； 因为最终 low == high 
	}
}
