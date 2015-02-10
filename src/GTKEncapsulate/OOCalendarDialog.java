/**
 * 
 */
package GTKEncapsulate;

import java.util.Date;

import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��10������3:02:08
 * @version   GTKEncapsulateOOCalendarDialog V1.0
 *                           V2.0 ����������response��������ʲô�������ӵ�OODialog�Ŀؼ�����ʲô���źţ�
 *                                         ����������OOButton�ռ䵽OODialog���������ť������һ��OOResponseTyep
 *                                         ��ok�źžͿ����� this.response(OOResponseType.OK)��ִ��
 *                                         Ȼ����Ϳ���������Ի����run���������ܷ���ֵ���źţ��жϵ���Ҫ��ʲô��Ϊ
 *                           V3.0  �ı䵥���¼���Ϊ˫���¼����ȽϷ��ϳ���
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
		
		btnOk = new OOButton("ȷ��");
		btnOk.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				//OOMessageDialog.showInfo("����	"+ocl.getYear()+ocl.getMonth()+ocl.getDay(), "��Ϣ");
				response(OOResponseType.OK);
			}
		});
		btnCancel = new OOButton("ȡ��");
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