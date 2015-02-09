/**
 * 
 */
package TestGTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import GTKEncapsulate.*;
/**
 * @author    叶昭良
 * @time      2015年2月8日下午10:36:09
 * @version   GTKEncapsulateTestOOInputDialog V1.0
 */
public class TestOOInputDialog
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		
		OOInputDialog oidi = new OOInputDialog();
	//	oidi.setValue("请在这里输入文本");  因为这些信息的定义是在run里面的
		// 于是我们就把构建对话框放在构造函数即可
		oidi.setValue("请在这里输入文本");
		oidi.run();
		
		GTK.gtk_main();
	}

}
