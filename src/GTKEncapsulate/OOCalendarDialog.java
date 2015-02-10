/**
 * 
 */
package GTKEncapsulate;

import java.util.Date;

import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月10日下午3:02:08
 * @version   GTKEncapsulateOOCalendarDialog V1.0
 *                           V2.0 终于明白了response的作用是什么，让添加到OODialog的控件具有什么的信号！
 *                                         比如你添加OOButton空间到OODialog，当点击按钮，发出一个OOResponseTyep
 *                                         的ok信号就可以用 this.response(OOResponseType.OK)来执行
 *                                         然后你就可以让这个对话框给run起来，接受返回值的信号！判断到底要做什么行为
 *                           V3.0  改变单击事件变为双击事件，比较符合常理
 */
public  class OOCalendarDialog extends OODialog
{

	/**
	 * @param args
	 */
	private OOButton btnOk ;
	private OOButton btnCancel;
	private OOCalendar ocl;
	
	public Date getDate()
	{
		return ocl.getDate();
	}

	public OOCalendarDialog()
	{
		ocl = new OOCalendar();
		ocl.addDaySelectedDoubleClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				response(OOResponseType.OK);
			}
		});
		ocl.show();
		this.createContentArea().add(ocl);
		
		btnOk = new OOButton("确认");
		btnOk.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				//OOMessageDialog.showInfo("您好	"+ocl.getYear()+ocl.getMonth()+ocl.getDay(), "消息");
				response(OOResponseType.OK);
			}
		});
		btnCancel = new OOButton("取消");
		btnCancel.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				//ocd.destroy();
				response(OOResponseType.CANCEL);
			}
		});
		btnOk.show();
		btnCancel.show();
		this.createActionArea().addWidget(btnOk);
		this.createActionArea().addWidget(btnCancel);
	}


}
