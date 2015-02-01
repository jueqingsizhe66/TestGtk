
/**
 * @author 叶昭良
 * @version : 计算器v 2.0  替换掉entry 采用了SpinBox和ComboBox
 *
 */
import javax.swing.JOptionPane;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import java.math.*;
import java.lang.Math;
public class TestCalc2
{

	/**
	 * @param args
	 */
	static int window;

	public static void main(String[] args)
	{
		// TODO 自动生成的方法存根
		//初始化
		GTK.gtk_init();
		//建立窗口  设置成static int window变量
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//设置窗口名称
		GTK.gtk_window_set_title(window, "计算器v2.0");
		//添加窗口
		GTK.gtk_widget_show(window);
		
		//关闭对话框
		GTK.g_signal_connect(window, "destroy", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
		
		//网格布局
	    int houseGrid = GTK.gtk_grid_new();
	    //载入计算机控件
	   
	    Calc(window,houseGrid,0);
	    
	    //载入到windows当中
		GTK.gtk_container_add(window, houseGrid);
		//启动循环
		GTK.gtk_main();
		
	}
	/**
	 * 
	 * @param window     窗口的标识
 	 * @param houseGrid  整租房间的标识
	 * @param start      设置整租房间的起始行数
	 */
	public static void Calc(int window,int houseGrid,int start)
	{
		final int sbOne = GTK.gtk_spin_button_new_with_range(-32768, 32767, 1);
		final int sbAnother = GTK.gtk_spin_button_new_with_range(-32768, 32767, 1);
		GTK.gtk_spin_button_set_value(sbOne, 12.0);
		GTK.gtk_spin_button_set_value(sbAnother, 4.0);
		final int cbbOperator = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(cbbOperator, "+");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "-");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "*");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "/");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "%");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "^");
		
		int label1 = GTK.gtk_label_new("=");
		final int label2 = GTK.gtk_label_new("我知道答案是什么");
		
		// Box用于存储  + - operator



		//加入整租
		
		GTK.gtk_grid_attach(houseGrid, sbOne, 0, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, cbbOperator, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonPlus, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonMinus, 1, start+1, 1, 1);
		GTK.gtk_grid_attach(houseGrid, sbAnother, 2, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label1, 3, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label2, 4, start, 1, 1);
		
		//显示控件
		GTK.gtk_widget_show(sbOne);
		GTK.gtk_widget_show(sbAnother);
		GTK.gtk_widget_show(cbbOperator);

		GTK.gtk_widget_show(label1);
		GTK.gtk_widget_show(label2);
		//GTK.gtk_widget_show(box);
		GTK.gtk_widget_show(houseGrid);
		
		
		//加入事件控制机制 
		GTK.g_signal_connect(cbbOperator, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				
				double plus1 = GTK.gtk_spin_button_get_value(sbOne);
				double plus2 = GTK.gtk_spin_button_get_value(sbAnother);
				
				//增加一个 只读模式的entry
				//GTK.gtk_editable_set_editable(entryOne, false); //只读不能写入。
				
				String cbbString = GTK.gtk_combo_box_text_get_active_text(cbbOperator);
				//panduan(plus1,entryOne);
				panduan(plus2,sbAnother);
				double sum1 = 0;
				if(cbbString.equalsIgnoreCase("+"))
				{
					 sum1 = plus1 + plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("-"))
				{
					sum1 = plus1 -plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("*"))
				{
					sum1 = plus1 * plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("/"))
				{
					panduan(plus2,sbAnother);
					sum1 = plus1 / plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("%"))
				{
					panduan(plus2,sbAnother);
					sum1 = plus1 % plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("^"))
				{
					//String temp = Double.toString(plus2);
					
					sum1 = Math.pow(plus1,plus2);
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}
				
			}
		}, null);
			
		
	}
	/**
	 * 
	 * @param plus1  entry1的数字字符串
	 * @param entry1 entry1的标识
	 */
	public static void panduan(double plus1,int sbAnother)
	{
		if(plus1 == 0)
		{
			JOptionPane.showMessageDialog(null, "被除数不能为0,已经置为空 请重新输入");
			GTK.gtk_spin_button_set_value(sbAnother, 1.0);;
			return;
		}
	}


}
