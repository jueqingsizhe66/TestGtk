/**
 * ���ͣ�
 */
package TestRegex;

import java.util.regex.*;
/**
 * @author    Ҷ����
 * @time      2015��3��17������9:27:15
 * @version   TestRegexTestStringAndPattern V1.0
 * ���ܣ� �����������ʽ 
                ���裺 �ַ���
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestStringAndPattern
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		//�ҵ�˫Ԫ�ַ� ��������
		String testString = "I Love you oooo fdsf ooo gfff fly with sky";
		System.out.println(testString.indexOf('o'));
		System.out.println(testString.lastIndexOf('o'));
		System.out.println(testString.replaceAll("([a-z])\\1+", "$1"));
		
		Pattern p1 = Pattern.compile("([a-z])\\1+");
		Matcher m1 = p1.matcher(testString);
		while(m1.find())
		{
			System.out.println(m1.group());
			
		}
		System.out.println(m1.replaceAll("first"));
	}

}