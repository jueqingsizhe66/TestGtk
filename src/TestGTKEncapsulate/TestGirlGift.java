
package TestGTKEncapsulate;




import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGSourceFunc;

import GTKEncapsulate.*;

/**
 * @author    Ҷ����
 * @time      2015��2��10������1:17:57
 * @version   TestGTKEncapsulateTestGrilGift V1.0 Ů���ѱ�������
 */
public class TestGirlGift extends OOWindow
{

	/**
	 * @param args
	 */
	private OOImage oim ;
	private OOTextView otv;
	private OOMusic om ;
	public TestGirlGift() 
	{
		OOBox ob =new OOBox(false);
		ob.show();
		oim = new OOImage();
		oim.setResourceImage("yumufeng.jpg");
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
		
		final StringBuilder love = new StringBuilder();
		// +��֮�󶼻����bug
		final String Lovewords = "��Ȼ:\r\nлл����ҵ����йػ������⣬��������Щ����������ʱ������ů����飬������ʼ��ǿ��ᶨ!\r\n"
	            +"��Ҫ�����Ϊ���������Ҹ���Ů��,������Ϊ��һ�����۵�����,��λ��Ƹ�,��������Ϊ��ĬĬ��õİ�!\r\n"
	            +"����,˵����Щ��������ô����ȴ����ô����,�ⶼ������ô��ʱ�����������ĵ׵Ļ���!��һ��ֻ����Ϊ������Ҫ����"
	            +"���������׸������:����˰���!\r\n����ˣ�Ҷ���� ";
		love.append(Lovewords);
	//��������������أ�����һֱ������������
		GTK.g_timeout_add(100, new IGSourceFunc()
		{
			
			@Override
			public boolean execute(Object userdata)
			{
				// TODO Auto-generated method stub
				int len = otv.getText().length();
				char ch = love.charAt(len);
				otv.insertTextAtEnd(Character.toString(ch));
				if(otv.getText().length() == love.length()-1)
				{
					//om.close();
					om = new OOMusic("����˰���.mp3",true);
					om.playRepeat();
					return true;
				}else if(otv.getText().length() == love.length())
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
		//��ʱ������  ���ӱ���   ֻ��������д�ֵ�ʱ��ר��д�֣����ű�������
/*		om = new OOMusic("ISurrender.mp3",true);
		om.playOnce();*/
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		TestGirlGift tgg = new TestGirlGift();
		tgg.setTitle("Ů���������֮��������");
		tgg.show();
		tgg.setExitAfterDestroy(true);
		GTK.gtk_main();
	}

}