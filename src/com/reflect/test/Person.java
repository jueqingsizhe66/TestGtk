/**
 *  1: һ��class��һ���Ӧ��������  �ֶ�  ���� ����
 *        Ҳ�����ֶη��� ����������  ���෴�� ���⻹�����������Constructor���캯��
 *        �ķ���
 *        
 *        �ֶη����� Field
 *        ����������Method
 *        ���캯����Ӧ Constructor
 *        �෴��  �� Class
 *        
 *        һ����Ҫ���ȹ���class����ſ��Ի��������������䣡�������ع�ϵ
 *        Ҳ����һ����ķ���ͨ�������ֶ� ���캯�� ���� ��������
 *  
 */
package com.reflect.test;

/**
 * @author    Ҷ����
 * @time      2015��3��3������3:19:03
 * @version   com.reflect.testPerson V1.0
 */
public class Person
{
	//
	private String Name;
	private int Age;
	
	//�޲ι��캯��
	public Person()
	{
		
	}
	//�вι��캯��  ���ڹ��캯��  ��Ӧ�ķ���ΪConstructor
	public Person(String Name, int Age)
	{
		this.Name = Name;
		this.Age = Age;
	}
	
	//��ͨ����
	public void sayHi()
	{
		System.out.println("����"+this.Name+",�ҵ�������"+this.Age+",�ܸ�����ʶ�㣡");
	}
	
	public void singSong(String musicName)
	{
		if(musicName.equals("�ú���"))
		{
			System.out.println("����������������ǲα���");
		}else
		{
			System.out.println("�Բ���̫���˲��ᳪ��");
		}
	}
}