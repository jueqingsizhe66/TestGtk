/**
 * 
 */
package TestGTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import GTKEncapsulate.*;
/**
 * @author    叶昭良
 * @time      2015年2月9日上午3:44:20
 * @version   GTKEncapsulateTestOOTreeView V1.0
 */
public class TestOOTreeView
{

	/**
	 * @param args
	 */
	private static OOEntry oeApple;
	private static OOEntry oeBanana;
	private static OOEntry oeOrange;
	private static OOTreeView otv;
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		OOWindow ow = new OOWindow();
		ow.show();
		ow.setExitAfterDestroy(true);
		
		//设置布局
		OOGrid  grid = new OOGrid();
		grid.show();
		ow.add(grid);
		
		//设置布局
		otv = new  OOTreeView();
		otv.addField("ID", 0);
		otv.addField("Name", 1);
		otv.addField("Age", 2);
		
		otv.createModel(3);
		otv.setFieldValue("001", "yezhao", "21");
		otv.setFieldValue("002", "huowa", "32");
		otv.setFieldValue("003", "yefeng", "31");
		otv.addScrollBar(grid, 0, 200, 200);
		
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
					System.out.println("selection's length== "+ selection.length+"当前行"+selection[i]);
					//values[i]  = otv.getliststore().getValue(selection[i], 1);
					OOMessageDialog.showInfo("选中的信息为"+otv.getliststore().getValue(selection[i], 1), "用户名");
					System.out.println("selection["+i+"]"+selection[i]);
				}
			}
		});
		//grid.add(otv, 0);
		
		OOGrid gridTemp = new OOGrid();
		OOLabel olID = new   OOLabel("ID   :");
		OOLabel olName = new OOLabel("Name:");
		OOLabel olAge = new  OOLabel("Age :");
		 
		//让show变成默认怎么样？！
		olID.show();
		olName.show();
		olAge.show();
		
		oeApple = new OOEntry();
		oeBanana = new OOEntry();
		oeOrange = new OOEntry();
		oeApple.show();
		oeBanana.show();
		oeOrange.show();
		
		gridTemp.add(olID, 0, 4);
		gridTemp.add(oeApple,0,5);
		gridTemp.add(olName,1,4);
		gridTemp.add(oeBanana,1,5);
		gridTemp.add(olAge, 2,4);
		gridTemp.add(oeOrange,2,5);
		gridTemp.show();
		
		grid.add(gridTemp,1,3);
		
		
		OOButton ob = new OOButton("插入");
		ob.show();
		grid.add(ob,2,4);
		ob.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				String ID = oeApple.getText();
				System.out.println(ID);
				if(ID.equalsIgnoreCase(""))
				{
				OOMessageDialog.showError("ID为空，请重新输入","警告");
				oeApple.setText("");
					return;
				}
				String Name = oeBanana.getText();
				if(Name.equalsIgnoreCase(""))
				{
					OOMessageDialog.showError("Name为空，请重新输入","警告");
					oeBanana.setText("");
					return;
				}
				String Age = oeOrange.getText();
				if(Age.equalsIgnoreCase(""))
				{
					oeOrange.setText("");
					OOMessageDialog.showError("Age为空，请重新输入","警告");
					return;
				}
				otv.insertRecord(ID,Name, Age);
				 
			}
		});
		
		
		GTK.gtk_main();
	}


}
