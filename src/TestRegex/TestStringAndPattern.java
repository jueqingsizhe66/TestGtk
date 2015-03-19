/**
 * 解释：
 */
package TestRegex;

import java.util.regex.*;
/**
 * @author    叶昭良
 * @time      2015年3月17日下午9:27:15
 * @version   TestRegexTestStringAndPattern V1.0
 * 功能： 测试正则表达式 
                步骤： 字符串
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestStringAndPattern
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		//找到双元字符 或者以上
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
