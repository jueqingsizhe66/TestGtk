/**
 * 
 */
package GTKEncapsulate;

import java.text.SimpleDateFormat;
import java.util.*;

import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��10������4:14:33
 * @version   GTKEncapsulateOOCalendarButton V1.0
 *                   V2.0 bug�޸�yyyy��mm��dd��    yyyy��MM��dd�գ����������ֵ����֣�
 */
public class OOCalendarButton extends OOButton
{
	private Date date;
	public OOCalendarButton(String labelName)
	{
		super(labelName);
		this.addClickedListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				OOCalendarDialog ocd = new OOCalendarDialog();
				OOResponseType ret = ocd.run();
				if(ret == OOResponseType.OK)
				{
					// Ҳ������  date = ocd.getDate();  ���������� ---����һ��ģ���ؿ��ܣ�Ҳ���ڲ�����һ��
					   //date�ǾͲ��ð��ˣ����������� 
					// this.date�϶����У����� ��Ϊthisָ�������һ����
					OOCalendarButton.this.date = ocd.getDate();  //��õ�һ�ַ���
					//OOCalendarButton��this���󡣡�	
					//SimpleDateFormat sdf = new SimpleDateFormat("yyyy��mm��dd��");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
					OOCalendarButton.this.setLabel(sdf.format(date));
					
				}
				//����ʶ�� �رղ���ʱ�򣬼�����destroy
				ocd.destroy();
				
			}
		});
		
	}
}