
package TestGTKEncapsulate;
import GTKEncapsulate.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月6日下午10:09:33
 * @version   GTKEncapsulateTestNewMenuFourStep V1.0
 * 
 * 涉及到四个类       OOMenuBar.java
 *               OOSubMenu.java
 *               OOSingleMenu.java
 *               OOMenuVetetable.menu   依次创建四个类即可
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
		//初始化还是需要的
				GTK.gtk_init();
				//创建一个OOWindow对象，其中已经包含了  
				OOWindow window = new OOWindow();
				window.setTitle("测试面向对象");
				window.setExitAfterDestroy(true);
				window.show();
				
				
				//创建布局对象
				OOGrid grid = new OOGrid();
				grid.show();
				//添加到window对象
				window.add(grid);
				
				//创建一个textview 界面
				ootv = new OOTextView();
				ootv.setType();
				ootv.show();
				ootv.addScrollBar(grid, 2, 500, 300);
				//创建一个大的menubar
				OOMenuBar ombApple = new OOMenuBar();
				//创建第二大的（可以有很多个）
				OOSubMenu osmApple = new OOSubMenu("File");
				
				//创建第三大的（可以有很多个）
				OOSingleMenu menuApple = new OOSingleMenu();
				
				//往菜盘中添菜,改变了return选项 使得可以返回Vegetable对象，从而进行事件监听
				//由于已经在内部show了 所以不需要show
				//创建第四大的（可以有很多个）
				OOMenuVegetable vgNew = new OOMenuVegetable("新建");
				OOMenuVegetable vgOpen = new OOMenuVegetable("打开");
				OOMenuVegetable vgSave = new OOMenuVegetable("保存");
				OOMenuVegetable vgQuit = new OOMenuVegetable("退出");
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
				//添加第四大到第三大
				menuApple.addVegetable(vgNew);
				menuApple.addVegetable(vgOpen);
				menuApple.addVegetable(vgSave);
				menuApple.addVegetable(vgQuit);
				
				//添加第三大到第二大  采用的是填充
				osmApple.fillSubMenu(menuApple);
				//添加第二大到第一大
				ombApple.addSubMenuToBar(osmApple);
				ombApple.show();
				
				grid.add(ombApple.getId(), 0);
				
				
				//上面的部分都是关于menu的现在做一个关于
				OOToolBar otbApple = new OOToolBar();
				//必须设置上 否则太小了
				otbApple.setWidgetSize(300, 20);
				OOToolButton tbApple = otbApple.addTool("新建", OOStockImage.NEW, 0);
				OOToolButton tbBanana =otbApple.addTool("打开", OOStockImage.OPEN, 1);
				OOToolButton tbOrange =otbApple.addTool("保存", OOStockImage.SAVE, 2);
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
				//启动循环
				GTK.gtk_main();
	}
	
	public static void youOpenfile(OOTextView ootv)
	{
		OpenFileNew ofApple = new OpenFileNew();
		ofApple.setMultipleSelect();
		ofApple.createFilter();
		ofApple.nameFilter("文本文件");
		ofApple.editFilter("*.txt");
		ofApple.editFilter("*.java");
		//把filter的操作放入打开对话框中
		ofApple.finishFilter();
		String[] filenames = ofApple.processResponse();
		showAllFiles(filenames,  ootv);
	}
	public static void youSavefile()
	{
		SaveFileNew ofBanana = new SaveFileNew("保存文件",GTK.GTK_FILE_CHOOSER_ACTION_SAVE,"保存");
		//把filter的操作放入打开对话框中
		
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
			otv.insertTextAtEnd("******************\n当期文件为"+filename+"\n******************\n\n"+temp+" 文件内容如下：\n+---------------------------------------------------------------------------+\n");
			String content = null;
			while((content = br.readLine())!=null) // -1读取完毕
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				otv.insertTextAtEnd(new String(content));
				
			}
			otv.insertTextAtEnd("\n+---------------------------------------------------------------------------+\n******************\n文件"+filename+"读取结束\n******************\n");
		}
		catch(IOException e)
		{
			System.out.println("文件读入异常");
		}
		
	}

}
//取和OpenFile不一样的类名防止重复
class OpenFileNew extends OOFileChooser
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
				
				System.out.println("选中文件名"+i+": "+filenames[i]);
			}
			GTK.gtk_widget_destroy(this.getId()); //必须需要！！否则报错
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

class SaveFileNew extends OOFileChooser
{
	public SaveFileNew(String title, int action, String buttonText)
	{
		setId(GTK.gtk_file_chooser_dialog_new(title, 0, action, buttonText));
	}
	@Override
	public String[] processResponse()
	{
		return null;	
	}
	@Override
	public void processResponse1()
	{
		// TODO Auto-generated method stub
		String[] filenames = null;
		GTK.gtk_file_chooser_set_do_overwrite_confirmation(this.getId(), true);
		// TODO Auto-generated method stub
		System.out.println("已进入save");
		//SaveOneFile(filename,textview);
		int ret = GTK.gtk_dialog_run(this.getId());
		if(ret == GTK.GTK_RESPONSE_CANCEL)
		{
			GTK.gtk_widget_destroy(this.getId());
		}else 
		{
			String filename = GTK.gtk_file_chooser_get_filename(this.getId());
			
			System.out.println(filename);
			GTK.gtk_widget_destroy(this.getId());
			
		}
		
	}
}
