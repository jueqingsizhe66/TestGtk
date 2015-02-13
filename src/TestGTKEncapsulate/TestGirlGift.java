
package TestGTKEncapsulate;




import java.io.IOException;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGSourceFunc;

import GTKEncapsulate.*;

/**
 * @author    叶昭良
 * @time      2015年2月10日上午1:17:57
 * @version   TestGTKEncapsulateTestGrilGift V1.0 女朋友表达神器
 */
public class TestGirlGift extends OOWindow
{

	/**
	 * @param args
	 */
	private OOImage oim =null;
	private OOTextView otv =null;;
	//private OOMusic om =null ;
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
		osb.setWidgetSize(300, 300);
		osb.add(otv);
		ob.addWidget(osb);
		this.add(ob);
		
		final StringBuilder love = new StringBuilder();
		// +号之后都会出现bug
		final String Lovewords = "欣然:\r\n谢谢你给我的所有关怀和理解，尤其是那些孤立无助的时刻你温暖的陪伴，它让我始终强大坚定!\r\n"
	            +"我要让你成为世界上最幸福的女人,不是因为这一生积累的名望,地位与财富,而仅仅因为我默默恒久的爱!\r\n"
	            +"今天,说出这些话语是那么艰难却又那N么快乐,这都是我这么长时间以来埋在心底的话语!这一切只是因为下面我要唱给"
	            +"你听的这首歌的名字:我如此爱你!\r\n落款人：叶昭良 ";
		love.append(Lovewords);
	//这个过程是慢慢地，但是一直持续的在运行
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
					try
					(
							OOMusic om = new OOMusic("我如此爱你.mp3",true);
					)
					{
							om.playRepeat();
					}
				
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
			OOMusic om = new OOMusic("我如此爱你.mp3",true);
			om.playRepeat();
		}*/
		//不能放在里面。。。
		//暂时有问题  添加报错   只好让他在写字的时候专心写字，不放背景音乐
/*		om = new OOMusic("ISurrender.mp3",true);
		om.playOnce();*/
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		TestGirlGift tgg = new TestGirlGift();
		tgg.setTitle("女神表达神器之爱的誓言");
		tgg.show();
		tgg.setExitAfterDestroy(true);
		GTK.gtk_main();
	}

}
