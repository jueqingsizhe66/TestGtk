package TestGTKEncapsulate;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

import GTKEncapsulate.*;
/**
 * 
 * @author    叶昭良
 * @time      2015年2月6日下午8:13:45
 * @version   GTKEncapsulateTestMenuFileChooserAndToolbar V1.0
 * 							并进一步采用封装的OOWindow编写程序，由于继承了OOBin，所以具有add 一个控件的功能
 * 							由于OOWindow在构造函数中添加了destroy的事件监听，所以不需要再重写，但是现在的
 * 							OOWindow的对象还是可以添加destroy监听，要是可以不能再添加destroy就好了！！！
 * 				
 * 							另外发现了一个不对的地方，所有的控件现在都是可以弹出消息对话框，question对话框。
 * 
 * 这个测试出现在事件监听！还有bug，等到以后再次修改，估计是继承关系出现了问题
 *解决了这个问题，原来是因为menu的监听是通过activate而不是click
 *涉及到的文件      OOMenu.java （这是主要的类包含另外一种方法的OOMenuBar OOSubMenu OOSingleMenu)
 *              OOMenuVegetable.java （这是原先从OOMenu剥离出来的一个文件）
 *              
 *              V2.0  ToolButton 改为了OOToolButton  这样其他包才可以使用。
 */
public class TestMenuFileChooserAndToolbar
{
/*	public IGCallBack IGCQuit = new IGCallBack()
	{
		
		@Override
		public void execute(int instance, int eventData, Object object)
		{
			// TODO Auto-generated method stub
			GTK.gtk_main_quit();
		}
	};*/
	static OOTextView ootv =  null;
	public static void main(String[] args)
	{
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
		//创建menubar对象
		OOMenu omMenu = new OOMenu();
		omMenu.show();
		
		//创建垂直menubar（最终需要文件的下拉菜单 添加到menubar(水平的menubar)
		omMenu.createVerticalMenu("File");
		//创建单一的一盘菜
		omMenu.createMenu();
		
		//往菜盘中添菜,改变了return选项 使得可以返回Vegetable对象，从而进行事件监听
		//由于已经在内部show了 所以不需要show
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
				youSavefile(ootv);
				
			}
		});
		vgQuit.addActivateListener( new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				GTK.gtk_main_quit();
			}
		});
		omMenu.addMenuVegetable(vgNew);
		omMenu.addMenuVegetable(vgOpen);
		omMenu.addMenuVegetable(vgSave);
		omMenu.addMenuVegetable(vgQuit);
		
		//已经show了，唯一需要的是添加进网格
		omMenu.addVerticalMenuTOBar();
		
		grid.add(omMenu.getId(), 0);
		
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
				youSavefile(ootv);
			}
		});
		otbApple.show();
		grid.add(otbApple.getId(),1);
		
	
		
		GTK.gtk_main();
		
	}
	
	public static void youOpenfile(OOTextView ootv)
	{
		OOFileChooser ofApple = new OOFileChooser();
		ofApple.setMultipleSelect();
		ofApple.createFilter();
		ofApple.nameFilter("文本文件");
		ofApple.editFilter("*.txt");
		ofApple.editFilter("*.java");
		//把filter的操作放入打开对话框中
		ofApple.finishFilter();
		String[] filenames = ofApple.processOpen();
		showAllFiles(filenames,  ootv);
	}
	public static void youSavefile(OOTextView ootv)
	{
		OOFileChooser ofBanana = new OOFileChooser("保存文件",OOFileAction.SAVE,"保存");
		//把filter的操作放入打开对话框中
		
		ofBanana.processSave(ootv);
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


/*class OpenFile extends OOFileChooser
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

class SaveFile extends OOFileChooser
{
	private String[] filenames;
	public SaveFile(String title, int action, String buttonText)
	{
		setId(GTK.gtk_file_chooser_dialog_new(title, 0, action, buttonText));
	}
	@Override
	public void processResponse1()
	{
		filenames = null;
		GTK.gtk_file_chooser_set_do_overwrite_confirmation(this.getId(), true);
		// TODO Auto-generated method stub
		System.out.println("已进入save");
		//SaveOneFile(filename,textview);
		//int ret = GTK.gtk_dialog_run(this.getId());
		int ret = this.run().getValue();
		if(ret == GTK.GTK_RESPONSE_CANCEL)
		{
			GTK.gtk_widget_destroy(this.getId());
		}else 
		{
			String filename = GTK.gtk_file_chooser_get_filename(this.getId());
			filenames[1] = filename;
			System.out.println(filename);
			//GTK.gtk_widget_destroy(this.getId());
			this.destroy();
			
		}
		
		
	}
	@Override
	public String[] processResponse()
	{
		// TODO Auto-generated method stub
		return null;
	}
}*/