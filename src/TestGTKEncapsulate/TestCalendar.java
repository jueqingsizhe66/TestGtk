/**
 * 
 */
package TestGTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

import GTKEncapsulate.*;

/**
 * @author    Ҷ����
 * @time      2015��2��10������2:48:55
 * @version   TestGTKEncapsulateTestCalendar V1.0
 */
public class TestCalendar extends OOWindow
{

	/**
	 * @param args
	 */
	private OOCalendar ocl ;
	private OOCalendarDialog ocld;
	private OOBox obApple;
	private OOButton btnclick;
	private OOCalendarButton ocbApple;
	public TestCalendar()
	{
		ocl = new OOCalendar();
		ocl.show();
		
		btnclick = new OOButton("���");
		btnclick.show();
		btnclick.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				ocld = new OOCalendarDialog();
				ocld.show();
			}
		});
		
		ocbApple = new OOCalendarButton("ѡ������");
		ocbApple.show();
		
		
		obApple = new OOBox();
		obApple.addWidget(ocl);
		obApple.addWidget(btnclick);
		obApple.addWidget(ocbApple);
		obApple.show();
		
		System.out.println(ocl.getDay()+ocl.getMonth()+ocl.getYear());
		this.add(obApple);
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		TestCalendar tc = new TestCalendar();
		tc.show();
		tc.setExitAfterDestroy(true);
		GTK.gtk_main();
	}

}