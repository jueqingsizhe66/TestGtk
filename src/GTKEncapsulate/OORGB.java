package GTKEncapsulate;

/**
 * @author    叶昭良
 * @time      2015年2月4日上午10:23:29
 * @version   GTKEncapsulateOORGB V1.0   主要是对于颜色值的封装，
 *                                       直接使用OORGB.BLACK代替  (0,0,0).
 *                                       颜色一经创建不在修改，没有set语句
 *                                V2.0   把原先的RED  BLUE更改为public
 */
public class OORGB
{

	/**
	 * @param args
	 */
	//一般类的变量都是私有的
	private double red; //颜色值一般是0-1之间，所以是double类型
	private double green;
	private double blue;
	
	/**
	 * 
	 * @param red     构造函数的颜色红色值
	 * @param green   构造函数的颜色绿色值
	 * @param blue    构造函数的颜色蓝色值
	 */
	public OORGB(double red,double green,double blue)
	{
		this.red = red;
		this.green = green;
		this.blue =blue;
	}
	public double getRed()
	{
		return this.red;
	}
	public double getGreen()
	{
		return this.green;
	
	}
	public double getBlue()
	{
		return this.blue;
	}
	
	//采用final类型进行定义，因为不希望被修改
	//采用static进行定义，因为希望通过类名直接访问，而不是对戏那个
	//在新定义的类的内部也是可以直接访问当前的类。利用类的构造方法返回当前类对象。
	public final static OORGB RED   = new OORGB(1,0,0);
	public final static OORGB GREEN = new OORGB(0,1,0);
	public final static OORGB BLUE  = new OORGB(0,0,1);
	public final static OORGB WHITE = new OORGB(1,1,1);
	public final static OORGB BLACK = new OORGB(0,0,0);

	

}
