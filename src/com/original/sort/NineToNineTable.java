/**
 * 
 */
package com.original.sort;

/**
 * @author    叶昭良
 * @time      2015年2月27日下午1:40:10
 * @version   com.original.sortNineToNineTable V1.0
 */
public class NineToNineTable
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Nine n1 = new Nine();
		n1.set(4);
	}

}

class Nine{
	public Nine(){
		System.out.println("这是乘法表！");
	}
	public void set(int n){
		for(int i = 1 ; i <= n ; i++){  //少了等号的话  居然会少了很多东西  i<n
			for(int j = 1 ; j <= i ; j++){
				if(i == 3 && 3 == j || 4==i && 3==j){
					System.out.print(" "+j+"*"+i+"="+(i*j)+" ");
				}else{
					System.out.print(j+"*"+i+"="+(j*i)+" ");
				}
			}
			System.out.println("");
		}
	}
}