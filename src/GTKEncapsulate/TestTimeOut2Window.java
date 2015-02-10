/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGSourceFunc;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午3:07:17
 * @version   GTKEncapsulateTestTimeOut2Window V1.0
 */
public class TestTimeOut2Window extends OOWindow
{

	/**
	 * @param args
	 */
	private OOLabel labelApple;
	public TestTimeOut2Window()
	{
//		labelApple = new OOLabel("0");
		labelApple = new OOLabel("10");
		labelApple.show();
		this.add(labelApple);
		
		GTK.g_timeout_add(1000, new IGSourceFunc()
		{
			
			@Override
			public boolean execute(Object userdata)
			{
				// TODO Auto-generated method stub
				String txt = labelApple.getText();
				int apple = Integer.parseInt(txt);
				apple--;
				labelApple.setText(Integer.toString(apple));
				if(apple == 0)
				{
					OOMessageDialog.showInfo("时间到", "交了");
					return false;
				}
				return apple>=1;
			}
		}, null);
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		TestTimeOut2Window tt2w = new TestTimeOut2Window();
		tt2w.show();
		tt2w.setExitAfterDestroy(true);
		GTK.gtk_main();
	}

}
