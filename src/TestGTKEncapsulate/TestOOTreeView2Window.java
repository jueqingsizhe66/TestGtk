/**
 * 
 */
package TestGTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import GTKEncapsulate.*;
/**
 * @author    Ҷ����
 * @time      2015��2��9������1:33:31
 * @version   GTKEncapsulateTestOOTreeView2Window V1.0
 */
public class TestOOTreeView2Window extends OOWindow
{

	/**
	 * @param args
	 */
	private static OOTreeView otv ;
	public TestOOTreeView2Window() 
	{
		
		//���ò���
		otv = new  OOTreeView();
		otv.addField("ID", 0);
		otv.addField("Name", 1);
		otv.addField("Age", 2);
		
		otv.createModel(3);
		otv.setFieldValue("001", "yezhao", "21");
		otv.setFieldValue("002", "huowa", "32");
		otv.setFieldValue("003", "yefeng", "31");
		OOScrollBar osb = new OOScrollBar();
		osb.show();
		osb.setWidgetSize(300, 300);
		osb.addView(otv);
		//otv.addScrollBar(grid, 0, 200, 200);
		
		//��ʾ
		otv.fillModel();
		otv.show();
		otv.setRecordColumn(2);
		otv.setResizeColumn(2);
		otv.setColumnSort(2);
		otv.setMultipleSelect();
		System.out.println(otv.getliststore().getValue(1, 1));
		System.out.println(otv.getliststore().getValue(2, 1));
		System.out.println(otv.getliststore().getValue(2, 2));
		otv.addDoubleClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				int[] selection = otv.getMultipleSelectRows();
				for(int i = 0; i < selection.length; i++)
				{
					//System.out.println("selection's length== "+ selection.length+"��ǰ��"+selection[i]);
					//values[i]  = otv.getliststore().getValue(selection[i], 1);
					OOMessageDialog.showInfo("ѡ�е���ϢΪ"+otv.getliststore().getValue(selection[i], 1), "�û���");
				//	System.out.println("selection["+i+"]"+selection[i]);
				}
			}
		});
		this.add(osb);
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		//TestOOTreeView2Window �ķ�װ���������½��ֶ� �������� �������  main��������Щ���飬mainֻ����ʾ
		TestOOTreeView2Window ttv2w = new TestOOTreeView2Window();
		ttv2w.setExitAfterDestroy(true);
		ttv2w.show();
		GTK.gtk_main();
	}

}