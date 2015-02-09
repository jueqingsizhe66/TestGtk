
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午4:38:57
 * @version   GTKEncapsulateOOFileChooser V1.0 把类标记为abstract，这样的类无法被直接实例化（new）
 *                                     ，这就叫抽象类。
 *                                     V2.0   修改了run方法
 *                                     V3.0   抽象方法的返回值从void 该到了String[]
 *                                     v4.0   修改OOFileChooser继承类为来自OODailog
 *                                            并把public int run()方法提到OODialog中
 *                                     V5.0   利用OOFileAction枚举类 重新编写了OOFIleChooser
 *                                     V6.0   创建了setDoOverWrittenConfirmatio函数，并在封装构造函数中
 *                                            运用了一个判断技巧，增加了 getFileName()  setCurrentFilename
 *                                            createFileFolder 
 */
public abstract class OOFileChooser extends OODialog
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
	public abstract void processResponse1();  //用于保存
	public abstract String[] processResponse(); //用于打开
	/**
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
