
package GTKEncapsulate;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午4:38:57
 * @version   GTKEncapsulateOOFileChooser V1.0 把类标记为abstract，
 * 				这样的类无法被直接实例化（new），这就叫抽象类。
 *  V2.0   修改了run方法
 *  V3.0   抽象方法的返回值从void 该到了String[]
 *  v4.0   修改OOFileChooser继承类为来自OODailog
 *         并把public int run()方法提到OODialog中
 *  V5.0   利用OOFileAction枚举类 重新编写了OOFIleChooser
 *  V6.0   创建了setDoOverWrittenConfirmatio函数，并在封装构造函数中
 *         运用了一个判断技巧，增加了 getFileName()  setCurrentFilename
 *         createFileFolder 
 *  V7.0   从抽象类转变到非抽象类
 *         增加了一个文件接口 OOFileChooserInterface 供用户使用
 *         增加了打开文件和保存文件的默认处理方式
 */
public  class OOFileChooser extends OODialog
{
	private static Filter fi ;
	//private static int response;  //对话框的响应
	/**
	 * 
	 * @param title     有参构造函数的窗口标题
	 * @param action    有参构造函数的 行为，打开、保存。。
	 * @param buttonText  有参构造函数的 按钮的标签名字 
	 *  创建一个文件对话框，对话框一定要run一下。。。。。 并且一定要摧毁它
	 */
/*	public OOFileChooser(String title, int action, String buttonText)
	{
		setId(GTK.gtk_file_chooser_dialog_new(title, 0, action, buttonText));
	}*/
	public OOFileChooser(String title, OOFileAction action, String buttonText)
	{
		setId(GTK.gtk_file_chooser_dialog_new(title, 0, action.getValue(), buttonText));
		//一个封装技巧。
		if(action ==OOFileAction.SAVE)
		{
			setDoOverWrittenConfirmation(true);
		}
		
	}
	
	/**
	 *   无参构造函数  默认为打开文件对话框说
	 */
	public OOFileChooser()
	{
		this("打开文件", OOFileAction.OPEN, "打开");
	}
	/**
	 *   设置文件打开窗口的多选
	 */
	public void setMultipleSelect() 
	{
		GTK.gtk_file_chooser_set_select_multiple(this.getId(), true);
	}
	/**
	 * 
	 * @param do_overwrite_confirmation  在save模式下，保存时需要判断是否覆盖
	 */
	public void setDoOverWrittenConfirmation(boolean do_overwrite_confirmation)
	{
		GTK.gtk_file_chooser_set_do_overwrite_confirmation(this.getId(), do_overwrite_confirmation);
	}
	/**
	 * 
	 * @param text  设置当前选择的文件名为text
	 */
	public void setCurrentFilename(String text)
	{
		GTK.gtk_file_chooser_set_current_name(this.getId(), text);
	}
	
	/**
	 * 
	 * @param create_folders  布尔值 用于判断是否创建。。。不清楚这边。
	 */
	public void createFileFolder(boolean create_folders)
	{
		GTK.gtk_file_chooser_set_create_folders(this.getId(), create_folders);
	}
	/**
	 *   创建一个过滤器
	 */
	public void createFilter()
	{
		fi = new Filter();
	}
	/**
	 * 
	 * @param text     设置过滤器的名字
	 */
	public void nameFilter(String text)
	{
		fi.setFilterName(text);
	}
	/**
	 * 
	 * @param pattern   增加过滤器的后缀
	 */
	public void editFilter(String pattern)
	{
		fi.addFilterPattern(pattern);
	}
	
	public void finishFilter()
	{
		GTK.gtk_file_chooser_add_filter(this.getId(), fi.getId());
	}
	

	
	
	public String[]  getFileNames()
	{
		return GTK.gtk_file_chooser_get_filenames(this.getId());
	}
	
