/**
 * 
 */
package TestGTKEncapsulate;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

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
 * @author    Ҷ����
 * @time      2015��2��9������8:20:47
 * @version   TestGTKEncapsulateTestXiaoBingWindow V1.0
 * 							V2.0  �����е�equalsIgnoreCase ��Ϊcontains ֻ��Ҫ�ַ����������ɣ�
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
		//oeApple.setText("������������ı���");
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
		
		//���ü���
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
					otvApple.insertTextAtEnd("��˵:"+text+"\n");
					oeApple.setText("");
					//text.contains
					if(text.contains("��ð�"))
					{
						otvApple.insertTextAtEnd("С��˵:"+"�����ã�\n");
					}else if(text.contains("������"))
					{
						otvApple.insertTextAtEnd("С��˵:"+"����������\n");
					}else if(text.contains("���꼸��"))
					{
						otvApple.insertTextAtEnd("С��˵:"+"Ů���ӵ����䲻�������߱���\n");
					}else if(text.contains("�����еĻ���Ů��"))
					{
						otvApple.insertTextAtEnd("Little Bing Said:"+"You guess\n");
					}else if(text.contains("������ȥ�������"))
					{
						otvApple.insertTextAtEnd("Little Bing Said:"+"ȥ�����棿\n");
					}else if(text.contains("select"))
					{
						OpenFile ofApple = new OpenFile();
						ofApple.setMultipleSelect();
						ofApple.createFilter();
						ofApple.nameFilter("�����ļ�");
						ofApple.editFilter("*.MP3");
						ofApple.editFilter("*.wav");
						//��filter�Ĳ�������򿪶Ի�����
						ofApple.finishFilter();
						String[] filenames = ofApple.processResponse();
						//��src�ļ�����
						om = new OOMusic(filenames[0]);
						om.playOnce();
					}else if(text.contains("sing"))
					{
						//��src�ļ�����
						om = new OOMusic("breathless.mp3",false);
						om.playOnce();
					}else if(text.contains("pause"))
					{
						//��src�ļ�����
						
						om.pause();
					}else if(text.contains("resume"))
					{
						//��src�ļ�����
						
						om.pause();
					}else if(text.contains("close"))
					{
						//��src�ļ�����
						
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
					else if(text.contains("������ô��"))
					{
						otvApple.insertTextAtEnd("Little Bing Said:"+"����ô��������������������\n");	
					}else
					{
						otvApple.insertTextAtEnd("С��˵��û��������˵һ��\n");
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
//				System.out.println("��ǰ������ַ�"+enterCharacter+"��ascii��Ϊ:"+keycode+"\n���ҵ�����10������Ϊ"+value);
//				OOMessageDialog.showInfo("��ǰ������ַ�"+enterCharacter+"��ascii��Ϊ:"+keycode+"\n���ҵ�����10������Ϊ"+value, "ѧϰASCII��");
				//System.out.println("��ǰ������ַ�"+enterCharacter+"��ascii��Ϊ:"+keycode);
				//OOMessageDialog.showInfo("��ǰ������ַ�"+enterCharacter+"��ascii��Ϊ:"+keycode, "ѧϰASCII��");
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
	class OpenFile extends OOFileChooser
	{

		@Override
		public  String[] processResponse()
		{
			String[] filenames = null;
			// TODO Auto-generated method stub
			int ret = this.run().getValue();
			if(ret == GTK.GTK_RESPONSE_OK) 
			{
				filenames = GTK.gtk_file_chooser_get_filenames(this.getId());
				for(int i = 0 ; i< filenames.length ; i++)
				{
					
					System.out.println("ѡ���ļ���"+i+": "+filenames[i]);
				}
				GTK.gtk_widget_destroy(this.getId()); //������Ҫ�������򱨴�
			}else
			{
				GTK.gtk_widget_destroy(this.getId());
			}
			return filenames;
		}

		@Override
		public void processResponse1()
		{
			// TODO Auto-generated method stub
			
		}		
	}
}
