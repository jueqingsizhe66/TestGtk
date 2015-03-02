/**
 * 
 */
package com.collections.test;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author    叶昭良
 * @time      2015年3月1日下午10:00:48
 * @version   com.collections.testtestStatic V1.0
 */
public class testStatic
{

	/**
	 * @param args
	 */

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		PersonTutor.getPerson().say();
		
		
	}

}
class PersonTutor {

	private PersonTutor(){}
	
	public static PersonTutor getPerson(){
		return new PersonTutor();
	}
	
	public void say() {
		System.out.println("HH");
	}
}
