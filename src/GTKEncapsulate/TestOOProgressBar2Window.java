/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.IGSourceFunc;

/**
 * @author    Ҷ����
 * @time      2015��2��9������3:13:29
 * @version   GTKEncapsulateTestOOProgressBar2Window V1.0
 */
public class TestOOProgressBar2Window extends OOWindow
{

	/**
	 * @param args
	 */
	private OOLabel  olApple = null;
	private OOProgressBar opbApple = null;
	private OOButton obApple = null;
	public  TestOOProgressBar2Window()
	{
		
		OOBox obtemp = new OOBox();
		obtemp.show();
		
		olApple = new OOLabel("0");
		olApple.show();
		obtemp.add(olApple);
		opbApple = new OOProgressBar();
		opbApple.setText("���Ե�");
		opbApple.showText("���Ժ�");
		opbApple.show();
		obtemp.add(opbApple);
		
		obApple = new OOButton("��װ");
		obApple.show();
		obtemp.add(obApple);
		obApple.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				GTK.g_timeout_add(1000, new IGSourceFunc()
				{
					
					@Override
					public boolean execute(Object userdata)
					{
						// TODO Auto-generated method stu
						String txt = olApple.getText();	
						
						int apple = Integer.parseInt(txt);
						
						apple++;
						olApple.setText(Integer.toString(apple));
						double banana = apple*0.1;
						if(banana== 1.0)
						{
							OOMessageDialog.showInfo("��װ���", "��װ");
						}
						opbApple.setProgress(banana);
						return banana<=1;
					}
				}, null);
			}
		});
		
		this.add(obtemp);
		
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		TestOOProgressBar2Window top2w = new TestOOProgressBar2Window();
		top2w.show();
		top2w.setExitAfterDestroy(true);
		GTK.gtk_main();
	}

}