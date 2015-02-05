package GTKEncapsulate;

/**
 * @author    Ҷ����
 * @time      2015��2��4������10:23:29
 * @version   GTKEncapsulateOORGB V1.0   ��Ҫ�Ƕ�����ɫֵ�ķ�װ��
 *                                       ֱ��ʹ��OORGB.BLACK����  (0,0,0).
 *                                       ��ɫһ�����������޸ģ�û��set���
 */
public class OORGB
{

	/**
	 * @param args
	 */
	//һ����ı�������˽�е�
	private double red; //��ɫֵһ����0-1֮�䣬������double����
	private double green;
	private double blue;
	
	/**
	 * 
	 * @param red     ���캯������ɫ��ɫֵ
	 * @param green   ���캯������ɫ��ɫֵ
	 * @param blue    ���캯������ɫ��ɫֵ
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
	
	//����final���ͽ��ж��壬��Ϊ��ϣ�����޸�
	//����static���ж��壬��Ϊϣ��ͨ������ֱ�ӷ��ʣ������Ƕ�Ϸ�Ǹ�
	//���¶��������ڲ�Ҳ�ǿ���ֱ�ӷ��ʵ�ǰ���ࡣ������Ĺ��췽�����ص�ǰ�����
	final static OORGB RED   = new OORGB(1,0,0);
	final static OORGB GREEN = new OORGB(0,1,0);
	final static OORGB BLUE  = new OORGB(0,0,1);
	final static OORGB WHITE = new OORGB(1,1,1);
	final static OORGB BLACK = new OORGB(0,0,0);

	

}