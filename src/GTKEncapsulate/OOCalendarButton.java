/**
 * 
 */
package GTKEncapsulate;

import java.text.SimpleDateFormat;
import java.util.*;

import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月10日下午4:14:33
 * @version   GTKEncapsulateOOCalendarButton V1.0
 *                   V2.0 bug修复yyyy年mm月dd日    yyyy年MM月dd日，否则出现奇怪的数字！
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
					// 也可以用  date = ocd.getDate();  作用域问题 ---》有一个模糊地可能！也许内部类有一个
					   //date那就不好办了！！！！！！ 
					// this.date肯定不行！！！ 因为this指向最近的一个类
					OOCalendarButton.this.date = ocd.getDate();  //最好的一种方法
					//OOCalendarButton的this对象。。	
					//SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					OOCalendarButton.this.setLabel(sdf.format(date));
					
				}
				//当意识到 关闭不了时候，加入了destroy
				ocd.destroy();
				
			}
		});
		
	}
}
