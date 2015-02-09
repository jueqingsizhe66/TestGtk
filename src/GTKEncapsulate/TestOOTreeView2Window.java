/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午1:33:31
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
		
		//设置布局
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
		
		//显示
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
					//System.out.println("selection's length== "+ selection.length+"当前行"+selection[i]);
					//values[i]  = otv.getliststore().getValue(selection[i], 1);
					OOMessageDialog.showInfo("选中的信息为"+otv.getliststore().getValue(selection[i], 1), "用户名");
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
		//TestOOTreeView2Window 的封装的责任是新建字段 添加数据 加入监听  main不负责这些事情，main只是显示
		TestOOTreeView2Window ttv2w = new TestOOTreeView2Window();
		ttv2w.setExitAfterDestroy(true);
		ttv2w.show();
		GTK.gtk_main();
	}

}
