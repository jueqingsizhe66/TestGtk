/**
 * 解释：
 * 题目4：要求用户输入一个email地址，使用正则表达式检查用户输入的
 * 是否是合法的email地址，如果是合法的email地址，则把用户名和域名
 * 分别输出，比如用户输入yzk@rupeng.com，则输出“用户名为yzk，
 * 域名为rupeng.com”；
 */
package TestRegex;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author    叶昭良
 * @time      2015年3月21日下午1:54:30
 * @version   TestRegexTestRegexExam V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestRegexExam
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
		Scanner sc = new Scanner(System.in);
		String temp = null;
		while(true)
		{
			System.out.println("请输入一个邮箱地址,比如zhaoturkkey@163.com,退出敲exit|quit");
			temp= sc.nextLine();
			if(temp.equalsIgnoreCase("exit")||temp.equalsIgnoreCase("quit"))
			{
				break;
			}
			
			if(temp.matches("@"))
			{
				System.out.println("你输入的不是正确的邮箱地址！");
				continue;
			}
			
			int countAddr = 0;
			Pattern p1 = Pattern.compile("@");
			Matcher mp = p1.matcher(temp);
			while(mp.find())
			{
				countAddr++;
			}
			if(countAddr == 1)
			{
				String[] piles = temp.split("@");
				System.out.println("用户名为"+piles[0]+"  域名为"+piles[1]);
			}else
			{
				System.out.println("你输入的邮箱有多个@");
			}
			//matches要求整体匹配！
			System.out.println("sdfas|fsdfs".matches("fsdfs"));
			String likeType = "23";
			  String pattern = "[a-zA-Z0-9]*[" + likeType + "]{1}[a-zA-Z0-9]*";
			  String sourceStr = "adfjaslfj23ldfalsf";
			     System.out.println(sourceStr.matches(".*"+likeType+".*"));
		}
	}

}
