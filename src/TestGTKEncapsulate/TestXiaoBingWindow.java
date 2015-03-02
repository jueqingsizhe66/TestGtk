package TestGTKEncapsulate;
/**
 *  回想起OOKeyCode用法！  还有注意public enum
 *  的用法！
 */


import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

import GTKEncapsulate.OOContainer;
import GTKEncapsulate.OODialog;
import GTKEncapsulate.OOEntry;
import GTKEncapsulate.OOFileChooser;
import GTKEncapsulate.OOGrid;
import GTKEncapsulate.OOImage;
import GTKEncapsulate.OOKeycode;
import GTKEncapsulate.OOMessageDialog;
import GTKEncapsulate.OOMusic;
import GTKEncapsulate.OOMusicMode;
import GTKEncapsulate.OOScrollBar;
import GTKEncapsulate.OOTextView;
import GTKEncapsulate.OOWindow;
//import GTKEncapsulate.OpenFile;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午8:20:47
 * @version   TestGTKEncapsulateTestXiaoBingWindow V1.0
 * 							V2.0  把所有的equalsIgnoreCase 改为contains 只需要字符串包含即可，
 */
public class TestXiaoBingWindow extends OOWindow
{

	/**
	 * @param args
	 */
	private  OOEntry oeApple ;
	private OOTextView otvApple;
	private OOImage oim;
	private OOMusic om;
	public TestXiaoBingWindow()
	{
		OOGrid grid = new OOGrid();
		oeApple = new OOEntry();
		oeApple.show();
		//oeApple.setText("请在这边输入文本：");
		otvApple = new OOTextView();
		otvApple.show();
		otvApple.setEditable(false);
		OOScrollBar osbApple = new OOScrollBar();
		osbApple.show();
		osbApple.setWidgetSize(300, 300);
		osbApple.add(otvApple);
		
		grid.add(osbApple, 0);
		grid.add(oeApple,1);
		grid.show();
		this.add(grid);
//		GTK.MCI_MODE_OPEN
		
		//设置监听
		oeApple.addKeyPressListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				int keycode = GTK.gdk_event_get_keycode(eventData);
				if(keycode == OOKeycode.ENTER.getKeycode())
				{
					
					String text = oeApple.getText();
					otvApple.insertTextAtEnd("你说:"+text+"\n");
					oeApple.setText("");
					//text.contains
					if(text.contains("你好啊"))
					{
						otvApple.insertTextAtEnd("小兵说:"+"好你妹！\n");
					}else if(text.contains("脱衣舞"))
					{
						otvApple.insertTextAtEnd("小兵说:"+"不跟你玩了\n");
					}else if(text.contains("今年几岁"))
					{
						otvApple.insertTextAtEnd("小兵说:"+"女孩子的年龄不能随便告诉别人\n");
					}else if(text.contains("你是男的还是女的"))
					{
						otvApple.insertTextAtEnd("Little Bing Said:"+"You guess\n");
					}else if(text.contains("让我们去外面玩吧"))
					{
						otvApple.insertTextAtEnd("Little Bing Said:"+"去哪里玩？\n");
					}else if(text.contains("select"))
					{
						OOFileChooser ofApple = new OOFileChooser();
						ofApple.setMultipleSelect();
						ofApple.createFilter();
						ofApple.nameFilter("音乐文件");
						ofApple.editFilter("*.MP3");
						ofApple.editFilter("*.wav");
						//把filter的操作放入打开对话框中
						ofApple.finishFilter();
						String[] filenames = ofApple.processOpen();
						//在src文件夹下
						om = new OOMusic(filenames[0]);
						om.playRepeat();
					}else if(text.contains("breath"))
					{
						//在src文件夹下
						if(null != om)
						{
							om.close();
						}
						om = new OOMusic("breathless.mp3",false);
						om.playRepeat();
					}else if(text.contains("TwoDay"))
					{
						//在src文件夹下
						if(null != om)
						{
							om.close();
						}
						om = new OOMusic("两天.mp3",false);
						om.playRepeat();
					}else if(text.contains("wait"))
					{
						//在src文件夹下
						if(null != om)
						{
							om.close();
						}
						om = new OOMusic("RightHereWaiting.mp3",false);
						om.playRepeat();
					}else if(text.contains("surrender"))
					{
						//在src文件夹下
						if(null != om)
						{
							om.close();
						}
						om = new OOMusic("ISurrender.mp3",false);
						om.playRepeat();
					}else if(text.contains("love"))
					{
						//在src文件夹下
						if(null != om)
						{
							om.close();
						}
						om = new OOMusic("我如此爱你.mp3",false);
						om.playRepeat();
					}else if(text.contains("Some"))
					{
						//在src文件夹下
						if(null != om)
						{
							om.close();
						}
						om = new OOMusic("Somewhere.mp3",false);
						om.playRepeat();
					}else if(text.contains("pause"))
					{
						//在src文件夹下
						
						om.pause();
					}else if(text.contains("resume"))
					{
						//在src文件夹下
						
						om.pause();
					}else if(text.contains("close"))
					{
						//在src文件夹下
						
						om.close();
					}else if(text.contains("show"))
					{
						OODialog od = new OODialog();
						OOContainer oct = od.createContentArea();
						oim = new OOImage();
						oim.setResourceImage("hunsha.jpg");
						oim.setWidgetSize(200, 200);
						oim.show();
						oct.add(oim);
						od.run();
						od.destroy();
					}
					else if(text.contains("北京怎么样"))
					{
						otvApple.insertTextAtEnd("Little Bing Said:"+"不怎么样，先这样，改天再聊\n");	
					}else
					{
						otvApple.insertTextAtEnd("小兵说：没听见，再说一遍\n");
					}
				}
			//				System.out.println(OOKeycode.parseOOKeycode(keycode).getKeyName());
			}
			});
/*GTK.g_signal_connect(oeApple.getId(), "key-press-event", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				int keycode = GTK.gdk_event_get_keycode(eventData);
				
//				System.out.println(keycode);
				System.out.println(OOKeycode.parseOOKeycode(keycode).getKeyName());
				//int value = GTK.gdk_event_get_keyval(eventData);
				//String enterCharacterString= String.valueOf(keycode);
				//System.out.println(enterCharacterString);
				//char enterCharacter = enterCharacterString.charAt(0);
				//String text = oeApple.getText();
				//char lastchar = text.charAt(text.length()-1);
				//System.out.println(text+"text length="+text.length());
//				System.out.println("当前输入的字符"+enterCharacter+"的ascii码为:"+keycode+"\n并且的他的10进制数为"+value);
//				OOMessageDialog.showInfo("当前输入的字符"+enterCharacter+"的ascii码为:"+keycode+"\n并且的他的10进制数为"+value, "学习ASCII码");
				//System.out.println("当前输入的字符"+enterCharacter+"的ascii码为:"+keycode);
				//OOMessageDialog.showInfo("当前输入的字符"+enterCharacter+"的ascii码为:"+keycode, "学习ASCII码");
			}
		}, null);*/
}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		TestXiaoBingWindow txbw = new TestXiaoBingWindow();
		txbw.setExitAfterDestroy(true);
		txbw.show();
		GTK.gtk_main();
	}
}

