/**
 * 
 */
package GTKEncapsulate;

import TestGTKEncapsulate.TestGirlGift;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGSourceFunc;

/**
 * @author    叶昭良
 * @time      2015年2月10日上午11:28:17
 * @version   GTKEncapsulateLoveCard V1.0
 *                                未实现   V2.0 利用StringBuffer改进程序，（当然也可以使用StringBuilder
 *                                真尼玛奇怪  jpg不能在导出的jar包使用，报无法加载资源（资源存在）  
 *                                  有时候还有memory error 我的机子问题，内存不足，可见这个程序不好
 *                                  运行一次 必须加速一次360或者qq管家
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
		
		final String Lovewords = "欣然:\r\n谢谢你给我的所有关怀和理解，尤其是那些孤立无助的时刻你温暖的陪伴，它让我始终强大坚定!\r\n"
	            +"我要让你成为世界上最幸福的女人,不是因为这一生积累的名望,地位与财富,而仅仅因为我默默恒久的爱!\r\n"
	            +"今天,说出这些话语是那么艰难却又那么快乐,这都是我这么长时间以来埋在心底的话语!这一切只是因为下面我要唱给\r\n你听的这首歌的名字：我如此爱你!\r\n落款人：叶昭良 ";
	//这个过程是慢慢地，但是一直持续的在运行
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
				//	om = new OOMusic("C://Users//XinRan//workspace//GTK1//src//我如此爱你.mp3");
					om = new OOMusic("我如此爱你.mp3");
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
			OOMusic om = new OOMusic("我如此爱你.mp3",true);
			om.playRepeat();
		}*/
		//不能放在里面。。。
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
