
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午4:38:57
 * @version   GTKEncapsulateOOFileChooser V1.0 把类标记为abstract，这样的类无法被直接实例化（new）
 *                                     ，这就叫抽象类。
 *                                     V2.0   修改了run方法
 *                                     V3.0   抽象方法的返回值从void 该到了String[]
 */
public abstract class OOFileChooser extends OOWidget
{
	private static Filter fi ;
	private static int response;  //对话框的响应
	/**
	 * 
	 * @param title     有参构造函数的窗口标题
	 * @param action    有参构造函数的 行为，打开、保存。。
	 * @param buttonText  有参构造函数的 按钮的标签名字 
	 *  创建一个文件对话框，对话框一定要run一下。。。。。 并且一定要摧毁它
	 */
	public OOFileChooser(String title, int action, String buttonText)
	{
		setId(GTK.gtk_file_chooser_dialog_new(title, 0, action, buttonText));
	}
	
	/**
	 *   无参构造函数  默认为打开文件对话框说
	 */
	public OOFileChooser()
	{
		setId(GTK.gtk_file_chooser_dialog_new("打开文件", 0, GTK.GTK_FILE_CHOOSER_ACTION_OPEN,"打开"));
		
	}
	/**
	 *   设置文件打开窗口的多选
	 */
	public void setMultipleSelect() 
	{
		GTK.gtk_file_chooser_set_select_multiple(this.getId(), true);
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
	
	/**
	 *    在一切设置完毕后  必须要让他run起来，类似于线程的做法,并且一定要摧毁它 this.destroy..
	 */
	public  int run()
	{
		response =  GTK.gtk_dialog_run(this.getId());
		return response;
	}
	
	
	public String[]  getFileNames()
	{
		return GTK.gtk_file_chooser_get_filenames(this.getId());
	}
	
	/**
	 *   一个抽象方法 ，要求继承者去实现它
	 *   多了一个
	 */
	//public abstract void processResponse();
	public abstract String[] processResponse();
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
