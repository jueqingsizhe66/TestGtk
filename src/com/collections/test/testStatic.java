/**
 * 
 */
package com.collections.test;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author    Ҷ����
 * @time      2015��3��1������10:00:48
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
