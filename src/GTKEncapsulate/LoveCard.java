/**
 * 
 */
package GTKEncapsulate;

import TestGTKEncapsulate.TestGirlGift;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGSourceFunc;

/**
 * @author    Ҷ����
 * @time      2015��2��10������11:28:17
 * @version   GTKEncapsulateLoveCard V1.0
 *                                δʵ��   V2.0 ����StringBuffer�Ľ����򣬣���ȻҲ����ʹ��StringBuilder
 *                                ���������  jpg�����ڵ�����jar��ʹ�ã����޷�������Դ����Դ���ڣ�  
 *                                  ��ʱ����memory error �ҵĻ������⣬�ڴ治�㣬�ɼ�������򲻺�
 *                                  ����һ�� �������һ��360����qq�ܼ�
 *                                  
 */
public class LoveCard extends OOWindow
{

	/**
	 * @param args
	 */
	private OOImage oim ;
	private OOTextView otv;
	private OOMusic om ;
	public LoveCard() 
	{
		OOBox ob =new OOBox(false);
		ob.show();
		oim = new OOImage();
		//oim.setStockImage(OOStockImage.NEW, OOStockSize.DIALOG);
		oim.setFileImage("C://Users//XinRan//workspace//GTK1//src//yumufeng.png");
	//	oim.setResourceImage("yumufeng.png");
		oim.show();
		ob.addWidget(oim);
		otv = new OOTextView();
		otv.show();
		otv.setType();
		OOScrollBar osb = new OOScrollBar();
		osb.show();
		osb.setWidgetSize(200, 220);
		osb.add(otv);
		ob.addWidget(osb);
		this.add(ob);
		
		final String Lovewords = "��Ȼ:\r\nлл����ҵ����йػ������⣬��������Щ����������ʱ������ů����飬������ʼ��ǿ��ᶨ!\r\n"
	            +"��Ҫ�����Ϊ���������Ҹ���Ů��,������Ϊ��һ�����۵�����,��λ��Ƹ�,��������Ϊ��ĬĬ��õİ�!\r\n"
	            +"����,˵����Щ��������ô����ȴ����ô����,�ⶼ������ô��ʱ�����������ĵ׵Ļ���!��һ��ֻ����Ϊ������Ҫ����\r\n���������׸�����֣�����˰���!\r\n����ˣ�Ҷ���� ";
	//��������������أ�����һֱ������������
		GTK.g_timeout_add(100, new IGSourceFunc()
		{
			
			@Override
			public boolean execute(Object userdata)
			{
				// TODO Auto-generated method stub
				int len = otv.getText().length();
				char ch = Lovewords.charAt(len);
				otv.insertTextAtEnd(Character.toString(ch));
				if(otv.getText().length() == Lovewords.length()-1)
				{
					//om.close();
				//	om = new OOMusic("C://Users//XinRan//workspace//GTK1//src//����˰���.mp3");
					om = new OOMusic("����˰���.mp3");
					om.playRepeat();
					return false;
				}else if(otv.getText().length() == Lovewords.length())
				{
					return false;
				}
				else
				{ 
					
					//System.out.println("2");
					return true;
				}

			}
		}, null);
		
		//System.out.println("134");
/*		if(otv.getText().length() == Lovewords.length()-1)
		{
			System.out.println("12");
			OOMusic om = new OOMusic("����˰���.mp3",true);
			om.playRepeat();
		}*/
		//���ܷ������档����
/*		om = new OOMusic("ISurrender.mp3",true);
		om.playOnce();*/
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		GTK.gtk_init();
		LoveCard tgg = new LoveCard();
		tgg.show();
		tgg.setExitAfterDestroy(true);
		GTK.gtk_main();
	}

}