	public String  getFileName()
	{
		return GTK.gtk_file_chooser_get_filename(this.getId());
	}
	/**
	 *   一个抽象方法 ，要求继承者去实现它
	 *   多了一个
	 *   
	 *   V 可不可以用接口来实现，估计也是一样的下过
	 */
	/**
	 * 
	 * @param ofci  开放了一个OOFileChooserInterface接口，
	 *              用于处理打开文件和保存文件，需要进行的额外操作
	 */
	public void processResponse(final OOFileChooserInterface ofci)
	{
		ofci.doit();
	}
	/**
	 *   默认处理打开的函数的方式，得到文件！！ 显示到textview和treeview交给用户去做
	 *   而processSave则交给OOFileChooser的processSave去做
	 *   新版本改用OOResponseTyep的类型进行判断
	 */
	public String[] processOpen()
	{
		String[] filenames = null;
		// TODO Auto-generated method stub
		OOResponseType ret = this.run();
		//int ret = this.run().getValue();
		if(ret == OOResponseType.OK) 
		{
			filenames = GTK.gtk_file_chooser_get_filenames(this.getId());
			for(int i = 0 ; i< filenames.length ; i++)
			{
				
				System.out.println("选中文件名"+i+": "+filenames[i]);
			}
			//GTK.gtk_widget_destroy(this.getId()); //必须需要！！否则报错
			this.destroy();
		}else
		{
			//GTK.gtk_widget_destroy(this.getId());
			this.destroy();
		}
		return filenames;
	}
	/**
	 *   默认处理保存对话框的方式   基本上默认是大部分采用的方式，
	 *   保存文件的打开文件流有OOFileChooser去做
	 *   
	 *   暂时直接接受textview的文本信息，类似的可以使用OOTreeview
	 *   
	 *   新版本改用OOResponseTyep的类型进行判断
	 */
	public void processSave(OOTextView ootv)
	{
		//GTK.gtk_file_chooser_set_do_overwrite_confirmation(this.getId(), true);
		// TODO Auto-generated method stub
		System.out.println("已进入save");
		//SaveOneFile(filename,textview);
		OOResponseType ret = this.run();
		//int ret = GTK.gtk_dialog_run(this.getId());
		if(ret == OOResponseType.CANCEL)
		{
			this.destroy();
			//GTK.gtk_widget_destroy(this.getId());
		}else 
		{
			String filename =this.getFileName() ;
			//保存文件
			SaveOneFile(filename,ootv);
			System.out.println(filename);
			//GTK.gtk_widget_destroy(this.getId());
			this.destroy();
			
		}
	}
	
	
	public static void SaveOneFile(String filename, OOTextView ootv)
	{
		try
		(
			OutputStream os = new FileOutputStream(filename);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
		)
		{
			
			String  temp= filename.substring(filename.lastIndexOf('\\')+1);
			System.out.println(temp);
			
			/*int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
			//这是一个空的iter，需要用textBuffer进行赋值
			int textIter = GTK.gtk_text_iter_new();  
			
			GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);
			// 或者textview的textBuffer的末尾！
			String tempText = GTK.gtk_text_buffer_get_text(textBuffer);*/
			
			//while(tempText != null) // -1读取完毕
			{
				//InsertStringToTextViewFunction(textview,fileToFile.toString());
				bw.write(ootv.getText());
				bw.newLine();
			//	GTK.gtk_text_iter_backward_char(textIter);
			//	tempText = GTK.gtk_text_buffer_get_text(textBuffer);
			}
		}
		catch(IOException e)
		{
			System.out.println("文件读入异常");
		}
		
	}
	
	
/*	public abstract void processResponse1();  //用于保存
	public abstract String[] processResponse(); //用于打开
*/	/**
	 * 
	 * @author    叶昭良
	 * @time      2015年2月5日下午5:07:17
	 * @version   GTKEncapsulateFilter V1.0
	 */
	class Filter extends OOWidget
	{
		public Filter()
		{
			setId(GTK.gtk_file_filter_new());
		}
		/**
		 * 
		 * @param text   设置过滤器的名字
		 */
		public void setFilterName(String text)
		{
			GTK.gtk_file_filter_set_name(this.getId(), text);
		}
		/**
		 * 
		 * @param pattern  设置过滤器的过滤类型
		 */
		public void addFilterPattern(String pattern)
		{
			GTK.gtk_file_filter_add_pattern(this.getId(), pattern);
		}
		
		
	}
}
