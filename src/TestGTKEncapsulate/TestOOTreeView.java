/**
 * 
 */
package TestGTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import GTKEncapsulate.*;
/**
 * @author    Ҷ����
 * @time      2015��2��9������3:44:20
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
		
		//���ò���
		OOGrid  grid = new OOGrid();
		grid.show();
		ow.add(grid);
		
		//���ò���
		otv = new  OOTreeView();
		otv.addField("ID", 0);
		otv.addField("Name", 1);
		otv.addField("Age", 2);
		
		otv.createModel(3);
		otv.setFieldValue("001", "yezhao", "21");
		otv.setFieldValue("002", "huowa", "32");
		otv.setFieldValue("003", "yefeng", "31");
		otv.addScrollBar(grid, 0, 200, 200);
		
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
					System.out.println("selection's length== "+ selection.length+"��ǰ��"+selection[i]);
					//values[i]  = otv.getliststore().getValue(selection[i], 1);
					OOMessageDialog.showInfo("ѡ�е���ϢΪ"+otv.getliststore().getValue(selection[i], 1), "�û���");
					System.out.println("selection["+i+"]"+selection[i]);
				}
			}
		});
		//grid.add(otv, 0);
		
		OOGrid gridTemp = new OOGrid();
		OOLabel olID = new   OOLabel("ID   :");
		OOLabel olName = new OOLabel("Name:");
		OOLabel olAge = new  OOLabel("Age :");
		 
		//��show���Ĭ����ô������
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
		
		
		OOButton ob = new OOButton("����");
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
				OOMessageDialog.showError("IDΪ�գ�����������","����");
				oeApple.setText("");
					return;
				}
				String Name = oeBanana.getText();
				if(Name.equalsIgnoreCase(""))
				{
					OOMessageDialog.showError("NameΪ�գ�����������","����");
					oeBanana.setText("");
					return;
				}
				String Age = oeOrange.getText();
				if(Age.equalsIgnoreCase(""))
				{
					oeOrange.setText("");
					OOMessageDialog.showError("AgeΪ�գ�����������","����");
					return;
				}
				otv.insertRecord(ID,Name, Age);
				 
			}
		});
		
		
		GTK.gtk_main();
	}


}