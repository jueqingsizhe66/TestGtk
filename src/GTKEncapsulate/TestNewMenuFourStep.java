
package GTKEncapsulate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��6������10:09:33
 * @version   GTKEncapsulateTestNewMenuFourStep V1.0
 * 
 * �漰���ĸ���       OOMenuBar.java
 *               OOSubMenu.java
 *               OOSingleMenu.java
 *               OOMenuVetetable.menu   ���δ����ĸ��༴��
 */
public class TestNewMenuFourStep
{

	/**
	 * @param args
	 */
	static OOTextView ootv =  null;
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//��ʼ��������Ҫ��
				GTK.gtk_init();
				//����һ��OOWindow���������Ѿ�������  
				OOWindow window = new OOWindow();
				window.setTitle("�����������");
				window.setExitAfterDestroy(true);
				window.show();
				
				
				//�������ֶ���
				OOGrid grid = new OOGrid();
				grid.show();
				//���ӵ�window����
				window.add(grid);
				
				//����һ��textview ����
				ootv = new OOTextView();
				ootv.setType();
				ootv.show();
				ootv.addScrollBar(grid, 2, 500, 300);
				//����һ�����menubar
				OOMenuBar ombApple = new OOMenuBar();
				//�����ڶ���ģ������кܶ����
				OOSubMenu osmApple = new OOSubMenu("File");
				
				//����������ģ������кܶ����
				OOSingleMenu menuApple = new OOSingleMenu();
				
				//������������,�ı���returnѡ�� ʹ�ÿ��Է���Vegetable���󣬴Ӷ������¼�����
				//�����Ѿ����ڲ�show�� ���Բ���Ҫshow
				//�������Ĵ�ģ������кܶ����
				OOMenuVegetable vgNew = new OOMenuVegetable("�½�");
				OOMenuVegetable vgOpen = new OOMenuVegetable("��");
				OOMenuVegetable vgSave = new OOMenuVegetable("����");
				OOMenuVegetable vgQuit = new OOMenuVegetable("�˳�");
				vgOpen.addActivateListener(new IGCallBack()
				{
					
					@Override
					public void execute(int instance, int eventData, Object object)
					{
						youOpenfile(ootv);
						
					}
				});
				vgSave.addActivateListener(new IGCallBack()
				{
					
					@Override
					public void execute(int instance, int eventData, Object object)
					{
						youSavefile();
						
					}
				});
				vgQuit.addActivateListener(new IGCallBack()
				{
					
					@Override
					public void execute(int instance, int eventData, Object object)
					{
						// TODO Auto-generated method stub
						GTK.gtk_main_quit();
					}
				});
				//���ӵ��Ĵ󵽵�����
				menuApple.addVegetable(vgNew);
				menuApple.addVegetable(vgOpen);
				menuApple.addVegetable(vgSave);
				menuApple.addVegetable(vgQuit);
				
				//���ӵ����󵽵ڶ���  ���õ������
				osmApple.fillSubMenu(menuApple);
				//���ӵڶ��󵽵�һ��
				ombApple.addSubMenuToBar(osmApple);
				ombApple.show();
				
				grid.add(ombApple.getId(), 0);
				
				
				//����Ĳ��ֶ��ǹ���menu��������һ������
				OOToolBar otbApple = new OOToolBar();
				//���������� ����̫С��
				otbApple.setWidgetSize(300, 20);
				OOToolBar.ToolButton tbApple = otbApple.addTool("�½�", GTK.GTK_STOCK_NEW, 0);
				OOToolBar.ToolButton tbBanana =otbApple.addTool("��", GTK.GTK_STOCK_OPEN, 1);
				OOToolBar.ToolButton tbOrange =otbApple.addTool("����", GTK.GTK_STOCK_SAVE, 2);
				tbBanana.addClickedListener(new IGCallBack()
				{
					
					@Override
					public void execute(int instance, int eventData, Object object)
					{
						// TODO Auto-generated method stub
						youOpenfile(ootv);
					}
				});
				tbOrange.addClickedListener(new IGCallBack()
				{
					
					@Override
					public void execute(int instance, int eventData, Object object)
					{
						youSavefile();
					}
				});
				otbApple.show();
				grid.add(otbApple.getId(),1);
				//����ѭ��
				GTK.gtk_main();
	}
	
	public static void youOpenfile(OOTextView ootv)
	{
		OpenFileNew ofApple = new OpenFileNew();
		ofApple.setMultipleSelect();
		ofApple.createFilter();
		ofApple.nameFilter("�ı��ļ�");
		ofApple.editFilter("*.txt");
		ofApple.editFilter("*.java");
		//��filter�Ĳ�������򿪶Ի�����
		ofApple.finishFilter();
		String[] filenames = ofApple.processResponse();
		showAllFiles(filenames,  ootv);
	}
	public static void youSavefile()
	{
		SaveFileNew ofBanana = new SaveFileNew("�����ļ�",GTK.GTK_FILE_CHOOSER_ACTION_SAVE,"����");
		//��filter�Ĳ�������򿪶Ի�����
		
		ofBanana.processResponse();
	}
	
	public static void showAllFiles(String[] filenames,OOTextView otv)
	{
		for(int i = 0 ; i < filenames.length; i++)
		{
			showOneFile(filenames[i],otv);
		}
	}
	
	public static void showOneFile(String filename, OOTextView otv)
	{
		try
		(
			InputStream is = new FileInputStream(filename);
			InputStreamReader osr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(osr);
		)
		{
			
			String  temp= filename.substring(filename.lastIndexOf('\\')+1);
			System.out.println(temp);
			otv.insertTextAtEnd("******************\n�����ļ�Ϊ"+filename+"\n******************\n\n"+temp+" �ļ��������£�\n+---------------------------------------------------------------------------+\n");
			String content = null;
			while((content = br.readLine())!=null) // -1��ȡ���
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				otv.insertTextAtEnd(new String(content));
				
			}
			otv.insertTextAtEnd("\n+---------------------------------------------------------------------------+\n******************\n�ļ�"+filename+"��ȡ����\n******************\n");
		}
		catch(IOException e)
		{
			System.out.println("�ļ������쳣");
		}
		
	}

}
//ȡ��OpenFile��һ����������ֹ�ظ�
class OpenFileNew extends OOFileChooser
{

	@Override
	public  String[] processResponse()
	{
		String[] filenames = null;
		// TODO Auto-generated method stub
		int ret = this.run();
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
}

class SaveFileNew extends OOFileChooser
{
	public SaveFileNew(String title, int action, String buttonText)
	{
		setId(GTK.gtk_file_chooser_dialog_new(title, 0, action, buttonText));
	}
	@Override
	public String[] processResponse()
	{
		String[] filenames = null;
		GTK.gtk_file_chooser_set_do_overwrite_confirmation(this.getId(), true);
		// TODO Auto-generated method stub
		System.out.println("�ѽ���save");
		//SaveOneFile(filename,textview);
		int ret = GTK.gtk_dialog_run(this.getId());
		if(ret == GTK.GTK_RESPONSE_CANCEL)
		{
			GTK.gtk_widget_destroy(this.getId());
		}else 
		{
			String filename = GTK.gtk_file_chooser_get_filename(this.getId());
			filenames[0] = filename;
			System.out.println(filename);
			GTK.gtk_widget_destroy(this.getId());
			return  filenames;
		}
		return filenames;
		
	}
}