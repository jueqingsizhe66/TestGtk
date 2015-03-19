/**
 * 解释：
 */
package TestNetwork;
import java.util.Scanner;
/**
 * @author    叶昭良
 * @time      2015年3月19日下午2:25:37
 * @version   TestNetworkTestHomewordWHOStandard V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestHomewordWHOStandard
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
        
        double weight = 0.0;
        double height = 0.0;
        double weightIndex = 0.0;
        System.out.println("Please input your weight (kg)");
        weight =sc.nextDouble();
        while(weight==0.0)
        {
            System.out.println("Please input your weight(m)");
            weight =sc.nextDouble();
        }
        System.out.println("Please input your height");
        height =sc.nextDouble();
        while(height==0.0)
        {
            System.out.println("Please input your height");
            height =sc.nextDouble();
        }

        weightIndex = weight / (height*height);
        if(weightIndex < 18.5)
        {
            System.out.println("Your weight is too low.We suggest you  eat some high fat ");
        }else if(weightIndex >=18.5 && weightIndex <24.9)
        {
            System.out.println("Your weight is Normal.We suggest you  keep health ");
        }else if(25 == weightIndex)
        {
            System.out.println("Your weight is a little  hight.We suggest you keep doing sports ");
        }else if(weightIndex > 25.0 && weightIndex < 29.9)
        {
            System.out.println("Your weight is a little fat.We suggest you  eat some fruits and vegetables and do  some sports ");
        }else if(weightIndex > 30.0 && weightIndex < 34.9)
        {
            System.out.println("Your weight is fat.We suggest you  do sports every day and reduce the fat injection ");
        }else if(weightIndex > 35.0 && weightIndex < 39.9)
        {
            System.out.println("Your weight is too fat.We suggest you watch doctor ");
        }else 
        {
            System.out.println("Your weight is super fat.We suggest you  do some surgery operation! ");
        }
	}

}
