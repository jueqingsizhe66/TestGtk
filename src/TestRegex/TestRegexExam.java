/**
 * ���ͣ�
 * ��Ŀ4��Ҫ���û�����һ��email��ַ��ʹ���������ʽ����û������
 * �Ƿ��ǺϷ���email��ַ������ǺϷ���email��ַ������û���������
 * �ֱ�����������û�����yzk@rupeng.com����������û���Ϊyzk��
 * ����Ϊrupeng.com����
 */
package TestRegex;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author    Ҷ����
 * @time      2015��3��21������1:54:30
 * @version   TestRegexTestRegexExam V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestRegexExam
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
		Scanner sc = new Scanner(System.in);
		String temp = null;
		while(true)
		{
			System.out.println("������һ�������ַ,����zhaoturkkey@163.com,�˳���exit|quit");
			temp= sc.nextLine();
			if(temp.equalsIgnoreCase("exit")||temp.equalsIgnoreCase("quit"))
			{
				break;
			}
			
			if(temp.matches("@"))
			{
				System.out.println("������Ĳ�����ȷ�������ַ��");
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
				System.out.println("�û���Ϊ"+piles[0]+"  ����Ϊ"+piles[1]);
			}else
			{
				System.out.println("������������ж��@");
			}
			//matchesҪ������ƥ�䣡
			System.out.println("sdfas|fsdfs".matches("fsdfs"));
			String likeType = "23";
			  String pattern = "[a-zA-Z0-9]*[" + likeType + "]{1}[a-zA-Z0-9]*";
			  String sourceStr = "adfjaslfj23ldfalsf";
			     System.out.println(sourceStr.matches(".*"+likeType+".*"));
		}
	}

}