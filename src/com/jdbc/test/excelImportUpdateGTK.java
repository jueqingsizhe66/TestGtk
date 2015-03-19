/**
 * 
 */
package com.jdbc.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import GTKEncapsulate.OOBox;
import GTKEncapsulate.OOButton;
import GTKEncapsulate.OOEntry;
import GTKEncapsulate.OOLabel;
import GTKEncapsulate.OOMessageDialog;
import GTKEncapsulate.OOWindow;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��22������8:10:07
 * @version   com.jdbc.testexcelImportUpdateGTK V1.0
 */
public class excelImportUpdateGTK
{

	/**
	 * @param args
	 */
	private static  OOLabel olLabel;
	private static  OOEntry oeCerti;
	private static OOButton obClick;
	private static OOBox obBox;
	private static OOWindow owPig;
	public excelImportUpdateGTK()
	{
		olLabel = new  OOLabel("����������֤��ǰ��λ��");
		oeCerti = new OOEntry();
		oeCerti.setTextMaxLength(6);
		oeCerti.setText("350628");
		
		obBox = new OOBox();
		obClick = new OOButton("��ѯ");
		owPig = new OOWindow();
		owPig.setWidgetSize(200, 50);
		//show
		owPig.show();
		owPig.setExitAfterDestroy(true);
		olLabel.show();
		oeCerti.show();
		obBox.show();
		obClick.show();
		
		//contains
		owPig.add(obBox);	
		obBox.add(olLabel);
		obBox.add(oeCerti);
		obBox.add(obClick);
		
		//�����¼�
		obClick.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				String text= oeCerti.getText();
				OOMessageDialog omd = new OOMessageDialog(checkNumber(text));
				omd.run();
				omd.destroy();
				
			}
		});
	}
	
	public static String checkNumber(String id)
	{
		ResultSet rs = null;
		String sql = "select * from certificate where code like ?";
		try
		{
			rs = JDBCUtils.executeQuery(sql, id);
			if(!rs.next())
			{
				System.out.println("����Ϊ��");
				return null;
			}
			
			System.out.println("����֤��ǰ��λΪ"+id+"�ĸ��ǻ��߽�������"+rs.getString("Addr"));
			return "����֤��ǰ��λΪ"+id+"�ĸ��ǻ��߽�������"+rs.getString("Addr");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return null;
		}finally
		{
			//����֮�� ��һ��ƨ��
			JDBCUtils.closeQuietly(rs);
		}
		
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		excelImportUpdateGTK eiug = new excelImportUpdateGTK();
		GTK.gtk_main();
	}

}