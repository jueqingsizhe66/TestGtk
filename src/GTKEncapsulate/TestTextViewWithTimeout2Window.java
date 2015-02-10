/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGSourceFunc;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午3:36:53
 * @version   GTKEncapsulateTestTextViewWithTimeout2Window V1.0
 */
public class TestTextViewWithTimeout2Window extends OOWindow
{

	/**
	 * @param args
	 */
	private OOTextView otv; 
	private int i =0;
	public TestTextViewWithTimeout2Window()
	{
		otv= new OOTextView();
		otv.show();
		OOScrollBar osb = new OOScrollBar();
		osb.show();
		osb.setWidgetSize(300, 300);
		osb.addView(otv);
		GTK.g_timeout_add(500, new IGSourceFunc()
		{
			
			@Override
			public boolean execute(Object userdata)
			{
				// TODO Auto-generated method stub
				otv.insertTextAtEnd("hello\n");
				i++;
				return i<=5;
			}
		}, null);
		this.add(osb);
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		TestTextViewWithTimeout2Window ttvwt2wApple = new TestTextViewWithTimeout2Window();
		ttvwt2wApple.show();
		ttvwt2wApple.setExitAfterDestroy(true);
		GTK.gtk_main();
	}

}
