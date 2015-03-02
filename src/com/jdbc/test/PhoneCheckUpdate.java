/**
 * 
 */
package com.jdbc.test;

import java.sql.ResultSet;
import java.sql.SQLException;





import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;



/**
 * @author    叶昭良
 * @time      2015年2月22日下午7:48:03
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
		olLabel = new  OOLabel("请输入电话的前六位：");
		oeCerti = new OOEntry();
		oeCerti.setTextMaxLength(6);
		oeCerti.setText("151010");
		
		obBox = new OOBox();
		obClick = new OOButton("查询");
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
		
		//添加事件
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
				System.out.println("当前版本没有"+PhoneNumber+"的信息");
				return "当前版本没有"+PhoneNumber+"的信息";
				
//				return;
			}
			//rs.next();
			System.out.println(PhoneNumber+"手机号来自"+rs.getString("MobileArea")+rs.getString("MobileType"));
			return PhoneNumber+"手机号来自"+rs.getString("MobileArea")+rs.getString("MobileType");
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return null;
		}finally
		{
			//用完之后就差屁股
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
