/**
 *   ͨ���ӿڵĶ�̬ ����ʾ��ͬ�豸ʵ��USB�Ķ�̬
 *    ��ͬ���豸�����Ա���ͬ�ĵ�����ʹ�ã�
 *    1 �� ���ȶ���һ�� usb interface
 *    2 : Ȼ���弸��ʵ��usb�ӿڵ��豸��
 *    3 : �õ���ʹ��usb�ӿ�  ����usb����
 *    4 �� ����һ�����Զ��󣬾Ϳ���ʹ�øö����ˣ� ���ĳ̨�����ϵ��豸��������
 *    		����Ҳ������Ҫͨ��������������ʵ���ˡ�
 */
package com.original.sort;

/**
 * @author    Ҷ����
 * @time      2015��2��27������1:22:53
 * @version   com.original.sortInterface_USB_Computer V1.0
 */
public class Interface_USB_Computer
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		computer c1 =  new computer();
		c1.Useusb(new camero());
		c1.Useusb(new phone());
		
	}

}


//�ӿ���� �ֲ���������static����  ��ȫ������    
//�ӿ����в��ܶԷ�������ʵ��  ����Ӳ�Թ涨  ����˵�ǳ���ĳ���  ��Ϊ�����໹�ǿ���ӵ�з�����ʵ��
//���ǽӿ�����һ�������к�����ʵ��
//�ӿڵ�ʵ��һ�����ڶ�Ӧ��Ӳ����  ����ʵ�����еķ��� ����ᱨ����
//�ӿ��������ǳ���������һ�����ܽ���ʵ����
//һ�����п���ʵ�ֶ���ӿ�  Ҳ�������ٶ���һ��interface  ����phone���ǿ��Ե�
//usb�ӿڿ϶���׼��������������  ������׼���ڷ����������
interface usb
{
	int a = 1; //����usb.a���Է��ʵ�a��ֵ Ĭ����static����
	//����usb����
	public void startup();
	//�ص�usb����
	public void stop();
}

class camero implements usb
{
	public void startup()
	{
		System.out.println("����camero  ���������ɹ�");
	}
	public void  stop()
	{
		System.out.println("����cameron ������ֹͣ����");
	}
}

class phone implements usb
{
	
	public void startup()
	{
		System.out.println("����phone  ���������ɹ�");
	}
	public void  stop()
	{
		System.out.println("����phone ������ֹͣ����");
	}
}

//���ֻ�  camero ���ǵ������ڵ�����  ������Ӧ������ô���õ������أ�
//�϶�����Ҫ׼��Ҫ���ӵĶ���  ��ξ���usb�ӿ�  Ҳ�������������Ǳ���ġ�
//���ö�̬�������ͬ��Ӳ����

class computer 
{
	public void Useusb(usb Usb)
	{
		Usb.startup();
		Usb.stop();
	}
}