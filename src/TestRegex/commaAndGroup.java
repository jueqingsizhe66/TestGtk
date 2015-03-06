/**
 * 
 */
package TestRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author    叶昭良
 * @time      2015年3月5日下午11:09:51
 * @version   TestRegexcommaAndGroup V1.0
 */
public class commaAndGroup
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		//字符串测试
		System.out.println("fdasfsd".indexOf('d'));
		
		System.out.println("fdasfsd".lastIndexOf('d'));
		
		String[] temp ="sdfddfdsgsdfddg".split("([a-z])\\1+");
		for(String p : temp)
		{
			System.out.println(p);
		}
		
		System.out.println("sdfddfdsgsdfddg".replaceAll("([a-z])\\1+","*"));
		
		System.out.println("sdfddfdsgsdfddg".replaceAll("([a-z])\\1+","$1"));
		
		//工作中的  正则获取方式
		System.out.println("2020".matches("\\d{3,10}"));
		
		String pat = "Welcome to china to have a pinny lunch ! Start the lesson";
		Pattern p = Pattern.compile("\\b[a-z]{5}\\b");
		Matcher m = p.matcher(pat);
		while(m.find())
		{
			System.out.println(m.group());
		}
	}

}
