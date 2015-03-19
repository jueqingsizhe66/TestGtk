/**
 * 
 */
package com.jdbc.test;

import java.sql.ResultSet;
import java.sql.SQLException;





import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;



/**
 * @author    Ҷ����
 * @time      2015��2��22������7:48:03
 * @version   com.jdbc.testPhoneCheckUpdate V1.0
 */
import GTKEncapsulate.*;
public class PhoneCheckUpdate
{

	/**
	 * @param args
	 */
	private static  OOLabel olLabel;
	private static  OOEntry oeCerti;
	private static OOButton obClick;
	private static OOBox obBox;
	private static OOWindow owPig;
	public PhoneCheckUpdate()
	{
		olLabel = new  OOLabel("������绰��ǰ��λ��");
		oeCerti = new OOEntry();
		oeCerti.setTextMaxLength(6);
		oeCerti.setText("151010");
		
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
				OOMessageDialog omd = new OOMessageDialog(checkPhoneNumber(text));
				omd.run();
				omd.destroy();
				
			}
		});
	}
	private static String checkPhoneNumber(String PhoneNumber)
	{

		
		PhoneNumber = "%"+PhoneNumber+"%";
		String sql = "select * from phone where MobileNumber like ?";
		ResultSet rs  = null;
		try
		{
			rs = JDBCUtils.executeQuery(sql, PhoneNumber);
			if(!rs.next())
			{
				System.out.println("��ǰ�汾û��"+PhoneNumber+"����Ϣ");
				return "��ǰ�汾û��"+PhoneNumber+"����Ϣ";
				
//				return;
			}
			//rs.next();
			System.out.println(PhoneNumber+"�ֻ�������"+rs.getString("MobileArea")+rs.getString("MobileType"));
			return PhoneNumber+"�ֻ�������"+rs.getString("MobileArea")+rs.getString("MobileType");
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return null;
		}finally
		{
			//����֮��Ͳ�ƨ��
			JDBCUtils.closeAll(rs);
		}
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		PhoneCheckUpdate pcu = new PhoneCheckUpdate();
		
		GTK.gtk_main();
	}

}