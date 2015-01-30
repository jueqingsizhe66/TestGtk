
/**
 * @author 叶昭良
 * @timestart  18:55
 * @timeEnd    19:50
 * @version : 计算器v1.0
 *
 */
import javax.swing.JOptionPane;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
public class TestCalc
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
		GTK.gtk_window_set_title(window, "计算器v1.0");
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
		final int entryOne = GTK.gtk_entry_new();
		final int entryAnother = GTK.gtk_entry_new();
		
		final int buttonPlus = GTK.gtk_button_new_with_label("+");
		int buttonMinus= GTK.gtk_button_new_with_label("-");
		
		int label1 = GTK.gtk_label_new("=");
		final int label2 = GTK.gtk_label_new("我知道答案是什么");
		
		// Box用于存储  + - operator
		int box =  GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 1);
		GTK.gtk_box_pack_start(box, buttonPlus, false, false, 0);
		GTK.gtk_box_pack_start(box, buttonMinus, false, false, 0);


		//加入整租
		
		GTK.gtk_grid_attach(houseGrid, entryOne, 0, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, box, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonPlus, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonMinus, 1, start+1, 1, 1);
		GTK.gtk_grid_attach(houseGrid, entryAnother, 2, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label1, 3, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label2, 4, start, 1, 1);
		
		//显示控件
		GTK.gtk_widget_show(entryOne);
		GTK.gtk_widget_show(entryAnother);
		GTK.gtk_widget_show(box);
		GTK.gtk_widget_show(buttonPlus);
		GTK.gtk_widget_show(buttonMinus);
		GTK.gtk_widget_show(label1);
		GTK.gtk_widget_show(label2);
		//GTK.gtk_widget_show(box);
		GTK.gtk_widget_show(houseGrid);
		
		
		//加入事件控制机制 
		GTK.g_signal_connect(buttonPlus, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根

				String plus1 = GTK.gtk_entry_get_text(entryOne);
				String plus2 = GTK.gtk_entry_get_text(entryAnother);
				// 改进  增加最大的数目
				GTK.gtk_entry_set_max_length(entryOne, 5);
				GTK.gtk_entry_set_max_length(entryAnother, 5);
				
				//增加一个 只读模式的entry
				//GTK.gtk_editable_set_editable(entryOne, false); //只读不能写入。
				
				
				
				panduan(plus1,entryOne);
				panduan(plus2,entryAnother);
				int sum1 = Integer.parseInt(plus1)+Integer.parseInt(plus2);
				GTK.gtk_label_set_text(label2, Integer.toString(sum1));
			}
		}, null);
		
		
		GTK.g_signal_connect(buttonMinus, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				String plus1 = GTK.gtk_entry_get_text(entryOne);
				String plus2 = GTK.gtk_entry_get_text(entryAnother);
				
				int sum1 = Integer.parseInt(plus1)-Integer.parseInt(plus2);
				GTK.gtk_label_set_text(label2, Integer.toString(sum1));
			}
		}, null);
		
		
	}
	/**
	 * 
	 * @param plus1  entry1的数字字符串
	 * @param entry1 entry1的标识
	 */
	public static void panduan(String plus1,int entry1)
	{

		char[] panduanOne = plus1.toCharArray();
		for(int i = 0 ; i < panduanOne.length; i++)
		{
			if(panduanOne[i] >= 47 && panduanOne[i] <= 57)
			{
				continue;
			}else
			{
				JOptionPane.showMessageDialog(null, "您输入的Entry项不是整数数字,已经置为空 请重新输入");
				GTK.gtk_entry_set_text(entry1, "");
				return;
			}
		}

	}

}
