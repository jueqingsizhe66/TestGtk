/**
 * 
 */
package TestGTKEncapsulate;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGSourceFunc;

import GTKEncapsulate.*;

/**
 * @author    叶昭良
 * @time      2015年2月10日下午1:17:40
 * @version   TestGTKEncapsulateLEDClock V1.0
 */
public class LEDClock extends OOWindow
{

	/**
	 * @param args
	 */
	private OOImage oiHourShi;
	
	private OOImage oiHourGe;
	private OOImage oiMinuteShi;
	private OOImage oiMinuteGe;
	private OOImage oiSecondShi;
	private OOImage oiSecondGe;
	private int hourShi;
	private int hourGe;
	private int minuteShi;
	private int minuteGe;
	private int secondShi;
	private int secondGe;
	private OOBox obApple;
	private OOImage colon1;
	private OOImage colon2;
	private OOLabel olDate;
	public LEDClock()
	{
		obApple = new OOBox();
		obApple.show();
		
		olDate = new OOLabel("");
		olDate.show();

		obApple.addWidget(olDate);

		oiHourShi = new OOImage();
		oiHourGe = new OOImage();
		colon1 = new OOImage();
		oiMinuteShi = new OOImage();
		oiMinuteGe = new OOImage();
		colon2 = new OOImage();
		oiSecondShi = new OOImage();
		oiSecondGe = new OOImage();
		obApple.addWidget(oiHourShi);
		obApple.addWidget(oiHourGe);
		obApple.addWidget(colon1);
		obApple.addWidget(oiMinuteShi);
		obApple.addWidget(oiMinuteGe);
		obApple.addWidget(colon2);
		obApple.addWidget(oiSecondShi);
		obApple.addWidget(oiSecondGe);
		getTime();
		
		GTK.g_timeout_add(1000, new IGSourceFunc()
		{
			
			@Override
			public boolean execute(Object userdata)
			{
				// TODO Auto-generated method stub
				getTime();
				return true;
			}
		}, null);
		this.setWidgetSize(200, 100);
		this.add(obApple);
	}
	
	public void getTime()
	{
		String[] table = {"零","壹","贰","叁","肆","伍","陆","柒","扒","玖"};
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年-MM月-dd日 ");
		String labelText = sdf.format(date);
		//System.out.println(labelText);
		olDate.setText(labelText);
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int hour = cl.get(Calendar.HOUR_OF_DAY);
		int minute = cl.get(Calendar.MINUTE);
		int second = cl.get(Calendar.SECOND);
		
		hourShi = hour/10;
		hourGe  = hour%10;
		minuteShi = minute/10;
		minuteGe = minute%10;
		secondShi = second/10;
		secondGe  = second%10;
		
		
		oiHourShi.setResourceImage("ledclock//"+hourShi+".png");
		oiHourGe.setResourceImage("ledclock//"+hourGe+".png");
		colon1.setResourceImage("ledclock//"+(secondGe%2==1?"colon":"empty")+".png");
		oiMinuteShi.setResourceImage("ledclock//"+minuteShi+".png");
		oiMinuteGe.setResourceImage("ledclock//"+minuteGe+".png");
		colon2.setResourceImage("ledclock//"+(secondGe%2==1?"colon":"empty")+".png");
		oiSecondShi.setResourceImage("ledclock//"+secondShi+".png");
		oiSecondGe.setResourceImage("ledclock//"+secondGe+".png");
		
		if( minute ==0 && second == 0)
		{
			System.out.println("北京时间"+table[hourShi]+table[hourGe]+"整");
			OOMessageDialog.showInfo("北京时间"+table[hourShi]+table[hourGe]+"整", "整点消息");
		}
	
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		LEDClock lc = new LEDClock();
		lc.show();
		lc.setExitAfterDestroy(true);
		GTK.gtk_main();
	}

}
